package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4PipelineDataSetSerializerDescriptor extends NSObject {
    private static final long MTL_4_PIPELINE_DATA_SET_SERIALIZER_DESCRIPTOR = ObjC.cls("MTL4PipelineDataSetSerializerDescriptor");

    private static final long CONFIGURATION = ObjC.sel("configuration");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_CONFIGURATION = ObjC.sel("setConfiguration:");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTL4PipelineDataSetSerializerDescriptor(long id) {
        super(id);
    }

    public static MTL4PipelineDataSetSerializerDescriptor of(long id) {
        return new MTL4PipelineDataSetSerializerDescriptor(id);
    }

    public static MTL4PipelineDataSetSerializerDescriptor new_() {
        return new MTL4PipelineDataSetSerializerDescriptor(
                sendPtr(MTL_4_PIPELINE_DATA_SET_SERIALIZER_DESCRIPTOR, NEW));
    }

    public long configuration() {
        return sendLong(id, CONFIGURATION);
    }

    @SneakyThrows
    public void setConfiguration(long configuration) {
        L.invokeExact(id, SET_CONFIGURATION, configuration);
    }
}
