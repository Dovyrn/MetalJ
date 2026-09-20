package dev.dov.metalj.metal4;

import dev.dov.metalj.debug.MTLLogState;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CommandBufferOptions extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4CommandBufferOptions(long id) {
        super(id);
    }

    public static MTL4CommandBufferOptions of(long id) {
        return new MTL4CommandBufferOptions(id);
    }

    public static MTL4CommandBufferOptions new_() {
        return new MTL4CommandBufferOptions(sendPtr(ObjC.cls("MTL4CommandBufferOptions"), "new"));
    }

    public MTLLogState logState() {
        return MTLLogState.of(sendPtr(id, "logState"));
    }

    @SneakyThrows
    public void setLogState(MTLLogState state) {
        P.invokeExact(id, ObjC.sel("setLogState:"), state.getId());
    }
}
