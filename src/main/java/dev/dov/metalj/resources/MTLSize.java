package dev.dov.metalj.resources;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLSize {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.LONG.withName("width"),
            ObjC.LONG.withName("height"),
            ObjC.LONG.withName("depth"));

    public MemorySegment of(Arena arena, long width, long height, long depth) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.LONG, 0, width);
        out.set(ObjC.LONG, 8, height);
        out.set(ObjC.LONG, 16, depth);
        return out;
    }
}
