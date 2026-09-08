package dev.dov.metalj.raytracing;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLAxisAlignedBoundingBox {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            MTLPackedFloat3.LAYOUT.withName("min"),
            MTLPackedFloat3.LAYOUT.withName("max"));

    public MemorySegment of(Arena arena, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        var out = arena.allocate(LAYOUT);
        MTLPackedFloat3.set(out, 0, minX, minY, minZ);
        MTLPackedFloat3.set(out, 12, maxX, maxY, maxZ);
        return out;
    }
}
