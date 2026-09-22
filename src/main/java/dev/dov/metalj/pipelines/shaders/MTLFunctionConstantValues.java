package dev.dov.metalj.pipelines.shaders;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLFunctionConstantValues extends NSObject {
    private static final long MTL_FUNCTION_CONSTANT_VALUES = ObjC.cls("MTLFunctionConstantValues");

    private static final long NEW = ObjC.sel("new");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_CONSTANT_VALUE_TYPE_AT_INDEX = ObjC.sel("setConstantValue:type:atIndex:");
    private static final long SET_CONSTANT_VALUE_TYPE_WITH_NAME = ObjC.sel("setConstantValue:type:withName:");
    private static final long SET_CONSTANT_VALUES_TYPE_WITH_RANGE = ObjC.sel("setConstantValues:type:withRange:");

    private static final MethodHandle ALL = handle(null, ValueLayout.ADDRESS, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle ALR = handle(null, ValueLayout.ADDRESS, ObjC.LONG, NSRange.LAYOUT);
    private static final MethodHandle ALP = handle(null, ValueLayout.ADDRESS, ObjC.LONG, ObjC.PTR);

    private MTLFunctionConstantValues(long id) {
        super(id);
    }

    public static MTLFunctionConstantValues of(long id) {
        return new MTLFunctionConstantValues(id);
    }

    public static MTLFunctionConstantValues new_() {
        return new MTLFunctionConstantValues(sendPtr(MTL_FUNCTION_CONSTANT_VALUES, NEW));
    }

    @SneakyThrows
    public void setConstantValue(MemorySegment value, long type, long index) {
        ALL.invokeExact(id, SET_CONSTANT_VALUE_TYPE_AT_INDEX, value, type, index);
    }

    @SneakyThrows
    public void setConstantValues(MemorySegment values, long type, MemorySegment range) {
        ALR.invokeExact(id, SET_CONSTANT_VALUES_TYPE_WITH_RANGE, values, type, range);
    }

    @SneakyThrows
    public void setConstantValue(MemorySegment value, long type, NSString name) {
        ALP.invokeExact(id, SET_CONSTANT_VALUE_TYPE_WITH_NAME, value, type, name.getId());
    }

    public void reset() {
        sendVoid(id, RESET);
    }
}
