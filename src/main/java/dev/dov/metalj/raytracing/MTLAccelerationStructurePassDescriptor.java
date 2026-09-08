package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLAccelerationStructurePassDescriptor extends NSObject {
    private MTLAccelerationStructurePassDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructurePassDescriptor of(long id) {
        return new MTLAccelerationStructurePassDescriptor(id);
    }

    public static MTLAccelerationStructurePassDescriptor accelerationStructurePassDescriptor() {
        return new MTLAccelerationStructurePassDescriptor(sendPtr(
                ObjC.cls("MTLAccelerationStructurePassDescriptor"), "accelerationStructurePassDescriptor"));
    }

    public MTLAccelerationStructurePassSampleBufferAttachmentDescriptorArray sampleBufferAttachments() {
        return MTLAccelerationStructurePassSampleBufferAttachmentDescriptorArray.of(
                sendPtr(id, "sampleBufferAttachments"));
    }
}
