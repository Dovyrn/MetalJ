package dev.dov.metalj.functions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLFunctionOptions {
    public final long MTLFunctionOptionNone = 0;
    public final long MTLFunctionOptionCompileToBinary = 1;
    public final long MTLFunctionOptionStoreFunctionInMetalScript = 2;
    public final long MTLFunctionOptionFailOnBinaryArchiveMiss = 4;
    public final long MTLFunctionOptionPipelineIndependent = 8;
}
