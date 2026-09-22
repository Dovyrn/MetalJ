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
    private static final long GPU_RESOURCE_ID = ObjC.sel("gpuResourceID");
    private static final long SET_FUNCTION_AT_INDEX = ObjC.sel("setFunction:atIndex:");
    private static final long SET_FUNCTIONS_WITH_RANGE = ObjC.sel("setFunctions:withRange:");

    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle AR = handle(null, ValueLayout.ADDRESS, NSRange.LAYOUT);

    private MTLVisibleFunctionTable(long id) {
        super(id);
    }

    public static MTLVisibleFunctionTable of(long id) {
        return new MTLVisibleFunctionTable(id);
    }

    public long gpuResourceID() {
        return sendLong(id, GPU_RESOURCE_ID);
    }

    @SneakyThrows
    public void setFunction(MTLFunctionHandle function, long index) {
        PL.invokeExact(id, SET_FUNCTION_AT_INDEX, function.getId(), index);
    }

    @SneakyThrows
    public void setFunctions(MemorySegment functions, MemorySegment range) {
        AR.invokeExact(id, SET_FUNCTIONS_WITH_RANGE, functions, range);
    }
}
