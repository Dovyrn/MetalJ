package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLFunctionConstantValues;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLFunctionDescriptor extends NSObject {
    private static final long MTL_FUNCTION_DESCRIPTOR = ObjC.cls("MTLFunctionDescriptor");

    private static final long CONSTANT_VALUES = ObjC.sel("constantValues");
    private static final long FUNCTION_DESCRIPTOR = ObjC.sel("functionDescriptor");
    private static final long NAME = ObjC.sel("name");
    private static final long OPTIONS = ObjC.sel("options");
    private static final long SET_CONSTANT_VALUES = ObjC.sel("setConstantValues:");
    private static final long SET_NAME = ObjC.sel("setName:");
    private static final long SET_OPTIONS = ObjC.sel("setOptions:");
    private static final long SET_SPECIALIZED_NAME = ObjC.sel("setSpecializedName:");
    private static final long SPECIALIZED_NAME = ObjC.sel("specializedName");

    protected static final MethodHandle L = handle(null, ObjC.LONG);
    protected static final MethodHandle P = handle(null, ObjC.PTR);

    protected MTLFunctionDescriptor(long id) {
        super(id);
    }

    public static MTLFunctionDescriptor of(long id) {
        return new MTLFunctionDescriptor(id);
    }

    public static MTLFunctionDescriptor functionDescriptor() {
        return new MTLFunctionDescriptor(owned(() -> sendPtr(MTL_FUNCTION_DESCRIPTOR, FUNCTION_DESCRIPTOR)));
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    @SneakyThrows
    public void setName(NSString name) {
        P.invokeExact(id, SET_NAME, name.getId());
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

    public long options() {
        return sendLong(id, OPTIONS);
    }

    @SneakyThrows
    public void setOptions(long options) {
        L.invokeExact(id, SET_OPTIONS, options);
    }
}
