package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import lombok.SneakyThrows;

public class MTLAccelerationStructureBoundingBoxGeometryDescriptor
        extends MTLAccelerationStructureGeometryDescriptor {
    private static final long MTL_ACCELERATION_STRUCTURE_BOUNDING_BOX_GEOMETRY_DESCRIPTOR = ObjC.cls("MTLAccelerationStructureBoundingBoxGeometryDescriptor");

    private static final long BOUNDING_BOX_BUFFER = ObjC.sel("boundingBoxBuffer");
    private static final long BOUNDING_BOX_BUFFER_OFFSET = ObjC.sel("boundingBoxBufferOffset");
    private static final long BOUNDING_BOX_COUNT = ObjC.sel("boundingBoxCount");
    private static final long BOUNDING_BOX_STRIDE = ObjC.sel("boundingBoxStride");
    private static final long DESCRIPTOR = ObjC.sel("descriptor");
    private static final long SET_BOUNDING_BOX_BUFFER = ObjC.sel("setBoundingBoxBuffer:");
    private static final long SET_BOUNDING_BOX_BUFFER_OFFSET = ObjC.sel("setBoundingBoxBufferOffset:");
    private static final long SET_BOUNDING_BOX_COUNT = ObjC.sel("setBoundingBoxCount:");
    private static final long SET_BOUNDING_BOX_STRIDE = ObjC.sel("setBoundingBoxStride:");

    private MTLAccelerationStructureBoundingBoxGeometryDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructureBoundingBoxGeometryDescriptor of(long id) {
        return new MTLAccelerationStructureBoundingBoxGeometryDescriptor(id);
    }

    public static MTLAccelerationStructureBoundingBoxGeometryDescriptor descriptor() {
        return new MTLAccelerationStructureBoundingBoxGeometryDescriptor(
                owned(() -> sendPtr(MTL_ACCELERATION_STRUCTURE_BOUNDING_BOX_GEOMETRY_DESCRIPTOR, DESCRIPTOR)));
    }

    public MTLBuffer boundingBoxBuffer() {
        return MTLBuffer.of(sendPtr(id, BOUNDING_BOX_BUFFER));
    }

    @SneakyThrows
    public void setBoundingBoxBuffer(MTLBuffer buffer) {
        P.invokeExact(id, SET_BOUNDING_BOX_BUFFER, buffer.getId());
    }

    public long boundingBoxBufferOffset() {
        return sendLong(id, BOUNDING_BOX_BUFFER_OFFSET);
    }

    @SneakyThrows
    public void setBoundingBoxBufferOffset(long offset) {
        L.invokeExact(id, SET_BOUNDING_BOX_BUFFER_OFFSET, offset);
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
