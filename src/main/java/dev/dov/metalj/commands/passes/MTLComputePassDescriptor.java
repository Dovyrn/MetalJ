package dev.dov.metalj.commands.passes;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLComputePassDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLComputePassDescriptor(long id) {
        super(id);
    }

    public static MTLComputePassDescriptor of(long id) {
        return new MTLComputePassDescriptor(id);
    }

    public static MTLComputePassDescriptor computePassDescriptor() {
        return new MTLComputePassDescriptor(sendPtr(ObjC.cls("MTLComputePassDescriptor"), "computePassDescriptor"));
    }

    @SneakyThrows
    public void setDispatchType(long dispatchType) {
        L.invokeExact(id, ObjC.sel("setDispatchType:"), dispatchType);
    }

    public MTLComputePassSampleBufferAttachmentDescriptorArray sampleBufferAttachments() {
        return MTLComputePassSampleBufferAttachmentDescriptorArray.of(sendPtr(id, "sampleBufferAttachments"));
    }
}
