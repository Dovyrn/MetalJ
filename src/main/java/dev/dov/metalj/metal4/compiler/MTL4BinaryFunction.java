package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTL4BinaryFunction extends NSObject {
    private static final long FUNCTION_TYPE = ObjC.sel("functionType");
    private static final long NAME = ObjC.sel("name");

    private MTL4BinaryFunction(long id) {
        super(id);
    }

    public static MTL4BinaryFunction of(long id) {
        return new MTL4BinaryFunction(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    public long functionType() {
        return sendLong(id, FUNCTION_TYPE);
    }
}
