package dev.dov.metalj.metal4;

import dev.dov.metalj.debug.MTLLogState;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CommandBufferOptions extends NSObject {
    private static final long MTL_4_COMMAND_BUFFER_OPTIONS = ObjC.cls("MTL4CommandBufferOptions");

    private static final long LOG_STATE = ObjC.sel("logState");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_LOG_STATE = ObjC.sel("setLogState:");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4CommandBufferOptions(long id) {
        super(id);
    }

    public static MTL4CommandBufferOptions of(long id) {
        return new MTL4CommandBufferOptions(id);
    }

    public static MTL4CommandBufferOptions new_() {
        return new MTL4CommandBufferOptions(sendPtr(MTL_4_COMMAND_BUFFER_OPTIONS, NEW));
    }

    public MTLLogState logState() {
        return MTLLogState.of(sendPtr(id, LOG_STATE));
    }

    @SneakyThrows
    public void setLogState(MTLLogState state) {
        P.invokeExact(id, SET_LOG_STATE, state.getId());
    }
}
