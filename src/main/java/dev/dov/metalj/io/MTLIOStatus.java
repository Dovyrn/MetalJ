package dev.dov.metalj.io;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLIOStatus {
    public final long MTLIOStatusPending = 0;
    public final long MTLIOStatusCancelled = 1;
    public final long MTLIOStatusError = 2;
    public final long MTLIOStatusComplete = 3;
}
