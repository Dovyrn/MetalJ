package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import dev.dov.metalj.commands.passes.MTLComputePassDescriptor;
import dev.dov.metalj.debug.MTLCaptureDestination;
import dev.dov.metalj.debug.MTLCaptureManager;
import dev.dov.metalj.debug.MTLCommonCounter;
import dev.dov.metalj.debug.MTLCounterResultTimestamp;
import dev.dov.metalj.debug.MTLCounterSampleBufferDescriptor;
import dev.dov.metalj.debug.MTLCounterSamplingPoint;
import dev.dov.metalj.debug.MTLCounterSet;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.MTLStorageMode;
import java.lang.foreign.Arena;
import org.junit.jupiter.api.Test;

class DebugTest {
    private static final String SOURCE = """
            #include <metal_stdlib>
            using namespace metal;
            kernel void spin(device float *data [[buffer(0)]], uint i [[thread_position_in_grid]]) {
                float sum = 0;
                for (uint k = 0; k < 4096; k++) {
                    sum += sqrt(float(k));
                }
                data[i] = sum;
            }
            """;

    private static MTLCounterSet timestampSet(MTLDevice device) {
        var sets = device.counterSets();
        var wanted = MTLCommonCounter.MTLCommonCounterSetTimestamp().UTF8String();
        for (long i = 0; i < sets.count(); i++) {
            var set = MTLCounterSet.of(sets.objectAtIndex(i));
            if (wanted.equals(set.name().UTF8String())) {
                return set;
            }
        }
        return null;
    }

    @Test
    void counters() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        assumeTrue(device.supportsCounterSampling(
                MTLCounterSamplingPoint.MTLCounterSamplingPointAtStageBoundary));
        var set = timestampSet(device);
        assumeTrue(set != null);
        assertTrue(set.counters().count() > 0);
        var descriptor = MTLCounterSampleBufferDescriptor.new_();
        descriptor.setCounterSet(set);
        descriptor.setStorageMode(MTLStorageMode.MTLStorageModeShared);
        descriptor.setSampleCount(2);
        descriptor.setLabel(NSString.stringWithUTF8String("pass"));
        var samples = device.newCounterSampleBufferWithDescriptor(descriptor);
        assertEquals(2, samples.sampleCount());
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(SOURCE), MTLCompileOptions.new_());
        var pipeline = device.newComputePipelineStateWithFunction(
                library.newFunctionWithName(NSString.stringWithUTF8String("spin")));
        var data = device.newBufferWithLength(4096, MTLResourceOptions.MTLResourceStorageModeShared);
        var pass = MTLComputePassDescriptor.computePassDescriptor();
        var attachment = pass.sampleBufferAttachments().objectAtIndexedSubscript(0);
        attachment.setSampleBuffer(samples);
        attachment.setStartOfEncoderSampleIndex(0);
        attachment.setEndOfEncoderSampleIndex(1);
        var cmd = device.newCommandQueue().commandBuffer();
        var encoder = cmd.computeCommandEncoderWithDescriptor(pass);
        encoder.setComputePipelineState(pipeline);
        encoder.setBuffer(data, 0, 0);
        try (var arena = Arena.ofConfined()) {
            encoder.dispatchThreads(MTLSize.of(arena, 1024, 1, 1), MTLSize.of(arena, 64, 1, 1));
        }
        encoder.endEncoding();
        cmd.commit();
        cmd.waitUntilCompleted();
        var resolved = samples.resolveCounterRange(0, 2).bytes();
        long begin = MTLCounterResultTimestamp.timestamp(resolved, 0);
        long end = MTLCounterResultTimestamp.timestamp(resolved, 1);
        assertTrue(end > begin);
    }

    @Test
    void scope() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var capture = MTLCaptureManager.sharedCaptureManager();
        assertFalse(capture.isCapturing());
        var queue = device.newCommandQueue();
        var scope = capture.newCaptureScopeWithCommandQueue(queue);
        scope.setLabel(NSString.stringWithUTF8String("frame"));
        assertEquals("frame", scope.label().UTF8String());
        assertEquals(queue, scope.commandQueue());
        capture.setDefaultCaptureScope(scope);
        assertEquals(scope, capture.defaultCaptureScope());
        scope.beginScope();
        var cmd = queue.commandBuffer();
        cmd.commit();
        cmd.waitUntilCompleted();
        scope.endScope();
        capture.supportsDestination(MTLCaptureDestination.MTLCaptureDestinationDeveloperTools);
    }

    @Test
    void timestamps() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        try (var arena = Arena.ofConfined()) {
            var cpu = arena.allocate(ObjC.LONG);
            var gpu = arena.allocate(ObjC.LONG);
            device.sampleTimestamps(cpu, gpu);
            assertTrue(cpu.get(ObjC.LONG, 0) > 0);
            assertTrue(gpu.get(ObjC.LONG, 0) > 0);
        }
    }
}
