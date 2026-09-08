package dev.dov.metalj.pools;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLResourceViewPool extends NSObject {
    private static final MethodHandle L_PRL = handle(ObjC.LONG, ObjC.PTR, NSRange.LAYOUT, ObjC.LONG);

    protected MTLResourceViewPool(long id) {
        super(id);
    }

    public static MTLResourceViewPool of(long id) {
        return new MTLResourceViewPool(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    public long baseResourceID() {
        return sendLong(id, "baseResourceID");
    }

    public long resourceViewCount() {
        return sendLong(id, "resourceViewCount");
    }

    @SneakyThrows
    public long copyResourceViewsFromPool(MTLResourceViewPool source, MemorySegment range, long destinationIndex) {
        return (long) L_PRL.invokeExact(id, ObjC.sel("copyResourceViewsFromPool:sourceRange:destinationIndex:"),
                source.getId(), range, destinationIndex);
    }
}
