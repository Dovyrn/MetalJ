package dev.dov.metalj.pipelines.compute;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLBufferLayoutDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLBufferLayoutDescriptor(long id) {
        super(id);
    }

    public static MTLBufferLayoutDescriptor of(long id) {
        return new MTLBufferLayoutDescriptor(id);
    }

    public long stride() {
        return sendLong(id, "stride");
    }

    @SneakyThrows
    public void setStride(long stride) {
        L.invokeExact(id, ObjC.sel("setStride:"), stride);
    }

    public long stepFunction() {
        return sendLong(id, "stepFunction");
    }

    @SneakyThrows
    public void setStepFunction(long function) {
        L.invokeExact(id, ObjC.sel("setStepFunction:"), function);
    }

    public long stepRate() {
        return sendLong(id, "stepRate");
    }

    @SneakyThrows
    public void setStepRate(long rate) {
        L.invokeExact(id, ObjC.sel("setStepRate:"), rate);
    }
}
