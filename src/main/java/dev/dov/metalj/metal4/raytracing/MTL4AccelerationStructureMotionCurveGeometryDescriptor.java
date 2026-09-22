package dev.dov.metalj.metal4.raytracing;

import dev.dov.metalj.metal4.MTL4BufferRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4AccelerationStructureMotionCurveGeometryDescriptor
        extends MTL4AccelerationStructureGeometryDescriptor {
    private static final long MTL_4_ACCELERATION_STRUCTURE_MOTION_CURVE_GEOMETRY_DESCRIPTOR = ObjC.cls("MTL4AccelerationStructureMotionCurveGeometryDescriptor");

    private static final long CONTROL_POINT_BUFFERS = ObjC.sel("controlPointBuffers");
    private static final long CONTROL_POINT_COUNT = ObjC.sel("controlPointCount");
    private static final long CONTROL_POINT_FORMAT = ObjC.sel("controlPointFormat");
    private static final long CONTROL_POINT_STRIDE = ObjC.sel("controlPointStride");
    private static final long CURVE_BASIS = ObjC.sel("curveBasis");
    private static final long CURVE_END_CAPS = ObjC.sel("curveEndCaps");
    private static final long CURVE_TYPE = ObjC.sel("curveType");
    private static final long INDEX_BUFFER = ObjC.sel("indexBuffer");
    private static final long INDEX_TYPE = ObjC.sel("indexType");
    private static final long NEW = ObjC.sel("new");
    private static final long RADIUS_BUFFERS = ObjC.sel("radiusBuffers");
    private static final long RADIUS_FORMAT = ObjC.sel("radiusFormat");
    private static final long RADIUS_STRIDE = ObjC.sel("radiusStride");
    private static final long SEGMENT_CONTROL_POINT_COUNT = ObjC.sel("segmentControlPointCount");
    private static final long SEGMENT_COUNT = ObjC.sel("segmentCount");
    private static final long SET_CONTROL_POINT_BUFFERS = ObjC.sel("setControlPointBuffers:");
    private static final long SET_CONTROL_POINT_COUNT = ObjC.sel("setControlPointCount:");
    private static final long SET_CONTROL_POINT_FORMAT = ObjC.sel("setControlPointFormat:");
    private static final long SET_CONTROL_POINT_STRIDE = ObjC.sel("setControlPointStride:");
    private static final long SET_CURVE_BASIS = ObjC.sel("setCurveBasis:");
    private static final long SET_CURVE_END_CAPS = ObjC.sel("setCurveEndCaps:");
    private static final long SET_CURVE_TYPE = ObjC.sel("setCurveType:");
    private static final long SET_INDEX_BUFFER = ObjC.sel("setIndexBuffer:");
    private static final long SET_INDEX_TYPE = ObjC.sel("setIndexType:");
    private static final long SET_RADIUS_BUFFERS = ObjC.sel("setRadiusBuffers:");
    private static final long SET_RADIUS_FORMAT = ObjC.sel("setRadiusFormat:");
    private static final long SET_RADIUS_STRIDE = ObjC.sel("setRadiusStride:");
    private static final long SET_SEGMENT_CONTROL_POINT_COUNT = ObjC.sel("setSegmentControlPointCount:");
    private static final long SET_SEGMENT_COUNT = ObjC.sel("setSegmentCount:");

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
                sendPtr(MTL_4_ACCELERATION_STRUCTURE_MOTION_CURVE_GEOMETRY_DESCRIPTOR, NEW));
    }

    @SneakyThrows
    public MemorySegment controlPointBuffers(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, CONTROL_POINT_BUFFERS);
    }

    @SneakyThrows
    public void setControlPointBuffers(MemorySegment buffers) {
        RANGE.invokeExact(id, SET_CONTROL_POINT_BUFFERS, buffers);
    }

    public long controlPointCount() {
        return sendLong(id, CONTROL_POINT_COUNT);
    }

    @SneakyThrows
    public void setControlPointCount(long count) {
        L.invokeExact(id, SET_CONTROL_POINT_COUNT, count);
    }

    public long controlPointStride() {
        return sendLong(id, CONTROL_POINT_STRIDE);
    }

    @SneakyThrows
    public void setControlPointStride(long stride) {
        L.invokeExact(id, SET_CONTROL_POINT_STRIDE, stride);
    }

    public long controlPointFormat() {
        return sendLong(id, CONTROL_POINT_FORMAT);
    }

    @SneakyThrows
    public void setControlPointFormat(long format) {
        L.invokeExact(id, SET_CONTROL_POINT_FORMAT, format);
    }

    @SneakyThrows
    public MemorySegment radiusBuffers(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, RADIUS_BUFFERS);
    }

    @SneakyThrows
    public void setRadiusBuffers(MemorySegment buffers) {
        RANGE.invokeExact(id, SET_RADIUS_BUFFERS, buffers);
    }

    public long radiusFormat() {
        return sendLong(id, RADIUS_FORMAT);
    }

    @SneakyThrows
    public void setRadiusFormat(long format) {
        L.invokeExact(id, SET_RADIUS_FORMAT, format);
    }

    public long radiusStride() {
        return sendLong(id, RADIUS_STRIDE);
    }

    @SneakyThrows
    public void setRadiusStride(long stride) {
        L.invokeExact(id, SET_RADIUS_STRIDE, stride);
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

    public long segmentCount() {
        return sendLong(id, SEGMENT_COUNT);
    }

    @SneakyThrows
    public void setSegmentCount(long count) {
        L.invokeExact(id, SET_SEGMENT_COUNT, count);
    }

    public long segmentControlPointCount() {
        return sendLong(id, SEGMENT_CONTROL_POINT_COUNT);
    }

    @SneakyThrows
    public void setSegmentControlPointCount(long count) {
        L.invokeExact(id, SET_SEGMENT_CONTROL_POINT_COUNT, count);
    }

    public long curveType() {
        return sendLong(id, CURVE_TYPE);
    }

    @SneakyThrows
    public void setCurveType(long type) {
        L.invokeExact(id, SET_CURVE_TYPE, type);
    }

    public long curveBasis() {
        return sendLong(id, CURVE_BASIS);
    }

    @SneakyThrows
    public void setCurveBasis(long basis) {
        L.invokeExact(id, SET_CURVE_BASIS, basis);
    }

    public long curveEndCaps() {
        return sendLong(id, CURVE_END_CAPS);
    }

    @SneakyThrows
    public void setCurveEndCaps(long caps) {
        L.invokeExact(id, SET_CURVE_END_CAPS, caps);
    }
}
