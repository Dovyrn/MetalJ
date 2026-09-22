package dev.dov.metalj.device;

import dev.dov.metalj.debug.MTLLogState;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCommandQueueDescriptor extends NSObject {
    private static final long MTL_COMMAND_QUEUE_DESCRIPTOR = ObjC.cls("MTLCommandQueueDescriptor");

    private static final long LOG_STATE = ObjC.sel("logState");
    private static final long MAX_COMMAND_BUFFER_COUNT = ObjC.sel("maxCommandBufferCount");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_LOG_STATE = ObjC.sel("setLogState:");
    private static final long SET_MAX_COMMAND_BUFFER_COUNT = ObjC.sel("setMaxCommandBufferCount:");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLCommandQueueDescriptor(long id) {
        super(id);
    }

    public static MTLCommandQueueDescriptor of(long id) {
        return new MTLCommandQueueDescriptor(id);
    }

    public static MTLCommandQueueDescriptor new_() {
        return new MTLCommandQueueDescriptor(sendPtr(MTL_COMMAND_QUEUE_DESCRIPTOR, NEW));
    }

    public long maxCommandBufferCount() {
        return sendLong(id, MAX_COMMAND_BUFFER_COUNT);
    }

    @SneakyThrows
    public void setMaxCommandBufferCount(long count) {
        L.invokeExact(id, SET_MAX_COMMAND_BUFFER_COUNT, count);
    }

    public MTLLogState logState() {
        return MTLLogState.of(sendPtr(id, LOG_STATE));
    }

    @SneakyThrows
    public void setLogState(MTLLogState state) {
        P.invokeExact(id, SET_LOG_STATE, state.getId());
    }
}
