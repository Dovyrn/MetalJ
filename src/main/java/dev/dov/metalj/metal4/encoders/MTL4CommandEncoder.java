package dev.dov.metalj.metal4.encoders;

import dev.dov.metalj.metal4.MTL4CommandBuffer;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.sync.MTLFence;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CommandEncoder extends NSObject {
    private static final long BARRIER_AFTER_ENCODER_STAGES_BEFORE_ENCODER_STAGES_VISIBILITY_OPTIONS = ObjC.sel("barrierAfterEncoderStages:beforeEncoderStages:visibilityOptions:");
    private static final long BARRIER_AFTER_QUEUE_STAGES_BEFORE_STAGES_VISIBILITY_OPTIONS = ObjC.sel("barrierAfterQueueStages:beforeStages:visibilityOptions:");
    private static final long BARRIER_AFTER_STAGES_BEFORE_QUEUE_STAGES_VISIBILITY_OPTIONS = ObjC.sel("barrierAfterStages:beforeQueueStages:visibilityOptions:");
    private static final long COMMAND_BUFFER = ObjC.sel("commandBuffer");
    private static final long END_ENCODING = ObjC.sel("endEncoding");
    private static final long INSERT_DEBUG_SIGNPOST = ObjC.sel("insertDebugSignpost:");
    private static final long LABEL = ObjC.sel("label");
    private static final long POP_DEBUG_GROUP = ObjC.sel("popDebugGroup");
    private static final long PUSH_DEBUG_GROUP = ObjC.sel("pushDebugGroup:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long UPDATE_FENCE_AFTER_ENCODER_STAGES = ObjC.sel("updateFence:afterEncoderStages:");
    private static final long WAIT_FOR_FENCE_BEFORE_ENCODER_STAGES = ObjC.sel("waitForFence:beforeEncoderStages:");

    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle LLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG);

    protected MTL4CommandEncoder(long id) {
        super(id);
    }

    public static MTL4CommandEncoder of(long id) {
        return new MTL4CommandEncoder(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public MTL4CommandBuffer commandBuffer() {
        return MTL4CommandBuffer.of(sendPtr(id, COMMAND_BUFFER));
    }

    @SneakyThrows
    public void barrierAfterQueueStages(long after, long before, long options) {
        LLL.invokeExact(id, BARRIER_AFTER_QUEUE_STAGES_BEFORE_STAGES_VISIBILITY_OPTIONS, after, before,
                options);
    }

    @SneakyThrows
    public void barrierAfterStages(long after, long before, long options) {
        LLL.invokeExact(id, BARRIER_AFTER_STAGES_BEFORE_QUEUE_STAGES_VISIBILITY_OPTIONS, after, before,
                options);
    }

    @SneakyThrows
    public void barrierAfterEncoderStages(long after, long before, long options) {
        LLL.invokeExact(id, BARRIER_AFTER_ENCODER_STAGES_BEFORE_ENCODER_STAGES_VISIBILITY_OPTIONS, after,
                before, options);
    }

    @SneakyThrows
    public void updateFence(MTLFence fence, long stages) {
        PL.invokeExact(id, UPDATE_FENCE_AFTER_ENCODER_STAGES, fence.getId(), stages);
    }

    @SneakyThrows
    public void waitForFence(MTLFence fence, long stages) {
        PL.invokeExact(id, WAIT_FOR_FENCE_BEFORE_ENCODER_STAGES, fence.getId(), stages);
    }

    @SneakyThrows
    public void insertDebugSignpost(NSString string) {
        P.invokeExact(id, INSERT_DEBUG_SIGNPOST, string.getId());
    }

    @SneakyThrows
    public void pushDebugGroup(NSString string) {
        P.invokeExact(id, PUSH_DEBUG_GROUP, string.getId());
    }

    public void popDebugGroup() {
        sendVoid(id, POP_DEBUG_GROUP);
    }

    public void endEncoding() {
        sendVoid(id, END_ENCODING);
    }
}
