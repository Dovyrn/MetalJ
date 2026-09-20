package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4PipelineDataSetSerializerDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTL4PipelineDataSetSerializerDescriptor(long id) {
        super(id);
    }

    public static MTL4PipelineDataSetSerializerDescriptor of(long id) {
        return new MTL4PipelineDataSetSerializerDescriptor(id);
    }

    public static MTL4PipelineDataSetSerializerDescriptor new_() {
        return new MTL4PipelineDataSetSerializerDescriptor(
                sendPtr(ObjC.cls("MTL4PipelineDataSetSerializerDescriptor"), "new"));
    }

    public long configuration() {
        return sendLong(id, "configuration");
    }

    @SneakyThrows
    public void setConfiguration(long configuration) {
        L.invokeExact(id, ObjC.sel("setConfiguration:"), configuration);
    }
}
