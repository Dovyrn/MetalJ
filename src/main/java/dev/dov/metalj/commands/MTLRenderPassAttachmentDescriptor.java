package dev.dov.metalj.commands;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLTexture;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPassAttachmentDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    protected MTLRenderPassAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLRenderPassAttachmentDescriptor of(long id) {
        return new MTLRenderPassAttachmentDescriptor(id);
    }

    public MTLTexture texture() {
        return MTLTexture.of(sendPtr(id, "texture"));
    }

    @SneakyThrows
    public void setTexture(MTLTexture texture) {
        L.invokeExact(id, ObjC.sel("setTexture:"), texture.getId());
    }

    @SneakyThrows
    public void setLevel(long level) {
        L.invokeExact(id, ObjC.sel("setLevel:"), level);
    }

    @SneakyThrows
    public void setSlice(long slice) {
        L.invokeExact(id, ObjC.sel("setSlice:"), slice);
    }

    @SneakyThrows
    public void setDepthPlane(long depthPlane) {
        L.invokeExact(id, ObjC.sel("setDepthPlane:"), depthPlane);
    }

    @SneakyThrows
    public void setResolveTexture(MTLTexture resolveTexture) {
        L.invokeExact(id, ObjC.sel("setResolveTexture:"), resolveTexture.getId());
    }

    @SneakyThrows
    public void setResolveLevel(long resolveLevel) {
        L.invokeExact(id, ObjC.sel("setResolveLevel:"), resolveLevel);
    }

    @SneakyThrows
    public void setResolveSlice(long resolveSlice) {
        L.invokeExact(id, ObjC.sel("setResolveSlice:"), resolveSlice);
    }

    @SneakyThrows
    public void setResolveDepthPlane(long resolveDepthPlane) {
        L.invokeExact(id, ObjC.sel("setResolveDepthPlane:"), resolveDepthPlane);
    }

    @SneakyThrows
    public void setLoadAction(long loadAction) {
        L.invokeExact(id, ObjC.sel("setLoadAction:"), loadAction);
    }

    @SneakyThrows
    public void setStoreAction(long storeAction) {
        L.invokeExact(id, ObjC.sel("setStoreAction:"), storeAction);
    }

    @SneakyThrows
    public void setStoreActionOptions(long storeActionOptions) {
        L.invokeExact(id, ObjC.sel("setStoreActionOptions:"), storeActionOptions);
    }
}
