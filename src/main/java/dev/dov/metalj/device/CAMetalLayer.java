package dev.dov.metalj.device;

import dev.dov.metalj.objc.CGSize;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class CAMetalLayer extends NSObject {
    private static final long CA_METAL_LAYER = ObjC.cls("CAMetalLayer");

    private static final long LAYER = ObjC.sel("layer");
    private static final long NEXT_DRAWABLE = ObjC.sel("nextDrawable");
    private static final long SET_CONTENTS_SCALE = ObjC.sel("setContentsScale:");
    private static final long SET_DEVICE = ObjC.sel("setDevice:");
    private static final long SET_DISPLAY_SYNC_ENABLED = ObjC.sel("setDisplaySyncEnabled:");
    private static final long SET_DRAWABLE_SIZE = ObjC.sel("setDrawableSize:");
    private static final long SET_FRAMEBUFFER_ONLY = ObjC.sel("setFramebufferOnly:");
    private static final long SET_MAXIMUM_DRAWABLE_COUNT = ObjC.sel("setMaximumDrawableCount:");
    private static final long SET_OPAQUE = ObjC.sel("setOpaque:");
    private static final long SET_PIXEL_FORMAT = ObjC.sel("setPixelFormat:");

    private static final MethodHandle SET_PTR = handle(null, ObjC.PTR);
    private static final MethodHandle SET_LONG = handle(null, ObjC.LONG);
    private static final MethodHandle SET_BOOL = handle(null, ObjC.BOOL);
    private static final MethodHandle SET_DOUBLE = handle(null, ObjC.DOUBLE);
    private static final MethodHandle SET_SIZE = handle(null, dev.dov.metalj.objc.CGSize.LAYOUT);

    private CAMetalLayer(long id) {
        super(id);
    }

    public static CAMetalLayer of(long id) {
        return new CAMetalLayer(id);
    }

    public static CAMetalLayer layer() {
        return new CAMetalLayer(owned(() -> sendPtr(CA_METAL_LAYER, LAYER)));
    }

    @SneakyThrows
    public void setDevice(MTLDevice device) {
        SET_PTR.invokeExact(id, SET_DEVICE, device.getId());
    }

    @SneakyThrows
    public void setPixelFormat(long format) {
        SET_LONG.invokeExact(id, SET_PIXEL_FORMAT, format);
    }

    @SneakyThrows
    public void setOpaque(boolean opaque) {
        SET_BOOL.invokeExact(id, SET_OPAQUE, opaque);
    }

    @SneakyThrows
    public void setFramebufferOnly(boolean only) {
        SET_BOOL.invokeExact(id, SET_FRAMEBUFFER_ONLY, only);
    }

    @SneakyThrows
    public void setDisplaySyncEnabled(boolean enabled) {
        SET_BOOL.invokeExact(id, SET_DISPLAY_SYNC_ENABLED, enabled);
    }

    @SneakyThrows
    public void setContentsScale(double scale) {
        SET_DOUBLE.invokeExact(id, SET_CONTENTS_SCALE, scale);
    }

    @SneakyThrows
    public void setMaximumDrawableCount(long count) {
        SET_LONG.invokeExact(id, SET_MAXIMUM_DRAWABLE_COUNT, count);
    }

    @SneakyThrows
    public void setDrawableSize(MemorySegment size) {
        SET_SIZE.invokeExact(id, SET_DRAWABLE_SIZE, size);
    }

    public CAMetalDrawable nextDrawable() {
        return CAMetalDrawable.of(owned(() -> sendPtr(id, NEXT_DRAWABLE)));
    }
}
