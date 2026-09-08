package dev.dov.metalj.arguments;

public class MTLTensorBinding extends MTLBinding {
    private MTLTensorBinding(long id) {
        super(id);
    }

    public static MTLTensorBinding of(long id) {
        return new MTLTensorBinding(id);
    }

    public long tensorDataType() {
        return sendLong(id, "tensorDataType");
    }

    public long indexType() {
        return sendLong(id, "indexType");
    }
}
