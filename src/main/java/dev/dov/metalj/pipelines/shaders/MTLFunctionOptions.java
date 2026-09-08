package dev.dov.metalj.pipelines.shaders;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLFunctionOptions {
    public final long MTLFunctionOptionNone = 0;
    public final long MTLFunctionOptionCompileToBinary = 1 << 0;
    public final long MTLFunctionOptionStoreFunctionInMetalPipelinesScript = 1 << 1;
    public final long MTLFunctionOptionFailOnBinaryArchiveMiss = 1 << 2;
}
