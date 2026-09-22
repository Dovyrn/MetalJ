package dev.dov.metalj.raytracing;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLResource;

public class MTLAccelerationStructure extends MTLResource {
    private static final long GPU_RESOURCE_ID = ObjC.sel("gpuResourceID");
    private static final long SIZE = ObjC.sel("size");

    private MTLAccelerationStructure(long id) {
        super(id);
    }

    public static MTLAccelerationStructure of(long id) {
        return new MTLAccelerationStructure(id);
    }

    public long size() {
        return sendLong(id, SIZE);
    }

    public long gpuResourceID() {
        return sendLong(id, GPU_RESOURCE_ID);
    }
}
