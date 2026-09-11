package dev.dov.metalj.objc;

import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class NSURL extends NSObject {
    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);

    private NSURL(long id) {
        super(id);
    }

    public static NSURL of(long id) {
        return new NSURL(id);
    }

    @SneakyThrows
    public static NSURL fileURLWithPath(String path) {
        var text = NSString.stringWithUTF8String(path);
        try {
            return new NSURL(owned(() -> (long) P_P.invokeExact(ObjC.cls("NSURL"), ObjC.sel("fileURLWithPath:"),
                    text.getId())));
        } finally {
            text.release();
        }
    }

    public NSString path() {
        return NSString.of(sendPtr(id, "path"));
    }

    public NSString absoluteString() {
        return NSString.of(sendPtr(id, "absoluteString"));
    }
}
