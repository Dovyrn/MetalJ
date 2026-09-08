package dev.dov.metalj.commands.passes;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLStoreActionOptions {
    public final long MTLStoreActionOptionNone = 0;
    public final long MTLStoreActionOptionCustomSamplePositions = 1 << 0;
}
