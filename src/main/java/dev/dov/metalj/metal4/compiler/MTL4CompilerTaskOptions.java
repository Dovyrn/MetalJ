package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CompilerTaskOptions extends NSObject {
    private static final long MTL_4_COMPILER_TASK_OPTIONS = ObjC.cls("MTL4CompilerTaskOptions");

    private static final long LOOKUP_ARCHIVES = ObjC.sel("lookupArchives");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_LOOKUP_ARCHIVES = ObjC.sel("setLookupArchives:");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4CompilerTaskOptions(long id) {
        super(id);
    }

    public static MTL4CompilerTaskOptions of(long id) {
        return new MTL4CompilerTaskOptions(id);
    }

    public static MTL4CompilerTaskOptions new_() {
        return new MTL4CompilerTaskOptions(sendPtr(MTL_4_COMPILER_TASK_OPTIONS, NEW));
    }

    public NSArray lookupArchives() {
        return NSArray.of(sendPtr(id, LOOKUP_ARCHIVES));
    }

    @SneakyThrows
    public void setLookupArchives(NSArray archives) {
        P.invokeExact(id, SET_LOOKUP_ARCHIVES, archives.getId());
    }
}
