package dev.dov.metalj.metal4;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLOrigin;
import dev.dov.metalj.resources.MTLRegion;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTL4CopySparseTextureMappingOperation {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            MTLRegion.LAYOUT.withName("sourceRegion"),
            ObjC.LONG.withName("sourceLevel"),
            ObjC.LONG.withName("sourceSlice"),
            MTLOrigin.LAYOUT.withName("destinationOrigin"),
            ObjC.LONG.withName("destinationLevel"),
            ObjC.LONG.withName("destinationSlice"));

    public void set(MemorySegment operations, long index, MemorySegment region, long level, long slice,
            MemorySegment origin, long destinationLevel, long destinationSlice) {
        var out = operations.asSlice(index * LAYOUT.byteSize(), LAYOUT.byteSize());
        MemorySegment.copy(region, 0, out, 0, MTLRegion.LAYOUT.byteSize());
        out.set(ObjC.LONG, 48, level);
        out.set(ObjC.LONG, 56, slice);
        MemorySegment.copy(origin, 0, out, 64, MTLOrigin.LAYOUT.byteSize());
        out.set(ObjC.LONG, 88, destinationLevel);
        out.set(ObjC.LONG, 96, destinationSlice);
    }
}
