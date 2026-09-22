package dev.dov.metalj.raytracing;

import dev.dov.metalj.commands.encoders.MTLCommandEncoder;
import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLResource;
import dev.dov.metalj.sync.MTLFence;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.heaps.MTLHeap;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLAccelerationStructureCommandEncoder extends MTLCommandEncoder {
    private static final long BUILD_ACCELERATION_STRUCTURE_DESCRIPTOR_SCRATCH_BUFFER_SCRATCH_BUFFER_OFFSET = ObjC.sel("buildAccelerationStructure:descriptor:scratchBuffer:scratchBufferOffset:");
    private static final long COPY_ACCELERATION_STRUCTURE_TO_ACCELERATION_STRUCTURE = ObjC.sel("copyAccelerationStructure:toAccelerationStructure:");
    private static final long COPY_AND_COMPACT_ACCELERATION_STRUCTURE_TO_ACCELERATION_STRUCTURE = ObjC.sel("copyAndCompactAccelerationStructure:toAccelerationStructure:");
    private static final long REFIT_ACCELERATION_STRUCTURE_DESCRIPTOR_DESTINATION_SCRATCH_BUFFER_SCRATCH_BUFFER_OFFSET = ObjC.sel("refitAccelerationStructure:descriptor:destination:scratchBuffer:scratchBufferOffset:");
    private static final long SAMPLE_COUNTERS_IN_BUFFER_AT_SAMPLE_INDEX_WITH_BARRIER = ObjC.sel("sampleCountersInBuffer:atSampleIndex:withBarrier:");
    private static final long UPDATE_FENCE = ObjC.sel("updateFence:");
    private static final long USE_HEAP = ObjC.sel("useHeap:");
    private static final long USE_HEAPS_COUNT = ObjC.sel("useHeaps:count:");
    private static final long USE_RESOURCE_USAGE = ObjC.sel("useResource:usage:");
    private static final long USE_RESOURCES_COUNT_USAGE = ObjC.sel("useResources:count:usage:");
    private static final long WAIT_FOR_FENCE = ObjC.sel("waitForFence:");
    private static final long WRITE_COMPACTED_ACCELERATION_STRUCTURE_SIZE_TO_BUFFER_OFFSET = ObjC.sel("writeCompactedAccelerationStructureSize:toBuffer:offset:");

    private static final MethodHandle PPPL = handle(null, ObjC.PTR, ObjC.PTR, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PPPPL = handle(null, ObjC.PTR, ObjC.PTR, ObjC.PTR, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PP = handle(null, ObjC.PTR, ObjC.PTR);
    private static final MethodHandle PPL = handle(null, ObjC.PTR, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle AL = handle(null, ValueLayout.ADDRESS, ObjC.LONG);
    private static final MethodHandle ALL = handle(null, ValueLayout.ADDRESS, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle PLB = handle(null, ObjC.PTR, ObjC.LONG, ObjC.BOOL);

    private MTLAccelerationStructureCommandEncoder(long id) {
        super(id);
    }

    public static MTLAccelerationStructureCommandEncoder of(long id) {
        return new MTLAccelerationStructureCommandEncoder(id);
    }

    @SneakyThrows
    public void buildAccelerationStructure(MTLAccelerationStructure structure,
            MTLAccelerationStructureDescriptor descriptor, MTLBuffer scratchBuffer, long scratchBufferOffset) {
        PPPL.invokeExact(id, BUILD_ACCELERATION_STRUCTURE_DESCRIPTOR_SCRATCH_BUFFER_SCRATCH_BUFFER_OFFSET,
                structure.getId(), descriptor.getId(), scratchBuffer.getId(), scratchBufferOffset);
    }

    @SneakyThrows
    public void refitAccelerationStructure(MTLAccelerationStructure source,
            MTLAccelerationStructureDescriptor descriptor, MTLAccelerationStructure destination,
            MTLBuffer scratchBuffer, long scratchBufferOffset) {
        PPPPL.invokeExact(id,
                REFIT_ACCELERATION_STRUCTURE_DESCRIPTOR_DESTINATION_SCRATCH_BUFFER_SCRATCH_BUFFER_OFFSET,
                source.getId(), descriptor.getId(), destination.getId(), scratchBuffer.getId(), scratchBufferOffset);
    }

    @SneakyThrows
    public void copyAccelerationStructure(MTLAccelerationStructure source, MTLAccelerationStructure destination) {
        PP.invokeExact(id, COPY_ACCELERATION_STRUCTURE_TO_ACCELERATION_STRUCTURE, source.getId(),
                destination.getId());
    }

    @SneakyThrows
    public void writeCompactedAccelerationStructureSize(MTLAccelerationStructure structure, MTLBuffer buffer,
            long offset) {
        PPL.invokeExact(id, WRITE_COMPACTED_ACCELERATION_STRUCTURE_SIZE_TO_BUFFER_OFFSET, structure.getId(),
                buffer.getId(), offset);
    }

    @SneakyThrows
    public void copyAndCompactAccelerationStructure(MTLAccelerationStructure source,
            MTLAccelerationStructure destination) {
        PP.invokeExact(id, COPY_AND_COMPACT_ACCELERATION_STRUCTURE_TO_ACCELERATION_STRUCTURE, source.getId(),
                destination.getId());
    }

    @SneakyThrows
    public void useResource(MTLResource resource, long usage) {
        PL.invokeExact(id, USE_RESOURCE_USAGE, resource.getId(), usage);
    }

    @SneakyThrows
    public void useResources(MemorySegment resources, long count, long usage) {
        ALL.invokeExact(id, USE_RESOURCES_COUNT_USAGE, resources, count, usage);
    }

    @SneakyThrows
    public void useHeap(MTLHeap heap) {
        P.invokeExact(id, USE_HEAP, heap.getId());
    }

    @SneakyThrows
    public void useHeaps(MemorySegment heaps, long count) {
        AL.invokeExact(id, USE_HEAPS_COUNT, heaps, count);
    }

    @SneakyThrows
    public void sampleCountersInBuffer(MTLCounterSampleBuffer sampleBuffer, long sampleIndex, boolean barrier) {
        PLB.invokeExact(id, SAMPLE_COUNTERS_IN_BUFFER_AT_SAMPLE_INDEX_WITH_BARRIER, sampleBuffer.getId(),
                sampleIndex, barrier);
    }

    @SneakyThrows
    public void updateFence(MTLFence fence) {
        P.invokeExact(id, UPDATE_FENCE, fence.getId());
    }

    @SneakyThrows
    public void waitForFence(MTLFence fence) {
        P.invokeExact(id, WAIT_FOR_FENCE, fence.getId());
    }
}
