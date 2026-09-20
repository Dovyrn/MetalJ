package dev.dov.metalj.metal4.pipelines;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTL4ShaderReflection {
    public final long MTL4ShaderReflectionNone = 0;
    public final long MTL4ShaderReflectionBindingInfo = 1L << 0;
    public final long MTL4ShaderReflectionBufferTypeInfo = 1L << 1;
}
