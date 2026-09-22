package dev.dov.metalj.resources;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLAllocation extends NSObject {
    private static final long ALLOCATED_SIZE = ObjC.sel("allocatedSize");

    protected MTLAllocation(long id) {
        super(id);
    }

    public static MTLAllocation of(long id) {
        return new MTLAllocation(id);
    }

    public long allocatedSize() {
        return sendLong(id, ALLOCATED_SIZE);
    }
}
