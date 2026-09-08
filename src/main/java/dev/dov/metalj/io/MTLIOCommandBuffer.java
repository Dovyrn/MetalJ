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
        ALPL.invokeExact(id, ObjC.sel("loadBytes:size:sourceHandle:sourceHandleOffset:"), pointer, size,
                source.getId(), offset);
    }

    @SneakyThrows
    public void loadBuffer(MTLBuffer buffer, long offset, long size, MTLIOFileHandle source, long sourceOffset) {
        PLLPL.invokeExact(id, ObjC.sel("loadBuffer:offset:size:sourceHandle:sourceHandleOffset:"), buffer.getId(),
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
        PL.invokeExact(id, ObjC.sel("copyStatusToBuffer:offset:"), buffer.getId(), offset);
    }

    public void enqueue() {
        sendVoid(id, "enqueue");
    }

    public void commit() {
        sendVoid(id, "commit");
    }

    public void waitUntilCompleted() {
        sendVoid(id, "waitUntilCompleted");
    }

    public void tryCancel() {
        sendVoid(id, "tryCancel");
    }

    public void addBarrier() {
        sendVoid(id, "addBarrier");
    }

    @SneakyThrows
    public void addCompletedHandler(Block block) {
        P.invokeExact(id, ObjC.sel("addCompletedHandler:"), block.address());
    }

    @SneakyThrows
    public void signalEvent(MTLSharedEvent event, long value) {
        PL.invokeExact(id, ObjC.sel("signalEvent:value:"), event.getId(), value);
    }

    @SneakyThrows
    public void waitForEvent(MTLSharedEvent event, long value) {
        PL.invokeExact(id, ObjC.sel("waitForEvent:value:"), event.getId(), value);
    }

    @SneakyThrows
    public void pushDebugGroup(NSString string) {
        P.invokeExact(id, ObjC.sel("pushDebugGroup:"), string.getId());
    }

    public void popDebugGroup() {
        sendVoid(id, "popDebugGroup");
    }

    public long status() {
        return sendLong(id, "status");
    }

    public NSError error() {
        return NSError.of(sendPtr(id, "error"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }
}
