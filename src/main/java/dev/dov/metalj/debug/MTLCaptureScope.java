package dev.dov.metalj.debug;

import dev.dov.metalj.device.MTLCommandQueue;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCaptureScope extends NSObject {
    private static final long BEGIN_SCOPE = ObjC.sel("beginScope");
    private static final long COMMAND_QUEUE = ObjC.sel("commandQueue");
    private static final long DEVICE = ObjC.sel("device");
    private static final long END_SCOPE = ObjC.sel("endScope");
    private static final long LABEL = ObjC.sel("label");
    private static final long SET_LABEL = ObjC.sel("setLabel:");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLCaptureScope(long id) {
        super(id);
    }

    public static MTLCaptureScope of(long id) {
        return new MTLCaptureScope(id);
    }

    public void beginScope() {
        sendVoid(id, BEGIN_SCOPE);
    }

    public void endScope() {
        sendVoid(id, END_SCOPE);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public MTLCommandQueue commandQueue() {
        return MTLCommandQueue.of(sendPtr(id, COMMAND_QUEUE));
    }
}
