package dev.dov.metalj.pipelines.shaders;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLFunctionHandle extends NSObject {
    private MTLFunctionHandle(long id) {
        super(id);
    }

    public static MTLFunctionHandle of(long id) {
        return new MTLFunctionHandle(id);
    }

    public long functionType() {
        return sendLong(id, "functionType");
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }
}
