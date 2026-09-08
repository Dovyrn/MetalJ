package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import dev.dov.metalj.commands.encoders.MTLResourceUsage;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.pipelines.vertex.MTLAttributeFormat;
import dev.dov.metalj.raytracing.MTLAccelerationStructure;
import dev.dov.metalj.raytracing.MTLAccelerationStructureSizes;
import dev.dov.metalj.raytracing.MTLAccelerationStructureTriangleGeometryDescriptor;
import dev.dov.metalj.raytracing.MTLPrimitiveAccelerationStructureDescriptor;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.Arena;
import org.junit.jupiter.api.Test;

class RaytracingTest {
    private static final String SOURCE = """
            #include <metal_stdlib>
            #include <metal_raytracing>
            using namespace metal;
            using namespace raytracing;
            kernel void trace(primitive_acceleration_structure world [[buffer(0)]],
                              device float *out [[buffer(1)]], uint i [[thread_position_in_grid]]) {
                ray r;
                r.origin = float3(0, 0, -1);
                r.direction = float3(0, 0, 1);
                r.min_distance = 0;
                r.max_distance = 100;
                intersector<triangle_data> tracer;
                tracer.assume_geometry_type(geometry_type::triangle);
                auto hit = tracer.intersect(r, world);
                out[0] = hit.type == intersection_type::none ? -1 : hit.distance;
                out[1] = hit.primitive_id;
            }
            """;

    private static MTLAccelerationStructure triangle(MTLDevice device) {
        var geometry = MTLAccelerationStructureTriangleGeometryDescriptor.descriptor();
        try (var arena = Arena.ofConfined()) {
            float[] points = {0, 1, 0, -1, -1, 0, 1, -1, 0};
            var bytes = arena.allocateFrom(ObjC.FLOAT, points);
            geometry.setVertexBuffer(device.newBufferWithBytes(bytes, bytes.byteSize(),
                    MTLResourceOptions.MTLResourceStorageModeShared));
        }
        geometry.setVertexFormat(MTLAttributeFormat.MTLAttributeFormatFloat3);
        geometry.setVertexStride(12);
        geometry.setTriangleCount(1);
        var descriptor = MTLPrimitiveAccelerationStructureDescriptor.descriptor();
        descriptor.setGeometryDescriptors(NSArray.arrayWithObjects(geometry));
        try (var arena = Arena.ofConfined()) {
            var sizes = device.accelerationStructureSizesWithDescriptor(arena, descriptor);
            assertTrue(MTLAccelerationStructureSizes.accelerationStructureSize(sizes) > 0);
            var structure = device.newAccelerationStructureWithSize(
                    MTLAccelerationStructureSizes.accelerationStructureSize(sizes));
            var scratch = device.newBufferWithLength(
                    Math.max(1, MTLAccelerationStructureSizes.buildScratchBufferSize(sizes)),
                    MTLResourceOptions.MTLResourceStorageModePrivate);
            var cmd = device.newCommandQueue().commandBuffer();
            var encoder = cmd.accelerationStructureCommandEncoder();
            encoder.buildAccelerationStructure(structure, descriptor, scratch, 0);
            encoder.endEncoding();
            cmd.commit();
            cmd.waitUntilCompleted();
            return structure;
        }
    }

    @Test
    void intersect() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        assumeTrue(device.supportsRaytracing());
        var world = triangle(device);
        assertTrue(world.size() > 0);
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(SOURCE), MTLCompileOptions.new_());
        var pipeline = device.newComputePipelineStateWithFunction(
                library.newFunctionWithName(NSString.stringWithUTF8String("trace")));
        var out = device.newBufferWithLength(8, MTLResourceOptions.MTLResourceStorageModeShared);
        var cmd = device.newCommandQueue().commandBuffer();
        var encoder = cmd.computeCommandEncoder();
        encoder.setComputePipelineState(pipeline);
        encoder.setAccelerationStructure(world, 0);
        encoder.setBuffer(out, 0, 1);
        encoder.useResource(world, MTLResourceUsage.MTLResourceUsageRead);
        try (var arena = Arena.ofConfined()) {
            encoder.dispatchThreads(MTLSize.of(arena, 1, 1, 1), MTLSize.of(arena, 1, 1, 1));
        }
        encoder.endEncoding();
        cmd.commit();
        cmd.waitUntilCompleted();
        assertEquals(1, out.contents().get(ObjC.FLOAT, 0));
        assertEquals(0, out.contents().get(ObjC.FLOAT, 4));
    }
}
