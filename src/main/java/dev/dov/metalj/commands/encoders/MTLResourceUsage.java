package dev.dov.metalj.commands.encoders;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLResourceUsage {
    public final long MTLResourceUsageRead = 1 << 0;
    public final long MTLResourceUsageWrite = 1 << 1;
    public final long MTLResourceUsageSample = 1 << 2;
}
