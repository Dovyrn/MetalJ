package dev.dov.metalj.pipelines;

import dev.dov.metalj.objc.NSObject;

public class MTLDepthStencilState extends NSObject {
    private MTLDepthStencilState(long id) {
        super(id);
    }

    public static MTLDepthStencilState of(long id) {
        return new MTLDepthStencilState(id);
    }
}
