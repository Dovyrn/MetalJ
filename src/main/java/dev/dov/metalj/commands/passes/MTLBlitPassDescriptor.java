package dev.dov.metalj.commands.passes;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLBlitPassDescriptor extends NSObject {
    private static final long MTL_BLIT_PASS_DESCRIPTOR = ObjC.cls("MTLBlitPassDescriptor");

    private static final long BLIT_PASS_DESCRIPTOR = ObjC.sel("blitPassDescriptor");
    private static final long SAMPLE_BUFFER_ATTACHMENTS = ObjC.sel("sampleBufferAttachments");

    private MTLBlitPassDescriptor(long id) {
        super(id);
    }

    public static MTLBlitPassDescriptor of(long id) {
        return new MTLBlitPassDescriptor(id);
    }

    public static MTLBlitPassDescriptor blitPassDescriptor() {
        return new MTLBlitPassDescriptor(owned(() -> sendPtr(MTL_BLIT_PASS_DESCRIPTOR, BLIT_PASS_DESCRIPTOR)));
    }

    public MTLBlitPassSampleBufferAttachmentDescriptorArray sampleBufferAttachments() {
        return MTLBlitPassSampleBufferAttachmentDescriptorArray.of(sendPtr(id, SAMPLE_BUFFER_ATTACHMENTS));
    }
}
