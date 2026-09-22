package dev.dov.metalj.objc;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.SneakyThrows;

public class NSObject {
    private static final MethodHandle VOID = ObjC.send(ObjC.of(null));
    private static final MethodHandle PTR = ObjC.send(ObjC.of(ObjC.PTR));
    private static final MethodHandle LONG = ObjC.send(ObjC.of(ObjC.LONG));
    private static final MethodHandle BOOL = ObjC.send(ObjC.of(ObjC.BOOL));
    private static final MethodHandle FLOAT = ObjC.send(ObjC.of(ObjC.FLOAT));
    private static final long ALLOC = ObjC.sel("alloc");
    private static final long RETAIN = ObjC.sel("retain");
    private static final long RELEASE = ObjC.sel("release");
    private static final long DESCRIPTION = ObjC.sel("description");

    @Getter
    protected final long id;

    protected NSObject(long id) {
        this.id = id;
    }

    public static long alloc(String cls) {
        return sendPtr(ObjC.cls(cls), ALLOC);
    }

    public boolean isNull() {
        return id == 0;
    }

    public NSObject retain() {
        sendPtr(id, RETAIN);
        return this;
    }

    public void release() {
        sendVoid(id, RELEASE);
    }

    public String description() {
        return drained(() -> NSString.of(sendPtr(id, DESCRIPTION)).UTF8String());
    }

    public interface Call {
        long get() throws Throwable;
    }

    @SneakyThrows
    public static long owned(Call call) {
        long pool = ObjC.push();
        try {
            long created = call.get();
            if (created != 0) {
                sendPtr(created, RETAIN);
            }
            return created;
        } finally {
            ObjC.pop(pool);
        }
    }

    public static <T> T drained(Supplier<T> call) {
        long pool = ObjC.push();
        try {
            return call.get();
        } finally {
            ObjC.pop(pool);
        }
    }

    @SneakyThrows
    public static void sendVoid(long target, long selector) {
        VOID.invokeExact(target, selector);
    }

    @SneakyThrows
    public static long sendPtr(long target, long selector) {
        return (long) PTR.invokeExact(target, selector);
    }

    @SneakyThrows
    public static long sendLong(long target, long selector) {
        return (long) LONG.invokeExact(target, selector);
    }

    @SneakyThrows
    public static float sendFloat(long target, long selector) {
        return (float) FLOAT.invokeExact(target, selector);
    }

    @SneakyThrows
    public static boolean sendBool(long target, long selector) {
        return (boolean) BOOL.invokeExact(target, selector);
    }

    public static MethodHandle handle(MemoryLayout result, MemoryLayout... args) {
        return ObjC.send(ObjC.of(result, args));
    }

    public static MethodHandle structHandle(MemoryLayout result, MemoryLayout... args) {
        return ObjC.sendStruct(ObjC.of(result, args));
    }

    public static MemorySegment segment(long address, long bytes) {
        return MemorySegment.ofAddress(address).reinterpret(bytes);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof NSObject o && o.id == id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public String toString() {
        return isNull() ? "nil" : description();
    }
}
