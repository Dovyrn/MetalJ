package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLPipelineBufferDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLPipelineBufferDescriptor(long id) {
        super(id);
    }

    public static MTLPipelineBufferDescriptor of(long id) {
        return new MTLPipelineBufferDescriptor(id);
    }

    public static MTLPipelineBufferDescriptor new_() {
        return new MTLPipelineBufferDescriptor(sendPtr(ObjC.cls("MTLPipelineBufferDescriptor"), "new"));
    }

    public long mutability() {
        return sendLong(id, "mutability");
    }

    @SneakyThrows
    public void setMutability(long mutability) {
        L.invokeExact(id, ObjC.sel("setMutability:"), mutability);
    }
}
