package dev.dov.metalj.pipelines.shaders;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLFunctionReflection extends NSObject {
    private static final long BINDINGS = ObjC.sel("bindings");
    private static final long USER_ANNOTATION = ObjC.sel("userAnnotation");

    private MTLFunctionReflection(long id) {
        super(id);
    }

    public static MTLFunctionReflection of(long id) {
        return new MTLFunctionReflection(id);
    }

    public NSArray bindings() {
        return NSArray.of(sendPtr(id, BINDINGS));
    }

    public NSString userAnnotation() {
        return NSString.of(sendPtr(id, USER_ANNOTATION));
    }
}
