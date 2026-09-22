package dev.dov.metalj.pipelines.depth;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLDepthStencilState extends NSObject {
    private static final long DEVICE = ObjC.sel("device");
    private static final long LABEL = ObjC.sel("label");

    private MTLDepthStencilState(long id) {
        super(id);
    }

    public static MTLDepthStencilState of(long id) {
        return new MTLDepthStencilState(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }
}
