package dev.dov.metalj.resources;

public class MTLTexture extends MTLResource {
    private MTLTexture(long id) {
        super(id);
    }

    public static MTLTexture of(long id) {
        return new MTLTexture(id);
    }
}
