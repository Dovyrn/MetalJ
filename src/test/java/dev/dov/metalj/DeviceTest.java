package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.dov.metalj.device.CAMetalLayer;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.CGSize;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import org.junit.jupiter.api.Test;

class DeviceTest {
    @Test
    void device() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        assertFalse(device.name().UTF8String().isEmpty());
        assertTrue(device.maxBufferLength() > 0);
        assertTrue(device.supportsFamily(MTLDevice.MTLGPUFamilyMetal3));
        try (var arena = Arena.ofConfined()) {
            var cpu = arena.allocate(ObjC.LONG);
            var gpu = arena.allocate(ObjC.LONG);
            device.sampleTimestamps(cpu, gpu);
            assertTrue(gpu.get(ObjC.LONG, 0) > 0);
        }
        assertFalse(device.newCommandQueue().isNull());
    }

    @Test
    void layer() {
        var layer = CAMetalLayer.layer();
        layer.setDevice(Metal.MTLCreateSystemDefaultDevice());
        layer.setPixelFormat(80);
        try (var arena = Arena.ofConfined()) {
            layer.setDrawableSize(CGSize.of(arena, 64, 64));
        }
        layer.setDisplaySyncEnabled(false);
        assertFalse(layer.nextDrawable().isNull());
    }
}
