package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4BinaryFunctionDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4BinaryFunctionDescriptor(long id) {
        super(id);
    }

    public static MTL4BinaryFunctionDescriptor of(long id) {
        return new MTL4BinaryFunctionDescriptor(id);
    }

    public static MTL4BinaryFunctionDescriptor new_() {
        return new MTL4BinaryFunctionDescriptor(sendPtr(ObjC.cls("MTL4BinaryFunctionDescriptor"), "new"));
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    @SneakyThrows
    public void setName(NSString name) {
        P.invokeExact(id, ObjC.sel("setName:"), name.getId());
    }

    public MTL4FunctionDescriptor functionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, "functionDescriptor"));
    }

    @SneakyThrows
    public void setFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setFunctionDescriptor:"), descriptor.getId());
    }

    public long options() {
        return sendLong(id, "options");
    }

    @SneakyThrows
    public void setOptions(long options) {
        L.invokeExact(id, ObjC.sel("setOptions:"), options);
    }
}
