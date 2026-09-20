package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4LibraryDescriptor extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4LibraryDescriptor(long id) {
        super(id);
    }

    public static MTL4LibraryDescriptor of(long id) {
        return new MTL4LibraryDescriptor(id);
    }

    public static MTL4LibraryDescriptor new_() {
        return new MTL4LibraryDescriptor(sendPtr(ObjC.cls("MTL4LibraryDescriptor"), "new"));
    }

    public NSString source() {
        return NSString.of(sendPtr(id, "source"));
    }

    @SneakyThrows
    public void setSource(NSString source) {
        P.invokeExact(id, ObjC.sel("setSource:"), source.getId());
    }

    public MTLCompileOptions options() {
        return MTLCompileOptions.of(sendPtr(id, "options"));
    }

    @SneakyThrows
    public void setOptions(MTLCompileOptions options) {
        P.invokeExact(id, ObjC.sel("setOptions:"), options.getId());
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    @SneakyThrows
    public void setName(NSString name) {
        P.invokeExact(id, ObjC.sel("setName:"), name.getId());
    }
}
