package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.objc.NSArray;
import lombok.SneakyThrows;

public class MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor extends MTLAccelerationStructureGeometryDescriptor {
    private static final long MTL_ACCELERATION_STRUCTURE_MOTION_BOUNDING_BOX_GEOMETRY_DESCRIPTOR = ObjC.cls("MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor");

    private static final long BOUNDING_BOX_BUFFERS = ObjC.sel("boundingBoxBuffers");
    private static final long BOUNDING_BOX_COUNT = ObjC.sel("boundingBoxCount");
    private static final long BOUNDING_BOX_STRIDE = ObjC.sel("boundingBoxStride");
    private static final long DESCRIPTOR = ObjC.sel("descriptor");
    private static final long SET_BOUNDING_BOX_BUFFERS = ObjC.sel("setBoundingBoxBuffers:");
    private static final long SET_BOUNDING_BOX_COUNT = ObjC.sel("setBoundingBoxCount:");
    private static final long SET_BOUNDING_BOX_STRIDE = ObjC.sel("setBoundingBoxStride:");

    private MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor of(long id) {
        return new MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor(id);
    }

    public static MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor descriptor() {
        return new MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor(owned(() -> sendPtr(MTL_ACCELERATION_STRUCTURE_MOTION_BOUNDING_BOX_GEOMETRY_DESCRIPTOR, DESCRIPTOR)));
    }

    public NSArray boundingBoxBuffers() {
        return NSArray.of(sendPtr(id, BOUNDING_BOX_BUFFERS));
    }

    @SneakyThrows
    public void setBoundingBoxBuffers(NSArray boundingBoxBuffers) {
        P.invokeExact(id, SET_BOUNDING_BOX_BUFFERS, boundingBoxBuffers.getId());
    }

    public long boundingBoxStride() {
        return sendLong(id, BOUNDING_BOX_STRIDE);
    }

    @SneakyThrows
    public void setBoundingBoxStride(long boundingBoxStride) {
        L.invokeExact(id, SET_BOUNDING_BOX_STRIDE, boundingBoxStride);
    }

    public long boundingBoxCount() {
        return sendLong(id, BOUNDING_BOX_COUNT);
    }

    @SneakyThrows
    public void setBoundingBoxCount(long boundingBoxCount) {
        L.invokeExact(id, SET_BOUNDING_BOX_COUNT, boundingBoxCount);
    }
}
