package dev.dov.metalj.metal4.compiler;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTL4BinaryFunctionOptions {
    public final long MTL4BinaryFunctionOptionNone = 0;
    public final long MTL4BinaryFunctionOptionPipelineIndependent = 1L << 1;
}
