package dev.dov.metalj.metal4.raytracing;

import dev.dov.metalj.metal4.MTL4BufferRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4AccelerationStructureMotionTriangleGeometryDescriptor
        extends MTL4AccelerationStructureGeometryDescriptor {
    private static final MethodHandle RANGE = handle(null, MTL4BufferRange.LAYOUT);
    private static final MethodHandle R = structHandle(MTL4BufferRange.LAYOUT);

    private MTL4AccelerationStructureMotionTriangleGeometryDescriptor(long id) {
        super(id);
    }

    public static MTL4AccelerationStructureMotionTriangleGeometryDescriptor of(long id) {
        return new MTL4AccelerationStructureMotionTriangleGeometryDescriptor(id);
    }

    public static MTL4AccelerationStructureMotionTriangleGeometryDescriptor new_() {
        return new MTL4AccelerationStructureMotionTriangleGeometryDescriptor(
                sendPtr(ObjC.cls("MTL4AccelerationStructureMotionTriangleGeometryDescriptor"), "new"));
    }

    @SneakyThrows
    public MemorySegment vertexBuffers(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, ObjC.sel("vertexBuffers"));
    }

    @SneakyThrows
    public void setVertexBuffers(MemorySegment buffers) {
        RANGE.invokeExact(id, ObjC.sel("setVertexBuffers:"), buffers);
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

    @SneakyThrows
    public MemorySegment indexBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, ObjC.sel("indexBuffer"));
    }

    @SneakyThrows
    public void setIndexBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, ObjC.sel("setIndexBuffer:"), buffer);
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

    @SneakyThrows
    public MemorySegment transformationMatrixBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, ObjC.sel("transformationMatrixBuffer"));
    }

    @SneakyThrows
    public void setTransformationMatrixBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, ObjC.sel("setTransformationMatrixBuffer:"), buffer);
    }

    public long transformationMatrixLayout() {
        return sendLong(id, "transformationMatrixLayout");
    }

    @SneakyThrows
    public void setTransformationMatrixLayout(long layout) {
        L.invokeExact(id, ObjC.sel("setTransformationMatrixLayout:"), layout);
    }
}
