package dev.dov.metalj.state;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLResourceStatePassDescriptor extends NSObject {
    private static final long MTL_RESOURCE_STATE_PASS_DESCRIPTOR = ObjC.cls("MTLResourceStatePassDescriptor");

    private static final long RESOURCE_STATE_PASS_DESCRIPTOR = ObjC.sel("resourceStatePassDescriptor");
    private static final long SAMPLE_BUFFER_ATTACHMENTS = ObjC.sel("sampleBufferAttachments");

    private MTLResourceStatePassDescriptor(long id) {
        super(id);
    }

    public static MTLResourceStatePassDescriptor of(long id) {
        return new MTLResourceStatePassDescriptor(id);
    }

    public static MTLResourceStatePassDescriptor resourceStatePassDescriptor() {
        return new MTLResourceStatePassDescriptor(
                owned(() -> sendPtr(MTL_RESOURCE_STATE_PASS_DESCRIPTOR, RESOURCE_STATE_PASS_DESCRIPTOR)));
    }

    public MTLResourceStatePassSampleBufferAttachmentDescriptorArray sampleBufferAttachments() {
        return MTLResourceStatePassSampleBufferAttachmentDescriptorArray.of(sendPtr(id, SAMPLE_BUFFER_ATTACHMENTS));
    }
}
