package dev.dov.metalj.objc;

import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class NSNumber extends NSObject {
    private static final MethodHandle P_F = handle(ObjC.PTR, ObjC.FLOAT);
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);

    private NSNumber(long id) {
        super(id);
    }

    public static NSNumber of(long id) {
        return new NSNumber(id);
    }

    @SneakyThrows
    public static NSNumber numberWithFloat(float value) {
        return new NSNumber(owned(() -> (long) P_F.invokeExact(ObjC.cls("NSNumber"), ObjC.sel("numberWithFloat:"), value)));
    }

    @SneakyThrows
    public static NSNumber numberWithUnsignedInteger(long value) {
        return new NSNumber(owned(() -> (long) P_L.invokeExact(ObjC.cls("NSNumber"),
                ObjC.sel("numberWithUnsignedInteger:"), value)));
    }

    public float floatValue() {
        return sendFloat(id, "floatValue");
    }

    public long unsignedIntegerValue() {
        return sendLong(id, "unsignedIntegerValue");
    }
}
