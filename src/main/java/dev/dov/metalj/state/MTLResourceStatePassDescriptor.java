package dev.dov.metalj.state;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLResourceStatePassDescriptor extends NSObject {
    private MTLResourceStatePassDescriptor(long id) {
        super(id);
    }

    public static MTLResourceStatePassDescriptor of(long id) {
        return new MTLResourceStatePassDescriptor(id);
    }

    public static MTLResourceStatePassDescriptor resourceStatePassDescriptor() {
        return new MTLResourceStatePassDescriptor(
                sendPtr(ObjC.cls("MTLResourceStatePassDescriptor"), "resourceStatePassDescriptor"));
    }

    public MTLResourceStatePassSampleBufferAttachmentDescriptorArray sampleBufferAttachments() {
        return MTLResourceStatePassSampleBufferAttachmentDescriptorArray.of(sendPtr(id, "sampleBufferAttachments"));
    }
}
