package dev.dov.metalj.example;

import dev.dov.metalj.io.MTLIOCompressionContext;
import dev.dov.metalj.io.MTLIOCompressionMethod;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.nio.file.Files;
import lombok.SneakyThrows;

public class Lifetimes {
    @SneakyThrows
    public static void main(String[] args) {
        var path = Files.createTempDirectory("metalj").resolve("packed.lz4");
        byte[] source = new byte[4096];
        for (int i = 0; i < source.length; i++) {
            source[i] = (byte) (i / 16);
        }
        var chunk = MTLIOCompressionContext.MTLIOCompressionContextDefaultChunkSize();
        var context = MTLIOCompressionContext.MTLIOCreateCompressionContext(path.toString(),
                MTLIOCompressionMethod.MTLIOCompressionMethodLZ4, chunk);
        try (var arena = Arena.ofConfined()) {
            var bytes = arena.allocateFrom(ObjC.BYTE, source);
            MTLIOCompressionContext.MTLIOCompressionContextAppendData(context, bytes, source.length);
            System.out.println("flush: "
                    + MTLIOCompressionContext.MTLIOFlushAndDestroyCompressionContext(context));
        }
        System.out.println("packed " + source.length + " bytes into " + Files.size(path));
    }
}
