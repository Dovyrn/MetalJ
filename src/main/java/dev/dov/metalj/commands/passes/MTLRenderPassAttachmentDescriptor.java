package dev.dov.metalj.commands.passes;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.textures.MTLTexture;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPassAttachmentDescriptor extends NSObject {
    private static final long SET_DEPTH_PLANE = ObjC.sel("setDepthPlane:");
    private static final long SET_LEVEL = ObjC.sel("setLevel:");
    private static final long SET_LOAD_ACTION = ObjC.sel("setLoadAction:");
    private static final long SET_RESOLVE_DEPTH_PLANE = ObjC.sel("setResolveDepthPlane:");
    private static final long SET_RESOLVE_LEVEL = ObjC.sel("setResolveLevel:");
    private static final long SET_RESOLVE_SLICE = ObjC.sel("setResolveSlice:");
    private static final long SET_RESOLVE_TEXTURE = ObjC.sel("setResolveTexture:");
    private static final long SET_SLICE = ObjC.sel("setSlice:");
    private static final long SET_STORE_ACTION = ObjC.sel("setStoreAction:");
    private static final long SET_STORE_ACTION_OPTIONS = ObjC.sel("setStoreActionOptions:");
    private static final long SET_TEXTURE = ObjC.sel("setTexture:");
    private static final long TEXTURE = ObjC.sel("texture");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    protected MTLRenderPassAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLRenderPassAttachmentDescriptor of(long id) {
        return new MTLRenderPassAttachmentDescriptor(id);
    }

    public MTLTexture texture() {
        return MTLTexture.of(sendPtr(id, TEXTURE));
    }

    @SneakyThrows
    public void setTexture(MTLTexture texture) {
        L.invokeExact(id, SET_TEXTURE, texture.getId());
    }

    @SneakyThrows
    public void setLevel(long level) {
        L.invokeExact(id, SET_LEVEL, level);
    }

    @SneakyThrows
    public void setSlice(long slice) {
        L.invokeExact(id, SET_SLICE, slice);
    }

    @SneakyThrows
    public void setDepthPlane(long depthPlane) {
        L.invokeExact(id, SET_DEPTH_PLANE, depthPlane);
    }

    @SneakyThrows
    public void setResolveTexture(MTLTexture resolveTexture) {
        L.invokeExact(id, SET_RESOLVE_TEXTURE, resolveTexture.getId());
    }

    @SneakyThrows
    public void setResolveLevel(long resolveLevel) {
        L.invokeExact(id, SET_RESOLVE_LEVEL, resolveLevel);
    }

    @SneakyThrows
    public void setResolveSlice(long resolveSlice) {
        L.invokeExact(id, SET_RESOLVE_SLICE, resolveSlice);
    }

    @SneakyThrows
    public void setResolveDepthPlane(long resolveDepthPlane) {
        L.invokeExact(id, SET_RESOLVE_DEPTH_PLANE, resolveDepthPlane);
    }

    @SneakyThrows
    public void setLoadAction(long loadAction) {
        L.invokeExact(id, SET_LOAD_ACTION, loadAction);
    }

    @SneakyThrows
    public void setStoreAction(long storeAction) {
        L.invokeExact(id, SET_STORE_ACTION, storeAction);
    }

    @SneakyThrows
    public void setStoreActionOptions(long storeActionOptions) {
        L.invokeExact(id, SET_STORE_ACTION_OPTIONS, storeActionOptions);
    }
}
