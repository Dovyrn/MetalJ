package dev.dov.metalj.commands;

import dev.dov.metalj.debug.MTLLogState;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCommandBufferDescriptor extends NSObject {
    private static final long MTL_COMMAND_BUFFER_DESCRIPTOR = ObjC.cls("MTLCommandBufferDescriptor");

    private static final long ERROR_OPTIONS = ObjC.sel("errorOptions");
    private static final long LOG_STATE = ObjC.sel("logState");
    private static final long NEW = ObjC.sel("new");
    private static final long RETAINED_REFERENCES = ObjC.sel("retainedReferences");
    private static final long SET_ERROR_OPTIONS = ObjC.sel("setErrorOptions:");
    private static final long SET_LOG_STATE = ObjC.sel("setLogState:");
    private static final long SET_RETAINED_REFERENCES = ObjC.sel("setRetainedReferences:");

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
        return new MTLCommandBufferDescriptor(sendPtr(MTL_COMMAND_BUFFER_DESCRIPTOR, NEW));
    }

    public boolean retainedReferences() {
        return sendBool(id, RETAINED_REFERENCES);
    }

    @SneakyThrows
    public void setRetainedReferences(boolean retained) {
        B.invokeExact(id, SET_RETAINED_REFERENCES, retained);
    }

    public long errorOptions() {
        return sendLong(id, ERROR_OPTIONS);
    }

    @SneakyThrows
    public void setErrorOptions(long options) {
        L.invokeExact(id, SET_ERROR_OPTIONS, options);
    }

    public MTLLogState logState() {
        return MTLLogState.of(sendPtr(id, LOG_STATE));
    }

    @SneakyThrows
    public void setLogState(MTLLogState state) {
        P.invokeExact(id, SET_LOG_STATE, state.getId());
    }
}
