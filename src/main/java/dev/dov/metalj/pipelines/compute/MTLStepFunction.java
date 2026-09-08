package dev.dov.metalj.pipelines.compute;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLStepFunction {
    public final long MTLStepFunctionConstant = 0;
    public final long MTLStepFunctionPerVertex = 1;
    public final long MTLStepFunctionPerInstance = 2;
    public final long MTLStepFunctionPerPatch = 3;
    public final long MTLStepFunctionPerPatchControlPoint = 4;
    public final long MTLStepFunctionThreadPositionInGridX = 5;
    public final long MTLStepFunctionThreadPositionInGridY = 6;
    public final long MTLStepFunctionThreadPositionInGridXIndexed = 7;
    public final long MTLStepFunctionThreadPositionInGridYIndexed = 8;
}
