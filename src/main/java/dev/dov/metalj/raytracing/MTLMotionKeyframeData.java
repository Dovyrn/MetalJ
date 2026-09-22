package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLMotionKeyframeData extends NSObject {
    private static final long MTL_MOTION_KEYFRAME_DATA = ObjC.cls("MTLMotionKeyframeData");

    private static final long BUFFER = ObjC.sel("buffer");
    private static final long DATA = ObjC.sel("data");
    private static final long OFFSET = ObjC.sel("offset");
    private static final long SET_BUFFER = ObjC.sel("setBuffer:");
    private static final long SET_OFFSET = ObjC.sel("setOffset:");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLMotionKeyframeData(long id) {
        super(id);
    }

    public static MTLMotionKeyframeData of(long id) {
        return new MTLMotionKeyframeData(id);
    }

    public static MTLMotionKeyframeData data() {
        return new MTLMotionKeyframeData(owned(() -> sendPtr(MTL_MOTION_KEYFRAME_DATA, DATA)));
    }

    public MTLBuffer buffer() {
        return MTLBuffer.of(sendPtr(id, BUFFER));
    }

    @SneakyThrows
    public void setBuffer(MTLBuffer buffer) {
        P.invokeExact(id, SET_BUFFER, buffer.getId());
    }

    public long offset() {
        return sendLong(id, OFFSET);
    }

    @SneakyThrows
    public void setOffset(long offset) {
        L.invokeExact(id, SET_OFFSET, offset);
    }
}
