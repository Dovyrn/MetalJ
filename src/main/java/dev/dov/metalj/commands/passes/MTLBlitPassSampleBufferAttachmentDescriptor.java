package dev.dov.metalj.commands.passes;

import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLBlitPassSampleBufferAttachmentDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLBlitPassSampleBufferAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLBlitPassSampleBufferAttachmentDescriptor of(long id) {
        return new MTLBlitPassSampleBufferAttachmentDescriptor(id);
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
