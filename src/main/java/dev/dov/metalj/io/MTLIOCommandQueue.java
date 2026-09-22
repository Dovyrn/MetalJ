package dev.dov.metalj.io;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIOCommandQueue extends NSObject {
    private static final long COMMAND_BUFFER = ObjC.sel("commandBuffer");
    private static final long COMMAND_BUFFER_WITH_UNRETAINED_REFERENCES = ObjC.sel("commandBufferWithUnretainedReferences");
    private static final long ENQUEUE_BARRIER = ObjC.sel("enqueueBarrier");
    private static final long LABEL = ObjC.sel("label");
    private static final long SET_LABEL = ObjC.sel("setLabel:");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLIOCommandQueue(long id) {
        super(id);
    }

    public static MTLIOCommandQueue of(long id) {
        return new MTLIOCommandQueue(id);
    }

    public MTLIOCommandBuffer commandBuffer() {
        return MTLIOCommandBuffer.of(owned(() -> sendPtr(id, COMMAND_BUFFER)));
    }

    public MTLIOCommandBuffer commandBufferWithUnretainedReferences() {
        return MTLIOCommandBuffer.of(owned(() -> sendPtr(id, COMMAND_BUFFER_WITH_UNRETAINED_REFERENCES)));
    }

    public void enqueueBarrier() {
        sendVoid(id, ENQUEUE_BARRIER);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }
}
