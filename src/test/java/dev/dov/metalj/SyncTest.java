package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.sync.MTLSharedEvent;
import dev.dov.metalj.sync.MTLSharedEventListener;
import java.lang.foreign.Arena;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class SyncTest {
    private static final String SOURCE = """
            #include <metal_stdlib>
            using namespace metal;
            kernel void bump(device float *data [[buffer(0)]], uint i [[thread_position_in_grid]]) {
                data[i] += 1;
            }
            """;

    @Test
    void fence() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var fence = device.newFence();
        fence.setLabel(NSString.stringWithUTF8String("stage"));
        assertEquals("stage", fence.label().UTF8String());
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(SOURCE), MTLCompileOptions.new_());
        var pipeline = device.newComputePipelineStateWithFunction(
                library.newFunctionWithName(NSString.stringWithUTF8String("bump")));
        var data = device.newBufferWithLength(4, MTLResourceOptions.MTLResourceStorageModeShared);
        var cmd = device.newCommandQueue().commandBuffer();
        try (var arena = Arena.ofConfined()) {
            var grid = MTLSize.of(arena, 1, 1, 1);
            var first = cmd.computeCommandEncoder();
            first.setComputePipelineState(pipeline);
            first.setBuffer(data, 0, 0);
            first.dispatchThreads(grid, grid);
            first.updateFence(fence);
            first.endEncoding();
            var second = cmd.computeCommandEncoder();
            second.waitForFence(fence);
            second.setComputePipelineState(pipeline);
            second.setBuffer(data, 0, 0);
            second.dispatchThreads(grid, grid);
            second.endEncoding();
        }
        cmd.commit();
        cmd.waitUntilCompleted();
        assertEquals(2, data.contents().get(ObjC.FLOAT, 0));
    }

    @Test
    void event() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var event = device.newSharedEvent();
        assertEquals(0, event.signaledValue());
        var queue = device.newCommandQueue();
        var waiter = queue.commandBuffer();
        waiter.encodeWaitForEvent(event, 1);
        waiter.commit();
        var signaller = queue.commandBuffer();
        signaller.encodeSignalEvent(event, 1);
        signaller.commit();
        waiter.waitUntilCompleted();
        assertEquals(1, event.signaledValue());
        assertTrue(event.waitUntilSignaledValue(1, 100));
    }

    @Test
    void listener() throws Exception {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var event = device.newSharedEvent();
        var listener = MTLSharedEventListener.new_();
        var reached = new CountDownLatch(1);
        var seen = new long[1];
        var block = MTLSharedEvent.listener(value -> {
            seen[0] = value;
            reached.countDown();
        });
        event.notifyListener(listener, 7, block);
        event.setSignaledValue(7);
        assertTrue(reached.await(2, TimeUnit.SECONDS));
        assertEquals(7, seen[0]);
    }

    @Test
    void handle() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var event = device.newSharedEvent();
        event.setSignaledValue(3);
        var shared = device.newSharedEventWithHandle(event.newSharedEventHandle());
        assertEquals(3, shared.signaledValue());
    }
}
