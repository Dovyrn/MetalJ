package dev.dov.metalj.commands.passes;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPassSampleBufferAttachmentDescriptorArray extends NSObject {
    private static final MethodHandle AT = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle SET = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLRenderPassSampleBufferAttachmentDescriptorArray(long id) {
        super(id);
    }

    public static MTLRenderPassSampleBufferAttachmentDescriptorArray of(long id) {
        return new MTLRenderPassSampleBufferAttachmentDescriptorArray(id);
    }

    @SneakyThrows
    public MTLRenderPassSampleBufferAttachmentDescriptor objectAtIndexedSubscript(long attachmentIndex) {
        return MTLRenderPassSampleBufferAttachmentDescriptor.of((long) AT.invokeExact(id, ObjC.sel("objectAtIndexedSubscript:"), attachmentIndex));
    }

    @SneakyThrows
    public void setObject(MTLRenderPassSampleBufferAttachmentDescriptor attachment, long attachmentIndex) {
        SET.invokeExact(id, ObjC.sel("setObject:atIndexedSubscript:"), attachment.getId(), attachmentIndex);
    }
}
