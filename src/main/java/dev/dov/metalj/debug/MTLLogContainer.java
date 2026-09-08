package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSObject;

public class MTLLogContainer extends NSObject {
    private MTLLogContainer(long id) {
        super(id);
    }

    public static MTLLogContainer of(long id) {
        return new MTLLogContainer(id);
    }
}
