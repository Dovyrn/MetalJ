package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLLogStateDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLLogStateDescriptor(long id) {
        super(id);
    }

    public static MTLLogStateDescriptor of(long id) {
        return new MTLLogStateDescriptor(id);
    }

    public static MTLLogStateDescriptor new_() {
        return new MTLLogStateDescriptor(sendPtr(ObjC.cls("MTLLogStateDescriptor"), "new"));
    }

    public long level() {
        return sendLong(id, "level");
    }

    @SneakyThrows
    public void setLevel(long level) {
        L.invokeExact(id, ObjC.sel("setLevel:"), level);
    }

    public long bufferSize() {
        return sendLong(id, "bufferSize");
    }

    @SneakyThrows
    public void setBufferSize(long bufferSize) {
        L.invokeExact(id, ObjC.sel("setBufferSize:"), bufferSize);
    }
}
