package dev.dov.metalj.io;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIOCommandQueueDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLIOCommandQueueDescriptor(long id) {
        super(id);
    }

    public static MTLIOCommandQueueDescriptor of(long id) {
        return new MTLIOCommandQueueDescriptor(id);
    }

    public static MTLIOCommandQueueDescriptor new_() {
        return new MTLIOCommandQueueDescriptor(sendPtr(ObjC.cls("MTLIOCommandQueueDescriptor"), "new"));
    }

    public long maxCommandBufferCount() {
        return sendLong(id, "maxCommandBufferCount");
    }

    @SneakyThrows
    public void setMaxCommandBufferCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxCommandBufferCount:"), count);
    }

    public long priority() {
        return sendLong(id, "priority");
    }

    @SneakyThrows
    public void setPriority(long priority) {
        L.invokeExact(id, ObjC.sel("setPriority:"), priority);
    }

    public long type() {
        return sendLong(id, "type");
    }

    @SneakyThrows
    public void setType(long type) {
        L.invokeExact(id, ObjC.sel("setType:"), type);
    }

    public long maxCommandsInFlight() {
        return sendLong(id, "maxCommandsInFlight");
    }

    @SneakyThrows
    public void setMaxCommandsInFlight(long count) {
        L.invokeExact(id, ObjC.sel("setMaxCommandsInFlight:"), count);
    }

    public long scratchBufferAllocator() {
        return sendPtr(id, "scratchBufferAllocator");
    }

    @SneakyThrows
    public void setScratchBufferAllocator(long allocator) {
        P.invokeExact(id, ObjC.sel("setScratchBufferAllocator:"), allocator);
    }
}
