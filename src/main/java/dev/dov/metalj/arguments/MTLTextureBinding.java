package dev.dov.metalj.arguments;
import dev.dov.metalj.objc.ObjC;


public class MTLTextureBinding extends MTLBinding {
    private static final long ARRAY_LENGTH = ObjC.sel("arrayLength");
    private static final long IS_DEPTH_TEXTURE = ObjC.sel("isDepthTexture");
    private static final long TEXTURE_DATA_TYPE = ObjC.sel("textureDataType");
    private static final long TEXTURE_TYPE = ObjC.sel("textureType");

    private MTLTextureBinding(long id) {
        super(id);
    }

    public static MTLTextureBinding of(long id) {
        return new MTLTextureBinding(id);
    }

    public long textureType() {
        return sendLong(id, TEXTURE_TYPE);
    }

    public long textureDataType() {
        return sendLong(id, TEXTURE_DATA_TYPE);
    }

    public boolean isDepthTexture() {
        return sendBool(id, IS_DEPTH_TEXTURE);
    }

    public long arrayLength() {
        return sendLong(id, ARRAY_LENGTH);
    }
}
