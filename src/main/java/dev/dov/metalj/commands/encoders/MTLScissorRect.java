package dev.dov.metalj.commands.encoders;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLScissorRect {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.LONG.withName("x"),
            ObjC.LONG.withName("y"),
            ObjC.LONG.withName("width"),
            ObjC.LONG.withName("height"));

    public MemorySegment of(Arena arena, long x, long y, long width, long height) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.LONG, 0, x);
        out.set(ObjC.LONG, 8, y);
        out.set(ObjC.LONG, 16, width);
        out.set(ObjC.LONG, 24, height);
        return out;
    }
}
