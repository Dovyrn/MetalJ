package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.pipelines.shaders.MTLFunction;

public class MTLFunctionLog extends NSObject {
    private MTLFunctionLog(long id) {
        super(id);
    }

    public static MTLFunctionLog of(long id) {
        return new MTLFunctionLog(id);
    }

    public long type() {
        return sendLong(id, "type");
    }

    public NSString encoderLabel() {
        return NSString.of(sendPtr(id, "encoderLabel"));
    }

    public MTLFunction function() {
        return MTLFunction.of(sendPtr(id, "function"));
    }

    public MTLFunctionLogDebugLocation debugLocation() {
        return MTLFunctionLogDebugLocation.of(sendPtr(id, "debugLocation"));
    }
}
