package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLLinkedFunctions extends NSObject {
    private static final long MTL_LINKED_FUNCTIONS = ObjC.cls("MTLLinkedFunctions");

    private static final long BINARY_FUNCTIONS = ObjC.sel("binaryFunctions");
    private static final long FUNCTIONS = ObjC.sel("functions");
    private static final long GROUPS = ObjC.sel("groups");
    private static final long LINKED_FUNCTIONS = ObjC.sel("linkedFunctions");
    private static final long PRIVATE_FUNCTIONS = ObjC.sel("privateFunctions");
    private static final long SET_BINARY_FUNCTIONS = ObjC.sel("setBinaryFunctions:");
    private static final long SET_FUNCTIONS = ObjC.sel("setFunctions:");
    private static final long SET_GROUPS = ObjC.sel("setGroups:");
    private static final long SET_PRIVATE_FUNCTIONS = ObjC.sel("setPrivateFunctions:");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLLinkedFunctions(long id) {
        super(id);
    }

    public static MTLLinkedFunctions of(long id) {
        return new MTLLinkedFunctions(id);
    }

    public static MTLLinkedFunctions linkedFunctions() {
        return new MTLLinkedFunctions(owned(() -> sendPtr(MTL_LINKED_FUNCTIONS, LINKED_FUNCTIONS)));
    }

    public NSArray functions() {
        return NSArray.of(sendPtr(id, FUNCTIONS));
    }

    @SneakyThrows
    public void setFunctions(NSArray functions) {
        P.invokeExact(id, SET_FUNCTIONS, functions.getId());
    }

    public NSArray binaryFunctions() {
        return NSArray.of(sendPtr(id, BINARY_FUNCTIONS));
    }

    @SneakyThrows
    public void setBinaryFunctions(NSArray functions) {
        P.invokeExact(id, SET_BINARY_FUNCTIONS, functions.getId());
    }

    public NSArray privateFunctions() {
        return NSArray.of(sendPtr(id, PRIVATE_FUNCTIONS));
    }

    @SneakyThrows
    public void setPrivateFunctions(NSArray functions) {
        P.invokeExact(id, SET_PRIVATE_FUNCTIONS, functions.getId());
    }

    public long groups() {
        return sendPtr(id, GROUPS);
    }

    @SneakyThrows
    public void setGroups(long groups) {
        P.invokeExact(id, SET_GROUPS, groups);
    }
}
