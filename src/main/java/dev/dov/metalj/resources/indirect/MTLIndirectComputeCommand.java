package dev.dov.metalj.resources.indirect;

import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.MTLRegion;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineState;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIndirectComputeCommand extends NSObject {
    private static final long CLEAR_BARRIER = ObjC.sel("clearBarrier");
    private static final long CONCURRENT_DISPATCH_THREADGROUPS_THREADS_PER_THREADGROUP = ObjC.sel("concurrentDispatchThreadgroups:threadsPerThreadgroup:");
    private static final long CONCURRENT_DISPATCH_THREADS_THREADS_PER_THREADGROUP = ObjC.sel("concurrentDispatchThreads:threadsPerThreadgroup:");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_BARRIER = ObjC.sel("setBarrier");
    private static final long SET_COMPUTE_PIPELINE_STATE = ObjC.sel("setComputePipelineState:");
    private static final long SET_IMAGEBLOCK_WIDTH_HEIGHT = ObjC.sel("setImageblockWidth:height:");
    private static final long SET_KERNEL_BUFFER_OFFSET_AT_INDEX = ObjC.sel("setKernelBuffer:offset:atIndex:");
    private static final long SET_STAGE_IN_REGION = ObjC.sel("setStageInRegion:");
    private static final long SET_THREADGROUP_MEMORY_LENGTH_AT_INDEX = ObjC.sel("setThreadgroupMemoryLength:atIndex:");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle LL = handle(null, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle SS = handle(null, MTLSize.LAYOUT, MTLSize.LAYOUT);
    private static final MethodHandle REGION = handle(null, MTLRegion.LAYOUT);

    private MTLIndirectComputeCommand(long id) {
        super(id);
    }

    public static MTLIndirectComputeCommand of(long id) {
        return new MTLIndirectComputeCommand(id);
    }

    @SneakyThrows
    public void setComputePipelineState(MTLComputePipelineState pipelineState) {
        L.invokeExact(id, SET_COMPUTE_PIPELINE_STATE, pipelineState.getId());
    }

    @SneakyThrows
    public void setKernelBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, SET_KERNEL_BUFFER_OFFSET_AT_INDEX, buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void concurrentDispatchThreadgroups(MemorySegment threadgroupsPerGrid, MemorySegment threadsPerThreadgroup) {
        SS.invokeExact(id, CONCURRENT_DISPATCH_THREADGROUPS_THREADS_PER_THREADGROUP, threadgroupsPerGrid,
                threadsPerThreadgroup);
    }

    @SneakyThrows
    public void concurrentDispatchThreads(MemorySegment threadsPerGrid, MemorySegment threadsPerThreadgroup) {
        SS.invokeExact(id, CONCURRENT_DISPATCH_THREADS_THREADS_PER_THREADGROUP, threadsPerGrid,
                threadsPerThreadgroup);
    }

    public void setBarrier() {
        sendVoid(id, SET_BARRIER);
    }

    public void clearBarrier() {
        sendVoid(id, CLEAR_BARRIER);
    }

    @SneakyThrows
    public void setImageblockWidth(long width, long height) {
        LL.invokeExact(id, SET_IMAGEBLOCK_WIDTH_HEIGHT, width, height);
    }

    public void reset() {
        sendVoid(id, RESET);
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length, long index) {
        LL.invokeExact(id, SET_THREADGROUP_MEMORY_LENGTH_AT_INDEX, length, index);
    }

    @SneakyThrows
    public void setStageInRegion(MemorySegment region) {
        REGION.invokeExact(id, SET_STAGE_IN_REGION, region);
    }
}
