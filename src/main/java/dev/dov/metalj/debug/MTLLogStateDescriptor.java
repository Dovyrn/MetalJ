package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLLogStateDescriptor extends NSObject {
    private static final long MTL_LOG_STATE_DESCRIPTOR = ObjC.cls("MTLLogStateDescriptor");

    private static final long BUFFER_SIZE = ObjC.sel("bufferSize");
    private static final long LEVEL = ObjC.sel("level");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_BUFFER_SIZE = ObjC.sel("setBufferSize:");
    private static final long SET_LEVEL = ObjC.sel("setLevel:");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLLogStateDescriptor(long id) {
        super(id);
    }

    public static MTLLogStateDescriptor of(long id) {
        return new MTLLogStateDescriptor(id);
    }

    public static MTLLogStateDescriptor new_() {
        return new MTLLogStateDescriptor(sendPtr(MTL_LOG_STATE_DESCRIPTOR, NEW));
    }

    public long level() {
        return sendLong(id, LEVEL);
    }

    @SneakyThrows
    public void setLevel(long level) {
        L.invokeExact(id, SET_LEVEL, level);
    }

    public long bufferSize() {
        return sendLong(id, BUFFER_SIZE);
    }

    @SneakyThrows
    public void setBufferSize(long bufferSize) {
        L.invokeExact(id, SET_BUFFER_SIZE, bufferSize);
    }
}
