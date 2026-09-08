package dev.dov.metalj.resources.textures;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTextureDescriptor extends NSObject {
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
        return new MTLTextureDescriptor(sendPtr(ObjC.cls("MTLTextureDescriptor"), "new"));
    }

    @SneakyThrows
    public static MTLTextureDescriptor texture2DDescriptorWithPixelFormat(long pixelFormat, long width, long height,
            boolean mipmapped) {
        return new MTLTextureDescriptor((long) P_LLLB.invokeExact(ObjC.cls("MTLTextureDescriptor"),
                ObjC.sel("texture2DDescriptorWithPixelFormat:width:height:mipmapped:"), pixelFormat, width, height,
                mipmapped));
    }

    @SneakyThrows
    public static MTLTextureDescriptor textureCubeDescriptorWithPixelFormat(long pixelFormat, long size,
            boolean mipmapped) {
        return new MTLTextureDescriptor((long) P_LLB.invokeExact(ObjC.cls("MTLTextureDescriptor"),
                ObjC.sel("textureCubeDescriptorWithPixelFormat:size:mipmapped:"), pixelFormat, size, mipmapped));
    }

    @SneakyThrows
    public static MTLTextureDescriptor textureBufferDescriptorWithPixelFormat(long pixelFormat, long width,
            long resourceOptions, long usage) {
        return new MTLTextureDescriptor((long) P_LLLL.invokeExact(ObjC.cls("MTLTextureDescriptor"),
                ObjC.sel("textureBufferDescriptorWithPixelFormat:width:resourceOptions:usage:"), pixelFormat, width,
                resourceOptions, usage));
    }

    public long textureType() {
        return sendLong(id, "textureType");
    }

    @SneakyThrows
    public void setTextureType(long textureType) {
        L.invokeExact(id, ObjC.sel("setTextureType:"), textureType);
    }

    public long pixelFormat() {
        return sendLong(id, "pixelFormat");
    }

    @SneakyThrows
    public void setPixelFormat(long pixelFormat) {
        L.invokeExact(id, ObjC.sel("setPixelFormat:"), pixelFormat);
    }

    public long width() {
        return sendLong(id, "width");
    }

    @SneakyThrows
    public void setWidth(long width) {
        L.invokeExact(id, ObjC.sel("setWidth:"), width);
    }

    public long height() {
        return sendLong(id, "height");
    }

    @SneakyThrows
    public void setHeight(long height) {
        L.invokeExact(id, ObjC.sel("setHeight:"), height);
    }

    public long depth() {
        return sendLong(id, "depth");
    }

    @SneakyThrows
    public void setDepth(long depth) {
        L.invokeExact(id, ObjC.sel("setDepth:"), depth);
    }

    public long mipmapLevelCount() {
        return sendLong(id, "mipmapLevelCount");
    }

    @SneakyThrows
    public void setMipmapLevelCount(long count) {
        L.invokeExact(id, ObjC.sel("setMipmapLevelCount:"), count);
    }

    public long sampleCount() {
        return sendLong(id, "sampleCount");
    }

    @SneakyThrows
    public void setSampleCount(long count) {
        L.invokeExact(id, ObjC.sel("setSampleCount:"), count);
    }

    public long arrayLength() {
        return sendLong(id, "arrayLength");
    }

    @SneakyThrows
    public void setArrayLength(long length) {
        L.invokeExact(id, ObjC.sel("setArrayLength:"), length);
    }

    public long resourceOptions() {
        return sendLong(id, "resourceOptions");
    }

    @SneakyThrows
    public void setResourceOptions(long options) {
        L.invokeExact(id, ObjC.sel("setResourceOptions:"), options);
    }

    public long cpuCacheMode() {
        return sendLong(id, "cpuCacheMode");
    }

    @SneakyThrows
    public void setCpuCacheMode(long mode) {
        L.invokeExact(id, ObjC.sel("setCpuCacheMode:"), mode);
    }

    public long storageMode() {
        return sendLong(id, "storageMode");
    }

    @SneakyThrows
    public void setStorageMode(long mode) {
        L.invokeExact(id, ObjC.sel("setStorageMode:"), mode);
    }

    public long hazardTrackingMode() {
        return sendLong(id, "hazardTrackingMode");
    }

    @SneakyThrows
    public void setHazardTrackingMode(long mode) {
        L.invokeExact(id, ObjC.sel("setHazardTrackingMode:"), mode);
    }

    public long usage() {
        return sendLong(id, "usage");
    }

    @SneakyThrows
    public void setUsage(long usage) {
        L.invokeExact(id, ObjC.sel("setUsage:"), usage);
    }

    public boolean allowGPUOptimizedContents() {
        return sendBool(id, "allowGPUOptimizedContents");
    }

    @SneakyThrows
    public void setAllowGPUOptimizedContents(boolean allow) {
        B.invokeExact(id, ObjC.sel("setAllowGPUOptimizedContents:"), allow);
    }

    public long compressionType() {
        return sendLong(id, "compressionType");
    }

    @SneakyThrows
    public void setCompressionType(long type) {
        L.invokeExact(id, ObjC.sel("setCompressionType:"), type);
    }

    @SneakyThrows
    public MemorySegment swizzle(SegmentAllocator allocator) {
        return (MemorySegment) SWIZZLE.invokeExact(allocator, id, ObjC.sel("swizzle"));
    }

    @SneakyThrows
    public void setSwizzle(MemorySegment swizzle) {
        W.invokeExact(id, ObjC.sel("setSwizzle:"), swizzle);
    }
}
