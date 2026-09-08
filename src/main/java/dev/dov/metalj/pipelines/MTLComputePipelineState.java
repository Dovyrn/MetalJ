package dev.dov.metalj.pipelines;

import dev.dov.metalj.objc.NSObject;

public class MTLComputePipelineState extends NSObject {
    private MTLComputePipelineState(long id) {
        super(id);
    }

    public static MTLComputePipelineState of(long id) {
        return new MTLComputePipelineState(id);
    }
}
