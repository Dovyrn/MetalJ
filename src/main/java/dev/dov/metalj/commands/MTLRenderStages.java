package dev.dov.metalj.commands;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLRenderStages {
    public final long MTLRenderStageVertex = 1L << 0;
    public final long MTLRenderStageFragment = 1L << 1;
    public final long MTLRenderStageTile = 1L << 2;
    public final long MTLRenderStageObject = 1L << 3;
    public final long MTLRenderStageMesh = 1L << 4;
}
