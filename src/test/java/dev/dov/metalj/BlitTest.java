package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLOrigin;
import dev.dov.metalj.resources.MTLRegion;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.textures.MTLPixelFormat;
import dev.dov.metalj.resources.textures.MTLTextureDescriptor;
import dev.dov.metalj.resources.textures.MTLTextureUsage;
import java.lang.foreign.Arena;
import org.junit.jupiter.api.Test;

class BlitTest {
    @Test
    void copy() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var source = device.newBufferWithLength(16, MTLResourceOptions.MTLResourceStorageModeShared);
        for (int i = 0; i < 4; i++) {
            source.contents().set(ObjC.FLOAT, i * 4L, i + 1);
        }
        var target = device.newBufferWithLength(16, MTLResourceOptions.MTLResourceStorageModeShared);
        var cmd = device.newCommandQueue().commandBuffer();
        var blit = cmd.blitCommandEncoder();
        blit.copyFromBuffer(source, 0, target, 0, 16);
        blit.endEncoding();
        cmd.commit();
        cmd.waitUntilCompleted();
        assertEquals(1, target.contents().get(ObjC.FLOAT, 0));
        assertEquals(4, target.contents().get(ObjC.FLOAT, 12));
    }

    @Test
    void fill() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var buffer = device.newBufferWithLength(8, MTLResourceOptions.MTLResourceStorageModeShared);
        var cmd = device.newCommandQueue().commandBuffer();
        var blit = cmd.blitCommandEncoder();
        try (var arena = Arena.ofConfined()) {
            blit.fillBuffer(buffer, NSRange.of(arena, 0, 8), (byte) 0xAB);
        }
        blit.endEncoding();
        cmd.commit();
        cmd.waitUntilCompleted();
        assertEquals((byte) 0xAB, buffer.contents().get(ObjC.BYTE, 0));
        assertEquals((byte) 0xAB, buffer.contents().get(ObjC.BYTE, 7));
    }

    @Test
    void texture() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var descriptor = MTLTextureDescriptor.texture2DDescriptorWithPixelFormat(
                MTLPixelFormat.MTLPixelFormatRGBA8Unorm, 2, 2, false);
        descriptor.setUsage(MTLTextureUsage.MTLTextureUsageShaderRead);
        var texture = device.newTextureWithDescriptor(descriptor);
        var source = device.newBufferWithLength(16, MTLResourceOptions.MTLResourceStorageModeShared);
        for (int i = 0; i < 16; i++) {
            source.contents().set(ObjC.BYTE, i, (byte) (i + 1));
        }
        var back = device.newBufferWithLength(16, MTLResourceOptions.MTLResourceStorageModeShared);
        var cmd = device.newCommandQueue().commandBuffer();
        var blit = cmd.blitCommandEncoder();
        try (var arena = Arena.ofConfined()) {
            var size = MTLSize.of(arena, 2, 2, 1);
            var origin = MTLOrigin.of(arena, 0, 0, 0);
            blit.copyFromBuffer(source, 0, 8, 16, size, texture, 0, 0, origin);
            blit.copyFromTexture(texture, 0, 0, origin, size, back, 0, 8, 16);
        }
        blit.endEncoding();
        cmd.commit();
        cmd.waitUntilCompleted();
        assertEquals((byte) 1, back.contents().get(ObjC.BYTE, 0));
        assertEquals((byte) 16, back.contents().get(ObjC.BYTE, 15));
    }
}
