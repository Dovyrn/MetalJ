package dev.dov.metalj.device;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.textures.MTLTexture;

public class CAMetalDrawable extends MTLDrawable {
    private static final long LAYER = ObjC.sel("layer");
    private static final long TEXTURE = ObjC.sel("texture");

    private CAMetalDrawable(long id) {
        super(id);
    }

    public static CAMetalDrawable of(long id) {
        return new CAMetalDrawable(id);
    }

    public MTLTexture texture() {
        return MTLTexture.of(sendPtr(id, TEXTURE));
    }

    public CAMetalLayer layer() {
        return CAMetalLayer.of(sendPtr(id, LAYER));
    }
}
