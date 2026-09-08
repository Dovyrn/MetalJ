package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;

public class MTLType extends NSObject {
    protected MTLType(long id) {
        super(id);
    }

    public static MTLType of(long id) {
        return new MTLType(id);
    }

    public long dataType() {
        return sendLong(id, "dataType");
    }
}
