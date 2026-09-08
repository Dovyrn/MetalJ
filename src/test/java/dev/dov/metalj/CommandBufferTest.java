package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.dov.metalj.commands.passes.MTLClearColor;
import dev.dov.metalj.commands.MTLCommandBuffer;
import dev.dov.metalj.commands.passes.MTLLoadAction;
import dev.dov.metalj.commands.passes.MTLRenderPassDescriptor;
import dev.dov.metalj.commands.passes.MTLStoreAction;
import dev.dov.metalj.device.CAMetalLayer;
import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.Block;
import dev.dov.metalj.objc.CGSize;
import java.lang.foreign.Arena;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;

class CommandBufferTest {
    @Test
    void clear() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var layer = CAMetalLayer.layer();
        layer.setDevice(device);
        layer.setPixelFormat(80);
        layer.setDisplaySyncEnabled(false);
        try (var arena = Arena.ofConfined()) {
            layer.setDrawableSize(CGSize.of(arena, 64, 64));
            var drawable = layer.nextDrawable();
            var pass = MTLRenderPassDescriptor.renderPassDescriptor();
            var color = pass.colorAttachments().objectAtIndexedSubscript(0);
            color.setTexture(drawable.texture());
            color.setLoadAction(MTLLoadAction.MTLLoadActionClear);
            color.setStoreAction(MTLStoreAction.MTLStoreActionStore);
            color.setClearColor(MTLClearColor.of(arena, 1, 0, 0, 1));
            var buffer = device.newCommandQueue().commandBuffer();
            buffer.renderCommandEncoderWithDescriptor(pass).endEncoding();
            buffer.presentDrawable(drawable);
            buffer.commit();
            buffer.waitUntilCompleted();
            assertEquals(MTLCommandBuffer.MTLCommandBufferStatusCompleted, buffer.status());
        }
    }

    @Test
    void completed() {
        var buffer = Metal.MTLCreateSystemDefaultDevice().newCommandQueue().commandBuffer();
        var ran = new AtomicBoolean();
        var block = Block.once(() -> ran.set(true));
        buffer.addCompletedHandler(block);
        buffer.commit();
        buffer.waitUntilCompleted();
        assertTrue(ran.get());
        block.close();
    }
}
