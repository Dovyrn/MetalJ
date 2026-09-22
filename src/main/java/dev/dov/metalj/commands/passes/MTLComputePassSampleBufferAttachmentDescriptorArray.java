package dev.dov.metalj.commands.passes;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLComputePassSampleBufferAttachmentDescriptorArray extends NSObject {
    private static final long OBJECT_AT_INDEXED_SUBSCRIPT = ObjC.sel("objectAtIndexedSubscript:");
    private static final long SET_OBJECT_AT_INDEXED_SUBSCRIPT = ObjC.sel("setObject:atIndexedSubscript:");

    private static final MethodHandle AT = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle SET = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLComputePassSampleBufferAttachmentDescriptorArray(long id) {
        super(id);
    }

    public static MTLComputePassSampleBufferAttachmentDescriptorArray of(long id) {
        return new MTLComputePassSampleBufferAttachmentDescriptorArray(id);
    }

    @SneakyThrows
    public MTLComputePassSampleBufferAttachmentDescriptor objectAtIndexedSubscript(long attachmentIndex) {
        return MTLComputePassSampleBufferAttachmentDescriptor.of((long) AT.invokeExact(id, OBJECT_AT_INDEXED_SUBSCRIPT, attachmentIndex));
    }

    @SneakyThrows
    public void setObject(MTLComputePassSampleBufferAttachmentDescriptor attachment, long attachmentIndex) {
        SET.invokeExact(id, SET_OBJECT_AT_INDEXED_SUBSCRIPT, attachment.getId(), attachmentIndex);
    }
}
