package dev.dov.metalj.commands.encoders;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLStages {
    public final long MTLStageVertex = 1L << 0;
    public final long MTLStageFragment = 1L << 1;
    public final long MTLStageTile = 1L << 2;
    public final long MTLStageObject = 1L << 3;
    public final long MTLStageMesh = 1L << 4;
    public final long MTLStageResourceState = 1L << 26;
    public final long MTLStageDispatch = 1L << 27;
    public final long MTLStageBlit = 1L << 28;
    public final long MTLStageAccelerationStructure = 1L << 29;
    public final long MTLStageMachineLearning = 1L << 30;
    public final long MTLStageAll = Long.MAX_VALUE;
}
