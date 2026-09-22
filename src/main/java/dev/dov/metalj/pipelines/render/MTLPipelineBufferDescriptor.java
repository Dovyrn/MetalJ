package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLPipelineBufferDescriptor extends NSObject {
    private static final long MTL_PIPELINE_BUFFER_DESCRIPTOR = ObjC.cls("MTLPipelineBufferDescriptor");

    private static final long MUTABILITY = ObjC.sel("mutability");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_MUTABILITY = ObjC.sel("setMutability:");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLPipelineBufferDescriptor(long id) {
        super(id);
    }

    public static MTLPipelineBufferDescriptor of(long id) {
        return new MTLPipelineBufferDescriptor(id);
    }

    public static MTLPipelineBufferDescriptor new_() {
        return new MTLPipelineBufferDescriptor(sendPtr(MTL_PIPELINE_BUFFER_DESCRIPTOR, NEW));
    }

    public long mutability() {
        return sendLong(id, MUTABILITY);
    }

    @SneakyThrows
    public void setMutability(long mutability) {
        L.invokeExact(id, SET_MUTABILITY, mutability);
    }
}
