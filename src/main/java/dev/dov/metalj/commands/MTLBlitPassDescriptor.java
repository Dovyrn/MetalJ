package dev.dov.metalj.commands;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLBlitPassDescriptor extends NSObject {
    private MTLBlitPassDescriptor(long id) {
        super(id);
    }

    public static MTLBlitPassDescriptor of(long id) {
        return new MTLBlitPassDescriptor(id);
    }

    public static MTLBlitPassDescriptor blitPassDescriptor() {
        return new MTLBlitPassDescriptor(sendPtr(ObjC.cls("MTLBlitPassDescriptor"), "blitPassDescriptor"));
    }

    public MTLBlitPassSampleBufferAttachmentDescriptorArray sampleBufferAttachments() {
        return MTLBlitPassSampleBufferAttachmentDescriptorArray.of(sendPtr(id, "sampleBufferAttachments"));
    }
}
