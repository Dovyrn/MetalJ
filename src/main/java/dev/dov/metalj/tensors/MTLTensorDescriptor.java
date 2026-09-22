package dev.dov.metalj.tensors;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTensorDescriptor extends NSObject {
    private static final long MTL_TENSOR_DESCRIPTOR = ObjC.cls("MTLTensorDescriptor");

    private static final long CPU_CACHE_MODE = ObjC.sel("cpuCacheMode");
    private static final long DATA_TYPE = ObjC.sel("dataType");
    private static final long DIMENSIONS = ObjC.sel("dimensions");
    private static final long HAZARD_TRACKING_MODE = ObjC.sel("hazardTrackingMode");
    private static final long NEW = ObjC.sel("new");
    private static final long RESOURCE_OPTIONS = ObjC.sel("resourceOptions");
    private static final long SET_CPU_CACHE_MODE = ObjC.sel("setCpuCacheMode:");
    private static final long SET_DATA_TYPE = ObjC.sel("setDataType:");
    private static final long SET_DIMENSIONS = ObjC.sel("setDimensions:");
    private static final long SET_HAZARD_TRACKING_MODE = ObjC.sel("setHazardTrackingMode:");
    private static final long SET_RESOURCE_OPTIONS = ObjC.sel("setResourceOptions:");
    private static final long SET_STORAGE_MODE = ObjC.sel("setStorageMode:");
    private static final long SET_STRIDES = ObjC.sel("setStrides:");
    private static final long SET_USAGE = ObjC.sel("setUsage:");
    private static final long STORAGE_MODE = ObjC.sel("storageMode");
    private static final long STRIDES = ObjC.sel("strides");
    private static final long USAGE = ObjC.sel("usage");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLTensorDescriptor(long id) {
        super(id);
    }

    public static MTLTensorDescriptor of(long id) {
        return new MTLTensorDescriptor(id);
    }

    public static MTLTensorDescriptor new_() {
        return new MTLTensorDescriptor(sendPtr(MTL_TENSOR_DESCRIPTOR, NEW));
    }

    public MTLTensorExtents dimensions() {
        return MTLTensorExtents.of(sendPtr(id, DIMENSIONS));
    }

    @SneakyThrows
    public void setDimensions(MTLTensorExtents dimensions) {
        P.invokeExact(id, SET_DIMENSIONS, dimensions.getId());
    }

    public MTLTensorExtents strides() {
        return MTLTensorExtents.of(sendPtr(id, STRIDES));
    }

    @SneakyThrows
    public void setStrides(MTLTensorExtents strides) {
        P.invokeExact(id, SET_STRIDES, strides.getId());
    }

    public long dataType() {
        return sendLong(id, DATA_TYPE);
    }

    @SneakyThrows
    public void setDataType(long dataType) {
        L.invokeExact(id, SET_DATA_TYPE, dataType);
    }

    public long usage() {
        return sendLong(id, USAGE);
    }

    @SneakyThrows
    public void setUsage(long usage) {
        L.invokeExact(id, SET_USAGE, usage);
    }

    public long resourceOptions() {
        return sendLong(id, RESOURCE_OPTIONS);
    }

    @SneakyThrows
    public void setResourceOptions(long options) {
        L.invokeExact(id, SET_RESOURCE_OPTIONS, options);
    }

    public long cpuCacheMode() {
        return sendLong(id, CPU_CACHE_MODE);
    }

    @SneakyThrows
    public void setCpuCacheMode(long mode) {
        L.invokeExact(id, SET_CPU_CACHE_MODE, mode);
    }

    public long storageMode() {
        return sendLong(id, STORAGE_MODE);
    }

    @SneakyThrows
    public void setStorageMode(long mode) {
        L.invokeExact(id, SET_STORAGE_MODE, mode);
    }

    public long hazardTrackingMode() {
        return sendLong(id, HAZARD_TRACKING_MODE);
    }

    @SneakyThrows
    public void setHazardTrackingMode(long mode) {
        L.invokeExact(id, SET_HAZARD_TRACKING_MODE, mode);
    }
}
