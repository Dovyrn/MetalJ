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
    private static final MethodHandle S = handle(null, MTLSize.LAYOUT);
    private static final MethodHandle SIZE = structHandle(MTLSize.LAYOUT);

    private MTL4TileRenderPipelineDescriptor(long id) {
        super(id);
    }

    public static MTL4TileRenderPipelineDescriptor of(long id) {
        return new MTL4TileRenderPipelineDescriptor(id);
    }

    public static MTL4TileRenderPipelineDescriptor new_() {
        return new MTL4TileRenderPipelineDescriptor(sendPtr(ObjC.cls("MTL4TileRenderPipelineDescriptor"), "new"));
    }

    public MTL4FunctionDescriptor tileFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, "tileFunctionDescriptor"));
    }

    @SneakyThrows
    public void setTileFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setTileFunctionDescriptor:"), descriptor.getId());
    }

    public long rasterSampleCount() {
        return sendLong(id, "rasterSampleCount");
    }

    @SneakyThrows
    public void setRasterSampleCount(long count) {
        L.invokeExact(id, ObjC.sel("setRasterSampleCount:"), count);
    }

    public MTLTileRenderPipelineColorAttachmentDescriptorArray colorAttachments() {
        return MTLTileRenderPipelineColorAttachmentDescriptorArray.of(sendPtr(id, "colorAttachments"));
    }

    public boolean threadgroupSizeMatchesTileSize() {
        return sendBool(id, "threadgroupSizeMatchesTileSize");
    }

    @SneakyThrows
    public void setThreadgroupSizeMatchesTileSize(boolean matches) {
        B.invokeExact(id, ObjC.sel("setThreadgroupSizeMatchesTileSize:"), matches);
    }

    public long maxTotalThreadsPerThreadgroup() {
        return sendLong(id, "maxTotalThreadsPerThreadgroup");
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerThreadgroup(long threads) {
        L.invokeExact(id, ObjC.sel("setMaxTotalThreadsPerThreadgroup:"), threads);
    }

    @SneakyThrows
    public MemorySegment requiredThreadsPerThreadgroup(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, ObjC.sel("requiredThreadsPerThreadgroup"));
    }

    @SneakyThrows
    public void setRequiredThreadsPerThreadgroup(MemorySegment threads) {
        S.invokeExact(id, ObjC.sel("setRequiredThreadsPerThreadgroup:"), threads);
    }

    public MTL4StaticLinkingDescriptor staticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, "staticLinkingDescriptor"));
    }

    @SneakyThrows
    public void setStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setStaticLinkingDescriptor:"), descriptor.getId());
    }

    public boolean supportBinaryLinking() {
        return sendBool(id, "supportBinaryLinking");
    }

    @SneakyThrows
    public void setSupportBinaryLinking(boolean support) {
        B.invokeExact(id, ObjC.sel("setSupportBinaryLinking:"), support);
    }

    public void reset() {
        sendVoid(id, "reset");
    }
}
