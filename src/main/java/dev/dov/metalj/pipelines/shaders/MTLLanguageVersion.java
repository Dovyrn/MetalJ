package dev.dov.metalj.pipelines.shaders;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLLanguageVersion {
    public final long MTLLanguageVersion1_1 = (1 << 16) + 1;
    public final long MTLLanguageVersion1_2 = (1 << 16) + 2;
    public final long MTLLanguageVersion2_0 = 2 << 16;
    public final long MTLLanguageVersion2_1 = (2 << 16) + 1;
    public final long MTLLanguageVersion2_2 = (2 << 16) + 2;
    public final long MTLLanguageVersion2_3 = (2 << 16) + 3;
    public final long MTLLanguageVersion2_4 = (2 << 16) + 4;
    public final long MTLLanguageVersion3_0 = 3 << 16;
    public final long MTLLanguageVersion3_1 = (3 << 16) + 1;
    public final long MTLLanguageVersion3_2 = (3 << 16) + 2;
    public final long MTLLanguageVersion4_0 = 4 << 16;
}
