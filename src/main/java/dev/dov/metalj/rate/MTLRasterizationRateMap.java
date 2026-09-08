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
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    public long layerCount() {
        return sendLong(id, "layerCount");
    }

    @SneakyThrows
    public MemorySegment screenSize(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, ObjC.sel("screenSize"));
    }

    @SneakyThrows
    public MemorySegment physicalGranularity(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, ObjC.sel("physicalGranularity"));
    }

    @SneakyThrows
    public MemorySegment physicalSizeForLayer(SegmentAllocator allocator, long layer) {
        return (MemorySegment) SIZE_L.invokeExact(allocator, id, ObjC.sel("physicalSizeForLayer:"), layer);
    }

    @SneakyThrows
    public MemorySegment parameterBufferSizeAndAlign(SegmentAllocator allocator) {
        return (MemorySegment) ALIGN.invokeExact(allocator, id, ObjC.sel("parameterBufferSizeAndAlign"));
    }

    @SneakyThrows
    public void copyParameterDataToBuffer(MTLBuffer buffer, long offset) {
        PL.invokeExact(id, ObjC.sel("copyParameterDataToBuffer:offset:"), buffer.getId(), offset);
    }

    @SneakyThrows
    public MemorySegment mapScreenToPhysicalCoordinates(SegmentAllocator allocator, MemorySegment screen, long layer) {
        return (MemorySegment) COORD.invokeExact(allocator, id,
                ObjC.sel("mapScreenToPhysicalCoordinates:forLayer:"), screen, layer);
    }

    @SneakyThrows
    public MemorySegment mapPhysicalToScreenCoordinates(SegmentAllocator allocator, MemorySegment physical,
            long layer) {
        return (MemorySegment) COORD.invokeExact(allocator, id,
                ObjC.sel("mapPhysicalToScreenCoordinates:forLayer:"), physical, layer);
    }
}
