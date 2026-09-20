package dev.dov.metalj.metal4.raytracing;

import dev.dov.metalj.metal4.MTL4BufferRange;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4AccelerationStructureGeometryDescriptor extends NSObject {
    protected static final MethodHandle L = handle(null, ObjC.LONG);
    protected static final MethodHandle B = handle(null, ObjC.BOOL);
    protected static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle RANGE = handle(null, MTL4BufferRange.LAYOUT);
    private static final MethodHandle R = structHandle(MTL4BufferRange.LAYOUT);

    protected MTL4AccelerationStructureGeometryDescriptor(long id) {
        super(id);
    }

    public static MTL4AccelerationStructureGeometryDescriptor of(long id) {
        return new MTL4AccelerationStructureGeometryDescriptor(id);
    }

    public static MTL4AccelerationStructureGeometryDescriptor new_() {
        return new MTL4AccelerationStructureGeometryDescriptor(
                sendPtr(ObjC.cls("MTL4AccelerationStructureGeometryDescriptor"), "new"));
    }

    public long intersectionFunctionTableOffset() {
        return sendLong(id, "intersectionFunctionTableOffset");
    }

    @SneakyThrows
    public void setIntersectionFunctionTableOffset(long offset) {
        L.invokeExact(id, ObjC.sel("setIntersectionFunctionTableOffset:"), offset);
    }

    public boolean opaque() {
        return sendBool(id, "opaque");
    }

    @SneakyThrows
    public void setOpaque(boolean opaque) {
        B.invokeExact(id, ObjC.sel("setOpaque:"), opaque);
    }

    public boolean allowDuplicateIntersectionFunctionInvocation() {
        return sendBool(id, "allowDuplicateIntersectionFunctionInvocation");
    }

    @SneakyThrows
    public void setAllowDuplicateIntersectionFunctionInvocation(boolean allow) {
        B.invokeExact(id, ObjC.sel("setAllowDuplicateIntersectionFunctionInvocation:"), allow);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    @SneakyThrows
    public MemorySegment primitiveDataBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, ObjC.sel("primitiveDataBuffer"));
    }

    @SneakyThrows
    public void setPrimitiveDataBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, ObjC.sel("setPrimitiveDataBuffer:"), buffer);
    }

    public long primitiveDataStride() {
        return sendLong(id, "primitiveDataStride");
    }

    @SneakyThrows
    public void setPrimitiveDataStride(long stride) {
        L.invokeExact(id, ObjC.sel("setPrimitiveDataStride:"), stride);
    }

    public long primitiveDataElementSize() {
        return sendLong(id, "primitiveDataElementSize");
    }

    @SneakyThrows
    public void setPrimitiveDataElementSize(long size) {
        L.invokeExact(id, ObjC.sel("setPrimitiveDataElementSize:"), size);
    }
}
