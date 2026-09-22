package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4LibraryDescriptor extends NSObject {
    private static final long MTL_4_LIBRARY_DESCRIPTOR = ObjC.cls("MTL4LibraryDescriptor");

    private static final long NAME = ObjC.sel("name");
    private static final long NEW = ObjC.sel("new");
    private static final long OPTIONS = ObjC.sel("options");
    private static final long SET_NAME = ObjC.sel("setName:");
    private static final long SET_OPTIONS = ObjC.sel("setOptions:");
    private static final long SET_SOURCE = ObjC.sel("setSource:");
    private static final long SOURCE = ObjC.sel("source");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4LibraryDescriptor(long id) {
        super(id);
    }

    public static MTL4LibraryDescriptor of(long id) {
        return new MTL4LibraryDescriptor(id);
    }

    public static MTL4LibraryDescriptor new_() {
        return new MTL4LibraryDescriptor(sendPtr(MTL_4_LIBRARY_DESCRIPTOR, NEW));
    }

    public NSString source() {
        return NSString.of(sendPtr(id, SOURCE));
    }

    @SneakyThrows
    public void setSource(NSString source) {
        P.invokeExact(id, SET_SOURCE, source.getId());
    }

    public MTLCompileOptions options() {
        return MTLCompileOptions.of(sendPtr(id, OPTIONS));
    }

    @SneakyThrows
    public void setOptions(MTLCompileOptions options) {
        P.invokeExact(id, SET_OPTIONS, options.getId());
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    @SneakyThrows
    public void setName(NSString name) {
        P.invokeExact(id, SET_NAME, name.getId());
    }
}
