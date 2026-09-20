package dev.dov.metalj.metal4;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTL4CommandAllocator extends NSObject {
    private MTL4CommandAllocator(long id) {
        super(id);
    }

    public static MTL4CommandAllocator of(long id) {
        return new MTL4CommandAllocator(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    public long allocatedSize() {
        return sendLong(id, "allocatedSize");
    }

    public void reset() {
        sendVoid(id, "reset");
    }
}
