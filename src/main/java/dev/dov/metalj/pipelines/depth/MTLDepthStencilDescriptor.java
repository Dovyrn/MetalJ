package dev.dov.metalj.pipelines.depth;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLDepthStencilDescriptor extends NSObject {
    private static final long MTL_DEPTH_STENCIL_DESCRIPTOR = ObjC.cls("MTLDepthStencilDescriptor");

    private static final long BACK_FACE_STENCIL = ObjC.sel("backFaceStencil");
    private static final long DEPTH_COMPARE_FUNCTION = ObjC.sel("depthCompareFunction");
    private static final long FRONT_FACE_STENCIL = ObjC.sel("frontFaceStencil");
    private static final long IS_DEPTH_WRITE_ENABLED = ObjC.sel("isDepthWriteEnabled");
    private static final long LABEL = ObjC.sel("label");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_BACK_FACE_STENCIL = ObjC.sel("setBackFaceStencil:");
    private static final long SET_DEPTH_COMPARE_FUNCTION = ObjC.sel("setDepthCompareFunction:");
    private static final long SET_DEPTH_WRITE_ENABLED = ObjC.sel("setDepthWriteEnabled:");
    private static final long SET_FRONT_FACE_STENCIL = ObjC.sel("setFrontFaceStencil:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");

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
        return new MTLDepthStencilDescriptor(sendPtr(MTL_DEPTH_STENCIL_DESCRIPTOR, NEW));
    }

    public long depthCompareFunction() {
        return sendLong(id, DEPTH_COMPARE_FUNCTION);
    }

    @SneakyThrows
    public void setDepthCompareFunction(long depthCompareFunction) {
        L.invokeExact(id, SET_DEPTH_COMPARE_FUNCTION, depthCompareFunction);
    }

    public boolean isDepthWriteEnabled() {
        return sendBool(id, IS_DEPTH_WRITE_ENABLED);
    }

    @SneakyThrows
    public void setDepthWriteEnabled(boolean depthWriteEnabled) {
        B.invokeExact(id, SET_DEPTH_WRITE_ENABLED, depthWriteEnabled);
    }

    public MTLStencilDescriptor frontFaceStencil() {
        return MTLStencilDescriptor.of(sendPtr(id, FRONT_FACE_STENCIL));
    }

    @SneakyThrows
    public void setFrontFaceStencil(MTLStencilDescriptor frontFaceStencil) {
        P.invokeExact(id, SET_FRONT_FACE_STENCIL, frontFaceStencil.getId());
    }

    public MTLStencilDescriptor backFaceStencil() {
        return MTLStencilDescriptor.of(sendPtr(id, BACK_FACE_STENCIL));
    }

    @SneakyThrows
    public void setBackFaceStencil(MTLStencilDescriptor backFaceStencil) {
        P.invokeExact(id, SET_BACK_FACE_STENCIL, backFaceStencil.getId());
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }
}
