package dev.dov.metalj.resources;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLBuffer extends MTLResource {
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
        return sendLong(id, "length");
    }

    public MemorySegment contents() {
        return segment(sendPtr(id, "contents"), length());
    }

    @SneakyThrows
    public void didModifyRange(MemorySegment range) {
        R.invokeExact(id, ObjC.sel("didModifyRange:"), range);
    }

    @SneakyThrows
    public MTLTexture newTextureWithDescriptor(MTLTextureDescriptor descriptor, long offset, long bytesPerRow) {
        return MTLTexture.of((long) P_PLL.invokeExact(id, ObjC.sel("newTextureWithDescriptor:offset:bytesPerRow:"),
                descriptor.getId(), offset, bytesPerRow));
    }

    @SneakyThrows
    public void addDebugMarker(NSString marker, MemorySegment range) {
        PR.invokeExact(id, ObjC.sel("addDebugMarker:range:"), marker.getId(), range);
    }

    public void removeAllDebugMarkers() {
        sendVoid(id, "removeAllDebugMarkers");
    }

    public long gpuAddress() {
        return sendLong(id, "gpuAddress");
    }

    public MTLBuffer remoteStorageBuffer() {
        return new MTLBuffer(sendPtr(id, "remoteStorageBuffer"));
    }

    @SneakyThrows
    public MTLBuffer newRemoteBufferViewForDevice(MTLDevice device) {
        return new MTLBuffer((long) P_P.invokeExact(id, ObjC.sel("newRemoteBufferViewForDevice:"), device.getId()));
    }
}
