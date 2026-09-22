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
    private static final long MTL_CAPTURE_MANAGER = ObjC.cls("MTLCaptureManager");

    private static final long DEFAULT_CAPTURE_SCOPE = ObjC.sel("defaultCaptureScope");
    private static final long IS_CAPTURING = ObjC.sel("isCapturing");
    private static final long NEW_CAPTURE_SCOPE_WITH_COMMAND_QUEUE = ObjC.sel("newCaptureScopeWithCommandQueue:");
    private static final long NEW_CAPTURE_SCOPE_WITH_DEVICE = ObjC.sel("newCaptureScopeWithDevice:");
    private static final long SET_DEFAULT_CAPTURE_SCOPE = ObjC.sel("setDefaultCaptureScope:");
    private static final long SHARED_CAPTURE_MANAGER = ObjC.sel("sharedCaptureManager");
    private static final long START_CAPTURE_WITH_COMMAND_QUEUE = ObjC.sel("startCaptureWithCommandQueue:");
    private static final long START_CAPTURE_WITH_DESCRIPTOR_ERROR = ObjC.sel("startCaptureWithDescriptor:error:");
    private static final long START_CAPTURE_WITH_DEVICE = ObjC.sel("startCaptureWithDevice:");
    private static final long START_CAPTURE_WITH_SCOPE = ObjC.sel("startCaptureWithScope:");
    private static final long STOP_CAPTURE = ObjC.sel("stopCapture");
    private static final long SUPPORTS_DESTINATION = ObjC.sel("supportsDestination:");

    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle B_L = handle(ObjC.BOOL, ObjC.LONG);
    private static final MethodHandle B_PA = handle(ObjC.BOOL, ObjC.PTR, ValueLayout.ADDRESS);

    private MTLCaptureManager(long id) {
        super(id);
    }

    public static MTLCaptureManager sharedCaptureManager() {
        return new MTLCaptureManager(sendPtr(MTL_CAPTURE_MANAGER, SHARED_CAPTURE_MANAGER));
    }

    @SneakyThrows
    public MTLCaptureScope newCaptureScopeWithDevice(MTLDevice device) {
        return MTLCaptureScope.of((long) P_P.invokeExact(id, NEW_CAPTURE_SCOPE_WITH_DEVICE, device.getId()));
    }

    @SneakyThrows
    public MTLCaptureScope newCaptureScopeWithCommandQueue(MTLCommandQueue queue) {
        return MTLCaptureScope.of(
                (long) P_P.invokeExact(id, NEW_CAPTURE_SCOPE_WITH_COMMAND_QUEUE, queue.getId()));
    }

    @SneakyThrows
    public boolean supportsDestination(long destination) {
        return (boolean) B_L.invokeExact(id, SUPPORTS_DESTINATION, destination);
    }

    @SneakyThrows
    public void startCaptureWithDescriptor(MTLCaptureDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            boolean ok = (boolean) B_PA.invokeExact(id, START_CAPTURE_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "startCaptureWithDescriptor");
            if (!ok) {
                throw new IllegalStateException("startCaptureWithDescriptor failed");
            }
        }
    }

    @SneakyThrows
    public void startCaptureWithDevice(MTLDevice device) {
        P.invokeExact(id, START_CAPTURE_WITH_DEVICE, device.getId());
    }

    @SneakyThrows
    public void startCaptureWithCommandQueue(MTLCommandQueue queue) {
        P.invokeExact(id, START_CAPTURE_WITH_COMMAND_QUEUE, queue.getId());
    }

    @SneakyThrows
    public void startCaptureWithScope(MTLCaptureScope scope) {
        P.invokeExact(id, START_CAPTURE_WITH_SCOPE, scope.getId());
    }

    public void stopCapture() {
        sendVoid(id, STOP_CAPTURE);
    }

    public boolean isCapturing() {
        return sendBool(id, IS_CAPTURING);
    }

    public MTLCaptureScope defaultCaptureScope() {
        return MTLCaptureScope.of(sendPtr(id, DEFAULT_CAPTURE_SCOPE));
    }

    @SneakyThrows
    public void setDefaultCaptureScope(MTLCaptureScope scope) {
        P.invokeExact(id, SET_DEFAULT_CAPTURE_SCOPE, scope.getId());
    }
}
