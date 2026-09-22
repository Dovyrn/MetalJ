package dev.dov.metalj.metal4.raytracing;

import dev.dov.metalj.metal4.MTL4BufferRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4AccelerationStructureTriangleGeometryDescriptor extends MTL4AccelerationStructureGeometryDescriptor {
    private static final long MTL_4_ACCELERATION_STRUCTURE_TRIANGLE_GEOMETRY_DESCRIPTOR = ObjC.cls("MTL4AccelerationStructureTriangleGeometryDescriptor");

    private static final long INDEX_BUFFER = ObjC.sel("indexBuffer");
    private static final long INDEX_TYPE = ObjC.sel("indexType");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_INDEX_BUFFER = ObjC.sel("setIndexBuffer:");
    private static final long SET_INDEX_TYPE = ObjC.sel("setIndexType:");
    private static final long SET_TRANSFORMATION_MATRIX_BUFFER = ObjC.sel("setTransformationMatrixBuffer:");
    private static final long SET_TRANSFORMATION_MATRIX_LAYOUT = ObjC.sel("setTransformationMatrixLayout:");
    private static final long SET_TRIANGLE_COUNT = ObjC.sel("setTriangleCount:");
    private static final long SET_VERTEX_BUFFER = ObjC.sel("setVertexBuffer:");
    private static final long SET_VERTEX_FORMAT = ObjC.sel("setVertexFormat:");
    private static final long SET_VERTEX_STRIDE = ObjC.sel("setVertexStride:");
    private static final long TRANSFORMATION_MATRIX_BUFFER = ObjC.sel("transformationMatrixBuffer");
    private static final long TRANSFORMATION_MATRIX_LAYOUT = ObjC.sel("transformationMatrixLayout");
    private static final long TRIANGLE_COUNT = ObjC.sel("triangleCount");
    private static final long VERTEX_BUFFER = ObjC.sel("vertexBuffer");
    private static final long VERTEX_FORMAT = ObjC.sel("vertexFormat");
    private static final long VERTEX_STRIDE = ObjC.sel("vertexStride");

    private static final MethodHandle RANGE = handle(null, MTL4BufferRange.LAYOUT);
    private static final MethodHandle R = structHandle(MTL4BufferRange.LAYOUT);

    private MTL4AccelerationStructureTriangleGeometryDescriptor(long id) {
        super(id);
    }

    public static MTL4AccelerationStructureTriangleGeometryDescriptor of(long id) {
        return new MTL4AccelerationStructureTriangleGeometryDescriptor(id);
    }

    public static MTL4AccelerationStructureTriangleGeometryDescriptor new_() {
        return new MTL4AccelerationStructureTriangleGeometryDescriptor(
                sendPtr(MTL_4_ACCELERATION_STRUCTURE_TRIANGLE_GEOMETRY_DESCRIPTOR, NEW));
    }

    @SneakyThrows
    public MemorySegment vertexBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, VERTEX_BUFFER);
    }

    @SneakyThrows
    public void setVertexBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, SET_VERTEX_BUFFER, buffer);
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

    @SneakyThrows
    public MemorySegment indexBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, INDEX_BUFFER);
    }

    @SneakyThrows
    public void setIndexBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, SET_INDEX_BUFFER, buffer);
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

    @SneakyThrows
    public MemorySegment transformationMatrixBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, TRANSFORMATION_MATRIX_BUFFER);
    }

    @SneakyThrows
    public void setTransformationMatrixBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, SET_TRANSFORMATION_MATRIX_BUFFER, buffer);
    }

    public long transformationMatrixLayout() {
        return sendLong(id, TRANSFORMATION_MATRIX_LAYOUT);
    }

    @SneakyThrows
    public void setTransformationMatrixLayout(long layout) {
        L.invokeExact(id, SET_TRANSFORMATION_MATRIX_LAYOUT, layout);
    }
}
