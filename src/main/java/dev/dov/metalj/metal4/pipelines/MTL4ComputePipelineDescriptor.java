package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.metal4.compiler.MTL4FunctionDescriptor;
import dev.dov.metalj.metal4.compiler.MTL4StaticLinkingDescriptor;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4ComputePipelineDescriptor extends MTL4PipelineDescriptor {
    private static final long MTL_4_COMPUTE_PIPELINE_DESCRIPTOR = ObjC.cls("MTL4ComputePipelineDescriptor");

    private static final long COMPUTE_FUNCTION_DESCRIPTOR = ObjC.sel("computeFunctionDescriptor");
    private static final long MAX_TOTAL_THREADS_PER_THREADGROUP = ObjC.sel("maxTotalThreadsPerThreadgroup");
    private static final long NEW = ObjC.sel("new");
    private static final long REQUIRED_THREADS_PER_THREADGROUP = ObjC.sel("requiredThreadsPerThreadgroup");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_COMPUTE_FUNCTION_DESCRIPTOR = ObjC.sel("setComputeFunctionDescriptor:");
    private static final long SET_MAX_TOTAL_THREADS_PER_THREADGROUP = ObjC.sel("setMaxTotalThreadsPerThreadgroup:");
    private static final long SET_REQUIRED_THREADS_PER_THREADGROUP = ObjC.sel("setRequiredThreadsPerThreadgroup:");
    private static final long SET_STATIC_LINKING_DESCRIPTOR = ObjC.sel("setStaticLinkingDescriptor:");
    private static final long SET_SUPPORT_BINARY_LINKING = ObjC.sel("setSupportBinaryLinking:");
    private static final long SET_SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("setSupportIndirectCommandBuffers:");
    private static final long SET_THREAD_GROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH = ObjC.sel("setThreadGroupSizeIsMultipleOfThreadExecutionWidth:");
    private static final long STATIC_LINKING_DESCRIPTOR = ObjC.sel("staticLinkingDescriptor");
    private static final long SUPPORT_BINARY_LINKING = ObjC.sel("supportBinaryLinking");
    private static final long SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("supportIndirectCommandBuffers");
    private static final long THREAD_GROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH = ObjC.sel("threadGroupSizeIsMultipleOfThreadExecutionWidth");

    private static final MethodHandle S = handle(null, MTLSize.LAYOUT);
    private static final MethodHandle SIZE = structHandle(MTLSize.LAYOUT);

    private MTL4ComputePipelineDescriptor(long id) {
        super(id);
    }

    public static MTL4ComputePipelineDescriptor of(long id) {
        return new MTL4ComputePipelineDescriptor(id);
    }

    public static MTL4ComputePipelineDescriptor new_() {
        return new MTL4ComputePipelineDescriptor(sendPtr(MTL_4_COMPUTE_PIPELINE_DESCRIPTOR, NEW));
    }

    public MTL4FunctionDescriptor computeFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, COMPUTE_FUNCTION_DESCRIPTOR));
    }

    @SneakyThrows
    public void setComputeFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, SET_COMPUTE_FUNCTION_DESCRIPTOR, descriptor.getId());
    }

    public boolean threadGroupSizeIsMultipleOfThreadExecutionWidth() {
        return sendBool(id, THREAD_GROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH);
    }

    @SneakyThrows
    public void setThreadGroupSizeIsMultipleOfThreadExecutionWidth(boolean multiple) {
        B.invokeExact(id, SET_THREAD_GROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH, multiple);
    }

    public long maxTotalThreadsPerThreadgroup() {
        return sendLong(id, MAX_TOTAL_THREADS_PER_THREADGROUP);
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerThreadgroup(long threads) {
        L.invokeExact(id, SET_MAX_TOTAL_THREADS_PER_THREADGROUP, threads);
    }

    @SneakyThrows
    public MemorySegment requiredThreadsPerThreadgroup(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, REQUIRED_THREADS_PER_THREADGROUP);
    }

    @SneakyThrows
    public void setRequiredThreadsPerThreadgroup(MemorySegment threads) {
        S.invokeExact(id, SET_REQUIRED_THREADS_PER_THREADGROUP, threads);
    }

    public boolean supportBinaryLinking() {
        return sendBool(id, SUPPORT_BINARY_LINKING);
    }

    @SneakyThrows
    public void setSupportBinaryLinking(boolean support) {
        B.invokeExact(id, SET_SUPPORT_BINARY_LINKING, support);
    }

    public MTL4StaticLinkingDescriptor staticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, STATIC_LINKING_DESCRIPTOR));
    }

    @SneakyThrows
    public void setStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, SET_STATIC_LINKING_DESCRIPTOR, descriptor.getId());
    }

    public long supportIndirectCommandBuffers() {
        return sendLong(id, SUPPORT_INDIRECT_COMMAND_BUFFERS);
    }

    @SneakyThrows
    public void setSupportIndirectCommandBuffers(long state) {
        L.invokeExact(id, SET_SUPPORT_INDIRECT_COMMAND_BUFFERS, state);
    }

    public void reset() {
        sendVoid(id, RESET);
    }
}
