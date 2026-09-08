package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.dov.metalj.functions.MTLLinkedFunctions;
import dev.dov.metalj.functions.MTLVisibleFunctionTableDescriptor;
import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineDescriptor;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.Arena;
import org.junit.jupiter.api.Test;

class FunctionsTest {
    private static final String SOURCE = """
            #include <metal_stdlib>
            using namespace metal;

            [[visible]] float twice(float x) {
                return x * 2;
            }
            [[visible]] float square(float x) {
                return x * x;
            }
            kernel void apply(device float *data [[buffer(0)]],
                              visible_function_table<float(float)> table [[buffer(1)]],
                              uint i [[thread_position_in_grid]]) {
                data[i] = table[i](data[i]);
            }
            """;

    @Test
    void table() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(SOURCE), MTLCompileOptions.new_());
        var twice = library.newFunctionWithName(NSString.stringWithUTF8String("twice"));
        var square = library.newFunctionWithName(NSString.stringWithUTF8String("square"));
        var linked = MTLLinkedFunctions.linkedFunctions();
        linked.setFunctions(NSArray.arrayWithObjects(twice, square));
        var descriptor = MTLComputePipelineDescriptor.new_();
        descriptor.setComputeFunction(library.newFunctionWithName(NSString.stringWithUTF8String("apply")));
        descriptor.setLinkedFunctions(linked);
        var pipeline = device.newComputePipelineStateWithDescriptor(descriptor);
        var tableDescriptor = MTLVisibleFunctionTableDescriptor.visibleFunctionTableDescriptor();
        tableDescriptor.setFunctionCount(2);
        var table = pipeline.newVisibleFunctionTableWithDescriptor(tableDescriptor);
        table.setFunction(pipeline.functionHandleWithFunction(twice), 0);
        table.setFunction(pipeline.functionHandleWithFunction(square), 1);
        var data = device.newBufferWithLength(8, MTLResourceOptions.MTLResourceStorageModeShared);
        data.contents().set(ObjC.FLOAT, 0, 3);
        data.contents().set(ObjC.FLOAT, 4, 4);
        var queue = device.newCommandQueue();
        var cmd = queue.commandBuffer();
        var encoder = cmd.computeCommandEncoder();
        encoder.setComputePipelineState(pipeline);
        encoder.setBuffer(data, 0, 0);
        encoder.setVisibleFunctionTable(table, 1);
        try (var arena = Arena.ofConfined()) {
            encoder.dispatchThreads(MTLSize.of(arena, 2, 1, 1), MTLSize.of(arena, 2, 1, 1));
        }
        encoder.endEncoding();
        cmd.commit();
        cmd.waitUntilCompleted();
        assertEquals(6, data.contents().get(ObjC.FLOAT, 0));
        assertEquals(16, data.contents().get(ObjC.FLOAT, 4));
    }
}
