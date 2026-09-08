package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.heaps.MTLHeapDescriptor;
import dev.dov.metalj.resources.textures.MTLPixelFormat;
import dev.dov.metalj.resources.MTLRegion;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.samplers.MTLSamplerDescriptor;
import dev.dov.metalj.resources.samplers.MTLSamplerMinMagFilter;
import dev.dov.metalj.resources.MTLStorageMode;
import dev.dov.metalj.resources.textures.MTLTextureDescriptor;
import dev.dov.metalj.resources.textures.MTLTextureType;
import dev.dov.metalj.resources.textures.MTLTextureUsage;
import java.lang.foreign.Arena;
import java.lang.foreign.ValueLayout;
import org.junit.jupiter.api.Test;

class ResourcesTest {
    @Test
    void buffer() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        byte[] bytes = {1, 2, 3, 4, 5, 6, 7, 8};
        try (var arena = Arena.ofConfined()) {
            var buffer = device.newBufferWithBytes(arena.allocateFrom(ValueLayout.JAVA_BYTE, bytes), bytes.length,
                    MTLResourceOptions.MTLResourceStorageModeShared);
            assertEquals(bytes.length, buffer.length());
            assertArrayEquals(bytes, buffer.contents().toArray(ValueLayout.JAVA_BYTE));
        }
    }

    @Test
    void texture() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var descriptor = MTLTextureDescriptor.texture2DDescriptorWithPixelFormat(
                MTLPixelFormat.MTLPixelFormatRGBA8Unorm, 4, 4, false);
        descriptor.setStorageMode(MTLStorageMode.MTLStorageModeShared);
        descriptor.setUsage(MTLTextureUsage.MTLTextureUsageShaderRead);
        var texture = device.newTextureWithDescriptor(descriptor);
        assertEquals(4, texture.width());
        assertEquals(MTLPixelFormat.MTLPixelFormatRGBA8Unorm, texture.pixelFormat());
        var pixels = new byte[64];
        for (var i = 0; i < pixels.length; i++) {
            pixels[i] = (byte) i;
        }
        try (var arena = Arena.ofConfined()) {
            var region = MTLRegion.of(arena, 0, 0, 0, 4, 4, 1);
            texture.replaceRegion(region, 0, arena.allocateFrom(ValueLayout.JAVA_BYTE, pixels), 16);
            var out = arena.allocate(pixels.length);
            texture.getBytes(out, 16, region, 0);
            assertArrayEquals(pixels, out.toArray(ValueLayout.JAVA_BYTE));
        }
    }

    @Test
    void view() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var descriptor = MTLTextureDescriptor.texture2DDescriptorWithPixelFormat(
                MTLPixelFormat.MTLPixelFormatRGBA8Unorm, 16, 16, true);
        var texture = device.newTextureWithDescriptor(descriptor);
        assertEquals(5, texture.mipmapLevelCount());
        try (var arena = Arena.ofConfined()) {
            var view = texture.newTextureViewWithPixelFormat(MTLPixelFormat.MTLPixelFormatRGBA8Unorm,
                    MTLTextureType.MTLTextureType2D, NSRange.of(arena, 1, 2), NSRange.of(arena, 0, 1));
            assertEquals(2, view.mipmapLevelCount());
            assertEquals(1, view.parentRelativeLevel());
            assertEquals(texture, view.parentTexture());
        }
    }

    @Test
    void sampler() {
        var descriptor = MTLSamplerDescriptor.new_();
        descriptor.setMinFilter(MTLSamplerMinMagFilter.MTLSamplerMinMagFilterLinear);
        var sampler = Metal.MTLCreateSystemDefaultDevice().newSamplerStateWithDescriptor(descriptor);
        assertFalse(sampler.isNull());
        assertFalse(sampler.device().isNull());
    }

    @Test
    void heap() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var descriptor = MTLHeapDescriptor.new_();
        descriptor.setSize(1 << 20);
        descriptor.setStorageMode(MTLStorageMode.MTLStorageModePrivate);
        var heap = device.newHeapWithDescriptor(descriptor);
        assertEquals(1 << 20, heap.size());
        var buffer = heap.newBufferWithLength(4096, MTLResourceOptions.MTLResourceStorageModePrivate);
        assertFalse(buffer.isNull());
        assertEquals(heap, buffer.heap());
        assertEquals(4096, heap.usedSize());
        try (var arena = Arena.ofConfined()) {
            var size = device.maxThreadsPerThreadgroup(arena);
            assertEquals(1024, size.get(ObjC.LONG, 0));
        }
    }
}
