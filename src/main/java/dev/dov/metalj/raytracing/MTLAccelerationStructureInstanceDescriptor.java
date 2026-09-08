package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLAccelerationStructureInstanceDescriptor {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            MTLPackedFloat4x3.LAYOUT.withName("transformationMatrix"),
            ObjC.INT.withName("options"),
            ObjC.INT.withName("mask"),
            ObjC.INT.withName("intersectionFunctionTableOffset"),
            ObjC.INT.withName("accelerationStructureIndex"));

    public void set(MemorySegment out, long index, int options, int mask, int tableOffset, int structureIndex) {
        long base = index * LAYOUT.byteSize();
        out.set(ObjC.INT, base + 48, options);
        out.set(ObjC.INT, base + 52, mask);
        out.set(ObjC.INT, base + 56, tableOffset);
        out.set(ObjC.INT, base + 60, structureIndex);
    }

    public MemorySegment transform(MemorySegment out, long index) {
        return out.asSlice(index * LAYOUT.byteSize(), MTLPackedFloat4x3.LAYOUT.byteSize());
    }
}
