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
    private static final long DISPATCH_THREADGROUPS_THREADS_PER_THREADGROUP = ObjC.sel("dispatchThreadgroups:threadsPerThreadgroup:");
    private static final long DISPATCH_THREADGROUPS_WITH_INDIRECT_BUFFER_INDIRECT_BUFFER_OFFSET_THREADS_PER_THREADGROUP = ObjC.sel("dispatchThreadgroupsWithIndirectBuffer:indirectBufferOffset:threadsPerThreadgroup:");
    private static final long DISPATCH_THREADS_THREADS_PER_THREADGROUP = ObjC.sel("dispatchThreads:threadsPerThreadgroup:");
    private static final long DISPATCH_TYPE = ObjC.sel("dispatchType");
    private static final long EXECUTE_COMMANDS_IN_BUFFER_INDIRECT_BUFFER_INDIRECT_BUFFER_OFFSET = ObjC.sel("executeCommandsInBuffer:indirectBuffer:indirectBufferOffset:");
    private static final long EXECUTE_COMMANDS_IN_BUFFER_WITH_RANGE = ObjC.sel("executeCommandsInBuffer:withRange:");
    private static final long MEMORY_BARRIER_WITH_RESOURCES_COUNT = ObjC.sel("memoryBarrierWithResources:count:");
    private static final long MEMORY_BARRIER_WITH_SCOPE = ObjC.sel("memoryBarrierWithScope:");
    private static final long SAMPLE_COUNTERS_IN_BUFFER_AT_SAMPLE_INDEX_WITH_BARRIER = ObjC.sel("sampleCountersInBuffer:atSampleIndex:withBarrier:");
    private static final long SET_ACCELERATION_STRUCTURE_AT_BUFFER_INDEX = ObjC.sel("setAccelerationStructure:atBufferIndex:");
    private static final long SET_BUFFER_OFFSET_AT_INDEX = ObjC.sel("setBuffer:offset:atIndex:");
    private static final long SETBUFFEROFFSET_ATINDEX = ObjC.sel("setBufferOffset:atIndex:");
    private static final long SET_BUFFERS_OFFSETS_WITH_RANGE = ObjC.sel("setBuffers:offsets:withRange:");
    private static final long SET_BYTES_LENGTH_AT_INDEX = ObjC.sel("setBytes:length:atIndex:");
    private static final long SET_COMPUTE_PIPELINE_STATE = ObjC.sel("setComputePipelineState:");
    private static final long SET_IMAGEBLOCK_WIDTH_HEIGHT = ObjC.sel("setImageblockWidth:height:");
    private static final long SET_INTERSECTION_FUNCTION_TABLE_AT_BUFFER_INDEX = ObjC.sel("setIntersectionFunctionTable:atBufferIndex:");
    private static final long SET_INTERSECTION_FUNCTION_TABLES_WITH_BUFFER_RANGE = ObjC.sel("setIntersectionFunctionTables:withBufferRange:");
    private static final long SET_SAMPLER_STATE_AT_INDEX = ObjC.sel("setSamplerState:atIndex:");
    private static final long SET_SAMPLER_STATE_LOD_MIN_CLAMP_LOD_MAX_CLAMP_AT_INDEX = ObjC.sel("setSamplerState:lodMinClamp:lodMaxClamp:atIndex:");
    private static final long SET_SAMPLER_STATES_LOD_MIN_CLAMPS_LOD_MAX_CLAMPS_WITH_RANGE = ObjC.sel("setSamplerStates:lodMinClamps:lodMaxClamps:withRange:");
    private static final long SET_SAMPLER_STATES_WITH_RANGE = ObjC.sel("setSamplerStates:withRange:");
    private static final long SET_STAGE_IN_REGION = ObjC.sel("setStageInRegion:");
    private static final long SET_STAGE_IN_REGION_WITH_INDIRECT_BUFFER_INDIRECT_BUFFER_OFFSET = ObjC.sel("setStageInRegionWithIndirectBuffer:indirectBufferOffset:");
    private static final long SET_TEXTURE_AT_INDEX = ObjC.sel("setTexture:atIndex:");
    private static final long SET_TEXTURES_WITH_RANGE = ObjC.sel("setTextures:withRange:");
    private static final long SET_THREADGROUP_MEMORY_LENGTH_AT_INDEX = ObjC.sel("setThreadgroupMemoryLength:atIndex:");
    private static final long SET_VISIBLE_FUNCTION_TABLE_AT_BUFFER_INDEX = ObjC.sel("setVisibleFunctionTable:atBufferIndex:");
    private static final long SET_VISIBLE_FUNCTION_TABLES_WITH_BUFFER_RANGE = ObjC.sel("setVisibleFunctionTables:withBufferRange:");
    private static final long UPDATE_FENCE = ObjC.sel("updateFence:");
    private static final long USE_HEAP = ObjC.sel("useHeap:");
    private static final long USE_HEAPS_COUNT = ObjC.sel("useHeaps:count:");
    private static final long USE_RESOURCE_USAGE = ObjC.sel("useResource:usage:");
    private static final long USE_RESOURCES_COUNT_USAGE = ObjC.sel("useResources:count:usage:");
    private static final long WAIT_FOR_FENCE = ObjC.sel("waitForFence:");

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
        return sendLong(id, DISPATCH_TYPE);
    }

    @SneakyThrows
    public void setComputePipelineState(MTLComputePipelineState state) {
        L.invokeExact(id, SET_COMPUTE_PIPELINE_STATE, state.getId());
    }

    @SneakyThrows
    public void setBytes(MemorySegment bytes, long length, long index) {
        ALL.invokeExact(id, SET_BYTES_LENGTH_AT_INDEX, bytes, length, index);
    }

    @SneakyThrows
    public void setBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, SET_BUFFER_OFFSET_AT_INDEX, buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setBufferOffset(long offset, long index) {
        LL.invokeExact(id, SETBUFFEROFFSET_ATINDEX, offset, index);
    }

    @SneakyThrows
    public void setBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, SET_BUFFERS_OFFSETS_WITH_RANGE, buffers, offsets, range);
    }

    @SneakyThrows
    public void setTexture(MTLTexture texture, long index) {
        LL.invokeExact(id, SET_TEXTURE_AT_INDEX, texture.getId(), index);
    }

    @SneakyThrows
    public void setTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, SET_TEXTURES_WITH_RANGE, textures, range);
    }

    @SneakyThrows
    public void setSamplerState(MTLSamplerState sampler, long index) {
        LL.invokeExact(id, SET_SAMPLER_STATE_AT_INDEX, sampler.getId(), index);
    }

    @SneakyThrows
    public void setSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, SET_SAMPLER_STATES_WITH_RANGE, samplers, range);
    }

    @SneakyThrows
    public void setSamplerState(MTLSamplerState sampler, float lodMinClamp, float lodMaxClamp, long index) {
        LFFL.invokeExact(id, SET_SAMPLER_STATE_LOD_MIN_CLAMP_LOD_MAX_CLAMP_AT_INDEX, sampler.getId(),
                lodMinClamp, lodMaxClamp, index);
    }

    @SneakyThrows
    public void setSamplerStates(MemorySegment samplers, MemorySegment lodMinClamps, MemorySegment lodMaxClamps,
            MemorySegment range) {
        AAAR.invokeExact(id, SET_SAMPLER_STATES_LOD_MIN_CLAMPS_LOD_MAX_CLAMPS_WITH_RANGE, samplers,
                lodMinClamps, lodMaxClamps, range);
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length, long index) {
        LL.invokeExact(id, SET_THREADGROUP_MEMORY_LENGTH_AT_INDEX, length, index);
    }

    @SneakyThrows
    public void setImageblockWidth(long width, long height) {
        LL.invokeExact(id, SET_IMAGEBLOCK_WIDTH_HEIGHT, width, height);
    }

    @SneakyThrows
    public void setStageInRegion(MemorySegment region) {
        REGION.invokeExact(id, SET_STAGE_IN_REGION, region);
    }

    @SneakyThrows
    public void setStageInRegionWithIndirectBuffer(MTLBuffer indirectBuffer, long indirectBufferOffset) {
        LL.invokeExact(id, SET_STAGE_IN_REGION_WITH_INDIRECT_BUFFER_INDIRECT_BUFFER_OFFSET,
                indirectBuffer.getId(), indirectBufferOffset);
    }

    @SneakyThrows
    public void dispatchThreadgroups(MemorySegment threadgroupsPerGrid, MemorySegment threadsPerThreadgroup) {
        SS.invokeExact(id, DISPATCH_THREADGROUPS_THREADS_PER_THREADGROUP, threadgroupsPerGrid,
                threadsPerThreadgroup);
    }

    @SneakyThrows
    public void dispatchThreadgroupsWithIndirectBuffer(MTLBuffer indirectBuffer, long indirectBufferOffset,
            MemorySegment threadsPerThreadgroup) {
        LLS.invokeExact(id, DISPATCH_THREADGROUPS_WITH_INDIRECT_BUFFER_INDIRECT_BUFFER_OFFSET_THREADS_PER_THREADGROUP,
                indirectBuffer.getId(), indirectBufferOffset, threadsPerThreadgroup);
    }

    @SneakyThrows
    public void dispatchThreads(MemorySegment threadsPerGrid, MemorySegment threadsPerThreadgroup) {
        SS.invokeExact(id, DISPATCH_THREADS_THREADS_PER_THREADGROUP, threadsPerGrid, threadsPerThreadgroup);
    }

    @SneakyThrows
    public void useResource(MTLResource resource, long usage) {
        LL.invokeExact(id, USE_RESOURCE_USAGE, resource.getId(), usage);
    }

    @SneakyThrows
    public void useResources(MemorySegment resources, long count, long usage) {
        ALL.invokeExact(id, USE_RESOURCES_COUNT_USAGE, resources, count, usage);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, MemorySegment range) {
        LR.invokeExact(id, EXECUTE_COMMANDS_IN_BUFFER_WITH_RANGE, buffer.getId(), range);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, MTLBuffer indirectBuffer,
            long indirectBufferOffset) {
        LLL.invokeExact(id, EXECUTE_COMMANDS_IN_BUFFER_INDIRECT_BUFFER_INDIRECT_BUFFER_OFFSET,
                buffer.getId(), indirectBuffer.getId(), indirectBufferOffset);
    }

    @SneakyThrows
    public void memoryBarrierWithScope(long scope) {
        L.invokeExact(id, MEMORY_BARRIER_WITH_SCOPE, scope);
    }

    @SneakyThrows
    public void memoryBarrierWithResources(MemorySegment resources, long count) {
        AL.invokeExact(id, MEMORY_BARRIER_WITH_RESOURCES_COUNT, resources, count);
    }

    @SneakyThrows
    public void sampleCountersInBuffer(MTLCounterSampleBuffer sampleBuffer, long sampleIndex, boolean barrier) {
        LLB.invokeExact(id, SAMPLE_COUNTERS_IN_BUFFER_AT_SAMPLE_INDEX_WITH_BARRIER, sampleBuffer.getId(),
                sampleIndex, barrier);
    }

    @SneakyThrows
    public void setAccelerationStructure(MTLAccelerationStructure structure, long index) {
        LL.invokeExact(id, SET_ACCELERATION_STRUCTURE_AT_BUFFER_INDEX, structure.getId(), index);
    }

    @SneakyThrows
    public void setIntersectionFunctionTable(MTLIntersectionFunctionTable table, long index) {
        LL.invokeExact(id, SET_INTERSECTION_FUNCTION_TABLE_AT_BUFFER_INDEX, table.getId(), index);
    }

    @SneakyThrows
    public void setIntersectionFunctionTables(MemorySegment tables, MemorySegment range) {
        AR.invokeExact(id, SET_INTERSECTION_FUNCTION_TABLES_WITH_BUFFER_RANGE, tables, range);
    }

    @SneakyThrows
    public void updateFence(MTLFence fence) {
        L.invokeExact(id, UPDATE_FENCE, fence.getId());
    }

    @SneakyThrows
    public void waitForFence(MTLFence fence) {
        L.invokeExact(id, WAIT_FOR_FENCE, fence.getId());
    }

    @SneakyThrows
    public void setVisibleFunctionTable(MTLVisibleFunctionTable table, long index) {
        LL.invokeExact(id, SET_VISIBLE_FUNCTION_TABLE_AT_BUFFER_INDEX, table.getId(), index);
    }

    @SneakyThrows
    public void setVisibleFunctionTables(MemorySegment tables, MemorySegment range) {
        AR.invokeExact(id, SET_VISIBLE_FUNCTION_TABLES_WITH_BUFFER_RANGE, tables, range);
    }

    @SneakyThrows
    public void useHeap(MTLHeap heap) {
        L.invokeExact(id, USE_HEAP, heap.getId());
    }

    @SneakyThrows
    public void useHeaps(MemorySegment heaps, long count) {
        AL.invokeExact(id, USE_HEAPS_COUNT, heaps, count);
    }
}
