package dev.dov.metalj.metal4.raytracing;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4PrimitiveAccelerationStructureDescriptor extends MTL4AccelerationStructureDescriptor {
    private static final long MTL_4_PRIMITIVE_ACCELERATION_STRUCTURE_DESCRIPTOR = ObjC.cls("MTL4PrimitiveAccelerationStructureDescriptor");

    private static final long GEOMETRY_DESCRIPTORS = ObjC.sel("geometryDescriptors");
    private static final long MOTION_END_BORDER_MODE = ObjC.sel("motionEndBorderMode");
    private static final long MOTION_END_TIME = ObjC.sel("motionEndTime");
    private static final long MOTION_KEYFRAME_COUNT = ObjC.sel("motionKeyframeCount");
    private static final long MOTION_START_BORDER_MODE = ObjC.sel("motionStartBorderMode");
    private static final long MOTION_START_TIME = ObjC.sel("motionStartTime");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_GEOMETRY_DESCRIPTORS = ObjC.sel("setGeometryDescriptors:");
    private static final long SET_MOTION_END_BORDER_MODE = ObjC.sel("setMotionEndBorderMode:");
    private static final long SET_MOTION_END_TIME = ObjC.sel("setMotionEndTime:");
    private static final long SET_MOTION_KEYFRAME_COUNT = ObjC.sel("setMotionKeyframeCount:");
    private static final long SET_MOTION_START_BORDER_MODE = ObjC.sel("setMotionStartBorderMode:");
    private static final long SET_MOTION_START_TIME = ObjC.sel("setMotionStartTime:");

    private MTL4PrimitiveAccelerationStructureDescriptor(long id) {
        super(id);
    }

    public static MTL4PrimitiveAccelerationStructureDescriptor of(long id) {
        return new MTL4PrimitiveAccelerationStructureDescriptor(id);
    }

    public static MTL4PrimitiveAccelerationStructureDescriptor new_() {
        return new MTL4PrimitiveAccelerationStructureDescriptor(
                sendPtr(MTL_4_PRIMITIVE_ACCELERATION_STRUCTURE_DESCRIPTOR, NEW));
    }

    public NSArray geometryDescriptors() {
        return NSArray.of(sendPtr(id, GEOMETRY_DESCRIPTORS));
    }

    @SneakyThrows
    public void setGeometryDescriptors(NSArray descriptors) {
        P.invokeExact(id, SET_GEOMETRY_DESCRIPTORS, descriptors.getId());
    }

    public long motionStartBorderMode() {
        return sendLong(id, MOTION_START_BORDER_MODE);
    }

    @SneakyThrows
    public void setMotionStartBorderMode(long mode) {
        L.invokeExact(id, SET_MOTION_START_BORDER_MODE, mode);
    }

    public long motionEndBorderMode() {
        return sendLong(id, MOTION_END_BORDER_MODE);
    }

    @SneakyThrows
    public void setMotionEndBorderMode(long mode) {
        L.invokeExact(id, SET_MOTION_END_BORDER_MODE, mode);
    }

    public float motionStartTime() {
        return sendFloat(id, MOTION_START_TIME);
    }

    @SneakyThrows
    public void setMotionStartTime(float time) {
        F.invokeExact(id, SET_MOTION_START_TIME, time);
    }

    public float motionEndTime() {
        return sendFloat(id, MOTION_END_TIME);
    }

    @SneakyThrows
    public void setMotionEndTime(float time) {
        F.invokeExact(id, SET_MOTION_END_TIME, time);
    }

    public long motionKeyframeCount() {
        return sendLong(id, MOTION_KEYFRAME_COUNT);
    }

    @SneakyThrows
    public void setMotionKeyframeCount(long count) {
        L.invokeExact(id, SET_MOTION_KEYFRAME_COUNT, count);
    }
}
