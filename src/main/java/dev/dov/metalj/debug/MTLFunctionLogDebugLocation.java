package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.NSURL;

public class MTLFunctionLogDebugLocation extends NSObject {
    private MTLFunctionLogDebugLocation(long id) {
        super(id);
    }

    public static MTLFunctionLogDebugLocation of(long id) {
        return new MTLFunctionLogDebugLocation(id);
    }

    public NSString functionName() {
        return NSString.of(sendPtr(id, "functionName"));
    }

    public NSURL URL() {
        return NSURL.of(sendPtr(id, "URL"));
    }

    public long line() {
        return sendLong(id, "line");
    }

    public long column() {
        return sendLong(id, "column");
    }
}
