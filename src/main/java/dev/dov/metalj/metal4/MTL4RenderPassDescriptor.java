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
        return new MTL4RenderPassDescriptor(sendPtr(ObjC.cls("MTL4RenderPassDescriptor"), "new"));
    }

    public MTLRenderPassColorAttachmentDescriptorArray colorAttachments() {
        return MTLRenderPassColorAttachmentDescriptorArray.of(sendPtr(id, "colorAttachments"));
    }

    public MTLRenderPassDepthAttachmentDescriptor depthAttachment() {
        return MTLRenderPassDepthAttachmentDescriptor.of(sendPtr(id, "depthAttachment"));
    }

    @SneakyThrows
    public void setDepthAttachment(MTLRenderPassDepthAttachmentDescriptor attachment) {
        P.invokeExact(id, ObjC.sel("setDepthAttachment:"), attachment.getId());
    }

    public MTLRenderPassStencilAttachmentDescriptor stencilAttachment() {
        return MTLRenderPassStencilAttachmentDescriptor.of(sendPtr(id, "stencilAttachment"));
    }

    @SneakyThrows
    public void setStencilAttachment(MTLRenderPassStencilAttachmentDescriptor attachment) {
        P.invokeExact(id, ObjC.sel("setStencilAttachment:"), attachment.getId());
    }

    public long renderTargetArrayLength() {
        return sendLong(id, "renderTargetArrayLength");
    }

    @SneakyThrows
    public void setRenderTargetArrayLength(long length) {
        L.invokeExact(id, ObjC.sel("setRenderTargetArrayLength:"), length);
    }

    public long imageblockSampleLength() {
        return sendLong(id, "imageblockSampleLength");
    }

    @SneakyThrows
    public void setImageblockSampleLength(long length) {
        L.invokeExact(id, ObjC.sel("setImageblockSampleLength:"), length);
    }

    public long threadgroupMemoryLength() {
        return sendLong(id, "threadgroupMemoryLength");
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length) {
        L.invokeExact(id, ObjC.sel("setThreadgroupMemoryLength:"), length);
    }

    public long tileWidth() {
        return sendLong(id, "tileWidth");
    }

    @SneakyThrows
    public void setTileWidth(long width) {
        L.invokeExact(id, ObjC.sel("setTileWidth:"), width);
    }

    public long tileHeight() {
        return sendLong(id, "tileHeight");
    }

    @SneakyThrows
    public void setTileHeight(long height) {
        L.invokeExact(id, ObjC.sel("setTileHeight:"), height);
    }

    public long defaultRasterSampleCount() {
        return sendLong(id, "defaultRasterSampleCount");
    }

    @SneakyThrows
    public void setDefaultRasterSampleCount(long count) {
        L.invokeExact(id, ObjC.sel("setDefaultRasterSampleCount:"), count);
    }

    public long renderTargetWidth() {
        return sendLong(id, "renderTargetWidth");
    }

    @SneakyThrows
    public void setRenderTargetWidth(long width) {
        L.invokeExact(id, ObjC.sel("setRenderTargetWidth:"), width);
    }

    public long renderTargetHeight() {
        return sendLong(id, "renderTargetHeight");
    }

    @SneakyThrows
    public void setRenderTargetHeight(long height) {
        L.invokeExact(id, ObjC.sel("setRenderTargetHeight:"), height);
    }

    public MTLRasterizationRateMap rasterizationRateMap() {
        return MTLRasterizationRateMap.of(sendPtr(id, "rasterizationRateMap"));
    }

    @SneakyThrows
    public void setRasterizationRateMap(MTLRasterizationRateMap map) {
        P.invokeExact(id, ObjC.sel("setRasterizationRateMap:"), map.getId());
    }

    public MTLBuffer visibilityResultBuffer() {
        return MTLBuffer.of(sendPtr(id, "visibilityResultBuffer"));
    }

    @SneakyThrows
    public void setVisibilityResultBuffer(MTLBuffer buffer) {
        P.invokeExact(id, ObjC.sel("setVisibilityResultBuffer:"), buffer.getId());
    }

    public long visibilityResultType() {
        return sendLong(id, "visibilityResultType");
    }

    @SneakyThrows
    public void setVisibilityResultType(long type) {
        L.invokeExact(id, ObjC.sel("setVisibilityResultType:"), type);
    }

    @SneakyThrows
    public void setSamplePositions(MemorySegment positions, long count) {
        AL.invokeExact(id, ObjC.sel("setSamplePositions:count:"), positions, count);
    }

    @SneakyThrows
    public long getSamplePositions(MemorySegment positions, long count) {
        return (long) L_AL.invokeExact(id, ObjC.sel("getSamplePositions:count:"), positions, count);
    }

    public boolean supportColorAttachmentMapping() {
        return sendBool(id, "supportColorAttachmentMapping");
    }

    @SneakyThrows
    public void setSupportColorAttachmentMapping(boolean support) {
        B.invokeExact(id, ObjC.sel("setSupportColorAttachmentMapping:"), support);
    }
}
