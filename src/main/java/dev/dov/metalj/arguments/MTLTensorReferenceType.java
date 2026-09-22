package dev.dov.metalj.arguments;
import dev.dov.metalj.objc.ObjC;


public class MTLTensorReferenceType extends MTLType {
    private static final long ACCESS = ObjC.sel("access");
    private static final long DIMENSIONS = ObjC.sel("dimensions");
    private static final long INDEX_TYPE = ObjC.sel("indexType");
    private static final long TENSOR_DATA_TYPE = ObjC.sel("tensorDataType");

    private MTLTensorReferenceType(long id) {
        super(id);
    }

    public static MTLTensorReferenceType of(long id) {
        return new MTLTensorReferenceType(id);
    }

    public long tensorDataType() {
        return sendLong(id, TENSOR_DATA_TYPE);
    }

    public long indexType() {
        return sendLong(id, INDEX_TYPE);
    }

    public long dimensions() {
        return sendPtr(id, DIMENSIONS);
    }

    public long access() {
        return sendLong(id, ACCESS);
    }
}
