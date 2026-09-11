package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLVisibleFunctionTableDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLVisibleFunctionTableDescriptor(long id) {
        super(id);
    }

    public static MTLVisibleFunctionTableDescriptor of(long id) {
        return new MTLVisibleFunctionTableDescriptor(id);
    }

    public static MTLVisibleFunctionTableDescriptor visibleFunctionTableDescriptor() {
        return new MTLVisibleFunctionTableDescriptor(
                owned(() -> sendPtr(ObjC.cls("MTLVisibleFunctionTableDescriptor"), "visibleFunctionTableDescriptor")));
    }

    public long functionCount() {
        return sendLong(id, "functionCount");
    }

    @SneakyThrows
    public void setFunctionCount(long count) {
        L.invokeExact(id, ObjC.sel("setFunctionCount:"), count);
    }
}
