package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLFunctionConstant extends NSObject {
    private MTLFunctionConstant(long id) {
        super(id);
    }

    public static MTLFunctionConstant of(long id) {
        return new MTLFunctionConstant(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    public long type() {
        return sendLong(id, "type");
    }

    public long index() {
        return sendLong(id, "index");
    }

    public boolean isRequired() {
        return sendBool(id, "isRequired");
    }
}
