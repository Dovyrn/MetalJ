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
    private static final long GPU_RESOURCE_ID = ObjC.sel("gpuResourceID");
    private static final long SET_BUFFER_OFFSET_AT_INDEX = ObjC.sel("setBuffer:offset:atIndex:");
    private static final long SET_FUNCTION_AT_INDEX = ObjC.sel("setFunction:atIndex:");
    private static final long SET_FUNCTIONS_WITH_RANGE = ObjC.sel("setFunctions:withRange:");
    private static final long SET_OPAQUE_TRIANGLE_INTERSECTION_FUNCTION_WITH_SIGNATURE_AT_INDEX = ObjC.sel("setOpaqueTriangleIntersectionFunctionWithSignature:atIndex:");
    private static final long SET_OPAQUE_TRIANGLE_INTERSECTION_FUNCTION_WITH_SIGNATURE_WITH_RANGE = ObjC.sel("setOpaqueTriangleIntersectionFunctionWithSignature:withRange:");
    private static final long SET_VISIBLE_FUNCTION_TABLE_AT_BUFFER_INDEX = ObjC.sel("setVisibleFunctionTable:atBufferIndex:");

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
        return sendLong(id, GPU_RESOURCE_ID);
    }

    @SneakyThrows
    public void setBuffer(MTLBuffer buffer, long offset, long index) {
        PLL.invokeExact(id, SET_BUFFER_OFFSET_AT_INDEX, buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setFunction(MTLFunctionHandle function, long index) {
        PL.invokeExact(id, SET_FUNCTION_AT_INDEX, function.getId(), index);
    }

    @SneakyThrows
    public void setFunctions(MemorySegment functions, MemorySegment range) {
        AR.invokeExact(id, SET_FUNCTIONS_WITH_RANGE, functions, range);
    }

    @SneakyThrows
    public void setOpaqueTriangleIntersectionFunctionWithSignature(long signature, long index) {
        LL.invokeExact(id, SET_OPAQUE_TRIANGLE_INTERSECTION_FUNCTION_WITH_SIGNATURE_AT_INDEX, signature, index);
    }

    @SneakyThrows
    public void setOpaqueTriangleIntersectionFunctionWithSignature(long signature, MemorySegment range) {
        LR.invokeExact(id, SET_OPAQUE_TRIANGLE_INTERSECTION_FUNCTION_WITH_SIGNATURE_WITH_RANGE, signature,
                range);
    }

    @SneakyThrows
    public void setVisibleFunctionTable(MTLVisibleFunctionTable table, long index) {
        PL.invokeExact(id, SET_VISIBLE_FUNCTION_TABLE_AT_BUFFER_INDEX, table.getId(), index);
    }
}
