package dev.dov.metalj.metal4.raytracing;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4PrimitiveAccelerationStructureDescriptor extends MTL4AccelerationStructureDescriptor {
    private MTL4PrimitiveAccelerationStructureDescriptor(long id) {
        super(id);
    }

    public static MTL4PrimitiveAccelerationStructureDescriptor of(long id) {
        return new MTL4PrimitiveAccelerationStructureDescriptor(id);
    }

    public static MTL4PrimitiveAccelerationStructureDescriptor new_() {
        return new MTL4PrimitiveAccelerationStructureDescriptor(
                sendPtr(ObjC.cls("MTL4PrimitiveAccelerationStructureDescriptor"), "new"));
    }

    public NSArray geometryDescriptors() {
        return NSArray.of(sendPtr(id, "geometryDescriptors"));
    }

    @SneakyThrows
    public void setGeometryDescriptors(NSArray descriptors) {
        P.invokeExact(id, ObjC.sel("setGeometryDescriptors:"), descriptors.getId());
    }

    public long motionStartBorderMode() {
        return sendLong(id, "motionStartBorderMode");
    }

    @SneakyThrows
    public void setMotionStartBorderMode(long mode) {
        L.invokeExact(id, ObjC.sel("setMotionStartBorderMode:"), mode);
    }

    public long motionEndBorderMode() {
        return sendLong(id, "motionEndBorderMode");
    }

    @SneakyThrows
    public void setMotionEndBorderMode(long mode) {
        L.invokeExact(id, ObjC.sel("setMotionEndBorderMode:"), mode);
    }

    public float motionStartTime() {
        return sendFloat(id, "motionStartTime");
    }

    @SneakyThrows
    public void setMotionStartTime(float time) {
        F.invokeExact(id, ObjC.sel("setMotionStartTime:"), time);
    }

    public float motionEndTime() {
        return sendFloat(id, "motionEndTime");
    }

    @SneakyThrows
    public void setMotionEndTime(float time) {
        F.invokeExact(id, ObjC.sel("setMotionEndTime:"), time);
    }

    public long motionKeyframeCount() {
        return sendLong(id, "motionKeyframeCount");
    }

    @SneakyThrows
    public void setMotionKeyframeCount(long count) {
        L.invokeExact(id, ObjC.sel("setMotionKeyframeCount:"), count);
    }
}
