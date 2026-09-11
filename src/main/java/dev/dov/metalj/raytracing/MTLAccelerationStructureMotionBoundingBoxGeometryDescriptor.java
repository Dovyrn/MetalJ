package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.objc.NSArray;
import lombok.SneakyThrows;

public class MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor extends MTLAccelerationStructureGeometryDescriptor {
    private MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor of(long id) {
        return new MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor(id);
    }

    public static MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor descriptor() {
        return new MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor(owned(() -> sendPtr(ObjC.cls("MTLAccelerationStructureMotionBoundingBoxGeometryDescriptor"), "descriptor")));
    }

    public NSArray boundingBoxBuffers() {
        return NSArray.of(sendPtr(id, "boundingBoxBuffers"));
    }

    @SneakyThrows
    public void setBoundingBoxBuffers(NSArray boundingBoxBuffers) {
        P.invokeExact(id, ObjC.sel("setBoundingBoxBuffers:"), boundingBoxBuffers.getId());
    }

    public long boundingBoxStride() {
        return sendLong(id, "boundingBoxStride");
    }

    @SneakyThrows
    public void setBoundingBoxStride(long boundingBoxStride) {
        L.invokeExact(id, ObjC.sel("setBoundingBoxStride:"), boundingBoxStride);
    }

    public long boundingBoxCount() {
        return sendLong(id, "boundingBoxCount");
    }

    @SneakyThrows
    public void setBoundingBoxCount(long boundingBoxCount) {
        L.invokeExact(id, ObjC.sel("setBoundingBoxCount:"), boundingBoxCount);
    }
}
