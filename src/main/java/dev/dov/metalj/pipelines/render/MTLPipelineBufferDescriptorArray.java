package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLPipelineBufferDescriptorArray extends NSObject {
    private static final MethodHandle AT = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle SET = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLPipelineBufferDescriptorArray(long id) {
        super(id);
    }

    public static MTLPipelineBufferDescriptorArray of(long id) {
        return new MTLPipelineBufferDescriptorArray(id);
    }

    @SneakyThrows
    public MTLPipelineBufferDescriptor objectAtIndexedSubscript(long index) {
        return MTLPipelineBufferDescriptor.of((long) AT.invokeExact(id, ObjC.sel("objectAtIndexedSubscript:"), index));
    }

    @SneakyThrows
    public void setObject(MTLPipelineBufferDescriptor object, long index) {
        SET.invokeExact(id, ObjC.sel("setObject:atIndexedSubscript:"), object.getId(), index);
    }
}
