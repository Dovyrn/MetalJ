package dev.dov.metalj.resources;

import dev.dov.metalj.objc.NSObject;

public class MTLSamplerState extends NSObject {
    private MTLSamplerState(long id) {
        super(id);
    }

    public static MTLSamplerState of(long id) {
        return new MTLSamplerState(id);
    }
}
