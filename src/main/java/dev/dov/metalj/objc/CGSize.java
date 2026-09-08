package dev.dov.metalj.objc;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CGSize {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.DOUBLE.withName("width"),
            ObjC.DOUBLE.withName("height"));

    public MemorySegment of(Arena arena, double width, double height) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.DOUBLE, 0, width);
        out.set(ObjC.DOUBLE, 8, height);
        return out;
    }
}
