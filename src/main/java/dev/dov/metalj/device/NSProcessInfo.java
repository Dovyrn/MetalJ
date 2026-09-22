package dev.dov.metalj.device;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class NSProcessInfo extends NSObject {
    private static final long NS_PROCESS_INFO = ObjC.cls("NSProcessInfo");

    private static final long HAS_PERFORMANCE_PROFILE = ObjC.sel("hasPerformanceProfile:");
    private static final long IS_DEVICE_CERTIFIED_FOR = ObjC.sel("isDeviceCertifiedFor:");
    private static final long PROCESS_INFO = ObjC.sel("processInfo");

    private static final MethodHandle B_L = handle(ObjC.BOOL, ObjC.LONG);

    public static final long NSDeviceCertificationiPhonePerformanceGaming = 1;
    public static final long NSProcessPerformanceProfileDefault = 0;
    public static final long NSProcessPerformanceProfileSustained = 1;

    private NSProcessInfo(long id) {
        super(id);
    }

    public static NSProcessInfo processInfo() {
        return new NSProcessInfo(sendPtr(NS_PROCESS_INFO, PROCESS_INFO));
    }

    @SneakyThrows
    public boolean isDeviceCertifiedFor(long tier) {
        return (boolean) B_L.invokeExact(id, IS_DEVICE_CERTIFIED_FOR, tier);
    }

    @SneakyThrows
    public boolean hasPerformanceProfile(long profile) {
        return (boolean) B_L.invokeExact(id, HAS_PERFORMANCE_PROFILE, profile);
    }
}
