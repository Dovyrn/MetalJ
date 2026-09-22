package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLLibrary;
import lombok.SneakyThrows;

public class MTL4LibraryFunctionDescriptor extends MTL4FunctionDescriptor {
    private static final long MTL_4_LIBRARY_FUNCTION_DESCRIPTOR = ObjC.cls("MTL4LibraryFunctionDescriptor");

    private static final long LIBRARY = ObjC.sel("library");
    private static final long NAME = ObjC.sel("name");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_LIBRARY = ObjC.sel("setLibrary:");
    private static final long SET_NAME = ObjC.sel("setName:");

    private MTL4LibraryFunctionDescriptor(long id) {
        super(id);
    }

    public static MTL4LibraryFunctionDescriptor of(long id) {
        return new MTL4LibraryFunctionDescriptor(id);
    }

    public static MTL4LibraryFunctionDescriptor new_() {
        return new MTL4LibraryFunctionDescriptor(sendPtr(MTL_4_LIBRARY_FUNCTION_DESCRIPTOR, NEW));
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    @SneakyThrows
    public void setName(NSString name) {
        P.invokeExact(id, SET_NAME, name.getId());
    }

    public MTLLibrary library() {
        return MTLLibrary.of(sendPtr(id, LIBRARY));
    }

    @SneakyThrows
    public void setLibrary(MTLLibrary library) {
        P.invokeExact(id, SET_LIBRARY, library.getId());
    }
}
