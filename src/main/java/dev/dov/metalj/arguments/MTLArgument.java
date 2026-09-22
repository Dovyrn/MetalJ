package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLArgument extends NSObject {
    private static final long ACCESS = ObjC.sel("access");
    private static final long ARRAY_LENGTH = ObjC.sel("arrayLength");
    private static final long BUFFER_ALIGNMENT = ObjC.sel("bufferAlignment");
    private static final long BUFFER_DATA_SIZE = ObjC.sel("bufferDataSize");
    private static final long BUFFER_DATA_TYPE = ObjC.sel("bufferDataType");
    private static final long BUFFER_POINTER_TYPE = ObjC.sel("bufferPointerType");
    private static final long BUFFER_STRUCT_TYPE = ObjC.sel("bufferStructType");
    private static final long INDEX = ObjC.sel("index");
    private static final long IS_ACTIVE = ObjC.sel("isActive");
    private static final long IS_DEPTH_TEXTURE = ObjC.sel("isDepthTexture");
    private static final long NAME = ObjC.sel("name");
    private static final long TEXTURE_DATA_TYPE = ObjC.sel("textureDataType");
    private static final long TEXTURE_TYPE = ObjC.sel("textureType");
    private static final long THREADGROUP_MEMORY_ALIGNMENT = ObjC.sel("threadgroupMemoryAlignment");
    private static final long THREADGROUP_MEMORY_DATA_SIZE = ObjC.sel("threadgroupMemoryDataSize");
    private static final long TYPE = ObjC.sel("type");

    private MTLArgument(long id) {
        super(id);
    }

    public static MTLArgument of(long id) {
        return new MTLArgument(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    public long type() {
        return sendLong(id, TYPE);
    }

    public long access() {
        return sendLong(id, ACCESS);
    }

    public long index() {
        return sendLong(id, INDEX);
    }

    public boolean isActive() {
        return sendBool(id, IS_ACTIVE);
    }

    public long bufferAlignment() {
        return sendLong(id, BUFFER_ALIGNMENT);
    }

    public long bufferDataSize() {
        return sendLong(id, BUFFER_DATA_SIZE);
    }

    public long bufferDataType() {
        return sendLong(id, BUFFER_DATA_TYPE);
    }

    public MTLStructType bufferStructType() {
        return MTLStructType.of(sendPtr(id, BUFFER_STRUCT_TYPE));
    }

    public MTLPointerType bufferPointerType() {
        return MTLPointerType.of(sendPtr(id, BUFFER_POINTER_TYPE));
    }

    public long threadgroupMemoryAlignment() {
        return sendLong(id, THREADGROUP_MEMORY_ALIGNMENT);
    }

    public long threadgroupMemoryDataSize() {
        return sendLong(id, THREADGROUP_MEMORY_DATA_SIZE);
    }

    public long textureType() {
        return sendLong(id, TEXTURE_TYPE);
    }

    public long textureDataType() {
        return sendLong(id, TEXTURE_DATA_TYPE);
    }

    public boolean isDepthTexture() {
        return sendBool(id, IS_DEPTH_TEXTURE);
    }

    public long arrayLength() {
        return sendLong(id, ARRAY_LENGTH);
    }
}
