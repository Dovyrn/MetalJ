package dev.dov.metalj.rate;

import dev.dov.metalj.objc.NSNumber;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRasterizationRateSampleArray extends NSObject {
    private static final long OBJECT_AT_INDEXED_SUBSCRIPT = ObjC.sel("objectAtIndexedSubscript:");
    private static final long SET_OBJECT_AT_INDEXED_SUBSCRIPT = ObjC.sel("setObject:atIndexedSubscript:");

    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);

    private MTLRasterizationRateSampleArray(long id) {
        super(id);
    }

    public static MTLRasterizationRateSampleArray of(long id) {
        return new MTLRasterizationRateSampleArray(id);
    }

    @SneakyThrows
    public NSNumber objectAtIndexedSubscript(long index) {
        return NSNumber.of((long) P_L.invokeExact(id, OBJECT_AT_INDEXED_SUBSCRIPT, index));
    }

    @SneakyThrows
    public void setObjectAtIndexedSubscript(NSNumber value, long index) {
        PL.invokeExact(id, SET_OBJECT_AT_INDEXED_SUBSCRIPT, value.getId(), index);
    }
}
