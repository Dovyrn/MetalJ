package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLFunctionConstantValues;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLFunctionDescriptor extends NSObject {
    protected static final MethodHandle L = handle(null, ObjC.LONG);
    protected static final MethodHandle P = handle(null, ObjC.PTR);

    protected MTLFunctionDescriptor(long id) {
        super(id);
    }

    public static MTLFunctionDescriptor of(long id) {
        return new MTLFunctionDescriptor(id);
    }

    public static MTLFunctionDescriptor functionDescriptor() {
        return new MTLFunctionDescriptor(sendPtr(ObjC.cls("MTLFunctionDescriptor"), "functionDescriptor"));
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    @SneakyThrows
    public void setName(NSString name) {
        P.invokeExact(id, ObjC.sel("setName:"), name.getId());
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

    public long options() {
        return sendLong(id, "options");
    }

    @SneakyThrows
    public void setOptions(long options) {
        L.invokeExact(id, ObjC.sel("setOptions:"), options);
    }
}
