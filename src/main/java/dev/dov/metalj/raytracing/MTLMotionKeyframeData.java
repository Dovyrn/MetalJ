package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLMotionKeyframeData extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLMotionKeyframeData(long id) {
        super(id);
    }

    public static MTLMotionKeyframeData of(long id) {
        return new MTLMotionKeyframeData(id);
    }

    public static MTLMotionKeyframeData data() {
        return new MTLMotionKeyframeData(owned(() -> sendPtr(ObjC.cls("MTLMotionKeyframeData"), "data")));
    }

    public MTLBuffer buffer() {
        return MTLBuffer.of(sendPtr(id, "buffer"));
    }

    @SneakyThrows
    public void setBuffer(MTLBuffer buffer) {
        P.invokeExact(id, ObjC.sel("setBuffer:"), buffer.getId());
    }

    public long offset() {
        return sendLong(id, "offset");
    }

    @SneakyThrows
    public void setOffset(long offset) {
        L.invokeExact(id, ObjC.sel("setOffset:"), offset);
    }
}
