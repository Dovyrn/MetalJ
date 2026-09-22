package dev.dov.metalj.metal4.raytracing;

import dev.dov.metalj.metal4.MTL4BufferRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4AccelerationStructureMotionBoundingBoxGeometryDescriptor
        extends MTL4AccelerationStructureGeometryDescriptor {
    private static final long MTL_4_ACCELERATION_STRUCTURE_MOTION_BOUNDING_BOX_GEOMETRY_DESCRIPTOR = ObjC.cls("MTL4AccelerationStructureMotionBoundingBoxGeometryDescriptor");

    private static final long BOUNDING_BOX_BUFFERS = ObjC.sel("boundingBoxBuffers");
    private static final long BOUNDING_BOX_COUNT = ObjC.sel("boundingBoxCount");
    private static final long BOUNDING_BOX_STRIDE = ObjC.sel("boundingBoxStride");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_BOUNDING_BOX_BUFFERS = ObjC.sel("setBoundingBoxBuffers:");
    private static final long SET_BOUNDING_BOX_COUNT = ObjC.sel("setBoundingBoxCount:");
    private static final long SET_BOUNDING_BOX_STRIDE = ObjC.sel("setBoundingBoxStride:");

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
                sendPtr(MTL_4_ACCELERATION_STRUCTURE_MOTION_BOUNDING_BOX_GEOMETRY_DESCRIPTOR, NEW));
    }

    @SneakyThrows
    public MemorySegment boundingBoxBuffers(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, BOUNDING_BOX_BUFFERS);
    }

    @SneakyThrows
    public void setBoundingBoxBuffers(MemorySegment buffers) {
        RANGE.invokeExact(id, SET_BOUNDING_BOX_BUFFERS, buffers);
    }

    public long boundingBoxStride() {
        return sendLong(id, BOUNDING_BOX_STRIDE);
    }

    @SneakyThrows
    public void setBoundingBoxStride(long stride) {
        L.invokeExact(id, SET_BOUNDING_BOX_STRIDE, stride);
    }

    public long boundingBoxCount() {
        return sendLong(id, BOUNDING_BOX_COUNT);
    }

    @SneakyThrows
    public void setBoundingBoxCount(long count) {
        L.invokeExact(id, SET_BOUNDING_BOX_COUNT, count);
    }
}
