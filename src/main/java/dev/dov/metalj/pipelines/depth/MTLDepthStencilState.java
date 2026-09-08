package dev.dov.metalj.pipelines.depth;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLDepthStencilState extends NSObject {
    private MTLDepthStencilState(long id) {
        super(id);
    }

    public static MTLDepthStencilState of(long id) {
        return new MTLDepthStencilState(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }
}
