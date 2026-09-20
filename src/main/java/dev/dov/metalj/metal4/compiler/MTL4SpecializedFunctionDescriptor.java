package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLFunctionConstantValues;
import lombok.SneakyThrows;

public class MTL4SpecializedFunctionDescriptor extends MTL4FunctionDescriptor {
    private MTL4SpecializedFunctionDescriptor(long id) {
        super(id);
    }

    public static MTL4SpecializedFunctionDescriptor of(long id) {
        return new MTL4SpecializedFunctionDescriptor(id);
    }

    public static MTL4SpecializedFunctionDescriptor new_() {
        return new MTL4SpecializedFunctionDescriptor(sendPtr(ObjC.cls("MTL4SpecializedFunctionDescriptor"), "new"));
    }

    public MTL4FunctionDescriptor functionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, "functionDescriptor"));
    }

    @SneakyThrows
    public void setFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setFunctionDescriptor:"), descriptor.getId());
    }

    public NSString specializedName() {
        return NSString.of(sendPtr(id, "specializedName"));
    }

    @SneakyThrows
    public void setSpecializedName(NSString name) {
        P.invokeExact(id, ObjC.sel("setSpecializedName:"), name.getId());
    }

    public MTLFunctionConstantValues constantValues() {
        return MTLFunctionConstantValues.of(sendPtr(id, "constantValues"));
    }

    @SneakyThrows
    public void setConstantValues(MTLFunctionConstantValues values) {
        P.invokeExact(id, ObjC.sel("setConstantValues:"), values.getId());
    }
}
