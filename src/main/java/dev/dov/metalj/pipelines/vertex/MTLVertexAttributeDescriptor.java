package dev.dov.metalj.pipelines.vertex;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLVertexAttributeDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLVertexAttributeDescriptor(long id) {
        super(id);
    }

    public static MTLVertexAttributeDescriptor of(long id) {
        return new MTLVertexAttributeDescriptor(id);
    }

    public static MTLVertexAttributeDescriptor new_() {
        return new MTLVertexAttributeDescriptor(sendPtr(ObjC.cls("MTLVertexAttributeDescriptor"), "new"));
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
    public void setBufferIndex(long bufferIndex) {
        L.invokeExact(id, ObjC.sel("setBufferIndex:"), bufferIndex);
    }
}
