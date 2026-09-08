package dev.dov.metalj.tensors;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTensorDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLTensorDescriptor(long id) {
        super(id);
    }

    public static MTLTensorDescriptor of(long id) {
        return new MTLTensorDescriptor(id);
    }

    public static MTLTensorDescriptor new_() {
        return new MTLTensorDescriptor(sendPtr(ObjC.cls("MTLTensorDescriptor"), "new"));
    }

    public MTLTensorExtents dimensions() {
        return MTLTensorExtents.of(sendPtr(id, "dimensions"));
    }

    @SneakyThrows
    public void setDimensions(MTLTensorExtents dimensions) {
        P.invokeExact(id, ObjC.sel("setDimensions:"), dimensions.getId());
    }

    public MTLTensorExtents strides() {
        return MTLTensorExtents.of(sendPtr(id, "strides"));
    }

    @SneakyThrows
    public void setStrides(MTLTensorExtents strides) {
        P.invokeExact(id, ObjC.sel("setStrides:"), strides.getId());
    }

    public long dataType() {
        return sendLong(id, "dataType");
    }

    @SneakyThrows
    public void setDataType(long dataType) {
        L.invokeExact(id, ObjC.sel("setDataType:"), dataType);
    }

    public long usage() {
        return sendLong(id, "usage");
    }

    @SneakyThrows
    public void setUsage(long usage) {
        L.invokeExact(id, ObjC.sel("setUsage:"), usage);
    }

    public long resourceOptions() {
        return sendLong(id, "resourceOptions");
    }

    @SneakyThrows
    public void setResourceOptions(long options) {
        L.invokeExact(id, ObjC.sel("setResourceOptions:"), options);
    }

    public long cpuCacheMode() {
        return sendLong(id, "cpuCacheMode");
    }

    @SneakyThrows
    public void setCpuCacheMode(long mode) {
        L.invokeExact(id, ObjC.sel("setCpuCacheMode:"), mode);
    }

    public long storageMode() {
        return sendLong(id, "storageMode");
    }

    @SneakyThrows
    public void setStorageMode(long mode) {
        L.invokeExact(id, ObjC.sel("setStorageMode:"), mode);
    }

    public long hazardTrackingMode() {
        return sendLong(id, "hazardTrackingMode");
    }

    @SneakyThrows
    public void setHazardTrackingMode(long mode) {
        L.invokeExact(id, ObjC.sel("setHazardTrackingMode:"), mode);
    }
}
