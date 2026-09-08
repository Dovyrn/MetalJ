package dev.dov.metalj.rate;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRasterizationRateLayerDescriptor extends NSObject {
    private static final MethodHandle P_S = handle(ObjC.PTR, MTLSize.LAYOUT);
    private static final MethodHandle P_SAA = handle(ObjC.PTR, MTLSize.LAYOUT, ValueLayout.ADDRESS,
            ValueLayout.ADDRESS);
    private static final MethodHandle SIZE = structHandle(MTLSize.LAYOUT);
    private static final MethodHandle ADDRESS = handle(ValueLayout.ADDRESS);

    private MTLRasterizationRateLayerDescriptor(long id) {
        super(id);
    }

    public static MTLRasterizationRateLayerDescriptor of(long id) {
        return new MTLRasterizationRateLayerDescriptor(id);
    }

    @SneakyThrows
    public static MTLRasterizationRateLayerDescriptor initWithSampleCount(MemorySegment sampleCount) {
        long id = (long) P_S.invokeExact(alloc("MTLRasterizationRateLayerDescriptor"),
                ObjC.sel("initWithSampleCount:"), sampleCount);
        return new MTLRasterizationRateLayerDescriptor(id);
    }

    @SneakyThrows
    public static MTLRasterizationRateLayerDescriptor initWithSampleCount(MemorySegment sampleCount,
            MemorySegment horizontal, MemorySegment vertical) {
        long id = (long) P_SAA.invokeExact(alloc("MTLRasterizationRateLayerDescriptor"),
                ObjC.sel("initWithSampleCount:horizontal:vertical:"), sampleCount, horizontal, vertical);
        return new MTLRasterizationRateLayerDescriptor(id);
    }

    @SneakyThrows
    public MemorySegment sampleCount(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, ObjC.sel("sampleCount"));
    }

    @SneakyThrows
    public MemorySegment maxSampleCount(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, ObjC.sel("maxSampleCount"));
    }

    public MTLRasterizationRateSampleArray horizontal() {
        return MTLRasterizationRateSampleArray.of(sendPtr(id, "horizontal"));
    }

    public MTLRasterizationRateSampleArray vertical() {
        return MTLRasterizationRateSampleArray.of(sendPtr(id, "vertical"));
    }

    @SneakyThrows
    public MemorySegment horizontalSampleStorage() {
        return (MemorySegment) ADDRESS.invokeExact(id, ObjC.sel("horizontalSampleStorage"));
    }

    @SneakyThrows
    public MemorySegment verticalSampleStorage() {
        return (MemorySegment) ADDRESS.invokeExact(id, ObjC.sel("verticalSampleStorage"));
    }
}
