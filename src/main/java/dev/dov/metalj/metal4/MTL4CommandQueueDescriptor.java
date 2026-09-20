package dev.dov.metalj.metal4;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CommandQueueDescriptor extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4CommandQueueDescriptor(long id) {
        super(id);
    }

    public static MTL4CommandQueueDescriptor of(long id) {
        return new MTL4CommandQueueDescriptor(id);
    }

    public static MTL4CommandQueueDescriptor new_() {
        return new MTL4CommandQueueDescriptor(sendPtr(ObjC.cls("MTL4CommandQueueDescriptor"), "new"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    public long feedbackQueue() {
        return sendPtr(id, "feedbackQueue");
    }

    @SneakyThrows
    public void setFeedbackQueue(long queue) {
        P.invokeExact(id, ObjC.sel("setFeedbackQueue:"), queue);
    }
}
