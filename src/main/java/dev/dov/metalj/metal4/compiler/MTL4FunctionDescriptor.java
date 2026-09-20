package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;

public class MTL4FunctionDescriptor extends NSObject {
    protected static final MethodHandle P = handle(null, ObjC.PTR);

    protected MTL4FunctionDescriptor(long id) {
        super(id);
    }

    public static MTL4FunctionDescriptor of(long id) {
        return new MTL4FunctionDescriptor(id);
    }
}
