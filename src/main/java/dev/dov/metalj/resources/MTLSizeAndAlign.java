package dev.dov.metalj.resources;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLSizeAndAlign {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.LONG.withName("size"),
            ObjC.LONG.withName("align"));

    public MemorySegment of(Arena arena, long size, long align) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.LONG, 0, size);
        out.set(ObjC.LONG, 8, align);
        return out;
    }
}
