package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCounterSampleBufferDescriptor extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLCounterSampleBufferDescriptor(long id) {
        super(id);
    }

    public static MTLCounterSampleBufferDescriptor of(long id) {
        return new MTLCounterSampleBufferDescriptor(id);
    }

    public static MTLCounterSampleBufferDescriptor new_() {
        return new MTLCounterSampleBufferDescriptor(
                sendPtr(ObjC.cls("MTLCounterSampleBufferDescriptor"), "new"));
    }

    public MTLCounterSet counterSet() {
        return MTLCounterSet.of(sendPtr(id, "counterSet"));
    }

    @SneakyThrows
    public void setCounterSet(MTLCounterSet counterSet) {
        P.invokeExact(id, ObjC.sel("setCounterSet:"), counterSet.getId());
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    public long storageMode() {
        return sendLong(id, "storageMode");
    }

    @SneakyThrows
    public void setStorageMode(long storageMode) {
        L.invokeExact(id, ObjC.sel("setStorageMode:"), storageMode);
    }

    public long sampleCount() {
        return sendLong(id, "sampleCount");
    }

    @SneakyThrows
    public void setSampleCount(long sampleCount) {
        L.invokeExact(id, ObjC.sel("setSampleCount:"), sampleCount);
    }
}
