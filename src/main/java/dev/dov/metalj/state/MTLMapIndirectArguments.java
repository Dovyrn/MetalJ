package dev.dov.metalj.state;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLMapIndirectArguments {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.INT.withName("regionOriginX"),
            ObjC.INT.withName("regionOriginY"),
            ObjC.INT.withName("regionOriginZ"),
            ObjC.INT.withName("regionSizeWidth"),
            ObjC.INT.withName("regionSizeHeight"),
            ObjC.INT.withName("regionSizeDepth"),
            ObjC.INT.withName("mipMapLevel"),
            ObjC.INT.withName("sliceId"));

    public void set(MemorySegment out, long index, int x, int y, int z, int width, int height, int depth, int level,
            int slice) {
        long base = index * LAYOUT.byteSize();
        out.set(ObjC.INT, base, x);
        out.set(ObjC.INT, base + 4, y);
        out.set(ObjC.INT, base + 8, z);
        out.set(ObjC.INT, base + 12, width);
        out.set(ObjC.INT, base + 16, height);
        out.set(ObjC.INT, base + 20, depth);
        out.set(ObjC.INT, base + 24, level);
        out.set(ObjC.INT, base + 28, slice);
    }
}
