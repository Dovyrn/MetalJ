package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLPackedFloat3 {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.FLOAT.withName("x"),
            ObjC.FLOAT.withName("y"),
            ObjC.FLOAT.withName("z"));

    public MemorySegment of(Arena arena, float x, float y, float z) {
        var out = arena.allocate(LAYOUT);
        set(out, 0, x, y, z);
        return out;
    }

    public void set(MemorySegment out, long offset, float x, float y, float z) {
        out.set(ObjC.FLOAT, offset, x);
        out.set(ObjC.FLOAT, offset + 4, y);
        out.set(ObjC.FLOAT, offset + 8, z);
    }
}
