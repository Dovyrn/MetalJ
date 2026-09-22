package dev.dov.metalj.rate;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.heaps.MTLSizeAndAlign;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRasterizationRateMap extends NSObject {
    private static final long COPY_PARAMETER_DATA_TO_BUFFER_OFFSET = ObjC.sel("copyParameterDataToBuffer:offset:");
    private static final long DEVICE = ObjC.sel("device");
    private static final long LABEL = ObjC.sel("label");
    private static final long LAYER_COUNT = ObjC.sel("layerCount");
    private static final long MAP_PHYSICAL_TO_SCREEN_COORDINATES_FOR_LAYER = ObjC.sel("mapPhysicalToScreenCoordinates:forLayer:");
    private static final long MAP_SCREEN_TO_PHYSICAL_COORDINATES_FOR_LAYER = ObjC.sel("mapScreenToPhysicalCoordinates:forLayer:");
    private static final long PARAMETER_BUFFER_SIZE_AND_ALIGN = ObjC.sel("parameterBufferSizeAndAlign");
    private static final long PHYSICAL_GRANULARITY = ObjC.sel("physicalGranularity");
    private static final long PHYSICAL_SIZE_FOR_LAYER = ObjC.sel("physicalSizeForLayer:");
    private static final long SCREEN_SIZE = ObjC.sel("screenSize");

    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle SIZE = structHandle(MTLSize.LAYOUT);
    private static final MethodHandle SIZE_L = structHandle(MTLSize.LAYOUT, ObjC.LONG);
    private static final MethodHandle ALIGN = structHandle(MTLSizeAndAlign.LAYOUT);
    private static final MethodHandle COORD = structHandle(MTLCoordinate2D.LAYOUT, MTLCoordinate2D.LAYOUT,
            ObjC.LONG);

    private MTLRasterizationRateMap(long id) {
        super(id);
    }

    public static MTLRasterizationRateMap of(long id) {
        return new MTLRasterizationRateMap(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    public long layerCount() {
        return sendLong(id, LAYER_COUNT);
    }

    @SneakyThrows
    public MemorySegment screenSize(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, SCREEN_SIZE);
    }

    @SneakyThrows
    public MemorySegment physicalGranularity(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, PHYSICAL_GRANULARITY);
    }

    @SneakyThrows
    public MemorySegment physicalSizeForLayer(SegmentAllocator allocator, long layer) {
        return (MemorySegment) SIZE_L.invokeExact(allocator, id, PHYSICAL_SIZE_FOR_LAYER, layer);
    }

    @SneakyThrows
    public MemorySegment parameterBufferSizeAndAlign(SegmentAllocator allocator) {
        return (MemorySegment) ALIGN.invokeExact(allocator, id, PARAMETER_BUFFER_SIZE_AND_ALIGN);
    }

    @SneakyThrows
    public void copyParameterDataToBuffer(MTLBuffer buffer, long offset) {
        PL.invokeExact(id, COPY_PARAMETER_DATA_TO_BUFFER_OFFSET, buffer.getId(), offset);
    }

    @SneakyThrows
    public MemorySegment mapScreenToPhysicalCoordinates(SegmentAllocator allocator, MemorySegment screen, long layer) {
        return (MemorySegment) COORD.invokeExact(allocator, id,
                MAP_SCREEN_TO_PHYSICAL_COORDINATES_FOR_LAYER, screen, layer);
    }

    @SneakyThrows
    public MemorySegment mapPhysicalToScreenCoordinates(SegmentAllocator allocator, MemorySegment physical,
            long layer) {
        return (MemorySegment) COORD.invokeExact(allocator, id,
                MAP_PHYSICAL_TO_SCREEN_COORDINATES_FOR_LAYER, physical, layer);
    }
}
