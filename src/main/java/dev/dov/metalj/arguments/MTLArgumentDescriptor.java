package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLArgumentDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLArgumentDescriptor(long id) {
        super(id);
    }

    public static MTLArgumentDescriptor of(long id) {
        return new MTLArgumentDescriptor(id);
    }

    public static MTLArgumentDescriptor argumentDescriptor() {
        return new MTLArgumentDescriptor(owned(() -> sendPtr(ObjC.cls("MTLArgumentDescriptor"), "argumentDescriptor")));
    }

    public long dataType() {
        return sendLong(id, "dataType");
    }

    @SneakyThrows
    public void setDataType(long dataType) {
        L.invokeExact(id, ObjC.sel("setDataType:"), dataType);
    }

    public long index() {
        return sendLong(id, "index");
    }

    @SneakyThrows
    public void setIndex(long index) {
        L.invokeExact(id, ObjC.sel("setIndex:"), index);
    }

    public long arrayLength() {
        return sendLong(id, "arrayLength");
    }

    @SneakyThrows
    public void setArrayLength(long arrayLength) {
        L.invokeExact(id, ObjC.sel("setArrayLength:"), arrayLength);
    }

    public long access() {
        return sendLong(id, "access");
    }

    @SneakyThrows
    public void setAccess(long access) {
        L.invokeExact(id, ObjC.sel("setAccess:"), access);
    }

    public long textureType() {
        return sendLong(id, "textureType");
    }

    @SneakyThrows
    public void setTextureType(long textureType) {
        L.invokeExact(id, ObjC.sel("setTextureType:"), textureType);
    }

    public long constantBlockAlignment() {
        return sendLong(id, "constantBlockAlignment");
    }

    @SneakyThrows
    public void setConstantBlockAlignment(long alignment) {
        L.invokeExact(id, ObjC.sel("setConstantBlockAlignment:"), alignment);
    }
}
