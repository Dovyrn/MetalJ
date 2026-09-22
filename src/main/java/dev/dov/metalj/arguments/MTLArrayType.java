package dev.dov.metalj.arguments;
import dev.dov.metalj.objc.ObjC;


public class MTLArrayType extends MTLType {
    private static final long ARGUMENT_INDEX_STRIDE = ObjC.sel("argumentIndexStride");
    private static final long ARRAY_LENGTH = ObjC.sel("arrayLength");
    private static final long ELEMENT_ARRAY_TYPE = ObjC.sel("elementArrayType");
    private static final long ELEMENT_POINTER_TYPE = ObjC.sel("elementPointerType");
    private static final long ELEMENT_STRUCT_TYPE = ObjC.sel("elementStructType");
    private static final long ELEMENT_TEXTURE_REFERENCE_TYPE = ObjC.sel("elementTextureReferenceType");
    private static final long ELEMENT_TYPE = ObjC.sel("elementType");
    private static final long STRIDE = ObjC.sel("stride");

    private MTLArrayType(long id) {
        super(id);
    }

    public static MTLArrayType of(long id) {
        return new MTLArrayType(id);
    }

    public long elementType() {
        return sendLong(id, ELEMENT_TYPE);
    }

    public long arrayLength() {
        return sendLong(id, ARRAY_LENGTH);
    }

    public long stride() {
        return sendLong(id, STRIDE);
    }

    public long argumentIndexStride() {
        return sendLong(id, ARGUMENT_INDEX_STRIDE);
    }

    public MTLStructType elementStructType() {
        return MTLStructType.of(sendPtr(id, ELEMENT_STRUCT_TYPE));
    }

    public MTLArrayType elementArrayType() {
        return MTLArrayType.of(sendPtr(id, ELEMENT_ARRAY_TYPE));
    }

    public MTLPointerType elementPointerType() {
        return MTLPointerType.of(sendPtr(id, ELEMENT_POINTER_TYPE));
    }

    public MTLTextureReferenceType elementTextureReferenceType() {
        return MTLTextureReferenceType.of(sendPtr(id, ELEMENT_TEXTURE_REFERENCE_TYPE));
    }
}
