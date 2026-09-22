package dev.dov.metalj.pools;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLResourceViewPoolDescriptor extends NSObject {
    private static final long MTL_RESOURCE_VIEW_POOL_DESCRIPTOR = ObjC.cls("MTLResourceViewPoolDescriptor");

    private static final long LABEL = ObjC.sel("label");
    private static final long NEW = ObjC.sel("new");
    private static final long RESOURCE_VIEW_COUNT = ObjC.sel("resourceViewCount");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_RESOURCE_VIEW_COUNT = ObjC.sel("setResourceViewCount:");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLResourceViewPoolDescriptor(long id) {
        super(id);
    }

    public static MTLResourceViewPoolDescriptor of(long id) {
        return new MTLResourceViewPoolDescriptor(id);
    }

    public static MTLResourceViewPoolDescriptor new_() {
        return new MTLResourceViewPoolDescriptor(sendPtr(MTL_RESOURCE_VIEW_POOL_DESCRIPTOR, NEW));
    }

    public long resourceViewCount() {
        return sendLong(id, RESOURCE_VIEW_COUNT);
    }

    @SneakyThrows
    public void setResourceViewCount(long count) {
        L.invokeExact(id, SET_RESOURCE_VIEW_COUNT, count);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }
}
