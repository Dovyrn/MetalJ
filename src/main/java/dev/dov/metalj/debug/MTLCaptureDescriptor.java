package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCaptureDescriptor extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLCaptureDescriptor(long id) {
        super(id);
    }

    public static MTLCaptureDescriptor of(long id) {
        return new MTLCaptureDescriptor(id);
    }

    public static MTLCaptureDescriptor new_() {
        return new MTLCaptureDescriptor(sendPtr(ObjC.cls("MTLCaptureDescriptor"), "new"));
    }

    public long captureObject() {
        return sendPtr(id, "captureObject");
    }

    @SneakyThrows
    public void setCaptureObject(NSObject object) {
        P.invokeExact(id, ObjC.sel("setCaptureObject:"), object.getId());
    }

    public long destination() {
        return sendLong(id, "destination");
    }

    @SneakyThrows
    public void setDestination(long destination) {
        L.invokeExact(id, ObjC.sel("setDestination:"), destination);
    }

    public NSURL outputURL() {
        return NSURL.of(sendPtr(id, "outputURL"));
    }

    @SneakyThrows
    public void setOutputURL(NSURL outputURL) {
        P.invokeExact(id, ObjC.sel("setOutputURL:"), outputURL.getId());
    }
}
