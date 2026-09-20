package dev.dov.metalj.metal4;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTL4BufferRange {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.LONG.withName("bufferAddress"),
            ObjC.LONG.withName("length"));

    public MemorySegment of(Arena arena, long address, long length) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.LONG, 0, address);
        out.set(ObjC.LONG, 8, length);
        return out;
    }
}
