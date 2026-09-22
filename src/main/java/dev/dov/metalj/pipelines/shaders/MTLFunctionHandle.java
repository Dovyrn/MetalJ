package dev.dov.metalj.pipelines.shaders;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLFunctionHandle extends NSObject {
    private static final long DEVICE = ObjC.sel("device");
    private static final long FUNCTION_TYPE = ObjC.sel("functionType");
    private static final long NAME = ObjC.sel("name");

    private MTLFunctionHandle(long id) {
        super(id);
    }

    public static MTLFunctionHandle of(long id) {
        return new MTLFunctionHandle(id);
    }

    public long functionType() {
        return sendLong(id, FUNCTION_TYPE);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }
}
