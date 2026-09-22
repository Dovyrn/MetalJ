package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import lombok.SneakyThrows;

public class MTLAccelerationStructureCurveGeometryDescriptor extends MTLAccelerationStructureGeometryDescriptor {
    private static final long MTL_ACCELERATION_STRUCTURE_CURVE_GEOMETRY_DESCRIPTOR = ObjC.cls("MTLAccelerationStructureCurveGeometryDescriptor");

    private static final long CONTROL_POINT_BUFFER = ObjC.sel("controlPointBuffer");
    private static final long CONTROL_POINT_BUFFER_OFFSET = ObjC.sel("controlPointBufferOffset");
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
    private static final long RADIUS_BUFFER = ObjC.sel("radiusBuffer");
    private static final long RADIUS_BUFFER_OFFSET = ObjC.sel("radiusBufferOffset");
    private static final long RADIUS_FORMAT = ObjC.sel("radiusFormat");
    private static final long RADIUS_STRIDE = ObjC.sel("radiusStride");
    private static final long SEGMENT_CONTROL_POINT_COUNT = ObjC.sel("segmentControlPointCount");
    private static final long SEGMENT_COUNT = ObjC.sel("segmentCount");
    private static final long SET_CONTROL_POINT_BUFFER = ObjC.sel("setControlPointBuffer:");
    private static final long SET_CONTROL_POINT_BUFFER_OFFSET = ObjC.sel("setControlPointBufferOffset:");
    private static final long SET_CONTROL_POINT_COUNT = ObjC.sel("setControlPointCount:");
    private static final long SET_CONTROL_POINT_FORMAT = ObjC.sel("setControlPointFormat:");
    private static final long SET_CONTROL_POINT_STRIDE = ObjC.sel("setControlPointStride:");
    private static final long SET_CURVE_BASIS = ObjC.sel("setCurveBasis:");
    private static final long SET_CURVE_END_CAPS = ObjC.sel("setCurveEndCaps:");
    private static final long SET_CURVE_TYPE = ObjC.sel("setCurveType:");
    private static final long SET_INDEX_BUFFER = ObjC.sel("setIndexBuffer:");
    private static final long SET_INDEX_BUFFER_OFFSET = ObjC.sel("setIndexBufferOffset:");
    private static final long SET_INDEX_TYPE = ObjC.sel("setIndexType:");
    private static final long SET_RADIUS_BUFFER = ObjC.sel("setRadiusBuffer:");
    private static final long SET_RADIUS_BUFFER_OFFSET = ObjC.sel("setRadiusBufferOffset:");
    private static final long SET_RADIUS_FORMAT = ObjC.sel("setRadiusFormat:");
    private static final long SET_RADIUS_STRIDE = ObjC.sel("setRadiusStride:");
    private static final long SET_SEGMENT_CONTROL_POINT_COUNT = ObjC.sel("setSegmentControlPointCount:");
    private static final long SET_SEGMENT_COUNT = ObjC.sel("setSegmentCount:");

    private MTLAccelerationStructureCurveGeometryDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructureCurveGeometryDescriptor of(long id) {
        return new MTLAccelerationStructureCurveGeometryDescriptor(id);
    }

    public static MTLAccelerationStructureCurveGeometryDescriptor descriptor() {
        return new MTLAccelerationStructureCurveGeometryDescriptor(owned(() -> sendPtr(MTL_ACCELERATION_STRUCTURE_CURVE_GEOMETRY_DESCRIPTOR, DESCRIPTOR)));
    }

    public MTLBuffer controlPointBuffer() {
        return MTLBuffer.of(sendPtr(id, CONTROL_POINT_BUFFER));
    }

    @SneakyThrows
    public void setControlPointBuffer(MTLBuffer controlPointBuffer) {
        P.invokeExact(id, SET_CONTROL_POINT_BUFFER, controlPointBuffer.getId());
    }

    public long controlPointBufferOffset() {
        return sendLong(id, CONTROL_POINT_BUFFER_OFFSET);
    }

    @SneakyThrows
    public void setControlPointBufferOffset(long controlPointBufferOffset) {
        L.invokeExact(id, SET_CONTROL_POINT_BUFFER_OFFSET, controlPointBufferOffset);
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

    public MTLBuffer radiusBuffer() {
        return MTLBuffer.of(sendPtr(id, RADIUS_BUFFER));
    }

    @SneakyThrows
    public void setRadiusBuffer(MTLBuffer radiusBuffer) {
        P.invokeExact(id, SET_RADIUS_BUFFER, radiusBuffer.getId());
    }

    public long radiusBufferOffset() {
        return sendLong(id, RADIUS_BUFFER_OFFSET);
    }

    @SneakyThrows
    public void setRadiusBufferOffset(long radiusBufferOffset) {
        L.invokeExact(id, SET_RADIUS_BUFFER_OFFSET, radiusBufferOffset);
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
