package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTL4MachineLearningPipelineReflection extends NSObject {
    private static final long BINDINGS = ObjC.sel("bindings");

    private MTL4MachineLearningPipelineReflection(long id) {
        super(id);
    }

    public static MTL4MachineLearningPipelineReflection of(long id) {
        return new MTL4MachineLearningPipelineReflection(id);
    }

    public NSArray bindings() {
        return NSArray.of(sendPtr(id, BINDINGS));
    }
}
