package dev.dov.metalj.commands.encoders;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLDrawPrimitivesIndirectArguments {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.INT.withName("vertexCount"),
            ObjC.INT.withName("instanceCount"),
            ObjC.INT.withName("vertexStart"),
            ObjC.INT.withName("baseInstance"));

    public MemorySegment of(Arena arena, int vertexCount, int instanceCount, int vertexStart, int baseInstance) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.INT, 0, vertexCount);
        out.set(ObjC.INT, 4, instanceCount);
        out.set(ObjC.INT, 8, vertexStart);
        out.set(ObjC.INT, 12, baseInstance);
        return out;
    }
}
