package dev.dov.metalj.arguments;
import dev.dov.metalj.objc.ObjC;


public class MTLTextureReferenceType extends MTLType {
    private static final long ACCESS = ObjC.sel("access");
    private static final long IS_DEPTH_TEXTURE = ObjC.sel("isDepthTexture");
    private static final long TEXTURE_DATA_TYPE = ObjC.sel("textureDataType");
    private static final long TEXTURE_TYPE = ObjC.sel("textureType");

    private MTLTextureReferenceType(long id) {
        super(id);
    }

    public static MTLTextureReferenceType of(long id) {
        return new MTLTextureReferenceType(id);
    }

    public long textureDataType() {
        return sendLong(id, TEXTURE_DATA_TYPE);
    }

    public long textureType() {
        return sendLong(id, TEXTURE_TYPE);
    }

    public long access() {
        return sendLong(id, ACCESS);
    }

    public boolean isDepthTexture() {
        return sendBool(id, IS_DEPTH_TEXTURE);
    }
}
