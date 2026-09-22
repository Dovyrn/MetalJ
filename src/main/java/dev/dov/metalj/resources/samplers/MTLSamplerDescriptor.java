package dev.dov.metalj.resources.samplers;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLSamplerDescriptor extends NSObject {
    private static final long MTL_SAMPLER_DESCRIPTOR = ObjC.cls("MTLSamplerDescriptor");

    private static final long NEW = ObjC.sel("new");
    private static final long SET_BORDER_COLOR = ObjC.sel("setBorderColor:");
    private static final long SET_COMPARE_FUNCTION = ObjC.sel("setCompareFunction:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_LOD_AVERAGE = ObjC.sel("setLodAverage:");
    private static final long SET_LOD_MAX_CLAMP = ObjC.sel("setLodMaxClamp:");
    private static final long SET_LOD_MIN_CLAMP = ObjC.sel("setLodMinClamp:");
    private static final long SET_MAG_FILTER = ObjC.sel("setMagFilter:");
    private static final long SET_MAX_ANISOTROPY = ObjC.sel("setMaxAnisotropy:");
    private static final long SET_MIN_FILTER = ObjC.sel("setMinFilter:");
    private static final long SET_MIP_FILTER = ObjC.sel("setMipFilter:");
    private static final long SET_NORMALIZED_COORDINATES = ObjC.sel("setNormalizedCoordinates:");
    private static final long SET_R_ADDRESS_MODE = ObjC.sel("setRAddressMode:");
    private static final long SET_S_ADDRESS_MODE = ObjC.sel("setSAddressMode:");
    private static final long SET_SUPPORT_ARGUMENT_BUFFERS = ObjC.sel("setSupportArgumentBuffers:");
    private static final long SET_T_ADDRESS_MODE = ObjC.sel("setTAddressMode:");

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
        return new MTLSamplerDescriptor(sendPtr(MTL_SAMPLER_DESCRIPTOR, NEW));
    }

    @SneakyThrows
    public void setMinFilter(long filter) {
        L.invokeExact(id, SET_MIN_FILTER, filter);
    }

    @SneakyThrows
    public void setMagFilter(long filter) {
        L.invokeExact(id, SET_MAG_FILTER, filter);
    }

    @SneakyThrows
    public void setMipFilter(long filter) {
        L.invokeExact(id, SET_MIP_FILTER, filter);
    }

    @SneakyThrows
    public void setMaxAnisotropy(long anisotropy) {
        L.invokeExact(id, SET_MAX_ANISOTROPY, anisotropy);
    }

    @SneakyThrows
    public void setSAddressMode(long mode) {
        L.invokeExact(id, SET_S_ADDRESS_MODE, mode);
    }

    @SneakyThrows
    public void setTAddressMode(long mode) {
        L.invokeExact(id, SET_T_ADDRESS_MODE, mode);
    }

    @SneakyThrows
    public void setRAddressMode(long mode) {
        L.invokeExact(id, SET_R_ADDRESS_MODE, mode);
    }

    @SneakyThrows
    public void setBorderColor(long color) {
        L.invokeExact(id, SET_BORDER_COLOR, color);
    }

    @SneakyThrows
    public void setNormalizedCoordinates(boolean normalized) {
        B.invokeExact(id, SET_NORMALIZED_COORDINATES, normalized);
    }

    @SneakyThrows
    public void setLodMinClamp(float clamp) {
        F.invokeExact(id, SET_LOD_MIN_CLAMP, clamp);
    }

    @SneakyThrows
    public void setLodMaxClamp(float clamp) {
        F.invokeExact(id, SET_LOD_MAX_CLAMP, clamp);
    }

    @SneakyThrows
    public void setLodAverage(boolean average) {
        B.invokeExact(id, SET_LOD_AVERAGE, average);
    }

    @SneakyThrows
    public void setCompareFunction(long function) {
        L.invokeExact(id, SET_COMPARE_FUNCTION, function);
    }

    @SneakyThrows
    public void setSupportArgumentBuffers(boolean support) {
        B.invokeExact(id, SET_SUPPORT_ARGUMENT_BUFFERS, support);
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }
}
