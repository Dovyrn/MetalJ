package dev.dov.metalj.arguments;

public class MTLArrayType extends MTLType {
    private MTLArrayType(long id) {
        super(id);
    }

    public static MTLArrayType of(long id) {
        return new MTLArrayType(id);
    }

    public long elementType() {
        return sendLong(id, "elementType");
    }

    public long arrayLength() {
        return sendLong(id, "arrayLength");
    }

    public long stride() {
        return sendLong(id, "stride");
    }

    public long argumentIndexStride() {
        return sendLong(id, "argumentIndexStride");
    }

    public MTLStructType elementStructType() {
        return MTLStructType.of(sendPtr(id, "elementStructType"));
    }

    public MTLArrayType elementArrayType() {
        return MTLArrayType.of(sendPtr(id, "elementArrayType"));
    }

    public MTLPointerType elementPointerType() {
        return MTLPointerType.of(sendPtr(id, "elementPointerType"));
    }

    public MTLTextureReferenceType elementTextureReferenceType() {
        return MTLTextureReferenceType.of(sendPtr(id, "elementTextureReferenceType"));
    }
}
