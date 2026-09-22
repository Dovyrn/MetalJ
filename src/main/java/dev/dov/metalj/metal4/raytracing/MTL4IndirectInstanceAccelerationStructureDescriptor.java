package dev.dov.metalj.metal4.raytracing;

import dev.dov.metalj.metal4.MTL4BufferRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4IndirectInstanceAccelerationStructureDescriptor extends MTL4AccelerationStructureDescriptor {
    private static final long MTL_4_INDIRECT_INSTANCE_ACCELERATION_STRUCTURE_DESCRIPTOR = ObjC.cls("MTL4IndirectInstanceAccelerationStructureDescriptor");

    private static final long INSTANCE_COUNT_BUFFER = ObjC.sel("instanceCountBuffer");
    private static final long INSTANCE_DESCRIPTOR_BUFFER = ObjC.sel("instanceDescriptorBuffer");
    private static final long INSTANCE_DESCRIPTOR_STRIDE = ObjC.sel("instanceDescriptorStride");
    private static final long INSTANCE_DESCRIPTOR_TYPE = ObjC.sel("instanceDescriptorType");
    private static final long INSTANCE_TRANSFORMATION_MATRIX_LAYOUT = ObjC.sel("instanceTransformationMatrixLayout");
    private static final long MAX_INSTANCE_COUNT = ObjC.sel("maxInstanceCount");
    private static final long MAX_MOTION_TRANSFORM_COUNT = ObjC.sel("maxMotionTransformCount");
    private static final long MOTION_TRANSFORM_BUFFER = ObjC.sel("motionTransformBuffer");
    private static final long MOTION_TRANSFORM_COUNT_BUFFER = ObjC.sel("motionTransformCountBuffer");
    private static final long MOTION_TRANSFORM_STRIDE = ObjC.sel("motionTransformStride");
    private static final long MOTION_TRANSFORM_TYPE = ObjC.sel("motionTransformType");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_INSTANCE_COUNT_BUFFER = ObjC.sel("setInstanceCountBuffer:");
    private static final long SET_INSTANCE_DESCRIPTOR_BUFFER = ObjC.sel("setInstanceDescriptorBuffer:");
    private static final long SET_INSTANCE_DESCRIPTOR_STRIDE = ObjC.sel("setInstanceDescriptorStride:");
    private static final long SET_INSTANCE_DESCRIPTOR_TYPE = ObjC.sel("setInstanceDescriptorType:");
    private static final long SET_INSTANCE_TRANSFORMATION_MATRIX_LAYOUT = ObjC.sel("setInstanceTransformationMatrixLayout:");
    private static final long SET_MAX_INSTANCE_COUNT = ObjC.sel("setMaxInstanceCount:");
    private static final long SET_MAX_MOTION_TRANSFORM_COUNT = ObjC.sel("setMaxMotionTransformCount:");
    private static final long SET_MOTION_TRANSFORM_BUFFER = ObjC.sel("setMotionTransformBuffer:");
    private static final long SET_MOTION_TRANSFORM_COUNT_BUFFER = ObjC.sel("setMotionTransformCountBuffer:");
    private static final long SET_MOTION_TRANSFORM_STRIDE = ObjC.sel("setMotionTransformStride:");
    private static final long SET_MOTION_TRANSFORM_TYPE = ObjC.sel("setMotionTransformType:");

    private static final MethodHandle RANGE = handle(null, MTL4BufferRange.LAYOUT);
    private static final MethodHandle R = structHandle(MTL4BufferRange.LAYOUT);

    private MTL4IndirectInstanceAccelerationStructureDescriptor(long id) {
        super(id);
    }

    public static MTL4IndirectInstanceAccelerationStructureDescriptor of(long id) {
        return new MTL4IndirectInstanceAccelerationStructureDescriptor(id);
    }

    public static MTL4IndirectInstanceAccelerationStructureDescriptor new_() {
        return new MTL4IndirectInstanceAccelerationStructureDescriptor(
                sendPtr(MTL_4_INDIRECT_INSTANCE_ACCELERATION_STRUCTURE_DESCRIPTOR, NEW));
    }

    @SneakyThrows
    public MemorySegment instanceDescriptorBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, INSTANCE_DESCRIPTOR_BUFFER);
    }

    @SneakyThrows
    public void setInstanceDescriptorBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, SET_INSTANCE_DESCRIPTOR_BUFFER, buffer);
    }

    public long instanceDescriptorStride() {
        return sendLong(id, INSTANCE_DESCRIPTOR_STRIDE);
    }

    @SneakyThrows
    public void setInstanceDescriptorStride(long stride) {
        L.invokeExact(id, SET_INSTANCE_DESCRIPTOR_STRIDE, stride);
    }

    public long maxInstanceCount() {
        return sendLong(id, MAX_INSTANCE_COUNT);
    }

    @SneakyThrows
    public void setMaxInstanceCount(long count) {
        L.invokeExact(id, SET_MAX_INSTANCE_COUNT, count);
    }

    @SneakyThrows
    public MemorySegment instanceCountBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, INSTANCE_COUNT_BUFFER);
    }

    @SneakyThrows
    public void setInstanceCountBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, SET_INSTANCE_COUNT_BUFFER, buffer);
    }

    public long instanceDescriptorType() {
        return sendLong(id, INSTANCE_DESCRIPTOR_TYPE);
    }

    @SneakyThrows
    public void setInstanceDescriptorType(long type) {
        L.invokeExact(id, SET_INSTANCE_DESCRIPTOR_TYPE, type);
    }

    @SneakyThrows
    public MemorySegment motionTransformBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, MOTION_TRANSFORM_BUFFER);
    }

    @SneakyThrows
    public void setMotionTransformBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, SET_MOTION_TRANSFORM_BUFFER, buffer);
    }

    public long maxMotionTransformCount() {
        return sendLong(id, MAX_MOTION_TRANSFORM_COUNT);
    }

    @SneakyThrows
    public void setMaxMotionTransformCount(long count) {
        L.invokeExact(id, SET_MAX_MOTION_TRANSFORM_COUNT, count);
    }

    @SneakyThrows
    public MemorySegment motionTransformCountBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, MOTION_TRANSFORM_COUNT_BUFFER);
    }

    @SneakyThrows
    public void setMotionTransformCountBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, SET_MOTION_TRANSFORM_COUNT_BUFFER, buffer);
    }

    public long instanceTransformationMatrixLayout() {
        return sendLong(id, INSTANCE_TRANSFORMATION_MATRIX_LAYOUT);
    }

    @SneakyThrows
    public void setInstanceTransformationMatrixLayout(long layout) {
        L.invokeExact(id, SET_INSTANCE_TRANSFORMATION_MATRIX_LAYOUT, layout);
    }

    public long motionTransformType() {
        return sendLong(id, MOTION_TRANSFORM_TYPE);
    }

    @SneakyThrows
    public void setMotionTransformType(long type) {
        L.invokeExact(id, SET_MOTION_TRANSFORM_TYPE, type);
    }

    public long motionTransformStride() {
        return sendLong(id, MOTION_TRANSFORM_STRIDE);
    }

    @SneakyThrows
    public void setMotionTransformStride(long stride) {
        L.invokeExact(id, SET_MOTION_TRANSFORM_STRIDE, stride);
    }
}
