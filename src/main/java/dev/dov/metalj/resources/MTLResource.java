package dev.dov.metalj.resources;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLResource extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle L_L = handle(ObjC.LONG, ObjC.LONG);

    protected MTLResource(long id) {
        super(id);
    }

    public static MTLResource of(long id) {
        return new MTLResource(id);
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

    public long cpuCacheMode() {
        return sendLong(id, "cpuCacheMode");
    }

    public long storageMode() {
        return sendLong(id, "storageMode");
    }

    public long hazardTrackingMode() {
        return sendLong(id, "hazardTrackingMode");
    }

    public long resourceOptions() {
        return sendLong(id, "resourceOptions");
    }

    @SneakyThrows
    public long setPurgeableState(long state) {
        return (long) L_L.invokeExact(id, ObjC.sel("setPurgeableState:"), state);
    }

    public MTLHeap heap() {
        return MTLHeap.of(sendPtr(id, "heap"));
    }

    public long heapOffset() {
        return sendLong(id, "heapOffset");
    }

    public long allocatedSize() {
        return sendLong(id, "allocatedSize");
    }

    public void makeAliasable() {
        sendVoid(id, "makeAliasable");
    }

    public boolean isAliasable() {
        return sendBool(id, "isAliasable");
    }
}
