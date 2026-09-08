package dev.dov.metalj.resources;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLResourceOptions {
    public final long MTLResourceCPUCacheModeShift = 0;
    public final long MTLResourceCPUCacheModeMask = 0xfL << MTLResourceCPUCacheModeShift;
    public final long MTLResourceStorageModeShift = 4;
    public final long MTLResourceStorageModeMask = 0xfL << MTLResourceStorageModeShift;
    public final long MTLResourceHazardTrackingModeShift = 8;
    public final long MTLResourceHazardTrackingModeMask = 0x3L << MTLResourceHazardTrackingModeShift;
    public final long MTLResourceCPUCacheModeDefaultCache = MTLCPUCacheMode.MTLCPUCacheModeDefaultCache << MTLResourceCPUCacheModeShift;
    public final long MTLResourceCPUCacheModeWriteCombined = MTLCPUCacheMode.MTLCPUCacheModeWriteCombined << MTLResourceCPUCacheModeShift;
    public final long MTLResourceStorageModeShared = MTLStorageMode.MTLStorageModeShared << MTLResourceStorageModeShift;
    public final long MTLResourceStorageModeManaged = MTLStorageMode.MTLStorageModeManaged << MTLResourceStorageModeShift;
    public final long MTLResourceStorageModePrivate = MTLStorageMode.MTLStorageModePrivate << MTLResourceStorageModeShift;
    public final long MTLResourceStorageModeMemoryless = MTLStorageMode.MTLStorageModeMemoryless << MTLResourceStorageModeShift;
    public final long MTLResourceHazardTrackingModeDefault = MTLHazardTrackingMode.MTLHazardTrackingModeDefault << MTLResourceHazardTrackingModeShift;
    public final long MTLResourceHazardTrackingModeUntracked = MTLHazardTrackingMode.MTLHazardTrackingModeUntracked << MTLResourceHazardTrackingModeShift;
    public final long MTLResourceHazardTrackingModeTracked = MTLHazardTrackingMode.MTLHazardTrackingModeTracked << MTLResourceHazardTrackingModeShift;
    public final long MTLResourceOptionCPUCacheModeDefault = MTLResourceCPUCacheModeDefaultCache;
    public final long MTLResourceOptionCPUCacheModeWriteCombined = MTLResourceCPUCacheModeWriteCombined;
}
