package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4PipelineStageDynamicLinkingDescriptor extends NSObject {
    private static final long MTL_4_PIPELINE_STAGE_DYNAMIC_LINKING_DESCRIPTOR = ObjC.cls("MTL4PipelineStageDynamicLinkingDescriptor");

    private static final long BINARY_LINKED_FUNCTIONS = ObjC.sel("binaryLinkedFunctions");
    private static final long MAX_CALL_STACK_DEPTH = ObjC.sel("maxCallStackDepth");
    private static final long NEW = ObjC.sel("new");
    private static final long PRELOADED_LIBRARIES = ObjC.sel("preloadedLibraries");
    private static final long SET_BINARY_LINKED_FUNCTIONS = ObjC.sel("setBinaryLinkedFunctions:");
    private static final long SET_MAX_CALL_STACK_DEPTH = ObjC.sel("setMaxCallStackDepth:");
    private static final long SET_PRELOADED_LIBRARIES = ObjC.sel("setPreloadedLibraries:");

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
                sendPtr(MTL_4_PIPELINE_STAGE_DYNAMIC_LINKING_DESCRIPTOR, NEW));
    }

    public long maxCallStackDepth() {
        return sendLong(id, MAX_CALL_STACK_DEPTH);
    }

    @SneakyThrows
    public void setMaxCallStackDepth(long depth) {
        L.invokeExact(id, SET_MAX_CALL_STACK_DEPTH, depth);
    }

    public NSArray binaryLinkedFunctions() {
        return NSArray.of(sendPtr(id, BINARY_LINKED_FUNCTIONS));
    }

    @SneakyThrows
    public void setBinaryLinkedFunctions(NSArray functions) {
        P.invokeExact(id, SET_BINARY_LINKED_FUNCTIONS, functions.getId());
    }

    public NSArray preloadedLibraries() {
        return NSArray.of(sendPtr(id, PRELOADED_LIBRARIES));
    }

    @SneakyThrows
    public void setPreloadedLibraries(NSArray libraries) {
        P.invokeExact(id, SET_PRELOADED_LIBRARIES, libraries.getId());
    }
}
