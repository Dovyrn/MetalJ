package dev.dov.metalj.tensors;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTensorExtents extends NSObject {
    private static final long EXTENT_AT_DIMENSION_INDEX = ObjC.sel("extentAtDimensionIndex:");
    private static final long INIT_WITH_RANK_VALUES = ObjC.sel("initWithRank:values:");
    private static final long RANK = ObjC.sel("rank");

    private static final MethodHandle P_LA = handle(ObjC.PTR, ObjC.LONG, ValueLayout.ADDRESS);
    private static final MethodHandle L_L = handle(ObjC.LONG, ObjC.LONG);

    private MTLTensorExtents(long id) {
        super(id);
    }

    public static MTLTensorExtents of(long id) {
        return new MTLTensorExtents(id);
    }

    @SneakyThrows
    public static MTLTensorExtents initWithRank(long... values) {
        try (var arena = Arena.ofConfined()) {
            var slots = arena.allocateFrom(ObjC.LONG, values);
            long id = (long) P_LA.invokeExact(alloc("MTLTensorExtents"), INIT_WITH_RANK_VALUES,
                    (long) values.length, slots);
            return new MTLTensorExtents(id);
        }
    }

    public long rank() {
        return sendLong(id, RANK);
    }

    @SneakyThrows
    public long extentAtDimensionIndex(long index) {
        return (long) L_L.invokeExact(id, EXTENT_AT_DIMENSION_INDEX, index);
    }
}
