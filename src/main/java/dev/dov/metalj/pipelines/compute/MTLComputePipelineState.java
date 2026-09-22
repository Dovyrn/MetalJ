package dev.dov.metalj.pipelines.compute;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.pipelines.shaders.MTLFunction;
import dev.dov.metalj.pipelines.shaders.MTLFunctionHandle;
import dev.dov.metalj.functions.MTLVisibleFunctionTable;
import dev.dov.metalj.functions.MTLVisibleFunctionTableDescriptor;
import dev.dov.metalj.raytracing.MTLIntersectionFunctionTable;
import dev.dov.metalj.raytracing.MTLIntersectionFunctionTableDescriptor;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLComputePipelineState extends NSObject {
    private static final long DEVICE = ObjC.sel("device");
    private static final long FUNCTION_HANDLE_WITH_FUNCTION = ObjC.sel("functionHandleWithFunction:");
    private static final long GPU_RESOURCE_ID = ObjC.sel("gpuResourceID");
    private static final long IMAGEBLOCK_MEMORY_LENGTH_FOR_DIMENSIONS = ObjC.sel("imageblockMemoryLengthForDimensions:");
    private static final long LABEL = ObjC.sel("label");
    private static final long MAX_TOTAL_THREADS_PER_THREADGROUP = ObjC.sel("maxTotalThreadsPerThreadgroup");
    private static final long NEW_INTERSECTION_FUNCTION_TABLE_WITH_DESCRIPTOR = ObjC.sel("newIntersectionFunctionTableWithDescriptor:");
    private static final long NEW_VISIBLE_FUNCTION_TABLE_WITH_DESCRIPTOR = ObjC.sel("newVisibleFunctionTableWithDescriptor:");
    private static final long STATIC_THREADGROUP_MEMORY_LENGTH = ObjC.sel("staticThreadgroupMemoryLength");
    private static final long SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("supportIndirectCommandBuffers");
    private static final long THREAD_EXECUTION_WIDTH = ObjC.sel("threadExecutionWidth");

    private static final MethodHandle L_S = handle(ObjC.LONG, MTLSize.LAYOUT);
    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);

    private MTLComputePipelineState(long id) {
        super(id);
    }

    public static MTLComputePipelineState of(long id) {
        return new MTLComputePipelineState(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public long maxTotalThreadsPerThreadgroup() {
        return sendLong(id, MAX_TOTAL_THREADS_PER_THREADGROUP);
    }

    public long threadExecutionWidth() {
        return sendLong(id, THREAD_EXECUTION_WIDTH);
    }

    public long staticThreadgroupMemoryLength() {
        return sendLong(id, STATIC_THREADGROUP_MEMORY_LENGTH);
    }

    @SneakyThrows
    public long imageblockMemoryLengthForDimensions(MemorySegment size) {
        return (long) L_S.invokeExact(id, IMAGEBLOCK_MEMORY_LENGTH_FOR_DIMENSIONS, size);
    }

    public boolean supportIndirectCommandBuffers() {
        return sendBool(id, SUPPORT_INDIRECT_COMMAND_BUFFERS);
    }

    public long gpuResourceID() {
        return sendLong(id, GPU_RESOURCE_ID);
    }

    @SneakyThrows
    public MTLFunctionHandle functionHandleWithFunction(MTLFunction function) {
        return MTLFunctionHandle.of((long) P_P.invokeExact(id, FUNCTION_HANDLE_WITH_FUNCTION,
                function.getId()));
    }

    @SneakyThrows
    public MTLIntersectionFunctionTable newIntersectionFunctionTableWithDescriptor(
            MTLIntersectionFunctionTableDescriptor descriptor) {
        return MTLIntersectionFunctionTable.of((long) P_P.invokeExact(id,
                NEW_INTERSECTION_FUNCTION_TABLE_WITH_DESCRIPTOR, descriptor.getId()));
    }

    @SneakyThrows
    public MTLVisibleFunctionTable newVisibleFunctionTableWithDescriptor(
            MTLVisibleFunctionTableDescriptor descriptor) {
        return MTLVisibleFunctionTable.of((long) P_P.invokeExact(id,
                NEW_VISIBLE_FUNCTION_TABLE_WITH_DESCRIPTOR, descriptor.getId()));
    }
}
