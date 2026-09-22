package dev.dov.metalj.pipelines.shaders;

import dev.dov.metalj.arguments.MTLArgumentEncoder;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLFunction extends NSObject {
    private static final long DEVICE = ObjC.sel("device");
    private static final long FUNCTION_TYPE = ObjC.sel("functionType");
    private static final long LABEL = ObjC.sel("label");
    private static final long NAME = ObjC.sel("name");
    private static final long NEW_ARGUMENT_ENCODER_WITH_BUFFER_INDEX = ObjC.sel("newArgumentEncoderWithBufferIndex:");
    private static final long OPTIONS = ObjC.sel("options");
    private static final long PATCH_CONTROL_POINT_COUNT = ObjC.sel("patchControlPointCount");
    private static final long PATCH_TYPE = ObjC.sel("patchType");
    private static final long SET_LABEL = ObjC.sel("setLabel:");

    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);

    private MTLFunction(long id) {
        super(id);
    }

    public static MTLFunction of(long id) {
        return new MTLFunction(id);
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

    public long functionType() {
        return sendLong(id, FUNCTION_TYPE);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    public long patchType() {
        return sendLong(id, PATCH_TYPE);
    }

    public long patchControlPointCount() {
        return sendLong(id, PATCH_CONTROL_POINT_COUNT);
    }

    public long options() {
        return sendLong(id, OPTIONS);
    }

    @SneakyThrows
    public MTLArgumentEncoder newArgumentEncoderWithBufferIndex(long index) {
        return MTLArgumentEncoder.of((long) P_L.invokeExact(id, NEW_ARGUMENT_ENCODER_WITH_BUFFER_INDEX, index));
    }
}
