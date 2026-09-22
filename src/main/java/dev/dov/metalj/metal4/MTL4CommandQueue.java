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
    private static final long ADD_RESIDENCY_SET = ObjC.sel("addResidencySet:");
    private static final long ADD_RESIDENCY_SETS_COUNT = ObjC.sel("addResidencySets:count:");
    private static final long COMMIT_COUNT = ObjC.sel("commit:count:");
    private static final long COMMIT_COUNT_OPTIONS = ObjC.sel("commit:count:options:");
    private static final long COPY_BUFFER_MAPPINGS_FROM_BUFFER_TO_BUFFER_OPERATIONS_COUNT = ObjC.sel("copyBufferMappingsFromBuffer:toBuffer:operations:count:");
    private static final long COPY_TEXTURE_MAPPINGS_FROM_TEXTURE_TO_TEXTURE_OPERATIONS_COUNT = ObjC.sel("copyTextureMappingsFromTexture:toTexture:operations:count:");
    private static final long DEVICE = ObjC.sel("device");
    private static final long LABEL = ObjC.sel("label");
    private static final long REMOVE_RESIDENCY_SET = ObjC.sel("removeResidencySet:");
    private static final long REMOVE_RESIDENCY_SETS_COUNT = ObjC.sel("removeResidencySets:count:");
    private static final long SIGNAL_DRAWABLE = ObjC.sel("signalDrawable:");
    private static final long SIGNAL_EVENT_VALUE = ObjC.sel("signalEvent:value:");
    private static final long UPDATE_BUFFER_MAPPINGS_HEAP_OPERATIONS_COUNT = ObjC.sel("updateBufferMappings:heap:operations:count:");
    private static final long UPDATE_TEXTURE_MAPPINGS_HEAP_OPERATIONS_COUNT = ObjC.sel("updateTextureMappings:heap:operations:count:");
    private static final long WAIT_FOR_DRAWABLE = ObjC.sel("waitForDrawable:");
    private static final long WAIT_FOR_EVENT_VALUE = ObjC.sel("waitForEvent:value:");

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
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void commit(MemorySegment buffers, long count) {
        AL.invokeExact(id, COMMIT_COUNT, buffers, count);
    }

    @SneakyThrows
    public void commit(MemorySegment buffers, long count, MTL4CommitOptions options) {
        ALP.invokeExact(id, COMMIT_COUNT_OPTIONS, buffers, count, options.getId());
    }

    @SneakyThrows
    public void signalEvent(MTLEvent event, long value) {
        PL.invokeExact(id, SIGNAL_EVENT_VALUE, event.getId(), value);
    }

    @SneakyThrows
    public void waitForEvent(MTLEvent event, long value) {
        PL.invokeExact(id, WAIT_FOR_EVENT_VALUE, event.getId(), value);
    }

    @SneakyThrows
    public void signalDrawable(MTLDrawable drawable) {
        P.invokeExact(id, SIGNAL_DRAWABLE, drawable.getId());
    }

    @SneakyThrows
    public void waitForDrawable(MTLDrawable drawable) {
        P.invokeExact(id, WAIT_FOR_DRAWABLE, drawable.getId());
    }

    @SneakyThrows
    public void addResidencySet(MTLResidencySet set) {
        P.invokeExact(id, ADD_RESIDENCY_SET, set.getId());
    }

    @SneakyThrows
    public void addResidencySets(MemorySegment sets, long count) {
        AL.invokeExact(id, ADD_RESIDENCY_SETS_COUNT, sets, count);
    }

    @SneakyThrows
    public void removeResidencySet(MTLResidencySet set) {
        P.invokeExact(id, REMOVE_RESIDENCY_SET, set.getId());
    }

    @SneakyThrows
    public void removeResidencySets(MemorySegment sets, long count) {
        AL.invokeExact(id, REMOVE_RESIDENCY_SETS_COUNT, sets, count);
    }

    @SneakyThrows
    public void updateTextureMappings(MTLTexture texture, MTLHeap heap, MemorySegment operations, long count) {
        PPAL.invokeExact(id, UPDATE_TEXTURE_MAPPINGS_HEAP_OPERATIONS_COUNT, texture.getId(), heap.getId(),
                operations, count);
    }

    @SneakyThrows
    public void copyTextureMappingsFromTexture(MTLTexture source, MTLTexture destination, MemorySegment operations,
            long count) {
        PPAL.invokeExact(id, COPY_TEXTURE_MAPPINGS_FROM_TEXTURE_TO_TEXTURE_OPERATIONS_COUNT, source.getId(),
                destination.getId(), operations, count);
    }

    @SneakyThrows
    public void updateBufferMappings(MTLBuffer buffer, MTLHeap heap, MemorySegment operations, long count) {
        PPAL.invokeExact(id, UPDATE_BUFFER_MAPPINGS_HEAP_OPERATIONS_COUNT, buffer.getId(), heap.getId(),
                operations, count);
    }

    @SneakyThrows
    public void copyBufferMappingsFromBuffer(MTLBuffer source, MTLBuffer destination, MemorySegment operations,
            long count) {
        PPAL.invokeExact(id, COPY_BUFFER_MAPPINGS_FROM_BUFFER_TO_BUFFER_OPERATIONS_COUNT, source.getId(),
                destination.getId(), operations, count);
    }
}
