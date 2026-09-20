package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4PipelineStageDynamicLinkingDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4PipelineStageDynamicLinkingDescriptor(long id) {
        super(id);
    }

    public static MTL4PipelineStageDynamicLinkingDescriptor of(long id) {
        return new MTL4PipelineStageDynamicLinkingDescriptor(id);
    }

    public static MTL4PipelineStageDynamicLinkingDescriptor new_() {
        return new MTL4PipelineStageDynamicLinkingDescriptor(
                sendPtr(ObjC.cls("MTL4PipelineStageDynamicLinkingDescriptor"), "new"));
    }

    public long maxCallStackDepth() {
        return sendLong(id, "maxCallStackDepth");
    }

    @SneakyThrows
    public void setMaxCallStackDepth(long depth) {
        L.invokeExact(id, ObjC.sel("setMaxCallStackDepth:"), depth);
    }

    public NSArray binaryLinkedFunctions() {
        return NSArray.of(sendPtr(id, "binaryLinkedFunctions"));
    }

    @SneakyThrows
    public void setBinaryLinkedFunctions(NSArray functions) {
        P.invokeExact(id, ObjC.sel("setBinaryLinkedFunctions:"), functions.getId());
    }

    public NSArray preloadedLibraries() {
        return NSArray.of(sendPtr(id, "preloadedLibraries"));
    }

    @SneakyThrows
    public void setPreloadedLibraries(NSArray libraries) {
        P.invokeExact(id, ObjC.sel("setPreloadedLibraries:"), libraries.getId());
    }
}
