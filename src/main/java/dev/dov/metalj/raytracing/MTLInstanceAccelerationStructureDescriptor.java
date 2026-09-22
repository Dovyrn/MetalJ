package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import lombok.SneakyThrows;

public class MTLInstanceAccelerationStructureDescriptor extends MTLAccelerationStructureDescriptor {
    private static final long MTL_INSTANCE_ACCELERATION_STRUCTURE_DESCRIPTOR = ObjC.cls("MTLInstanceAccelerationStructureDescriptor");

    private static final long DESCRIPTOR = ObjC.sel("descriptor");
    private static final long INSTANCE_COUNT = ObjC.sel("instanceCount");
    private static final long INSTANCE_DESCRIPTOR_BUFFER = ObjC.sel("instanceDescriptorBuffer");
    private static final long INSTANCE_DESCRIPTOR_BUFFER_OFFSET = ObjC.sel("instanceDescriptorBufferOffset");
    private static final long INSTANCE_DESCRIPTOR_STRIDE = ObjC.sel("instanceDescriptorStride");
    private static final long INSTANCE_DESCRIPTOR_TYPE = ObjC.sel("instanceDescriptorType");
    private static final long INSTANCE_TRANSFORMATION_MATRIX_LAYOUT = ObjC.sel("instanceTransformationMatrixLayout");
    private static final long INSTANCED_ACCELERATION_STRUCTURES = ObjC.sel("instancedAccelerationStructures");
    private static final long MOTION_TRANSFORM_BUFFER = ObjC.sel("motionTransformBuffer");
    private static final long MOTION_TRANSFORM_COUNT = ObjC.sel("motionTransformCount");
    private static final long SET_INSTANCE_COUNT = ObjC.sel("setInstanceCount:");
    private static final long SET_INSTANCE_DESCRIPTOR_BUFFER = ObjC.sel("setInstanceDescriptorBuffer:");
    private static final long SET_INSTANCE_DESCRIPTOR_BUFFER_OFFSET = ObjC.sel("setInstanceDescriptorBufferOffset:");
    private static final long SET_INSTANCE_DESCRIPTOR_STRIDE = ObjC.sel("setInstanceDescriptorStride:");
    private static final long SET_INSTANCE_DESCRIPTOR_TYPE = ObjC.sel("setInstanceDescriptorType:");
    private static final long SET_INSTANCE_TRANSFORMATION_MATRIX_LAYOUT = ObjC.sel("setInstanceTransformationMatrixLayout:");
    private static final long SET_INSTANCED_ACCELERATION_STRUCTURES = ObjC.sel("setInstancedAccelerationStructures:");
    private static final long SET_MOTION_TRANSFORM_BUFFER = ObjC.sel("setMotionTransformBuffer:");
    private static final long SET_MOTION_TRANSFORM_COUNT = ObjC.sel("setMotionTransformCount:");

    private MTLInstanceAccelerationStructureDescriptor(long id) {
        super(id);
    }

    public static MTLInstanceAccelerationStructureDescriptor of(long id) {
        return new MTLInstanceAccelerationStructureDescriptor(id);
    }

    public static MTLInstanceAccelerationStructureDescriptor descriptor() {
        return new MTLInstanceAccelerationStructureDescriptor(
                owned(() -> sendPtr(MTL_INSTANCE_ACCELERATION_STRUCTURE_DESCRIPTOR, DESCRIPTOR)));
    }

    public MTLBuffer instanceDescriptorBuffer() {
        return MTLBuffer.of(sendPtr(id, INSTANCE_DESCRIPTOR_BUFFER));
    }

    @SneakyThrows
    public void setInstanceDescriptorBuffer(MTLBuffer buffer) {
        P.invokeExact(id, SET_INSTANCE_DESCRIPTOR_BUFFER, buffer.getId());
    }

    public long instanceDescriptorBufferOffset() {
        return sendLong(id, INSTANCE_DESCRIPTOR_BUFFER_OFFSET);
    }

    @SneakyThrows
    public void setInstanceDescriptorBufferOffset(long offset) {
        L.invokeExact(id, SET_INSTANCE_DESCRIPTOR_BUFFER_OFFSET, offset);
    }

    public long instanceDescriptorStride() {
        return sendLong(id, INSTANCE_DESCRIPTOR_STRIDE);
    }

    @SneakyThrows
    public void setInstanceDescriptorStride(long stride) {
        L.invokeExact(id, SET_INSTANCE_DESCRIPTOR_STRIDE, stride);
    }

    public long instanceCount() {
        return sendLong(id, INSTANCE_COUNT);
    }

    @SneakyThrows
    public void setInstanceCount(long count) {
        L.invokeExact(id, SET_INSTANCE_COUNT, count);
    }

    public long instanceDescriptorType() {
        return sendLong(id, INSTANCE_DESCRIPTOR_TYPE);
    }

    @SneakyThrows
    public void setInstanceDescriptorType(long type) {
        L.invokeExact(id, SET_INSTANCE_DESCRIPTOR_TYPE, type);
    }

    public long instanceTransformationMatrixLayout() {
        return sendLong(id, INSTANCE_TRANSFORMATION_MATRIX_LAYOUT);
    }

    @SneakyThrows
    public void setInstanceTransformationMatrixLayout(long layout) {
        L.invokeExact(id, SET_INSTANCE_TRANSFORMATION_MATRIX_LAYOUT, layout);
    }

    public NSArray instancedAccelerationStructures() {
        return NSArray.of(sendPtr(id, INSTANCED_ACCELERATION_STRUCTURES));
    }

    @SneakyThrows
    public void setInstancedAccelerationStructures(NSArray structures) {
        P.invokeExact(id, SET_INSTANCED_ACCELERATION_STRUCTURES, structures.getId());
    }

    public MTLBuffer motionTransformBuffer() {
        return MTLBuffer.of(sendPtr(id, MOTION_TRANSFORM_BUFFER));
    }

    @SneakyThrows
    public void setMotionTransformBuffer(MTLBuffer buffer) {
        P.invokeExact(id, SET_MOTION_TRANSFORM_BUFFER, buffer.getId());
    }

    public long motionTransformCount() {
        return sendLong(id, MOTION_TRANSFORM_COUNT);
    }

    @SneakyThrows
    public void setMotionTransformCount(long count) {
        L.invokeExact(id, SET_MOTION_TRANSFORM_COUNT, count);
    }
}
