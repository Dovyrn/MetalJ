package dev.dov.metalj.commands;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLStoreAction {
    public final long MTLStoreActionDontCare = 0;
    public final long MTLStoreActionStore = 1;
    public final long MTLStoreActionMultisampleResolve = 2;
    public final long MTLStoreActionStoreAndMultisampleResolve = 3;
    public final long MTLStoreActionUnknown = 4;
    public final long MTLStoreActionCustomSampleDepthStore = 5;
}
