package dev.dov.metalj.commands;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPassColorAttachmentDescriptorArray extends NSObject {
    private static final MethodHandle AT = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle SET = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLRenderPassColorAttachmentDescriptorArray(long id) {
        super(id);
    }

    public static MTLRenderPassColorAttachmentDescriptorArray of(long id) {
        return new MTLRenderPassColorAttachmentDescriptorArray(id);
    }

    @SneakyThrows
    public MTLRenderPassColorAttachmentDescriptor objectAtIndexedSubscript(long attachmentIndex) {
        return MTLRenderPassColorAttachmentDescriptor.of((long) AT.invokeExact(id, ObjC.sel("objectAtIndexedSubscript:"), attachmentIndex));
    }

    @SneakyThrows
    public void setObject(MTLRenderPassColorAttachmentDescriptor attachment, long attachmentIndex) {
        SET.invokeExact(id, ObjC.sel("setObject:atIndexedSubscript:"), attachment.getId(), attachmentIndex);
    }
}
