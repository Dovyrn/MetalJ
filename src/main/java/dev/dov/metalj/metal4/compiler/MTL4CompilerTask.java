package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTL4CompilerTask extends NSObject {
    private static final long COMPILER = ObjC.sel("compiler");
    private static final long STATUS = ObjC.sel("status");
    private static final long WAIT_UNTIL_COMPLETED = ObjC.sel("waitUntilCompleted");

    private MTL4CompilerTask(long id) {
        super(id);
    }

    public static MTL4CompilerTask of(long id) {
        return new MTL4CompilerTask(id);
    }

    public MTL4Compiler compiler() {
        return MTL4Compiler.of(sendPtr(id, COMPILER));
    }

    public long status() {
        return sendLong(id, STATUS);
    }

    public void waitUntilCompleted() {
        sendVoid(id, WAIT_UNTIL_COMPLETED);
    }
}
