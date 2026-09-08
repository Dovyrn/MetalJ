package dev.dov.metalj.pipelines.render;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLTessellationPartitionMode {
    public final long MTLTessellationPartitionModePow2 = 0;
    public final long MTLTessellationPartitionModeInteger = 1;
    public final long MTLTessellationPartitionModeFractionalOdd = 2;
    public final long MTLTessellationPartitionModeFractionalEven = 3;
}
