package dev.dov.metalj.metal4;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CommandAllocatorDescriptor extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4CommandAllocatorDescriptor(long id) {
        super(id);
    }

    public static MTL4CommandAllocatorDescriptor of(long id) {
        return new MTL4CommandAllocatorDescriptor(id);
    }

    public static MTL4CommandAllocatorDescriptor new_() {
        return new MTL4CommandAllocatorDescriptor(sendPtr(ObjC.cls("MTL4CommandAllocatorDescriptor"), "new"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }
}
