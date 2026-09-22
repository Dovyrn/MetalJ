package dev.dov.metalj.metal4;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTL4CommandAllocator extends NSObject {
    private static final long ALLOCATED_SIZE = ObjC.sel("allocatedSize");
    private static final long DEVICE = ObjC.sel("device");
    private static final long LABEL = ObjC.sel("label");
    private static final long RESET = ObjC.sel("reset");

    private MTL4CommandAllocator(long id) {
        super(id);
    }

    public static MTL4CommandAllocator of(long id) {
        return new MTL4CommandAllocator(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    public long allocatedSize() {
        return sendLong(id, ALLOCATED_SIZE);
    }

    public void reset() {
        sendVoid(id, RESET);
    }
}
