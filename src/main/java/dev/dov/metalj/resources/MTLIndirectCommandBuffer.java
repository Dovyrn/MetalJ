package dev.dov.metalj.resources;

public class MTLIndirectCommandBuffer extends MTLResource {
    private MTLIndirectCommandBuffer(long id) {
        super(id);
    }

    public static MTLIndirectCommandBuffer of(long id) {
        return new MTLIndirectCommandBuffer(id);
    }
}
