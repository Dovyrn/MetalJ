package dev.dov.metalj.metal4.counters;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CounterHeapDescriptor extends NSObject {
    private static final long MTL_4_COUNTER_HEAP_DESCRIPTOR = ObjC.cls("MTL4CounterHeapDescriptor");

    private static final long COUNT = ObjC.sel("count");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_COUNT = ObjC.sel("setCount:");
    private static final long SET_TYPE = ObjC.sel("setType:");
    private static final long TYPE = ObjC.sel("type");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTL4CounterHeapDescriptor(long id) {
        super(id);
    }

    public static MTL4CounterHeapDescriptor of(long id) {
        return new MTL4CounterHeapDescriptor(id);
    }

    public static MTL4CounterHeapDescriptor new_() {
        return new MTL4CounterHeapDescriptor(sendPtr(MTL_4_COUNTER_HEAP_DESCRIPTOR, NEW));
    }

    public long type() {
        return sendLong(id, TYPE);
    }

    @SneakyThrows
    public void setType(long type) {
        L.invokeExact(id, SET_TYPE, type);
    }

    public long count() {
        return sendLong(id, COUNT);
    }

    @SneakyThrows
    public void setCount(long count) {
        L.invokeExact(id, SET_COUNT, count);
    }
}
