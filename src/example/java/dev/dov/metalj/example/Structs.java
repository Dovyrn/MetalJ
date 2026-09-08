package dev.dov.metalj.example;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLRegion;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.heaps.MTLSizeAndAlign;
import java.lang.foreign.Arena;

public class Structs {
    public static void main(String[] args) {
        var device = Metal.MTLCreateSystemDefaultDevice();
        try (var arena = Arena.ofConfined()) {
            var groups = MTLSize.of(arena, 64, 1, 1);
            var region = MTLRegion.of(arena, 0, 0, 0, 16, 16, 1);
            System.out.println("size bytes: " + MTLSize.LAYOUT.byteSize());
            System.out.println("region width: " + region.get(ObjC.LONG, 24));
            System.out.println("groups width: " + groups.get(ObjC.LONG, 0));

            var threads = device.maxThreadsPerThreadgroup(arena);
            System.out.println("max threads: " + threads.get(ObjC.LONG, 0));

            var sizeAndAlign = device.heapBufferSizeAndAlignWithLength(arena, 4096, 0);
            System.out.println("heap size: " + sizeAndAlign.get(ObjC.LONG, 0)
                    + " align: " + sizeAndAlign.get(ObjC.LONG, 8));
            System.out.println("sizeAndAlign bytes: " + MTLSizeAndAlign.LAYOUT.byteSize());
        }
    }
}
