package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4PipelineOptions extends NSObject {
    private static final long MTL_4_PIPELINE_OPTIONS = ObjC.cls("MTL4PipelineOptions");

    private static final long NEW = ObjC.sel("new");
    private static final long SET_SHADER_REFLECTION = ObjC.sel("setShaderReflection:");
    private static final long SET_SHADER_VALIDATION = ObjC.sel("setShaderValidation:");
    private static final long SHADER_REFLECTION = ObjC.sel("shaderReflection");
    private static final long SHADER_VALIDATION = ObjC.sel("shaderValidation");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTL4PipelineOptions(long id) {
        super(id);
    }

    public static MTL4PipelineOptions of(long id) {
        return new MTL4PipelineOptions(id);
    }

    public static MTL4PipelineOptions new_() {
        return new MTL4PipelineOptions(sendPtr(MTL_4_PIPELINE_OPTIONS, NEW));
    }

    public long shaderValidation() {
        return sendLong(id, SHADER_VALIDATION);
    }

    @SneakyThrows
    public void setShaderValidation(long validation) {
        L.invokeExact(id, SET_SHADER_VALIDATION, validation);
    }

    public long shaderReflection() {
        return sendLong(id, SHADER_REFLECTION);
    }

    @SneakyThrows
    public void setShaderReflection(long reflection) {
        L.invokeExact(id, SET_SHADER_REFLECTION, reflection);
    }
}
