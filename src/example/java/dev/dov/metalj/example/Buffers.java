package dev.dov.metalj.example;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLResourceOptions;
import java.lang.foreign.Arena;

public class Buffers {
    public static void main(String[] args) {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var buffer = device.newBufferWithLength(32, MTLResourceOptions.MTLResourceStorageModeShared);
        buffer.setLabel(NSString.stringWithUTF8String("scratch"));

        var contents = buffer.contents();
        for (int i = 0; i < 8; i++) {
            contents.setAtIndex(ObjC.FLOAT, i, i * 1.5f);
        }
        System.out.println("label: " + buffer.label().UTF8String());
        System.out.println("length: " + buffer.length());
        System.out.println("read back: " + contents.getAtIndex(ObjC.FLOAT, 3));
        System.out.println("gpu address: " + Long.toHexString(buffer.gpuAddress()));

        try (var arena = Arena.ofConfined()) {
            float[] values = {1, 2, 3, 4};
            var bytes = arena.allocateFrom(ObjC.FLOAT, values);
            var copy = device.newBufferWithBytes(bytes, bytes.byteSize(),
                    MTLResourceOptions.MTLResourceStorageModeShared);
            System.out.println("copied: " + copy.contents().getAtIndex(ObjC.FLOAT, 2));
        }
    }
}
