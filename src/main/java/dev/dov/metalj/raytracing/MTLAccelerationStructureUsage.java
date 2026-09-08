package dev.dov.metalj.raytracing;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLAccelerationStructureUsage {
    public final long MTLAccelerationStructureUsageNone = 0;
    public final long MTLAccelerationStructureUsageRefit = 1;
    public final long MTLAccelerationStructureUsagePreferFastBuild = 2;
    public final long MTLAccelerationStructureUsageExtendedLimits = 4;
}
