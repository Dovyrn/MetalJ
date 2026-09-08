package dev.dov.metalj.pipelines.compute;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;

public class MTLComputePipelineReflection extends NSObject {
    private MTLComputePipelineReflection(long id) {
        super(id);
    }

    public static MTLComputePipelineReflection of(long id) {
        return new MTLComputePipelineReflection(id);
    }

    public NSArray bindings() {
        return NSArray.of(sendPtr(id, "bindings"));
    }
}
