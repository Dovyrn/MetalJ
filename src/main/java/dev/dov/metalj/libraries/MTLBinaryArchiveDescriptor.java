package dev.dov.metalj.libraries;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLBinaryArchiveDescriptor extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLBinaryArchiveDescriptor(long id) {
        super(id);
    }

    public static MTLBinaryArchiveDescriptor of(long id) {
        return new MTLBinaryArchiveDescriptor(id);
    }

    public static MTLBinaryArchiveDescriptor new_() {
        return new MTLBinaryArchiveDescriptor(sendPtr(ObjC.cls("MTLBinaryArchiveDescriptor"), "new"));
    }

    public NSURL url() {
        return NSURL.of(sendPtr(id, "url"));
    }

    @SneakyThrows
    public void setUrl(NSURL url) {
        P.invokeExact(id, ObjC.sel("setUrl:"), url.getId());
    }
}
