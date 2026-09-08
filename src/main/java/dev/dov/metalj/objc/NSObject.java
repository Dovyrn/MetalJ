package dev.dov.metalj.objc;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.Getter;
import lombok.SneakyThrows;

public class NSObject {
    private static final FunctionDescriptor VOID = ObjC.of(null);
    private static final FunctionDescriptor PTR = ObjC.of(ObjC.PTR);
    private static final FunctionDescriptor LONG = ObjC.of(ObjC.LONG);
    private static final FunctionDescriptor BOOL = ObjC.of(ObjC.BOOL);
    private static final FunctionDescriptor FLOAT = ObjC.of(ObjC.FLOAT);

    @Getter
    protected final long id;

    protected NSObject(long id) {
        this.id = id;
    }

    public static long alloc(String cls) {
        return sendPtr(ObjC.cls(cls), "alloc");
    }

    public boolean isNull() {
        return id == 0;
    }

    public NSObject retain() {
        sendPtr(id, "retain");
        return this;
    }

    public void release() {
        sendVoid(id, "release");
    }

    public String description() {
        return NSString.of(sendPtr(id, "description")).UTF8String();
    }

    @SneakyThrows
    public static void sendVoid(long target, String selector) {
        ObjC.send(VOID).invokeExact(target, ObjC.sel(selector));
    }

    @SneakyThrows
    public static long sendPtr(long target, String selector) {
        return (long) ObjC.send(PTR).invokeExact(target, ObjC.sel(selector));
    }

    @SneakyThrows
    public static long sendLong(long target, String selector) {
        return (long) ObjC.send(LONG).invokeExact(target, ObjC.sel(selector));
    }

    @SneakyThrows
    public static float sendFloat(long target, String selector) {
        return (float) ObjC.send(FLOAT).invokeExact(target, ObjC.sel(selector));
    }

    @SneakyThrows
    public static boolean sendBool(long target, String selector) {
        return (boolean) ObjC.send(BOOL).invokeExact(target, ObjC.sel(selector));
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
