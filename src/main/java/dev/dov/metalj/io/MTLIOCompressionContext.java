package dev.dov.metalj.io;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLIOCompressionContext {
    private final MethodHandle CREATE = ObjC.function("MTLIOCreateCompressionContext",
            FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ObjC.LONG, ObjC.LONG));
    private final MethodHandle APPEND = ObjC.function("MTLIOCompressionContextAppendData",
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ObjC.LONG));
    private final MethodHandle FLUSH = ObjC.function("MTLIOFlushAndDestroyCompressionContext",
            FunctionDescriptor.of(ObjC.LONG, ValueLayout.ADDRESS));
    private final MethodHandle CHUNK = ObjC.function("MTLIOCompressionContextDefaultChunkSize",
            FunctionDescriptor.of(ObjC.LONG));

    @SneakyThrows
    public long MTLIOCompressionContextDefaultChunkSize() {
        return (long) CHUNK.invokeExact();
    }

    @SneakyThrows
    public MemorySegment MTLIOCreateCompressionContext(String path, long method, long chunkSize) {
        try (var arena = Arena.ofConfined()) {
            return (MemorySegment) CREATE.invokeExact(arena.allocateFrom(path), method, chunkSize);
        }
    }

    @SneakyThrows
    public void MTLIOCompressionContextAppendData(MemorySegment context, MemorySegment data, long size) {
        APPEND.invokeExact(context, data, size);
    }

    @SneakyThrows
    public long MTLIOFlushAndDestroyCompressionContext(MemorySegment context) {
        return (long) FLUSH.invokeExact(context);
    }
}
