package dev.dov.metalj.metal4.encoders;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTL4RenderEncoderOptions {
    public final long MTL4RenderEncoderOptionNone = 0;
    public final long MTL4RenderEncoderOptionSuspending = 1L << 0;
    public final long MTL4RenderEncoderOptionResuming = 1L << 1;
}
