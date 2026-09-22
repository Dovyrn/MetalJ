package dev.dov.metalj.tensors;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLResource;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTensor extends MTLResource {
    private static final long BUFFER = ObjC.sel("buffer");
    private static final long BUFFER_OFFSET = ObjC.sel("bufferOffset");
    private static final long DATA_TYPE = ObjC.sel("dataType");
    private static final long DIMENSIONS = ObjC.sel("dimensions");
    private static final long GET_BYTES_STRIDES_FROM_SLICE_ORIGIN_SLICE_DIMENSIONS = ObjC.sel("getBytes:strides:fromSliceOrigin:sliceDimensions:");
    private static final long GPU_RESOURCE_ID = ObjC.sel("gpuResourceID");
    private static final long REPLACE_SLICE_ORIGIN_SLICE_DIMENSIONS_WITH_BYTES_STRIDES = ObjC.sel("replaceSliceOrigin:sliceDimensions:withBytes:strides:");
    private static final long STRIDES = ObjC.sel("strides");
    private static final long USAGE = ObjC.sel("usage");

    private static final MethodHandle REPLACE = handle(null, ObjC.PTR, ObjC.PTR, ValueLayout.ADDRESS, ObjC.PTR);
    private static final MethodHandle GET = handle(null, ValueLayout.ADDRESS, ObjC.PTR, ObjC.PTR, ObjC.PTR);

    private MTLTensor(long id) {
        super(id);
    }

    public static MTLTensor of(long id) {
        return new MTLTensor(id);
    }

    public long gpuResourceID() {
        return sendLong(id, GPU_RESOURCE_ID);
    }

    public MTLBuffer buffer() {
        return MTLBuffer.of(sendPtr(id, BUFFER));
    }

    public long bufferOffset() {
        return sendLong(id, BUFFER_OFFSET);
    }

    public MTLTensorExtents dimensions() {
        return MTLTensorExtents.of(sendPtr(id, DIMENSIONS));
    }

    public MTLTensorExtents strides() {
        return MTLTensorExtents.of(sendPtr(id, STRIDES));
    }

    public long dataType() {
        return sendLong(id, DATA_TYPE);
    }

    public long usage() {
        return sendLong(id, USAGE);
    }

    @SneakyThrows
    public void replaceSliceOrigin(MTLTensorExtents origin, MTLTensorExtents dimensions, MemorySegment bytes,
            MTLTensorExtents strides) {
        REPLACE.invokeExact(id, REPLACE_SLICE_ORIGIN_SLICE_DIMENSIONS_WITH_BYTES_STRIDES, origin.getId(),
                dimensions.getId(), bytes, strides.getId());
    }

    @SneakyThrows
    public void getBytes(MemorySegment bytes, MTLTensorExtents strides, MTLTensorExtents origin,
            MTLTensorExtents dimensions) {
        GET.invokeExact(id, GET_BYTES_STRIDES_FROM_SLICE_ORIGIN_SLICE_DIMENSIONS, bytes, strides.getId(),
                origin.getId(), dimensions.getId());
    }
}
