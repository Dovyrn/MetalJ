package dev.dov.metalj.resources.textures;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTextureDescriptor extends NSObject {
    private static final long MTL_TEXTURE_DESCRIPTOR = ObjC.cls("MTLTextureDescriptor");

    private static final long ALLOW_GPU_OPTIMIZED_CONTENTS = ObjC.sel("allowGPUOptimizedContents");
    private static final long ARRAY_LENGTH = ObjC.sel("arrayLength");
    private static final long COMPRESSION_TYPE = ObjC.sel("compressionType");
    private static final long CPU_CACHE_MODE = ObjC.sel("cpuCacheMode");
    private static final long DEPTH = ObjC.sel("depth");
    private static final long HAZARD_TRACKING_MODE = ObjC.sel("hazardTrackingMode");
    private static final long HEIGHT = ObjC.sel("height");
    private static final long MIPMAP_LEVEL_COUNT = ObjC.sel("mipmapLevelCount");
    private static final long NEW = ObjC.sel("new");
    private static final long PIXEL_FORMAT = ObjC.sel("pixelFormat");
    private static final long RESOURCE_OPTIONS = ObjC.sel("resourceOptions");
    private static final long SAMPLE_COUNT = ObjC.sel("sampleCount");
    private static final long SET_ALLOW_GPU_OPTIMIZED_CONTENTS = ObjC.sel("setAllowGPUOptimizedContents:");
    private static final long SET_ARRAY_LENGTH = ObjC.sel("setArrayLength:");
    private static final long SET_COMPRESSION_TYPE = ObjC.sel("setCompressionType:");
    private static final long SET_CPU_CACHE_MODE = ObjC.sel("setCpuCacheMode:");
    private static final long SET_DEPTH = ObjC.sel("setDepth:");
    private static final long SET_HAZARD_TRACKING_MODE = ObjC.sel("setHazardTrackingMode:");
    private static final long SET_HEIGHT = ObjC.sel("setHeight:");
    private static final long SET_MIPMAP_LEVEL_COUNT = ObjC.sel("setMipmapLevelCount:");
    private static final long SET_PIXEL_FORMAT = ObjC.sel("setPixelFormat:");
    private static final long SET_RESOURCE_OPTIONS = ObjC.sel("setResourceOptions:");
    private static final long SET_SAMPLE_COUNT = ObjC.sel("setSampleCount:");
    private static final long SET_STORAGE_MODE = ObjC.sel("setStorageMode:");
    private static final long SET_SWIZZLE = ObjC.sel("setSwizzle:");
    private static final long SET_TEXTURE_TYPE = ObjC.sel("setTextureType:");
    private static final long SET_USAGE = ObjC.sel("setUsage:");
    private static final long SET_WIDTH = ObjC.sel("setWidth:");
    private static final long STORAGE_MODE = ObjC.sel("storageMode");
    private static final long SWIZZLE_SEL = ObjC.sel("swizzle");
    private static final long TEXTURE2_D_DESCRIPTOR_WITH_PIXEL_FORMAT_WIDTH_HEIGHT_MIPMAPPED = ObjC.sel("texture2DDescriptorWithPixelFormat:width:height:mipmapped:");
    private static final long TEXTURE_BUFFER_DESCRIPTOR_WITH_PIXEL_FORMAT_WIDTH_RESOURCE_OPTIONS_USAGE = ObjC.sel("textureBufferDescriptorWithPixelFormat:width:resourceOptions:usage:");
    private static final long TEXTURE_CUBE_DESCRIPTOR_WITH_PIXEL_FORMAT_SIZE_MIPMAPPED = ObjC.sel("textureCubeDescriptorWithPixelFormat:size:mipmapped:");
    private static final long TEXTURE_TYPE = ObjC.sel("textureType");
    private static final long USAGE = ObjC.sel("usage");
    private static final long WIDTH = ObjC.sel("width");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle W = handle(null, MTLTextureSwizzleChannels.LAYOUT);
    private static final MethodHandle SWIZZLE = structHandle(MTLTextureSwizzleChannels.LAYOUT);
    private static final MethodHandle P_LLLB = handle(ObjC.PTR, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.BOOL);
    private static final MethodHandle P_LLB = handle(ObjC.PTR, ObjC.LONG, ObjC.LONG, ObjC.BOOL);
    private static final MethodHandle P_LLLL = handle(ObjC.PTR, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);

    private MTLTextureDescriptor(long id) {
        super(id);
    }

    public static MTLTextureDescriptor of(long id) {
        return new MTLTextureDescriptor(id);
    }

    public static MTLTextureDescriptor new_() {
        return new MTLTextureDescriptor(sendPtr(MTL_TEXTURE_DESCRIPTOR, NEW));
    }

    @SneakyThrows
    public static MTLTextureDescriptor texture2DDescriptorWithPixelFormat(long pixelFormat, long width, long height,
            boolean mipmapped) {
        return new MTLTextureDescriptor(owned(() -> (long) P_LLLB.invokeExact(MTL_TEXTURE_DESCRIPTOR,
                TEXTURE2_D_DESCRIPTOR_WITH_PIXEL_FORMAT_WIDTH_HEIGHT_MIPMAPPED, pixelFormat, width, height,
                mipmapped)));
    }

    @SneakyThrows
    public static MTLTextureDescriptor textureCubeDescriptorWithPixelFormat(long pixelFormat, long size,
            boolean mipmapped) {
        return new MTLTextureDescriptor(owned(() -> (long) P_LLB.invokeExact(MTL_TEXTURE_DESCRIPTOR,
                TEXTURE_CUBE_DESCRIPTOR_WITH_PIXEL_FORMAT_SIZE_MIPMAPPED, pixelFormat, size, mipmapped)));
    }

    @SneakyThrows
    public static MTLTextureDescriptor textureBufferDescriptorWithPixelFormat(long pixelFormat, long width,
            long resourceOptions, long usage) {
        return new MTLTextureDescriptor(owned(() -> (long) P_LLLL.invokeExact(MTL_TEXTURE_DESCRIPTOR,
                TEXTURE_BUFFER_DESCRIPTOR_WITH_PIXEL_FORMAT_WIDTH_RESOURCE_OPTIONS_USAGE, pixelFormat, width,
                resourceOptions, usage)));
    }

    public long textureType() {
        return sendLong(id, TEXTURE_TYPE);
    }

    @SneakyThrows
    public void setTextureType(long textureType) {
        L.invokeExact(id, SET_TEXTURE_TYPE, textureType);
    }

    public long pixelFormat() {
        return sendLong(id, PIXEL_FORMAT);
    }

    @SneakyThrows
    public void setPixelFormat(long pixelFormat) {
        L.invokeExact(id, SET_PIXEL_FORMAT, pixelFormat);
    }

    public long width() {
        return sendLong(id, WIDTH);
    }

    @SneakyThrows
    public void setWidth(long width) {
        L.invokeExact(id, SET_WIDTH, width);
    }

    public long height() {
        return sendLong(id, HEIGHT);
    }

    @SneakyThrows
    public void setHeight(long height) {
        L.invokeExact(id, SET_HEIGHT, height);
    }

    public long depth() {
        return sendLong(id, DEPTH);
    }

    @SneakyThrows
    public void setDepth(long depth) {
        L.invokeExact(id, SET_DEPTH, depth);
    }

    public long mipmapLevelCount() {
        return sendLong(id, MIPMAP_LEVEL_COUNT);
    }

    @SneakyThrows
    public void setMipmapLevelCount(long count) {
        L.invokeExact(id, SET_MIPMAP_LEVEL_COUNT, count);
    }

    public long sampleCount() {
        return sendLong(id, SAMPLE_COUNT);
    }

    @SneakyThrows
    public void setSampleCount(long count) {
        L.invokeExact(id, SET_SAMPLE_COUNT, count);
    }

    public long arrayLength() {
        return sendLong(id, ARRAY_LENGTH);
    }

    @SneakyThrows
    public void setArrayLength(long length) {
        L.invokeExact(id, SET_ARRAY_LENGTH, length);
    }

    public long resourceOptions() {
        return sendLong(id, RESOURCE_OPTIONS);
    }

    @SneakyThrows
    public void setResourceOptions(long options) {
        L.invokeExact(id, SET_RESOURCE_OPTIONS, options);
    }

    public long cpuCacheMode() {
        return sendLong(id, CPU_CACHE_MODE);
    }

    @SneakyThrows
    public void setCpuCacheMode(long mode) {
        L.invokeExact(id, SET_CPU_CACHE_MODE, mode);
    }

    public long storageMode() {
        return sendLong(id, STORAGE_MODE);
    }

    @SneakyThrows
    public void setStorageMode(long mode) {
        L.invokeExact(id, SET_STORAGE_MODE, mode);
    }

    public long hazardTrackingMode() {
        return sendLong(id, HAZARD_TRACKING_MODE);
    }

    @SneakyThrows
    public void setHazardTrackingMode(long mode) {
        L.invokeExact(id, SET_HAZARD_TRACKING_MODE, mode);
    }

    public long usage() {
        return sendLong(id, USAGE);
    }

    @SneakyThrows
    public void setUsage(long usage) {
        L.invokeExact(id, SET_USAGE, usage);
    }

    public boolean allowGPUOptimizedContents() {
        return sendBool(id, ALLOW_GPU_OPTIMIZED_CONTENTS);
    }

    @SneakyThrows
    public void setAllowGPUOptimizedContents(boolean allow) {
        B.invokeExact(id, SET_ALLOW_GPU_OPTIMIZED_CONTENTS, allow);
    }

    public long compressionType() {
        return sendLong(id, COMPRESSION_TYPE);
    }

    @SneakyThrows
    public void setCompressionType(long type) {
        L.invokeExact(id, SET_COMPRESSION_TYPE, type);
    }

    @SneakyThrows
    public MemorySegment swizzle(SegmentAllocator allocator) {
        return (MemorySegment) SWIZZLE.invokeExact(allocator, id, SWIZZLE_SEL);
    }

    @SneakyThrows
    public void setSwizzle(MemorySegment swizzle) {
        W.invokeExact(id, SET_SWIZZLE, swizzle);
    }
}
