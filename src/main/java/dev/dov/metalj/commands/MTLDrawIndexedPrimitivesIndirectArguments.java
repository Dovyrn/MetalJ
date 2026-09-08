package dev.dov.metalj.commands;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLDrawIndexedPrimitivesIndirectArguments {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.INT.withName("indexCount"),
            ObjC.INT.withName("instanceCount"),
            ObjC.INT.withName("indexStart"),
            ObjC.INT.withName("baseVertex"),
            ObjC.INT.withName("baseInstance"));

    public MemorySegment of(Arena arena, int indexCount, int instanceCount, int indexStart, int baseVertex,
            int baseInstance) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.INT, 0, indexCount);
        out.set(ObjC.INT, 4, instanceCount);
        out.set(ObjC.INT, 8, indexStart);
        out.set(ObjC.INT, 12, baseVertex);
        out.set(ObjC.INT, 16, baseInstance);
        return out;
    }
}
