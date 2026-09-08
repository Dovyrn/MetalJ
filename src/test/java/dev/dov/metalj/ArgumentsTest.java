package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.dov.metalj.arguments.MTLArgumentDescriptor;
import dev.dov.metalj.arguments.MTLBindingAccess;
import dev.dov.metalj.commands.encoders.MTLResourceUsage;
import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.pipelines.shaders.MTLDataType;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.Arena;
import org.junit.jupiter.api.Test;

class ArgumentsTest {
    private static final String SOURCE = """
            #include <metal_stdlib>
            using namespace metal;
            struct Scene {
                device float *values;
                float scale;
            };
            kernel void run(device Scene &scene [[buffer(0)]], device float *out [[buffer(1)]],
                            uint i [[thread_position_in_grid]]) {
                out[i] = scene.values[i] * scene.scale;
            }
            """;

    @Test
    void encoder() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(SOURCE), MTLCompileOptions.new_());
        var function = library.newFunctionWithName(NSString.stringWithUTF8String("run"));
        var scene = function.newArgumentEncoderWithBufferIndex(0);
        assertTrue(scene.encodedLength() >= 12);
        var values = device.newBufferWithLength(8, MTLResourceOptions.MTLResourceStorageModeShared);
        values.contents().set(ObjC.FLOAT, 0, 3);
        values.contents().set(ObjC.FLOAT, 4, 5);
        var arguments = device.newBufferWithLength(scene.encodedLength(),
                MTLResourceOptions.MTLResourceStorageModeShared);
        scene.setArgumentBuffer(arguments, 0);
        scene.setBuffer(values, 0, 0);
        scene.constantDataAtIndex(1).reinterpret(4).set(ObjC.FLOAT, 0, 2);
        var out = device.newBufferWithLength(8, MTLResourceOptions.MTLResourceStorageModeShared);
        var pipeline = device.newComputePipelineStateWithFunction(function);
        var cmd = device.newCommandQueue().commandBuffer();
        var encoder = cmd.computeCommandEncoder();
        encoder.setComputePipelineState(pipeline);
        encoder.setBuffer(arguments, 0, 0);
        encoder.setBuffer(out, 0, 1);
        encoder.useResource(values, MTLResourceUsage.MTLResourceUsageRead);
        try (var arena = Arena.ofConfined()) {
            encoder.dispatchThreads(MTLSize.of(arena, 2, 1, 1), MTLSize.of(arena, 2, 1, 1));
        }
        encoder.endEncoding();
        cmd.commit();
        cmd.waitUntilCompleted();
        assertEquals(6, out.contents().get(ObjC.FLOAT, 0));
        assertEquals(10, out.contents().get(ObjC.FLOAT, 4));
    }

    @Test
    void built() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var buffer = MTLArgumentDescriptor.argumentDescriptor();
        buffer.setDataType(MTLDataType.MTLDataTypePointer);
        buffer.setIndex(0);
        buffer.setAccess(MTLBindingAccess.MTLBindingAccessReadOnly);
        var texture = MTLArgumentDescriptor.argumentDescriptor();
        texture.setDataType(MTLDataType.MTLDataTypeTexture);
        texture.setIndex(1);
        texture.setTextureType(2);
        texture.setAccess(MTLBindingAccess.MTLBindingAccessReadOnly);
        var encoder = device.newArgumentEncoderWithArguments(NSArray.arrayWithObjects(buffer, texture));
        assertEquals(16, encoder.encodedLength());
        assertTrue(encoder.alignment() >= 8);
    }
}
