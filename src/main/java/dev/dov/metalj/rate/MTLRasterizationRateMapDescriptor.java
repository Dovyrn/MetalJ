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
        long id = owned(() -> (long) P_S.invokeExact(ObjC.cls("MTLRasterizationRateMapDescriptor"),
                ObjC.sel("rasterizationRateMapDescriptorWithScreenSize:"), screenSize));
        return new MTLRasterizationRateMapDescriptor(id);
    }

    @SneakyThrows
    public static MTLRasterizationRateMapDescriptor rasterizationRateMapDescriptor(MemorySegment screenSize,
            MTLRasterizationRateLayerDescriptor layer) {
        long id = owned(() -> (long) P_SP.invokeExact(ObjC.cls("MTLRasterizationRateMapDescriptor"),
                ObjC.sel("rasterizationRateMapDescriptorWithScreenSize:layer:"), screenSize, layer.getId()));
        return new MTLRasterizationRateMapDescriptor(id);
    }

    @SneakyThrows
    public MTLRasterizationRateLayerDescriptor layerAtIndex(long index) {
        return MTLRasterizationRateLayerDescriptor.of((long) P_L.invokeExact(id, ObjC.sel("layerAtIndex:"), index));
    }

    @SneakyThrows
    public void setLayer(MTLRasterizationRateLayerDescriptor layer, long index) {
        PL.invokeExact(id, ObjC.sel("setLayer:atIndex:"), layer.getId(), index);
    }

    public MTLRasterizationRateLayerArray layers() {
        return MTLRasterizationRateLayerArray.of(sendPtr(id, "layers"));
    }

    public long layerCount() {
        return sendLong(id, "layerCount");
    }

    @SneakyThrows
    public MemorySegment screenSize(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, ObjC.sel("screenSize"));
    }

    @SneakyThrows
    public void setScreenSize(MemorySegment size) {
        S.invokeExact(id, ObjC.sel("setScreenSize:"), size);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }
}
