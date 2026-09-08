package dev.dov.metalj.pools;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLResourceViewPoolDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLResourceViewPoolDescriptor(long id) {
        super(id);
    }

    public static MTLResourceViewPoolDescriptor of(long id) {
        return new MTLResourceViewPoolDescriptor(id);
    }

    public static MTLResourceViewPoolDescriptor new_() {
        return new MTLResourceViewPoolDescriptor(sendPtr(ObjC.cls("MTLResourceViewPoolDescriptor"), "new"));
    }

    public long resourceViewCount() {
        return sendLong(id, "resourceViewCount");
    }

    @SneakyThrows
    public void setResourceViewCount(long count) {
        L.invokeExact(id, ObjC.sel("setResourceViewCount:"), count);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }
}
