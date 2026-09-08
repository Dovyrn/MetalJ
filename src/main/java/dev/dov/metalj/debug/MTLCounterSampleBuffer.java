package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSObject;

public class MTLCounterSampleBuffer extends NSObject {
    private MTLCounterSampleBuffer(long id) {
        super(id);
    }

    public static MTLCounterSampleBuffer of(long id) {
        return new MTLCounterSampleBuffer(id);
    }
}
