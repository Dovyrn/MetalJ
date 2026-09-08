package dev.dov.metalj.pipelines.depth;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLStencilOperation {
    public final long MTLStencilOperationKeep = 0;
    public final long MTLStencilOperationZero = 1;
    public final long MTLStencilOperationReplace = 2;
    public final long MTLStencilOperationIncrementClamp = 3;
    public final long MTLStencilOperationDecrementClamp = 4;
    public final long MTLStencilOperationInvert = 5;
    public final long MTLStencilOperationIncrementWrap = 6;
    public final long MTLStencilOperationDecrementWrap = 7;
}
