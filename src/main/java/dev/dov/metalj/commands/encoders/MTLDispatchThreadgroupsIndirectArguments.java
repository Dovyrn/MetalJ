package dev.dov.metalj.commands.encoders;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLDispatchThreadgroupsIndirectArguments {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            MemoryLayout.sequenceLayout(3, ObjC.INT).withName("threadgroupsPerGrid"));

    public MemorySegment of(Arena arena, int x, int y, int z) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.INT, 0, x);
        out.set(ObjC.INT, 4, y);
        out.set(ObjC.INT, 8, z);
        return out;
    }
}
