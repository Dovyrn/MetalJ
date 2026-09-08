package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLArgument extends NSObject {
    private MTLArgument(long id) {
        super(id);
    }

    public static MTLArgument of(long id) {
        return new MTLArgument(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    public long type() {
        return sendLong(id, "type");
    }

    public long access() {
        return sendLong(id, "access");
    }

    public long index() {
        return sendLong(id, "index");
    }

    public boolean isActive() {
        return sendBool(id, "isActive");
    }

    public long bufferAlignment() {
        return sendLong(id, "bufferAlignment");
    }

    public long bufferDataSize() {
        return sendLong(id, "bufferDataSize");
    }

    public long bufferDataType() {
        return sendLong(id, "bufferDataType");
    }

    public MTLStructType bufferStructType() {
        return MTLStructType.of(sendPtr(id, "bufferStructType"));
    }

    public MTLPointerType bufferPointerType() {
        return MTLPointerType.of(sendPtr(id, "bufferPointerType"));
    }

    public long threadgroupMemoryAlignment() {
        return sendLong(id, "threadgroupMemoryAlignment");
    }

    public long threadgroupMemoryDataSize() {
        return sendLong(id, "threadgroupMemoryDataSize");
    }

    public long textureType() {
        return sendLong(id, "textureType");
    }

    public long textureDataType() {
        return sendLong(id, "textureDataType");
    }

    public boolean isDepthTexture() {
        return sendBool(id, "isDepthTexture");
    }

    public long arrayLength() {
        return sendLong(id, "arrayLength");
    }
}
