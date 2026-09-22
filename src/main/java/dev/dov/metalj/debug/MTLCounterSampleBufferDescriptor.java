package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCounterSampleBufferDescriptor extends NSObject {
    private static final long MTL_COUNTER_SAMPLE_BUFFER_DESCRIPTOR = ObjC.cls("MTLCounterSampleBufferDescriptor");

    private static final long COUNTER_SET = ObjC.sel("counterSet");
    private static final long LABEL = ObjC.sel("label");
    private static final long NEW = ObjC.sel("new");
    private static final long SAMPLE_COUNT = ObjC.sel("sampleCount");
    private static final long SET_COUNTER_SET = ObjC.sel("setCounterSet:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_SAMPLE_COUNT = ObjC.sel("setSampleCount:");
    private static final long SET_STORAGE_MODE = ObjC.sel("setStorageMode:");
    private static final long STORAGE_MODE = ObjC.sel("storageMode");

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
                sendPtr(MTL_COUNTER_SAMPLE_BUFFER_DESCRIPTOR, NEW));
    }

    public MTLCounterSet counterSet() {
        return MTLCounterSet.of(sendPtr(id, COUNTER_SET));
    }

    @SneakyThrows
    public void setCounterSet(MTLCounterSet counterSet) {
        P.invokeExact(id, SET_COUNTER_SET, counterSet.getId());
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public long storageMode() {
        return sendLong(id, STORAGE_MODE);
    }

    @SneakyThrows
    public void setStorageMode(long storageMode) {
        L.invokeExact(id, SET_STORAGE_MODE, storageMode);
    }

    public long sampleCount() {
        return sendLong(id, SAMPLE_COUNT);
    }

    @SneakyThrows
    public void setSampleCount(long sampleCount) {
        L.invokeExact(id, SET_SAMPLE_COUNT, sampleCount);
    }
}
