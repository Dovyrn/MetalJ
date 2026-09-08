package dev.dov.metalj.device;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class NSWindow extends NSObject {
    private static final MethodHandle SCALE = handle(ObjC.DOUBLE);

    private NSWindow(long id) {
        super(id);
    }

    public static NSWindow of(long id) {
        return new NSWindow(id);
    }

    @SneakyThrows
    public double backingScaleFactor() {
        return (double) SCALE.invokeExact(id, ObjC.sel("backingScaleFactor"));
    }

    public NSView contentView() {
        return NSView.of(sendPtr(id, "contentView"));
    }
}
