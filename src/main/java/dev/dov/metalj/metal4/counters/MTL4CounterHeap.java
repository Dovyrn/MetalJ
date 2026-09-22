package dev.dov.metalj.metal4.counters;

import dev.dov.metalj.objc.NSData;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CounterHeap extends NSObject {
    private static final long COUNT = ObjC.sel("count");
    private static final long INVALIDATE_COUNTER_RANGE = ObjC.sel("invalidateCounterRange:");
    private static final long LABEL = ObjC.sel("label");
    private static final long RESOLVE_COUNTER_RANGE = ObjC.sel("resolveCounterRange:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long TYPE = ObjC.sel("type");

    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle R = handle(null, NSRange.LAYOUT);
    private static final MethodHandle P_R = handle(ObjC.PTR, NSRange.LAYOUT);

    private MTL4CounterHeap(long id) {
        super(id);
    }

    public static MTL4CounterHeap of(long id) {
        return new MTL4CounterHeap(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public long count() {
        return sendLong(id, COUNT);
    }

    public long type() {
        return sendLong(id, TYPE);
    }

    @SneakyThrows
    public NSData resolveCounterRange(MemorySegment range) {
        return NSData.of((long) P_R.invokeExact(id, RESOLVE_COUNTER_RANGE, range));
    }

    @SneakyThrows
    public void invalidateCounterRange(MemorySegment range) {
        R.invokeExact(id, INVALIDATE_COUNTER_RANGE, range);
    }
}
