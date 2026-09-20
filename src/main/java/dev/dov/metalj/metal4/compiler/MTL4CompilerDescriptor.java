package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CompilerDescriptor extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4CompilerDescriptor(long id) {
        super(id);
    }

    public static MTL4CompilerDescriptor of(long id) {
        return new MTL4CompilerDescriptor(id);
    }

    public static MTL4CompilerDescriptor new_() {
        return new MTL4CompilerDescriptor(sendPtr(ObjC.cls("MTL4CompilerDescriptor"), "new"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    public MTL4PipelineDataSetSerializer pipelineDataSetSerializer() {
        return MTL4PipelineDataSetSerializer.of(sendPtr(id, "pipelineDataSetSerializer"));
    }

    @SneakyThrows
    public void setPipelineDataSetSerializer(MTL4PipelineDataSetSerializer serializer) {
        P.invokeExact(id, ObjC.sel("setPipelineDataSetSerializer:"), serializer.getId());
    }
}
