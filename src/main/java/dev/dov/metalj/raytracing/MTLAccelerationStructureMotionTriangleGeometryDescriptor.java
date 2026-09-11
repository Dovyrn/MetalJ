package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import lombok.SneakyThrows;

public class MTLAccelerationStructureMotionTriangleGeometryDescriptor extends MTLAccelerationStructureGeometryDescriptor {
    private MTLAccelerationStructureMotionTriangleGeometryDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructureMotionTriangleGeometryDescriptor of(long id) {
        return new MTLAccelerationStructureMotionTriangleGeometryDescriptor(id);
    }

    public static MTLAccelerationStructureMotionTriangleGeometryDescriptor descriptor() {
        return new MTLAccelerationStructureMotionTriangleGeometryDescriptor(owned(() -> sendPtr(ObjC.cls("MTLAccelerationStructureMotionTriangleGeometryDescriptor"), "descriptor")));
    }

    public NSArray vertexBuffers() {
        return NSArray.of(sendPtr(id, "vertexBuffers"));
    }

    @SneakyThrows
    public void setVertexBuffers(NSArray vertexBuffers) {
        P.invokeExact(id, ObjC.sel("setVertexBuffers:"), vertexBuffers.getId());
    }

    public long vertexFormat() {
        return sendLong(id, "vertexFormat");
    }

    @SneakyThrows
    public void setVertexFormat(long vertexFormat) {
        L.invokeExact(id, ObjC.sel("setVertexFormat:"), vertexFormat);
    }

    public long vertexStride() {
        return sendLong(id, "vertexStride");
    }

    @SneakyThrows
    public void setVertexStride(long vertexStride) {
        L.invokeExact(id, ObjC.sel("setVertexStride:"), vertexStride);
    }

    public MTLBuffer indexBuffer() {
        return MTLBuffer.of(sendPtr(id, "indexBuffer"));
    }

    @SneakyThrows
    public void setIndexBuffer(MTLBuffer indexBuffer) {
        P.invokeExact(id, ObjC.sel("setIndexBuffer:"), indexBuffer.getId());
    }

    public long indexBufferOffset() {
        return sendLong(id, "indexBufferOffset");
    }

    @SneakyThrows
    public void setIndexBufferOffset(long indexBufferOffset) {
        L.invokeExact(id, ObjC.sel("setIndexBufferOffset:"), indexBufferOffset);
    }

    public long indexType() {
        return sendLong(id, "indexType");
    }

    @SneakyThrows
    public void setIndexType(long indexType) {
        L.invokeExact(id, ObjC.sel("setIndexType:"), indexType);
    }

    public long triangleCount() {
        return sendLong(id, "triangleCount");
    }

    @SneakyThrows
    public void setTriangleCount(long triangleCount) {
        L.invokeExact(id, ObjC.sel("setTriangleCount:"), triangleCount);
    }

    public MTLBuffer transformationMatrixBuffer() {
        return MTLBuffer.of(sendPtr(id, "transformationMatrixBuffer"));
    }

    @SneakyThrows
    public void setTransformationMatrixBuffer(MTLBuffer transformationMatrixBuffer) {
        P.invokeExact(id, ObjC.sel("setTransformationMatrixBuffer:"), transformationMatrixBuffer.getId());
    }

    public long transformationMatrixBufferOffset() {
        return sendLong(id, "transformationMatrixBufferOffset");
    }

    @SneakyThrows
    public void setTransformationMatrixBufferOffset(long transformationMatrixBufferOffset) {
        L.invokeExact(id, ObjC.sel("setTransformationMatrixBufferOffset:"), transformationMatrixBufferOffset);
    }

    public long transformationMatrixLayout() {
        return sendLong(id, "transformationMatrixLayout");
    }

    @SneakyThrows
    public void setTransformationMatrixLayout(long transformationMatrixLayout) {
        L.invokeExact(id, ObjC.sel("setTransformationMatrixLayout:"), transformationMatrixLayout);
    }
}
