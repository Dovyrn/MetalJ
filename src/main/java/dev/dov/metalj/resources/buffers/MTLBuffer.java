package dev.dov.metalj.resources.buffers;

import dev.dov.metalj.resources.MTLResource;
import dev.dov.metalj.resources.textures.MTLTexture;
import dev.dov.metalj.resources.textures.MTLTextureDescriptor;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.NSError;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.tensors.MTLTensor;
import dev.dov.metalj.tensors.MTLTensorDescriptor;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLBuffer extends MTLResource {
    private static final long ADD_DEBUG_MARKER_RANGE = ObjC.sel("addDebugMarker:range:");
    private static final long CONTENTS = ObjC.sel("contents");
    private static final long DID_MODIFY_RANGE = ObjC.sel("didModifyRange:");
    private static final long GPU_ADDRESS = ObjC.sel("gpuAddress");
    private static final long LENGTH = ObjC.sel("length");
    private static final long NEW_REMOTE_BUFFER_VIEW_FOR_DEVICE = ObjC.sel("newRemoteBufferViewForDevice:");
    private static final long NEW_TENSOR_WITH_DESCRIPTOR_OFFSET_ERROR = ObjC.sel("newTensorWithDescriptor:offset:error:");
    private static final long NEW_TEXTURE_WITH_DESCRIPTOR_OFFSET_BYTES_PER_ROW = ObjC.sel("newTextureWithDescriptor:offset:bytesPerRow:");
    private static final long REMOTE_STORAGE_BUFFER = ObjC.sel("remoteStorageBuffer");
    private static final long REMOVE_ALL_DEBUG_MARKERS = ObjC.sel("removeAllDebugMarkers");

    private static final MethodHandle P_PLA = handle(ObjC.PTR, ObjC.PTR, ObjC.LONG, ValueLayout.ADDRESS);
    private static final MethodHandle R = handle(null, NSRange.LAYOUT);
    private static final MethodHandle PR = handle(null, ObjC.PTR, NSRange.LAYOUT);
    private static final MethodHandle P_PLL = handle(ObjC.PTR, ObjC.PTR, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);

    private MTLBuffer(long id) {
        super(id);
    }

    public static MTLBuffer of(long id) {
        return new MTLBuffer(id);
    }

    public long length() {
        return sendLong(id, LENGTH);
    }

    public MemorySegment contents() {
        return segment(sendPtr(id, CONTENTS), length());
    }

    @SneakyThrows
    public void didModifyRange(MemorySegment range) {
        R.invokeExact(id, DID_MODIFY_RANGE, range);
    }

    @SneakyThrows
    public MTLTexture newTextureWithDescriptor(MTLTextureDescriptor descriptor, long offset, long bytesPerRow) {
        return MTLTexture.of((long) P_PLL.invokeExact(id, NEW_TEXTURE_WITH_DESCRIPTOR_OFFSET_BYTES_PER_ROW,
                descriptor.getId(), offset, bytesPerRow));
    }

    @SneakyThrows
    public void addDebugMarker(NSString marker, MemorySegment range) {
        PR.invokeExact(id, ADD_DEBUG_MARKER_RANGE, marker.getId(), range);
    }

    public void removeAllDebugMarkers() {
        sendVoid(id, REMOVE_ALL_DEBUG_MARKERS);
    }

    public long gpuAddress() {
        return sendLong(id, GPU_ADDRESS);
    }

    public MTLBuffer remoteStorageBuffer() {
        return new MTLBuffer(sendPtr(id, REMOTE_STORAGE_BUFFER));
    }

    @SneakyThrows
    public MTLBuffer newRemoteBufferViewForDevice(MTLDevice device) {
        return new MTLBuffer((long) P_P.invokeExact(id, NEW_REMOTE_BUFFER_VIEW_FOR_DEVICE, device.getId()));
    }

    @SneakyThrows
    public MTLTensor newTensorWithDescriptor(MTLTensorDescriptor descriptor, long offset) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long tensor = (long) P_PLA.invokeExact(id, NEW_TENSOR_WITH_DESCRIPTOR_OFFSET_ERROR,
                    descriptor.getId(), offset, error);
            NSError.check(error, "newTensorWithDescriptor:offset:error:");
            return MTLTensor.of(tensor);
        }
    }
}
