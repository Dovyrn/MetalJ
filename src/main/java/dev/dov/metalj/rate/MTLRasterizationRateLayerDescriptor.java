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
    private static final long HORIZONTAL = ObjC.sel("horizontal");
    private static final long HORIZONTAL_SAMPLE_STORAGE = ObjC.sel("horizontalSampleStorage");
    private static final long INIT_WITH_SAMPLE_COUNT = ObjC.sel("initWithSampleCount:");
    private static final long INIT_WITH_SAMPLE_COUNT_HORIZONTAL_VERTICAL = ObjC.sel("initWithSampleCount:horizontal:vertical:");
    private static final long MAX_SAMPLE_COUNT = ObjC.sel("maxSampleCount");
    private static final long SAMPLE_COUNT = ObjC.sel("sampleCount");
    private static final long VERTICAL = ObjC.sel("vertical");
    private static final long VERTICAL_SAMPLE_STORAGE = ObjC.sel("verticalSampleStorage");

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
                INIT_WITH_SAMPLE_COUNT, sampleCount);
        return new MTLRasterizationRateLayerDescriptor(id);
    }

    @SneakyThrows
    public static MTLRasterizationRateLayerDescriptor initWithSampleCount(MemorySegment sampleCount,
            MemorySegment horizontal, MemorySegment vertical) {
        long id = (long) P_SAA.invokeExact(alloc("MTLRasterizationRateLayerDescriptor"),
                INIT_WITH_SAMPLE_COUNT_HORIZONTAL_VERTICAL, sampleCount, horizontal, vertical);
        return new MTLRasterizationRateLayerDescriptor(id);
    }

    @SneakyThrows
    public MemorySegment sampleCount(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, SAMPLE_COUNT);
    }

    @SneakyThrows
    public MemorySegment maxSampleCount(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, MAX_SAMPLE_COUNT);
    }

    public MTLRasterizationRateSampleArray horizontal() {
        return MTLRasterizationRateSampleArray.of(sendPtr(id, HORIZONTAL));
    }

    public MTLRasterizationRateSampleArray vertical() {
        return MTLRasterizationRateSampleArray.of(sendPtr(id, VERTICAL));
    }

    @SneakyThrows
    public MemorySegment horizontalSampleStorage() {
        return (MemorySegment) ADDRESS.invokeExact(id, HORIZONTAL_SAMPLE_STORAGE);
    }

    @SneakyThrows
    public MemorySegment verticalSampleStorage() {
        return (MemorySegment) ADDRESS.invokeExact(id, VERTICAL_SAMPLE_STORAGE);
    }
}
