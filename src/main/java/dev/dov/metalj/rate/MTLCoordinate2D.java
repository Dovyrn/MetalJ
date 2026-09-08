package dev.dov.metalj.rate;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLCoordinate2D {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.FLOAT.withName("x"),
            ObjC.FLOAT.withName("y"));

    public MemorySegment of(Arena arena, float x, float y) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.FLOAT, 0, x);
        out.set(ObjC.FLOAT, 4, y);
        return out;
    }

    public float x(MemorySegment coordinate) {
        return coordinate.get(ObjC.FLOAT, 0);
    }

    public float y(MemorySegment coordinate) {
        return coordinate.get(ObjC.FLOAT, 4);
    }
}
