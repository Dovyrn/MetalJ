package dev.dov.metalj.pipelines.compute;

import dev.dov.metalj.pipelines.shaders.MTLFunction;
import dev.dov.metalj.pipelines.render.MTLPipelineBufferDescriptorArray;
import dev.dov.metalj.functions.MTLLinkedFunctions;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLComputePipelineDescriptor extends NSObject {
    private static final long MTL_COMPUTE_PIPELINE_DESCRIPTOR = ObjC.cls("MTLComputePipelineDescriptor");

    private static final long BINARY_ARCHIVES = ObjC.sel("binaryArchives");
    private static final long BUFFERS = ObjC.sel("buffers");
    private static final long COMPUTE_FUNCTION = ObjC.sel("computeFunction");
    private static final long LABEL = ObjC.sel("label");
    private static final long LINKED_FUNCTIONS = ObjC.sel("linkedFunctions");
    private static final long MAX_CALL_STACK_DEPTH = ObjC.sel("maxCallStackDepth");
    private static final long MAX_TOTAL_THREADS_PER_THREADGROUP = ObjC.sel("maxTotalThreadsPerThreadgroup");
    private static final long NEW = ObjC.sel("new");
    private static final long PRELOADED_LIBRARIES = ObjC.sel("preloadedLibraries");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_BINARY_ARCHIVES = ObjC.sel("setBinaryArchives:");
    private static final long SET_COMPUTE_FUNCTION = ObjC.sel("setComputeFunction:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_LINKED_FUNCTIONS = ObjC.sel("setLinkedFunctions:");
    private static final long SET_MAX_CALL_STACK_DEPTH = ObjC.sel("setMaxCallStackDepth:");
    private static final long SET_MAX_TOTAL_THREADS_PER_THREADGROUP = ObjC.sel("setMaxTotalThreadsPerThreadgroup:");
    private static final long SET_PRELOADED_LIBRARIES = ObjC.sel("setPreloadedLibraries:");
    private static final long SET_SHADER_VALIDATION = ObjC.sel("setShaderValidation:");
    private static final long SET_STAGE_INPUT_DESCRIPTOR = ObjC.sel("setStageInputDescriptor:");
    private static final long SET_SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("setSupportIndirectCommandBuffers:");
    private static final long SET_THREAD_GROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH = ObjC.sel("setThreadGroupSizeIsMultipleOfThreadExecutionWidth:");
    private static final long SHADER_VALIDATION = ObjC.sel("shaderValidation");
    private static final long STAGE_INPUT_DESCRIPTOR = ObjC.sel("stageInputDescriptor");
    private static final long SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("supportIndirectCommandBuffers");
    private static final long THREAD_GROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH = ObjC.sel("threadGroupSizeIsMultipleOfThreadExecutionWidth");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLComputePipelineDescriptor(long id) {
        super(id);
    }

    public static MTLComputePipelineDescriptor of(long id) {
        return new MTLComputePipelineDescriptor(id);
    }

    public static MTLComputePipelineDescriptor new_() {
        return new MTLComputePipelineDescriptor(sendPtr(MTL_COMPUTE_PIPELINE_DESCRIPTOR, NEW));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public MTLFunction computeFunction() {
        return MTLFunction.of(sendPtr(id, COMPUTE_FUNCTION));
    }

    @SneakyThrows
    public void setComputeFunction(MTLFunction computeFunction) {
        P.invokeExact(id, SET_COMPUTE_FUNCTION, computeFunction.getId());
    }

    public boolean threadGroupSizeIsMultipleOfThreadExecutionWidth() {
        return sendBool(id, THREAD_GROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH);
    }

    @SneakyThrows
    public void setThreadGroupSizeIsMultipleOfThreadExecutionWidth(boolean threadGroupSizeIsMultipleOfThreadExecutionWidth) {
        B.invokeExact(id, SET_THREAD_GROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH, threadGroupSizeIsMultipleOfThreadExecutionWidth);
    }

    public long maxTotalThreadsPerThreadgroup() {
        return sendLong(id, MAX_TOTAL_THREADS_PER_THREADGROUP);
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerThreadgroup(long maxTotalThreadsPerThreadgroup) {
        L.invokeExact(id, SET_MAX_TOTAL_THREADS_PER_THREADGROUP, maxTotalThreadsPerThreadgroup);
    }

    public MTLPipelineBufferDescriptorArray buffers() {
        return MTLPipelineBufferDescriptorArray.of(sendPtr(id, BUFFERS));
    }

    public boolean supportIndirectCommandBuffers() {
        return sendBool(id, SUPPORT_INDIRECT_COMMAND_BUFFERS);
    }

    @SneakyThrows
    public void setSupportIndirectCommandBuffers(boolean supportIndirectCommandBuffers) {
        B.invokeExact(id, SET_SUPPORT_INDIRECT_COMMAND_BUFFERS, supportIndirectCommandBuffers);
    }

    public long maxCallStackDepth() {
        return sendLong(id, MAX_CALL_STACK_DEPTH);
    }

    @SneakyThrows
    public void setMaxCallStackDepth(long maxCallStackDepth) {
        L.invokeExact(id, SET_MAX_CALL_STACK_DEPTH, maxCallStackDepth);
    }

    public long shaderValidation() {
        return sendLong(id, SHADER_VALIDATION);
    }

    @SneakyThrows
    public void setShaderValidation(long shaderValidation) {
        L.invokeExact(id, SET_SHADER_VALIDATION, shaderValidation);
    }

    public void reset() {
        sendVoid(id, RESET);
    }

    public MTLLinkedFunctions linkedFunctions() {
        return MTLLinkedFunctions.of(sendPtr(id, LINKED_FUNCTIONS));
    }

    @SneakyThrows
    public void setLinkedFunctions(MTLLinkedFunctions functions) {
        P.invokeExact(id, SET_LINKED_FUNCTIONS, functions.getId());
    }

    public NSArray binaryArchives() {
        return NSArray.of(sendPtr(id, BINARY_ARCHIVES));
    }

    @SneakyThrows
    public void setBinaryArchives(NSArray archives) {
        P.invokeExact(id, SET_BINARY_ARCHIVES, archives.getId());
    }

    public NSArray preloadedLibraries() {
        return NSArray.of(sendPtr(id, PRELOADED_LIBRARIES));
    }

    @SneakyThrows
    public void setPreloadedLibraries(NSArray libraries) {
        P.invokeExact(id, SET_PRELOADED_LIBRARIES, libraries.getId());
    }

    public MTLStageInputOutputDescriptor stageInputDescriptor() {
        return MTLStageInputOutputDescriptor.of(sendPtr(id, STAGE_INPUT_DESCRIPTOR));
    }

    @SneakyThrows
    public void setStageInputDescriptor(MTLStageInputOutputDescriptor descriptor) {
        P.invokeExact(id, SET_STAGE_INPUT_DESCRIPTOR, descriptor.getId());
    }
}
