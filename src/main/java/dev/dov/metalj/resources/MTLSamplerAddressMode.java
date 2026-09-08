package dev.dov.metalj.resources;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLSamplerAddressMode {
    public final long MTLSamplerAddressModeClampToEdge = 0;
    public final long MTLSamplerAddressModeMirrorClampToEdge = 1;
    public final long MTLSamplerAddressModeRepeat = 2;
    public final long MTLSamplerAddressModeMirrorRepeat = 3;
    public final long MTLSamplerAddressModeClampToZero = 4;
    public final long MTLSamplerAddressModeClampToBorderColor = 5;
}
