package dev.dov.metalj.metal4.raytracing;

import dev.dov.metalj.metal4.MTL4BufferRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4AccelerationStructureMotionCurveGeometryDescriptor
        extends MTL4AccelerationStructureGeometryDescriptor {
    private static final MethodHandle RANGE = handle(null, MTL4BufferRange.LAYOUT);
    private static final MethodHandle R = structHandle(MTL4BufferRange.LAYOUT);

    private MTL4AccelerationStructureMotionCurveGeometryDescriptor(long id) {
        super(id);
    }

    public static MTL4AccelerationStructureMotionCurveGeometryDescriptor of(long id) {
        return new MTL4AccelerationStructureMotionCurveGeometryDescriptor(id);
    }

    public static MTL4AccelerationStructureMotionCurveGeometryDescriptor new_() {
        return new MTL4AccelerationStructureMotionCurveGeometryDescriptor(
                sendPtr(ObjC.cls("MTL4AccelerationStructureMotionCurveGeometryDescriptor"), "new"));
    }

    @SneakyThrows
    public MemorySegment controlPointBuffers(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, ObjC.sel("controlPointBuffers"));
    }

    @SneakyThrows
    public void setControlPointBuffers(MemorySegment buffers) {
        RANGE.invokeExact(id, ObjC.sel("setControlPointBuffers:"), buffers);
    }

    public long controlPointCount() {
        return sendLong(id, "controlPointCount");
    }

    @SneakyThrows
    public void setControlPointCount(long count) {
        L.invokeExact(id, ObjC.sel("setControlPointCount:"), count);
    }

    public long controlPointStride() {
        return sendLong(id, "controlPointStride");
    }

    @SneakyThrows
    public void setControlPointStride(long stride) {
        L.invokeExact(id, ObjC.sel("setControlPointStride:"), stride);
    }

    public long controlPointFormat() {
        return sendLong(id, "controlPointFormat");
    }

    @SneakyThrows
    public void setControlPointFormat(long format) {
        L.invokeExact(id, ObjC.sel("setControlPointFormat:"), format);
    }

    @SneakyThrows
    public MemorySegment radiusBuffers(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, ObjC.sel("radiusBuffers"));
    }

    @SneakyThrows
    public void setRadiusBuffers(MemorySegment buffers) {
        RANGE.invokeExact(id, ObjC.sel("setRadiusBuffers:"), buffers);
    }

    public long radiusFormat() {
        return sendLong(id, "radiusFormat");
    }

    @SneakyThrows
    public void setRadiusFormat(long format) {
        L.invokeExact(id, ObjC.sel("setRadiusFormat:"), format);
    }

    public long radiusStride() {
        return sendLong(id, "radiusStride");
    }

    @SneakyThrows
    public void setRadiusStride(long stride) {
        L.invokeExact(id, ObjC.sel("setRadiusStride:"), stride);
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

    public long segmentCount() {
        return sendLong(id, "segmentCount");
    }

    @SneakyThrows
    public void setSegmentCount(long count) {
        L.invokeExact(id, ObjC.sel("setSegmentCount:"), count);
    }

    public long segmentControlPointCount() {
        return sendLong(id, "segmentControlPointCount");
    }

    @SneakyThrows
    public void setSegmentControlPointCount(long count) {
        L.invokeExact(id, ObjC.sel("setSegmentControlPointCount:"), count);
    }

    public long curveType() {
        return sendLong(id, "curveType");
    }

    @SneakyThrows
    public void setCurveType(long type) {
        L.invokeExact(id, ObjC.sel("setCurveType:"), type);
    }

    public long curveBasis() {
        return sendLong(id, "curveBasis");
    }

    @SneakyThrows
    public void setCurveBasis(long basis) {
        L.invokeExact(id, ObjC.sel("setCurveBasis:"), basis);
    }

    public long curveEndCaps() {
        return sendLong(id, "curveEndCaps");
    }

    @SneakyThrows
    public void setCurveEndCaps(long caps) {
        L.invokeExact(id, ObjC.sel("setCurveEndCaps:"), caps);
    }
}
