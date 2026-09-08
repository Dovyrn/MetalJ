package dev.dov.metalj.arguments;

public class MTLObjectPayloadBinding extends MTLBinding {
    private MTLObjectPayloadBinding(long id) {
        super(id);
    }

    public static MTLObjectPayloadBinding of(long id) {
        return new MTLObjectPayloadBinding(id);
    }

    public long objectPayloadAlignment() {
        return sendLong(id, "objectPayloadAlignment");
    }

    public long objectPayloadDataSize() {
        return sendLong(id, "objectPayloadDataSize");
    }
}
