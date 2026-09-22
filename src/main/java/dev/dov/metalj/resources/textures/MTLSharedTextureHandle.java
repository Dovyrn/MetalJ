package dev.dov.metalj.resources.textures;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLSharedTextureHandle extends NSObject {
    private static final long DEVICE = ObjC.sel("device");
    private static final long LABEL = ObjC.sel("label");

    private MTLSharedTextureHandle(long id) {
        super(id);
    }

    public static MTLSharedTextureHandle of(long id) {
        return new MTLSharedTextureHandle(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }
}
