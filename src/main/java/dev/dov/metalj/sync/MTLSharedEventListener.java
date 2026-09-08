package dev.dov.metalj.sync;

import dev.dov.metalj.objc.NSObject;

public class MTLSharedEventListener extends NSObject {
    private MTLSharedEventListener(long id) {
        super(id);
    }

    public static MTLSharedEventListener of(long id) {
        return new MTLSharedEventListener(id);
    }

    public static MTLSharedEventListener new_() {
        return new MTLSharedEventListener(sendPtr(alloc("MTLSharedEventListener"), "init"));
    }
}
