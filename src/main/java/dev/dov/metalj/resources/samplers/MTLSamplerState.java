package dev.dov.metalj.resources.samplers;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLSamplerState extends NSObject {
    private static final long DEVICE = ObjC.sel("device");
    private static final long GPU_RESOURCE_ID = ObjC.sel("gpuResourceID");
    private static final long LABEL = ObjC.sel("label");

    private MTLSamplerState(long id) {
        super(id);
    }

    public static MTLSamplerState of(long id) {
        return new MTLSamplerState(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public long gpuResourceID() {
        return sendLong(id, GPU_RESOURCE_ID);
    }
}
