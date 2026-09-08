package dev.dov.metalj.demo;

import dev.dov.metalj.commands.passes.MTLClearColor;
import dev.dov.metalj.commands.passes.MTLLoadAction;
import dev.dov.metalj.commands.encoders.MTLRenderCommandEncoder;
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
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.device.Metal;
import dev.dov.metalj.device.NSWindow;
import dev.dov.metalj.objc.CGSize;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.resources.MTLStorageMode;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineDescriptor;
import java.lang.foreign.Arena;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWNativeCocoa;

public class Demo {
    private static final String SOURCE = """
            #include <metal_stdlib>
            using namespace metal;
            struct V {
                float4 position [[position]];
                float2 uv;
            };
            
            vertex V vs(uint id [[vertex_id]]) {
                float2 p = float2((id << 1) & 2, id & 2);
                V out;
                out.position = float4(p * 2 - 1, 0, 1);
                out.uv = p;
                return out;
            }
            fragment float4 fs(V in [[stage_in]]) {
                return float4(in.uv, 0.5, 1);
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
        layer.setPixelFormat(80);
        layer.setContentsScale(cocoa.backingScaleFactor());
        layer.setDisplaySyncEnabled(false);
        try (var arena = Arena.ofConfined()) {
            layer.setDrawableSize(CGSize.of(arena, 960 * cocoa.backingScaleFactor(), 540 * cocoa.backingScaleFactor()));
        }
        var view = cocoa.contentView();
        view.setWantsLayer(true);
        view.setLayer(layer);
        System.out.println("device: " + device.name().UTF8String());
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
        var descriptor = MTLRenderPipelineDescriptor.new_();
        descriptor.setVertexFunction(library.newFunctionWithName(NSString.stringWithUTF8String("vs")));
        descriptor.setFragmentFunction(library.newFunctionWithName(NSString.stringWithUTF8String("fs")));
        descriptor.colorAttachments()
                .objectAtIndexedSubscript(0)
                .setPixelFormat(80);
        var pipeline = device.newRenderPipelineStateWithDescriptor(descriptor);
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
            var pass = MTLRenderPassDescriptor.renderPassDescriptor();
            var color = pass.colorAttachments().objectAtIndexedSubscript(0);
            color.setTexture(drawable.texture());
            color.setLoadAction(MTLLoadAction.MTLLoadActionClear);
            color.setStoreAction(MTLStoreAction.MTLStoreActionStore);
            try (var arena = Arena.ofConfined()) {
                color.setClearColor(MTLClearColor.of(arena, 0.5 + 0.5 * Math.sin(t), 0.2, 0.5 + 0.5 * Math.cos(t), 1));
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
}
