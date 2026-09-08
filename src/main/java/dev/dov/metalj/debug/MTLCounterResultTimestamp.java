package dev.dov.metalj.debug;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLCounterResultTimestamp {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(ObjC.LONG.withName("timestamp"));

    public long timestamp(MemorySegment data, long index) {
        return data.get(ObjC.LONG, index * LAYOUT.byteSize());
    }
}
