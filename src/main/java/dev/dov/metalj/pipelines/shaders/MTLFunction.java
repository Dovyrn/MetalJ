package dev.dov.metalj.pipelines.shaders;

import dev.dov.metalj.arguments.MTLArgumentEncoder;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLFunction extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);

    private MTLFunction(long id) {
        super(id);
    }

    public static MTLFunction of(long id) {
        return new MTLFunction(id);
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

    public long functionType() {
        return sendLong(id, "functionType");
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    public long patchType() {
        return sendLong(id, "patchType");
    }

    public long patchControlPointCount() {
        return sendLong(id, "patchControlPointCount");
    }

    public long options() {
        return sendLong(id, "options");
    }

    @SneakyThrows
    public MTLArgumentEncoder newArgumentEncoderWithBufferIndex(long index) {
        return MTLArgumentEncoder.of((long) P_L.invokeExact(id, ObjC.sel("newArgumentEncoderWithBufferIndex:"), index));
    }
}
