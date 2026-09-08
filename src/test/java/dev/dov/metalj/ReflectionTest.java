package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.dov.metalj.arguments.MTLBinding;
import dev.dov.metalj.arguments.MTLBindingType;
import dev.dov.metalj.arguments.MTLBufferBinding;
import dev.dov.metalj.arguments.MTLStructMember;
import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.pipelines.MTLPipelineOption;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineReflection;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import org.junit.jupiter.api.Test;

class ReflectionTest {
    private static final String SOURCE = """
            #include <metal_stdlib>
            using namespace metal;
            struct Params {
                float scale;
                uint count;
            };
            kernel void run(device float *data [[buffer(0)]],
                            constant Params &params [[buffer(1)]],
                            texture2d<float> tex [[texture(0)]],
                            uint i [[thread_position_in_grid]]) {
                data[i] = data[i] * params.scale + tex.read(uint2(i, 0)).x;
            }
            """;

    @Test
    void bindings() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(SOURCE), MTLCompileOptions.new_());
        var function = library.newFunctionWithName(NSString.stringWithUTF8String("run"));
        var reflection = new MTLComputePipelineReflection[1];
        device.newComputePipelineStateWithFunction(function,
                MTLPipelineOption.MTLPipelineOptionBindingInfo | MTLPipelineOption.MTLPipelineOptionBufferTypeInfo,
                reflection);
        var bindings = reflection[0].bindings();
        assertEquals(3, bindings.count());
        var data = MTLBinding.of(bindings.objectAtIndex(0));
        assertEquals("data", data.name().UTF8String());
        assertEquals(MTLBindingType.MTLBindingTypeBuffer, data.type());
        assertEquals(0, data.index());
        assertTrue(data.isUsed());
        var params = MTLBufferBinding.of(bindings.objectAtIndex(1));
        assertEquals("params", params.name().UTF8String());
        assertEquals(8, params.bufferDataSize());
        assertEquals("scale", MTLStructMember.of(params.bufferStructType().members().objectAtIndex(0))
                .name().UTF8String());
        var texture = MTLBinding.of(bindings.objectAtIndex(2));
        assertEquals("tex", texture.name().UTF8String());
        assertEquals(MTLBindingType.MTLBindingTypeTexture, texture.type());
    }
}
