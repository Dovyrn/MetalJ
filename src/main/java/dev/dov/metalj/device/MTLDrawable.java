package dev.dov.metalj.device;

import dev.dov.metalj.objc.Block;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLDrawable extends NSObject {
    private static final MethodHandle D = handle(null, ObjC.DOUBLE);
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle DOUBLE = handle(ObjC.DOUBLE);

    protected MTLDrawable(long id) {
        super(id);
    }

    public static MTLDrawable of(long id) {
        return new MTLDrawable(id);
    }

    public void present() {
        sendVoid(id, "present");
    }

    @SneakyThrows
    public void presentAtTime(double time) {
        D.invokeExact(id, ObjC.sel("presentAtTime:"), time);
    }

    @SneakyThrows
    public void presentAfterMinimumDuration(double duration) {
        D.invokeExact(id, ObjC.sel("presentAfterMinimumDuration:"), duration);
    }

    @SneakyThrows
    public void addPresentedHandler(Block block) {
        P.invokeExact(id, ObjC.sel("addPresentedHandler:"), block.address());
    }

    @SneakyThrows
    public double presentedTime() {
        return (double) DOUBLE.invokeExact(id, ObjC.sel("presentedTime"));
    }

    public long drawableID() {
        return sendLong(id, "drawableID");
    }
}
