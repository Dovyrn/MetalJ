package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.io.MTLIOCommandQueueDescriptor;
import dev.dov.metalj.io.MTLIOCompressionContext;
import dev.dov.metalj.io.MTLIOCompressionMethod;
import dev.dov.metalj.io.MTLIOCompressionStatus;
import dev.dov.metalj.io.MTLIOPriority;
import dev.dov.metalj.io.MTLIOStatus;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLResourceOptions;
import java.lang.foreign.Arena;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;

class IOTest {
    @Test
    void load() throws Exception {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var path = Files.createTempDirectory("metalj").resolve("world.bin");
        byte[] source = new byte[1024];
        for (int i = 0; i < source.length; i++) {
            source[i] = (byte) i;
        }
        Files.write(path, source);
        var descriptor = MTLIOCommandQueueDescriptor.new_();
        descriptor.setPriority(MTLIOPriority.MTLIOPriorityNormal);
        var queue = device.newIOCommandQueueWithDescriptor(descriptor);
        var file = device.newIOFileHandleWithURL(NSURL.fileURLWithPath(path.toString()));
        var buffer = device.newBufferWithLength(1024, MTLResourceOptions.MTLResourceStorageModeShared);
        var cmd = queue.commandBuffer();
        cmd.loadBuffer(buffer, 0, 1024, file, 0);
        cmd.commit();
        cmd.waitUntilCompleted();
        assertEquals(MTLIOStatus.MTLIOStatusComplete, cmd.status());
        for (int i = 0; i < 1024; i++) {
            assertEquals(source[i], buffer.contents().get(ObjC.BYTE, i));
        }
    }

    @Test
    void compressed() throws Exception {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var path = Files.createTempDirectory("metalj").resolve("world.lz4");
        byte[] source = new byte[4096];
        for (int i = 0; i < source.length; i++) {
            source[i] = (byte) (i / 16);
        }
        var chunk = MTLIOCompressionContext.MTLIOCompressionContextDefaultChunkSize();
        assertTrue(chunk > 0);
        var context = MTLIOCompressionContext.MTLIOCreateCompressionContext(path.toString(),
                MTLIOCompressionMethod.MTLIOCompressionMethodLZ4, chunk);
        try (var arena = Arena.ofConfined()) {
            var bytes = arena.allocateFrom(ObjC.BYTE, source);
            MTLIOCompressionContext.MTLIOCompressionContextAppendData(context, bytes, source.length);
            assertEquals(MTLIOCompressionStatus.MTLIOCompressionStatusComplete,
                    MTLIOCompressionContext.MTLIOFlushAndDestroyCompressionContext(context));
        }
        assertTrue(Files.size(path) < source.length);
        var queue = device.newIOCommandQueueWithDescriptor(MTLIOCommandQueueDescriptor.new_());
        var file = device.newIOFileHandleWithURL(NSURL.fileURLWithPath(path.toString()),
                MTLIOCompressionMethod.MTLIOCompressionMethodLZ4);
        var buffer = device.newBufferWithLength(4096, MTLResourceOptions.MTLResourceStorageModeShared);
        var cmd = queue.commandBuffer();
        cmd.loadBuffer(buffer, 0, 4096, file, 0);
        cmd.commit();
        cmd.waitUntilCompleted();
        assertEquals(MTLIOStatus.MTLIOStatusComplete, cmd.status());
        for (int i = 0; i < 4096; i++) {
            assertEquals(source[i], buffer.contents().get(ObjC.BYTE, i));
        }
    }
}
