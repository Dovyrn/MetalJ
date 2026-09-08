package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLCounterSet extends NSObject {
    private MTLCounterSet(long id) {
        super(id);
    }

    public static MTLCounterSet of(long id) {
        return new MTLCounterSet(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    public NSArray counters() {
        return NSArray.of(sendPtr(id, "counters"));
    }
}
