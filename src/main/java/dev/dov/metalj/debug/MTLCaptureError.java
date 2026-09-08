package dev.dov.metalj.debug;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLCaptureError {
    public final long MTLCaptureErrorNotSupported = 1;
    public final long MTLCaptureErrorAlreadyCapturing = 2;
    public final long MTLCaptureErrorInvalidDescriptor = 3;
}
