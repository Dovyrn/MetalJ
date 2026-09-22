package dev.dov.metalj.functions;

import dev.dov.metalj.objc.ObjC;

public class MTLIntersectionFunctionDescriptor extends MTLFunctionDescriptor {
    private static final long MTL_INTERSECTION_FUNCTION_DESCRIPTOR = ObjC.cls("MTLIntersectionFunctionDescriptor");

    private static final long FUNCTION_DESCRIPTOR = ObjC.sel("functionDescriptor");

    private MTLIntersectionFunctionDescriptor(long id) {
        super(id);
    }

    public static MTLIntersectionFunctionDescriptor of(long id) {
        return new MTLIntersectionFunctionDescriptor(id);
    }

    public static MTLIntersectionFunctionDescriptor functionDescriptor() {
        return new MTLIntersectionFunctionDescriptor(
                owned(() -> sendPtr(MTL_INTERSECTION_FUNCTION_DESCRIPTOR, FUNCTION_DESCRIPTOR)));
    }
}
