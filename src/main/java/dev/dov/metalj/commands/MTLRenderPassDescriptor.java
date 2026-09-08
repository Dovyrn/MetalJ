package dev.dov.metalj.commands;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLBuffer;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPassDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLRenderPassDescriptor(long id) {
        super(id);
    }

    public static MTLRenderPassDescriptor of(long id) {
        return new MTLRenderPassDescriptor(id);
    }

    public static MTLRenderPassDescriptor renderPassDescriptor() {
        return new MTLRenderPassDescriptor(sendPtr(ObjC.cls("MTLRenderPassDescriptor"), "renderPassDescriptor"));
    }

    public MTLRenderPassColorAttachmentDescriptorArray colorAttachments() {
        return MTLRenderPassColorAttachmentDescriptorArray.of(sendPtr(id, "colorAttachments"));
    }

    public MTLRenderPassDepthAttachmentDescriptor depthAttachment() {
        return MTLRenderPassDepthAttachmentDescriptor.of(sendPtr(id, "depthAttachment"));
    }

    public MTLRenderPassStencilAttachmentDescriptor stencilAttachment() {
        return MTLRenderPassStencilAttachmentDescriptor.of(sendPtr(id, "stencilAttachment"));
    }

    @SneakyThrows
    public void setVisibilityResultBuffer(MTLBuffer buffer) {
        L.invokeExact(id, ObjC.sel("setVisibilityResultBuffer:"), buffer.getId());
    }

    @SneakyThrows
    public void setRenderTargetArrayLength(long length) {
        L.invokeExact(id, ObjC.sel("setRenderTargetArrayLength:"), length);
    }

    @SneakyThrows
    public void setImageblockSampleLength(long length) {
        L.invokeExact(id, ObjC.sel("setImageblockSampleLength:"), length);
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length) {
        L.invokeExact(id, ObjC.sel("setThreadgroupMemoryLength:"), length);
    }

    @SneakyThrows
    public void setTileWidth(long width) {
        L.invokeExact(id, ObjC.sel("setTileWidth:"), width);
    }

    @SneakyThrows
    public void setTileHeight(long height) {
        L.invokeExact(id, ObjC.sel("setTileHeight:"), height);
    }

    @SneakyThrows
    public void setDefaultRasterSampleCount(long count) {
        L.invokeExact(id, ObjC.sel("setDefaultRasterSampleCount:"), count);
    }

    @SneakyThrows
    public void setRenderTargetWidth(long width) {
        L.invokeExact(id, ObjC.sel("setRenderTargetWidth:"), width);
    }

    @SneakyThrows
    public void setRenderTargetHeight(long height) {
        L.invokeExact(id, ObjC.sel("setRenderTargetHeight:"), height);
    }

    public MTLRenderPassSampleBufferAttachmentDescriptorArray sampleBufferAttachments() {
        return MTLRenderPassSampleBufferAttachmentDescriptorArray.of(sendPtr(id, "sampleBufferAttachments"));
    }
}
