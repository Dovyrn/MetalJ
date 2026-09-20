package dev.dov.metalj.metal4;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLRegion;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTL4UpdateSparseTextureMappingOperation {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.LONG.withName("mode"),
            MTLRegion.LAYOUT.withName("textureRegion"),
            ObjC.LONG.withName("textureLevel"),
            ObjC.LONG.withName("textureSlice"),
            ObjC.LONG.withName("heapOffset"));

    public void set(MemorySegment operations, long index, long mode, MemorySegment region, long level, long slice,
            long heapOffset) {
        var out = operations.asSlice(index * LAYOUT.byteSize(), LAYOUT.byteSize());
        out.set(ObjC.LONG, 0, mode);
        MemorySegment.copy(region, 0, out, 8, MTLRegion.LAYOUT.byteSize());
        out.set(ObjC.LONG, 56, level);
        out.set(ObjC.LONG, 64, slice);
        out.set(ObjC.LONG, 72, heapOffset);
    }
}
