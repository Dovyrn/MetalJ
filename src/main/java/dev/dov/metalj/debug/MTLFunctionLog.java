package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLFunction;

public class MTLFunctionLog extends NSObject {
    private static final long DEBUG_LOCATION = ObjC.sel("debugLocation");
    private static final long ENCODER_LABEL = ObjC.sel("encoderLabel");
    private static final long FUNCTION = ObjC.sel("function");
    private static final long TYPE = ObjC.sel("type");

    private MTLFunctionLog(long id) {
        super(id);
    }

    public static MTLFunctionLog of(long id) {
        return new MTLFunctionLog(id);
    }

    public long type() {
        return sendLong(id, TYPE);
    }

    public NSString encoderLabel() {
        return NSString.of(sendPtr(id, ENCODER_LABEL));
    }

    public MTLFunction function() {
        return MTLFunction.of(sendPtr(id, FUNCTION));
    }

    public MTLFunctionLogDebugLocation debugLocation() {
        return MTLFunctionLogDebugLocation.of(sendPtr(id, DEBUG_LOCATION));
    }
}
