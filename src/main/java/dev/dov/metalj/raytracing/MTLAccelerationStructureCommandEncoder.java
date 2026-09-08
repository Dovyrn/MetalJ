package dev.dov.metalj.raytracing;

import dev.dov.metalj.commands.encoders.MTLCommandEncoder;
import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLResource;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.heaps.MTLHeap;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLAccelerationStructureCommandEncoder extends MTLCommandEncoder {
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
        PPPL.invokeExact(id, ObjC.sel("buildAccelerationStructure:descriptor:scratchBuffer:scratchBufferOffset:"),
                structure.getId(), descriptor.getId(), scratchBuffer.getId(), scratchBufferOffset);
    }

    @SneakyThrows
    public void refitAccelerationStructure(MTLAccelerationStructure source,
            MTLAccelerationStructureDescriptor descriptor, MTLAccelerationStructure destination,
            MTLBuffer scratchBuffer, long scratchBufferOffset) {
        PPPPL.invokeExact(id,
                ObjC.sel("refitAccelerationStructure:descriptor:destination:scratchBuffer:scratchBufferOffset:"),
                source.getId(), descriptor.getId(), destination.getId(), scratchBuffer.getId(), scratchBufferOffset);
    }

    @SneakyThrows
    public void copyAccelerationStructure(MTLAccelerationStructure source, MTLAccelerationStructure destination) {
        PP.invokeExact(id, ObjC.sel("copyAccelerationStructure:toAccelerationStructure:"), source.getId(),
                destination.getId());
    }

    @SneakyThrows
    public void writeCompactedAccelerationStructureSize(MTLAccelerationStructure structure, MTLBuffer buffer,
            long offset) {
        PPL.invokeExact(id, ObjC.sel("writeCompactedAccelerationStructureSize:toBuffer:offset:"), structure.getId(),
                buffer.getId(), offset);
    }

    @SneakyThrows
    public void copyAndCompactAccelerationStructure(MTLAccelerationStructure source,
            MTLAccelerationStructure destination) {
        PP.invokeExact(id, ObjC.sel("copyAndCompactAccelerationStructure:toAccelerationStructure:"), source.getId(),
                destination.getId());
    }

    @SneakyThrows
    public void useResource(MTLResource resource, long usage) {
        PL.invokeExact(id, ObjC.sel("useResource:usage:"), resource.getId(), usage);
    }

    @SneakyThrows
    public void useResources(MemorySegment resources, long count, long usage) {
        ALL.invokeExact(id, ObjC.sel("useResources:count:usage:"), resources, count, usage);
    }

    @SneakyThrows
    public void useHeap(MTLHeap heap) {
        P.invokeExact(id, ObjC.sel("useHeap:"), heap.getId());
    }

    @SneakyThrows
    public void useHeaps(MemorySegment heaps, long count) {
        AL.invokeExact(id, ObjC.sel("useHeaps:count:"), heaps, count);
    }

    @SneakyThrows
    public void sampleCountersInBuffer(MTLCounterSampleBuffer sampleBuffer, long sampleIndex, boolean barrier) {
        PLB.invokeExact(id, ObjC.sel("sampleCountersInBuffer:atSampleIndex:withBarrier:"), sampleBuffer.getId(),
                sampleIndex, barrier);
    }
}
