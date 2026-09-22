package dev.dov.metalj.commands.passes;

import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLBlitPassSampleBufferAttachmentDescriptor extends NSObject {
    private static final long SET_END_OF_ENCODER_SAMPLE_INDEX = ObjC.sel("setEndOfEncoderSampleIndex:");
    private static final long SET_SAMPLE_BUFFER = ObjC.sel("setSampleBuffer:");
    private static final long SET_START_OF_ENCODER_SAMPLE_INDEX = ObjC.sel("setStartOfEncoderSampleIndex:");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLBlitPassSampleBufferAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLBlitPassSampleBufferAttachmentDescriptor of(long id) {
        return new MTLBlitPassSampleBufferAttachmentDescriptor(id);
    }

    @SneakyThrows
    public void setSampleBuffer(MTLCounterSampleBuffer sampleBuffer) {
        L.invokeExact(id, SET_SAMPLE_BUFFER, sampleBuffer.getId());
    }

    @SneakyThrows
    public void setStartOfEncoderSampleIndex(long index) {
        L.invokeExact(id, SET_START_OF_ENCODER_SAMPLE_INDEX, index);
    }

    @SneakyThrows
    public void setEndOfEncoderSampleIndex(long index) {
        L.invokeExact(id, SET_END_OF_ENCODER_SAMPLE_INDEX, index);
    }
}
