package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLLibrary;
import lombok.SneakyThrows;

public class MTL4LibraryFunctionDescriptor extends MTL4FunctionDescriptor {
    private MTL4LibraryFunctionDescriptor(long id) {
        super(id);
    }

    public static MTL4LibraryFunctionDescriptor of(long id) {
        return new MTL4LibraryFunctionDescriptor(id);
    }

    public static MTL4LibraryFunctionDescriptor new_() {
        return new MTL4LibraryFunctionDescriptor(sendPtr(ObjC.cls("MTL4LibraryFunctionDescriptor"), "new"));
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    @SneakyThrows
    public void setName(NSString name) {
        P.invokeExact(id, ObjC.sel("setName:"), name.getId());
    }

    public MTLLibrary library() {
        return MTLLibrary.of(sendPtr(id, "library"));
    }

    @SneakyThrows
    public void setLibrary(MTLLibrary library) {
        P.invokeExact(id, ObjC.sel("setLibrary:"), library.getId());
    }
}
