package dev.dov.metalj.demo;

import dev.dov.metalj.commands.MTLClearColor;
import dev.dov.metalj.commands.MTLLoadAction;
import dev.dov.metalj.commands.MTLRenderPassDescriptor;
import dev.dov.metalj.commands.MTLStoreAction;
import dev.dov.metalj.device.CAMetalLayer;
import dev.dov.metalj.device.Metal;
import dev.dov.metalj.device.NSWindow;
import dev.dov.metalj.objc.CGSize;
import java.lang.foreign.Arena;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWNativeCocoa;

public class Demo {
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
        var cocoa = NSWindow.of(GLFWNativeCocoa.glfwGetCocoaWindow(window));
        var layer = CAMetalLayer.layer();
        layer.setDevice(device);
        layer.setPixelFormat(80);
        layer.setContentsScale(cocoa.backingScaleFactor());
        try (var arena = Arena.ofConfined()) {
            layer.setDrawableSize(CGSize.of(arena, 960 * cocoa.backingScaleFactor(), 540 * cocoa.backingScaleFactor()));
        }
        var view = cocoa.contentView();
        view.setWantsLayer(true);
        view.setLayer(layer);
        System.out.println("device: " + device.name().UTF8String());
        var queue = device.newCommandQueue();
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
            var cmd = queue.commandBuffer();
            cmd.renderCommandEncoderWithDescriptor(pass).endEncoding();
            cmd.presentDrawable(drawable);
            cmd.commit();
        }
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
