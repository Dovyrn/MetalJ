package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLFunctionConstantValues;
import lombok.SneakyThrows;

public class MTL4SpecializedFunctionDescriptor extends MTL4FunctionDescriptor {
    private static final long MTL_4_SPECIALIZED_FUNCTION_DESCRIPTOR = ObjC.cls("MTL4SpecializedFunctionDescriptor");

    private static final long CONSTANT_VALUES = ObjC.sel("constantValues");
    private static final long FUNCTION_DESCRIPTOR = ObjC.sel("functionDescriptor");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_CONSTANT_VALUES = ObjC.sel("setConstantValues:");
    private static final long SET_FUNCTION_DESCRIPTOR = ObjC.sel("setFunctionDescriptor:");
    private static final long SET_SPECIALIZED_NAME = ObjC.sel("setSpecializedName:");
    private static final long SPECIALIZED_NAME = ObjC.sel("specializedName");

    private MTL4SpecializedFunctionDescriptor(long id) {
        super(id);
    }

    public static MTL4SpecializedFunctionDescriptor of(long id) {
        return new MTL4SpecializedFunctionDescriptor(id);
    }

    public static MTL4SpecializedFunctionDescriptor new_() {
        return new MTL4SpecializedFunctionDescriptor(sendPtr(MTL_4_SPECIALIZED_FUNCTION_DESCRIPTOR, NEW));
    }

    public MTL4FunctionDescriptor functionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, FUNCTION_DESCRIPTOR));
    }

    @SneakyThrows
    public void setFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, SET_FUNCTION_DESCRIPTOR, descriptor.getId());
    }

    public NSString specializedName() {
        return NSString.of(sendPtr(id, SPECIALIZED_NAME));
    }

    @SneakyThrows
    public void setSpecializedName(NSString name) {
        P.invokeExact(id, SET_SPECIALIZED_NAME, name.getId());
    }

    public MTLFunctionConstantValues constantValues() {
        return MTLFunctionConstantValues.of(sendPtr(id, CONSTANT_VALUES));
    }

    @SneakyThrows
    public void setConstantValues(MTLFunctionConstantValues values) {
        P.invokeExact(id, SET_CONSTANT_VALUES, values.getId());
    }
}
