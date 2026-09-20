package dev.dov.metalj.metal4.raytracing;

import dev.dov.metalj.metal4.MTL4BufferRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4AccelerationStructureBoundingBoxGeometryDescriptor
        extends MTL4AccelerationStructureGeometryDescriptor {
    private static final MethodHandle RANGE = handle(null, MTL4BufferRange.LAYOUT);
    private static final MethodHandle R = structHandle(MTL4BufferRange.LAYOUT);

    private MTL4AccelerationStructureBoundingBoxGeometryDescriptor(long id) {
        super(id);
    }

    public static MTL4AccelerationStructureBoundingBoxGeometryDescriptor of(long id) {
        return new MTL4AccelerationStructureBoundingBoxGeometryDescriptor(id);
    }

    public static MTL4AccelerationStructureBoundingBoxGeometryDescriptor new_() {
        return new MTL4AccelerationStructureBoundingBoxGeometryDescriptor(
                sendPtr(ObjC.cls("MTL4AccelerationStructureBoundingBoxGeometryDescriptor"), "new"));
    }

    @SneakyThrows
    public MemorySegment boundingBoxBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, ObjC.sel("boundingBoxBuffer"));
    }

    @SneakyThrows
    public void setBoundingBoxBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, ObjC.sel("setBoundingBoxBuffer:"), buffer);
    }

    public long boundingBoxStride() {
        return sendLong(id, "boundingBoxStride");
    }

    @SneakyThrows
    public void setBoundingBoxStride(long stride) {
        L.invokeExact(id, ObjC.sel("setBoundingBoxStride:"), stride);
    }

    public long boundingBoxCount() {
        return sendLong(id, "boundingBoxCount");
    }

    @SneakyThrows
    public void setBoundingBoxCount(long count) {
        L.invokeExact(id, ObjC.sel("setBoundingBoxCount:"), count);
    }
}
