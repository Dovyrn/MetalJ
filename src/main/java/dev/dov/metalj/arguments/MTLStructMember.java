package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLStructMember extends NSObject {
    private MTLStructMember(long id) {
        super(id);
    }

    public static MTLStructMember of(long id) {
        return new MTLStructMember(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    public long offset() {
        return sendLong(id, "offset");
    }

    public long dataType() {
        return sendLong(id, "dataType");
    }

    public long argumentIndex() {
        return sendLong(id, "argumentIndex");
    }

    public MTLStructType structType() {
        return MTLStructType.of(sendPtr(id, "structType"));
    }

    public MTLArrayType arrayType() {
        return MTLArrayType.of(sendPtr(id, "arrayType"));
    }

    public MTLPointerType pointerType() {
        return MTLPointerType.of(sendPtr(id, "pointerType"));
    }

    public MTLTextureReferenceType textureReferenceType() {
        return MTLTextureReferenceType.of(sendPtr(id, "textureReferenceType"));
    }
}
