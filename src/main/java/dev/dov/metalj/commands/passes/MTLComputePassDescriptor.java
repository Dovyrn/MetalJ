package dev.dov.metalj.commands.passes;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLComputePassDescriptor extends NSObject {
    private static final long MTL_COMPUTE_PASS_DESCRIPTOR = ObjC.cls("MTLComputePassDescriptor");

    private static final long COMPUTE_PASS_DESCRIPTOR = ObjC.sel("computePassDescriptor");
    private static final long SAMPLE_BUFFER_ATTACHMENTS = ObjC.sel("sampleBufferAttachments");
    private static final long SET_DISPATCH_TYPE = ObjC.sel("setDispatchType:");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLComputePassDescriptor(long id) {
        super(id);
    }

    public static MTLComputePassDescriptor of(long id) {
        return new MTLComputePassDescriptor(id);
    }

    public static MTLComputePassDescriptor computePassDescriptor() {
        return new MTLComputePassDescriptor(owned(() -> sendPtr(MTL_COMPUTE_PASS_DESCRIPTOR, COMPUTE_PASS_DESCRIPTOR)));
    }

    @SneakyThrows
    public void setDispatchType(long dispatchType) {
        L.invokeExact(id, SET_DISPATCH_TYPE, dispatchType);
    }

    public MTLComputePassSampleBufferAttachmentDescriptorArray sampleBufferAttachments() {
        return MTLComputePassSampleBufferAttachmentDescriptorArray.of(sendPtr(id, SAMPLE_BUFFER_ATTACHMENTS));
    }
}
