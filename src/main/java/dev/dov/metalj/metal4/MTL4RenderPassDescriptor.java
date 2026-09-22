package dev.dov.metalj.metal4;

import dev.dov.metalj.commands.passes.MTLRenderPassColorAttachmentDescriptorArray;
import dev.dov.metalj.commands.passes.MTLRenderPassDepthAttachmentDescriptor;
import dev.dov.metalj.commands.passes.MTLRenderPassStencilAttachmentDescriptor;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.rate.MTLRasterizationRateMap;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4RenderPassDescriptor extends NSObject {
    private static final long MTL_4_RENDER_PASS_DESCRIPTOR = ObjC.cls("MTL4RenderPassDescriptor");

    private static final long COLOR_ATTACHMENTS = ObjC.sel("colorAttachments");
    private static final long DEFAULT_RASTER_SAMPLE_COUNT = ObjC.sel("defaultRasterSampleCount");
    private static final long DEPTH_ATTACHMENT = ObjC.sel("depthAttachment");
    private static final long GET_SAMPLE_POSITIONS_COUNT = ObjC.sel("getSamplePositions:count:");
    private static final long IMAGEBLOCK_SAMPLE_LENGTH = ObjC.sel("imageblockSampleLength");
    private static final long NEW = ObjC.sel("new");
    private static final long RASTERIZATION_RATE_MAP = ObjC.sel("rasterizationRateMap");
    private static final long RENDER_TARGET_ARRAY_LENGTH = ObjC.sel("renderTargetArrayLength");
    private static final long RENDER_TARGET_HEIGHT = ObjC.sel("renderTargetHeight");
    private static final long RENDER_TARGET_WIDTH = ObjC.sel("renderTargetWidth");
    private static final long SET_DEFAULT_RASTER_SAMPLE_COUNT = ObjC.sel("setDefaultRasterSampleCount:");
    private static final long SET_DEPTH_ATTACHMENT = ObjC.sel("setDepthAttachment:");
    private static final long SET_IMAGEBLOCK_SAMPLE_LENGTH = ObjC.sel("setImageblockSampleLength:");
    private static final long SET_RASTERIZATION_RATE_MAP = ObjC.sel("setRasterizationRateMap:");
    private static final long SET_RENDER_TARGET_ARRAY_LENGTH = ObjC.sel("setRenderTargetArrayLength:");
    private static final long SET_RENDER_TARGET_HEIGHT = ObjC.sel("setRenderTargetHeight:");
    private static final long SET_RENDER_TARGET_WIDTH = ObjC.sel("setRenderTargetWidth:");
    private static final long SET_SAMPLE_POSITIONS_COUNT = ObjC.sel("setSamplePositions:count:");
    private static final long SET_STENCIL_ATTACHMENT = ObjC.sel("setStencilAttachment:");
    private static final long SET_SUPPORT_COLOR_ATTACHMENT_MAPPING = ObjC.sel("setSupportColorAttachmentMapping:");
    private static final long SET_THREADGROUP_MEMORY_LENGTH = ObjC.sel("setThreadgroupMemoryLength:");
    private static final long SET_TILE_HEIGHT = ObjC.sel("setTileHeight:");
    private static final long SET_TILE_WIDTH = ObjC.sel("setTileWidth:");
    private static final long SET_VISIBILITY_RESULT_BUFFER = ObjC.sel("setVisibilityResultBuffer:");
    private static final long SET_VISIBILITY_RESULT_TYPE = ObjC.sel("setVisibilityResultType:");
    private static final long STENCIL_ATTACHMENT = ObjC.sel("stencilAttachment");
    private static final long SUPPORT_COLOR_ATTACHMENT_MAPPING = ObjC.sel("supportColorAttachmentMapping");
    private static final long THREADGROUP_MEMORY_LENGTH = ObjC.sel("threadgroupMemoryLength");
    private static final long TILE_HEIGHT = ObjC.sel("tileHeight");
    private static final long TILE_WIDTH = ObjC.sel("tileWidth");
    private static final long VISIBILITY_RESULT_BUFFER = ObjC.sel("visibilityResultBuffer");
    private static final long VISIBILITY_RESULT_TYPE = ObjC.sel("visibilityResultType");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle AL = handle(null, ValueLayout.ADDRESS, ObjC.LONG);
    private static final MethodHandle L_AL = handle(ObjC.LONG, ValueLayout.ADDRESS, ObjC.LONG);

    private MTL4RenderPassDescriptor(long id) {
        super(id);
    }

    public static MTL4RenderPassDescriptor of(long id) {
        return new MTL4RenderPassDescriptor(id);
    }

    public static MTL4RenderPassDescriptor new_() {
        return new MTL4RenderPassDescriptor(sendPtr(MTL_4_RENDER_PASS_DESCRIPTOR, NEW));
    }

    public MTLRenderPassColorAttachmentDescriptorArray colorAttachments() {
        return MTLRenderPassColorAttachmentDescriptorArray.of(sendPtr(id, COLOR_ATTACHMENTS));
    }

    public MTLRenderPassDepthAttachmentDescriptor depthAttachment() {
        return MTLRenderPassDepthAttachmentDescriptor.of(sendPtr(id, DEPTH_ATTACHMENT));
    }

    @SneakyThrows
    public void setDepthAttachment(MTLRenderPassDepthAttachmentDescriptor attachment) {
        P.invokeExact(id, SET_DEPTH_ATTACHMENT, attachment.getId());
    }

    public MTLRenderPassStencilAttachmentDescriptor stencilAttachment() {
        return MTLRenderPassStencilAttachmentDescriptor.of(sendPtr(id, STENCIL_ATTACHMENT));
    }

    @SneakyThrows
    public void setStencilAttachment(MTLRenderPassStencilAttachmentDescriptor attachment) {
        P.invokeExact(id, SET_STENCIL_ATTACHMENT, attachment.getId());
    }

    public long renderTargetArrayLength() {
        return sendLong(id, RENDER_TARGET_ARRAY_LENGTH);
    }

    @SneakyThrows
    public void setRenderTargetArrayLength(long length) {
        L.invokeExact(id, SET_RENDER_TARGET_ARRAY_LENGTH, length);
    }

    public long imageblockSampleLength() {
        return sendLong(id, IMAGEBLOCK_SAMPLE_LENGTH);
    }

    @SneakyThrows
    public void setImageblockSampleLength(long length) {
        L.invokeExact(id, SET_IMAGEBLOCK_SAMPLE_LENGTH, length);
    }

    public long threadgroupMemoryLength() {
        return sendLong(id, THREADGROUP_MEMORY_LENGTH);
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length) {
        L.invokeExact(id, SET_THREADGROUP_MEMORY_LENGTH, length);
    }

    public long tileWidth() {
        return sendLong(id, TILE_WIDTH);
    }

    @SneakyThrows
    public void setTileWidth(long width) {
        L.invokeExact(id, SET_TILE_WIDTH, width);
    }

    public long tileHeight() {
        return sendLong(id, TILE_HEIGHT);
    }

    @SneakyThrows
    public void setTileHeight(long height) {
        L.invokeExact(id, SET_TILE_HEIGHT, height);
    }

    public long defaultRasterSampleCount() {
        return sendLong(id, DEFAULT_RASTER_SAMPLE_COUNT);
    }

    @SneakyThrows
    public void setDefaultRasterSampleCount(long count) {
        L.invokeExact(id, SET_DEFAULT_RASTER_SAMPLE_COUNT, count);
    }

    public long renderTargetWidth() {
        return sendLong(id, RENDER_TARGET_WIDTH);
    }

    @SneakyThrows
    public void setRenderTargetWidth(long width) {
        L.invokeExact(id, SET_RENDER_TARGET_WIDTH, width);
    }

    public long renderTargetHeight() {
        return sendLong(id, RENDER_TARGET_HEIGHT);
    }

    @SneakyThrows
    public void setRenderTargetHeight(long height) {
        L.invokeExact(id, SET_RENDER_TARGET_HEIGHT, height);
    }

    public MTLRasterizationRateMap rasterizationRateMap() {
        return MTLRasterizationRateMap.of(sendPtr(id, RASTERIZATION_RATE_MAP));
    }

    @SneakyThrows
    public void setRasterizationRateMap(MTLRasterizationRateMap map) {
        P.invokeExact(id, SET_RASTERIZATION_RATE_MAP, map.getId());
    }

    public MTLBuffer visibilityResultBuffer() {
        return MTLBuffer.of(sendPtr(id, VISIBILITY_RESULT_BUFFER));
    }

    @SneakyThrows
    public void setVisibilityResultBuffer(MTLBuffer buffer) {
        P.invokeExact(id, SET_VISIBILITY_RESULT_BUFFER, buffer.getId());
    }

    public long visibilityResultType() {
        return sendLong(id, VISIBILITY_RESULT_TYPE);
    }

    @SneakyThrows
    public void setVisibilityResultType(long type) {
        L.invokeExact(id, SET_VISIBILITY_RESULT_TYPE, type);
    }

    @SneakyThrows
    public void setSamplePositions(MemorySegment positions, long count) {
        AL.invokeExact(id, SET_SAMPLE_POSITIONS_COUNT, positions, count);
    }

    @SneakyThrows
    public long getSamplePositions(MemorySegment positions, long count) {
        return (long) L_AL.invokeExact(id, GET_SAMPLE_POSITIONS_COUNT, positions, count);
    }

    public boolean supportColorAttachmentMapping() {
        return sendBool(id, SUPPORT_COLOR_ATTACHMENT_MAPPING);
    }

    @SneakyThrows
    public void setSupportColorAttachmentMapping(boolean support) {
        B.invokeExact(id, SET_SUPPORT_COLOR_ATTACHMENT_MAPPING, support);
    }
}
