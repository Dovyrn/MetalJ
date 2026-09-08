package dev.dov.metalj.device;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLDevice extends NSObject {
    private static final MethodHandle FAMILY = handle(ObjC.BOOL, ObjC.LONG);
    private static final MethodHandle TIMESTAMPS = handle(null, ValueLayout.ADDRESS, ValueLayout.ADDRESS);

    public static final long MTLGPUFamilyApple7 = 1007;
    public static final long MTLGPUFamilyApple9 = 1009;
    public static final long MTLGPUFamilyMetal3 = 5001;

    private MTLDevice(long id) {
        super(id);
    }

    public static MTLDevice of(long id) {
        return new MTLDevice(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    public boolean hasUnifiedMemory() {
        return sendBool(id, "hasUnifiedMemory");
    }

    public long maxBufferLength() {
        return sendLong(id, "maxBufferLength");
    }

    @SneakyThrows
    public boolean supportsFamily(long family) {
        return (boolean) FAMILY.invokeExact(id, ObjC.sel("supportsFamily:"), family);
    }

    @SneakyThrows
    public void sampleTimestamps(MemorySegment cpu, MemorySegment gpu) {
        TIMESTAMPS.invokeExact(id, ObjC.sel("sampleTimestamps:gpuTimestamp:"), cpu, gpu);
    }

    public MTLCommandQueue newCommandQueue() {
        return MTLCommandQueue.of(sendPtr(id, "newCommandQueue"));
    }
}
