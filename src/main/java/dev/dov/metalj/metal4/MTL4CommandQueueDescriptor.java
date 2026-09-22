package dev.dov.metalj.metal4;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CommandQueueDescriptor extends NSObject {
    private static final long MTL_4_COMMAND_QUEUE_DESCRIPTOR = ObjC.cls("MTL4CommandQueueDescriptor");

    private static final long FEEDBACK_QUEUE = ObjC.sel("feedbackQueue");
    private static final long LABEL = ObjC.sel("label");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_FEEDBACK_QUEUE = ObjC.sel("setFeedbackQueue:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4CommandQueueDescriptor(long id) {
        super(id);
    }

    public static MTL4CommandQueueDescriptor of(long id) {
        return new MTL4CommandQueueDescriptor(id);
    }

    public static MTL4CommandQueueDescriptor new_() {
        return new MTL4CommandQueueDescriptor(sendPtr(MTL_4_COMMAND_QUEUE_DESCRIPTOR, NEW));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public long feedbackQueue() {
        return sendPtr(id, FEEDBACK_QUEUE);
    }

    @SneakyThrows
    public void setFeedbackQueue(long queue) {
        P.invokeExact(id, SET_FEEDBACK_QUEUE, queue);
    }
}
