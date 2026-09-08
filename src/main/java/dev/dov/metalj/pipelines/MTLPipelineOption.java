package dev.dov.metalj.pipelines;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLPipelineOption {
    public final long MTLPipelineOptionNone = 0;
    public final long MTLPipelineOptionArgumentInfo = 1;
    public final long MTLPipelineOptionBindingInfo = 1;
    public final long MTLPipelineOptionBufferTypeInfo = 2;
    public final long MTLPipelineOptionFailOnBinaryArchiveMiss = 4;
}
