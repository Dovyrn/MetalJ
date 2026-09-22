package dev.dov.metalj.libraries;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSError;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLDynamicLibrary extends NSObject {
    private static final long DEVICE = ObjC.sel("device");
    private static final long INSTALL_NAME = ObjC.sel("installName");
    private static final long LABEL = ObjC.sel("label");
    private static final long SERIALIZE_TO_URL_ERROR = ObjC.sel("serializeToURL:error:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");

    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle B_PA = handle(ObjC.BOOL, ObjC.PTR, ValueLayout.ADDRESS);

    private MTLDynamicLibrary(long id) {
        super(id);
    }

    public static MTLDynamicLibrary of(long id) {
        return new MTLDynamicLibrary(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public NSString installName() {
        return NSString.of(sendPtr(id, INSTALL_NAME));
    }

    @SneakyThrows
    public void serializeToURL(NSURL url) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            boolean ok = (boolean) B_PA.invokeExact(id, SERIALIZE_TO_URL_ERROR, url.getId(), error);
            NSError.check(error, "serializeToURL:error:");
            if (!ok) {
                throw new IllegalStateException("serializeToURL failed");
            }
        }
    }
}
