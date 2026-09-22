package dev.dov.metalj.sync;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLSharedEventListener extends NSObject {
    private static final long INIT = ObjC.sel("init");

    private MTLSharedEventListener(long id) {
        super(id);
    }

    public static MTLSharedEventListener of(long id) {
        return new MTLSharedEventListener(id);
    }

    public static MTLSharedEventListener new_() {
        return new MTLSharedEventListener(sendPtr(alloc("MTLSharedEventListener"), INIT));
    }
}
