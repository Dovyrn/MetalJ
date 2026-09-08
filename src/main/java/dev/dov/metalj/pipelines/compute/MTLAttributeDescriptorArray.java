package dev.dov.metalj.pipelines.compute;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLAttributeDescriptorArray extends NSObject {
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLAttributeDescriptorArray(long id) {
        super(id);
    }

    public static MTLAttributeDescriptorArray of(long id) {
        return new MTLAttributeDescriptorArray(id);
    }

    @SneakyThrows
    public MTLAttributeDescriptor objectAtIndexedSubscript(long index) {
        return MTLAttributeDescriptor.of((long) P_L.invokeExact(id, ObjC.sel("objectAtIndexedSubscript:"), index));
    }

    @SneakyThrows
    public void setObjectAtIndexedSubscript(MTLAttributeDescriptor attribute, long index) {
        PL.invokeExact(id, ObjC.sel("setObject:atIndexedSubscript:"), attribute.getId(), index);
    }
}
