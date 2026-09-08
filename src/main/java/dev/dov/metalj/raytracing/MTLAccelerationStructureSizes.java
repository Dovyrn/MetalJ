package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLAccelerationStructureSizes {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.LONG.withName("accelerationStructureSize"),
            ObjC.LONG.withName("buildScratchBufferSize"),
            ObjC.LONG.withName("refitScratchBufferSize"));

    public long accelerationStructureSize(MemorySegment sizes) {
        return sizes.get(ObjC.LONG, 0);
    }

    public long buildScratchBufferSize(MemorySegment sizes) {
        return sizes.get(ObjC.LONG, 8);
    }

    public long refitScratchBufferSize(MemorySegment sizes) {
        return sizes.get(ObjC.LONG, 16);
    }
}
