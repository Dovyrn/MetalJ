package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import lombok.SneakyThrows;

public class MTLIndirectInstanceAccelerationStructureDescriptor extends MTLAccelerationStructureDescriptor {
    private MTLIndirectInstanceAccelerationStructureDescriptor(long id) {
        super(id);
    }

    public static MTLIndirectInstanceAccelerationStructureDescriptor of(long id) {
        return new MTLIndirectInstanceAccelerationStructureDescriptor(id);
    }

    public static MTLIndirectInstanceAccelerationStructureDescriptor descriptor() {
        return new MTLIndirectInstanceAccelerationStructureDescriptor(sendPtr(ObjC.cls("MTLIndirectInstanceAccelerationStructureDescriptor"), "descriptor"));
    }

    public MTLBuffer instanceDescriptorBuffer() {
        return MTLBuffer.of(sendPtr(id, "instanceDescriptorBuffer"));
    }

    @SneakyThrows
    public void setInstanceDescriptorBuffer(MTLBuffer instanceDescriptorBuffer) {
        P.invokeExact(id, ObjC.sel("setInstanceDescriptorBuffer:"), instanceDescriptorBuffer.getId());
    }

    public long instanceDescriptorBufferOffset() {
        return sendLong(id, "instanceDescriptorBufferOffset");
    }

    @SneakyThrows
    public void setInstanceDescriptorBufferOffset(long instanceDescriptorBufferOffset) {
        L.invokeExact(id, ObjC.sel("setInstanceDescriptorBufferOffset:"), instanceDescriptorBufferOffset);
    }

    public long instanceDescriptorStride() {
        return sendLong(id, "instanceDescriptorStride");
    }

    @SneakyThrows
    public void setInstanceDescriptorStride(long instanceDescriptorStride) {
        L.invokeExact(id, ObjC.sel("setInstanceDescriptorStride:"), instanceDescriptorStride);
    }

    public long maxInstanceCount() {
        return sendLong(id, "maxInstanceCount");
    }

    @SneakyThrows
    public void setMaxInstanceCount(long maxInstanceCount) {
        L.invokeExact(id, ObjC.sel("setMaxInstanceCount:"), maxInstanceCount);
    }

    public MTLBuffer instanceCountBuffer() {
        return MTLBuffer.of(sendPtr(id, "instanceCountBuffer"));
    }

    @SneakyThrows
    public void setInstanceCountBuffer(MTLBuffer instanceCountBuffer) {
        P.invokeExact(id, ObjC.sel("setInstanceCountBuffer:"), instanceCountBuffer.getId());
    }

    public long instanceCountBufferOffset() {
        return sendLong(id, "instanceCountBufferOffset");
    }

    @SneakyThrows
    public void setInstanceCountBufferOffset(long instanceCountBufferOffset) {
        L.invokeExact(id, ObjC.sel("setInstanceCountBufferOffset:"), instanceCountBufferOffset);
    }

    public long instanceDescriptorType() {
        return sendLong(id, "instanceDescriptorType");
    }

    @SneakyThrows
    public void setInstanceDescriptorType(long instanceDescriptorType) {
        L.invokeExact(id, ObjC.sel("setInstanceDescriptorType:"), instanceDescriptorType);
    }

    public MTLBuffer motionTransformBuffer() {
        return MTLBuffer.of(sendPtr(id, "motionTransformBuffer"));
    }

    @SneakyThrows
    public void setMotionTransformBuffer(MTLBuffer motionTransformBuffer) {
        P.invokeExact(id, ObjC.sel("setMotionTransformBuffer:"), motionTransformBuffer.getId());
    }

    public long motionTransformBufferOffset() {
        return sendLong(id, "motionTransformBufferOffset");
    }

    @SneakyThrows
    public void setMotionTransformBufferOffset(long motionTransformBufferOffset) {
        L.invokeExact(id, ObjC.sel("setMotionTransformBufferOffset:"), motionTransformBufferOffset);
    }

    public long maxMotionTransformCount() {
        return sendLong(id, "maxMotionTransformCount");
    }

    @SneakyThrows
    public void setMaxMotionTransformCount(long maxMotionTransformCount) {
        L.invokeExact(id, ObjC.sel("setMaxMotionTransformCount:"), maxMotionTransformCount);
    }

    public MTLBuffer motionTransformCountBuffer() {
        return MTLBuffer.of(sendPtr(id, "motionTransformCountBuffer"));
    }

    @SneakyThrows
    public void setMotionTransformCountBuffer(MTLBuffer motionTransformCountBuffer) {
        P.invokeExact(id, ObjC.sel("setMotionTransformCountBuffer:"), motionTransformCountBuffer.getId());
    }

    public long motionTransformCountBufferOffset() {
        return sendLong(id, "motionTransformCountBufferOffset");
    }

    @SneakyThrows
    public void setMotionTransformCountBufferOffset(long motionTransformCountBufferOffset) {
        L.invokeExact(id, ObjC.sel("setMotionTransformCountBufferOffset:"), motionTransformCountBufferOffset);
    }

    public long instanceTransformationMatrixLayout() {
        return sendLong(id, "instanceTransformationMatrixLayout");
    }

    @SneakyThrows
    public void setInstanceTransformationMatrixLayout(long instanceTransformationMatrixLayout) {
        L.invokeExact(id, ObjC.sel("setInstanceTransformationMatrixLayout:"), instanceTransformationMatrixLayout);
    }

    public long motionTransformType() {
        return sendLong(id, "motionTransformType");
    }

    @SneakyThrows
    public void setMotionTransformType(long motionTransformType) {
        L.invokeExact(id, ObjC.sel("setMotionTransformType:"), motionTransformType);
    }

    public long motionTransformStride() {
        return sendLong(id, "motionTransformStride");
    }

    @SneakyThrows
    public void setMotionTransformStride(long motionTransformStride) {
        L.invokeExact(id, ObjC.sel("setMotionTransformStride:"), motionTransformStride);
    }
}
