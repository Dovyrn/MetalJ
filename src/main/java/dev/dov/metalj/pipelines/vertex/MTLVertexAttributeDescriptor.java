package dev.dov.metalj.pipelines.vertex;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLVertexAttributeDescriptor extends NSObject {
    private static final long MTL_VERTEX_ATTRIBUTE_DESCRIPTOR = ObjC.cls("MTLVertexAttributeDescriptor");

    private static final long BUFFER_INDEX = ObjC.sel("bufferIndex");
    private static final long FORMAT = ObjC.sel("format");
    private static final long NEW = ObjC.sel("new");
    private static final long OFFSET = ObjC.sel("offset");
    private static final long SET_BUFFER_INDEX = ObjC.sel("setBufferIndex:");
    private static final long SET_FORMAT = ObjC.sel("setFormat:");
    private static final long SET_OFFSET = ObjC.sel("setOffset:");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLVertexAttributeDescriptor(long id) {
        super(id);
    }

    public static MTLVertexAttributeDescriptor of(long id) {
        return new MTLVertexAttributeDescriptor(id);
    }

    public static MTLVertexAttributeDescriptor new_() {
        return new MTLVertexAttributeDescriptor(sendPtr(MTL_VERTEX_ATTRIBUTE_DESCRIPTOR, NEW));
    }

    public long format() {
        return sendLong(id, FORMAT);
    }

    @SneakyThrows
    public void setFormat(long format) {
        L.invokeExact(id, SET_FORMAT, format);
    }

    public long offset() {
        return sendLong(id, OFFSET);
    }

    @SneakyThrows
    public void setOffset(long offset) {
        L.invokeExact(id, SET_OFFSET, offset);
    }

    public long bufferIndex() {
        return sendLong(id, BUFFER_INDEX);
    }

    @SneakyThrows
    public void setBufferIndex(long bufferIndex) {
        L.invokeExact(id, SET_BUFFER_INDEX, bufferIndex);
    }
}
