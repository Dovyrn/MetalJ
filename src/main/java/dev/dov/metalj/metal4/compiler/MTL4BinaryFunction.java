package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTL4BinaryFunction extends NSObject {
    private MTL4BinaryFunction(long id) {
        super(id);
    }

    public static MTL4BinaryFunction of(long id) {
        return new MTL4BinaryFunction(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    public long functionType() {
        return sendLong(id, "functionType");
    }
}
