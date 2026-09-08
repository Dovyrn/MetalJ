package dev.dov.metalj.device;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class CAMetalLayer extends NSObject {
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
        return new CAMetalLayer(sendPtr(ObjC.cls("CAMetalLayer"), "layer"));
    }

    @SneakyThrows
    public void setDevice(MTLDevice device) {
        SET_PTR.invokeExact(id, ObjC.sel("setDevice:"), device.getId());
    }

    @SneakyThrows
    public void setPixelFormat(long format) {
        SET_LONG.invokeExact(id, ObjC.sel("setPixelFormat:"), format);
    }

    @SneakyThrows
    public void setFramebufferOnly(boolean only) {
        SET_BOOL.invokeExact(id, ObjC.sel("setFramebufferOnly:"), only);
    }

    @SneakyThrows
    public void setDisplaySyncEnabled(boolean enabled) {
        SET_BOOL.invokeExact(id, ObjC.sel("setDisplaySyncEnabled:"), enabled);
    }

    @SneakyThrows
    public void setContentsScale(double scale) {
        SET_DOUBLE.invokeExact(id, ObjC.sel("setContentsScale:"), scale);
    }

    @SneakyThrows
    public void setMaximumDrawableCount(long count) {
        SET_LONG.invokeExact(id, ObjC.sel("setMaximumDrawableCount:"), count);
    }

    @SneakyThrows
    public void setDrawableSize(MemorySegment size) {
        SET_SIZE.invokeExact(id, ObjC.sel("setDrawableSize:"), size);
    }

    public CAMetalDrawable nextDrawable() {
        return CAMetalDrawable.of(sendPtr(id, "nextDrawable"));
    }
}
