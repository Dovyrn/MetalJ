package dev.dov.metalj.commands;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLClearColor {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.DOUBLE.withName("red"),
            ObjC.DOUBLE.withName("green"),
            ObjC.DOUBLE.withName("blue"),
            ObjC.DOUBLE.withName("alpha"));

    public MemorySegment of(Arena arena, double red, double green, double blue, double alpha) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.DOUBLE, 0, red);
        out.set(ObjC.DOUBLE, 8, green);
        out.set(ObjC.DOUBLE, 16, blue);
        out.set(ObjC.DOUBLE, 24, alpha);
        return out;
    }
}
