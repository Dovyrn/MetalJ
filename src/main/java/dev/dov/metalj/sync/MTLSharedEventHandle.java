package dev.dov.metalj.sync;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLSharedEventHandle extends NSObject {
    private MTLSharedEventHandle(long id) {
        super(id);
    }

    public static MTLSharedEventHandle of(long id) {
        return new MTLSharedEventHandle(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }
}
