package dev.dov.metalj.device;

import dev.dov.metalj.commands.MTLCommandBuffer;
import dev.dov.metalj.objc.NSObject;

public class MTLCommandQueue extends NSObject {
    private MTLCommandQueue(long id) {
        super(id);
    }

    public static MTLCommandQueue of(long id) {
        return new MTLCommandQueue(id);
    }

    public MTLCommandBuffer commandBuffer() {
        return MTLCommandBuffer.of(sendPtr(id, "commandBuffer"));
    }
}
