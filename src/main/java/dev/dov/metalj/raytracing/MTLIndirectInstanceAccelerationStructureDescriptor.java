package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import lombok.SneakyThrows;

public class MTLIndirectInstanceAccelerationStructureDescriptor extends MTLAccelerationStructureDescriptor {
    private static final long MTL_INDIRECT_INSTANCE_ACCELERATION_STRUCTURE_DESCRIPTOR = ObjC.cls("MTLIndirectInstanceAccelerationStructureDescriptor");

    private static final long DESCRIPTOR = ObjC.sel("descriptor");
    private static final long INSTANCE_COUNT_BUFFER = ObjC.sel("instanceCountBuffer");
    private static final long INSTANCE_COUNT_BUFFER_OFFSET = ObjC.sel("instanceCountBufferOffset");
    private static final long INSTANCE_DESCRIPTOR_BUFFER = ObjC.sel("instanceDescriptorBuffer");
    private static final long INSTANCE_DESCRIPTOR_BUFFER_OFFSET = ObjC.sel("instanceDescriptorBufferOffset");
    private static final long INSTANCE_DESCRIPTOR_STRIDE = ObjC.sel("instanceDescriptorStride");
    private static final long INSTANCE_DESCRIPTOR_TYPE = ObjC.sel("instanceDescriptorType");
    private static final long INSTANCE_TRANSFORMATION_MATRIX_LAYOUT = ObjC.sel("instanceTransformationMatrixLayout");
    private static final long MAX_INSTANCE_COUNT = ObjC.sel("maxInstanceCount");
    private static final long MAX_MOTION_TRANSFORM_COUNT = ObjC.sel("maxMotionTransformCount");
    private static final long MOTION_TRANSFORM_BUFFER = ObjC.sel("motionTransformBuffer");
    private static final long MOTION_TRANSFORM_BUFFER_OFFSET = ObjC.sel("motionTransformBufferOffset");
    private static final long MOTION_TRANSFORM_COUNT_BUFFER = ObjC.sel("motionTransformCountBuffer");
    private static final long MOTION_TRANSFORM_COUNT_BUFFER_OFFSET = ObjC.sel("motionTransformCountBufferOffset");
    private static final long MOTION_TRANSFORM_STRIDE = ObjC.sel("motionTransformStride");
    private static final long MOTION_TRANSFORM_TYPE = ObjC.sel("motionTransformType");
    private static final long SET_INSTANCE_COUNT_BUFFER = ObjC.sel("setInstanceCountBuffer:");
    private static final long SET_INSTANCE_COUNT_BUFFER_OFFSET = ObjC.sel("setInstanceCountBufferOffset:");
    private static final long SET_INSTANCE_DESCRIPTOR_BUFFER = ObjC.sel("setInstanceDescriptorBuffer:");
    private static final long SET_INSTANCE_DESCRIPTOR_BUFFER_OFFSET = ObjC.sel("setInstanceDescriptorBufferOffset:");
    private static final long SET_INSTANCE_DESCRIPTOR_STRIDE = ObjC.sel("setInstanceDescriptorStride:");
    private static final long SET_INSTANCE_DESCRIPTOR_TYPE = ObjC.sel("setInstanceDescriptorType:");
    private static final long SET_INSTANCE_TRANSFORMATION_MATRIX_LAYOUT = ObjC.sel("setInstanceTransformationMatrixLayout:");
    private static final long SET_MAX_INSTANCE_COUNT = ObjC.sel("setMaxInstanceCount:");
    private static final long SET_MAX_MOTION_TRANSFORM_COUNT = ObjC.sel("setMaxMotionTransformCount:");
    private static final long SET_MOTION_TRANSFORM_BUFFER = ObjC.sel("setMotionTransformBuffer:");
    private static final long SET_MOTION_TRANSFORM_BUFFER_OFFSET = ObjC.sel("setMotionTransformBufferOffset:");
    private static final long SET_MOTION_TRANSFORM_COUNT_BUFFER = ObjC.sel("setMotionTransformCountBuffer:");
    private static final long SET_MOTION_TRANSFORM_COUNT_BUFFER_OFFSET = ObjC.sel("setMotionTransformCountBufferOffset:");
    private static final long SET_MOTION_TRANSFORM_STRIDE = ObjC.sel("setMotionTransformStride:");
    private static final long SET_MOTION_TRANSFORM_TYPE = ObjC.sel("setMotionTransformType:");

    private MTLIndirectInstanceAccelerationStructureDescriptor(long id) {
        super(id);
    }

    public static MTLIndirectInstanceAccelerationStructureDescriptor of(long id) {
        return new MTLIndirectInstanceAccelerationStructureDescriptor(id);
    }

    public static MTLIndirectInstanceAccelerationStructureDescriptor descriptor() {
        return new MTLIndirectInstanceAccelerationStructureDescriptor(owned(() -> sendPtr(MTL_INDIRECT_INSTANCE_ACCELERATION_STRUCTURE_DESCRIPTOR, DESCRIPTOR)));
    }

    public MTLBuffer instanceDescriptorBuffer() {
        return MTLBuffer.of(sendPtr(id, INSTANCE_DESCRIPTOR_BUFFER));
    }

    @SneakyThrows
    public void setInstanceDescriptorBuffer(MTLBuffer instanceDescriptorBuffer) {
        P.invokeExact(id, SET_INSTANCE_DESCRIPTOR_BUFFER, instanceDescriptorBuffer.getId());
    }

    public long instanceDescriptorBufferOffset() {
        return sendLong(id, INSTANCE_DESCRIPTOR_BUFFER_OFFSET);
    }

    @SneakyThrows
    public void setInstanceDescriptorBufferOffset(long instanceDescriptorBufferOffset) {
        L.invokeExact(id, SET_INSTANCE_DESCRIPTOR_BUFFER_OFFSET, instanceDescriptorBufferOffset);
    }

    public long instanceDescriptorStride() {
        return sendLong(id, INSTANCE_DESCRIPTOR_STRIDE);
    }

    @SneakyThrows
    public void setInstanceDescriptorStride(long instanceDescriptorStride) {
        L.invokeExact(id, SET_INSTANCE_DESCRIPTOR_STRIDE, instanceDescriptorStride);
    }

    public long maxInstanceCount() {
        return sendLong(id, MAX_INSTANCE_COUNT);
    }

    @SneakyThrows
    public void setMaxInstanceCount(long maxInstanceCount) {
        L.invokeExact(id, SET_MAX_INSTANCE_COUNT, maxInstanceCount);
    }

    public MTLBuffer instanceCountBuffer() {
        return MTLBuffer.of(sendPtr(id, INSTANCE_COUNT_BUFFER));
    }

    @SneakyThrows
    public void setInstanceCountBuffer(MTLBuffer instanceCountBuffer) {
        P.invokeExact(id, SET_INSTANCE_COUNT_BUFFER, instanceCountBuffer.getId());
    }

    public long instanceCountBufferOffset() {
        return sendLong(id, INSTANCE_COUNT_BUFFER_OFFSET);
    }

    @SneakyThrows
    public void setInstanceCountBufferOffset(long instanceCountBufferOffset) {
        L.invokeExact(id, SET_INSTANCE_COUNT_BUFFER_OFFSET, instanceCountBufferOffset);
    }

    public long instanceDescriptorType() {
        return sendLong(id, INSTANCE_DESCRIPTOR_TYPE);
    }

    @SneakyThrows
    public void setInstanceDescriptorType(long instanceDescriptorType) {
        L.invokeExact(id, SET_INSTANCE_DESCRIPTOR_TYPE, instanceDescriptorType);
    }

    public MTLBuffer motionTransformBuffer() {
        return MTLBuffer.of(sendPtr(id, MOTION_TRANSFORM_BUFFER));
    }

    @SneakyThrows
    public void setMotionTransformBuffer(MTLBuffer motionTransformBuffer) {
        P.invokeExact(id, SET_MOTION_TRANSFORM_BUFFER, motionTransformBuffer.getId());
    }

    public long motionTransformBufferOffset() {
        return sendLong(id, MOTION_TRANSFORM_BUFFER_OFFSET);
    }

    @SneakyThrows
    public void setMotionTransformBufferOffset(long motionTransformBufferOffset) {
        L.invokeExact(id, SET_MOTION_TRANSFORM_BUFFER_OFFSET, motionTransformBufferOffset);
    }

    public long maxMotionTransformCount() {
        return sendLong(id, MAX_MOTION_TRANSFORM_COUNT);
    }

    @SneakyThrows
    public void setMaxMotionTransformCount(long maxMotionTransformCount) {
        L.invokeExact(id, SET_MAX_MOTION_TRANSFORM_COUNT, maxMotionTransformCount);
    }

    public MTLBuffer motionTransformCountBuffer() {
        return MTLBuffer.of(sendPtr(id, MOTION_TRANSFORM_COUNT_BUFFER));
    }

    @SneakyThrows
    public void setMotionTransformCountBuffer(MTLBuffer motionTransformCountBuffer) {
        P.invokeExact(id, SET_MOTION_TRANSFORM_COUNT_BUFFER, motionTransformCountBuffer.getId());
    }

    public long motionTransformCountBufferOffset() {
        return sendLong(id, MOTION_TRANSFORM_COUNT_BUFFER_OFFSET);
    }

    @SneakyThrows
    public void setMotionTransformCountBufferOffset(long motionTransformCountBufferOffset) {
        L.invokeExact(id, SET_MOTION_TRANSFORM_COUNT_BUFFER_OFFSET, motionTransformCountBufferOffset);
    }

    public long instanceTransformationMatrixLayout() {
        return sendLong(id, INSTANCE_TRANSFORMATION_MATRIX_LAYOUT);
    }

    @SneakyThrows
    public void setInstanceTransformationMatrixLayout(long instanceTransformationMatrixLayout) {
        L.invokeExact(id, SET_INSTANCE_TRANSFORMATION_MATRIX_LAYOUT, instanceTransformationMatrixLayout);
    }

    public long motionTransformType() {
        return sendLong(id, MOTION_TRANSFORM_TYPE);
    }

    @SneakyThrows
    public void setMotionTransformType(long motionTransformType) {
        L.invokeExact(id, SET_MOTION_TRANSFORM_TYPE, motionTransformType);
    }

    public long motionTransformStride() {
        return sendLong(id, MOTION_TRANSFORM_STRIDE);
    }

    @SneakyThrows
    public void setMotionTransformStride(long motionTransformStride) {
        L.invokeExact(id, SET_MOTION_TRANSFORM_STRIDE, motionTransformStride);
    }
}
