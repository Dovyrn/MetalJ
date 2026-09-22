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
    private static final long MTL_4_ACCELERATION_STRUCTURE_GEOMETRY_DESCRIPTOR = ObjC.cls("MTL4AccelerationStructureGeometryDescriptor");

    private static final long ALLOW_DUPLICATE_INTERSECTION_FUNCTION_INVOCATION = ObjC.sel("allowDuplicateIntersectionFunctionInvocation");
    private static final long INTERSECTION_FUNCTION_TABLE_OFFSET = ObjC.sel("intersectionFunctionTableOffset");
    private static final long LABEL = ObjC.sel("label");
    private static final long NEW = ObjC.sel("new");
    private static final long OPAQUE = ObjC.sel("opaque");
    private static final long PRIMITIVE_DATA_BUFFER = ObjC.sel("primitiveDataBuffer");
    private static final long PRIMITIVE_DATA_ELEMENT_SIZE = ObjC.sel("primitiveDataElementSize");
    private static final long PRIMITIVE_DATA_STRIDE = ObjC.sel("primitiveDataStride");
    private static final long SET_ALLOW_DUPLICATE_INTERSECTION_FUNCTION_INVOCATION = ObjC.sel("setAllowDuplicateIntersectionFunctionInvocation:");
    private static final long SET_INTERSECTION_FUNCTION_TABLE_OFFSET = ObjC.sel("setIntersectionFunctionTableOffset:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_OPAQUE = ObjC.sel("setOpaque:");
    private static final long SET_PRIMITIVE_DATA_BUFFER = ObjC.sel("setPrimitiveDataBuffer:");
    private static final long SET_PRIMITIVE_DATA_ELEMENT_SIZE = ObjC.sel("setPrimitiveDataElementSize:");
    private static final long SET_PRIMITIVE_DATA_STRIDE = ObjC.sel("setPrimitiveDataStride:");

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
                sendPtr(MTL_4_ACCELERATION_STRUCTURE_GEOMETRY_DESCRIPTOR, NEW));
    }

    public long intersectionFunctionTableOffset() {
        return sendLong(id, INTERSECTION_FUNCTION_TABLE_OFFSET);
    }

    @SneakyThrows
    public void setIntersectionFunctionTableOffset(long offset) {
        L.invokeExact(id, SET_INTERSECTION_FUNCTION_TABLE_OFFSET, offset);
    }

    public boolean opaque() {
        return sendBool(id, OPAQUE);
    }

    @SneakyThrows
    public void setOpaque(boolean opaque) {
        B.invokeExact(id, SET_OPAQUE, opaque);
    }

    public boolean allowDuplicateIntersectionFunctionInvocation() {
        return sendBool(id, ALLOW_DUPLICATE_INTERSECTION_FUNCTION_INVOCATION);
    }

    @SneakyThrows
    public void setAllowDuplicateIntersectionFunctionInvocation(boolean allow) {
        B.invokeExact(id, SET_ALLOW_DUPLICATE_INTERSECTION_FUNCTION_INVOCATION, allow);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    @SneakyThrows
    public MemorySegment primitiveDataBuffer(SegmentAllocator allocator) {
        return (MemorySegment) R.invokeExact(allocator, id, PRIMITIVE_DATA_BUFFER);
    }

    @SneakyThrows
    public void setPrimitiveDataBuffer(MemorySegment buffer) {
        RANGE.invokeExact(id, SET_PRIMITIVE_DATA_BUFFER, buffer);
    }

    public long primitiveDataStride() {
        return sendLong(id, PRIMITIVE_DATA_STRIDE);
    }

    @SneakyThrows
    public void setPrimitiveDataStride(long stride) {
        L.invokeExact(id, SET_PRIMITIVE_DATA_STRIDE, stride);
    }

    public long primitiveDataElementSize() {
        return sendLong(id, PRIMITIVE_DATA_ELEMENT_SIZE);
    }

    @SneakyThrows
    public void setPrimitiveDataElementSize(long size) {
        L.invokeExact(id, SET_PRIMITIVE_DATA_ELEMENT_SIZE, size);
    }
}
