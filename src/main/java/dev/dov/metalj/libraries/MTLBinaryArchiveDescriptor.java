package dev.dov.metalj.libraries;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLBinaryArchiveDescriptor extends NSObject {
    private static final long MTL_BINARY_ARCHIVE_DESCRIPTOR = ObjC.cls("MTLBinaryArchiveDescriptor");

    private static final long NEW = ObjC.sel("new");
    private static final long SET_URL = ObjC.sel("setUrl:");
    private static final long URL = ObjC.sel("url");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLBinaryArchiveDescriptor(long id) {
        super(id);
    }

    public static MTLBinaryArchiveDescriptor of(long id) {
        return new MTLBinaryArchiveDescriptor(id);
    }

    public static MTLBinaryArchiveDescriptor new_() {
        return new MTLBinaryArchiveDescriptor(sendPtr(MTL_BINARY_ARCHIVE_DESCRIPTOR, NEW));
    }

    public NSURL url() {
        return NSURL.of(sendPtr(id, URL));
    }

    @SneakyThrows
    public void setUrl(NSURL url) {
        P.invokeExact(id, SET_URL, url.getId());
    }
}
