package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.libraries.MTLDynamicLibrary;
import dev.dov.metalj.metal4.pipelines.MTL4ComputePipelineDescriptor;
import dev.dov.metalj.metal4.pipelines.MTL4MachineLearningPipelineDescriptor;
import dev.dov.metalj.metal4.pipelines.MTL4MachineLearningPipelineState;
import dev.dov.metalj.metal4.pipelines.MTL4PipelineDescriptor;
import dev.dov.metalj.objc.Block;
import dev.dov.metalj.objc.NSError;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineState;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineState;
import dev.dov.metalj.pipelines.shaders.MTLLibrary;
import java.lang.foreign.Arena;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4Compiler extends NSObject {
    private static final long DEVICE = ObjC.sel("device");
    private static final long LABEL = ObjC.sel("label");
    private static final long NEW_BINARY_FUNCTION_WITH_DESCRIPTOR_COMPILER_TASK_OPTIONS_COMPLETION_HANDLER = ObjC.sel("newBinaryFunctionWithDescriptor:compilerTaskOptions:completionHandler:");
    private static final long NEW_BINARY_FUNCTION_WITH_DESCRIPTOR_COMPILER_TASK_OPTIONS_ERROR = ObjC.sel("newBinaryFunctionWithDescriptor:compilerTaskOptions:error:");
    private static final long NEW_COMPUTE_PIPELINE_STATE_WITH_DESCRIPTOR_COMPILER_TASK_OPTIONS_COMPLETION_HANDLER = ObjC.sel("newComputePipelineStateWithDescriptor:compilerTaskOptions:completionHandler:");
    private static final long NEW_COMPUTE_PIPELINE_STATE_WITH_DESCRIPTOR_COMPILER_TASK_OPTIONS_ERROR = ObjC.sel("newComputePipelineStateWithDescriptor:compilerTaskOptions:error:");
    private static final long NEW_DYNAMIC_LIBRARY_COMPLETION_HANDLER = ObjC.sel("newDynamicLibrary:completionHandler:");
    private static final long NEW_DYNAMIC_LIBRARY_ERROR = ObjC.sel("newDynamicLibrary:error:");
    private static final long NEW_DYNAMIC_LIBRARY_WITH_URL_COMPLETION_HANDLER = ObjC.sel("newDynamicLibraryWithURL:completionHandler:");
    private static final long NEW_DYNAMIC_LIBRARY_WITH_URL_ERROR = ObjC.sel("newDynamicLibraryWithURL:error:");
    private static final long NEW_LIBRARY_WITH_DESCRIPTOR_COMPLETION_HANDLER = ObjC.sel("newLibraryWithDescriptor:completionHandler:");
    private static final long NEW_LIBRARY_WITH_DESCRIPTOR_ERROR = ObjC.sel("newLibraryWithDescriptor:error:");
    private static final long NEW_MACHINE_LEARNING_PIPELINE_STATE_WITH_DESCRIPTOR_COMPLETION_HANDLER = ObjC.sel("newMachineLearningPipelineStateWithDescriptor:completionHandler:");
    private static final long NEW_MACHINE_LEARNING_PIPELINE_STATE_WITH_DESCRIPTOR_ERROR = ObjC.sel("newMachineLearningPipelineStateWithDescriptor:error:");
    private static final long NEW_RENDER_PIPELINE_STATE_BY_SPECIALIZATION_WITH_DESCRIPTOR_PIPELINE_COMPLETION_HANDLER = ObjC.sel("newRenderPipelineStateBySpecializationWithDescriptor:pipeline:completionHandler:");
    private static final long NEW_RENDER_PIPELINE_STATE_BY_SPECIALIZATION_WITH_DESCRIPTOR_PIPELINE_ERROR = ObjC.sel("newRenderPipelineStateBySpecializationWithDescriptor:pipeline:error:");
    private static final long NEW_RENDER_PIPELINE_STATE_WITH_DESCRIPTOR_COMPILER_TASK_OPTIONS_COMPLETION_HANDLER = ObjC.sel("newRenderPipelineStateWithDescriptor:compilerTaskOptions:completionHandler:");
    private static final long NEW_RENDER_PIPELINE_STATE_WITH_DESCRIPTOR_COMPILER_TASK_OPTIONS_ERROR = ObjC.sel("newRenderPipelineStateWithDescriptor:compilerTaskOptions:error:");
    private static final long PIPELINE_DATA_SET_SERIALIZER = ObjC.sel("pipelineDataSetSerializer");

    private static final MethodHandle P_PA = handle(ObjC.PTR, ObjC.PTR, ValueLayout.ADDRESS);
    private static final MethodHandle P_PPA = handle(ObjC.PTR, ObjC.PTR, ObjC.PTR, ValueLayout.ADDRESS);
    private static final MethodHandle P_PPPA = handle(ObjC.PTR, ObjC.PTR, ObjC.PTR, ObjC.PTR, ValueLayout.ADDRESS);
    private static final MethodHandle P_PP = handle(ObjC.PTR, ObjC.PTR, ObjC.PTR);
    private static final MethodHandle P_PPP = handle(ObjC.PTR, ObjC.PTR, ObjC.PTR, ObjC.PTR);
    private static final MethodHandle P_PPPP = handle(ObjC.PTR, ObjC.PTR, ObjC.PTR, ObjC.PTR, ObjC.PTR);

    private MTL4Compiler(long id) {
        super(id);
    }

    public static MTL4Compiler of(long id) {
        return new MTL4Compiler(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    public MTL4PipelineDataSetSerializer pipelineDataSetSerializer() {
        return MTL4PipelineDataSetSerializer.of(sendPtr(id, PIPELINE_DATA_SET_SERIALIZER));
    }

    @SneakyThrows
    public MTLLibrary newLibraryWithDescriptor(MTL4LibraryDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long library = (long) P_PA.invokeExact(id, NEW_LIBRARY_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newLibraryWithDescriptor:error:");
            return MTLLibrary.of(library);
        }
    }

    @SneakyThrows
    public MTLDynamicLibrary newDynamicLibrary(MTLLibrary library) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long result = (long) P_PA.invokeExact(id, NEW_DYNAMIC_LIBRARY_ERROR, library.getId(), error);
            NSError.check(error, "newDynamicLibrary:error:");
            return MTLDynamicLibrary.of(result);
        }
    }

    @SneakyThrows
    public MTLDynamicLibrary newDynamicLibraryWithURL(NSURL url) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long result = (long) P_PA.invokeExact(id, NEW_DYNAMIC_LIBRARY_WITH_URL_ERROR, url.getId(),
                    error);
            NSError.check(error, "newDynamicLibraryWithURL:error:");
            return MTLDynamicLibrary.of(result);
        }
    }

    @SneakyThrows
    public MTLComputePipelineState newComputePipelineStateWithDescriptor(MTL4ComputePipelineDescriptor descriptor,
            MTL4CompilerTaskOptions options) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PPA.invokeExact(id,
                    NEW_COMPUTE_PIPELINE_STATE_WITH_DESCRIPTOR_COMPILER_TASK_OPTIONS_ERROR, descriptor.getId(),
                    options.getId(), error);
            NSError.check(error, "newComputePipelineStateWithDescriptor:compilerTaskOptions:error:");
            return MTLComputePipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLComputePipelineState newComputePipelineStateWithDescriptor(MTL4ComputePipelineDescriptor descriptor,
            MTL4PipelineStageDynamicLinkingDescriptor linking, MTL4CompilerTaskOptions options) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PPPA.invokeExact(id, ObjC.sel("newComputePipelineStateWithDescriptor:"
                    + "dynamicLinkingDescriptor:compilerTaskOptions:error:"),
                    descriptor.getId(), linking.getId(), options.getId(), error);
            NSError.check(error, "newComputePipelineStateWithDescriptor:dynamicLinkingDescriptor:"
                    + "compilerTaskOptions:error:");
            return MTLComputePipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLRenderPipelineState newRenderPipelineStateWithDescriptor(MTL4PipelineDescriptor descriptor,
            MTL4CompilerTaskOptions options) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PPA.invokeExact(id,
                    NEW_RENDER_PIPELINE_STATE_WITH_DESCRIPTOR_COMPILER_TASK_OPTIONS_ERROR, descriptor.getId(),
                    options.getId(), error);
            NSError.check(error, "newRenderPipelineStateWithDescriptor:compilerTaskOptions:error:");
            return MTLRenderPipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLRenderPipelineState newRenderPipelineStateWithDescriptor(MTL4PipelineDescriptor descriptor,
            MTL4RenderPipelineDynamicLinkingDescriptor linking, MTL4CompilerTaskOptions options) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PPPA.invokeExact(id, ObjC.sel("newRenderPipelineStateWithDescriptor:"
                    + "dynamicLinkingDescriptor:compilerTaskOptions:error:"),
                    descriptor.getId(), linking.getId(), options.getId(), error);
            NSError.check(error, "newRenderPipelineStateWithDescriptor:dynamicLinkingDescriptor:"
                    + "compilerTaskOptions:error:");
            return MTLRenderPipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLRenderPipelineState newRenderPipelineStateBySpecializationWithDescriptor(
            MTL4PipelineDescriptor descriptor, MTLRenderPipelineState pipeline) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PPA.invokeExact(id,
                    NEW_RENDER_PIPELINE_STATE_BY_SPECIALIZATION_WITH_DESCRIPTOR_PIPELINE_ERROR,
                    descriptor.getId(), pipeline.getId(), error);
            NSError.check(error, "newRenderPipelineStateBySpecializationWithDescriptor:pipeline:error:");
            return MTLRenderPipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTL4BinaryFunction newBinaryFunctionWithDescriptor(MTL4BinaryFunctionDescriptor descriptor,
            MTL4CompilerTaskOptions options) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long function = (long) P_PPA.invokeExact(id,
                    NEW_BINARY_FUNCTION_WITH_DESCRIPTOR_COMPILER_TASK_OPTIONS_ERROR, descriptor.getId(),
                    options.getId(), error);
            NSError.check(error, "newBinaryFunctionWithDescriptor:compilerTaskOptions:error:");
            return MTL4BinaryFunction.of(function);
        }
    }

    @SneakyThrows
    public MTL4MachineLearningPipelineState newMachineLearningPipelineStateWithDescriptor(
            MTL4MachineLearningPipelineDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PA.invokeExact(id,
                    NEW_MACHINE_LEARNING_PIPELINE_STATE_WITH_DESCRIPTOR_ERROR, descriptor.getId(), error);
            NSError.check(error, "newMachineLearningPipelineStateWithDescriptor:error:");
            return MTL4MachineLearningPipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTL4CompilerTask newLibraryWithDescriptor(MTL4LibraryDescriptor descriptor, Block completion) {
        return MTL4CompilerTask.of((long) P_PP.invokeExact(id,
                NEW_LIBRARY_WITH_DESCRIPTOR_COMPLETION_HANDLER, descriptor.getId(), completion.address()));
    }

    @SneakyThrows
    public MTL4CompilerTask newDynamicLibrary(MTLLibrary library, Block completion) {
        return MTL4CompilerTask.of((long) P_PP.invokeExact(id, NEW_DYNAMIC_LIBRARY_COMPLETION_HANDLER,
                library.getId(), completion.address()));
    }

    @SneakyThrows
    public MTL4CompilerTask newDynamicLibraryWithURL(NSURL url, Block completion) {
        return MTL4CompilerTask.of((long) P_PP.invokeExact(id,
                NEW_DYNAMIC_LIBRARY_WITH_URL_COMPLETION_HANDLER, url.getId(), completion.address()));
    }

    @SneakyThrows
    public MTL4CompilerTask newComputePipelineStateWithDescriptor(MTL4ComputePipelineDescriptor descriptor,
            MTL4CompilerTaskOptions options, Block completion) {
        return MTL4CompilerTask.of((long) P_PPP.invokeExact(id,
                NEW_COMPUTE_PIPELINE_STATE_WITH_DESCRIPTOR_COMPILER_TASK_OPTIONS_COMPLETION_HANDLER,
                descriptor.getId(), options.getId(), completion.address()));
    }

    @SneakyThrows
    public MTL4CompilerTask newComputePipelineStateWithDescriptor(MTL4ComputePipelineDescriptor descriptor,
            MTL4PipelineStageDynamicLinkingDescriptor linking, MTL4CompilerTaskOptions options, Block completion) {
        return MTL4CompilerTask.of((long) P_PPPP.invokeExact(id, ObjC.sel("newComputePipelineStateWithDescriptor:"
                + "dynamicLinkingDescriptor:compilerTaskOptions:completionHandler:"),
                descriptor.getId(), linking.getId(), options.getId(), completion.address()));
    }

    @SneakyThrows
    public MTL4CompilerTask newRenderPipelineStateWithDescriptor(MTL4PipelineDescriptor descriptor,
            MTL4CompilerTaskOptions options, Block completion) {
        return MTL4CompilerTask.of((long) P_PPP.invokeExact(id,
                NEW_RENDER_PIPELINE_STATE_WITH_DESCRIPTOR_COMPILER_TASK_OPTIONS_COMPLETION_HANDLER,
                descriptor.getId(), options.getId(), completion.address()));
    }

    @SneakyThrows
    public MTL4CompilerTask newRenderPipelineStateWithDescriptor(MTL4PipelineDescriptor descriptor,
            MTL4RenderPipelineDynamicLinkingDescriptor linking, MTL4CompilerTaskOptions options, Block completion) {
        return MTL4CompilerTask.of((long) P_PPPP.invokeExact(id, ObjC.sel("newRenderPipelineStateWithDescriptor:"
                + "dynamicLinkingDescriptor:compilerTaskOptions:completionHandler:"),
                descriptor.getId(), linking.getId(), options.getId(), completion.address()));
    }

    @SneakyThrows
    public MTL4CompilerTask newRenderPipelineStateBySpecializationWithDescriptor(
            MTL4PipelineDescriptor descriptor, MTLRenderPipelineState pipeline, Block completion) {
        return MTL4CompilerTask.of((long) P_PPP.invokeExact(id,
                NEW_RENDER_PIPELINE_STATE_BY_SPECIALIZATION_WITH_DESCRIPTOR_PIPELINE_COMPLETION_HANDLER,
                descriptor.getId(), pipeline.getId(), completion.address()));
    }

    @SneakyThrows
    public MTL4CompilerTask newBinaryFunctionWithDescriptor(MTL4BinaryFunctionDescriptor descriptor,
            MTL4CompilerTaskOptions options, Block completion) {
        return MTL4CompilerTask.of((long) P_PPP.invokeExact(id,
                NEW_BINARY_FUNCTION_WITH_DESCRIPTOR_COMPILER_TASK_OPTIONS_COMPLETION_HANDLER,
                descriptor.getId(), options.getId(), completion.address()));
    }

    @SneakyThrows
    public MTL4CompilerTask newMachineLearningPipelineStateWithDescriptor(
            MTL4MachineLearningPipelineDescriptor descriptor, Block completion) {
        return MTL4CompilerTask.of((long) P_PP.invokeExact(id,
                NEW_MACHINE_LEARNING_PIPELINE_STATE_WITH_DESCRIPTOR_COMPLETION_HANDLER, descriptor.getId(),
                completion.address()));
    }
}
