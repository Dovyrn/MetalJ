package dev.dov.metalj.arguments;
import dev.dov.metalj.objc.ObjC;


public class MTLObjectPayloadBinding extends MTLBinding {
    private static final long OBJECT_PAYLOAD_ALIGNMENT = ObjC.sel("objectPayloadAlignment");
    private static final long OBJECT_PAYLOAD_DATA_SIZE = ObjC.sel("objectPayloadDataSize");

    private MTLObjectPayloadBinding(long id) {
        super(id);
    }

    public static MTLObjectPayloadBinding of(long id) {
        return new MTLObjectPayloadBinding(id);
    }

    public long objectPayloadAlignment() {
        return sendLong(id, OBJECT_PAYLOAD_ALIGNMENT);
    }

    public long objectPayloadDataSize() {
        return sendLong(id, OBJECT_PAYLOAD_DATA_SIZE);
    }
}
