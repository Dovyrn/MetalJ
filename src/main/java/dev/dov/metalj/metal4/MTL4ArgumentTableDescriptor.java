package dev.dov.metalj.metal4;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4ArgumentTableDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4ArgumentTableDescriptor(long id) {
        super(id);
    }

    public static MTL4ArgumentTableDescriptor of(long id) {
        return new MTL4ArgumentTableDescriptor(id);
    }

    public static MTL4ArgumentTableDescriptor new_() {
        return new MTL4ArgumentTableDescriptor(sendPtr(ObjC.cls("MTL4ArgumentTableDescriptor"), "new"));
    }

    public long maxBufferBindCount() {
        return sendLong(id, "maxBufferBindCount");
    }

    @SneakyThrows
    public void setMaxBufferBindCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxBufferBindCount:"), count);
    }

    public long maxTextureBindCount() {
        return sendLong(id, "maxTextureBindCount");
    }

    @SneakyThrows
    public void setMaxTextureBindCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxTextureBindCount:"), count);
    }

    public long maxSamplerStateBindCount() {
        return sendLong(id, "maxSamplerStateBindCount");
    }

    @SneakyThrows
    public void setMaxSamplerStateBindCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxSamplerStateBindCount:"), count);
    }

    public boolean initializeBindings() {
        return sendBool(id, "initializeBindings");
    }

    @SneakyThrows
    public void setInitializeBindings(boolean initialize) {
        B.invokeExact(id, ObjC.sel("setInitializeBindings:"), initialize);
    }

    public boolean supportAttributeStrides() {
        return sendBool(id, "supportAttributeStrides");
    }

    @SneakyThrows
    public void setSupportAttributeStrides(boolean support) {
        B.invokeExact(id, ObjC.sel("setSupportAttributeStrides:"), support);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }
}
