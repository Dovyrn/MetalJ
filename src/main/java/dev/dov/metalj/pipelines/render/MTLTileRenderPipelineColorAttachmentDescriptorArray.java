package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTileRenderPipelineColorAttachmentDescriptorArray extends NSObject {
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLTileRenderPipelineColorAttachmentDescriptorArray(long id) {
        super(id);
    }

    public static MTLTileRenderPipelineColorAttachmentDescriptorArray of(long id) {
        return new MTLTileRenderPipelineColorAttachmentDescriptorArray(id);
    }

    @SneakyThrows
    public MTLTileRenderPipelineColorAttachmentDescriptor objectAtIndexedSubscript(long index) {
        return MTLTileRenderPipelineColorAttachmentDescriptor.of(
                (long) P_L.invokeExact(id, ObjC.sel("objectAtIndexedSubscript:"), index));
    }

    @SneakyThrows
    public void setObjectAtIndexedSubscript(MTLTileRenderPipelineColorAttachmentDescriptor attachment, long index) {
        PL.invokeExact(id, ObjC.sel("setObject:atIndexedSubscript:"), attachment.getId(), index);
    }
}
