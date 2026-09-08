package dev.dov.metalj.resources;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLOrigin {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.LONG.withName("x"),
            ObjC.LONG.withName("y"),
            ObjC.LONG.withName("z"));

    public MemorySegment of(Arena arena, long x, long y, long z) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.LONG, 0, x);
        out.set(ObjC.LONG, 8, y);
        out.set(ObjC.LONG, 16, z);
        return out;
    }
}
