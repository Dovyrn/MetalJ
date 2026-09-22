package dev.dov.metalj.state;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLResourceStatePassSampleBufferAttachmentDescriptorArray extends NSObject {
    private static final long OBJECT_AT_INDEXED_SUBSCRIPT = ObjC.sel("objectAtIndexedSubscript:");
    private static final long SET_OBJECT_AT_INDEXED_SUBSCRIPT = ObjC.sel("setObject:atIndexedSubscript:");

    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLResourceStatePassSampleBufferAttachmentDescriptorArray(long id) {
        super(id);
    }

    public static MTLResourceStatePassSampleBufferAttachmentDescriptorArray of(long id) {
        return new MTLResourceStatePassSampleBufferAttachmentDescriptorArray(id);
    }

    @SneakyThrows
    public MTLResourceStatePassSampleBufferAttachmentDescriptor objectAtIndexedSubscript(long index) {
        return MTLResourceStatePassSampleBufferAttachmentDescriptor.of(
                (long) P_L.invokeExact(id, OBJECT_AT_INDEXED_SUBSCRIPT, index));
    }

    @SneakyThrows
    public void setObjectAtIndexedSubscript(MTLResourceStatePassSampleBufferAttachmentDescriptor attachment,
            long index) {
        PL.invokeExact(id, SET_OBJECT_AT_INDEXED_SUBSCRIPT, attachment.getId(), index);
    }
}
