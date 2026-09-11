package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import lombok.SneakyThrows;

public class MTLInstanceAccelerationStructureDescriptor extends MTLAccelerationStructureDescriptor {
    private MTLInstanceAccelerationStructureDescriptor(long id) {
        super(id);
    }

    public static MTLInstanceAccelerationStructureDescriptor of(long id) {
        return new MTLInstanceAccelerationStructureDescriptor(id);
    }

    public static MTLInstanceAccelerationStructureDescriptor descriptor() {
        return new MTLInstanceAccelerationStructureDescriptor(
                owned(() -> sendPtr(ObjC.cls("MTLInstanceAccelerationStructureDescriptor"), "descriptor")));
    }

    public MTLBuffer instanceDescriptorBuffer() {
        return MTLBuffer.of(sendPtr(id, "instanceDescriptorBuffer"));
    }

    @SneakyThrows
    public void setInstanceDescriptorBuffer(MTLBuffer buffer) {
        P.invokeExact(id, ObjC.sel("setInstanceDescriptorBuffer:"), buffer.getId());
    }

    public long instanceDescriptorBufferOffset() {
        return sendLong(id, "instanceDescriptorBufferOffset");
    }

    @SneakyThrows
    public void setInstanceDescriptorBufferOffset(long offset) {
        L.invokeExact(id, ObjC.sel("setInstanceDescriptorBufferOffset:"), offset);
    }

    public long instanceDescriptorStride() {
        return sendLong(id, "instanceDescriptorStride");
    }

    @SneakyThrows
    public void setInstanceDescriptorStride(long stride) {
        L.invokeExact(id, ObjC.sel("setInstanceDescriptorStride:"), stride);
    }

    public long instanceCount() {
        return sendLong(id, "instanceCount");
    }

    @SneakyThrows
    public void setInstanceCount(long count) {
        L.invokeExact(id, ObjC.sel("setInstanceCount:"), count);
    }

    public long instanceDescriptorType() {
        return sendLong(id, "instanceDescriptorType");
    }

    @SneakyThrows
    public void setInstanceDescriptorType(long type) {
        L.invokeExact(id, ObjC.sel("setInstanceDescriptorType:"), type);
    }

    public long instanceTransformationMatrixLayout() {
        return sendLong(id, "instanceTransformationMatrixLayout");
    }

    @SneakyThrows
    public void setInstanceTransformationMatrixLayout(long layout) {
        L.invokeExact(id, ObjC.sel("setInstanceTransformationMatrixLayout:"), layout);
    }

    public NSArray instancedAccelerationStructures() {
        return NSArray.of(sendPtr(id, "instancedAccelerationStructures"));
    }

    @SneakyThrows
    public void setInstancedAccelerationStructures(NSArray structures) {
        P.invokeExact(id, ObjC.sel("setInstancedAccelerationStructures:"), structures.getId());
    }

    public MTLBuffer motionTransformBuffer() {
        return MTLBuffer.of(sendPtr(id, "motionTransformBuffer"));
    }

    @SneakyThrows
    public void setMotionTransformBuffer(MTLBuffer buffer) {
        P.invokeExact(id, ObjC.sel("setMotionTransformBuffer:"), buffer.getId());
    }

    public long motionTransformCount() {
        return sendLong(id, "motionTransformCount");
    }

    @SneakyThrows
    public void setMotionTransformCount(long count) {
        L.invokeExact(id, ObjC.sel("setMotionTransformCount:"), count);
    }
}
