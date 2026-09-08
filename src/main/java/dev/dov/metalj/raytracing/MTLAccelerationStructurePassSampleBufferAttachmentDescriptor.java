package dev.dov.metalj.raytracing;

import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLAccelerationStructurePassSampleBufferAttachmentDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLAccelerationStructurePassSampleBufferAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructurePassSampleBufferAttachmentDescriptor of(long id) {
        return new MTLAccelerationStructurePassSampleBufferAttachmentDescriptor(id);
    }

    public MTLCounterSampleBuffer sampleBuffer() {
        return MTLCounterSampleBuffer.of(sendPtr(id, "sampleBuffer"));
    }

    @SneakyThrows
    public void setSampleBuffer(MTLCounterSampleBuffer sampleBuffer) {
        P.invokeExact(id, ObjC.sel("setSampleBuffer:"), sampleBuffer.getId());
    }

    public long startOfEncoderSampleIndex() {
        return sendLong(id, "startOfEncoderSampleIndex");
    }

    @SneakyThrows
    public void setStartOfEncoderSampleIndex(long index) {
        L.invokeExact(id, ObjC.sel("setStartOfEncoderSampleIndex:"), index);
    }

    public long endOfEncoderSampleIndex() {
        return sendLong(id, "endOfEncoderSampleIndex");
    }

    @SneakyThrows
    public void setEndOfEncoderSampleIndex(long index) {
        L.invokeExact(id, ObjC.sel("setEndOfEncoderSampleIndex:"), index);
    }
}
