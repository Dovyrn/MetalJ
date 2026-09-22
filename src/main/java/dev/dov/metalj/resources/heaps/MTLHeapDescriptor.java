package dev.dov.metalj.resources.heaps;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLHeapDescriptor extends NSObject {
    private static final long MTL_HEAP_DESCRIPTOR = ObjC.cls("MTLHeapDescriptor");

    private static final long CPU_CACHE_MODE = ObjC.sel("cpuCacheMode");
    private static final long HAZARD_TRACKING_MODE = ObjC.sel("hazardTrackingMode");
    private static final long NEW = ObjC.sel("new");
    private static final long RESOURCE_OPTIONS = ObjC.sel("resourceOptions");
    private static final long SET_CPU_CACHE_MODE = ObjC.sel("setCpuCacheMode:");
    private static final long SET_HAZARD_TRACKING_MODE = ObjC.sel("setHazardTrackingMode:");
    private static final long SET_RESOURCE_OPTIONS = ObjC.sel("setResourceOptions:");
    private static final long SET_SIZE = ObjC.sel("setSize:");
    private static final long SET_STORAGE_MODE = ObjC.sel("setStorageMode:");
    private static final long SET_TYPE = ObjC.sel("setType:");
    private static final long SIZE = ObjC.sel("size");
    private static final long STORAGE_MODE = ObjC.sel("storageMode");
    private static final long TYPE = ObjC.sel("type");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLHeapDescriptor(long id) {
        super(id);
    }

    public static MTLHeapDescriptor of(long id) {
        return new MTLHeapDescriptor(id);
    }

    public static MTLHeapDescriptor new_() {
        return new MTLHeapDescriptor(sendPtr(MTL_HEAP_DESCRIPTOR, NEW));
    }

    public long size() {
        return sendLong(id, SIZE);
    }

    @SneakyThrows
    public void setSize(long size) {
        L.invokeExact(id, SET_SIZE, size);
    }

    public long storageMode() {
        return sendLong(id, STORAGE_MODE);
    }

    @SneakyThrows
    public void setStorageMode(long mode) {
        L.invokeExact(id, SET_STORAGE_MODE, mode);
    }

    public long cpuCacheMode() {
        return sendLong(id, CPU_CACHE_MODE);
    }

    @SneakyThrows
    public void setCpuCacheMode(long mode) {
        L.invokeExact(id, SET_CPU_CACHE_MODE, mode);
    }

    public long hazardTrackingMode() {
        return sendLong(id, HAZARD_TRACKING_MODE);
    }

    @SneakyThrows
    public void setHazardTrackingMode(long mode) {
        L.invokeExact(id, SET_HAZARD_TRACKING_MODE, mode);
    }

    public long resourceOptions() {
        return sendLong(id, RESOURCE_OPTIONS);
    }

    @SneakyThrows
    public void setResourceOptions(long options) {
        L.invokeExact(id, SET_RESOURCE_OPTIONS, options);
    }

    public long type() {
        return sendLong(id, TYPE);
    }

    @SneakyThrows
    public void setType(long type) {
        L.invokeExact(id, SET_TYPE, type);
    }
}
