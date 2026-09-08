package dev.dov.metalj.resources;

import dev.dov.metalj.objc.NSObject;

public class MTLAllocation extends NSObject {
    protected MTLAllocation(long id) {
        super(id);
    }

    public static MTLAllocation of(long id) {
        return new MTLAllocation(id);
    }

    public long allocatedSize() {
        return sendLong(id, "allocatedSize");
    }
}
