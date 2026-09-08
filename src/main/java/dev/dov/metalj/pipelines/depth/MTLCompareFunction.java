package dev.dov.metalj.pipelines.depth;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLCompareFunction {
    public final long MTLCompareFunctionNever = 0;
    public final long MTLCompareFunctionLess = 1;
    public final long MTLCompareFunctionEqual = 2;
    public final long MTLCompareFunctionLessEqual = 3;
    public final long MTLCompareFunctionGreater = 4;
    public final long MTLCompareFunctionNotEqual = 5;
    public final long MTLCompareFunctionGreaterEqual = 6;
    public final long MTLCompareFunctionAlways = 7;
}
