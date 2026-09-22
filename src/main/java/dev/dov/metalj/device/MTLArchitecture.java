package dev.dov.metalj.device;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLArchitecture extends NSObject {
    private static final long NAME = ObjC.sel("name");

    private MTLArchitecture(long id) {
        super(id);
    }

    public static MTLArchitecture of(long id) {
        return new MTLArchitecture(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }
}
