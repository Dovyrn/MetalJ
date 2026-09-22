package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLStitchedLibraryDescriptor extends NSObject {
    private static final long MTL_STITCHED_LIBRARY_DESCRIPTOR = ObjC.cls("MTLStitchedLibraryDescriptor");

    private static final long BINARY_ARCHIVES = ObjC.sel("binaryArchives");
    private static final long FUNCTION_GRAPHS = ObjC.sel("functionGraphs");
    private static final long FUNCTIONS = ObjC.sel("functions");
    private static final long NEW = ObjC.sel("new");
    private static final long OPTIONS = ObjC.sel("options");
    private static final long SET_BINARY_ARCHIVES = ObjC.sel("setBinaryArchives:");
    private static final long SET_FUNCTION_GRAPHS = ObjC.sel("setFunctionGraphs:");
    private static final long SET_FUNCTIONS = ObjC.sel("setFunctions:");
    private static final long SET_OPTIONS = ObjC.sel("setOptions:");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLStitchedLibraryDescriptor(long id) {
        super(id);
    }

    public static MTLStitchedLibraryDescriptor of(long id) {
        return new MTLStitchedLibraryDescriptor(id);
    }

    public static MTLStitchedLibraryDescriptor new_() {
        return new MTLStitchedLibraryDescriptor(sendPtr(MTL_STITCHED_LIBRARY_DESCRIPTOR, NEW));
    }

    public NSArray functionGraphs() {
        return NSArray.of(sendPtr(id, FUNCTION_GRAPHS));
    }

    @SneakyThrows
    public void setFunctionGraphs(NSArray graphs) {
        P.invokeExact(id, SET_FUNCTION_GRAPHS, graphs.getId());
    }

    public NSArray functions() {
        return NSArray.of(sendPtr(id, FUNCTIONS));
    }

    @SneakyThrows
    public void setFunctions(NSArray functions) {
        P.invokeExact(id, SET_FUNCTIONS, functions.getId());
    }

    public NSArray binaryArchives() {
        return NSArray.of(sendPtr(id, BINARY_ARCHIVES));
    }

    @SneakyThrows
    public void setBinaryArchives(NSArray archives) {
        P.invokeExact(id, SET_BINARY_ARCHIVES, archives.getId());
    }

    public long options() {
        return sendLong(id, OPTIONS);
    }

    @SneakyThrows
    public void setOptions(long options) {
        L.invokeExact(id, SET_OPTIONS, options);
    }
}
