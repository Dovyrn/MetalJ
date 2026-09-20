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
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    public long count() {
        return sendLong(id, "count");
    }

    public long type() {
        return sendLong(id, "type");
    }

    @SneakyThrows
    public NSData resolveCounterRange(MemorySegment range) {
        return NSData.of((long) P_R.invokeExact(id, ObjC.sel("resolveCounterRange:"), range));
    }

    @SneakyThrows
    public void invalidateCounterRange(MemorySegment range) {
        R.invokeExact(id, ObjC.sel("invalidateCounterRange:"), range);
    }
}
