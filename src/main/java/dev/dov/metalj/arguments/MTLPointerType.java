package dev.dov.metalj.arguments;
import dev.dov.metalj.objc.ObjC;


public class MTLPointerType extends MTLType {
    private static final long ACCESS = ObjC.sel("access");
    private static final long ALIGNMENT = ObjC.sel("alignment");
    private static final long DATA_SIZE = ObjC.sel("dataSize");
    private static final long ELEMENT_ARRAY_TYPE = ObjC.sel("elementArrayType");
    private static final long ELEMENT_IS_ARGUMENT_BUFFER = ObjC.sel("elementIsArgumentBuffer");
    private static final long ELEMENT_STRUCT_TYPE = ObjC.sel("elementStructType");
    private static final long ELEMENT_TYPE = ObjC.sel("elementType");

    private MTLPointerType(long id) {
        super(id);
    }

    public static MTLPointerType of(long id) {
        return new MTLPointerType(id);
    }

    public long elementType() {
        return sendLong(id, ELEMENT_TYPE);
    }

    public long access() {
        return sendLong(id, ACCESS);
    }

    public long alignment() {
        return sendLong(id, ALIGNMENT);
    }

    public long dataSize() {
        return sendLong(id, DATA_SIZE);
    }

    public boolean elementIsArgumentBuffer() {
        return sendBool(id, ELEMENT_IS_ARGUMENT_BUFFER);
    }

    public MTLStructType elementStructType() {
        return MTLStructType.of(sendPtr(id, ELEMENT_STRUCT_TYPE));
    }

    public MTLArrayType elementArrayType() {
        return MTLArrayType.of(sendPtr(id, ELEMENT_ARRAY_TYPE));
    }
}
