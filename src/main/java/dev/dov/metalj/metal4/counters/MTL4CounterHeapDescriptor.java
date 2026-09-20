package dev.dov.metalj.metal4.counters;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CounterHeapDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTL4CounterHeapDescriptor(long id) {
        super(id);
    }

    public static MTL4CounterHeapDescriptor of(long id) {
        return new MTL4CounterHeapDescriptor(id);
    }

    public static MTL4CounterHeapDescriptor new_() {
        return new MTL4CounterHeapDescriptor(sendPtr(ObjC.cls("MTL4CounterHeapDescriptor"), "new"));
    }

    public long type() {
        return sendLong(id, "type");
    }

    @SneakyThrows
    public void setType(long type) {
        L.invokeExact(id, ObjC.sel("setType:"), type);
    }

    public long count() {
        return sendLong(id, "count");
    }

    @SneakyThrows
    public void setCount(long count) {
        L.invokeExact(id, ObjC.sel("setCount:"), count);
    }
}
