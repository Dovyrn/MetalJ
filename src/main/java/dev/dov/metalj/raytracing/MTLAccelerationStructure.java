package dev.dov.metalj.raytracing;

import dev.dov.metalj.resources.MTLResource;

public class MTLAccelerationStructure extends MTLResource {
    private MTLAccelerationStructure(long id) {
        super(id);
    }

    public static MTLAccelerationStructure of(long id) {
        return new MTLAccelerationStructure(id);
    }

    public long size() {
        return sendLong(id, "size");
    }

    public long gpuResourceID() {
        return sendLong(id, "gpuResourceID");
    }
}
