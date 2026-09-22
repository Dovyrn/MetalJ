package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.objc.ObjC;

public class MTLFunctionLogDebugLocation extends NSObject {
    private static final long URL_SEL = ObjC.sel("URL");
    private static final long COLUMN = ObjC.sel("column");
    private static final long FUNCTION_NAME = ObjC.sel("functionName");
    private static final long LINE = ObjC.sel("line");

    private MTLFunctionLogDebugLocation(long id) {
        super(id);
    }

    public static MTLFunctionLogDebugLocation of(long id) {
        return new MTLFunctionLogDebugLocation(id);
    }

    public NSString functionName() {
        return NSString.of(sendPtr(id, FUNCTION_NAME));
    }

    public NSURL URL() {
        return NSURL.of(sendPtr(id, URL_SEL));
    }

    public long line() {
        return sendLong(id, LINE);
    }

    public long column() {
        return sendLong(id, COLUMN);
    }
}
