package dev.dov.metalj.arguments;

public class MTLBufferBinding extends MTLBinding {
    private MTLBufferBinding(long id) {
        super(id);
    }

    public static MTLBufferBinding of(long id) {
        return new MTLBufferBinding(id);
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
}
