package dev.dov.metalj.commands.encoders;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLViewport {
    public final MemoryLayout LAYOUT = MemoryLayout.structLayout(
            ObjC.DOUBLE.withName("originX"),
            ObjC.DOUBLE.withName("originY"),
            ObjC.DOUBLE.withName("width"),
            ObjC.DOUBLE.withName("height"),
            ObjC.DOUBLE.withName("znear"),
            ObjC.DOUBLE.withName("zfar"));

    public MemorySegment of(Arena arena, double originX, double originY, double width, double height,
            double znear, double zfar) {
        var out = arena.allocate(LAYOUT);
        out.set(ObjC.DOUBLE, 0, originX);
        out.set(ObjC.DOUBLE, 8, originY);
        out.set(ObjC.DOUBLE, 16, width);
        out.set(ObjC.DOUBLE, 24, height);
        out.set(ObjC.DOUBLE, 32, znear);
        out.set(ObjC.DOUBLE, 40, zfar);
        return out;
    }
}
