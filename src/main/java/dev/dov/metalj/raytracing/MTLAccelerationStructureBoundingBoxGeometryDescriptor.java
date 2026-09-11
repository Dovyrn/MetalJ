package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import lombok.SneakyThrows;

public class MTLAccelerationStructureBoundingBoxGeometryDescriptor
        extends MTLAccelerationStructureGeometryDescriptor {
    private MTLAccelerationStructureBoundingBoxGeometryDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructureBoundingBoxGeometryDescriptor of(long id) {
        return new MTLAccelerationStructureBoundingBoxGeometryDescriptor(id);
    }

    public static MTLAccelerationStructureBoundingBoxGeometryDescriptor descriptor() {
        return new MTLAccelerationStructureBoundingBoxGeometryDescriptor(
                owned(() -> sendPtr(ObjC.cls("MTLAccelerationStructureBoundingBoxGeometryDescriptor"), "descriptor")));
    }

    public MTLBuffer boundingBoxBuffer() {
        return MTLBuffer.of(sendPtr(id, "boundingBoxBuffer"));
    }

    @SneakyThrows
    public void setBoundingBoxBuffer(MTLBuffer buffer) {
        P.invokeExact(id, ObjC.sel("setBoundingBoxBuffer:"), buffer.getId());
    }

    public long boundingBoxBufferOffset() {
        return sendLong(id, "boundingBoxBufferOffset");
    }

    @SneakyThrows
    public void setBoundingBoxBufferOffset(long offset) {
        L.invokeExact(id, ObjC.sel("setBoundingBoxBufferOffset:"), offset);
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
