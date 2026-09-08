package dev.dov.metalj.objc;

import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class NSArray extends NSObject {
    private static final MethodHandle AT = handle(ObjC.PTR, ObjC.LONG);

    private NSArray(long id) {
        super(id);
    }

    public static NSArray of(long id) {
        return new NSArray(id);
    }

    public long count() {
        return sendLong(id, "count");
    }

    @SneakyThrows
    public long objectAtIndex(long index) {
        return (long) AT.invokeExact(id, ObjC.sel("objectAtIndex:"), index);
    }
}
