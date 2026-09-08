package dev.dov.metalj.sync;

import dev.dov.metalj.objc.Block;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.LongConsumer;
import lombok.SneakyThrows;

public class MTLSharedEvent extends MTLEvent {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle PLP = handle(null, ObjC.PTR, ObjC.LONG, ObjC.PTR);
    private static final MethodHandle B_LL = handle(ObjC.BOOL, ObjC.LONG, ObjC.LONG);

    private MTLSharedEvent(long id) {
        super(id);
    }

    public static MTLSharedEvent of(long id) {
        return new MTLSharedEvent(id);
    }

    public long signaledValue() {
        return sendLong(id, "signaledValue");
    }

    @SneakyThrows
    public void setSignaledValue(long value) {
        L.invokeExact(id, ObjC.sel("setSignaledValue:"), value);
    }

    public MTLSharedEventHandle newSharedEventHandle() {
        return MTLSharedEventHandle.of(sendPtr(id, "newSharedEventHandle"));
    }

    @SneakyThrows
    public void notifyListener(MTLSharedEventListener listener, long value, Block block) {
        PLP.invokeExact(id, ObjC.sel("notifyListener:atValue:block:"), listener.getId(), value, block.address());
    }

    @SneakyThrows
    public boolean waitUntilSignaledValue(long value, long milliseconds) {
        return (boolean) B_LL.invokeExact(id, ObjC.sel("waitUntilSignaledValue:timeoutMS:"), value, milliseconds);
    }

    @SneakyThrows
    public static Block listener(LongConsumer signaled) {
        var target = MethodHandles.lookup().findStatic(MTLSharedEvent.class, "call",
                MethodType.methodType(void.class, LongConsumer.class, MemorySegment.class, long.class, long.class));
        var descriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ObjC.PTR, ObjC.LONG);
        return Block.of(target.bindTo(signaled), descriptor);
    }

    private static void call(LongConsumer signaled, MemorySegment block, long event, long value) {
        signaled.accept(value);
    }
}
