package dev.dov.metalj.example;

import dev.dov.metalj.commands.encoders.MTLRenderCommandEncoder;
import dev.dov.metalj.commands.passes.MTLClearColor;
import dev.dov.metalj.commands.passes.MTLLoadAction;
import dev.dov.metalj.commands.passes.MTLRenderPassDescriptor;
import dev.dov.metalj.commands.passes.MTLStoreAction;
import dev.dov.metalj.device.CAMetalLayer;
import dev.dov.metalj.device.MTLCommandQueue;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.device.Metal;
import dev.dov.metalj.device.NSWindow;
import dev.dov.metalj.objc.CGSize;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.depth.MTLCompareFunction;
import dev.dov.metalj.pipelines.depth.MTLDepthStencilDescriptor;
import dev.dov.metalj.pipelines.depth.MTLDepthStencilState;
import dev.dov.metalj.pipelines.render.MTLMeshRenderPipelineDescriptor;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineState;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.MTLStorageMode;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.textures.MTLPixelFormat;
import dev.dov.metalj.resources.textures.MTLTexture;
import dev.dov.metalj.resources.textures.MTLTextureDescriptor;
import dev.dov.metalj.resources.textures.MTLTextureUsage;
import java.lang.foreign.Arena;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWNativeCocoa;

public class Mesh {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int SIDE = 20;
    private static final int CUBES = SIDE * SIDE * SIDE;
    private static final float SPACING = 3.4f;
    private static final long UNIFORMS = 96;

    private long window;
    private MTLDevice device;
    private CAMetalLayer layer;
    private MTLCommandQueue queue;
    private MTLRenderPipelineState pipeline;
    private MTLDepthStencilState depthState;
    private MTLTexture depth;
    private MTLBuffer uniforms;
    private double scale;
    private long start;
    private long frames;
    private long lastReport;
    private long reportedFrames;

    public static void main(String[] args) {
        var mesh = new Mesh();
        mesh.init();
        mesh.run();
        mesh.cleanup();
    }

    private void init() {
        GLFW.glfwInit();
        GLFW.glfwWindowHint(GLFW.GLFW_CLIENT_API, GLFW.GLFW_NO_API);
        window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "Mesh", 0, 0);
        device = Metal.MTLCreateSystemDefaultDevice();
        var cocoa = NSWindow.of(GLFWNativeCocoa.glfwGetCocoaWindow(window));
        scale = cocoa.backingScaleFactor();
        layer = CAMetalLayer.layer();
        layer.setDevice(device);
        layer.setPixelFormat(MTLPixelFormat.MTLPixelFormatBGRA8Unorm);
        layer.setContentsScale(scale);
        layer.setDisplaySyncEnabled(false);
        try (var arena = Arena.ofConfined()) {
            layer.setDrawableSize(CGSize.of(arena, WIDTH * scale, HEIGHT * scale));
        }
        var view = cocoa.contentView();
        view.setWantsLayer(true);
        view.setLayer(layer);
        queue = device.newCommandQueue();
        pipeline = pipeline();
        depthState = depthState();
        depth = depthTexture();
        uniforms = device.newBufferWithLength(UNIFORMS, MTLResourceOptions.MTLResourceStorageModeShared);
        start = System.nanoTime();
        lastReport = start;
    }

    private MTLRenderPipelineState pipeline() {
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(shader()),
                MTLCompileOptions.new_());
        var descriptor = MTLMeshRenderPipelineDescriptor.new_();
        descriptor.setObjectFunction(library.newFunctionWithName(NSString.stringWithUTF8String("cubes_object")));
        descriptor.setMeshFunction(library.newFunctionWithName(NSString.stringWithUTF8String("cubes_mesh")));
        descriptor.setFragmentFunction(library.newFunctionWithName(NSString.stringWithUTF8String("cubes_fragment")));
        descriptor.setDepthAttachmentPixelFormat(MTLPixelFormat.MTLPixelFormatDepth32Float);
        descriptor.colorAttachments()
                .objectAtIndexedSubscript(0)
                .setPixelFormat(MTLPixelFormat.MTLPixelFormatBGRA8Unorm);
        return device.newRenderPipelineStateWithMeshDescriptor(descriptor);
    }

    private MTLDepthStencilState depthState() {
        var descriptor = MTLDepthStencilDescriptor.new_();
        descriptor.setDepthCompareFunction(MTLCompareFunction.MTLCompareFunctionLess);
        descriptor.setDepthWriteEnabled(true);
        return device.newDepthStencilStateWithDescriptor(descriptor);
    }

    private MTLTexture depthTexture() {
        var descriptor = MTLTextureDescriptor.texture2DDescriptorWithPixelFormat(
                MTLPixelFormat.MTLPixelFormatDepth32Float, (long) (WIDTH * scale), (long) (HEIGHT * scale), false);
        descriptor.setUsage(MTLTextureUsage.MTLTextureUsageRenderTarget);
        descriptor.setStorageMode(MTLStorageMode.MTLStorageModePrivate);
        return device.newTextureWithDescriptor(descriptor);
    }

    @SneakyThrows
    private static String shader() {
        try (var source = Mesh.class.getResourceAsStream("/cubes.metal")) {
            return new String(source.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private void write(float time) {
        float span = SIDE * SPACING;
        var projection = Matrix.perspective(1.05f, (float) WIDTH / HEIGHT, 0.5f, span * 8);
        var viewProjection = Matrix.multiply(projection, Matrix.translation(0, 0, -span * 1.6f));
        var contents = uniforms.contents();
        for (int i = 0; i < 16; i++) {
            contents.setAtIndex(ObjC.FLOAT, i, viewProjection[i]);
        }
        contents.setAtIndex(ObjC.FLOAT, 16, time);
        contents.setAtIndex(ObjC.INT, 17, SIDE);
        contents.setAtIndex(ObjC.FLOAT, 18, SPACING);
    }

    private void run() {
        while (!GLFW.glfwWindowShouldClose(window)) {
            GLFW.glfwPollEvents();
            var drawable = layer.nextDrawable();
            if (drawable.isNull()) {
                continue;
            }
            long now = System.nanoTime();
            write((now - start) / 1e9f);
            var pass = MTLRenderPassDescriptor.renderPassDescriptor();
            var color = pass.colorAttachments().objectAtIndexedSubscript(0);
            color.setTexture(drawable.texture());
            color.setLoadAction(MTLLoadAction.MTLLoadActionClear);
            color.setStoreAction(MTLStoreAction.MTLStoreActionStore);
            var depthAttachment = pass.depthAttachment();
            depthAttachment.setTexture(depth);
            depthAttachment.setLoadAction(MTLLoadAction.MTLLoadActionClear);
            depthAttachment.setStoreAction(MTLStoreAction.MTLStoreActionDontCare);
            depthAttachment.setClearDepth(1);
            try (var arena = Arena.ofConfined()) {
                color.setClearColor(MTLClearColor.of(arena, 0.04, 0.05, 0.07, 1));
            }
            var cmd = queue.commandBuffer();
            var encoder = cmd.renderCommandEncoderWithDescriptor(pass);
            encoder.setRenderPipelineState(pipeline);
            encoder.setDepthStencilState(depthState);
            encoder.setCullMode(MTLRenderCommandEncoder.MTLCullModeBack);
            encoder.setFrontFacingWinding(MTLRenderCommandEncoder.MTLWindingCounterClockwise);
            encoder.setObjectBuffer(uniforms, 0, 0);
            encoder.setMeshBuffer(uniforms, 0, 0);
            try (var arena = Arena.ofConfined()) {
                encoder.drawMeshThreadgroups(MTLSize.of(arena, CUBES, 1, 1), MTLSize.of(arena, 1, 1, 1),
                        MTLSize.of(arena, 24, 1, 1));
            }
            encoder.endEncoding();
            cmd.presentDrawable(drawable);
            cmd.commit();
            frames++;
            if (now - lastReport > 1_000_000_000L) {
                System.out.println("fps: " + Math.round((frames - reportedFrames)
                        / ((now - lastReport) / 1e9)) + "  cubes: " + CUBES);
                lastReport = now;
                reportedFrames = frames;
            }
        }
    }

    private void cleanup() {
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
