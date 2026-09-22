package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLVisibleFunctionTableDescriptor extends NSObject {
    private static final long MTL_VISIBLE_FUNCTION_TABLE_DESCRIPTOR = ObjC.cls("MTLVisibleFunctionTableDescriptor");

    private static final long FUNCTION_COUNT = ObjC.sel("functionCount");
    private static final long SET_FUNCTION_COUNT = ObjC.sel("setFunctionCount:");
    private static final long VISIBLE_FUNCTION_TABLE_DESCRIPTOR = ObjC.sel("visibleFunctionTableDescriptor");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLVisibleFunctionTableDescriptor(long id) {
        super(id);
    }

    public static MTLVisibleFunctionTableDescriptor of(long id) {
        return new MTLVisibleFunctionTableDescriptor(id);
    }

    public static MTLVisibleFunctionTableDescriptor visibleFunctionTableDescriptor() {
        return new MTLVisibleFunctionTableDescriptor(
                owned(() -> sendPtr(MTL_VISIBLE_FUNCTION_TABLE_DESCRIPTOR, VISIBLE_FUNCTION_TABLE_DESCRIPTOR)));
    }

    public long functionCount() {
        return sendLong(id, FUNCTION_COUNT);
    }

    @SneakyThrows
    public void setFunctionCount(long count) {
        L.invokeExact(id, SET_FUNCTION_COUNT, count);
    }
}
