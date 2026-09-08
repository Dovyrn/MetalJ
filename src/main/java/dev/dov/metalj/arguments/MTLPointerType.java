package dev.dov.metalj.arguments;

public class MTLPointerType extends MTLType {
    private MTLPointerType(long id) {
        super(id);
    }

    public static MTLPointerType of(long id) {
        return new MTLPointerType(id);
    }

    public long elementType() {
        return sendLong(id, "elementType");
    }

    public long access() {
        return sendLong(id, "access");
    }

    public long alignment() {
        return sendLong(id, "alignment");
    }

    public long dataSize() {
        return sendLong(id, "dataSize");
    }

    public boolean elementIsArgumentBuffer() {
        return sendBool(id, "elementIsArgumentBuffer");
    }

    public MTLStructType elementStructType() {
        return MTLStructType.of(sendPtr(id, "elementStructType"));
    }

    public MTLArrayType elementArrayType() {
        return MTLArrayType.of(sendPtr(id, "elementArrayType"));
    }
}
