package dev.dov.metalj.pipelines.compute;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLBufferLayoutDescriptorArray extends NSObject {
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLBufferLayoutDescriptorArray(long id) {
        super(id);
    }

    public static MTLBufferLayoutDescriptorArray of(long id) {
        return new MTLBufferLayoutDescriptorArray(id);
    }

    @SneakyThrows
    public MTLBufferLayoutDescriptor objectAtIndexedSubscript(long index) {
        return MTLBufferLayoutDescriptor.of((long) P_L.invokeExact(id, ObjC.sel("objectAtIndexedSubscript:"), index));
    }

    @SneakyThrows
    public void setObjectAtIndexedSubscript(MTLBufferLayoutDescriptor layout, long index) {
        PL.invokeExact(id, ObjC.sel("setObject:atIndexedSubscript:"), layout.getId(), index);
    }
}
