package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIntersectionFunctionTableDescriptor extends NSObject {
    private static final long MTL_INTERSECTION_FUNCTION_TABLE_DESCRIPTOR = ObjC.cls("MTLIntersectionFunctionTableDescriptor");

    private static final long FUNCTION_COUNT = ObjC.sel("functionCount");
    private static final long INTERSECTION_FUNCTION_TABLE_DESCRIPTOR = ObjC.sel("intersectionFunctionTableDescriptor");
    private static final long SET_FUNCTION_COUNT = ObjC.sel("setFunctionCount:");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLIntersectionFunctionTableDescriptor(long id) {
        super(id);
    }

    public static MTLIntersectionFunctionTableDescriptor of(long id) {
        return new MTLIntersectionFunctionTableDescriptor(id);
    }

    public static MTLIntersectionFunctionTableDescriptor intersectionFunctionTableDescriptor() {
        return new MTLIntersectionFunctionTableDescriptor(
                owned(() -> sendPtr(MTL_INTERSECTION_FUNCTION_TABLE_DESCRIPTOR, INTERSECTION_FUNCTION_TABLE_DESCRIPTOR)));
    }

    public long functionCount() {
        return sendLong(id, FUNCTION_COUNT);
    }

    @SneakyThrows
    public void setFunctionCount(long count) {
        L.invokeExact(id, SET_FUNCTION_COUNT, count);
    }
}
