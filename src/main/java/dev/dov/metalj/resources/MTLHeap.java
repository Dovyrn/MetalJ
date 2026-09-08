package dev.dov.metalj.resources;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLHeap extends NSObject {
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
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public long storageMode() {
        return sendLong(id, "storageMode");
    }

    public long cpuCacheMode() {
        return sendLong(id, "cpuCacheMode");
    }

    public long hazardTrackingMode() {
        return sendLong(id, "hazardTrackingMode");
    }

    public long resourceOptions() {
        return sendLong(id, "resourceOptions");
    }

    public long size() {
        return sendLong(id, "size");
    }

    public long usedSize() {
        return sendLong(id, "usedSize");
    }

    public long currentAllocatedSize() {
        return sendLong(id, "currentAllocatedSize");
    }

    @SneakyThrows
    public long maxAvailableSizeWithAlignment(long alignment) {
        return (long) L_L.invokeExact(id, ObjC.sel("maxAvailableSizeWithAlignment:"), alignment);
    }

    @SneakyThrows
    public MTLBuffer newBufferWithLength(long length, long options) {
        return MTLBuffer.of((long) P_LL.invokeExact(id, ObjC.sel("newBufferWithLength:options:"), length, options));
    }

    @SneakyThrows
    public MTLTexture newTextureWithDescriptor(MTLTextureDescriptor descriptor) {
        return MTLTexture.of((long) P_P.invokeExact(id, ObjC.sel("newTextureWithDescriptor:"), descriptor.getId()));
    }

    @SneakyThrows
    public long setPurgeableState(long state) {
        return (long) L_L.invokeExact(id, ObjC.sel("setPurgeableState:"), state);
    }

    public long type() {
        return sendLong(id, "type");
    }

    @SneakyThrows
    public MTLBuffer newBufferWithLength(long length, long options, long offset) {
        return MTLBuffer.of((long) P_LLL.invokeExact(id, ObjC.sel("newBufferWithLength:options:offset:"), length,
                options, offset));
    }

    @SneakyThrows
    public MTLTexture newTextureWithDescriptor(MTLTextureDescriptor descriptor, long offset) {
        return MTLTexture.of((long) P_PL.invokeExact(id, ObjC.sel("newTextureWithDescriptor:offset:"),
                descriptor.getId(), offset));
    }
}
