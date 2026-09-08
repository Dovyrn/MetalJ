package dev.dov.metalj.pipelines.shaders;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLFunctionType {
    public final long MTLFunctionTypeVertex = 1;
    public final long MTLFunctionTypeFragment = 2;
    public final long MTLFunctionTypeKernel = 3;
    public final long MTLFunctionTypeVisible = 5;
    public final long MTLFunctionTypeIntersection = 6;
    public final long MTLFunctionTypeMesh = 7;
    public final long MTLFunctionTypeObject = 8;
}
