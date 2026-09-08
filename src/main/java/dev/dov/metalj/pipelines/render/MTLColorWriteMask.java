package dev.dov.metalj.pipelines.render;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLColorWriteMask {
    public final long MTLColorWriteMaskNone = 0;
    public final long MTLColorWriteMaskRed = 0x1 << 3;
    public final long MTLColorWriteMaskGreen = 0x1 << 2;
    public final long MTLColorWriteMaskBlue = 0x1 << 1;
    public final long MTLColorWriteMaskAlpha = 0x1 << 0;
    public final long MTLColorWriteMaskAll = 0xf;
}
