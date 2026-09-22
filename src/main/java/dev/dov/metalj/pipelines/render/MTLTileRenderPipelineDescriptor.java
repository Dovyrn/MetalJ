package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLFunction;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTileRenderPipelineDescriptor extends NSObject {
    private static final long MTL_TILE_RENDER_PIPELINE_DESCRIPTOR = ObjC.cls("MTLTileRenderPipelineDescriptor");

    private static final long COLOR_ATTACHMENTS = ObjC.sel("colorAttachments");
    private static final long LABEL = ObjC.sel("label");
    private static final long MAX_CALL_STACK_DEPTH = ObjC.sel("maxCallStackDepth");
    private static final long MAX_TOTAL_THREADS_PER_THREADGROUP = ObjC.sel("maxTotalThreadsPerThreadgroup");
    private static final long NEW = ObjC.sel("new");
    private static final long RASTER_SAMPLE_COUNT = ObjC.sel("rasterSampleCount");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_MAX_CALL_STACK_DEPTH = ObjC.sel("setMaxCallStackDepth:");
    private static final long SET_MAX_TOTAL_THREADS_PER_THREADGROUP = ObjC.sel("setMaxTotalThreadsPerThreadgroup:");
    private static final long SET_RASTER_SAMPLE_COUNT = ObjC.sel("setRasterSampleCount:");
    private static final long SET_SUPPORT_ADDING_BINARY_FUNCTIONS = ObjC.sel("setSupportAddingBinaryFunctions:");
    private static final long SET_THREADGROUP_SIZE_MATCHES_TILE_SIZE = ObjC.sel("setThreadgroupSizeMatchesTileSize:");
    private static final long SET_TILE_FUNCTION = ObjC.sel("setTileFunction:");
    private static final long SUPPORT_ADDING_BINARY_FUNCTIONS = ObjC.sel("supportAddingBinaryFunctions");
    private static final long THREADGROUP_SIZE_MATCHES_TILE_SIZE = ObjC.sel("threadgroupSizeMatchesTileSize");
    private static final long TILE_FUNCTION = ObjC.sel("tileFunction");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLTileRenderPipelineDescriptor(long id) {
        super(id);
    }

    public static MTLTileRenderPipelineDescriptor of(long id) {
        return new MTLTileRenderPipelineDescriptor(id);
    }

    public static MTLTileRenderPipelineDescriptor new_() {
        return new MTLTileRenderPipelineDescriptor(sendPtr(MTL_TILE_RENDER_PIPELINE_DESCRIPTOR, NEW));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public MTLFunction tileFunction() {
        return MTLFunction.of(sendPtr(id, TILE_FUNCTION));
    }

    @SneakyThrows
    public void setTileFunction(MTLFunction function) {
        P.invokeExact(id, SET_TILE_FUNCTION, function.getId());
    }

    public long rasterSampleCount() {
        return sendLong(id, RASTER_SAMPLE_COUNT);
    }

    @SneakyThrows
    public void setRasterSampleCount(long count) {
        L.invokeExact(id, SET_RASTER_SAMPLE_COUNT, count);
    }

    public MTLTileRenderPipelineColorAttachmentDescriptorArray colorAttachments() {
        return MTLTileRenderPipelineColorAttachmentDescriptorArray.of(sendPtr(id, COLOR_ATTACHMENTS));
    }

    public boolean threadgroupSizeMatchesTileSize() {
        return sendBool(id, THREADGROUP_SIZE_MATCHES_TILE_SIZE);
    }

    @SneakyThrows
    public void setThreadgroupSizeMatchesTileSize(boolean matches) {
        B.invokeExact(id, SET_THREADGROUP_SIZE_MATCHES_TILE_SIZE, matches);
    }

    public long maxTotalThreadsPerThreadgroup() {
        return sendLong(id, MAX_TOTAL_THREADS_PER_THREADGROUP);
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerThreadgroup(long count) {
        L.invokeExact(id, SET_MAX_TOTAL_THREADS_PER_THREADGROUP, count);
    }

    public boolean supportAddingBinaryFunctions() {
        return sendBool(id, SUPPORT_ADDING_BINARY_FUNCTIONS);
    }

    @SneakyThrows
    public void setSupportAddingBinaryFunctions(boolean support) {
        B.invokeExact(id, SET_SUPPORT_ADDING_BINARY_FUNCTIONS, support);
    }

    public long maxCallStackDepth() {
        return sendLong(id, MAX_CALL_STACK_DEPTH);
    }

    @SneakyThrows
    public void setMaxCallStackDepth(long depth) {
        L.invokeExact(id, SET_MAX_CALL_STACK_DEPTH, depth);
    }

    public void reset() {
        sendVoid(id, RESET);
    }
}
