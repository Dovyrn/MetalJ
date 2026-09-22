package dev.dov.metalj.io;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIOFileHandle extends NSObject {
    private static final long LABEL = ObjC.sel("label");
    private static final long SET_LABEL = ObjC.sel("setLabel:");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLIOFileHandle(long id) {
        super(id);
    }

    public static MTLIOFileHandle of(long id) {
        return new MTLIOFileHandle(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }
}
