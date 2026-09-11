package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.ObjC;
import lombok.SneakyThrows;

public class MTLPrimitiveAccelerationStructureDescriptor extends MTLAccelerationStructureDescriptor {
    private MTLPrimitiveAccelerationStructureDescriptor(long id) {
        super(id);
    }

    public static MTLPrimitiveAccelerationStructureDescriptor of(long id) {
        return new MTLPrimitiveAccelerationStructureDescriptor(id);
    }

    public static MTLPrimitiveAccelerationStructureDescriptor descriptor() {
        return new MTLPrimitiveAccelerationStructureDescriptor(
                owned(() -> sendPtr(ObjC.cls("MTLPrimitiveAccelerationStructureDescriptor"), "descriptor")));
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
