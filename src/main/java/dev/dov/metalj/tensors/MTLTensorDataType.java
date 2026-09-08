package dev.dov.metalj.tensors;

import dev.dov.metalj.pipelines.shaders.MTLDataType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLTensorDataType {
    public final long MTLTensorDataTypeNone = MTLDataType.MTLDataTypeNone;
    public final long MTLTensorDataTypeFloat32 = MTLDataType.MTLDataTypeFloat;
    public final long MTLTensorDataTypeFloat16 = MTLDataType.MTLDataTypeHalf;
    public final long MTLTensorDataTypeBFloat16 = MTLDataType.MTLDataTypeBFloat;
    public final long MTLTensorDataTypeInt8 = MTLDataType.MTLDataTypeChar;
    public final long MTLTensorDataTypeUInt8 = MTLDataType.MTLDataTypeUChar;
    public final long MTLTensorDataTypeInt16 = MTLDataType.MTLDataTypeShort;
    public final long MTLTensorDataTypeUInt16 = MTLDataType.MTLDataTypeUShort;
    public final long MTLTensorDataTypeInt32 = MTLDataType.MTLDataTypeInt;
    public final long MTLTensorDataTypeUInt32 = MTLDataType.MTLDataTypeUInt;
}
