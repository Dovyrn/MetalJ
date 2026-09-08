package dev.dov.metalj.resources;

public class MTLBuffer extends MTLResource {
    private MTLBuffer(long id) {
        super(id);
    }

    public static MTLBuffer of(long id) {
        return new MTLBuffer(id);
    }
}
