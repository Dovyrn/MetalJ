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
    private static final long BASE_RESOURCE_ID = ObjC.sel("baseResourceID");
    private static final long COPY_RESOURCE_VIEWS_FROM_POOL_SOURCE_RANGE_DESTINATION_INDEX = ObjC.sel("copyResourceViewsFromPool:sourceRange:destinationIndex:");
    private static final long DEVICE = ObjC.sel("device");
    private static final long LABEL = ObjC.sel("label");
    private static final long RESOURCE_VIEW_COUNT = ObjC.sel("resourceViewCount");

    private static final MethodHandle L_PRL = handle(ObjC.LONG, ObjC.PTR, NSRange.LAYOUT, ObjC.LONG);

    protected MTLResourceViewPool(long id) {
        super(id);
    }

    public static MTLResourceViewPool of(long id) {
        return new MTLResourceViewPool(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    public long baseResourceID() {
        return sendLong(id, BASE_RESOURCE_ID);
    }

    public long resourceViewCount() {
        return sendLong(id, RESOURCE_VIEW_COUNT);
    }

    @SneakyThrows
    public long copyResourceViewsFromPool(MTLResourceViewPool source, MemorySegment range, long destinationIndex) {
        return (long) L_PRL.invokeExact(id, COPY_RESOURCE_VIEWS_FROM_POOL_SOURCE_RANGE_DESTINATION_INDEX,
                source.getId(), range, destinationIndex);
    }
}
