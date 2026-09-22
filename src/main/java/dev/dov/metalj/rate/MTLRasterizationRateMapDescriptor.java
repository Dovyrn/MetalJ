package dev.dov.metalj.rate;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRasterizationRateMapDescriptor extends NSObject {
    private static final long MTL_RASTERIZATION_RATE_MAP_DESCRIPTOR = ObjC.cls("MTLRasterizationRateMapDescriptor");

    private static final long LABEL = ObjC.sel("label");
    private static final long LAYER_AT_INDEX = ObjC.sel("layerAtIndex:");
    private static final long LAYER_COUNT = ObjC.sel("layerCount");
    private static final long LAYERS = ObjC.sel("layers");
    private static final long RASTERIZATION_RATE_MAP_DESCRIPTOR_WITH_SCREEN_SIZE = ObjC.sel("rasterizationRateMapDescriptorWithScreenSize:");
    private static final long RASTERIZATION_RATE_MAP_DESCRIPTOR_WITH_SCREEN_SIZE_LAYER = ObjC.sel("rasterizationRateMapDescriptorWithScreenSize:layer:");
    private static final long SCREEN_SIZE = ObjC.sel("screenSize");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_LAYER_AT_INDEX = ObjC.sel("setLayer:atIndex:");
    private static final long SET_SCREEN_SIZE = ObjC.sel("setScreenSize:");

    private static final MethodHandle P_S = handle(ObjC.PTR, MTLSize.LAYOUT);
    private static final MethodHandle P_SP = handle(ObjC.PTR, MTLSize.LAYOUT, ObjC.PTR);
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle S = handle(null, MTLSize.LAYOUT);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle SIZE = structHandle(MTLSize.LAYOUT);

    private MTLRasterizationRateMapDescriptor(long id) {
        super(id);
    }

    public static MTLRasterizationRateMapDescriptor of(long id) {
        return new MTLRasterizationRateMapDescriptor(id);
    }

    @SneakyThrows
    public static MTLRasterizationRateMapDescriptor rasterizationRateMapDescriptor(MemorySegment screenSize) {
        long id = owned(() -> (long) P_S.invokeExact(MTL_RASTERIZATION_RATE_MAP_DESCRIPTOR,
                RASTERIZATION_RATE_MAP_DESCRIPTOR_WITH_SCREEN_SIZE, screenSize));
        return new MTLRasterizationRateMapDescriptor(id);
    }

    @SneakyThrows
    public static MTLRasterizationRateMapDescriptor rasterizationRateMapDescriptor(MemorySegment screenSize,
            MTLRasterizationRateLayerDescriptor layer) {
        long id = owned(() -> (long) P_SP.invokeExact(MTL_RASTERIZATION_RATE_MAP_DESCRIPTOR,
                RASTERIZATION_RATE_MAP_DESCRIPTOR_WITH_SCREEN_SIZE_LAYER, screenSize, layer.getId()));
        return new MTLRasterizationRateMapDescriptor(id);
    }

    @SneakyThrows
    public MTLRasterizationRateLayerDescriptor layerAtIndex(long index) {
        return MTLRasterizationRateLayerDescriptor.of((long) P_L.invokeExact(id, LAYER_AT_INDEX, index));
    }

    @SneakyThrows
    public void setLayer(MTLRasterizationRateLayerDescriptor layer, long index) {
        PL.invokeExact(id, SET_LAYER_AT_INDEX, layer.getId(), index);
    }

    public MTLRasterizationRateLayerArray layers() {
        return MTLRasterizationRateLayerArray.of(sendPtr(id, LAYERS));
    }

    public long layerCount() {
        return sendLong(id, LAYER_COUNT);
    }

    @SneakyThrows
    public MemorySegment screenSize(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, SCREEN_SIZE);
    }

    @SneakyThrows
    public void setScreenSize(MemorySegment size) {
        S.invokeExact(id, SET_SCREEN_SIZE, size);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }
}
