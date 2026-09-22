package dev.dov.metalj.commands.encoders;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCommandEncoder extends NSObject {
    private static final long END_ENCODING = ObjC.sel("endEncoding");
    private static final long INSERT_DEBUG_SIGNPOST = ObjC.sel("insertDebugSignpost:");
    private static final long LABEL = ObjC.sel("label");
    private static final long POP_DEBUG_GROUP = ObjC.sel("popDebugGroup");
    private static final long PUSH_DEBUG_GROUP = ObjC.sel("pushDebugGroup:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    protected MTLCommandEncoder(long id) {
        super(id);
    }

    public static MTLCommandEncoder of(long id) {
        return new MTLCommandEncoder(id);
    }

    public void endEncoding() {
        sendVoid(id, END_ENCODING);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
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
}
