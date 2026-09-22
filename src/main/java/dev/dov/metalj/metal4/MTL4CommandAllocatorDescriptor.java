package dev.dov.metalj.metal4;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CommandAllocatorDescriptor extends NSObject {
    private static final long MTL_4_COMMAND_ALLOCATOR_DESCRIPTOR = ObjC.cls("MTL4CommandAllocatorDescriptor");

    private static final long LABEL = ObjC.sel("label");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_LABEL = ObjC.sel("setLabel:");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4CommandAllocatorDescriptor(long id) {
        super(id);
    }

    public static MTL4CommandAllocatorDescriptor of(long id) {
        return new MTL4CommandAllocatorDescriptor(id);
    }

    public static MTL4CommandAllocatorDescriptor new_() {
        return new MTL4CommandAllocatorDescriptor(sendPtr(MTL_4_COMMAND_ALLOCATOR_DESCRIPTOR, NEW));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }
}
