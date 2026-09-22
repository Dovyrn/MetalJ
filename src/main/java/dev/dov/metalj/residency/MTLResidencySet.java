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
    private static final long ADD_ALLOCATION = ObjC.sel("addAllocation:");
    private static final long ADD_ALLOCATIONS_COUNT = ObjC.sel("addAllocations:count:");
    private static final long ALL_ALLOCATIONS = ObjC.sel("allAllocations");
    private static final long ALLOCATED_SIZE = ObjC.sel("allocatedSize");
    private static final long ALLOCATION_COUNT = ObjC.sel("allocationCount");
    private static final long COMMIT = ObjC.sel("commit");
    private static final long CONTAINS_ALLOCATION = ObjC.sel("containsAllocation:");
    private static final long DEVICE = ObjC.sel("device");
    private static final long END_RESIDENCY = ObjC.sel("endResidency");
    private static final long LABEL = ObjC.sel("label");
    private static final long REMOVE_ALL_ALLOCATIONS = ObjC.sel("removeAllAllocations");
    private static final long REMOVE_ALLOCATION = ObjC.sel("removeAllocation:");
    private static final long REMOVE_ALLOCATIONS_COUNT = ObjC.sel("removeAllocations:count:");
    private static final long REQUEST_RESIDENCY = ObjC.sel("requestResidency");

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
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    public long allocatedSize() {
        return sendLong(id, ALLOCATED_SIZE);
    }

    public void requestResidency() {
        sendVoid(id, REQUEST_RESIDENCY);
    }

    public void endResidency() {
        sendVoid(id, END_RESIDENCY);
    }

    @SneakyThrows
    public void addAllocation(MTLResource resource) {
        P.invokeExact(id, ADD_ALLOCATION, resource.getId());
    }

    @SneakyThrows
    public void addAllocations(MemorySegment resources, long count) {
        AL.invokeExact(id, ADD_ALLOCATIONS_COUNT, resources, count);
    }

    @SneakyThrows
    public void removeAllocation(MTLResource resource) {
        P.invokeExact(id, REMOVE_ALLOCATION, resource.getId());
    }

    @SneakyThrows
    public void removeAllocations(MemorySegment resources, long count) {
        AL.invokeExact(id, REMOVE_ALLOCATIONS_COUNT, resources, count);
    }

    public void removeAllAllocations() {
        sendVoid(id, REMOVE_ALL_ALLOCATIONS);
    }

    @SneakyThrows
    public boolean containsAllocation(MTLResource resource) {
        return (boolean) B_P.invokeExact(id, CONTAINS_ALLOCATION, resource.getId());
    }

    public NSArray allAllocations() {
        return NSArray.of(sendPtr(id, ALL_ALLOCATIONS));
    }

    public long allocationCount() {
        return sendLong(id, ALLOCATION_COUNT);
    }

    public void commit() {
        sendVoid(id, COMMIT);
    }
}
