package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLBinding extends NSObject {
    protected MTLBinding(long id) {
        super(id);
    }

    public static MTLBinding of(long id) {
        return new MTLBinding(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    public long type() {
        return sendLong(id, "type");
    }

    public long access() {
        return sendLong(id, "access");
    }

    public long index() {
        return sendLong(id, "index");
    }

    public boolean isUsed() {
        return sendBool(id, "isUsed");
    }

    public boolean isArgument() {
        return sendBool(id, "isArgument");
    }
}
