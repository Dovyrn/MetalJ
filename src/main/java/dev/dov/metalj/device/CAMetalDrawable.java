package dev.dov.metalj.device;

import dev.dov.metalj.resources.textures.MTLTexture;

public class CAMetalDrawable extends MTLDrawable {
    private CAMetalDrawable(long id) {
        super(id);
    }

    public static CAMetalDrawable of(long id) {
        return new CAMetalDrawable(id);
    }

    public MTLTexture texture() {
        return MTLTexture.of(sendPtr(id, "texture"));
    }

    public CAMetalLayer layer() {
        return CAMetalLayer.of(sendPtr(id, "layer"));
    }
}
