package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCaptureDescriptor extends NSObject {
    private static final long MTL_CAPTURE_DESCRIPTOR = ObjC.cls("MTLCaptureDescriptor");

    private static final long CAPTURE_OBJECT = ObjC.sel("captureObject");
    private static final long DESTINATION = ObjC.sel("destination");
    private static final long NEW = ObjC.sel("new");
    private static final long OUTPUT_URL = ObjC.sel("outputURL");
    private static final long SET_CAPTURE_OBJECT = ObjC.sel("setCaptureObject:");
    private static final long SET_DESTINATION = ObjC.sel("setDestination:");
    private static final long SET_OUTPUT_URL = ObjC.sel("setOutputURL:");

    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLCaptureDescriptor(long id) {
        super(id);
    }

    public static MTLCaptureDescriptor of(long id) {
        return new MTLCaptureDescriptor(id);
    }

    public static MTLCaptureDescriptor new_() {
        return new MTLCaptureDescriptor(sendPtr(MTL_CAPTURE_DESCRIPTOR, NEW));
    }

    public long captureObject() {
        return sendPtr(id, CAPTURE_OBJECT);
    }

    @SneakyThrows
    public void setCaptureObject(NSObject object) {
        P.invokeExact(id, SET_CAPTURE_OBJECT, object.getId());
    }

    public long destination() {
        return sendLong(id, DESTINATION);
    }

    @SneakyThrows
    public void setDestination(long destination) {
        L.invokeExact(id, SET_DESTINATION, destination);
    }

    public NSURL outputURL() {
        return NSURL.of(sendPtr(id, OUTPUT_URL));
    }

    @SneakyThrows
    public void setOutputURL(NSURL outputURL) {
        P.invokeExact(id, SET_OUTPUT_URL, outputURL.getId());
    }
}
