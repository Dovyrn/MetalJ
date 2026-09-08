package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.libraries.MTLBinaryArchiveDescriptor;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineDescriptor;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.pipelines.shaders.MTLLibraryType;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.Arena;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;

class LibrariesTest {
    private static final String SHARED = """
            #include <metal_stdlib>
            using namespace metal;
            extern "C" [[visible]] float bump(float x) {
                return x + 10;
            }
            """;
    private static final String USER = """
            #include <metal_stdlib>
            using namespace metal;
            extern "C" float bump(float x);
            kernel void run(device float *data [[buffer(0)]], uint i [[thread_position_in_grid]]) {
                data[i] = bump(data[i]);
            }
            """;

    @Test
    void dynamic() throws Exception {
        var device = Metal.MTLCreateSystemDefaultDevice();
        assertTrue(device.supportsDynamicLibraries());
        var path = Files.createTempDirectory("metalj").resolve("bump.metallib");
        var options = MTLCompileOptions.new_();
        options.setLibraryType(MTLLibraryType.MTLLibraryTypeDynamic);
        options.setInstallName(NSString.stringWithUTF8String(path.toString()));
        var shared = device.newLibraryWithSource(NSString.stringWithUTF8String(SHARED), options);
        var dynamic = device.newDynamicLibrary(shared);
        assertEquals(path.toString(), dynamic.installName().UTF8String());
        dynamic.serializeToURL(NSURL.fileURLWithPath(path.toString()));
        assertTrue(Files.size(path) > 0);
        var linking = MTLCompileOptions.new_();
        linking.setLibraries(NSArray.arrayWithObjects(dynamic));
        assertEquals(1, linking.libraries().count());
        var user = device.newLibraryWithSource(NSString.stringWithUTF8String(USER), linking);
        var descriptor = MTLComputePipelineDescriptor.new_();
        descriptor.setComputeFunction(user.newFunctionWithName(NSString.stringWithUTF8String("run")));
        descriptor.setPreloadedLibraries(NSArray.arrayWithObjects(dynamic));
        assertEquals(1, descriptor.preloadedLibraries().count());
        var pipeline = device.newComputePipelineStateWithDescriptor(descriptor);
        var data = device.newBufferWithLength(4, MTLResourceOptions.MTLResourceStorageModeShared);
        data.contents().set(ObjC.FLOAT, 0, 5);
        var queue = device.newCommandQueue();
        var cmd = queue.commandBuffer();
        var encoder = cmd.computeCommandEncoder();
        encoder.setComputePipelineState(pipeline);
        encoder.setBuffer(data, 0, 0);
        try (var arena = Arena.ofConfined()) {
            encoder.dispatchThreads(MTLSize.of(arena, 1, 1, 1), MTLSize.of(arena, 1, 1, 1));
        }
        encoder.endEncoding();
        cmd.commit();
        cmd.waitUntilCompleted();
        assertEquals(15, data.contents().get(ObjC.FLOAT, 0));
    }

    @Test
    void archive() throws Exception {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String("""
                #include <metal_stdlib>
                using namespace metal;
                kernel void add(device float *data [[buffer(0)]], uint i [[thread_position_in_grid]]) {
                    data[i] += 1;
                }
                """), MTLCompileOptions.new_());
        var descriptor = MTLComputePipelineDescriptor.new_();
        descriptor.setComputeFunction(library.newFunctionWithName(NSString.stringWithUTF8String("add")));
        var archive = device.newBinaryArchiveWithDescriptor(MTLBinaryArchiveDescriptor.new_());
        archive.addComputePipelineFunctions(descriptor);
        var archivePath = Files.createTempDirectory("metalj").resolve("pipelines.metallib");
        archive.serializeToURL(NSURL.fileURLWithPath(archivePath.toString()));
        assertTrue(Files.size(archivePath) > 0);
        var reload = MTLBinaryArchiveDescriptor.new_();
        reload.setUrl(NSURL.fileURLWithPath(archivePath.toString()));
        assertTrue(device.newBinaryArchiveWithDescriptor(reload).getId() != 0);
    }
}
