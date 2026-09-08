package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLAccelerationStructureGeometryDescriptor extends MTLAccelerationStructureDescriptor {
    private static final MethodHandle B = handle(null, ObjC.BOOL);

    protected MTLAccelerationStructureGeometryDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructureGeometryDescriptor of(long id) {
        return new MTLAccelerationStructureGeometryDescriptor(id);
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

    public MTLBuffer primitiveDataBuffer() {
        return MTLBuffer.of(sendPtr(id, "primitiveDataBuffer"));
    }

    @SneakyThrows
    public void setPrimitiveDataBuffer(MTLBuffer buffer) {
        P.invokeExact(id, ObjC.sel("setPrimitiveDataBuffer:"), buffer.getId());
    }

    public long primitiveDataBufferOffset() {
        return sendLong(id, "primitiveDataBufferOffset");
    }

    @SneakyThrows
    public void setPrimitiveDataBufferOffset(long offset) {
        L.invokeExact(id, ObjC.sel("setPrimitiveDataBufferOffset:"), offset);
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
