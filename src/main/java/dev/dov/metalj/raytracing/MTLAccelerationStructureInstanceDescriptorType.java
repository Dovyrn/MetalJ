package dev.dov.metalj.raytracing;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLAccelerationStructureInstanceDescriptorType {
    public final long MTLAccelerationStructureInstanceDescriptorTypeDefault = 0;
    public final long MTLAccelerationStructureInstanceDescriptorTypeUserID = 1;
    public final long MTLAccelerationStructureInstanceDescriptorTypeMotion = 2;
    public final long MTLAccelerationStructureInstanceDescriptorTypeIndirect = 3;
    public final long MTLAccelerationStructureInstanceDescriptorTypeIndirectMotion = 4;
}
