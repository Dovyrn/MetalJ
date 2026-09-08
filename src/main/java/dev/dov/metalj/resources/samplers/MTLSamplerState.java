package dev.dov.metalj.resources.samplers;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLSamplerState extends NSObject {
    private MTLSamplerState(long id) {
        super(id);
    }

    public static MTLSamplerState of(long id) {
        return new MTLSamplerState(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public long gpuResourceID() {
        return sendLong(id, "gpuResourceID");
    }
}
