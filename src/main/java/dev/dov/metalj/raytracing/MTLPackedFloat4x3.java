package dev.dov.metalj.raytracing;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLPackedFloat4x3 {
    public final MemoryLayout LAYOUT = MemoryLayout.sequenceLayout(4, MTLPackedFloat3.LAYOUT);

    public MemorySegment identity(Arena arena) {
        var out = arena.allocate(LAYOUT);
        MTLPackedFloat3.set(out, 0, 1, 0, 0);
        MTLPackedFloat3.set(out, 12, 0, 1, 0);
        MTLPackedFloat3.set(out, 24, 0, 0, 1);
        MTLPackedFloat3.set(out, 36, 0, 0, 0);
        return out;
    }

    public void setColumn(MemorySegment out, long offset, int column, float x, float y, float z) {
        MTLPackedFloat3.set(out, offset + column * 12L, x, y, z);
    }
}
