package dev.dov.metalj.resources.textures;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTextureViewDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle R = handle(null, NSRange.LAYOUT);
    private static final MethodHandle RANGE = structHandle(NSRange.LAYOUT);
    private static final MethodHandle SET_SWIZZLE = handle(null, MTLTextureSwizzleChannels.LAYOUT);
    private static final MethodHandle SWIZZLE = structHandle(MTLTextureSwizzleChannels.LAYOUT);

    private MTLTextureViewDescriptor(long id) {
        super(id);
    }

    public static MTLTextureViewDescriptor of(long id) {
        return new MTLTextureViewDescriptor(id);
    }

    public static MTLTextureViewDescriptor new_() {
        return new MTLTextureViewDescriptor(sendPtr(ObjC.cls("MTLTextureViewDescriptor"), "new"));
    }

    public long pixelFormat() {
        return sendLong(id, "pixelFormat");
    }

    @SneakyThrows
    public void setPixelFormat(long format) {
        L.invokeExact(id, ObjC.sel("setPixelFormat:"), format);
    }

    public long textureType() {
        return sendLong(id, "textureType");
    }

    @SneakyThrows
    public void setTextureType(long type) {
        L.invokeExact(id, ObjC.sel("setTextureType:"), type);
    }

    @SneakyThrows
    public MemorySegment levelRange(SegmentAllocator allocator) {
        return (MemorySegment) RANGE.invokeExact(allocator, id, ObjC.sel("levelRange"));
    }

    @SneakyThrows
    public void setLevelRange(MemorySegment range) {
        R.invokeExact(id, ObjC.sel("setLevelRange:"), range);
    }

    @SneakyThrows
    public MemorySegment sliceRange(SegmentAllocator allocator) {
        return (MemorySegment) RANGE.invokeExact(allocator, id, ObjC.sel("sliceRange"));
    }

    @SneakyThrows
    public void setSliceRange(MemorySegment range) {
        R.invokeExact(id, ObjC.sel("setSliceRange:"), range);
    }

    @SneakyThrows
    public MemorySegment swizzle(SegmentAllocator allocator) {
        return (MemorySegment) SWIZZLE.invokeExact(allocator, id, ObjC.sel("swizzle"));
    }

    @SneakyThrows
    public void setSwizzle(MemorySegment swizzle) {
        SET_SWIZZLE.invokeExact(id, ObjC.sel("setSwizzle:"), swizzle);
    }
}
