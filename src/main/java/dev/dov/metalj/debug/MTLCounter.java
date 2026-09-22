package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLCounter extends NSObject {
    private static final long NAME = ObjC.sel("name");

    private MTLCounter(long id) {
        super(id);
    }

    public static MTLCounter of(long id) {
        return new MTLCounter(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }
}
