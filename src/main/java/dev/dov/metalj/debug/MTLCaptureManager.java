package dev.dov.metalj.debug;

import dev.dov.metalj.device.MTLCommandQueue;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSError;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCaptureManager extends NSObject {
    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle B_L = handle(ObjC.BOOL, ObjC.LONG);
    private static final MethodHandle B_PA = handle(ObjC.BOOL, ObjC.PTR, ValueLayout.ADDRESS);

    private MTLCaptureManager(long id) {
        super(id);
    }

    public static MTLCaptureManager sharedCaptureManager() {
        return new MTLCaptureManager(sendPtr(ObjC.cls("MTLCaptureManager"), "sharedCaptureManager"));
    }

    @SneakyThrows
    public MTLCaptureScope newCaptureScopeWithDevice(MTLDevice device) {
        return MTLCaptureScope.of((long) P_P.invokeExact(id, ObjC.sel("newCaptureScopeWithDevice:"), device.getId()));
    }

    @SneakyThrows
    public MTLCaptureScope newCaptureScopeWithCommandQueue(MTLCommandQueue queue) {
        return MTLCaptureScope.of(
                (long) P_P.invokeExact(id, ObjC.sel("newCaptureScopeWithCommandQueue:"), queue.getId()));
    }

    @SneakyThrows
    public boolean supportsDestination(long destination) {
        return (boolean) B_L.invokeExact(id, ObjC.sel("supportsDestination:"), destination);
    }

    @SneakyThrows
    public void startCaptureWithDescriptor(MTLCaptureDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            boolean ok = (boolean) B_PA.invokeExact(id, ObjC.sel("startCaptureWithDescriptor:error:"),
                    descriptor.getId(), error);
            NSError.check(error, "startCaptureWithDescriptor");
            if (!ok) {
                throw new IllegalStateException("startCaptureWithDescriptor failed");
            }
        }
    }

    @SneakyThrows
    public void startCaptureWithDevice(MTLDevice device) {
        P.invokeExact(id, ObjC.sel("startCaptureWithDevice:"), device.getId());
    }

    @SneakyThrows
    public void startCaptureWithCommandQueue(MTLCommandQueue queue) {
        P.invokeExact(id, ObjC.sel("startCaptureWithCommandQueue:"), queue.getId());
    }

    @SneakyThrows
    public void startCaptureWithScope(MTLCaptureScope scope) {
        P.invokeExact(id, ObjC.sel("startCaptureWithScope:"), scope.getId());
    }

    public void stopCapture() {
        sendVoid(id, "stopCapture");
    }

    public boolean isCapturing() {
        return sendBool(id, "isCapturing");
    }

    public MTLCaptureScope defaultCaptureScope() {
        return MTLCaptureScope.of(sendPtr(id, "defaultCaptureScope"));
    }

    @SneakyThrows
    public void setDefaultCaptureScope(MTLCaptureScope scope) {
        P.invokeExact(id, ObjC.sel("setDefaultCaptureScope:"), scope.getId());
    }
}
