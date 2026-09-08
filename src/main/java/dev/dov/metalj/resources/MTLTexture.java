package dev.dov.metalj.resources;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTexture extends MTLResource {
    private static final MethodHandle ALGL = handle(null, ValueLayout.ADDRESS, ObjC.LONG, MTLRegion.LAYOUT,
            ObjC.LONG);
    private static final MethodHandle ALLGLL = handle(null, ValueLayout.ADDRESS, ObjC.LONG, ObjC.LONG,
            MTLRegion.LAYOUT, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle GLAL = handle(null, MTLRegion.LAYOUT, ObjC.LONG, ValueLayout.ADDRESS,
            ObjC.LONG);
    private static final MethodHandle GLLALL = handle(null, MTLRegion.LAYOUT, ObjC.LONG, ObjC.LONG,
            ValueLayout.ADDRESS, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);
    private static final MethodHandle P_LLRR = handle(ObjC.PTR, ObjC.LONG, ObjC.LONG, NSRange.LAYOUT,
            NSRange.LAYOUT);
    private static final MethodHandle P_LLRRW = handle(ObjC.PTR, ObjC.LONG, ObjC.LONG, NSRange.LAYOUT,
            NSRange.LAYOUT, MTLTextureSwizzleChannels.LAYOUT);
    private static final MethodHandle SWIZZLE = structHandle(MTLTextureSwizzleChannels.LAYOUT);

    private MTLTexture(long id) {
        super(id);
    }

    public static MTLTexture of(long id) {
        return new MTLTexture(id);
    }

    public MTLTexture parentTexture() {
        return new MTLTexture(sendPtr(id, "parentTexture"));
    }

    public long parentRelativeLevel() {
        return sendLong(id, "parentRelativeLevel");
    }

    public long parentRelativeSlice() {
        return sendLong(id, "parentRelativeSlice");
    }

    public MTLBuffer buffer() {
        return MTLBuffer.of(sendPtr(id, "buffer"));
    }

    public long bufferOffset() {
        return sendLong(id, "bufferOffset");
    }

    public long bufferBytesPerRow() {
        return sendLong(id, "bufferBytesPerRow");
    }

    public long textureType() {
        return sendLong(id, "textureType");
    }

    public long pixelFormat() {
        return sendLong(id, "pixelFormat");
    }

    public long width() {
        return sendLong(id, "width");
    }

    public long height() {
        return sendLong(id, "height");
    }

    public long depth() {
        return sendLong(id, "depth");
    }

    public long mipmapLevelCount() {
        return sendLong(id, "mipmapLevelCount");
    }

    public long sampleCount() {
        return sendLong(id, "sampleCount");
    }

    public long arrayLength() {
        return sendLong(id, "arrayLength");
    }

    public long usage() {
        return sendLong(id, "usage");
    }

    public boolean isShareable() {
        return sendBool(id, "isShareable");
    }

    public boolean isFramebufferOnly() {
        return sendBool(id, "isFramebufferOnly");
    }

    public boolean allowGPUOptimizedContents() {
        return sendBool(id, "allowGPUOptimizedContents");
    }

    public long compressionType() {
        return sendLong(id, "compressionType");
    }

    public long gpuResourceID() {
        return sendLong(id, "gpuResourceID");
    }

    @SneakyThrows
    public MemorySegment swizzle(SegmentAllocator allocator) {
        return (MemorySegment) SWIZZLE.invokeExact(allocator, id, ObjC.sel("swizzle"));
    }

    @SneakyThrows
    public void getBytes(MemorySegment pixelBytes, long bytesPerRow, MemorySegment region, long level) {
        ALGL.invokeExact(id, ObjC.sel("getBytes:bytesPerRow:fromRegion:mipmapLevel:"), pixelBytes, bytesPerRow,
                region, level);
    }

    @SneakyThrows
    public void getBytes(MemorySegment pixelBytes, long bytesPerRow, long bytesPerImage, MemorySegment region,
            long level, long slice) {
        ALLGLL.invokeExact(id, ObjC.sel("getBytes:bytesPerRow:bytesPerImage:fromRegion:mipmapLevel:slice:"),
                pixelBytes, bytesPerRow, bytesPerImage, region, level, slice);
    }

    @SneakyThrows
    public void replaceRegion(MemorySegment region, long level, MemorySegment pixelBytes, long bytesPerRow) {
        GLAL.invokeExact(id, ObjC.sel("replaceRegion:mipmapLevel:withBytes:bytesPerRow:"), region, level,
                pixelBytes, bytesPerRow);
    }

    @SneakyThrows
    public void replaceRegion(MemorySegment region, long level, long slice, MemorySegment pixelBytes,
            long bytesPerRow, long bytesPerImage) {
        GLLALL.invokeExact(id, ObjC.sel("replaceRegion:mipmapLevel:slice:withBytes:bytesPerRow:bytesPerImage:"),
                region, level, slice, pixelBytes, bytesPerRow, bytesPerImage);
    }

    @SneakyThrows
    public MTLTexture newTextureViewWithPixelFormat(long pixelFormat) {
        return new MTLTexture((long) P_L.invokeExact(id, ObjC.sel("newTextureViewWithPixelFormat:"), pixelFormat));
    }

    @SneakyThrows
    public MTLTexture newTextureViewWithPixelFormat(long pixelFormat, long textureType, MemorySegment levels,
            MemorySegment slices) {
        return new MTLTexture((long) P_LLRR.invokeExact(id,
                ObjC.sel("newTextureViewWithPixelFormat:textureType:levels:slices:"), pixelFormat, textureType,
                levels, slices));
    }

    @SneakyThrows
    public MTLTexture newTextureViewWithPixelFormat(long pixelFormat, long textureType, MemorySegment levels,
            MemorySegment slices, MemorySegment swizzle) {
        return new MTLTexture((long) P_LLRRW.invokeExact(id,
                ObjC.sel("newTextureViewWithPixelFormat:textureType:levels:slices:swizzle:"), pixelFormat,
                textureType, levels, slices, swizzle));
    }

    public long newSharedTextureHandle() {
        return sendPtr(id, "newSharedTextureHandle");
    }

    @SneakyThrows
    public MTLTexture newRemoteTextureViewForDevice(MTLDevice device) {
        return new MTLTexture((long) P_P.invokeExact(id, ObjC.sel("newRemoteTextureViewForDevice:"),
                device.getId()));
    }
}
