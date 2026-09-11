package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import lombok.SneakyThrows;

public class MTLAccelerationStructureCurveGeometryDescriptor extends MTLAccelerationStructureGeometryDescriptor {
    private MTLAccelerationStructureCurveGeometryDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructureCurveGeometryDescriptor of(long id) {
        return new MTLAccelerationStructureCurveGeometryDescriptor(id);
    }

    public static MTLAccelerationStructureCurveGeometryDescriptor descriptor() {
        return new MTLAccelerationStructureCurveGeometryDescriptor(owned(() -> sendPtr(ObjC.cls("MTLAccelerationStructureCurveGeometryDescriptor"), "descriptor")));
    }

    public MTLBuffer controlPointBuffer() {
        return MTLBuffer.of(sendPtr(id, "controlPointBuffer"));
    }

    @SneakyThrows
    public void setControlPointBuffer(MTLBuffer controlPointBuffer) {
        P.invokeExact(id, ObjC.sel("setControlPointBuffer:"), controlPointBuffer.getId());
    }

    public long controlPointBufferOffset() {
        return sendLong(id, "controlPointBufferOffset");
    }

    @SneakyThrows
    public void setControlPointBufferOffset(long controlPointBufferOffset) {
        L.invokeExact(id, ObjC.sel("setControlPointBufferOffset:"), controlPointBufferOffset);
    }

    public long controlPointCount() {
        return sendLong(id, "controlPointCount");
    }

    @SneakyThrows
    public void setControlPointCount(long controlPointCount) {
        L.invokeExact(id, ObjC.sel("setControlPointCount:"), controlPointCount);
    }

    public long controlPointStride() {
        return sendLong(id, "controlPointStride");
    }

    @SneakyThrows
    public void setControlPointStride(long controlPointStride) {
        L.invokeExact(id, ObjC.sel("setControlPointStride:"), controlPointStride);
    }

    public long controlPointFormat() {
        return sendLong(id, "controlPointFormat");
    }

    @SneakyThrows
    public void setControlPointFormat(long controlPointFormat) {
        L.invokeExact(id, ObjC.sel("setControlPointFormat:"), controlPointFormat);
    }

    public MTLBuffer radiusBuffer() {
        return MTLBuffer.of(sendPtr(id, "radiusBuffer"));
    }

    @SneakyThrows
    public void setRadiusBuffer(MTLBuffer radiusBuffer) {
        P.invokeExact(id, ObjC.sel("setRadiusBuffer:"), radiusBuffer.getId());
    }

    public long radiusBufferOffset() {
        return sendLong(id, "radiusBufferOffset");
    }

    @SneakyThrows
    public void setRadiusBufferOffset(long radiusBufferOffset) {
        L.invokeExact(id, ObjC.sel("setRadiusBufferOffset:"), radiusBufferOffset);
    }

    public long radiusFormat() {
        return sendLong(id, "radiusFormat");
    }

    @SneakyThrows
    public void setRadiusFormat(long radiusFormat) {
        L.invokeExact(id, ObjC.sel("setRadiusFormat:"), radiusFormat);
    }

    public long radiusStride() {
        return sendLong(id, "radiusStride");
    }

    @SneakyThrows
    public void setRadiusStride(long radiusStride) {
        L.invokeExact(id, ObjC.sel("setRadiusStride:"), radiusStride);
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

    public long segmentCount() {
        return sendLong(id, "segmentCount");
    }

    @SneakyThrows
    public void setSegmentCount(long segmentCount) {
        L.invokeExact(id, ObjC.sel("setSegmentCount:"), segmentCount);
    }

    public long segmentControlPointCount() {
        return sendLong(id, "segmentControlPointCount");
    }

    @SneakyThrows
    public void setSegmentControlPointCount(long segmentControlPointCount) {
        L.invokeExact(id, ObjC.sel("setSegmentControlPointCount:"), segmentControlPointCount);
    }

    public long curveType() {
        return sendLong(id, "curveType");
    }

    @SneakyThrows
    public void setCurveType(long curveType) {
        L.invokeExact(id, ObjC.sel("setCurveType:"), curveType);
    }

    public long curveBasis() {
        return sendLong(id, "curveBasis");
    }

    @SneakyThrows
    public void setCurveBasis(long curveBasis) {
        L.invokeExact(id, ObjC.sel("setCurveBasis:"), curveBasis);
    }

    public long curveEndCaps() {
        return sendLong(id, "curveEndCaps");
    }

    @SneakyThrows
    public void setCurveEndCaps(long curveEndCaps) {
        L.invokeExact(id, ObjC.sel("setCurveEndCaps:"), curveEndCaps);
    }
}
