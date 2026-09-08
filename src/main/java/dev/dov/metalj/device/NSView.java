package dev.dov.metalj.device;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class NSView extends NSObject {
    private static final MethodHandle SET_PTR = handle(null, ObjC.PTR);
    private static final MethodHandle SET_BOOL = handle(null, ObjC.BOOL);

    private NSView(long id) {
        super(id);
    }

    public static NSView of(long id) {
        return new NSView(id);
    }

    @SneakyThrows
    public void setWantsLayer(boolean wants) {
        SET_BOOL.invokeExact(id, ObjC.sel("setWantsLayer:"), wants);
    }

    @SneakyThrows
    public void setLayer(CAMetalLayer layer) {
        SET_PTR.invokeExact(id, ObjC.sel("setLayer:"), layer.getId());
    }
}
