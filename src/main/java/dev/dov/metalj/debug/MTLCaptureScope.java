package dev.dov.metalj.debug;

import dev.dov.metalj.device.MTLCommandQueue;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCaptureScope extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLCaptureScope(long id) {
        super(id);
    }

    public static MTLCaptureScope of(long id) {
        return new MTLCaptureScope(id);
    }

    public void beginScope() {
        sendVoid(id, "beginScope");
    }

    public void endScope() {
        sendVoid(id, "endScope");
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public MTLCommandQueue commandQueue() {
        return MTLCommandQueue.of(sendPtr(id, "commandQueue"));
    }
}
