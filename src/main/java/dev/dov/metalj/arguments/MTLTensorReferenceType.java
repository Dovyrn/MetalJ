package dev.dov.metalj.arguments;

public class MTLTensorReferenceType extends MTLType {
    private MTLTensorReferenceType(long id) {
        super(id);
    }

    public static MTLTensorReferenceType of(long id) {
        return new MTLTensorReferenceType(id);
    }

    public long tensorDataType() {
        return sendLong(id, "tensorDataType");
    }

    public long indexType() {
        return sendLong(id, "indexType");
    }

    public long dimensions() {
        return sendPtr(id, "dimensions");
    }

    public long access() {
        return sendLong(id, "access");
    }
}
