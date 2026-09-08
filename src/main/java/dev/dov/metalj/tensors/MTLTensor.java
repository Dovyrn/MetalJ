package dev.dov.metalj.tensors;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLResource;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTensor extends MTLResource {
    private static final MethodHandle REPLACE = handle(null, ObjC.PTR, ObjC.PTR, ValueLayout.ADDRESS, ObjC.PTR);
    private static final MethodHandle GET = handle(null, ValueLayout.ADDRESS, ObjC.PTR, ObjC.PTR, ObjC.PTR);

    private MTLTensor(long id) {
        super(id);
    }

    public static MTLTensor of(long id) {
        return new MTLTensor(id);
    }

    public long gpuResourceID() {
        return sendLong(id, "gpuResourceID");
    }

    public MTLBuffer buffer() {
        return MTLBuffer.of(sendPtr(id, "buffer"));
    }

    public long bufferOffset() {
        return sendLong(id, "bufferOffset");
    }

    public MTLTensorExtents dimensions() {
        return MTLTensorExtents.of(sendPtr(id, "dimensions"));
    }

    public MTLTensorExtents strides() {
        return MTLTensorExtents.of(sendPtr(id, "strides"));
    }

    public long dataType() {
        return sendLong(id, "dataType");
    }

    public long usage() {
        return sendLong(id, "usage");
    }

    @SneakyThrows
    public void replaceSliceOrigin(MTLTensorExtents origin, MTLTensorExtents dimensions, MemorySegment bytes,
            MTLTensorExtents strides) {
        REPLACE.invokeExact(id, ObjC.sel("replaceSliceOrigin:sliceDimensions:withBytes:strides:"), origin.getId(),
                dimensions.getId(), bytes, strides.getId());
    }

    @SneakyThrows
    public void getBytes(MemorySegment bytes, MTLTensorExtents strides, MTLTensorExtents origin,
            MTLTensorExtents dimensions) {
        GET.invokeExact(id, ObjC.sel("getBytes:strides:fromSliceOrigin:sliceDimensions:"), bytes, strides.getId(),
                origin.getId(), dimensions.getId());
    }
}
