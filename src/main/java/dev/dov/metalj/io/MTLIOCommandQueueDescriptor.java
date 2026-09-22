package dev.dov.metalj.io;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIOCommandQueueDescriptor extends NSObject {
    private static final long MTLIO_COMMAND_QUEUE_DESCRIPTOR = ObjC.cls("MTLIOCommandQueueDescriptor");

    private static final long MAX_COMMAND_BUFFER_COUNT = ObjC.sel("maxCommandBufferCount");
    private static final long MAX_COMMANDS_IN_FLIGHT = ObjC.sel("maxCommandsInFlight");
    private static final long NEW = ObjC.sel("new");
    private static final long PRIORITY = ObjC.sel("priority");
    private static final long SCRATCH_BUFFER_ALLOCATOR = ObjC.sel("scratchBufferAllocator");
    private static final long SET_MAX_COMMAND_BUFFER_COUNT = ObjC.sel("setMaxCommandBufferCount:");
    private static final long SET_MAX_COMMANDS_IN_FLIGHT = ObjC.sel("setMaxCommandsInFlight:");
    private static final long SET_PRIORITY = ObjC.sel("setPriority:");
    private static final long SET_SCRATCH_BUFFER_ALLOCATOR = ObjC.sel("setScratchBufferAllocator:");
    private static final long SET_TYPE = ObjC.sel("setType:");
    private static final long TYPE = ObjC.sel("type");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLIOCommandQueueDescriptor(long id) {
        super(id);
    }

    public static MTLIOCommandQueueDescriptor of(long id) {
        return new MTLIOCommandQueueDescriptor(id);
    }

    public static MTLIOCommandQueueDescriptor new_() {
        return new MTLIOCommandQueueDescriptor(sendPtr(MTLIO_COMMAND_QUEUE_DESCRIPTOR, NEW));
    }

    public long maxCommandBufferCount() {
        return sendLong(id, MAX_COMMAND_BUFFER_COUNT);
    }

    @SneakyThrows
    public void setMaxCommandBufferCount(long count) {
        L.invokeExact(id, SET_MAX_COMMAND_BUFFER_COUNT, count);
    }

    public long priority() {
        return sendLong(id, PRIORITY);
    }

    @SneakyThrows
    public void setPriority(long priority) {
        L.invokeExact(id, SET_PRIORITY, priority);
    }

    public long type() {
        return sendLong(id, TYPE);
    }

    @SneakyThrows
    public void setType(long type) {
        L.invokeExact(id, SET_TYPE, type);
    }

    public long maxCommandsInFlight() {
        return sendLong(id, MAX_COMMANDS_IN_FLIGHT);
    }

    @SneakyThrows
    public void setMaxCommandsInFlight(long count) {
        L.invokeExact(id, SET_MAX_COMMANDS_IN_FLIGHT, count);
    }

    public long scratchBufferAllocator() {
        return sendPtr(id, SCRATCH_BUFFER_ALLOCATOR);
    }

    @SneakyThrows
    public void setScratchBufferAllocator(long allocator) {
        P.invokeExact(id, SET_SCRATCH_BUFFER_ALLOCATOR, allocator);
    }
}
