package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;

public class MTL4MachineLearningPipelineReflection extends NSObject {
    private MTL4MachineLearningPipelineReflection(long id) {
        super(id);
    }

    public static MTL4MachineLearningPipelineReflection of(long id) {
        return new MTL4MachineLearningPipelineReflection(id);
    }

    public NSArray bindings() {
        return NSArray.of(sendPtr(id, "bindings"));
    }
}
