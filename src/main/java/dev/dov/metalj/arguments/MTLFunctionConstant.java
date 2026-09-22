package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLFunctionConstant extends NSObject {
    private static final long INDEX = ObjC.sel("index");
    private static final long IS_REQUIRED = ObjC.sel("isRequired");
    private static final long NAME = ObjC.sel("name");
    private static final long TYPE = ObjC.sel("type");

    private MTLFunctionConstant(long id) {
        super(id);
    }

    public static MTLFunctionConstant of(long id) {
        return new MTLFunctionConstant(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    public long type() {
        return sendLong(id, TYPE);
    }

    public long index() {
        return sendLong(id, INDEX);
    }

    public boolean isRequired() {
        return sendBool(id, IS_REQUIRED);
    }
}
