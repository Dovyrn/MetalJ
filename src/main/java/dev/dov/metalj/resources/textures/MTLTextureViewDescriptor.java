package dev.dov.metalj.resources.textures;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTextureViewDescriptor extends NSObject {
    private static final long MTL_TEXTURE_VIEW_DESCRIPTOR = ObjC.cls("MTLTextureViewDescriptor");

    private static final long LEVEL_RANGE = ObjC.sel("levelRange");
    private static final long NEW = ObjC.sel("new");
    private static final long PIXEL_FORMAT = ObjC.sel("pixelFormat");
    private static final long SET_LEVEL_RANGE = ObjC.sel("setLevelRange:");
    private static final long SET_PIXEL_FORMAT = ObjC.sel("setPixelFormat:");
    private static final long SET_SLICE_RANGE = ObjC.sel("setSliceRange:");
    private static final long SETSWIZZLE = ObjC.sel("setSwizzle:");
    private static final long SET_TEXTURE_TYPE = ObjC.sel("setTextureType:");
    private static final long SLICE_RANGE = ObjC.sel("sliceRange");
    private static final long SWIZZLE_SEL = ObjC.sel("swizzle");
    private static final long TEXTURE_TYPE = ObjC.sel("textureType");

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
        return new MTLTextureViewDescriptor(sendPtr(MTL_TEXTURE_VIEW_DESCRIPTOR, NEW));
    }

    public long pixelFormat() {
        return sendLong(id, PIXEL_FORMAT);
    }

    @SneakyThrows
    public void setPixelFormat(long format) {
        L.invokeExact(id, SET_PIXEL_FORMAT, format);
    }

    public long textureType() {
        return sendLong(id, TEXTURE_TYPE);
    }

    @SneakyThrows
    public void setTextureType(long type) {
        L.invokeExact(id, SET_TEXTURE_TYPE, type);
    }

    @SneakyThrows
    public MemorySegment levelRange(SegmentAllocator allocator) {
        return (MemorySegment) RANGE.invokeExact(allocator, id, LEVEL_RANGE);
    }

    @SneakyThrows
    public void setLevelRange(MemorySegment range) {
        R.invokeExact(id, SET_LEVEL_RANGE, range);
    }

    @SneakyThrows
    public MemorySegment sliceRange(SegmentAllocator allocator) {
        return (MemorySegment) RANGE.invokeExact(allocator, id, SLICE_RANGE);
    }

    @SneakyThrows
    public void setSliceRange(MemorySegment range) {
        R.invokeExact(id, SET_SLICE_RANGE, range);
    }

    @SneakyThrows
    public MemorySegment swizzle(SegmentAllocator allocator) {
        return (MemorySegment) SWIZZLE.invokeExact(allocator, id, SWIZZLE_SEL);
    }

    @SneakyThrows
    public void setSwizzle(MemorySegment swizzle) {
        SET_SWIZZLE.invokeExact(id, SETSWIZZLE, swizzle);
    }
}
