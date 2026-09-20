package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.metal4.pipelines.MTL4ComputePipelineDescriptor;
import dev.dov.metalj.metal4.pipelines.MTL4PipelineDescriptor;
import dev.dov.metalj.objc.NSError;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineState;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineState;
import java.lang.foreign.Arena;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4Archive extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle P_PA = handle(ObjC.PTR, ObjC.PTR, ValueLayout.ADDRESS);
    private static final MethodHandle P_PPA = handle(ObjC.PTR, ObjC.PTR, ObjC.PTR, ValueLayout.ADDRESS);

    private MTL4Archive(long id) {
        super(id);
    }

    public static MTL4Archive of(long id) {
        return new MTL4Archive(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    @SneakyThrows
    public MTLComputePipelineState newComputePipelineStateWithDescriptor(MTL4ComputePipelineDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PA.invokeExact(id, ObjC.sel("newComputePipelineStateWithDescriptor:error:"),
                    descriptor.getId(), error);
            NSError.check(error, "newComputePipelineStateWithDescriptor:error:");
            return MTLComputePipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLComputePipelineState newComputePipelineStateWithDescriptor(MTL4ComputePipelineDescriptor descriptor,
            MTL4PipelineStageDynamicLinkingDescriptor linking) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PPA.invokeExact(id,
                    ObjC.sel("newComputePipelineStateWithDescriptor:dynamicLinkingDescriptor:error:"),
                    descriptor.getId(), linking.getId(), error);
            NSError.check(error, "newComputePipelineStateWithDescriptor:dynamicLinkingDescriptor:error:");
            return MTLComputePipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLRenderPipelineState newRenderPipelineStateWithDescriptor(MTL4PipelineDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PA.invokeExact(id, ObjC.sel("newRenderPipelineStateWithDescriptor:error:"),
                    descriptor.getId(), error);
            NSError.check(error, "newRenderPipelineStateWithDescriptor:error:");
            return MTLRenderPipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLRenderPipelineState newRenderPipelineStateWithDescriptor(MTL4PipelineDescriptor descriptor,
            MTL4RenderPipelineDynamicLinkingDescriptor linking) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PPA.invokeExact(id,
                    ObjC.sel("newRenderPipelineStateWithDescriptor:dynamicLinkingDescriptor:error:"),
                    descriptor.getId(), linking.getId(), error);
            NSError.check(error, "newRenderPipelineStateWithDescriptor:dynamicLinkingDescriptor:error:");
            return MTLRenderPipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTL4BinaryFunction newBinaryFunctionWithDescriptor(MTL4BinaryFunctionDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long function = (long) P_PA.invokeExact(id, ObjC.sel("newBinaryFunctionWithDescriptor:error:"),
                    descriptor.getId(), error);
            NSError.check(error, "newBinaryFunctionWithDescriptor:error:");
            return MTL4BinaryFunction.of(function);
        }
    }
}
