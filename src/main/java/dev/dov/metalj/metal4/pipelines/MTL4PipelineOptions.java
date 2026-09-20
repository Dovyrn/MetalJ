package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4PipelineOptions extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTL4PipelineOptions(long id) {
        super(id);
    }

    public static MTL4PipelineOptions of(long id) {
        return new MTL4PipelineOptions(id);
    }

    public static MTL4PipelineOptions new_() {
        return new MTL4PipelineOptions(sendPtr(ObjC.cls("MTL4PipelineOptions"), "new"));
    }

    public long shaderValidation() {
        return sendLong(id, "shaderValidation");
    }

    @SneakyThrows
    public void setShaderValidation(long validation) {
        L.invokeExact(id, ObjC.sel("setShaderValidation:"), validation);
    }

    public long shaderReflection() {
        return sendLong(id, "shaderReflection");
    }

    @SneakyThrows
    public void setShaderReflection(long reflection) {
        L.invokeExact(id, ObjC.sel("setShaderReflection:"), reflection);
    }
}
