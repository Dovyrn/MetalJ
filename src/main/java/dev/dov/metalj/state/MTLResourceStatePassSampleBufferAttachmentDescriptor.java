package dev.dov.metalj.state;

import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLResourceStatePassSampleBufferAttachmentDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLResourceStatePassSampleBufferAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLResourceStatePassSampleBufferAttachmentDescriptor of(long id) {
        return new MTLResourceStatePassSampleBufferAttachmentDescriptor(id);
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
