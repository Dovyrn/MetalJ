package dev.dov.metalj.debug;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSData;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCounterSampleBuffer extends NSObject {
    private static final MethodHandle P_R = handle(ObjC.PTR, NSRange.LAYOUT);

    private MTLCounterSampleBuffer(long id) {
        super(id);
    }

    public static MTLCounterSampleBuffer of(long id) {
        return new MTLCounterSampleBuffer(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    public long sampleCount() {
        return sendLong(id, "sampleCount");
    }

    @SneakyThrows
    public NSData resolveCounterRange(long location, long length) {
        try (var arena = Arena.ofConfined()) {
            var range = NSRange.of(arena, location, length);
            return NSData.of((long) P_R.invokeExact(id, ObjC.sel("resolveCounterRange:"), range));
        }
    }
}
