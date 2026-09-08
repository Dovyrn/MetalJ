package dev.dov.metalj.commands.encoders;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLBarrierScope {
    public final long MTLBarrierScopeBuffers = 1 << 0;
    public final long MTLBarrierScopeTextures = 1 << 1;
}
