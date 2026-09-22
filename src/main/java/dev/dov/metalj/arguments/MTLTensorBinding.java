package dev.dov.metalj.arguments;
import dev.dov.metalj.objc.ObjC;


public class MTLTensorBinding extends MTLBinding {
    private static final long INDEX_TYPE = ObjC.sel("indexType");
    private static final long TENSOR_DATA_TYPE = ObjC.sel("tensorDataType");

    private MTLTensorBinding(long id) {
        super(id);
    }

    public static MTLTensorBinding of(long id) {
        return new MTLTensorBinding(id);
    }

    public long tensorDataType() {
        return sendLong(id, TENSOR_DATA_TYPE);
    }

    public long indexType() {
        return sendLong(id, INDEX_TYPE);
    }
}
