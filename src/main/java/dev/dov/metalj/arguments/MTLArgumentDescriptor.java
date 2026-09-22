package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLArgumentDescriptor extends NSObject {
    private static final long MTL_ARGUMENT_DESCRIPTOR = ObjC.cls("MTLArgumentDescriptor");

    private static final long ACCESS = ObjC.sel("access");
    private static final long ARGUMENT_DESCRIPTOR = ObjC.sel("argumentDescriptor");
    private static final long ARRAY_LENGTH = ObjC.sel("arrayLength");
    private static final long CONSTANT_BLOCK_ALIGNMENT = ObjC.sel("constantBlockAlignment");
    private static final long DATA_TYPE = ObjC.sel("dataType");
    private static final long INDEX = ObjC.sel("index");
    private static final long SET_ACCESS = ObjC.sel("setAccess:");
    private static final long SET_ARRAY_LENGTH = ObjC.sel("setArrayLength:");
    private static final long SET_CONSTANT_BLOCK_ALIGNMENT = ObjC.sel("setConstantBlockAlignment:");
    private static final long SET_DATA_TYPE = ObjC.sel("setDataType:");
    private static final long SET_INDEX = ObjC.sel("setIndex:");
    private static final long SET_TEXTURE_TYPE = ObjC.sel("setTextureType:");
    private static final long TEXTURE_TYPE = ObjC.sel("textureType");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLArgumentDescriptor(long id) {
        super(id);
    }

    public static MTLArgumentDescriptor of(long id) {
        return new MTLArgumentDescriptor(id);
    }

    public static MTLArgumentDescriptor argumentDescriptor() {
        return new MTLArgumentDescriptor(owned(() -> sendPtr(MTL_ARGUMENT_DESCRIPTOR, ARGUMENT_DESCRIPTOR)));
    }

    public long dataType() {
        return sendLong(id, DATA_TYPE);
    }

    @SneakyThrows
    public void setDataType(long dataType) {
        L.invokeExact(id, SET_DATA_TYPE, dataType);
    }

    public long index() {
        return sendLong(id, INDEX);
    }

    @SneakyThrows
    public void setIndex(long index) {
        L.invokeExact(id, SET_INDEX, index);
    }

    public long arrayLength() {
        return sendLong(id, ARRAY_LENGTH);
    }

    @SneakyThrows
    public void setArrayLength(long arrayLength) {
        L.invokeExact(id, SET_ARRAY_LENGTH, arrayLength);
    }

    public long access() {
        return sendLong(id, ACCESS);
    }

    @SneakyThrows
    public void setAccess(long access) {
        L.invokeExact(id, SET_ACCESS, access);
    }

    public long textureType() {
        return sendLong(id, TEXTURE_TYPE);
    }

    @SneakyThrows
    public void setTextureType(long textureType) {
        L.invokeExact(id, SET_TEXTURE_TYPE, textureType);
    }

    public long constantBlockAlignment() {
        return sendLong(id, CONSTANT_BLOCK_ALIGNMENT);
    }

    @SneakyThrows
    public void setConstantBlockAlignment(long alignment) {
        L.invokeExact(id, SET_CONSTANT_BLOCK_ALIGNMENT, alignment);
    }
}
