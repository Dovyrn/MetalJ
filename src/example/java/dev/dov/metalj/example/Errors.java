package dev.dov.metalj.example;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.pipelines.MTLPipelineOption;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineReflection;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.arguments.MTLBinding;

public class Errors {
    private static final String BROKEN = "kernel void run(device float *a) { a[0] = nope; }";
    private static final String GOOD = """
            #include <metal_stdlib>
            using namespace metal;
            kernel void run(device float *data [[buffer(0)]], constant float &scale [[buffer(1)]],
                            uint i [[thread_position_in_grid]]) {
                data[i] *= scale;
            }
            """;

    public static void main(String[] args) {
        var device = Metal.MTLCreateSystemDefaultDevice();
        try {
            device.newLibraryWithSource(NSString.stringWithUTF8String(BROKEN), MTLCompileOptions.new_());
        } catch (IllegalStateException e) {
            System.out.println("compile failed: " + e.getMessage().lines().findFirst().orElseThrow());
        }

        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(GOOD), MTLCompileOptions.new_());
        var function = library.newFunctionWithName(NSString.stringWithUTF8String("run"));
        var reflection = new MTLComputePipelineReflection[1];
        device.newComputePipelineStateWithFunction(function, MTLPipelineOption.MTLPipelineOptionBindingInfo,
                reflection);
        var bindings = reflection[0].bindings();
        for (long i = 0; i < bindings.count(); i++) {
            var binding = MTLBinding.of(bindings.objectAtIndex(i));
            System.out.println("binding " + binding.index() + ": " + binding.name().UTF8String()
                    + " type " + binding.type() + " used " + binding.isUsed());
        }
    }
}
