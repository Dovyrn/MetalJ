package dev.dov.metalj.debug;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLCounterResultStatistic {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.LONG.withName("tessellationInputPatches"),
            ObjC.LONG.withName("vertexInvocations"),
            ObjC.LONG.withName("postTessellationVertexInvocations"),
            ObjC.LONG.withName("clipperInvocations"),
            ObjC.LONG.withName("clipperPrimitivesOut"),
            ObjC.LONG.withName("fragmentInvocations"),
            ObjC.LONG.withName("fragmentsPassed"),
            ObjC.LONG.withName("computeKernelInvocations"));

    public long field(MemorySegment data, long index, String name) {
        var offset = LAYOUT.byteOffset(MemoryLayout.PathElement.groupElement(name));
        return data.get(ObjC.LONG, index * LAYOUT.byteSize() + offset);
    }
}
