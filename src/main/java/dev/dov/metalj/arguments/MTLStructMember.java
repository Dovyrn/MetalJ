package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLStructMember extends NSObject {
    private static final long ARGUMENT_INDEX = ObjC.sel("argumentIndex");
    private static final long ARRAY_TYPE = ObjC.sel("arrayType");
    private static final long DATA_TYPE = ObjC.sel("dataType");
    private static final long NAME = ObjC.sel("name");
    private static final long OFFSET = ObjC.sel("offset");
    private static final long POINTER_TYPE = ObjC.sel("pointerType");
    private static final long STRUCT_TYPE = ObjC.sel("structType");
    private static final long TEXTURE_REFERENCE_TYPE = ObjC.sel("textureReferenceType");

    private MTLStructMember(long id) {
        super(id);
    }

    public static MTLStructMember of(long id) {
        return new MTLStructMember(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    public long offset() {
        return sendLong(id, OFFSET);
    }

    public long dataType() {
        return sendLong(id, DATA_TYPE);
    }

    public long argumentIndex() {
        return sendLong(id, ARGUMENT_INDEX);
    }

    public MTLStructType structType() {
        return MTLStructType.of(sendPtr(id, STRUCT_TYPE));
    }

    public MTLArrayType arrayType() {
        return MTLArrayType.of(sendPtr(id, ARRAY_TYPE));
    }

    public MTLPointerType pointerType() {
        return MTLPointerType.of(sendPtr(id, POINTER_TYPE));
    }

    public MTLTextureReferenceType textureReferenceType() {
        return MTLTextureReferenceType.of(sendPtr(id, TEXTURE_REFERENCE_TYPE));
    }
}
