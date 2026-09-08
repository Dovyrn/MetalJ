package dev.dov.metalj.resources;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLPurgeableState {
    public final long MTLPurgeableStateKeepCurrent = 1;
    public final long MTLPurgeableStateNonVolatile = 2;
    public final long MTLPurgeableStateVolatile = 3;
    public final long MTLPurgeableStateEmpty = 4;
}
