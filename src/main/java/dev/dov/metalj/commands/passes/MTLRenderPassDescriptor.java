package dev.dov.metalj.commands.passes;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.rate.MTLRasterizationRateMap;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPassDescriptor extends NSObject {
    private static final long MTL_RENDER_PASS_DESCRIPTOR = ObjC.cls("MTLRenderPassDescriptor");

    private static final long COLOR_ATTACHMENTS = ObjC.sel("colorAttachments");
    private static final long DEPTH_ATTACHMENT = ObjC.sel("depthAttachment");
    private static final long RASTERIZATION_RATE_MAP = ObjC.sel("rasterizationRateMap");
    private static final long RENDER_PASS_DESCRIPTOR = ObjC.sel("renderPassDescriptor");
    private static final long SAMPLE_BUFFER_ATTACHMENTS = ObjC.sel("sampleBufferAttachments");
    private static final long SET_DEFAULT_RASTER_SAMPLE_COUNT = ObjC.sel("setDefaultRasterSampleCount:");
    private static final long SET_IMAGEBLOCK_SAMPLE_LENGTH = ObjC.sel("setImageblockSampleLength:");
    private static final long SET_RASTERIZATION_RATE_MAP = ObjC.sel("setRasterizationRateMap:");
    private static final long SET_RENDER_TARGET_ARRAY_LENGTH = ObjC.sel("setRenderTargetArrayLength:");
    private static final long SET_RENDER_TARGET_HEIGHT = ObjC.sel("setRenderTargetHeight:");
    private static final long SET_RENDER_TARGET_WIDTH = ObjC.sel("setRenderTargetWidth:");
    private static final long SET_THREADGROUP_MEMORY_LENGTH = ObjC.sel("setThreadgroupMemoryLength:");
    private static final long SET_TILE_HEIGHT = ObjC.sel("setTileHeight:");
    private static final long SET_TILE_WIDTH = ObjC.sel("setTileWidth:");
    private static final long SET_VISIBILITY_RESULT_BUFFER = ObjC.sel("setVisibilityResultBuffer:");
    private static final long STENCIL_ATTACHMENT = ObjC.sel("stencilAttachment");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLRenderPassDescriptor(long id) {
        super(id);
    }

    public static MTLRenderPassDescriptor of(long id) {
        return new MTLRenderPassDescriptor(id);
    }

    public static MTLRenderPassDescriptor renderPassDescriptor() {
        return new MTLRenderPassDescriptor(owned(() -> sendPtr(MTL_RENDER_PASS_DESCRIPTOR, RENDER_PASS_DESCRIPTOR)));
    }

    public MTLRenderPassColorAttachmentDescriptorArray colorAttachments() {
        return MTLRenderPassColorAttachmentDescriptorArray.of(sendPtr(id, COLOR_ATTACHMENTS));
    }

    public MTLRenderPassDepthAttachmentDescriptor depthAttachment() {
        return MTLRenderPassDepthAttachmentDescriptor.of(sendPtr(id, DEPTH_ATTACHMENT));
    }

    public MTLRenderPassStencilAttachmentDescriptor stencilAttachment() {
        return MTLRenderPassStencilAttachmentDescriptor.of(sendPtr(id, STENCIL_ATTACHMENT));
    }

    @SneakyThrows
    public void setVisibilityResultBuffer(MTLBuffer buffer) {
        L.invokeExact(id, SET_VISIBILITY_RESULT_BUFFER, buffer.getId());
    }

    @SneakyThrows
    public void setRenderTargetArrayLength(long length) {
        L.invokeExact(id, SET_RENDER_TARGET_ARRAY_LENGTH, length);
    }

    @SneakyThrows
    public void setImageblockSampleLength(long length) {
        L.invokeExact(id, SET_IMAGEBLOCK_SAMPLE_LENGTH, length);
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length) {
        L.invokeExact(id, SET_THREADGROUP_MEMORY_LENGTH, length);
    }

    @SneakyThrows
    public void setTileWidth(long width) {
        L.invokeExact(id, SET_TILE_WIDTH, width);
    }

    @SneakyThrows
    public void setTileHeight(long height) {
        L.invokeExact(id, SET_TILE_HEIGHT, height);
    }

    @SneakyThrows
    public void setDefaultRasterSampleCount(long count) {
        L.invokeExact(id, SET_DEFAULT_RASTER_SAMPLE_COUNT, count);
    }

    @SneakyThrows
    public void setRenderTargetWidth(long width) {
        L.invokeExact(id, SET_RENDER_TARGET_WIDTH, width);
    }

    @SneakyThrows
    public void setRenderTargetHeight(long height) {
        L.invokeExact(id, SET_RENDER_TARGET_HEIGHT, height);
    }

    public MTLRenderPassSampleBufferAttachmentDescriptorArray sampleBufferAttachments() {
        return MTLRenderPassSampleBufferAttachmentDescriptorArray.of(sendPtr(id, SAMPLE_BUFFER_ATTACHMENTS));
    }

    public MTLRasterizationRateMap rasterizationRateMap() {
        return MTLRasterizationRateMap.of(sendPtr(id, RASTERIZATION_RATE_MAP));
    }

    @SneakyThrows
    public void setRasterizationRateMap(MTLRasterizationRateMap map) {
        P.invokeExact(id, SET_RASTERIZATION_RATE_MAP, map.getId());
    }
}
