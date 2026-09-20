package dev.dov.metalj.metal4.counters;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTL4TimestampHeapEntry {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(ObjC.LONG.withName("timestamp"));

    public long timestamp(MemorySegment entries, long index) {
        return entries.getAtIndex(ObjC.LONG, index);
    }
}
