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
    private static final long MTL_4_MACHINE_LEARNING_PIPELINE_DESCRIPTOR = ObjC.cls("MTL4MachineLearningPipelineDescriptor");

    private static final long INPUT_DIMENSIONS_AT_BUFFER_INDEX = ObjC.sel("inputDimensionsAtBufferIndex:");
    private static final long MACHINE_LEARNING_FUNCTION_DESCRIPTOR = ObjC.sel("machineLearningFunctionDescriptor");
    private static final long NEW = ObjC.sel("new");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_INPUT_DIMENSIONS_AT_BUFFER_INDEX = ObjC.sel("setInputDimensions:atBufferIndex:");
    private static final long SET_INPUT_DIMENSIONS_WITH_RANGE = ObjC.sel("setInputDimensions:withRange:");
    private static final long SET_MACHINE_LEARNING_FUNCTION_DESCRIPTOR = ObjC.sel("setMachineLearningFunctionDescriptor:");

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
                sendPtr(MTL_4_MACHINE_LEARNING_PIPELINE_DESCRIPTOR, NEW));
    }

    public MTL4FunctionDescriptor machineLearningFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, MACHINE_LEARNING_FUNCTION_DESCRIPTOR));
    }

    @SneakyThrows
    public void setMachineLearningFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, SET_MACHINE_LEARNING_FUNCTION_DESCRIPTOR, descriptor.getId());
    }

    @SneakyThrows
    public void setInputDimensions(MTLTensorExtents dimensions, long index) {
        PL.invokeExact(id, SET_INPUT_DIMENSIONS_AT_BUFFER_INDEX, dimensions.getId(), index);
    }

    @SneakyThrows
    public void setInputDimensions(NSArray dimensions, MemorySegment range) {
        PR.invokeExact(id, SET_INPUT_DIMENSIONS_WITH_RANGE, dimensions.getId(), range);
    }

    @SneakyThrows
    public MTLTensorExtents inputDimensionsAtBufferIndex(long index) {
        return MTLTensorExtents.of((long) P_L.invokeExact(id, INPUT_DIMENSIONS_AT_BUFFER_INDEX, index));
    }

    public void reset() {
        sendVoid(id, RESET);
    }
}
