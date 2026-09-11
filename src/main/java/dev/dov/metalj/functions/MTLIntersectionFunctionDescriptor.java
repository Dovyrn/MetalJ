package dev.dov.metalj.functions;

import dev.dov.metalj.objc.ObjC;

public class MTLIntersectionFunctionDescriptor extends MTLFunctionDescriptor {
    private MTLIntersectionFunctionDescriptor(long id) {
        super(id);
    }

    public static MTLIntersectionFunctionDescriptor of(long id) {
        return new MTLIntersectionFunctionDescriptor(id);
    }

    public static MTLIntersectionFunctionDescriptor functionDescriptor() {
        return new MTLIntersectionFunctionDescriptor(
                owned(() -> sendPtr(ObjC.cls("MTLIntersectionFunctionDescriptor"), "functionDescriptor")));
    }
}
