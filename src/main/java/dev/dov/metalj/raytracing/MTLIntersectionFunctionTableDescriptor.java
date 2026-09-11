package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIntersectionFunctionTableDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLIntersectionFunctionTableDescriptor(long id) {
        super(id);
    }

    public static MTLIntersectionFunctionTableDescriptor of(long id) {
        return new MTLIntersectionFunctionTableDescriptor(id);
    }

    public static MTLIntersectionFunctionTableDescriptor intersectionFunctionTableDescriptor() {
        return new MTLIntersectionFunctionTableDescriptor(
                owned(() -> sendPtr(ObjC.cls("MTLIntersectionFunctionTableDescriptor"), "intersectionFunctionTableDescriptor")));
    }

    public long functionCount() {
        return sendLong(id, "functionCount");
    }

    @SneakyThrows
    public void setFunctionCount(long count) {
        L.invokeExact(id, ObjC.sel("setFunctionCount:"), count);
    }
}
