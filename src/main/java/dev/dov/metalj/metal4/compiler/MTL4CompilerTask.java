package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;

public class MTL4CompilerTask extends NSObject {
    private MTL4CompilerTask(long id) {
        super(id);
    }

    public static MTL4CompilerTask of(long id) {
        return new MTL4CompilerTask(id);
    }

    public MTL4Compiler compiler() {
        return MTL4Compiler.of(sendPtr(id, "compiler"));
    }

    public long status() {
        return sendLong(id, "status");
    }

    public void waitUntilCompleted() {
        sendVoid(id, "waitUntilCompleted");
    }
}
