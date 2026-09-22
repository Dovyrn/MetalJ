package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLBinding extends NSObject {
    private static final long ACCESS = ObjC.sel("access");
    private static final long INDEX = ObjC.sel("index");
    private static final long IS_ARGUMENT = ObjC.sel("isArgument");
    private static final long IS_USED = ObjC.sel("isUsed");
    private static final long NAME = ObjC.sel("name");
    private static final long TYPE = ObjC.sel("type");

    protected MTLBinding(long id) {
        super(id);
    }

    public static MTLBinding of(long id) {
        return new MTLBinding(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    public long type() {
        return sendLong(id, TYPE);
    }

    public long access() {
        return sendLong(id, ACCESS);
    }

    public long index() {
        return sendLong(id, INDEX);
    }

    public boolean isUsed() {
        return sendBool(id, IS_USED);
    }

    public boolean isArgument() {
        return sendBool(id, IS_ARGUMENT);
    }
}
