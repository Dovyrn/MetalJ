package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.metal4.compiler.MTL4FunctionDescriptor;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.tensors.MTLTensorExtents;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4MachineLearningPipelineDescriptor extends MTL4PipelineDescriptor {
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PR = handle(null, ObjC.PTR, NSRange.LAYOUT);
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);

    private MTL4MachineLearningPipelineDescriptor(long id) {
        super(id);
    }

    public static MTL4MachineLearningPipelineDescriptor of(long id) {
        return new MTL4MachineLearningPipelineDescriptor(id);
    }

    public static MTL4MachineLearningPipelineDescriptor new_() {
        return new MTL4MachineLearningPipelineDescriptor(
                sendPtr(ObjC.cls("MTL4MachineLearningPipelineDescriptor"), "new"));
    }

    public MTL4FunctionDescriptor machineLearningFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, "machineLearningFunctionDescriptor"));
    }

    @SneakyThrows
    public void setMachineLearningFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setMachineLearningFunctionDescriptor:"), descriptor.getId());
    }

    @SneakyThrows
    public void setInputDimensions(MTLTensorExtents dimensions, long index) {
        PL.invokeExact(id, ObjC.sel("setInputDimensions:atBufferIndex:"), dimensions.getId(), index);
    }

    @SneakyThrows
    public void setInputDimensions(NSArray dimensions, MemorySegment range) {
        PR.invokeExact(id, ObjC.sel("setInputDimensions:withRange:"), dimensions.getId(), range);
    }

    @SneakyThrows
    public MTLTensorExtents inputDimensionsAtBufferIndex(long index) {
        return MTLTensorExtents.of((long) P_L.invokeExact(id, ObjC.sel("inputDimensionsAtBufferIndex:"), index));
    }

    public void reset() {
        sendVoid(id, "reset");
    }
}
