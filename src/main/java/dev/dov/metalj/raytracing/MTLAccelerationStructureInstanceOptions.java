package dev.dov.metalj.raytracing;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLAccelerationStructureInstanceOptions {
    public final long MTLAccelerationStructureInstanceOptionNone = 0;
    public final long MTLAccelerationStructureInstanceOptionDisableTriangleCulling = 1;
    public final long MTLAccelerationStructureInstanceOptionTriangleFrontFacingWindingCounterClockwise = 2;
    public final long MTLAccelerationStructureInstanceOptionOpaque = 4;
    public final long MTLAccelerationStructureInstanceOptionNonOpaque = 8;
}
