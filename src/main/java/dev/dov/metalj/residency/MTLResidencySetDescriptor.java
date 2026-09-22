package dev.dov.metalj.residency;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLResidencySetDescriptor extends NSObject {
    private static final long MTL_RESIDENCY_SET_DESCRIPTOR = ObjC.cls("MTLResidencySetDescriptor");

    private static final long INITIAL_CAPACITY = ObjC.sel("initialCapacity");
    private static final long LABEL = ObjC.sel("label");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_INITIAL_CAPACITY = ObjC.sel("setInitialCapacity:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLResidencySetDescriptor(long id) {
        super(id);
    }

    public static MTLResidencySetDescriptor of(long id) {
        return new MTLResidencySetDescriptor(id);
    }

    public static MTLResidencySetDescriptor new_() {
        return new MTLResidencySetDescriptor(sendPtr(MTL_RESIDENCY_SET_DESCRIPTOR, NEW));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public long initialCapacity() {
        return sendLong(id, INITIAL_CAPACITY);
    }

    @SneakyThrows
    public void setInitialCapacity(long capacity) {
        L.invokeExact(id, SET_INITIAL_CAPACITY, capacity);
    }
}
