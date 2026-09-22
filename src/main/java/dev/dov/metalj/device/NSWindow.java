package dev.dov.metalj.device;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class NSWindow extends NSObject {
    private static final long BACKING_SCALE_FACTOR = ObjC.sel("backingScaleFactor");
    private static final long CONTENT_VIEW = ObjC.sel("contentView");

    private static final MethodHandle SCALE = handle(ObjC.DOUBLE);

    private NSWindow(long id) {
        super(id);
    }

    public static NSWindow of(long id) {
        return new NSWindow(id);
    }

    @SneakyThrows
    public double backingScaleFactor() {
        return (double) SCALE.invokeExact(id, BACKING_SCALE_FACTOR);
    }

    public NSView contentView() {
        return NSView.of(sendPtr(id, CONTENT_VIEW));
    }
}
