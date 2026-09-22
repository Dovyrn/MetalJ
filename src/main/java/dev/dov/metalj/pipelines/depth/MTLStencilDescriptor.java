package dev.dov.metalj.pipelines.depth;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLStencilDescriptor extends NSObject {
    private static final long MTL_STENCIL_DESCRIPTOR = ObjC.cls("MTLStencilDescriptor");

    private static final long DEPTH_FAILURE_OPERATION = ObjC.sel("depthFailureOperation");
    private static final long DEPTH_STENCIL_PASS_OPERATION = ObjC.sel("depthStencilPassOperation");
    private static final long NEW = ObjC.sel("new");
    private static final long READ_MASK = ObjC.sel("readMask");
    private static final long SET_DEPTH_FAILURE_OPERATION = ObjC.sel("setDepthFailureOperation:");
    private static final long SET_DEPTH_STENCIL_PASS_OPERATION = ObjC.sel("setDepthStencilPassOperation:");
    private static final long SET_READ_MASK = ObjC.sel("setReadMask:");
    private static final long SET_STENCIL_COMPARE_FUNCTION = ObjC.sel("setStencilCompareFunction:");
    private static final long SET_STENCIL_FAILURE_OPERATION = ObjC.sel("setStencilFailureOperation:");
    private static final long SET_WRITE_MASK = ObjC.sel("setWriteMask:");
    private static final long STENCIL_COMPARE_FUNCTION = ObjC.sel("stencilCompareFunction");
    private static final long STENCIL_FAILURE_OPERATION = ObjC.sel("stencilFailureOperation");
    private static final long WRITE_MASK = ObjC.sel("writeMask");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle I = handle(null, ObjC.INT);

    private MTLStencilDescriptor(long id) {
        super(id);
    }

    public static MTLStencilDescriptor of(long id) {
        return new MTLStencilDescriptor(id);
    }

    public static MTLStencilDescriptor new_() {
        return new MTLStencilDescriptor(sendPtr(MTL_STENCIL_DESCRIPTOR, NEW));
    }

    public long stencilCompareFunction() {
        return sendLong(id, STENCIL_COMPARE_FUNCTION);
    }

    @SneakyThrows
    public void setStencilCompareFunction(long stencilCompareFunction) {
        L.invokeExact(id, SET_STENCIL_COMPARE_FUNCTION, stencilCompareFunction);
    }

    public long stencilFailureOperation() {
        return sendLong(id, STENCIL_FAILURE_OPERATION);
    }

    @SneakyThrows
    public void setStencilFailureOperation(long stencilFailureOperation) {
        L.invokeExact(id, SET_STENCIL_FAILURE_OPERATION, stencilFailureOperation);
    }

    public long depthFailureOperation() {
        return sendLong(id, DEPTH_FAILURE_OPERATION);
    }

    @SneakyThrows
    public void setDepthFailureOperation(long depthFailureOperation) {
        L.invokeExact(id, SET_DEPTH_FAILURE_OPERATION, depthFailureOperation);
    }

    public long depthStencilPassOperation() {
        return sendLong(id, DEPTH_STENCIL_PASS_OPERATION);
    }

    @SneakyThrows
    public void setDepthStencilPassOperation(long depthStencilPassOperation) {
        L.invokeExact(id, SET_DEPTH_STENCIL_PASS_OPERATION, depthStencilPassOperation);
    }

    public long readMask() {
        return sendLong(id, READ_MASK) & 0xffffffffL;
    }

    @SneakyThrows
    public void setReadMask(int readMask) {
        I.invokeExact(id, SET_READ_MASK, readMask);
    }

    public long writeMask() {
        return sendLong(id, WRITE_MASK) & 0xffffffffL;
    }

    @SneakyThrows
    public void setWriteMask(int writeMask) {
        I.invokeExact(id, SET_WRITE_MASK, writeMask);
    }
}
