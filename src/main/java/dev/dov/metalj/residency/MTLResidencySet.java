package dev.dov.metalj.residency;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLResource;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLResidencySet extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle AL = handle(null, ValueLayout.ADDRESS, ObjC.LONG);
    private static final MethodHandle B_P = handle(ObjC.BOOL, ObjC.PTR);

    private MTLResidencySet(long id) {
        super(id);
    }

    public static MTLResidencySet of(long id) {
        return new MTLResidencySet(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    public long allocatedSize() {
        return sendLong(id, "allocatedSize");
    }

    public void requestResidency() {
        sendVoid(id, "requestResidency");
    }

    public void endResidency() {
        sendVoid(id, "endResidency");
    }

    @SneakyThrows
    public void addAllocation(MTLResource resource) {
        P.invokeExact(id, ObjC.sel("addAllocation:"), resource.getId());
    }

    @SneakyThrows
    public void addAllocations(MemorySegment resources, long count) {
        AL.invokeExact(id, ObjC.sel("addAllocations:count:"), resources, count);
    }

    @SneakyThrows
    public void removeAllocation(MTLResource resource) {
        P.invokeExact(id, ObjC.sel("removeAllocation:"), resource.getId());
    }

    @SneakyThrows
    public void removeAllocations(MemorySegment resources, long count) {
        AL.invokeExact(id, ObjC.sel("removeAllocations:count:"), resources, count);
    }

    public void removeAllAllocations() {
        sendVoid(id, "removeAllAllocations");
    }

    @SneakyThrows
    public boolean containsAllocation(MTLResource resource) {
        return (boolean) B_P.invokeExact(id, ObjC.sel("containsAllocation:"), resource.getId());
    }

    public NSArray allAllocations() {
        return NSArray.of(sendPtr(id, "allAllocations"));
    }

    public long allocationCount() {
        return sendLong(id, "allocationCount");
    }

    public void commit() {
        sendVoid(id, "commit");
    }
}
