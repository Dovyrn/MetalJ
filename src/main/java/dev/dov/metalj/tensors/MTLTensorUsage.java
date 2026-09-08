package dev.dov.metalj.tensors;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLTensorUsage {
    public final long MTLTensorUsageCompute = 1;
    public final long MTLTensorUsageRender = 2;
    public final long MTLTensorUsageMachineLearning = 4;
}
