package dev.dov.metalj.objc;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NSRange {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.LONG.withName("location"),
            ObjC.LONG.withName("length"));

    public MemorySegment of(Arena arena, long location, long length) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.LONG, 0, location);
        out.set(ObjC.LONG, 8, length);
        return out;
    }
}
