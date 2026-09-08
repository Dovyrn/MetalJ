package dev.dov.metalj.pipelines.depth;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLStencilDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle I = handle(null, ObjC.INT);

    private MTLStencilDescriptor(long id) {
        super(id);
    }

    public static MTLStencilDescriptor of(long id) {
        return new MTLStencilDescriptor(id);
    }

    public static MTLStencilDescriptor new_() {
        return new MTLStencilDescriptor(sendPtr(ObjC.cls("MTLStencilDescriptor"), "new"));
    }

    public long stencilCompareFunction() {
        return sendLong(id, "stencilCompareFunction");
    }

    @SneakyThrows
    public void setStencilCompareFunction(long stencilCompareFunction) {
        L.invokeExact(id, ObjC.sel("setStencilCompareFunction:"), stencilCompareFunction);
    }

    public long stencilFailureOperation() {
        return sendLong(id, "stencilFailureOperation");
    }

    @SneakyThrows
    public void setStencilFailureOperation(long stencilFailureOperation) {
        L.invokeExact(id, ObjC.sel("setStencilFailureOperation:"), stencilFailureOperation);
    }

    public long depthFailureOperation() {
        return sendLong(id, "depthFailureOperation");
    }

    @SneakyThrows
    public void setDepthFailureOperation(long depthFailureOperation) {
        L.invokeExact(id, ObjC.sel("setDepthFailureOperation:"), depthFailureOperation);
    }

    public long depthStencilPassOperation() {
        return sendLong(id, "depthStencilPassOperation");
    }

    @SneakyThrows
    public void setDepthStencilPassOperation(long depthStencilPassOperation) {
        L.invokeExact(id, ObjC.sel("setDepthStencilPassOperation:"), depthStencilPassOperation);
    }

    public long readMask() {
        return sendLong(id, "readMask") & 0xffffffffL;
    }

    @SneakyThrows
    public void setReadMask(int readMask) {
        I.invokeExact(id, ObjC.sel("setReadMask:"), readMask);
    }

    public long writeMask() {
        return sendLong(id, "writeMask") & 0xffffffffL;
    }

    @SneakyThrows
    public void setWriteMask(int writeMask) {
        I.invokeExact(id, ObjC.sel("setWriteMask:"), writeMask);
    }
}
