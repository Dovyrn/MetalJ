package dev.dov.metalj.resources.textures;

import dev.dov.metalj.resources.MTLResource;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.MTLRegion;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTexture extends MTLResource {
    private static final long ALLOW_GPU_OPTIMIZED_CONTENTS = ObjC.sel("allowGPUOptimizedContents");
    private static final long ARRAY_LENGTH = ObjC.sel("arrayLength");
    private static final long BUFFER = ObjC.sel("buffer");
    private static final long BUFFER_BYTES_PER_ROW = ObjC.sel("bufferBytesPerRow");
    private static final long BUFFER_OFFSET = ObjC.sel("bufferOffset");
    private static final long COMPRESSION_TYPE = ObjC.sel("compressionType");
    private static final long DEPTH = ObjC.sel("depth");
    private static final long GET_BYTES_BYTES_PER_ROW_BYTES_PER_IMAGE_FROM_REGION_MIPMAP_LEVEL_SLICE = ObjC.sel("getBytes:bytesPerRow:bytesPerImage:fromRegion:mipmapLevel:slice:");
    private static final long GET_BYTES_BYTES_PER_ROW_FROM_REGION_MIPMAP_LEVEL = ObjC.sel("getBytes:bytesPerRow:fromRegion:mipmapLevel:");
    private static final long GPU_RESOURCE_ID = ObjC.sel("gpuResourceID");
    private static final long HEIGHT = ObjC.sel("height");
    private static final long IS_FRAMEBUFFER_ONLY = ObjC.sel("isFramebufferOnly");
    private static final long IS_SHAREABLE = ObjC.sel("isShareable");
    private static final long MIPMAP_LEVEL_COUNT = ObjC.sel("mipmapLevelCount");
    private static final long NEW_REMOTE_TEXTURE_VIEW_FOR_DEVICE = ObjC.sel("newRemoteTextureViewForDevice:");
    private static final long NEW_SHARED_TEXTURE_HANDLE = ObjC.sel("newSharedTextureHandle");
    private static final long NEW_TEXTURE_VIEW_WITH_PIXEL_FORMAT = ObjC.sel("newTextureViewWithPixelFormat:");
    private static final long NEW_TEXTURE_VIEW_WITH_PIXEL_FORMAT_TEXTURE_TYPE_LEVELS_SLICES = ObjC.sel("newTextureViewWithPixelFormat:textureType:levels:slices:");
    private static final long NEW_TEXTURE_VIEW_WITH_PIXEL_FORMAT_TEXTURE_TYPE_LEVELS_SLICES_SWIZZLE = ObjC.sel("newTextureViewWithPixelFormat:textureType:levels:slices:swizzle:");
    private static final long PARENT_RELATIVE_LEVEL = ObjC.sel("parentRelativeLevel");
    private static final long PARENT_RELATIVE_SLICE = ObjC.sel("parentRelativeSlice");
    private static final long PARENT_TEXTURE = ObjC.sel("parentTexture");
    private static final long PIXEL_FORMAT = ObjC.sel("pixelFormat");
    private static final long REPLACE_REGION_MIPMAP_LEVEL_SLICE_WITH_BYTES_BYTES_PER_ROW_BYTES_PER_IMAGE = ObjC.sel("replaceRegion:mipmapLevel:slice:withBytes:bytesPerRow:bytesPerImage:");
    private static final long REPLACE_REGION_MIPMAP_LEVEL_WITH_BYTES_BYTES_PER_ROW = ObjC.sel("replaceRegion:mipmapLevel:withBytes:bytesPerRow:");
    private static final long SAMPLE_COUNT = ObjC.sel("sampleCount");
    private static final long SWIZZLE_SEL = ObjC.sel("swizzle");
    private static final long TEXTURE_TYPE = ObjC.sel("textureType");
    private static final long USAGE = ObjC.sel("usage");
    private static final long WIDTH = ObjC.sel("width");

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
        return new MTLTexture(sendPtr(id, PARENT_TEXTURE));
    }

    public long parentRelativeLevel() {
        return sendLong(id, PARENT_RELATIVE_LEVEL);
    }

    public long parentRelativeSlice() {
        return sendLong(id, PARENT_RELATIVE_SLICE);
    }

    public MTLBuffer buffer() {
        return MTLBuffer.of(sendPtr(id, BUFFER));
    }

    public long bufferOffset() {
        return sendLong(id, BUFFER_OFFSET);
    }

    public long bufferBytesPerRow() {
        return sendLong(id, BUFFER_BYTES_PER_ROW);
    }

    public long textureType() {
        return sendLong(id, TEXTURE_TYPE);
    }

    public long pixelFormat() {
        return sendLong(id, PIXEL_FORMAT);
    }

    public long width() {
        return sendLong(id, WIDTH);
    }

    public long height() {
        return sendLong(id, HEIGHT);
    }

    public long depth() {
        return sendLong(id, DEPTH);
    }

    public long mipmapLevelCount() {
        return sendLong(id, MIPMAP_LEVEL_COUNT);
    }

    public long sampleCount() {
        return sendLong(id, SAMPLE_COUNT);
    }

    public long arrayLength() {
        return sendLong(id, ARRAY_LENGTH);
    }

    public long usage() {
        return sendLong(id, USAGE);
    }

    public boolean isShareable() {
        return sendBool(id, IS_SHAREABLE);
    }

    public boolean isFramebufferOnly() {
        return sendBool(id, IS_FRAMEBUFFER_ONLY);
    }

    public boolean allowGPUOptimizedContents() {
        return sendBool(id, ALLOW_GPU_OPTIMIZED_CONTENTS);
    }

    public long compressionType() {
        return sendLong(id, COMPRESSION_TYPE);
    }

    public long gpuResourceID() {
        return sendLong(id, GPU_RESOURCE_ID);
    }

    @SneakyThrows
    public MemorySegment swizzle(SegmentAllocator allocator) {
        return (MemorySegment) SWIZZLE.invokeExact(allocator, id, SWIZZLE_SEL);
    }

    @SneakyThrows
    public void getBytes(MemorySegment pixelBytes, long bytesPerRow, MemorySegment region, long level) {
        ALGL.invokeExact(id, GET_BYTES_BYTES_PER_ROW_FROM_REGION_MIPMAP_LEVEL, pixelBytes, bytesPerRow,
                region, level);
    }

    @SneakyThrows
    public void getBytes(MemorySegment pixelBytes, long bytesPerRow, long bytesPerImage, MemorySegment region,
            long level, long slice) {
        ALLGLL.invokeExact(id, GET_BYTES_BYTES_PER_ROW_BYTES_PER_IMAGE_FROM_REGION_MIPMAP_LEVEL_SLICE,
                pixelBytes, bytesPerRow, bytesPerImage, region, level, slice);
    }

    @SneakyThrows
    public void replaceRegion(MemorySegment region, long level, MemorySegment pixelBytes, long bytesPerRow) {
        GLAL.invokeExact(id, REPLACE_REGION_MIPMAP_LEVEL_WITH_BYTES_BYTES_PER_ROW, region, level,
                pixelBytes, bytesPerRow);
    }

    @SneakyThrows
    public void replaceRegion(MemorySegment region, long level, long slice, MemorySegment pixelBytes,
            long bytesPerRow, long bytesPerImage) {
        GLLALL.invokeExact(id, REPLACE_REGION_MIPMAP_LEVEL_SLICE_WITH_BYTES_BYTES_PER_ROW_BYTES_PER_IMAGE,
                region, level, slice, pixelBytes, bytesPerRow, bytesPerImage);
    }

    @SneakyThrows
    public MTLTexture newTextureViewWithPixelFormat(long pixelFormat) {
        return new MTLTexture((long) P_L.invokeExact(id, NEW_TEXTURE_VIEW_WITH_PIXEL_FORMAT, pixelFormat));
    }

    @SneakyThrows
    public MTLTexture newTextureViewWithPixelFormat(long pixelFormat, long textureType, MemorySegment levels,
            MemorySegment slices) {
        return new MTLTexture((long) P_LLRR.invokeExact(id,
                NEW_TEXTURE_VIEW_WITH_PIXEL_FORMAT_TEXTURE_TYPE_LEVELS_SLICES, pixelFormat, textureType,
                levels, slices));
    }

    @SneakyThrows
    public MTLTexture newTextureViewWithPixelFormat(long pixelFormat, long textureType, MemorySegment levels,
            MemorySegment slices, MemorySegment swizzle) {
        return new MTLTexture((long) P_LLRRW.invokeExact(id,
                NEW_TEXTURE_VIEW_WITH_PIXEL_FORMAT_TEXTURE_TYPE_LEVELS_SLICES_SWIZZLE, pixelFormat,
                textureType, levels, slices, swizzle));
    }

    public MTLSharedTextureHandle newSharedTextureHandle() {
        return MTLSharedTextureHandle.of(sendPtr(id, NEW_SHARED_TEXTURE_HANDLE));
    }

    @SneakyThrows
    public MTLTexture newRemoteTextureViewForDevice(MTLDevice device) {
        return new MTLTexture((long) P_P.invokeExact(id, NEW_REMOTE_TEXTURE_VIEW_FOR_DEVICE,
                device.getId()));
    }
}
