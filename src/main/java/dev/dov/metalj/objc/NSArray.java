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

    public int count() {
        return (int) sendLong(id, "count");
    }

    @SneakyThrows
    public long at(int index) {
        return (long) AT.invokeExact(id, ObjC.sel("objectAtIndex:"), (long) index);
    }
}
