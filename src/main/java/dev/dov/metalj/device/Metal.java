package dev.dov.metalj.device;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.FunctionDescriptor;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Metal {
    private final MethodHandle CREATE = ObjC.function("MTLCreateSystemDefaultDevice",
            FunctionDescriptor.of(ObjC.PTR));

    @SneakyThrows
    public MTLDevice MTLCreateSystemDefaultDevice() {
        return MTLDevice.of((long) CREATE.invokeExact());
    }
}
