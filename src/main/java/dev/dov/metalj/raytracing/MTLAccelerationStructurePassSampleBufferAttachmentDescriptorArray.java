package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLAccelerationStructurePassSampleBufferAttachmentDescriptorArray extends NSObject {
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLAccelerationStructurePassSampleBufferAttachmentDescriptorArray(long id) {
        super(id);
    }

    public static MTLAccelerationStructurePassSampleBufferAttachmentDescriptorArray of(long id) {
        return new MTLAccelerationStructurePassSampleBufferAttachmentDescriptorArray(id);
    }

    @SneakyThrows
    public MTLAccelerationStructurePassSampleBufferAttachmentDescriptor objectAtIndexedSubscript(long index) {
        return MTLAccelerationStructurePassSampleBufferAttachmentDescriptor.of(
                (long) P_L.invokeExact(id, ObjC.sel("objectAtIndexedSubscript:"), index));
    }

    @SneakyThrows
    public void setObjectAtIndexedSubscript(MTLAccelerationStructurePassSampleBufferAttachmentDescriptor attachment,
            long index) {
        PL.invokeExact(id, ObjC.sel("setObject:atIndexedSubscript:"), attachment.getId(), index);
    }
}
