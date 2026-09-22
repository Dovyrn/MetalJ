package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import lombok.SneakyThrows;

public class MTLAccelerationStructureMotionTriangleGeometryDescriptor extends MTLAccelerationStructureGeometryDescriptor {
    private static final long MTL_ACCELERATION_STRUCTURE_MOTION_TRIANGLE_GEOMETRY_DESCRIPTOR = ObjC.cls("MTLAccelerationStructureMotionTriangleGeometryDescriptor");

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
    private static final long SET_VERTEX_BUFFERS = ObjC.sel("setVertexBuffers:");
    private static final long SET_VERTEX_FORMAT = ObjC.sel("setVertexFormat:");
    private static final long SET_VERTEX_STRIDE = ObjC.sel("setVertexStride:");
    private static final long TRANSFORMATION_MATRIX_BUFFER = ObjC.sel("transformationMatrixBuffer");
    private static final long TRANSFORMATION_MATRIX_BUFFER_OFFSET = ObjC.sel("transformationMatrixBufferOffset");
    private static final long TRANSFORMATION_MATRIX_LAYOUT = ObjC.sel("transformationMatrixLayout");
    private static final long TRIANGLE_COUNT = ObjC.sel("triangleCount");
    private static final long VERTEX_BUFFERS = ObjC.sel("vertexBuffers");
    private static final long VERTEX_FORMAT = ObjC.sel("vertexFormat");
    private static final long VERTEX_STRIDE = ObjC.sel("vertexStride");

    private MTLAccelerationStructureMotionTriangleGeometryDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructureMotionTriangleGeometryDescriptor of(long id) {
        return new MTLAccelerationStructureMotionTriangleGeometryDescriptor(id);
    }

    public static MTLAccelerationStructureMotionTriangleGeometryDescriptor descriptor() {
        return new MTLAccelerationStructureMotionTriangleGeometryDescriptor(owned(() -> sendPtr(MTL_ACCELERATION_STRUCTURE_MOTION_TRIANGLE_GEOMETRY_DESCRIPTOR, DESCRIPTOR)));
    }

    public NSArray vertexBuffers() {
        return NSArray.of(sendPtr(id, VERTEX_BUFFERS));
    }

    @SneakyThrows
    public void setVertexBuffers(NSArray vertexBuffers) {
        P.invokeExact(id, SET_VERTEX_BUFFERS, vertexBuffers.getId());
    }

    public long vertexFormat() {
        return sendLong(id, VERTEX_FORMAT);
    }

    @SneakyThrows
    public void setVertexFormat(long vertexFormat) {
        L.invokeExact(id, SET_VERTEX_FORMAT, vertexFormat);
    }

    public long vertexStride() {
        return sendLong(id, VERTEX_STRIDE);
    }

    @SneakyThrows
    public void setVertexStride(long vertexStride) {
        L.invokeExact(id, SET_VERTEX_STRIDE, vertexStride);
    }

    public MTLBuffer indexBuffer() {
        return MTLBuffer.of(sendPtr(id, INDEX_BUFFER));
    }

    @SneakyThrows
    public void setIndexBuffer(MTLBuffer indexBuffer) {
        P.invokeExact(id, SET_INDEX_BUFFER, indexBuffer.getId());
    }

    public long indexBufferOffset() {
        return sendLong(id, INDEX_BUFFER_OFFSET);
    }

    @SneakyThrows
    public void setIndexBufferOffset(long indexBufferOffset) {
        L.invokeExact(id, SET_INDEX_BUFFER_OFFSET, indexBufferOffset);
    }

    public long indexType() {
        return sendLong(id, INDEX_TYPE);
    }

    @SneakyThrows
    public void setIndexType(long indexType) {
        L.invokeExact(id, SET_INDEX_TYPE, indexType);
    }

    public long triangleCount() {
        return sendLong(id, TRIANGLE_COUNT);
    }

    @SneakyThrows
    public void setTriangleCount(long triangleCount) {
        L.invokeExact(id, SET_TRIANGLE_COUNT, triangleCount);
    }

    public MTLBuffer transformationMatrixBuffer() {
        return MTLBuffer.of(sendPtr(id, TRANSFORMATION_MATRIX_BUFFER));
    }

    @SneakyThrows
    public void setTransformationMatrixBuffer(MTLBuffer transformationMatrixBuffer) {
        P.invokeExact(id, SET_TRANSFORMATION_MATRIX_BUFFER, transformationMatrixBuffer.getId());
    }

    public long transformationMatrixBufferOffset() {
        return sendLong(id, TRANSFORMATION_MATRIX_BUFFER_OFFSET);
    }

    @SneakyThrows
    public void setTransformationMatrixBufferOffset(long transformationMatrixBufferOffset) {
        L.invokeExact(id, SET_TRANSFORMATION_MATRIX_BUFFER_OFFSET, transformationMatrixBufferOffset);
    }

    public long transformationMatrixLayout() {
        return sendLong(id, TRANSFORMATION_MATRIX_LAYOUT);
    }

    @SneakyThrows
    public void setTransformationMatrixLayout(long transformationMatrixLayout) {
        L.invokeExact(id, SET_TRANSFORMATION_MATRIX_LAYOUT, transformationMatrixLayout);
    }
}
