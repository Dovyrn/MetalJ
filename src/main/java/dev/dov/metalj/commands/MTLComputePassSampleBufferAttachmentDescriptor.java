package dev.dov.metalj.commands;

import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLComputePassSampleBufferAttachmentDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLComputePassSampleBufferAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLComputePassSampleBufferAttachmentDescriptor of(long id) {
        return new MTLComputePassSampleBufferAttachmentDescriptor(id);
    }

    @SneakyThrows
    public void setSampleBuffer(MTLCounterSampleBuffer sampleBuffer) {
        L.invokeExact(id, ObjC.sel("setSampleBuffer:"), sampleBuffer.getId());
    }

    @SneakyThrows
    public void setStartOfEncoderSampleIndex(long index) {
        L.invokeExact(id, ObjC.sel("setStartOfEncoderSampleIndex:"), index);
    }

    @SneakyThrows
    public void setEndOfEncoderSampleIndex(long index) {
        L.invokeExact(id, ObjC.sel("setEndOfEncoderSampleIndex:"), index);
    }
}
