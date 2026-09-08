package dev.dov.metalj.demo;

import dev.dov.metalj.arguments.MTLArgumentEncoder;
import dev.dov.metalj.commands.encoders.MTLRenderCommandEncoder;
import dev.dov.metalj.commands.encoders.MTLRenderStages;
import dev.dov.metalj.commands.encoders.MTLResourceUsage;
import dev.dov.metalj.commands.passes.MTLClearColor;
import dev.dov.metalj.commands.passes.MTLLoadAction;
import dev.dov.metalj.commands.passes.MTLRenderPassDescriptor;
import dev.dov.metalj.commands.passes.MTLStoreAction;
import dev.dov.metalj.debug.MTLCaptureDescriptor;
import dev.dov.metalj.debug.MTLCaptureDestination;
import dev.dov.metalj.debug.MTLCaptureManager;
import dev.dov.metalj.debug.MTLCommonCounter;
import dev.dov.metalj.debug.MTLCounterResultTimestamp;
import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.debug.MTLCounterSampleBufferDescriptor;
import dev.dov.metalj.debug.MTLCounterSamplingPoint;
import dev.dov.metalj.debug.MTLCounterSet;
import dev.dov.metalj.device.CAMetalLayer;
import dev.dov.metalj.device.MTLCommandQueue;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.device.Metal;
import dev.dov.metalj.device.NSWindow;
import dev.dov.metalj.objc.CGSize;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineDescriptor;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.pipelines.vertex.MTLAttributeFormat;
import dev.dov.metalj.raytracing.MTLAccelerationStructure;
import dev.dov.metalj.raytracing.MTLAccelerationStructureSizes;
import dev.dov.metalj.raytracing.MTLAccelerationStructureTriangleGeometryDescriptor;
import dev.dov.metalj.raytracing.MTLPrimitiveAccelerationStructureDescriptor;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.MTLStorageMode;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.textures.MTLPixelFormat;
import java.lang.foreign.Arena;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWNativeCocoa;

public class Demo {
    private static final String SOURCE = """
            #include <metal_stdlib>
            #include <metal_raytracing>
            using namespace metal;
            using namespace raytracing;
            struct V {
                float4 position [[position]];
                float2 uv;
            };
            struct Scene {
                primitive_acceleration_structure world [[id(0)]];
                device float4 *tint [[id(1)]];
            };

            vertex V vs(uint id [[vertex_id]]) {
                float2 p = float2((id << 1) & 2, id & 2);
                V out;
                out.position = float4(p * 2 - 1, 0, 1);
                out.uv = p;
                return out;
            }
            fragment float4 fs(V in [[stage_in]], device Scene &scene [[buffer(0)]]) {
                ray r;
                r.origin = float3((in.uv * 2 - 1) * float2(1.78, 1), -1);
                r.direction = float3(0, 0, 1);
                r.min_distance = 0;
                r.max_distance = 100;
                intersector<triangle_data> tracer;
                tracer.assume_geometry_type(geometry_type::triangle);
                auto hit = tracer.intersect(r, scene.world);
                if (hit.type == intersection_type::none) {
                    return float4(0, 0, 0, 1);
                }
                float2 uv = hit.triangle_barycentric_coord;
                float3 weights = float3(1 - uv.x - uv.y, uv.x, uv.y);
                return float4(weights, 1) * scene.tint[0];
            }
            """;

    private static MTLCounterSampleBuffer timestamps(MTLDevice device) {
        if (!device.supportsCounterSampling(MTLCounterSamplingPoint.MTLCounterSamplingPointAtStageBoundary)) {
            return null;
        }
        var sets = device.counterSets();
        var wanted = MTLCommonCounter.MTLCommonCounterSetTimestamp().UTF8String();
        for (long i = 0; i < sets.count(); i++) {
            var set = MTLCounterSet.of(sets.objectAtIndex(i));
            if (!wanted.equals(set.name().UTF8String())) {
                continue;
            }
            var descriptor = MTLCounterSampleBufferDescriptor.new_();
            descriptor.setCounterSet(set);
            descriptor.setStorageMode(MTLStorageMode.MTLStorageModeShared);
            descriptor.setSampleCount(4);
            return device.newCounterSampleBufferWithDescriptor(descriptor);
        }
        return null;
    }

    private static void report(MTLCounterSampleBuffer timestamps) {
        var data = timestamps.resolveCounterRange(0, 4).bytes();
        long begin = MTLCounterResultTimestamp.timestamp(data, 0);
        long end = MTLCounterResultTimestamp.timestamp(data, 3);
        System.out.println("gpu: " + (end - begin) / 1000.0 + " us");
    }

    private static MTLAccelerationStructure triangle(MTLDevice device, MTLCommandQueue queue) {
        var geometry = MTLAccelerationStructureTriangleGeometryDescriptor.descriptor();
        MTLBuffer vertices;
        try (var arena = Arena.ofConfined()) {
            float[] points = {0, 0.8f, 0, -0.8f, -0.6f, 0, 0.8f, -0.6f, 0};
            var bytes = arena.allocateFrom(ObjC.FLOAT, points);
            vertices = device.newBufferWithBytes(bytes, bytes.byteSize(),
                    MTLResourceOptions.MTLResourceStorageModeShared);
        }
        geometry.setVertexBuffer(vertices);
        geometry.setVertexFormat(MTLAttributeFormat.MTLAttributeFormatFloat3);
        geometry.setVertexStride(12);
        geometry.setTriangleCount(1);
        var descriptor = MTLPrimitiveAccelerationStructureDescriptor.descriptor();
        descriptor.setGeometryDescriptors(NSArray.arrayWithObjects(geometry));
        try (var arena = Arena.ofConfined()) {
            var sizes = device.accelerationStructureSizesWithDescriptor(arena, descriptor);
            var structure = device.newAccelerationStructureWithSize(
                    MTLAccelerationStructureSizes.accelerationStructureSize(sizes));
            var scratch = device.newBufferWithLength(
                    Math.max(1, MTLAccelerationStructureSizes.buildScratchBufferSize(sizes)),
                    MTLResourceOptions.MTLResourceStorageModePrivate);
            var cmd = queue.commandBuffer();
            var encoder = cmd.accelerationStructureCommandEncoder();
            encoder.buildAccelerationStructure(structure, descriptor, scratch, 0);
            encoder.endEncoding();
            cmd.commit();
            cmd.waitUntilCompleted();
            return structure;
        }
    }

    public static void main(String[] args) {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("glfw");
        }
        GLFW.glfwWindowHint(GLFW.GLFW_CLIENT_API, GLFW.GLFW_NO_API);
        long window = GLFW.glfwCreateWindow(960, 540, "MetalJ", 0, 0);
        if (window == 0) {
            throw new IllegalStateException("window");
        }
        var device = Metal.MTLCreateSystemDefaultDevice();
        var trace = System.getProperty("user.dir") + "/demo.gputrace";
        boolean capturing = args.length > 0 && args[0].equals("--capture");
        var cocoa = NSWindow.of(GLFWNativeCocoa.glfwGetCocoaWindow(window));
        var layer = CAMetalLayer.layer();
        layer.setDevice(device);
        layer.setPixelFormat(MTLPixelFormat.MTLPixelFormatBGRA8Unorm);
        layer.setContentsScale(cocoa.backingScaleFactor());
        layer.setDisplaySyncEnabled(false);
        try (var arena = Arena.ofConfined()) {
            layer.setDrawableSize(CGSize.of(arena, 960 * cocoa.backingScaleFactor(), 540 * cocoa.backingScaleFactor()));
        }
        var view = cocoa.contentView();
        view.setWantsLayer(true);
        view.setLayer(layer);
        System.out.println("device: " + device.name().UTF8String());
        System.out.println("argument buffers tier: " + (device.argumentBuffersSupport() + 1));
        if (!device.supportsRaytracingFromRender()) {
            throw new IllegalStateException("no raytracing from render");
        }
        var queue = device.newCommandQueue();
        var capture = MTLCaptureManager.sharedCaptureManager();
        if (capturing) {
            if (!capture.supportsDestination(MTLCaptureDestination.MTLCaptureDestinationGPUTraceDocument)) {
                throw new IllegalStateException("set MTL_CAPTURE_ENABLED=1");
            }
            var request = MTLCaptureDescriptor.new_();
            request.setCaptureObject(queue);
            request.setDestination(MTLCaptureDestination.MTLCaptureDestinationGPUTraceDocument);
            request.setOutputURL(NSURL.fileURLWithPath(trace));
            capture.startCaptureWithDescriptor(request);
        }
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(SOURCE), MTLCompileOptions.new_());
        var vertex = library.newFunctionWithName(NSString.stringWithUTF8String("vs"));
        var fragment = library.newFunctionWithName(NSString.stringWithUTF8String("fs"));
        var descriptor = MTLRenderPipelineDescriptor.new_();
        descriptor.setVertexFunction(vertex);
        descriptor.setFragmentFunction(fragment);
        descriptor.colorAttachments()
                .objectAtIndexedSubscript(0)
                .setPixelFormat(MTLPixelFormat.MTLPixelFormatBGRA8Unorm);
        var pipeline = device.newRenderPipelineStateWithDescriptor(descriptor);
        var world = triangle(device, queue);
        var tint = device.newBufferWithLength(16, MTLResourceOptions.MTLResourceStorageModeShared);
        var scene = fragment.newArgumentEncoderWithBufferIndex(0);
        var arguments = device.newBufferWithLength(scene.encodedLength(),
                MTLResourceOptions.MTLResourceStorageModeShared);
        arguments.setLabel(NSString.stringWithUTF8String("scene"));
        scene.setArgumentBuffer(arguments, 0);
        scene.setAccelerationStructure(world, 0);
        scene.setBuffer(tint, 0, 1);
        var timestamps = timestamps(device);
        var scope = capture.newCaptureScopeWithCommandQueue(queue);
        scope.setLabel(NSString.stringWithUTF8String("frame"));
        capture.setDefaultCaptureScope(scope);
        long frames = 0;
        var start = System.nanoTime();
        while (!GLFW.glfwWindowShouldClose(window)) {
            GLFW.glfwPollEvents();
            var drawable = layer.nextDrawable();
            if (drawable.isNull()) {
                continue;
            }
            double t = (System.nanoTime() - start) / 1e9;
            write(tint, (float) (0.5 + 0.5 * Math.sin(t)), 0.4f, (float) (0.5 + 0.5 * Math.cos(t)));
            var pass = MTLRenderPassDescriptor.renderPassDescriptor();
            var color = pass.colorAttachments().objectAtIndexedSubscript(0);
            color.setTexture(drawable.texture());
            color.setLoadAction(MTLLoadAction.MTLLoadActionClear);
            color.setStoreAction(MTLStoreAction.MTLStoreActionStore);
            try (var arena = Arena.ofConfined()) {
                color.setClearColor(MTLClearColor.of(arena, 0, 0, 0, 1));
            }
            if (timestamps != null) {
                var sample = pass.sampleBufferAttachments().objectAtIndexedSubscript(0);
                sample.setSampleBuffer(timestamps);
                sample.setStartOfVertexSampleIndex(0);
                sample.setEndOfVertexSampleIndex(1);
                sample.setStartOfFragmentSampleIndex(2);
                sample.setEndOfFragmentSampleIndex(3);
            }
            scope.beginScope();
            var cmd = queue.commandBuffer();
            cmd.setLabel(NSString.stringWithUTF8String("frame " + frames));
            var encoder = cmd.renderCommandEncoderWithDescriptor(pass);
            encoder.pushDebugGroup(NSString.stringWithUTF8String("triangle"));
            encoder.setRenderPipelineState(pipeline);
            encoder.useResource(world, MTLResourceUsage.MTLResourceUsageRead,
                    MTLRenderStages.MTLRenderStageFragment);
            encoder.useResource(tint, MTLResourceUsage.MTLResourceUsageRead,
                    MTLRenderStages.MTLRenderStageFragment);
            encoder.setFragmentBuffer(arguments, 0, 0);
            encoder.drawPrimitives(MTLRenderCommandEncoder.MTLPrimitiveTypeTriangleStrip, 0, 4);
            encoder.popDebugGroup();
            encoder.endEncoding();
            cmd.presentDrawable(drawable);
            cmd.commit();
            scope.endScope();
            if (timestamps != null && frames % 120 == 0) {
                cmd.waitUntilCompleted();
                report(timestamps);
            }
            if (capturing && frames == 1) {
                capture.stopCapture();
                System.out.println("capture written to " + trace);
            }
            frames++;
        }
        if (capturing) {
            capture.stopCapture();
        }
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    private static void write(MTLBuffer tint, float r, float g, float b) {
        var contents = tint.contents();
        contents.set(ObjC.FLOAT, 0, r);
        contents.set(ObjC.FLOAT, 4, g);
        contents.set(ObjC.FLOAT, 8, b);
        contents.set(ObjC.FLOAT, 12, 1);
    }
}
