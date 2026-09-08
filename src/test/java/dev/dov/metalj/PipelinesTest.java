package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.pipelines.depth.MTLCompareFunction;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.pipelines.depth.MTLDepthStencilDescriptor;
import dev.dov.metalj.pipelines.shaders.MTLFunctionType;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineDescriptor;
import dev.dov.metalj.resources.textures.MTLPixelFormat;
import org.junit.jupiter.api.Test;

class PipelinesTest {
    private static final String SOURCE = """
            #include <metal_stdlib>
            using namespace metal;
            struct V {
                float4 position [[position]];
            };
            vertex V vs(uint id [[vertex_id]]) {
                float2 p = float2((id << 1) & 2, id & 2);
                V out;
                out.position = float4(p * 2 - 1, 0, 1);
                return out;
            }
            fragment float4 fs(V in [[stage_in]]) {
                return float4(1, 0, 0, 1);
            }
            kernel void add(device float *a [[buffer(0)]], uint i [[thread_position_in_grid]]) {
                a[i] += 1;
            }
            """;

    @Test
    void library() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(SOURCE), MTLCompileOptions.new_());
        assertEquals(3, library.functionNames().count());
        var vs = library.newFunctionWithName(NSString.stringWithUTF8String("vs"));
        var fs = library.newFunctionWithName(NSString.stringWithUTF8String("fs"));
        assertEquals("vs", vs.name().UTF8String());
        assertEquals("fs", fs.name().UTF8String());
        assertEquals(MTLFunctionType.MTLFunctionTypeVertex, vs.functionType());
        assertEquals(MTLFunctionType.MTLFunctionTypeFragment, fs.functionType());
    }

    @Test
    void render() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(SOURCE), MTLCompileOptions.new_());
        var descriptor = MTLRenderPipelineDescriptor.new_();
        descriptor.setVertexFunction(library.newFunctionWithName(NSString.stringWithUTF8String("vs")));
        descriptor.setFragmentFunction(library.newFunctionWithName(NSString.stringWithUTF8String("fs")));
        descriptor.colorAttachments()
                .objectAtIndexedSubscript(0)
                .setPixelFormat(MTLPixelFormat.MTLPixelFormatBGRA8Unorm);
        var state = device.newRenderPipelineStateWithDescriptor(descriptor);
        assertFalse(state.isNull());
        assertEquals(device, state.device());
    }

    @Test
    void depthStencil() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var descriptor = MTLDepthStencilDescriptor.new_();
        descriptor.setDepthCompareFunction(MTLCompareFunction.MTLCompareFunctionGreaterEqual);
        descriptor.setDepthWriteEnabled(true);
        assertTrue(descriptor.isDepthWriteEnabled());
        assertFalse(device.newDepthStencilStateWithDescriptor(descriptor).isNull());
    }

    @Test
    void compute() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(SOURCE), MTLCompileOptions.new_());
        var state = device.newComputePipelineStateWithFunction(
                library.newFunctionWithName(NSString.stringWithUTF8String("add")));
        assertTrue(state.threadExecutionWidth() > 0);
        assertTrue(state.maxTotalThreadsPerThreadgroup() >= state.threadExecutionWidth());
    }

    @Test
    void broken() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        assertThrows(IllegalStateException.class, () -> device.newLibraryWithSource(
                NSString.stringWithUTF8String("kernel void k() { nope; }"), MTLCompileOptions.new_()));
    }
}
