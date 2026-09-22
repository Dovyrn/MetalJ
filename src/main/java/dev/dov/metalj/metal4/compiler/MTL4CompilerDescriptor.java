package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CompilerDescriptor extends NSObject {
    private static final long MTL_4_COMPILER_DESCRIPTOR = ObjC.cls("MTL4CompilerDescriptor");

    private static final long LABEL = ObjC.sel("label");
    private static final long NEW = ObjC.sel("new");
    private static final long PIPELINE_DATA_SET_SERIALIZER = ObjC.sel("pipelineDataSetSerializer");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_PIPELINE_DATA_SET_SERIALIZER = ObjC.sel("setPipelineDataSetSerializer:");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4CompilerDescriptor(long id) {
        super(id);
    }

    public static MTL4CompilerDescriptor of(long id) {
        return new MTL4CompilerDescriptor(id);
    }

    public static MTL4CompilerDescriptor new_() {
        return new MTL4CompilerDescriptor(sendPtr(MTL_4_COMPILER_DESCRIPTOR, NEW));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public MTL4PipelineDataSetSerializer pipelineDataSetSerializer() {
        return MTL4PipelineDataSetSerializer.of(sendPtr(id, PIPELINE_DATA_SET_SERIALIZER));
    }

    @SneakyThrows
    public void setPipelineDataSetSerializer(MTL4PipelineDataSetSerializer serializer) {
        P.invokeExact(id, SET_PIPELINE_DATA_SET_SERIALIZER, serializer.getId());
    }
}
