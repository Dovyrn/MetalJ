package dev.dov.metalj.arguments;

public class MTLTextureReferenceType extends MTLType {
    private MTLTextureReferenceType(long id) {
        super(id);
    }

    public static MTLTextureReferenceType of(long id) {
        return new MTLTextureReferenceType(id);
    }

    public long textureDataType() {
        return sendLong(id, "textureDataType");
    }

    public long textureType() {
        return sendLong(id, "textureType");
    }

    public long access() {
        return sendLong(id, "access");
    }

    public boolean isDepthTexture() {
        return sendBool(id, "isDepthTexture");
    }
}
