package dev.dov.metalj.pipelines.shaders;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLMathMode {
    public final long MTLMathModeSafe = 0;
    public final long MTLMathModeRelaxed = 1;
    public final long MTLMathModeFast = 2;
}
