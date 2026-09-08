package dev.dov.metalj.tensors;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLTensorError {
    public final long MTLTensorErrorNone = 0;
    public final long MTLTensorErrorInternalError = 1;
    public final long MTLTensorErrorInvalidDescriptor = 2;
}
