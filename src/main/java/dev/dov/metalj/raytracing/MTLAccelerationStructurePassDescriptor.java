package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLAccelerationStructurePassDescriptor extends NSObject {
    private static final long MTL_ACCELERATION_STRUCTURE_PASS_DESCRIPTOR = ObjC.cls("MTLAccelerationStructurePassDescriptor");

    private static final long ACCELERATION_STRUCTURE_PASS_DESCRIPTOR = ObjC.sel("accelerationStructurePassDescriptor");
    private static final long SAMPLE_BUFFER_ATTACHMENTS = ObjC.sel("sampleBufferAttachments");

    private MTLAccelerationStructurePassDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructurePassDescriptor of(long id) {
        return new MTLAccelerationStructurePassDescriptor(id);
    }

    public static MTLAccelerationStructurePassDescriptor accelerationStructurePassDescriptor() {
        return new MTLAccelerationStructurePassDescriptor(sendPtr(
                MTL_ACCELERATION_STRUCTURE_PASS_DESCRIPTOR, ACCELERATION_STRUCTURE_PASS_DESCRIPTOR));
    }

    public MTLAccelerationStructurePassSampleBufferAttachmentDescriptorArray sampleBufferAttachments() {
        return MTLAccelerationStructurePassSampleBufferAttachmentDescriptorArray.of(
                sendPtr(id, SAMPLE_BUFFER_ATTACHMENTS));
    }
}
