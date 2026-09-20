package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4StaticLinkingDescriptor extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4StaticLinkingDescriptor(long id) {
        super(id);
    }

    public static MTL4StaticLinkingDescriptor of(long id) {
        return new MTL4StaticLinkingDescriptor(id);
    }

    public static MTL4StaticLinkingDescriptor new_() {
        return new MTL4StaticLinkingDescriptor(sendPtr(ObjC.cls("MTL4StaticLinkingDescriptor"), "new"));
    }

    public NSArray functionDescriptors() {
        return NSArray.of(sendPtr(id, "functionDescriptors"));
    }

    @SneakyThrows
    public void setFunctionDescriptors(NSArray descriptors) {
        P.invokeExact(id, ObjC.sel("setFunctionDescriptors:"), descriptors.getId());
    }

    public NSArray privateFunctionDescriptors() {
        return NSArray.of(sendPtr(id, "privateFunctionDescriptors"));
    }

    @SneakyThrows
    public void setPrivateFunctionDescriptors(NSArray descriptors) {
        P.invokeExact(id, ObjC.sel("setPrivateFunctionDescriptors:"), descriptors.getId());
    }

    public long groups() {
        return sendPtr(id, "groups");
    }

    @SneakyThrows
    public void setGroups(long groups) {
        P.invokeExact(id, ObjC.sel("setGroups:"), groups);
    }
}
