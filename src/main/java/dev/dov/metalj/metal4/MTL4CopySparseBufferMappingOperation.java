package dev.dov.metalj.metal4;

import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTL4CopySparseBufferMappingOperation {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            NSRange.LAYOUT.withName("sourceRange"),
            ObjC.LONG.withName("destinationOffset"));

    public void set(MemorySegment operations, long index, long location, long length, long destinationOffset) {
        var out = operations.asSlice(index * LAYOUT.byteSize(), LAYOUT.byteSize());
        out.set(ObjC.LONG, 0, location);
        out.set(ObjC.LONG, 8, length);
        out.set(ObjC.LONG, 16, destinationOffset);
    }
}
