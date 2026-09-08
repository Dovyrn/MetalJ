package dev.dov.metalj.arguments;

public class MTLTextureBinding extends MTLBinding {
    private MTLTextureBinding(long id) {
        super(id);
    }

    public static MTLTextureBinding of(long id) {
        return new MTLTextureBinding(id);
    }

    public long textureType() {
        return sendLong(id, "textureType");
    }

    public long textureDataType() {
        return sendLong(id, "textureDataType");
    }

    public boolean isDepthTexture() {
        return sendBool(id, "isDepthTexture");
    }

    public long arrayLength() {
        return sendLong(id, "arrayLength");
    }
}
