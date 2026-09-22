package dev.dov.metalj.arguments;
import dev.dov.metalj.objc.ObjC;


public class MTLBufferBinding extends MTLBinding {
    private static final long BUFFER_ALIGNMENT = ObjC.sel("bufferAlignment");
    private static final long BUFFER_DATA_SIZE = ObjC.sel("bufferDataSize");
    private static final long BUFFER_DATA_TYPE = ObjC.sel("bufferDataType");
    private static final long BUFFER_POINTER_TYPE = ObjC.sel("bufferPointerType");
    private static final long BUFFER_STRUCT_TYPE = ObjC.sel("bufferStructType");

    private MTLBufferBinding(long id) {
        super(id);
    }

    public static MTLBufferBinding of(long id) {
        return new MTLBufferBinding(id);
    }

    public long bufferAlignment() {
        return sendLong(id, BUFFER_ALIGNMENT);
    }

    public long bufferDataSize() {
        return sendLong(id, BUFFER_DATA_SIZE);
    }

    public long bufferDataType() {
        return sendLong(id, BUFFER_DATA_TYPE);
    }

    public MTLStructType bufferStructType() {
        return MTLStructType.of(sendPtr(id, BUFFER_STRUCT_TYPE));
    }

    public MTLPointerType bufferPointerType() {
        return MTLPointerType.of(sendPtr(id, BUFFER_POINTER_TYPE));
    }
}
