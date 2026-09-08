package dev.dov.metalj.resources.textures;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLTextureUsage {
    public final long MTLTextureUsageUnknown = 0x0000;
    public final long MTLTextureUsageShaderRead = 0x0001;
    public final long MTLTextureUsageShaderWrite = 0x0002;
    public final long MTLTextureUsageRenderTarget = 0x0004;
    public final long MTLTextureUsagePixelFormatView = 0x0010;
    public final long MTLTextureUsageShaderAtomic = 0x0020;
}
