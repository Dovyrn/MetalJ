package dev.dov.metalj.resources.heaps;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLHeapDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLHeapDescriptor(long id) {
        super(id);
    }

    public static MTLHeapDescriptor of(long id) {
        return new MTLHeapDescriptor(id);
    }

    public static MTLHeapDescriptor new_() {
        return new MTLHeapDescriptor(sendPtr(ObjC.cls("MTLHeapDescriptor"), "new"));
    }

    public long size() {
        return sendLong(id, "size");
    }

    @SneakyThrows
    public void setSize(long size) {
        L.invokeExact(id, ObjC.sel("setSize:"), size);
    }

    public long storageMode() {
        return sendLong(id, "storageMode");
    }

    @SneakyThrows
    public void setStorageMode(long mode) {
        L.invokeExact(id, ObjC.sel("setStorageMode:"), mode);
    }

    public long cpuCacheMode() {
        return sendLong(id, "cpuCacheMode");
    }

    @SneakyThrows
    public void setCpuCacheMode(long mode) {
        L.invokeExact(id, ObjC.sel("setCpuCacheMode:"), mode);
    }

    public long hazardTrackingMode() {
        return sendLong(id, "hazardTrackingMode");
    }

    @SneakyThrows
    public void setHazardTrackingMode(long mode) {
        L.invokeExact(id, ObjC.sel("setHazardTrackingMode:"), mode);
    }

    public long resourceOptions() {
        return sendLong(id, "resourceOptions");
    }

    @SneakyThrows
    public void setResourceOptions(long options) {
        L.invokeExact(id, ObjC.sel("setResourceOptions:"), options);
    }

    public long type() {
        return sendLong(id, "type");
    }

    @SneakyThrows
    public void setType(long type) {
        L.invokeExact(id, ObjC.sel("setType:"), type);
    }
}
