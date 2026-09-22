package dev.dov.metalj.sync;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLSharedEventHandle extends NSObject {
    private static final long LABEL = ObjC.sel("label");

    private MTLSharedEventHandle(long id) {
        super(id);
    }

    public static MTLSharedEventHandle of(long id) {
        return new MTLSharedEventHandle(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }
}
