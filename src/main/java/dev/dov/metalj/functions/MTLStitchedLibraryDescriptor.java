package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLStitchedLibraryDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLStitchedLibraryDescriptor(long id) {
        super(id);
    }

    public static MTLStitchedLibraryDescriptor of(long id) {
        return new MTLStitchedLibraryDescriptor(id);
    }

    public static MTLStitchedLibraryDescriptor new_() {
        return new MTLStitchedLibraryDescriptor(sendPtr(ObjC.cls("MTLStitchedLibraryDescriptor"), "new"));
    }

    public NSArray functionGraphs() {
        return NSArray.of(sendPtr(id, "functionGraphs"));
    }

    @SneakyThrows
    public void setFunctionGraphs(NSArray graphs) {
        P.invokeExact(id, ObjC.sel("setFunctionGraphs:"), graphs.getId());
    }

    public NSArray functions() {
        return NSArray.of(sendPtr(id, "functions"));
    }

    @SneakyThrows
    public void setFunctions(NSArray functions) {
        P.invokeExact(id, ObjC.sel("setFunctions:"), functions.getId());
    }

    public NSArray binaryArchives() {
        return NSArray.of(sendPtr(id, "binaryArchives"));
    }

    @SneakyThrows
    public void setBinaryArchives(NSArray archives) {
        P.invokeExact(id, ObjC.sel("setBinaryArchives:"), archives.getId());
    }

    public long options() {
        return sendLong(id, "options");
    }

    @SneakyThrows
    public void setOptions(long options) {
        L.invokeExact(id, ObjC.sel("setOptions:"), options);
    }
}
