package dev.dov.metalj.io;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIOCommandQueue extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLIOCommandQueue(long id) {
        super(id);
    }

    public static MTLIOCommandQueue of(long id) {
        return new MTLIOCommandQueue(id);
    }

    public MTLIOCommandBuffer commandBuffer() {
        return MTLIOCommandBuffer.of(owned(() -> sendPtr(id, "commandBuffer")));
    }

    public MTLIOCommandBuffer commandBufferWithUnretainedReferences() {
        return MTLIOCommandBuffer.of(owned(() -> sendPtr(id, "commandBufferWithUnretainedReferences")));
    }

    public void enqueueBarrier() {
        sendVoid(id, "enqueueBarrier");
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }
}
