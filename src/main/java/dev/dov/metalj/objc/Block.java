package dev.dov.metalj.objc;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import lombok.SneakyThrows;

public class Block {
    private static final int GLOBAL = 1 << 28;
    private static final MemorySegment ISA = ObjC.symbol("_NSConcreteGlobalBlock");
    private static final MemoryLayout LITERAL = MemoryLayout.structLayout(
            ObjC.PTR.withName("isa"),
            ObjC.INT.withName("flags"),
            ObjC.INT.withName("reserved"),
            ObjC.PTR.withName("invoke"),
            ObjC.PTR.withName("descriptor"));
    private static final MemoryLayout DESCRIPTOR = MemoryLayout.structLayout(
            ObjC.LONG.withName("reserved"),
            ObjC.LONG.withName("size"));

    private final Arena arena = Arena.ofShared();
    private final MemorySegment literal;

    private Block(MethodHandle invoke, FunctionDescriptor descriptor) {
        var stub = ObjC.LINKER.upcallStub(invoke, descriptor, arena);
        var info = arena.allocate(DESCRIPTOR);
        info.set(ObjC.LONG, 8, LITERAL.byteSize());
        literal = arena.allocate(LITERAL);
        literal.set(ObjC.PTR, 0, ISA.address());
        literal.set(ObjC.INT, 8, GLOBAL);
        literal.set(ObjC.PTR, 16, stub.address());
        literal.set(ObjC.PTR, 24, info.address());
    }

    public static Block of(MethodHandle invoke, FunctionDescriptor descriptor) {
        return new Block(invoke, descriptor);
    }

    @SneakyThrows
    public static Block once(Runnable body) {
        var target = MethodHandles.lookup().findStatic(Block.class, "run",
                MethodType.methodType(void.class, Runnable.class, MemorySegment.class, MemorySegment.class));
        var descriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS);
        return new Block(target.bindTo(body), descriptor);
    }

    private static void run(Runnable body, MemorySegment block, MemorySegment argument) {
        body.run();
    }

    public long address() {
        return literal.address();
    }

    public void close() {
        arena.close();
    }
}
