package dev.dov.metalj.metal4;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTL4CommandQueueError {
    public final long MTL4CommandQueueErrorNone = 0;
    public final long MTL4CommandQueueErrorTimeout = 1;
    public final long MTL4CommandQueueErrorNotPermitted = 2;
    public final long MTL4CommandQueueErrorOutOfMemory = 3;
    public final long MTL4CommandQueueErrorDeviceRemoved = 4;
    public final long MTL4CommandQueueErrorAccessRevoked = 5;
    public final long MTL4CommandQueueErrorInternal = 6;
}
