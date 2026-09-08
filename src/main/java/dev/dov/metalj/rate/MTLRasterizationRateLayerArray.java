package dev.dov.metalj.rate;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRasterizationRateLayerArray extends NSObject {
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLRasterizationRateLayerArray(long id) {
        super(id);
    }

    public static MTLRasterizationRateLayerArray of(long id) {
        return new MTLRasterizationRateLayerArray(id);
    }

    @SneakyThrows
    public MTLRasterizationRateLayerDescriptor objectAtIndexedSubscript(long index) {
        return MTLRasterizationRateLayerDescriptor.of(
                (long) P_L.invokeExact(id, ObjC.sel("objectAtIndexedSubscript:"), index));
    }

    @SneakyThrows
    public void setObjectAtIndexedSubscript(MTLRasterizationRateLayerDescriptor layer, long index) {
        PL.invokeExact(id, ObjC.sel("setObject:atIndexedSubscript:"), layer.getId(), index);
    }
}
