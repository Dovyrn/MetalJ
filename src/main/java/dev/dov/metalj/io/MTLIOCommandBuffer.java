package dev.dov.metalj.io;

import dev.dov.metalj.objc.Block;
import dev.dov.metalj.objc.NSError;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLOrigin;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.textures.MTLTexture;
import dev.dov.metalj.sync.MTLSharedEvent;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIOCommandBuffer extends NSObject {
    private static final long ADD_BARRIER = ObjC.sel("addBarrier");
    private static final long ADD_COMPLETED_HANDLER = ObjC.sel("addCompletedHandler:");
    private static final long COMMIT = ObjC.sel("commit");
    private static final long COPY_STATUS_TO_BUFFER_OFFSET = ObjC.sel("copyStatusToBuffer:offset:");
    private static final long ENQUEUE = ObjC.sel("enqueue");
    private static final long ERROR = ObjC.sel("error");
    private static final long LABEL = ObjC.sel("label");
    private static final long LOAD_BUFFER_OFFSET_SIZE_SOURCE_HANDLE_SOURCE_HANDLE_OFFSET = ObjC.sel("loadBuffer:offset:size:sourceHandle:sourceHandleOffset:");
    private static final long LOAD_BYTES_SIZE_SOURCE_HANDLE_SOURCE_HANDLE_OFFSET = ObjC.sel("loadBytes:size:sourceHandle:sourceHandleOffset:");
    private static final long POP_DEBUG_GROUP = ObjC.sel("popDebugGroup");
    private static final long PUSH_DEBUG_GROUP = ObjC.sel("pushDebugGroup:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SIGNAL_EVENT_VALUE = ObjC.sel("signalEvent:value:");
    private static final long STATUS = ObjC.sel("status");
    private static final long TRY_CANCEL = ObjC.sel("tryCancel");
    private static final long WAIT_FOR_EVENT_VALUE = ObjC.sel("waitForEvent:value:");
    private static final long WAIT_UNTIL_COMPLETED = ObjC.sel("waitUntilCompleted");

    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle ALPL = handle(null, ValueLayout.ADDRESS, ObjC.LONG, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PLLPL = handle(null, ObjC.PTR, ObjC.LONG, ObjC.LONG, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle TEXTURE = handle(null, ObjC.PTR, ObjC.LONG, ObjC.LONG, MTLSize.LAYOUT,
            ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT, ObjC.PTR, ObjC.LONG);

    private MTLIOCommandBuffer(long id) {
        super(id);
    }

    public static MTLIOCommandBuffer of(long id) {
        return new MTLIOCommandBuffer(id);
    }

    @SneakyThrows
    public void loadBytes(MemorySegment pointer, long size, MTLIOFileHandle source, long offset) {
        ALPL.invokeExact(id, LOAD_BYTES_SIZE_SOURCE_HANDLE_SOURCE_HANDLE_OFFSET, pointer, size,
                source.getId(), offset);
    }

    @SneakyThrows
    public void loadBuffer(MTLBuffer buffer, long offset, long size, MTLIOFileHandle source, long sourceOffset) {
        PLLPL.invokeExact(id, LOAD_BUFFER_OFFSET_SIZE_SOURCE_HANDLE_SOURCE_HANDLE_OFFSET, buffer.getId(),
                offset, size, source.getId(), sourceOffset);
    }

    @SneakyThrows
    public void loadTexture(MTLTexture texture, long slice, long level, MemorySegment size, long bytesPerRow,
            long bytesPerImage, MemorySegment origin, MTLIOFileHandle source, long sourceOffset) {
        TEXTURE.invokeExact(id, ObjC.sel("loadTexture:slice:level:size:sourceBytesPerRow:sourceBytesPerImage:"
                + "destinationOrigin:sourceHandle:sourceHandleOffset:"), texture.getId(), slice, level, size,
                bytesPerRow, bytesPerImage, origin, source.getId(), sourceOffset);
    }

    @SneakyThrows
    public void copyStatusToBuffer(MTLBuffer buffer, long offset) {
        PL.invokeExact(id, COPY_STATUS_TO_BUFFER_OFFSET, buffer.getId(), offset);
    }

    public void enqueue() {
        sendVoid(id, ENQUEUE);
    }

    public void commit() {
        sendVoid(id, COMMIT);
    }

    public void waitUntilCompleted() {
        sendVoid(id, WAIT_UNTIL_COMPLETED);
    }

    public void tryCancel() {
        sendVoid(id, TRY_CANCEL);
    }

    public void addBarrier() {
        sendVoid(id, ADD_BARRIER);
    }

    @SneakyThrows
    public void addCompletedHandler(Block block) {
        P.invokeExact(id, ADD_COMPLETED_HANDLER, block.address());
    }

    @SneakyThrows
    public void signalEvent(MTLSharedEvent event, long value) {
        PL.invokeExact(id, SIGNAL_EVENT_VALUE, event.getId(), value);
    }

    @SneakyThrows
    public void waitForEvent(MTLSharedEvent event, long value) {
        PL.invokeExact(id, WAIT_FOR_EVENT_VALUE, event.getId(), value);
    }

    @SneakyThrows
    public void pushDebugGroup(NSString string) {
        P.invokeExact(id, PUSH_DEBUG_GROUP, string.getId());
    }

    public void popDebugGroup() {
        sendVoid(id, POP_DEBUG_GROUP);
    }

    public long status() {
        return sendLong(id, STATUS);
    }

    public NSError error() {
        return NSError.of(sendPtr(id, ERROR));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }
}
