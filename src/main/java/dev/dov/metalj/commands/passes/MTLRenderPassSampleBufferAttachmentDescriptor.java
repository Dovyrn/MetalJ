package dev.dov.metalj.commands.passes;

import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPassSampleBufferAttachmentDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLRenderPassSampleBufferAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLRenderPassSampleBufferAttachmentDescriptor of(long id) {
        return new MTLRenderPassSampleBufferAttachmentDescriptor(id);
    }

    @SneakyThrows
    public void setSampleBuffer(MTLCounterSampleBuffer sampleBuffer) {
        L.invokeExact(id, ObjC.sel("setSampleBuffer:"), sampleBuffer.getId());
    }

    @SneakyThrows
    public void setStartOfVertexSampleIndex(long index) {
        L.invokeExact(id, ObjC.sel("setStartOfVertexSampleIndex:"), index);
    }

    @SneakyThrows
    public void setEndOfVertexSampleIndex(long index) {
        L.invokeExact(id, ObjC.sel("setEndOfVertexSampleIndex:"), index);
    }

    @SneakyThrows
    public void setStartOfFragmentSampleIndex(long index) {
        L.invokeExact(id, ObjC.sel("setStartOfFragmentSampleIndex:"), index);
    }

    @SneakyThrows
    public void setEndOfFragmentSampleIndex(long index) {
        L.invokeExact(id, ObjC.sel("setEndOfFragmentSampleIndex:"), index);
    }
}
