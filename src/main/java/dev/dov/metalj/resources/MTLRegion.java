package dev.dov.metalj.resources;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLRegion {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            MTLOrigin.LAYOUT.withName("origin"),
            MTLSize.LAYOUT.withName("size"));

    public MemorySegment of(Arena arena, long x, long y, long z, long width, long height, long depth) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.LONG, 0, x);
        out.set(ObjC.LONG, 8, y);
        out.set(ObjC.LONG, 16, z);
        out.set(ObjC.LONG, 24, width);
        out.set(ObjC.LONG, 32, height);
        out.set(ObjC.LONG, 40, depth);
        return out;
    }
}
