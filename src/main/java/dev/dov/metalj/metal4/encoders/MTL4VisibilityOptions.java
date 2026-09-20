package dev.dov.metalj.metal4.encoders;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTL4VisibilityOptions {
    public final long MTL4VisibilityOptionNone = 0;
    public final long MTL4VisibilityOptionDevice = 1L << 0;
    public final long MTL4VisibilityOptionResourceAlias = 1L << 1;
}
