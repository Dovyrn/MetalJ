package dev.dov.metalj.resources.heaps;

import dev.dov.metalj.resources.textures.MTLTexture;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.textures.MTLTextureDescriptor;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLHeap extends NSObject {
    private static final long CPU_CACHE_MODE = ObjC.sel("cpuCacheMode");
    private static final long CURRENT_ALLOCATED_SIZE = ObjC.sel("currentAllocatedSize");
    private static final long DEVICE = ObjC.sel("device");
    private static final long HAZARD_TRACKING_MODE = ObjC.sel("hazardTrackingMode");
    private static final long LABEL = ObjC.sel("label");
    private static final long MAX_AVAILABLE_SIZE_WITH_ALIGNMENT = ObjC.sel("maxAvailableSizeWithAlignment:");
    private static final long NEW_BUFFER_WITH_LENGTH_OPTIONS = ObjC.sel("newBufferWithLength:options:");
    private static final long NEW_BUFFER_WITH_LENGTH_OPTIONS_OFFSET = ObjC.sel("newBufferWithLength:options:offset:");
    private static final long NEW_TEXTURE_WITH_DESCRIPTOR = ObjC.sel("newTextureWithDescriptor:");
    private static final long NEW_TEXTURE_WITH_DESCRIPTOR_OFFSET = ObjC.sel("newTextureWithDescriptor:offset:");
    private static final long RESOURCE_OPTIONS = ObjC.sel("resourceOptions");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_PURGEABLE_STATE = ObjC.sel("setPurgeableState:");
    private static final long SIZE = ObjC.sel("size");
    private static final long STORAGE_MODE = ObjC.sel("storageMode");
    private static final long TYPE = ObjC.sel("type");
    private static final long USED_SIZE = ObjC.sel("usedSize");

    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle L_L = handle(ObjC.LONG, ObjC.LONG);
    private static final MethodHandle P_LL = handle(ObjC.PTR, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle P_LLL = handle(ObjC.PTR, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);
    private static final MethodHandle P_PL = handle(ObjC.PTR, ObjC.PTR, ObjC.LONG);

    private MTLHeap(long id) {
        super(id);
    }

    public static MTLHeap of(long id) {
        return new MTLHeap(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public long storageMode() {
        return sendLong(id, STORAGE_MODE);
    }

    public long cpuCacheMode() {
        return sendLong(id, CPU_CACHE_MODE);
    }

    public long hazardTrackingMode() {
        return sendLong(id, HAZARD_TRACKING_MODE);
    }

    public long resourceOptions() {
        return sendLong(id, RESOURCE_OPTIONS);
    }

    public long size() {
        return sendLong(id, SIZE);
    }

    public long usedSize() {
        return sendLong(id, USED_SIZE);
    }

    public long currentAllocatedSize() {
        return sendLong(id, CURRENT_ALLOCATED_SIZE);
    }

    @SneakyThrows
    public long maxAvailableSizeWithAlignment(long alignment) {
        return (long) L_L.invokeExact(id, MAX_AVAILABLE_SIZE_WITH_ALIGNMENT, alignment);
    }

    @SneakyThrows
    public MTLBuffer newBufferWithLength(long length, long options) {
        return MTLBuffer.of((long) P_LL.invokeExact(id, NEW_BUFFER_WITH_LENGTH_OPTIONS, length, options));
    }

    @SneakyThrows
    public MTLTexture newTextureWithDescriptor(MTLTextureDescriptor descriptor) {
        return MTLTexture.of((long) P_P.invokeExact(id, NEW_TEXTURE_WITH_DESCRIPTOR, descriptor.getId()));
    }

    @SneakyThrows
    public long setPurgeableState(long state) {
        return (long) L_L.invokeExact(id, SET_PURGEABLE_STATE, state);
    }

    public long type() {
        return sendLong(id, TYPE);
    }

    @SneakyThrows
    public MTLBuffer newBufferWithLength(long length, long options, long offset) {
        return MTLBuffer.of((long) P_LLL.invokeExact(id, NEW_BUFFER_WITH_LENGTH_OPTIONS_OFFSET, length,
                options, offset));
    }

    @SneakyThrows
    public MTLTexture newTextureWithDescriptor(MTLTextureDescriptor descriptor, long offset) {
        return MTLTexture.of((long) P_PL.invokeExact(id, NEW_TEXTURE_WITH_DESCRIPTOR_OFFSET,
                descriptor.getId(), offset));
    }
}
