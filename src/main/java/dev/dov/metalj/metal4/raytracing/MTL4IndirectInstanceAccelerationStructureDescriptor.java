package dev.dov.metalj.metal4.raytracing;

import dev.dov.metalj.metal4.MTL4BufferRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4IndirectInstanceAccelerationStructureDescriptor extends MTL4AccelerationStructureDescriptor {
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
                sendPtr(ObjC.cls("MTL4IndirectInstanceAccelerationStructureDescriptor"), "new"));
    }

    @SneakyThrows
    public MemorySegment instanceDescriptorBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, ObjC.sel("instanceDescriptorBuffer"));
    }

    @SneakyThrows
    public void setInstanceDescriptorBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, ObjC.sel("setInstanceDescriptorBuffer:"), buffer);
    }

    public long instanceDescriptorStride() {
        return sendLong(id, "instanceDescriptorStride");
    }

    @SneakyThrows
    public void setInstanceDescriptorStride(long stride) {
        L.invokeExact(id, ObjC.sel("setInstanceDescriptorStride:"), stride);
    }

    public long maxInstanceCount() {
        return sendLong(id, "maxInstanceCount");
    }

    @SneakyThrows
    public void setMaxInstanceCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxInstanceCount:"), count);
    }

    @SneakyThrows
    public MemorySegment instanceCountBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, ObjC.sel("instanceCountBuffer"));
    }

    @SneakyThrows
    public void setInstanceCountBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, ObjC.sel("setInstanceCountBuffer:"), buffer);
    }

    public long instanceDescriptorType() {
        return sendLong(id, "instanceDescriptorType");
    }

    @SneakyThrows
    public void setInstanceDescriptorType(long type) {
        L.invokeExact(id, ObjC.sel("setInstanceDescriptorType:"), type);
    }

    @SneakyThrows
    public MemorySegment motionTransformBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, ObjC.sel("motionTransformBuffer"));
    }

    @SneakyThrows
    public void setMotionTransformBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, ObjC.sel("setMotionTransformBuffer:"), buffer);
    }

    public long maxMotionTransformCount() {
        return sendLong(id, "maxMotionTransformCount");
    }

    @SneakyThrows
    public void setMaxMotionTransformCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxMotionTransformCount:"), count);
    }

    @SneakyThrows
    public MemorySegment motionTransformCountBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, ObjC.sel("motionTransformCountBuffer"));
    }

    @SneakyThrows
    public void setMotionTransformCountBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, ObjC.sel("setMotionTransformCountBuffer:"), buffer);
    }

    public long instanceTransformationMatrixLayout() {
        return sendLong(id, "instanceTransformationMatrixLayout");
    }

    @SneakyThrows
    public void setInstanceTransformationMatrixLayout(long layout) {
        L.invokeExact(id, ObjC.sel("setInstanceTransformationMatrixLayout:"), layout);
    }

    public long motionTransformType() {
        return sendLong(id, "motionTransformType");
    }

    @SneakyThrows
    public void setMotionTransformType(long type) {
        L.invokeExact(id, ObjC.sel("setMotionTransformType:"), type);
    }

    public long motionTransformStride() {
        return sendLong(id, "motionTransformStride");
    }

    @SneakyThrows
    public void setMotionTransformStride(long stride) {
        L.invokeExact(id, ObjC.sel("setMotionTransformStride:"), stride);
    }
}
