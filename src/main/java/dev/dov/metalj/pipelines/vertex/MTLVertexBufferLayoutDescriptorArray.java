package dev.dov.metalj.pipelines.vertex;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLVertexBufferLayoutDescriptorArray extends NSObject {
    private static final long OBJECT_AT_INDEXED_SUBSCRIPT = ObjC.sel("objectAtIndexedSubscript:");
    private static final long SET_OBJECT_AT_INDEXED_SUBSCRIPT = ObjC.sel("setObject:atIndexedSubscript:");

    private static final MethodHandle AT = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle SET = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLVertexBufferLayoutDescriptorArray(long id) {
        super(id);
    }

    public static MTLVertexBufferLayoutDescriptorArray of(long id) {
        return new MTLVertexBufferLayoutDescriptorArray(id);
    }

    @SneakyThrows
    public MTLVertexBufferLayoutDescriptor objectAtIndexedSubscript(long index) {
        return MTLVertexBufferLayoutDescriptor.of((long) AT.invokeExact(id, OBJECT_AT_INDEXED_SUBSCRIPT, index));
    }

    @SneakyThrows
    public void setObject(MTLVertexBufferLayoutDescriptor object, long index) {
        SET.invokeExact(id, SET_OBJECT_AT_INDEXED_SUBSCRIPT, object.getId(), index);
    }
}
