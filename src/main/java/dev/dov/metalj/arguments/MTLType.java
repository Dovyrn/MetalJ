package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLType extends NSObject {
    private static final long DATA_TYPE = ObjC.sel("dataType");

    protected MTLType(long id) {
        super(id);
    }

    public static MTLType of(long id) {
        return new MTLType(id);
    }

    public long dataType() {
        return sendLong(id, DATA_TYPE);
    }
}
