package dev.dov.metalj.metal4.compiler;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTL4CompilerTaskStatus {
    public final long MTL4CompilerTaskStatusNone = 0;
    public final long MTL4CompilerTaskStatusScheduled = 1;
    public final long MTL4CompilerTaskStatusCompiling = 2;
    public final long MTL4CompilerTaskStatusFinished = 3;
}
