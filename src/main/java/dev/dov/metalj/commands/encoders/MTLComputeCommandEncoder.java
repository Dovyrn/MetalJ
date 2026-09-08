package dev.dov.metalj.commands.encoders;

import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.raytracing.MTLAccelerationStructure;
import dev.dov.metalj.raytracing.MTLIntersectionFunctionTable;
import dev.dov.metalj.functions.MTLVisibleFunctionTable;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.sync.MTLFence;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineState;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.indirect.MTLIndirectCommandBuffer;
import dev.dov.metalj.resources.MTLRegion;
import dev.dov.metalj.resources.MTLResource;
import dev.dov.metalj.resources.heaps.MTLHeap;
import dev.dov.metalj.resources.samplers.MTLSamplerState;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.textures.MTLTexture;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLComputeCommandEncoder extends MTLCommandEncoder {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle LL = handle(null, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLB = handle(null, ObjC.LONG, ObjC.LONG, ObjC.BOOL);
    private static final MethodHandle LFFL = handle(null, ObjC.LONG, ObjC.FLOAT, ObjC.FLOAT, ObjC.LONG);
    private static final MethodHandle ALL = handle(null, ValueLayout.ADDRESS, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle AL = handle(null, ValueLayout.ADDRESS, ObjC.LONG);
    private static final MethodHandle AAR = handle(null, ValueLayout.ADDRESS, ValueLayout.ADDRESS, NSRange.LAYOUT);
    private static final MethodHandle AR = handle(null, ValueLayout.ADDRESS, NSRange.LAYOUT);
    private static final MethodHandle AAAR = handle(null, ValueLayout.ADDRESS, ValueLayout.ADDRESS,
            ValueLayout.ADDRESS, NSRange.LAYOUT);
    private static final MethodHandle LR = handle(null, ObjC.LONG, NSRange.LAYOUT);
    private static final MethodHandle REGION = handle(null, MTLRegion.LAYOUT);
    private static final MethodHandle SS = handle(null, MTLSize.LAYOUT, MTLSize.LAYOUT);
    private static final MethodHandle LLS = handle(null, ObjC.LONG, ObjC.LONG, MTLSize.LAYOUT);

    private MTLComputeCommandEncoder(long id) {
        super(id);
    }

    public static MTLComputeCommandEncoder of(long id) {
        return new MTLComputeCommandEncoder(id);
    }

    public long dispatchType() {
        return sendLong(id, "dispatchType");
    }

    @SneakyThrows
    public void setComputePipelineState(MTLComputePipelineState state) {
        L.invokeExact(id, ObjC.sel("setComputePipelineState:"), state.getId());
    }

    @SneakyThrows
    public void setBytes(MemorySegment bytes, long length, long index) {
        ALL.invokeExact(id, ObjC.sel("setBytes:length:atIndex:"), bytes, length, index);
    }

    @SneakyThrows
    public void setBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, ObjC.sel("setBuffer:offset:atIndex:"), buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setBufferOffset(long offset, long index) {
        LL.invokeExact(id, ObjC.sel("setBufferOffset:atIndex:"), offset, index);
    }

    @SneakyThrows
    public void setBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, ObjC.sel("setBuffers:offsets:withRange:"), buffers, offsets, range);
    }

    @SneakyThrows
    public void setTexture(MTLTexture texture, long index) {
        LL.invokeExact(id, ObjC.sel("setTexture:atIndex:"), texture.getId(), index);
    }

    @SneakyThrows
    public void setTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setTextures:withRange:"), textures, range);
    }

    @SneakyThrows
    public void setSamplerState(MTLSamplerState sampler, long index) {
        LL.invokeExact(id, ObjC.sel("setSamplerState:atIndex:"), sampler.getId(), index);
    }

    @SneakyThrows
    public void setSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setSamplerStates:withRange:"), samplers, range);
    }

    @SneakyThrows
    public void setSamplerState(MTLSamplerState sampler, float lodMinClamp, float lodMaxClamp, long index) {
        LFFL.invokeExact(id, ObjC.sel("setSamplerState:lodMinClamp:lodMaxClamp:atIndex:"), sampler.getId(),
                lodMinClamp, lodMaxClamp, index);
    }

    @SneakyThrows
    public void setSamplerStates(MemorySegment samplers, MemorySegment lodMinClamps, MemorySegment lodMaxClamps,
            MemorySegment range) {
        AAAR.invokeExact(id, ObjC.sel("setSamplerStates:lodMinClamps:lodMaxClamps:withRange:"), samplers,
                lodMinClamps, lodMaxClamps, range);
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length, long index) {
        LL.invokeExact(id, ObjC.sel("setThreadgroupMemoryLength:atIndex:"), length, index);
    }

    @SneakyThrows
    public void setImageblockWidth(long width, long height) {
        LL.invokeExact(id, ObjC.sel("setImageblockWidth:height:"), width, height);
    }

    @SneakyThrows
    public void setStageInRegion(MemorySegment region) {
        REGION.invokeExact(id, ObjC.sel("setStageInRegion:"), region);
    }

    @SneakyThrows
    public void setStageInRegionWithIndirectBuffer(MTLBuffer indirectBuffer, long indirectBufferOffset) {
        LL.invokeExact(id, ObjC.sel("setStageInRegionWithIndirectBuffer:indirectBufferOffset:"),
                indirectBuffer.getId(), indirectBufferOffset);
    }

    @SneakyThrows
    public void dispatchThreadgroups(MemorySegment threadgroupsPerGrid, MemorySegment threadsPerThreadgroup) {
        SS.invokeExact(id, ObjC.sel("dispatchThreadgroups:threadsPerThreadgroup:"), threadgroupsPerGrid,
                threadsPerThreadgroup);
    }

    @SneakyThrows
    public void dispatchThreadgroupsWithIndirectBuffer(MTLBuffer indirectBuffer, long indirectBufferOffset,
            MemorySegment threadsPerThreadgroup) {
        LLS.invokeExact(id, ObjC.sel("dispatchThreadgroupsWithIndirectBuffer:indirectBufferOffset:threadsPerThreadgroup:"),
                indirectBuffer.getId(), indirectBufferOffset, threadsPerThreadgroup);
    }

    @SneakyThrows
    public void dispatchThreads(MemorySegment threadsPerGrid, MemorySegment threadsPerThreadgroup) {
        SS.invokeExact(id, ObjC.sel("dispatchThreads:threadsPerThreadgroup:"), threadsPerGrid, threadsPerThreadgroup);
    }

    @SneakyThrows
    public void useResource(MTLResource resource, long usage) {
        LL.invokeExact(id, ObjC.sel("useResource:usage:"), resource.getId(), usage);
    }

    @SneakyThrows
    public void useResources(MemorySegment resources, long count, long usage) {
        ALL.invokeExact(id, ObjC.sel("useResources:count:usage:"), resources, count, usage);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, MemorySegment range) {
        LR.invokeExact(id, ObjC.sel("executeCommandsInBuffer:withRange:"), buffer.getId(), range);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, MTLBuffer indirectBuffer,
            long indirectBufferOffset) {
        LLL.invokeExact(id, ObjC.sel("executeCommandsInBuffer:indirectBuffer:indirectBufferOffset:"),
                buffer.getId(), indirectBuffer.getId(), indirectBufferOffset);
    }

    @SneakyThrows
    public void memoryBarrierWithScope(long scope) {
        L.invokeExact(id, ObjC.sel("memoryBarrierWithScope:"), scope);
    }

    @SneakyThrows
    public void memoryBarrierWithResources(MemorySegment resources, long count) {
        AL.invokeExact(id, ObjC.sel("memoryBarrierWithResources:count:"), resources, count);
    }

    @SneakyThrows
    public void sampleCountersInBuffer(MTLCounterSampleBuffer sampleBuffer, long sampleIndex, boolean barrier) {
        LLB.invokeExact(id, ObjC.sel("sampleCountersInBuffer:atSampleIndex:withBarrier:"), sampleBuffer.getId(),
                sampleIndex, barrier);
    }

    @SneakyThrows
    public void setAccelerationStructure(MTLAccelerationStructure structure, long index) {
        LL.invokeExact(id, ObjC.sel("setAccelerationStructure:atBufferIndex:"), structure.getId(), index);
    }

    @SneakyThrows
    public void setIntersectionFunctionTable(MTLIntersectionFunctionTable table, long index) {
        LL.invokeExact(id, ObjC.sel("setIntersectionFunctionTable:atBufferIndex:"), table.getId(), index);
    }

    @SneakyThrows
    public void setIntersectionFunctionTables(MemorySegment tables, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setIntersectionFunctionTables:withBufferRange:"), tables, range);
    }

    @SneakyThrows
    public void updateFence(MTLFence fence) {
        L.invokeExact(id, ObjC.sel("updateFence:"), fence.getId());
    }

    @SneakyThrows
    public void waitForFence(MTLFence fence) {
        L.invokeExact(id, ObjC.sel("waitForFence:"), fence.getId());
    }

    @SneakyThrows
    public void setVisibleFunctionTable(MTLVisibleFunctionTable table, long index) {
        LL.invokeExact(id, ObjC.sel("setVisibleFunctionTable:atBufferIndex:"), table.getId(), index);
    }

    @SneakyThrows
    public void setVisibleFunctionTables(MemorySegment tables, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setVisibleFunctionTables:withBufferRange:"), tables, range);
    }

    @SneakyThrows
    public void useHeap(MTLHeap heap) {
        L.invokeExact(id, ObjC.sel("useHeap:"), heap.getId());
    }

    @SneakyThrows
    public void useHeaps(MemorySegment heaps, long count) {
        AL.invokeExact(id, ObjC.sel("useHeaps:count:"), heaps, count);
    }
}
