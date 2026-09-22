package dev.dov.metalj.arguments;
import dev.dov.metalj.objc.ObjC;


public class MTLThreadgroupBinding extends MTLBinding {
    private static final long THREADGROUP_MEMORY_ALIGNMENT = ObjC.sel("threadgroupMemoryAlignment");
    private static final long THREADGROUP_MEMORY_DATA_SIZE = ObjC.sel("threadgroupMemoryDataSize");

    private MTLThreadgroupBinding(long id) {
        super(id);
    }

    public static MTLThreadgroupBinding of(long id) {
        return new MTLThreadgroupBinding(id);
    }

    public long threadgroupMemoryAlignment() {
        return sendLong(id, THREADGROUP_MEMORY_ALIGNMENT);
    }

    public long threadgroupMemoryDataSize() {
        return sendLong(id, THREADGROUP_MEMORY_DATA_SIZE);
    }
}
