package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.metal4.MTL4ArgumentTableDescriptor;
import dev.dov.metalj.metal4.compiler.MTL4CompilerDescriptor;
import dev.dov.metalj.metal4.compiler.MTL4CompilerTaskOptions;
import dev.dov.metalj.metal4.compiler.MTL4LibraryDescriptor;
import dev.dov.metalj.metal4.compiler.MTL4LibraryFunctionDescriptor;
import dev.dov.metalj.metal4.pipelines.MTL4ComputePipelineDescriptor;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.residency.MTLResidencySetDescriptor;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.Arena;
import org.junit.jupiter.api.Test;

class Metal4Test {
    private static final String SOURCE = """
            #include <metal_stdlib>
            using namespace metal;
            kernel void add(device float *a [[buffer(0)]], uint i [[thread_position_in_grid]]) {
                a[i] += 1;
            }
            """;

    @Test
    void dispatch() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var compiler = device.newCompilerWithDescriptor(MTL4CompilerDescriptor.new_());
        var source = MTL4LibraryDescriptor.new_();
        source.setSource(NSString.stringWithUTF8String(SOURCE));
        var library = compiler.newLibraryWithDescriptor(source);
        var function = MTL4LibraryFunctionDescriptor.new_();
        function.setLibrary(library);
        function.setName(NSString.stringWithUTF8String("add"));
        var pipeline = MTL4ComputePipelineDescriptor.new_();
        pipeline.setComputeFunctionDescriptor(function);
        var state = compiler.newComputePipelineStateWithDescriptor(pipeline, MTL4CompilerTaskOptions.new_());

        var tableDescriptor = MTL4ArgumentTableDescriptor.new_();
        tableDescriptor.setMaxBufferBindCount(1);
        var table = device.newArgumentTableWithDescriptor(tableDescriptor);
        var data = device.newBufferWithLength(16, MTLResourceOptions.MTLResourceStorageModeShared);
        table.setAddress(data.gpuAddress(), 0);

        var set = device.newResidencySetWithDescriptor(MTLResidencySetDescriptor.new_());
        set.addAllocation(data);
        set.commit();
        set.requestResidency();

        var queue = device.newMTL4CommandQueue();
        queue.addResidencySet(set);
        var allocator = device.newCommandAllocator();
        var commands = device.newCommandBuffer();
        commands.beginCommandBufferWithAllocator(allocator);
        var encoder = commands.computeCommandEncoder();
        encoder.setComputePipelineState(state);
        encoder.setArgumentTable(table);
        try (var arena = Arena.ofConfined()) {
            encoder.dispatchThreads(MTLSize.of(arena, 4, 1, 1), MTLSize.of(arena, 4, 1, 1));
        }
        encoder.endEncoding();
        commands.endCommandBuffer();

        var event = device.newSharedEvent();
        try (var arena = Arena.ofConfined()) {
            var ids = arena.allocate(ObjC.PTR, 1);
            ids.setAtIndex(ObjC.PTR, 0, commands.getId());
            queue.commit(ids, 1);
        }
        queue.signalEvent(event, 1);
        assertTrue(event.waitUntilSignaledValue(1, 5000));

        var contents = data.contents();
        for (int i = 0; i < 4; i++) {
            assertEquals(1, contents.getAtIndex(ObjC.FLOAT, i));
        }
        allocator.reset();
        set.endResidency();
    }
}
