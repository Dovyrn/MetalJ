package dev.dov.metalj.objc;

import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class NSNumber extends NSObject {
    private static final long NS_NUMBER = ObjC.cls("NSNumber");

    private static final long FLOAT_VALUE = ObjC.sel("floatValue");
    private static final long NUMBER_WITH_FLOAT = ObjC.sel("numberWithFloat:");
    private static final long NUMBER_WITH_UNSIGNED_INTEGER = ObjC.sel("numberWithUnsignedInteger:");
    private static final long UNSIGNED_INTEGER_VALUE = ObjC.sel("unsignedIntegerValue");

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
        return new NSNumber(owned(() -> (long) P_F.invokeExact(NS_NUMBER, NUMBER_WITH_FLOAT, value)));
    }

    @SneakyThrows
    public static NSNumber numberWithUnsignedInteger(long value) {
        return new NSNumber(owned(() -> (long) P_L.invokeExact(NS_NUMBER,
                NUMBER_WITH_UNSIGNED_INTEGER, value)));
    }

    public float floatValue() {
        return sendFloat(id, FLOAT_VALUE);
    }

    public long unsignedIntegerValue() {
        return sendLong(id, UNSIGNED_INTEGER_VALUE);
    }
}
