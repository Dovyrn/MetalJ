package dev.dov.metalj.commands;

import dev.dov.metalj.debug.MTLLogState;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCommandBufferDescriptor extends NSObject {
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    public static final long MTLCommandBufferErrorOptionNone = 0;
    public static final long MTLCommandBufferErrorOptionEncoderExecutionStatus = 1;

    private MTLCommandBufferDescriptor(long id) {
        super(id);
    }

    public static MTLCommandBufferDescriptor of(long id) {
        return new MTLCommandBufferDescriptor(id);
    }

    public static MTLCommandBufferDescriptor new_() {
        return new MTLCommandBufferDescriptor(sendPtr(ObjC.cls("MTLCommandBufferDescriptor"), "new"));
    }

    public boolean retainedReferences() {
        return sendBool(id, "retainedReferences");
    }

    @SneakyThrows
    public void setRetainedReferences(boolean retained) {
        B.invokeExact(id, ObjC.sel("setRetainedReferences:"), retained);
    }

    public long errorOptions() {
        return sendLong(id, "errorOptions");
    }

    @SneakyThrows
    public void setErrorOptions(long options) {
        L.invokeExact(id, ObjC.sel("setErrorOptions:"), options);
    }

    public MTLLogState logState() {
        return MTLLogState.of(sendPtr(id, "logState"));
    }

    @SneakyThrows
    public void setLogState(MTLLogState state) {
        P.invokeExact(id, ObjC.sel("setLogState:"), state.getId());
    }
}
