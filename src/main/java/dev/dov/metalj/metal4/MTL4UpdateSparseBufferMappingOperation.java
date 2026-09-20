package dev.dov.metalj.metal4;

import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTL4UpdateSparseBufferMappingOperation {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.LONG.withName("mode"),
            NSRange.LAYOUT.withName("bufferRange"),
            ObjC.LONG.withName("heapOffset"));

    public void set(MemorySegment operations, long index, long mode, long location, long length, long heapOffset) {
        var out = operations.asSlice(index * LAYOUT.byteSize(), LAYOUT.byteSize());
        out.set(ObjC.LONG, 0, mode);
        out.set(ObjC.LONG, 8, location);
        out.set(ObjC.LONG, 16, length);
        out.set(ObjC.LONG, 24, heapOffset);
    }
}
