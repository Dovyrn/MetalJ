package dev.dov.metalj.resources;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLStorageMode {
    public final long MTLStorageModeShared = 0;
    public final long MTLStorageModeManaged = 1;
    public final long MTLStorageModePrivate = 2;
    public final long MTLStorageModeMemoryless = 3;
}
