package dev.dov.metalj.resources.textures;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLSharedTextureHandle extends NSObject {
    private MTLSharedTextureHandle(long id) {
        super(id);
    }

    public static MTLSharedTextureHandle of(long id) {
        return new MTLSharedTextureHandle(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }
}
