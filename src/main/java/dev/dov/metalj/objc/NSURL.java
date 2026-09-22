package dev.dov.metalj.objc;

import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class NSURL extends NSObject {
    private static final long NSURL_CLS = ObjC.cls("NSURL");

    private static final long ABSOLUTE_STRING = ObjC.sel("absoluteString");
    private static final long FILE_URL_WITH_PATH = ObjC.sel("fileURLWithPath:");
    private static final long PATH = ObjC.sel("path");

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
            return new NSURL(owned(() -> (long) P_P.invokeExact(NSURL_CLS, FILE_URL_WITH_PATH,
                    text.getId())));
        } finally {
            text.release();
        }
    }

    public NSString path() {
        return NSString.of(sendPtr(id, PATH));
    }

    public NSString absoluteString() {
        return NSString.of(sendPtr(id, ABSOLUTE_STRING));
    }
}
