package dev.dov.metalj.metal4.encoders;

import dev.dov.metalj.metal4.MTL4CommandBuffer;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.sync.MTLFence;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CommandEncoder extends NSObject {
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
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    public MTL4CommandBuffer commandBuffer() {
        return MTL4CommandBuffer.of(sendPtr(id, "commandBuffer"));
    }

    @SneakyThrows
    public void barrierAfterQueueStages(long after, long before, long options) {
        LLL.invokeExact(id, ObjC.sel("barrierAfterQueueStages:beforeStages:visibilityOptions:"), after, before,
                options);
    }

    @SneakyThrows
    public void barrierAfterStages(long after, long before, long options) {
        LLL.invokeExact(id, ObjC.sel("barrierAfterStages:beforeQueueStages:visibilityOptions:"), after, before,
                options);
    }

    @SneakyThrows
    public void barrierAfterEncoderStages(long after, long before, long options) {
        LLL.invokeExact(id, ObjC.sel("barrierAfterEncoderStages:beforeEncoderStages:visibilityOptions:"), after,
                before, options);
    }

    @SneakyThrows
    public void updateFence(MTLFence fence, long stages) {
        PL.invokeExact(id, ObjC.sel("updateFence:afterEncoderStages:"), fence.getId(), stages);
    }

    @SneakyThrows
    public void waitForFence(MTLFence fence, long stages) {
        PL.invokeExact(id, ObjC.sel("waitForFence:beforeEncoderStages:"), fence.getId(), stages);
    }

    @SneakyThrows
    public void insertDebugSignpost(NSString string) {
        P.invokeExact(id, ObjC.sel("insertDebugSignpost:"), string.getId());
    }

    @SneakyThrows
    public void pushDebugGroup(NSString string) {
        P.invokeExact(id, ObjC.sel("pushDebugGroup:"), string.getId());
    }

    public void popDebugGroup() {
        sendVoid(id, "popDebugGroup");
    }

    public void endEncoding() {
        sendVoid(id, "endEncoding");
    }
}
