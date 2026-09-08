package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLLinkedFunctions extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLLinkedFunctions(long id) {
        super(id);
    }

    public static MTLLinkedFunctions of(long id) {
        return new MTLLinkedFunctions(id);
    }

    public static MTLLinkedFunctions linkedFunctions() {
        return new MTLLinkedFunctions(sendPtr(ObjC.cls("MTLLinkedFunctions"), "linkedFunctions"));
    }

    public NSArray functions() {
        return NSArray.of(sendPtr(id, "functions"));
    }

    @SneakyThrows
    public void setFunctions(NSArray functions) {
        P.invokeExact(id, ObjC.sel("setFunctions:"), functions.getId());
    }

    public NSArray binaryFunctions() {
        return NSArray.of(sendPtr(id, "binaryFunctions"));
    }

    @SneakyThrows
    public void setBinaryFunctions(NSArray functions) {
        P.invokeExact(id, ObjC.sel("setBinaryFunctions:"), functions.getId());
    }

    public NSArray privateFunctions() {
        return NSArray.of(sendPtr(id, "privateFunctions"));
    }

    @SneakyThrows
    public void setPrivateFunctions(NSArray functions) {
        P.invokeExact(id, ObjC.sel("setPrivateFunctions:"), functions.getId());
    }

    public long groups() {
        return sendPtr(id, "groups");
    }

    @SneakyThrows
    public void setGroups(long groups) {
        P.invokeExact(id, ObjC.sel("setGroups:"), groups);
    }
}
