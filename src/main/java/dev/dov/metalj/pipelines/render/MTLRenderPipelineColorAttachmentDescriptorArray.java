package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPipelineColorAttachmentDescriptorArray extends NSObject {
    private static final MethodHandle AT = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle SET = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLRenderPipelineColorAttachmentDescriptorArray(long id) {
        super(id);
    }

    public static MTLRenderPipelineColorAttachmentDescriptorArray of(long id) {
        return new MTLRenderPipelineColorAttachmentDescriptorArray(id);
    }

    @SneakyThrows
    public MTLRenderPipelineColorAttachmentDescriptor objectAtIndexedSubscript(long index) {
        return MTLRenderPipelineColorAttachmentDescriptor.of((long) AT.invokeExact(id, ObjC.sel("objectAtIndexedSubscript:"), index));
    }

    @SneakyThrows
    public void setObject(MTLRenderPipelineColorAttachmentDescriptor object, long index) {
        SET.invokeExact(id, ObjC.sel("setObject:atIndexedSubscript:"), object.getId(), index);
    }
}
