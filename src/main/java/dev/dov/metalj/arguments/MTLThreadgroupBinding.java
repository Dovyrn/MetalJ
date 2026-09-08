package dev.dov.metalj.arguments;

public class MTLThreadgroupBinding extends MTLBinding {
    private MTLThreadgroupBinding(long id) {
        super(id);
    }

    public static MTLThreadgroupBinding of(long id) {
        return new MTLThreadgroupBinding(id);
    }

    public long threadgroupMemoryAlignment() {
        return sendLong(id, "threadgroupMemoryAlignment");
    }

    public long threadgroupMemoryDataSize() {
        return sendLong(id, "threadgroupMemoryDataSize");
    }
}
