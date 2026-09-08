package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineDescriptor;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.indirect.MTLIndirectCommandBufferDescriptor;
import dev.dov.metalj.resources.indirect.MTLIndirectCommandType;
import java.lang.foreign.Arena;
import org.junit.jupiter.api.Test;

class IndirectTest {
    private static final String SOURCE = """
            #include <metal_stdlib>
            using namespace metal;
            kernel void fill(device float *data [[buffer(0)]], uint i [[thread_position_in_grid]]) {
                data[i] = i + 1;
            }
            """;

    @Test
    void compute() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(SOURCE), MTLCompileOptions.new_());
        var pipelineDescriptor = MTLComputePipelineDescriptor.new_();
        pipelineDescriptor.setComputeFunction(library.newFunctionWithName(NSString.stringWithUTF8String("fill")));
        pipelineDescriptor.setSupportIndirectCommandBuffers(true);
        var pipeline = device.newComputePipelineStateWithDescriptor(pipelineDescriptor);
        var descriptor = MTLIndirectCommandBufferDescriptor.new_();
        descriptor.setCommandTypes(MTLIndirectCommandType.MTLIndirectCommandTypeConcurrentDispatch);
        descriptor.setInheritPipelineState(false);
        descriptor.setMaxKernelBufferBindCount(1);
        var indirect = device.newIndirectCommandBufferWithDescriptor(descriptor, 1,
                MTLResourceOptions.MTLResourceStorageModeShared);
        var data = device.newBufferWithLength(16, MTLResourceOptions.MTLResourceStorageModeShared);
        var command = indirect.indirectComputeCommandAtIndex(0);
        command.setComputePipelineState(pipeline);
        command.setKernelBuffer(data, 0, 0);
        try (var arena = Arena.ofConfined()) {
            command.concurrentDispatchThreads(MTLSize.of(arena, 4, 1, 1), MTLSize.of(arena, 4, 1, 1));
        }
        var cmd = device.newCommandQueue().commandBuffer();
        var encoder = cmd.computeCommandEncoder();
        try (var arena = Arena.ofConfined()) {
            encoder.executeCommandsInBuffer(indirect, NSRange.of(arena, 0, 1));
        }
        encoder.endEncoding();
        cmd.commit();
        cmd.waitUntilCompleted();
        assertEquals(1, data.contents().get(ObjC.FLOAT, 0));
        assertEquals(4, data.contents().get(ObjC.FLOAT, 12));
    }

    @Test
    void reset() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var descriptor = MTLIndirectCommandBufferDescriptor.new_();
        descriptor.setCommandTypes(MTLIndirectCommandType.MTLIndirectCommandTypeConcurrentDispatch);
        var indirect = device.newIndirectCommandBufferWithDescriptor(descriptor, 4,
                MTLResourceOptions.MTLResourceStorageModeShared);
        assertEquals(4, indirect.size());
        try (var arena = Arena.ofConfined()) {
            indirect.resetWithRange(NSRange.of(arena, 0, 4));
        }
    }
}
