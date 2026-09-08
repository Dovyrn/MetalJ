package dev.dov.metalj.pipelines.vertex;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLVertexAttributeDescriptorArray extends NSObject {
    private static final MethodHandle AT = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle SET = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLVertexAttributeDescriptorArray(long id) {
        super(id);
    }

    public static MTLVertexAttributeDescriptorArray of(long id) {
        return new MTLVertexAttributeDescriptorArray(id);
    }

    @SneakyThrows
    public MTLVertexAttributeDescriptor objectAtIndexedSubscript(long index) {
        return MTLVertexAttributeDescriptor.of((long) AT.invokeExact(id, ObjC.sel("objectAtIndexedSubscript:"), index));
    }

    @SneakyThrows
    public void setObject(MTLVertexAttributeDescriptor object, long index) {
        SET.invokeExact(id, ObjC.sel("setObject:atIndexedSubscript:"), object.getId(), index);
    }
}
