package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4StaticLinkingDescriptor extends NSObject {
    private static final long MTL_4_STATIC_LINKING_DESCRIPTOR = ObjC.cls("MTL4StaticLinkingDescriptor");

    private static final long FUNCTION_DESCRIPTORS = ObjC.sel("functionDescriptors");
    private static final long GROUPS = ObjC.sel("groups");
    private static final long NEW = ObjC.sel("new");
    private static final long PRIVATE_FUNCTION_DESCRIPTORS = ObjC.sel("privateFunctionDescriptors");
    private static final long SET_FUNCTION_DESCRIPTORS = ObjC.sel("setFunctionDescriptors:");
    private static final long SET_GROUPS = ObjC.sel("setGroups:");
    private static final long SET_PRIVATE_FUNCTION_DESCRIPTORS = ObjC.sel("setPrivateFunctionDescriptors:");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4StaticLinkingDescriptor(long id) {
        super(id);
    }

    public static MTL4StaticLinkingDescriptor of(long id) {
        return new MTL4StaticLinkingDescriptor(id);
    }

    public static MTL4StaticLinkingDescriptor new_() {
        return new MTL4StaticLinkingDescriptor(sendPtr(MTL_4_STATIC_LINKING_DESCRIPTOR, NEW));
    }

    public NSArray functionDescriptors() {
        return NSArray.of(sendPtr(id, FUNCTION_DESCRIPTORS));
    }

    @SneakyThrows
    public void setFunctionDescriptors(NSArray descriptors) {
        P.invokeExact(id, SET_FUNCTION_DESCRIPTORS, descriptors.getId());
    }

    public NSArray privateFunctionDescriptors() {
        return NSArray.of(sendPtr(id, PRIVATE_FUNCTION_DESCRIPTORS));
    }

    @SneakyThrows
    public void setPrivateFunctionDescriptors(NSArray descriptors) {
        P.invokeExact(id, SET_PRIVATE_FUNCTION_DESCRIPTORS, descriptors.getId());
    }

    public long groups() {
        return sendPtr(id, GROUPS);
    }

    @SneakyThrows
    public void setGroups(long groups) {
        P.invokeExact(id, SET_GROUPS, groups);
    }
}
