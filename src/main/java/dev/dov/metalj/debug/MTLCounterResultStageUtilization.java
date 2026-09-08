package dev.dov.metalj.debug;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLCounterResultStageUtilization {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.LONG.withName("totalCycles"),
            ObjC.LONG.withName("vertexCycles"),
            ObjC.LONG.withName("tessellationCycles"),
            ObjC.LONG.withName("postTessellationVertexCycles"),
            ObjC.LONG.withName("fragmentCycles"),
            ObjC.LONG.withName("renderTargetCycles"));

    public long field(MemorySegment data, long index, String name) {
        var offset = LAYOUT.byteOffset(MemoryLayout.PathElement.groupElement(name));
        return data.get(ObjC.LONG, index * LAYOUT.byteSize() + offset);
    }
}
