package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLFunctionHandle;
import dev.dov.metalj.resources.MTLResource;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLVisibleFunctionTable extends MTLResource {
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle AR = handle(null, ValueLayout.ADDRESS, NSRange.LAYOUT);

    private MTLVisibleFunctionTable(long id) {
        super(id);
    }

    public static MTLVisibleFunctionTable of(long id) {
        return new MTLVisibleFunctionTable(id);
    }

    public long gpuResourceID() {
        return sendLong(id, "gpuResourceID");
    }

    @SneakyThrows
    public void setFunction(MTLFunctionHandle function, long index) {
        PL.invokeExact(id, ObjC.sel("setFunction:atIndex:"), function.getId(), index);
    }

    @SneakyThrows
    public void setFunctions(MemorySegment functions, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setFunctions:withRange:"), functions, range);
    }
}
