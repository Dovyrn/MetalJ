package dev.dov.metalj.commands;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLBlitPassSampleBufferAttachmentDescriptorArray extends NSObject {
    private static final MethodHandle AT = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle SET = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLBlitPassSampleBufferAttachmentDescriptorArray(long id) {
        super(id);
    }

    public static MTLBlitPassSampleBufferAttachmentDescriptorArray of(long id) {
        return new MTLBlitPassSampleBufferAttachmentDescriptorArray(id);
    }

    @SneakyThrows
    public MTLBlitPassSampleBufferAttachmentDescriptor objectAtIndexedSubscript(long attachmentIndex) {
        return MTLBlitPassSampleBufferAttachmentDescriptor.of((long) AT.invokeExact(id, ObjC.sel("objectAtIndexedSubscript:"), attachmentIndex));
    }

    @SneakyThrows
    public void setObject(MTLBlitPassSampleBufferAttachmentDescriptor attachment, long attachmentIndex) {
        SET.invokeExact(id, ObjC.sel("setObject:atIndexedSubscript:"), attachment.getId(), attachmentIndex);
    }
}
