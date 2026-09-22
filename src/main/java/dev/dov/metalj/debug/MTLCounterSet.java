package dev.dov.metalj.debug;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLCounterSet extends NSObject {
    private static final long COUNTERS = ObjC.sel("counters");
    private static final long NAME = ObjC.sel("name");

    private MTLCounterSet(long id) {
        super(id);
    }

    public static MTLCounterSet of(long id) {
        return new MTLCounterSet(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    public NSArray counters() {
        return NSArray.of(sendPtr(id, COUNTERS));
    }
}
