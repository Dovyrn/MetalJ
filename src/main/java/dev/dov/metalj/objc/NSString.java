package dev.dov.metalj.objc;

import java.lang.foreign.Arena;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class NSString extends NSObject {
    private static final MethodHandle FROM = handle(ObjC.PTR, ValueLayout.ADDRESS);
    private static final MethodHandle UTF8 = handle(ValueLayout.ADDRESS);

    private NSString(long id) {
        super(id);
    }

    public static NSString of(long id) {
        return new NSString(id);
    }

    @SneakyThrows
    public static NSString stringWithUTF8String(String text) {
        try (var arena = Arena.ofConfined()) {
            long id = (long) FROM.invokeExact(ObjC.cls("NSString"), ObjC.sel("stringWithUTF8String:"),
                    arena.allocateFrom(text));
            return new NSString(id);
        }
    }

    @SneakyThrows
    public String UTF8String() {
        if (isNull()) {
            return null;
        }
        var chars = (java.lang.foreign.MemorySegment) UTF8.invokeExact(id, ObjC.sel("UTF8String"));
        return chars.reinterpret(Long.MAX_VALUE).getString(0);
    }
}
