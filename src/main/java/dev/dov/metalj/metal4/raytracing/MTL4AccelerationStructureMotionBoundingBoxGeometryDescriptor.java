package dev.dov.metalj.metal4.raytracing;

import dev.dov.metalj.metal4.MTL4BufferRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4AccelerationStructureMotionBoundingBoxGeometryDescriptor
        extends MTL4AccelerationStructureGeometryDescriptor {
    private static final MethodHandle RANGE = handle(null, MTL4BufferRange.LAYOUT);
    private static final MethodHandle R = structHandle(MTL4BufferRange.LAYOUT);

    private MTL4AccelerationStructureMotionBoundingBoxGeometryDescriptor(long id) {
        super(id);
    }

    public static MTL4AccelerationStructureMotionBoundingBoxGeometryDescriptor of(long id) {
        return new MTL4AccelerationStructureMotionBoundingBoxGeometryDescriptor(id);
    }

    public static MTL4AccelerationStructureMotionBoundingBoxGeometryDescriptor new_() {
        return new MTL4AccelerationStructureMotionBoundingBoxGeometryDescriptor(
                sendPtr(ObjC.cls("MTL4AccelerationStructureMotionBoundingBoxGeometryDescriptor"), "new"));
    }

    @SneakyThrows
    public MemorySegment boundingBoxBuffers(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, ObjC.sel("boundingBoxBuffers"));
    }

    @SneakyThrows
    public void setBoundingBoxBuffers(MemorySegment buffers) {
        RANGE.invokeExact(id, ObjC.sel("setBoundingBoxBuffers:"), buffers);
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
