package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSObject;

public class MTLFunctionStitchingNode extends NSObject {
    protected MTLFunctionStitchingNode(long id) {
        super(id);
    }

    public static MTLFunctionStitchingNode of(long id) {
        return new MTLFunctionStitchingNode(id);
    }
}
