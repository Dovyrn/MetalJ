package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.metal4.compiler.MTL4FunctionDescriptor;
import dev.dov.metalj.metal4.compiler.MTL4StaticLinkingDescriptor;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.render.MTLTileRenderPipelineColorAttachmentDescriptorArray;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4TileRenderPipelineDescriptor extends MTL4PipelineDescriptor {
    private static final long MTL_4_TILE_RENDER_PIPELINE_DESCRIPTOR = ObjC.cls("MTL4TileRenderPipelineDescriptor");

    private static final long COLOR_ATTACHMENTS = ObjC.sel("colorAttachments");
    private static final long MAX_TOTAL_THREADS_PER_THREADGROUP = ObjC.sel("maxTotalThreadsPerThreadgroup");
    private static final long NEW = ObjC.sel("new");
    private static final long RASTER_SAMPLE_COUNT = ObjC.sel("rasterSampleCount");
    private static final long REQUIRED_THREADS_PER_THREADGROUP = ObjC.sel("requiredThreadsPerThreadgroup");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_MAX_TOTAL_THREADS_PER_THREADGROUP = ObjC.sel("setMaxTotalThreadsPerThreadgroup:");
    private static final long SET_RASTER_SAMPLE_COUNT = ObjC.sel("setRasterSampleCount:");
    private static final long SET_REQUIRED_THREADS_PER_THREADGROUP = ObjC.sel("setRequiredThreadsPerThreadgroup:");
    private static final long SET_STATIC_LINKING_DESCRIPTOR = ObjC.sel("setStaticLinkingDescriptor:");
    private static final long SET_SUPPORT_BINARY_LINKING = ObjC.sel("setSupportBinaryLinking:");
    private static final long SET_THREADGROUP_SIZE_MATCHES_TILE_SIZE = ObjC.sel("setThreadgroupSizeMatchesTileSize:");
    private static final long SET_TILE_FUNCTION_DESCRIPTOR = ObjC.sel("setTileFunctionDescriptor:");
    private static final long STATIC_LINKING_DESCRIPTOR = ObjC.sel("staticLinkingDescriptor");
    private static final long SUPPORT_BINARY_LINKING = ObjC.sel("supportBinaryLinking");
    private static final long THREADGROUP_SIZE_MATCHES_TILE_SIZE = ObjC.sel("threadgroupSizeMatchesTileSize");
    private static final long TILE_FUNCTION_DESCRIPTOR = ObjC.sel("tileFunctionDescriptor");

    private static final MethodHandle S = handle(null, MTLSize.LAYOUT);
    private static final MethodHandle SIZE = structHandle(MTLSize.LAYOUT);

    private MTL4TileRenderPipelineDescriptor(long id) {
        super(id);
    }

    public static MTL4TileRenderPipelineDescriptor of(long id) {
        return new MTL4TileRenderPipelineDescriptor(id);
    }

    public static MTL4TileRenderPipelineDescriptor new_() {
        return new MTL4TileRenderPipelineDescriptor(sendPtr(MTL_4_TILE_RENDER_PIPELINE_DESCRIPTOR, NEW));
    }

    public MTL4FunctionDescriptor tileFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, TILE_FUNCTION_DESCRIPTOR));
    }

    @SneakyThrows
    public void setTileFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, SET_TILE_FUNCTION_DESCRIPTOR, descriptor.getId());
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
    public void setMaxTotalThreadsPerThreadgroup(long threads) {
        L.invokeExact(id, SET_MAX_TOTAL_THREADS_PER_THREADGROUP, threads);
    }

    @SneakyThrows
    public MemorySegment requiredThreadsPerThreadgroup(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, REQUIRED_THREADS_PER_THREADGROUP);
    }

    @SneakyThrows
    public void setRequiredThreadsPerThreadgroup(MemorySegment threads) {
        S.invokeExact(id, SET_REQUIRED_THREADS_PER_THREADGROUP, threads);
    }

    public MTL4StaticLinkingDescriptor staticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, STATIC_LINKING_DESCRIPTOR));
    }

    @SneakyThrows
    public void setStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, SET_STATIC_LINKING_DESCRIPTOR, descriptor.getId());
    }

    public boolean supportBinaryLinking() {
        return sendBool(id, SUPPORT_BINARY_LINKING);
    }

    @SneakyThrows
    public void setSupportBinaryLinking(boolean support) {
        B.invokeExact(id, SET_SUPPORT_BINARY_LINKING, support);
    }

    public void reset() {
        sendVoid(id, RESET);
    }
}
