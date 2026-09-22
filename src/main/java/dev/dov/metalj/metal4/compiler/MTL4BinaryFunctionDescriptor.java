package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4BinaryFunctionDescriptor extends NSObject {
    private static final long MTL_4_BINARY_FUNCTION_DESCRIPTOR = ObjC.cls("MTL4BinaryFunctionDescriptor");

    private static final long FUNCTION_DESCRIPTOR = ObjC.sel("functionDescriptor");
    private static final long NAME = ObjC.sel("name");
    private static final long NEW = ObjC.sel("new");
    private static final long OPTIONS = ObjC.sel("options");
    private static final long SET_FUNCTION_DESCRIPTOR = ObjC.sel("setFunctionDescriptor:");
    private static final long SET_NAME = ObjC.sel("setName:");
    private static final long SET_OPTIONS = ObjC.sel("setOptions:");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4BinaryFunctionDescriptor(long id) {
        super(id);
    }

    public static MTL4BinaryFunctionDescriptor of(long id) {
        return new MTL4BinaryFunctionDescriptor(id);
    }

    public static MTL4BinaryFunctionDescriptor new_() {
        return new MTL4BinaryFunctionDescriptor(sendPtr(MTL_4_BINARY_FUNCTION_DESCRIPTOR, NEW));
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    @SneakyThrows
    public void setName(NSString name) {
        P.invokeExact(id, SET_NAME, name.getId());
    }

    public MTL4FunctionDescriptor functionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, FUNCTION_DESCRIPTOR));
    }

    @SneakyThrows
    public void setFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, SET_FUNCTION_DESCRIPTOR, descriptor.getId());
    }

    public long options() {
        return sendLong(id, OPTIONS);
    }

    @SneakyThrows
    public void setOptions(long options) {
        L.invokeExact(id, SET_OPTIONS, options);
    }
}
