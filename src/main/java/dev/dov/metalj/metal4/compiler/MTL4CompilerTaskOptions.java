package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CompilerTaskOptions extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4CompilerTaskOptions(long id) {
        super(id);
    }

    public static MTL4CompilerTaskOptions of(long id) {
        return new MTL4CompilerTaskOptions(id);
    }

    public static MTL4CompilerTaskOptions new_() {
        return new MTL4CompilerTaskOptions(sendPtr(ObjC.cls("MTL4CompilerTaskOptions"), "new"));
    }

    public NSArray lookupArchives() {
        return NSArray.of(sendPtr(id, "lookupArchives"));
    }

    @SneakyThrows
    public void setLookupArchives(NSArray archives) {
        P.invokeExact(id, ObjC.sel("setLookupArchives:"), archives.getId());
    }
}
