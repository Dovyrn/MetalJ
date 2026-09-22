package dev.dov.metalj.resources;

import dev.dov.metalj.resources.heaps.MTLHeap;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLResource extends MTLAllocation {
    private static final long CPU_CACHE_MODE = ObjC.sel("cpuCacheMode");
    private static final long DEVICE = ObjC.sel("device");
    private static final long HAZARD_TRACKING_MODE = ObjC.sel("hazardTrackingMode");
    private static final long HEAP = ObjC.sel("heap");
    private static final long HEAP_OFFSET = ObjC.sel("heapOffset");
    private static final long IS_ALIASABLE = ObjC.sel("isAliasable");
    private static final long LABEL = ObjC.sel("label");
    private static final long MAKE_ALIASABLE = ObjC.sel("makeAliasable");
    private static final long RESOURCE_OPTIONS = ObjC.sel("resourceOptions");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_PURGEABLE_STATE = ObjC.sel("setPurgeableState:");
    private static final long STORAGE_MODE = ObjC.sel("storageMode");

    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle L_L = handle(ObjC.LONG, ObjC.LONG);

    protected MTLResource(long id) {
        super(id);
    }

    public static MTLResource of(long id) {
        return new MTLResource(id);
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

    public long cpuCacheMode() {
        return sendLong(id, CPU_CACHE_MODE);
    }

    public long storageMode() {
        return sendLong(id, STORAGE_MODE);
    }

    public long hazardTrackingMode() {
        return sendLong(id, HAZARD_TRACKING_MODE);
    }

    public long resourceOptions() {
        return sendLong(id, RESOURCE_OPTIONS);
    }

    @SneakyThrows
    public long setPurgeableState(long state) {
        return (long) L_L.invokeExact(id, SET_PURGEABLE_STATE, state);
    }

    public MTLHeap heap() {
        return MTLHeap.of(sendPtr(id, HEAP));
    }

    public long heapOffset() {
        return sendLong(id, HEAP_OFFSET);
    }

    public void makeAliasable() {
        sendVoid(id, MAKE_ALIASABLE);
    }

    public boolean isAliasable() {
        return sendBool(id, IS_ALIASABLE);
    }
}
