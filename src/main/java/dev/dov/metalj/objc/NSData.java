package dev.dov.metalj.objc;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class NSData extends NSObject {
    private static final long BYTES_SEL = ObjC.sel("bytes");
    private static final long LENGTH = ObjC.sel("length");

    private static final MethodHandle BYTES = handle(ValueLayout.ADDRESS);

    private NSData(long id) {
        super(id);
    }

    public static NSData of(long id) {
        return new NSData(id);
    }

    public long length() {
        return sendLong(id, LENGTH);
    }

    @SneakyThrows
    public MemorySegment bytes() {
        var address = (MemorySegment) BYTES.invokeExact(id, BYTES_SEL);
        return address.reinterpret(length());
    }
}
