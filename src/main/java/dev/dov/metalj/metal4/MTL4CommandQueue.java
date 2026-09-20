package dev.dov.metalj.metal4;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.device.MTLDrawable;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.residency.MTLResidencySet;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.heaps.MTLHeap;
import dev.dov.metalj.resources.textures.MTLTexture;
import dev.dov.metalj.sync.MTLEvent;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CommandQueue extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle AL = handle(null, ValueLayout.ADDRESS, ObjC.LONG);
    private static final MethodHandle ALP = handle(null, ValueLayout.ADDRESS, ObjC.LONG, ObjC.PTR);
    private static final MethodHandle PPAL = handle(null, ObjC.PTR, ObjC.PTR, ValueLayout.ADDRESS, ObjC.LONG);

    private MTL4CommandQueue(long id) {
        super(id);
    }

    public static MTL4CommandQueue of(long id) {
        return new MTL4CommandQueue(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void commit(MemorySegment buffers, long count) {
        AL.invokeExact(id, ObjC.sel("commit:count:"), buffers, count);
    }

    @SneakyThrows
    public void commit(MemorySegment buffers, long count, MTL4CommitOptions options) {
        ALP.invokeExact(id, ObjC.sel("commit:count:options:"), buffers, count, options.getId());
    }

    @SneakyThrows
    public void signalEvent(MTLEvent event, long value) {
        PL.invokeExact(id, ObjC.sel("signalEvent:value:"), event.getId(), value);
    }

    @SneakyThrows
    public void waitForEvent(MTLEvent event, long value) {
        PL.invokeExact(id, ObjC.sel("waitForEvent:value:"), event.getId(), value);
    }

    @SneakyThrows
    public void signalDrawable(MTLDrawable drawable) {
        P.invokeExact(id, ObjC.sel("signalDrawable:"), drawable.getId());
    }

    @SneakyThrows
    public void waitForDrawable(MTLDrawable drawable) {
        P.invokeExact(id, ObjC.sel("waitForDrawable:"), drawable.getId());
    }

    @SneakyThrows
    public void addResidencySet(MTLResidencySet set) {
        P.invokeExact(id, ObjC.sel("addResidencySet:"), set.getId());
    }

    @SneakyThrows
    public void addResidencySets(MemorySegment sets, long count) {
        AL.invokeExact(id, ObjC.sel("addResidencySets:count:"), sets, count);
    }

    @SneakyThrows
    public void removeResidencySet(MTLResidencySet set) {
        P.invokeExact(id, ObjC.sel("removeResidencySet:"), set.getId());
    }

    @SneakyThrows
    public void removeResidencySets(MemorySegment sets, long count) {
        AL.invokeExact(id, ObjC.sel("removeResidencySets:count:"), sets, count);
    }

    @SneakyThrows
    public void updateTextureMappings(MTLTexture texture, MTLHeap heap, MemorySegment operations, long count) {
        PPAL.invokeExact(id, ObjC.sel("updateTextureMappings:heap:operations:count:"), texture.getId(), heap.getId(),
                operations, count);
    }

    @SneakyThrows
    public void copyTextureMappingsFromTexture(MTLTexture source, MTLTexture destination, MemorySegment operations,
            long count) {
        PPAL.invokeExact(id, ObjC.sel("copyTextureMappingsFromTexture:toTexture:operations:count:"), source.getId(),
                destination.getId(), operations, count);
    }

    @SneakyThrows
    public void updateBufferMappings(MTLBuffer buffer, MTLHeap heap, MemorySegment operations, long count) {
        PPAL.invokeExact(id, ObjC.sel("updateBufferMappings:heap:operations:count:"), buffer.getId(), heap.getId(),
                operations, count);
    }

    @SneakyThrows
    public void copyBufferMappingsFromBuffer(MTLBuffer source, MTLBuffer destination, MemorySegment operations,
            long count) {
        PPAL.invokeExact(id, ObjC.sel("copyBufferMappingsFromBuffer:toBuffer:operations:count:"), source.getId(),
                destination.getId(), operations, count);
    }
}
