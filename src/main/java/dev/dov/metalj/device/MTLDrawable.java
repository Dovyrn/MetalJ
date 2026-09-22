package dev.dov.metalj.device;

import dev.dov.metalj.objc.Block;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLDrawable extends NSObject {
    private static final long ADD_PRESENTED_HANDLER = ObjC.sel("addPresentedHandler:");
    private static final long DRAWABLE_ID = ObjC.sel("drawableID");
    private static final long PRESENT = ObjC.sel("present");
    private static final long PRESENT_AFTER_MINIMUM_DURATION = ObjC.sel("presentAfterMinimumDuration:");
    private static final long PRESENT_AT_TIME = ObjC.sel("presentAtTime:");
    private static final long PRESENTED_TIME = ObjC.sel("presentedTime");

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
        sendVoid(id, PRESENT);
    }

    @SneakyThrows
    public void presentAtTime(double time) {
        D.invokeExact(id, PRESENT_AT_TIME, time);
    }

    @SneakyThrows
    public void presentAfterMinimumDuration(double duration) {
        D.invokeExact(id, PRESENT_AFTER_MINIMUM_DURATION, duration);
    }

    @SneakyThrows
    public void addPresentedHandler(Block block) {
        P.invokeExact(id, ADD_PRESENTED_HANDLER, block.address());
    }

    @SneakyThrows
    public double presentedTime() {
        return (double) DOUBLE.invokeExact(id, PRESENTED_TIME);
    }

    public long drawableID() {
        return sendLong(id, DRAWABLE_ID);
    }
}
