package dev.dov.metalj.resources;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.MTLComputePipelineState;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIndirectComputeCommand extends NSObject {
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
        L.invokeExact(id, ObjC.sel("setComputePipelineState:"), pipelineState.getId());
    }

    @SneakyThrows
    public void setKernelBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, ObjC.sel("setKernelBuffer:offset:atIndex:"), buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void concurrentDispatchThreadgroups(MemorySegment threadgroupsPerGrid, MemorySegment threadsPerThreadgroup) {
        SS.invokeExact(id, ObjC.sel("concurrentDispatchThreadgroups:threadsPerThreadgroup:"), threadgroupsPerGrid,
                threadsPerThreadgroup);
    }

    @SneakyThrows
    public void concurrentDispatchThreads(MemorySegment threadsPerGrid, MemorySegment threadsPerThreadgroup) {
        SS.invokeExact(id, ObjC.sel("concurrentDispatchThreads:threadsPerThreadgroup:"), threadsPerGrid,
                threadsPerThreadgroup);
    }

    public void setBarrier() {
        sendVoid(id, "setBarrier");
    }

    public void clearBarrier() {
        sendVoid(id, "clearBarrier");
    }

    @SneakyThrows
    public void setImageblockWidth(long width, long height) {
        LL.invokeExact(id, ObjC.sel("setImageblockWidth:height:"), width, height);
    }

    public void reset() {
        sendVoid(id, "reset");
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length, long index) {
        LL.invokeExact(id, ObjC.sel("setThreadgroupMemoryLength:atIndex:"), length, index);
    }

    @SneakyThrows
    public void setStageInRegion(MemorySegment region) {
        REGION.invokeExact(id, ObjC.sel("setStageInRegion:"), region);
    }
}
