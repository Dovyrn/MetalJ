package dev.dov.metalj.resources.samplers;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLSamplerDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle F = handle(null, ObjC.FLOAT);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLSamplerDescriptor(long id) {
        super(id);
    }

    public static MTLSamplerDescriptor of(long id) {
        return new MTLSamplerDescriptor(id);
    }

    public static MTLSamplerDescriptor new_() {
        return new MTLSamplerDescriptor(sendPtr(ObjC.cls("MTLSamplerDescriptor"), "new"));
    }

    @SneakyThrows
    public void setMinFilter(long filter) {
        L.invokeExact(id, ObjC.sel("setMinFilter:"), filter);
    }

    @SneakyThrows
    public void setMagFilter(long filter) {
        L.invokeExact(id, ObjC.sel("setMagFilter:"), filter);
    }

    @SneakyThrows
    public void setMipFilter(long filter) {
        L.invokeExact(id, ObjC.sel("setMipFilter:"), filter);
    }

    @SneakyThrows
    public void setMaxAnisotropy(long anisotropy) {
        L.invokeExact(id, ObjC.sel("setMaxAnisotropy:"), anisotropy);
    }

    @SneakyThrows
    public void setSAddressMode(long mode) {
        L.invokeExact(id, ObjC.sel("setSAddressMode:"), mode);
    }

    @SneakyThrows
    public void setTAddressMode(long mode) {
        L.invokeExact(id, ObjC.sel("setTAddressMode:"), mode);
    }

    @SneakyThrows
    public void setRAddressMode(long mode) {
        L.invokeExact(id, ObjC.sel("setRAddressMode:"), mode);
    }

    @SneakyThrows
    public void setBorderColor(long color) {
        L.invokeExact(id, ObjC.sel("setBorderColor:"), color);
    }

    @SneakyThrows
    public void setNormalizedCoordinates(boolean normalized) {
        B.invokeExact(id, ObjC.sel("setNormalizedCoordinates:"), normalized);
    }

    @SneakyThrows
    public void setLodMinClamp(float clamp) {
        F.invokeExact(id, ObjC.sel("setLodMinClamp:"), clamp);
    }

    @SneakyThrows
    public void setLodMaxClamp(float clamp) {
        F.invokeExact(id, ObjC.sel("setLodMaxClamp:"), clamp);
    }

    @SneakyThrows
    public void setLodAverage(boolean average) {
        B.invokeExact(id, ObjC.sel("setLodAverage:"), average);
    }

    @SneakyThrows
    public void setCompareFunction(long function) {
        L.invokeExact(id, ObjC.sel("setCompareFunction:"), function);
    }

    @SneakyThrows
    public void setSupportArgumentBuffers(boolean support) {
        B.invokeExact(id, ObjC.sel("setSupportArgumentBuffers:"), support);
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }
}
