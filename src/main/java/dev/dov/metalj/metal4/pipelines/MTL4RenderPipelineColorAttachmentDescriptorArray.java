package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4RenderPipelineColorAttachmentDescriptorArray extends NSObject {
    private static final MethodHandle AT = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle SET = handle(null, ObjC.PTR, ObjC.LONG);

    private MTL4RenderPipelineColorAttachmentDescriptorArray(long id) {
        super(id);
    }

    public static MTL4RenderPipelineColorAttachmentDescriptorArray of(long id) {
        return new MTL4RenderPipelineColorAttachmentDescriptorArray(id);
    }

    @SneakyThrows
    public MTL4RenderPipelineColorAttachmentDescriptor objectAtIndexedSubscript(long index) {
        return MTL4RenderPipelineColorAttachmentDescriptor.of(
                (long) AT.invokeExact(id, ObjC.sel("objectAtIndexedSubscript:"), index));
    }

    @SneakyThrows
    public void setObject(MTL4RenderPipelineColorAttachmentDescriptor object, long index) {
        SET.invokeExact(id, ObjC.sel("setObject:atIndexedSubscript:"), object.getId(), index);
    }

    public void reset() {
        sendVoid(id, "reset");
    }
}
