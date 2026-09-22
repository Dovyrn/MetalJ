package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import lombok.SneakyThrows;

public class MTLAccelerationStructureTriangleGeometryDescriptor extends MTLAccelerationStructureGeometryDescriptor {
    private static final long MTL_ACCELERATION_STRUCTURE_TRIANGLE_GEOMETRY_DESCRIPTOR = ObjC.cls("MTLAccelerationStructureTriangleGeometryDescriptor");

    private static final long DESCRIPTOR = ObjC.sel("descriptor");
    private static final long INDEX_BUFFER = ObjC.sel("indexBuffer");
    private static final long INDEX_BUFFER_OFFSET = ObjC.sel("indexBufferOffset");
    private static final long INDEX_TYPE = ObjC.sel("indexType");
    private static final long SET_INDEX_BUFFER = ObjC.sel("setIndexBuffer:");
    private static final long SET_INDEX_BUFFER_OFFSET = ObjC.sel("setIndexBufferOffset:");
    private static final long SET_INDEX_TYPE = ObjC.sel("setIndexType:");
    private static final long SET_TRANSFORMATION_MATRIX_BUFFER = ObjC.sel("setTransformationMatrixBuffer:");
    private static final long SET_TRANSFORMATION_MATRIX_BUFFER_OFFSET = ObjC.sel("setTransformationMatrixBufferOffset:");
    private static final long SET_TRANSFORMATION_MATRIX_LAYOUT = ObjC.sel("setTransformationMatrixLayout:");
    private static final long SET_TRIANGLE_COUNT = ObjC.sel("setTriangleCount:");
    private static final long SET_VERTEX_BUFFER = ObjC.sel("setVertexBuffer:");
    private static final long SET_VERTEX_BUFFER_OFFSET = ObjC.sel("setVertexBufferOffset:");
    private static final long SET_VERTEX_FORMAT = ObjC.sel("setVertexFormat:");
    private static final long SET_VERTEX_STRIDE = ObjC.sel("setVertexStride:");
    private static final long TRANSFORMATION_MATRIX_BUFFER = ObjC.sel("transformationMatrixBuffer");
    private static final long TRANSFORMATION_MATRIX_BUFFER_OFFSET = ObjC.sel("transformationMatrixBufferOffset");
    private static final long TRANSFORMATION_MATRIX_LAYOUT = ObjC.sel("transformationMatrixLayout");
    private static final long TRIANGLE_COUNT = ObjC.sel("triangleCount");
    private static final long VERTEX_BUFFER = ObjC.sel("vertexBuffer");
    private static final long VERTEX_BUFFER_OFFSET = ObjC.sel("vertexBufferOffset");
    private static final long VERTEX_FORMAT = ObjC.sel("vertexFormat");
    private static final long VERTEX_STRIDE = ObjC.sel("vertexStride");

    private MTLAccelerationStructureTriangleGeometryDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructureTriangleGeometryDescriptor of(long id) {
        return new MTLAccelerationStructureTriangleGeometryDescriptor(id);
    }

    public static MTLAccelerationStructureTriangleGeometryDescriptor descriptor() {
        return new MTLAccelerationStructureTriangleGeometryDescriptor(
                owned(() -> sendPtr(MTL_ACCELERATION_STRUCTURE_TRIANGLE_GEOMETRY_DESCRIPTOR, DESCRIPTOR)));
    }

    public MTLBuffer vertexBuffer() {
        return MTLBuffer.of(sendPtr(id, VERTEX_BUFFER));
    }

    @SneakyThrows
    public void setVertexBuffer(MTLBuffer buffer) {
        P.invokeExact(id, SET_VERTEX_BUFFER, buffer.getId());
    }

    public long vertexBufferOffset() {
        return sendLong(id, VERTEX_BUFFER_OFFSET);
    }

    @SneakyThrows
    public void setVertexBufferOffset(long offset) {
        L.invokeExact(id, SET_VERTEX_BUFFER_OFFSET, offset);
    }

    public long vertexFormat() {
        return sendLong(id, VERTEX_FORMAT);
    }

    @SneakyThrows
    public void setVertexFormat(long format) {
        L.invokeExact(id, SET_VERTEX_FORMAT, format);
    }

    public long vertexStride() {
        return sendLong(id, VERTEX_STRIDE);
    }

    @SneakyThrows
    public void setVertexStride(long stride) {
        L.invokeExact(id, SET_VERTEX_STRIDE, stride);
    }

    public MTLBuffer indexBuffer() {
        return MTLBuffer.of(sendPtr(id, INDEX_BUFFER));
    }

    @SneakyThrows
    public void setIndexBuffer(MTLBuffer buffer) {
        P.invokeExact(id, SET_INDEX_BUFFER, buffer.getId());
    }

    public long indexBufferOffset() {
        return sendLong(id, INDEX_BUFFER_OFFSET);
    }

    @SneakyThrows
    public void setIndexBufferOffset(long offset) {
        L.invokeExact(id, SET_INDEX_BUFFER_OFFSET, offset);
    }

    public long indexType() {
        return sendLong(id, INDEX_TYPE);
    }

    @SneakyThrows
    public void setIndexType(long type) {
        L.invokeExact(id, SET_INDEX_TYPE, type);
    }

    public long triangleCount() {
        return sendLong(id, TRIANGLE_COUNT);
    }

    @SneakyThrows
    public void setTriangleCount(long count) {
        L.invokeExact(id, SET_TRIANGLE_COUNT, count);
    }

    public MTLBuffer transformationMatrixBuffer() {
        return MTLBuffer.of(sendPtr(id, TRANSFORMATION_MATRIX_BUFFER));
    }

    @SneakyThrows
    public void setTransformationMatrixBuffer(MTLBuffer buffer) {
        P.invokeExact(id, SET_TRANSFORMATION_MATRIX_BUFFER, buffer.getId());
    }

    public long transformationMatrixBufferOffset() {
        return sendLong(id, TRANSFORMATION_MATRIX_BUFFER_OFFSET);
    }

    @SneakyThrows
    public void setTransformationMatrixBufferOffset(long offset) {
        L.invokeExact(id, SET_TRANSFORMATION_MATRIX_BUFFER_OFFSET, offset);
    }

    public long transformationMatrixLayout() {
        return sendLong(id, TRANSFORMATION_MATRIX_LAYOUT);
    }

    @SneakyThrows
    public void setTransformationMatrixLayout(long layout) {
        L.invokeExact(id, SET_TRANSFORMATION_MATRIX_LAYOUT, layout);
    }
}
