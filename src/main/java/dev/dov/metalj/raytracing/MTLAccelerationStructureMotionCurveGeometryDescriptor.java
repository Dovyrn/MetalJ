package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import lombok.SneakyThrows;

public class MTLAccelerationStructureMotionCurveGeometryDescriptor extends MTLAccelerationStructureGeometryDescriptor {
    private static final long MTL_ACCELERATION_STRUCTURE_MOTION_CURVE_GEOMETRY_DESCRIPTOR = ObjC.cls("MTLAccelerationStructureMotionCurveGeometryDescriptor");

    private static final long CONTROL_POINT_BUFFERS = ObjC.sel("controlPointBuffers");
    private static final long CONTROL_POINT_COUNT = ObjC.sel("controlPointCount");
    private static final long CONTROL_POINT_FORMAT = ObjC.sel("controlPointFormat");
    private static final long CONTROL_POINT_STRIDE = ObjC.sel("controlPointStride");
    private static final long CURVE_BASIS = ObjC.sel("curveBasis");
    private static final long CURVE_END_CAPS = ObjC.sel("curveEndCaps");
    private static final long CURVE_TYPE = ObjC.sel("curveType");
    private static final long DESCRIPTOR = ObjC.sel("descriptor");
    private static final long INDEX_BUFFER = ObjC.sel("indexBuffer");
    private static final long INDEX_BUFFER_OFFSET = ObjC.sel("indexBufferOffset");
    private static final long INDEX_TYPE = ObjC.sel("indexType");
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
    private static final long SET_INDEX_BUFFER_OFFSET = ObjC.sel("setIndexBufferOffset:");
    private static final long SET_INDEX_TYPE = ObjC.sel("setIndexType:");
    private static final long SET_RADIUS_BUFFERS = ObjC.sel("setRadiusBuffers:");
    private static final long SET_RADIUS_FORMAT = ObjC.sel("setRadiusFormat:");
    private static final long SET_RADIUS_STRIDE = ObjC.sel("setRadiusStride:");
    private static final long SET_SEGMENT_CONTROL_POINT_COUNT = ObjC.sel("setSegmentControlPointCount:");
    private static final long SET_SEGMENT_COUNT = ObjC.sel("setSegmentCount:");

    private MTLAccelerationStructureMotionCurveGeometryDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructureMotionCurveGeometryDescriptor of(long id) {
        return new MTLAccelerationStructureMotionCurveGeometryDescriptor(id);
    }

    public static MTLAccelerationStructureMotionCurveGeometryDescriptor descriptor() {
        return new MTLAccelerationStructureMotionCurveGeometryDescriptor(owned(() -> sendPtr(MTL_ACCELERATION_STRUCTURE_MOTION_CURVE_GEOMETRY_DESCRIPTOR, DESCRIPTOR)));
    }

    public NSArray controlPointBuffers() {
        return NSArray.of(sendPtr(id, CONTROL_POINT_BUFFERS));
    }

    @SneakyThrows
    public void setControlPointBuffers(NSArray controlPointBuffers) {
        P.invokeExact(id, SET_CONTROL_POINT_BUFFERS, controlPointBuffers.getId());
    }

    public long controlPointCount() {
        return sendLong(id, CONTROL_POINT_COUNT);
    }

    @SneakyThrows
    public void setControlPointCount(long controlPointCount) {
        L.invokeExact(id, SET_CONTROL_POINT_COUNT, controlPointCount);
    }

    public long controlPointStride() {
        return sendLong(id, CONTROL_POINT_STRIDE);
    }

    @SneakyThrows
    public void setControlPointStride(long controlPointStride) {
        L.invokeExact(id, SET_CONTROL_POINT_STRIDE, controlPointStride);
    }

    public long controlPointFormat() {
        return sendLong(id, CONTROL_POINT_FORMAT);
    }

    @SneakyThrows
    public void setControlPointFormat(long controlPointFormat) {
        L.invokeExact(id, SET_CONTROL_POINT_FORMAT, controlPointFormat);
    }

    public NSArray radiusBuffers() {
        return NSArray.of(sendPtr(id, RADIUS_BUFFERS));
    }

    @SneakyThrows
    public void setRadiusBuffers(NSArray radiusBuffers) {
        P.invokeExact(id, SET_RADIUS_BUFFERS, radiusBuffers.getId());
    }

    public long radiusFormat() {
        return sendLong(id, RADIUS_FORMAT);
    }

    @SneakyThrows
    public void setRadiusFormat(long radiusFormat) {
        L.invokeExact(id, SET_RADIUS_FORMAT, radiusFormat);
    }

    public long radiusStride() {
        return sendLong(id, RADIUS_STRIDE);
    }

    @SneakyThrows
    public void setRadiusStride(long radiusStride) {
        L.invokeExact(id, SET_RADIUS_STRIDE, radiusStride);
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

    public long segmentCount() {
        return sendLong(id, SEGMENT_COUNT);
    }

    @SneakyThrows
    public void setSegmentCount(long segmentCount) {
        L.invokeExact(id, SET_SEGMENT_COUNT, segmentCount);
    }

    public long segmentControlPointCount() {
        return sendLong(id, SEGMENT_CONTROL_POINT_COUNT);
    }

    @SneakyThrows
    public void setSegmentControlPointCount(long segmentControlPointCount) {
        L.invokeExact(id, SET_SEGMENT_CONTROL_POINT_COUNT, segmentControlPointCount);
    }

    public long curveType() {
        return sendLong(id, CURVE_TYPE);
    }

    @SneakyThrows
    public void setCurveType(long curveType) {
        L.invokeExact(id, SET_CURVE_TYPE, curveType);
    }

    public long curveBasis() {
        return sendLong(id, CURVE_BASIS);
    }

    @SneakyThrows
    public void setCurveBasis(long curveBasis) {
        L.invokeExact(id, SET_CURVE_BASIS, curveBasis);
    }

    public long curveEndCaps() {
        return sendLong(id, CURVE_END_CAPS);
    }

    @SneakyThrows
    public void setCurveEndCaps(long curveEndCaps) {
        L.invokeExact(id, SET_CURVE_END_CAPS, curveEndCaps);
    }
}
