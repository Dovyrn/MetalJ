package dev.dov.metalj.device;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class NSProcessInfo extends NSObject {
    private static final MethodHandle B_L = handle(ObjC.BOOL, ObjC.LONG);

    public static final long NSDeviceCertificationiPhonePerformanceGaming = 1;
    public static final long NSProcessPerformanceProfileDefault = 0;
    public static final long NSProcessPerformanceProfileSustained = 1;

    private NSProcessInfo(long id) {
        super(id);
    }

    public static NSProcessInfo processInfo() {
        return new NSProcessInfo(sendPtr(ObjC.cls("NSProcessInfo"), "processInfo"));
    }

    @SneakyThrows
    public boolean isDeviceCertifiedFor(long tier) {
        return (boolean) B_L.invokeExact(id, ObjC.sel("isDeviceCertifiedFor:"), tier);
    }

    @SneakyThrows
    public boolean hasPerformanceProfile(long profile) {
        return (boolean) B_L.invokeExact(id, ObjC.sel("hasPerformanceProfile:"), profile);
    }
}
