package dev.dov.metalj.raytracing;

import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLAccelerationStructurePassSampleBufferAttachmentDescriptor extends NSObject {
    private static final long END_OF_ENCODER_SAMPLE_INDEX = ObjC.sel("endOfEncoderSampleIndex");
    private static final long SAMPLE_BUFFER = ObjC.sel("sampleBuffer");
    private static final long SET_END_OF_ENCODER_SAMPLE_INDEX = ObjC.sel("setEndOfEncoderSampleIndex:");
    private static final long SET_SAMPLE_BUFFER = ObjC.sel("setSampleBuffer:");
    private static final long SET_START_OF_ENCODER_SAMPLE_INDEX = ObjC.sel("setStartOfEncoderSampleIndex:");
    private static final long START_OF_ENCODER_SAMPLE_INDEX = ObjC.sel("startOfEncoderSampleIndex");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLAccelerationStructurePassSampleBufferAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructurePassSampleBufferAttachmentDescriptor of(long id) {
        return new MTLAccelerationStructurePassSampleBufferAttachmentDescriptor(id);
    }

    public MTLCounterSampleBuffer sampleBuffer() {
        return MTLCounterSampleBuffer.of(sendPtr(id, SAMPLE_BUFFER));
    }

    @SneakyThrows
    public void setSampleBuffer(MTLCounterSampleBuffer sampleBuffer) {
        P.invokeExact(id, SET_SAMPLE_BUFFER, sampleBuffer.getId());
    }

    public long startOfEncoderSampleIndex() {
        return sendLong(id, START_OF_ENCODER_SAMPLE_INDEX);
    }

    @SneakyThrows
    public void setStartOfEncoderSampleIndex(long index) {
        L.invokeExact(id, SET_START_OF_ENCODER_SAMPLE_INDEX, index);
    }

    public long endOfEncoderSampleIndex() {
        return sendLong(id, END_OF_ENCODER_SAMPLE_INDEX);
    }

    @SneakyThrows
    public void setEndOfEncoderSampleIndex(long index) {
        L.invokeExact(id, SET_END_OF_ENCODER_SAMPLE_INDEX, index);
    }
}
