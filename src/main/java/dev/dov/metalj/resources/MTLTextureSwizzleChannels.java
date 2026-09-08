package dev.dov.metalj.resources;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLTextureSwizzleChannels {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ValueLayout.JAVA_BYTE.withName("red"),
            ValueLayout.JAVA_BYTE.withName("green"),
            ValueLayout.JAVA_BYTE.withName("blue"),
            ValueLayout.JAVA_BYTE.withName("alpha"));

    public MemorySegment of(Arena arena, long red, long green, long blue, long alpha) {
        var out = arena.allocate(LAYOUT);
        out.set(ValueLayout.JAVA_BYTE, 0, (byte) red);
        out.set(ValueLayout.JAVA_BYTE, 1, (byte) green);
        out.set(ValueLayout.JAVA_BYTE, 2, (byte) blue);
        out.set(ValueLayout.JAVA_BYTE, 3, (byte) alpha);
        return out;
    }
}
