package dev.dov.metalj.commands.passes;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPassColorAttachmentDescriptorArray extends NSObject {
    private static final long OBJECT_AT_INDEXED_SUBSCRIPT = ObjC.sel("objectAtIndexedSubscript:");
    private static final long SET_OBJECT_AT_INDEXED_SUBSCRIPT = ObjC.sel("setObject:atIndexedSubscript:");

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
        return MTLRenderPassColorAttachmentDescriptor.of((long) AT.invokeExact(id, OBJECT_AT_INDEXED_SUBSCRIPT, attachmentIndex));
    }

    @SneakyThrows
    public void setObject(MTLRenderPassColorAttachmentDescriptor attachment, long attachmentIndex) {
        SET.invokeExact(id, SET_OBJECT_AT_INDEXED_SUBSCRIPT, attachment.getId(), attachmentIndex);
    }
}
