package dev.dov.metalj.resources.indirect;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLIndirectCommandType {
    public final long MTLIndirectCommandTypeDraw = 1 << 0;
    public final long MTLIndirectCommandTypeDrawIndexed = 1 << 1;
    public final long MTLIndirectCommandTypeDrawPatches = 1 << 2;
    public final long MTLIndirectCommandTypeDrawIndexedPatches = 1 << 3;
    public final long MTLIndirectCommandTypeConcurrentDispatch = 1 << 5;
    public final long MTLIndirectCommandTypeConcurrentDispatchThreads = 1 << 6;
    public final long MTLIndirectCommandTypeDrawMeshThreadgroups = 1 << 7;
    public final long MTLIndirectCommandTypeDrawMeshThreads = 1 << 8;
}
