package dev.dov.metalj.pipelines;

import dev.dov.metalj.objc.NSObject;

public class MTLRenderPipelineState extends NSObject {
    private MTLRenderPipelineState(long id) {
        super(id);
    }

    public static MTLRenderPipelineState of(long id) {
        return new MTLRenderPipelineState(id);
    }
}
