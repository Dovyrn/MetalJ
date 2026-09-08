package dev.dov.metalj.pipelines.shaders;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLFunctionReflection extends NSObject {
    private MTLFunctionReflection(long id) {
        super(id);
    }

    public static MTLFunctionReflection of(long id) {
        return new MTLFunctionReflection(id);
    }

    public NSArray bindings() {
        return NSArray.of(sendPtr(id, "bindings"));
    }

    public NSString userAnnotation() {
        return NSString.of(sendPtr(id, "userAnnotation"));
    }
}
