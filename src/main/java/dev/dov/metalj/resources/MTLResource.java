package dev.dov.metalj.resources;

import dev.dov.metalj.objc.NSObject;

public class MTLResource extends NSObject {
    protected MTLResource(long id) {
        super(id);
    }

    public static MTLResource of(long id) {
        return new MTLResource(id);
    }
}
