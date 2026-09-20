package dev.dov.metalj.metal4.raytracing;

import dev.dov.metalj.raytracing.MTLAccelerationStructureDescriptor;

public class MTL4AccelerationStructureDescriptor extends MTLAccelerationStructureDescriptor {
    protected MTL4AccelerationStructureDescriptor(long id) {
        super(id);
    }

    public static MTL4AccelerationStructureDescriptor of(long id) {
        return new MTL4AccelerationStructureDescriptor(id);
    }
}
