package dev.dov.metalj.debug;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLCounterSamplingPoint {
    public final long MTLCounterSamplingPointAtStageBoundary = 0;
    public final long MTLCounterSamplingPointAtDrawBoundary = 1;
    public final long MTLCounterSamplingPointAtDispatchBoundary = 2;
    public final long MTLCounterSamplingPointAtTileDispatchBoundary = 3;
    public final long MTLCounterSamplingPointAtBlitBoundary = 4;
}
