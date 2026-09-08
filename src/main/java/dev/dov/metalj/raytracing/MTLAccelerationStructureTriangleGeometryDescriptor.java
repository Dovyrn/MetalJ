package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import lombok.SneakyThrows;

public class MTLAccelerationStructureTriangleGeometryDescriptor extends MTLAccelerationStructureGeometryDescriptor {
    private MTLAccelerationStructureTriangleGeometryDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructureTriangleGeometryDescriptor of(long id) {
        return new MTLAccelerationStructureTriangleGeometryDescriptor(id);
    }

    public static MTLAccelerationStructureTriangleGeometryDescriptor descriptor() {
        return new MTLAccelerationStructureTriangleGeometryDescriptor(
                sendPtr(ObjC.cls("MTLAccelerationStructureTriangleGeometryDescriptor"), "descriptor"));
    }

    public MTLBuffer vertexBuffer() {
        return MTLBuffer.of(sendPtr(id, "vertexBuffer"));
    }

    @SneakyThrows
    public void setVertexBuffer(MTLBuffer buffer) {
        P.invokeExact(id, ObjC.sel("setVertexBuffer:"), buffer.getId());
    }

    public long vertexBufferOffset() {
        return sendLong(id, "vertexBufferOffset");
    }

    @SneakyThrows
    public void setVertexBufferOffset(long offset) {
        L.invokeExact(id, ObjC.sel("setVertexBufferOffset:"), offset);
    }

    public long vertexFormat() {
        return sendLong(id, "vertexFormat");
    }

    @SneakyThrows
    public void setVertexFormat(long format) {
        L.invokeExact(id, ObjC.sel("setVertexFormat:"), format);
    }

    public long vertexStride() {
        return sendLong(id, "vertexStride");
    }

    @SneakyThrows
    public void setVertexStride(long stride) {
        L.invokeExact(id, ObjC.sel("setVertexStride:"), stride);
    }

    public MTLBuffer indexBuffer() {
        return MTLBuffer.of(sendPtr(id, "indexBuffer"));
    }

    @SneakyThrows
    public void setIndexBuffer(MTLBuffer buffer) {
        P.invokeExact(id, ObjC.sel("setIndexBuffer:"), buffer.getId());
    }

    public long indexBufferOffset() {
        return sendLong(id, "indexBufferOffset");
    }

    @SneakyThrows
    public void setIndexBufferOffset(long offset) {
        L.invokeExact(id, ObjC.sel("setIndexBufferOffset:"), offset);
    }

    public long indexType() {
        return sendLong(id, "indexType");
    }

    @SneakyThrows
    public void setIndexType(long type) {
        L.invokeExact(id, ObjC.sel("setIndexType:"), type);
    }

    public long triangleCount() {
        return sendLong(id, "triangleCount");
    }

    @SneakyThrows
    public void setTriangleCount(long count) {
        L.invokeExact(id, ObjC.sel("setTriangleCount:"), count);
    }

    public MTLBuffer transformationMatrixBuffer() {
        return MTLBuffer.of(sendPtr(id, "transformationMatrixBuffer"));
    }

    @SneakyThrows
    public void setTransformationMatrixBuffer(MTLBuffer buffer) {
        P.invokeExact(id, ObjC.sel("setTransformationMatrixBuffer:"), buffer.getId());
    }

    public long transformationMatrixBufferOffset() {
        return sendLong(id, "transformationMatrixBufferOffset");
    }

    @SneakyThrows
    public void setTransformationMatrixBufferOffset(long offset) {
        L.invokeExact(id, ObjC.sel("setTransformationMatrixBufferOffset:"), offset);
    }

    public long transformationMatrixLayout() {
        return sendLong(id, "transformationMatrixLayout");
    }

    @SneakyThrows
    public void setTransformationMatrixLayout(long layout) {
        L.invokeExact(id, ObjC.sel("setTransformationMatrixLayout:"), layout);
    }
}
