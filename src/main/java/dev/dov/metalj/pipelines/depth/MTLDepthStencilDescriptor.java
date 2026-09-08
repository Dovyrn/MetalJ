package dev.dov.metalj.pipelines.depth;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLDepthStencilDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLDepthStencilDescriptor(long id) {
        super(id);
    }

    public static MTLDepthStencilDescriptor of(long id) {
        return new MTLDepthStencilDescriptor(id);
    }

    public static MTLDepthStencilDescriptor new_() {
        return new MTLDepthStencilDescriptor(sendPtr(ObjC.cls("MTLDepthStencilDescriptor"), "new"));
    }

    public long depthCompareFunction() {
        return sendLong(id, "depthCompareFunction");
    }

    @SneakyThrows
    public void setDepthCompareFunction(long depthCompareFunction) {
        L.invokeExact(id, ObjC.sel("setDepthCompareFunction:"), depthCompareFunction);
    }

    public boolean isDepthWriteEnabled() {
        return sendBool(id, "isDepthWriteEnabled");
    }

    @SneakyThrows
    public void setDepthWriteEnabled(boolean depthWriteEnabled) {
        B.invokeExact(id, ObjC.sel("setDepthWriteEnabled:"), depthWriteEnabled);
    }

    public MTLStencilDescriptor frontFaceStencil() {
        return MTLStencilDescriptor.of(sendPtr(id, "frontFaceStencil"));
    }

    @SneakyThrows
    public void setFrontFaceStencil(MTLStencilDescriptor frontFaceStencil) {
        P.invokeExact(id, ObjC.sel("setFrontFaceStencil:"), frontFaceStencil.getId());
    }

    public MTLStencilDescriptor backFaceStencil() {
        return MTLStencilDescriptor.of(sendPtr(id, "backFaceStencil"));
    }

    @SneakyThrows
    public void setBackFaceStencil(MTLStencilDescriptor backFaceStencil) {
        P.invokeExact(id, ObjC.sel("setBackFaceStencil:"), backFaceStencil.getId());
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }
}
