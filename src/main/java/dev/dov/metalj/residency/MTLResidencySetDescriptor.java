package dev.dov.metalj.residency;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLResidencySetDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLResidencySetDescriptor(long id) {
        super(id);
    }

    public static MTLResidencySetDescriptor of(long id) {
        return new MTLResidencySetDescriptor(id);
    }

    public static MTLResidencySetDescriptor new_() {
        return new MTLResidencySetDescriptor(sendPtr(ObjC.cls("MTLResidencySetDescriptor"), "new"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    public long initialCapacity() {
        return sendLong(id, "initialCapacity");
    }

    @SneakyThrows
    public void setInitialCapacity(long capacity) {
        L.invokeExact(id, ObjC.sel("setInitialCapacity:"), capacity);
    }
}
