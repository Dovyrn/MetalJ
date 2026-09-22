package dev.dov.metalj.pipelines.compute;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLBufferLayoutDescriptor extends NSObject {
    private static final long SET_STEP_FUNCTION = ObjC.sel("setStepFunction:");
    private static final long SET_STEP_RATE = ObjC.sel("setStepRate:");
    private static final long SET_STRIDE = ObjC.sel("setStride:");
    private static final long STEP_FUNCTION = ObjC.sel("stepFunction");
    private static final long STEP_RATE = ObjC.sel("stepRate");
    private static final long STRIDE = ObjC.sel("stride");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLBufferLayoutDescriptor(long id) {
        super(id);
    }

    public static MTLBufferLayoutDescriptor of(long id) {
        return new MTLBufferLayoutDescriptor(id);
    }

    public long stride() {
        return sendLong(id, STRIDE);
    }

    @SneakyThrows
    public void setStride(long stride) {
        L.invokeExact(id, SET_STRIDE, stride);
    }

    public long stepFunction() {
        return sendLong(id, STEP_FUNCTION);
    }

    @SneakyThrows
    public void setStepFunction(long function) {
        L.invokeExact(id, SET_STEP_FUNCTION, function);
    }

    public long stepRate() {
        return sendLong(id, STEP_RATE);
    }

    @SneakyThrows
    public void setStepRate(long rate) {
        L.invokeExact(id, SET_STEP_RATE, rate);
    }
}
