package dev.dov.metalj.raytracing;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLIntersectionFunctionSignature {
    public final long MTLIntersectionFunctionSignatureNone = 0;
    public final long MTLIntersectionFunctionSignatureInstancing = 1;
    public final long MTLIntersectionFunctionSignatureTriangleData = 2;
    public final long MTLIntersectionFunctionSignatureWorldSpaceData = 4;
    public final long MTLIntersectionFunctionSignatureInstanceMotion = 8;
    public final long MTLIntersectionFunctionSignaturePrimitiveMotion = 16;
    public final long MTLIntersectionFunctionSignatureExtendedLimits = 32;
    public final long MTLIntersectionFunctionSignatureMaxLevels = 64;
    public final long MTLIntersectionFunctionSignatureCurveData = 128;
}
