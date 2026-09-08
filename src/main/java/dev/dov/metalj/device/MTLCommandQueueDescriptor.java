package dev.dov.metalj.device;

import dev.dov.metalj.debug.MTLLogState;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCommandQueueDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLCommandQueueDescriptor(long id) {
        super(id);
    }

    public static MTLCommandQueueDescriptor of(long id) {
        return new MTLCommandQueueDescriptor(id);
    }

    public static MTLCommandQueueDescriptor new_() {
        return new MTLCommandQueueDescriptor(sendPtr(ObjC.cls("MTLCommandQueueDescriptor"), "new"));
    }

    public long maxCommandBufferCount() {
        return sendLong(id, "maxCommandBufferCount");
    }

    @SneakyThrows
    public void setMaxCommandBufferCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxCommandBufferCount:"), count);
    }

    public MTLLogState logState() {
        return MTLLogState.of(sendPtr(id, "logState"));
    }

    @SneakyThrows
    public void setLogState(MTLLogState state) {
        P.invokeExact(id, ObjC.sel("setLogState:"), state.getId());
    }
}
