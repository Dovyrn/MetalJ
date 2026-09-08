package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLAccelerationStructureDescriptor extends NSObject {
    protected static final MethodHandle L = handle(null, ObjC.LONG);
    protected static final MethodHandle P = handle(null, ObjC.PTR);
    protected static final MethodHandle F = handle(null, ObjC.FLOAT);

    protected MTLAccelerationStructureDescriptor(long id) {
        super(id);
    }

    public static MTLAccelerationStructureDescriptor of(long id) {
        return new MTLAccelerationStructureDescriptor(id);
    }

    public long usage() {
        return sendLong(id, "usage");
    }

    @SneakyThrows
    public void setUsage(long usage) {
        L.invokeExact(id, ObjC.sel("setUsage:"), usage);
    }
}
