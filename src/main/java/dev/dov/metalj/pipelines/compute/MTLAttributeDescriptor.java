package dev.dov.metalj.pipelines.compute;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLAttributeDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLAttributeDescriptor(long id) {
        super(id);
    }

    public static MTLAttributeDescriptor of(long id) {
        return new MTLAttributeDescriptor(id);
    }

    public long format() {
        return sendLong(id, "format");
    }

    @SneakyThrows
    public void setFormat(long format) {
        L.invokeExact(id, ObjC.sel("setFormat:"), format);
    }

    public long offset() {
        return sendLong(id, "offset");
    }

    @SneakyThrows
    public void setOffset(long offset) {
        L.invokeExact(id, ObjC.sel("setOffset:"), offset);
    }

    public long bufferIndex() {
        return sendLong(id, "bufferIndex");
    }

    @SneakyThrows
    public void setBufferIndex(long index) {
        L.invokeExact(id, ObjC.sel("setBufferIndex:"), index);
    }
}
