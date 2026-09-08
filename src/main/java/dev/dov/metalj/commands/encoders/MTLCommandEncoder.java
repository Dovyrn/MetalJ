package dev.dov.metalj.commands.encoders;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCommandEncoder extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    protected MTLCommandEncoder(long id) {
        super(id);
    }

    public static MTLCommandEncoder of(long id) {
        return new MTLCommandEncoder(id);
    }

    public void endEncoding() {
        sendVoid(id, "endEncoding");
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
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
}
