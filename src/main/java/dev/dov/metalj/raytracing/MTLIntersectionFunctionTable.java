package dev.dov.metalj.raytracing;

import dev.dov.metalj.functions.MTLVisibleFunctionTable;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLFunctionHandle;
import dev.dov.metalj.resources.MTLResource;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIntersectionFunctionTable extends MTLResource {
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PLL = handle(null, ObjC.PTR, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle AR = handle(null, ValueLayout.ADDRESS, NSRange.LAYOUT);
    private static final MethodHandle LL = handle(null, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LR = handle(null, ObjC.LONG, NSRange.LAYOUT);

    private MTLIntersectionFunctionTable(long id) {
        super(id);
    }

    public static MTLIntersectionFunctionTable of(long id) {
        return new MTLIntersectionFunctionTable(id);
    }

    public long gpuResourceID() {
        return sendLong(id, "gpuResourceID");
    }

    @SneakyThrows
    public void setBuffer(MTLBuffer buffer, long offset, long index) {
        PLL.invokeExact(id, ObjC.sel("setBuffer:offset:atIndex:"), buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setFunction(MTLFunctionHandle function, long index) {
        PL.invokeExact(id, ObjC.sel("setFunction:atIndex:"), function.getId(), index);
    }

    @SneakyThrows
    public void setFunctions(MemorySegment functions, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setFunctions:withRange:"), functions, range);
    }

    @SneakyThrows
    public void setOpaqueTriangleIntersectionFunctionWithSignature(long signature, long index) {
        LL.invokeExact(id, ObjC.sel("setOpaqueTriangleIntersectionFunctionWithSignature:atIndex:"), signature, index);
    }

    @SneakyThrows
    public void setOpaqueTriangleIntersectionFunctionWithSignature(long signature, MemorySegment range) {
        LR.invokeExact(id, ObjC.sel("setOpaqueTriangleIntersectionFunctionWithSignature:withRange:"), signature,
                range);
    }

    @SneakyThrows
    public void setVisibleFunctionTable(MTLVisibleFunctionTable table, long index) {
        PL.invokeExact(id, ObjC.sel("setVisibleFunctionTable:atBufferIndex:"), table.getId(), index);
    }
}
