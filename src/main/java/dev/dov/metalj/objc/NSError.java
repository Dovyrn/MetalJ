package dev.dov.metalj.objc;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public class NSError extends NSObject {
    private static final long LOCALIZED_DESCRIPTION = ObjC.sel("localizedDescription");

    private NSError(long id) {
        super(id);
    }

    public static NSError of(long id) {
        return new NSError(id);
    }

    public static MemorySegment slot(Arena arena) {
        return arena.allocate(ObjC.PTR);
    }

    public static void check(MemorySegment slot, String what) {
        long id = slot.get(ObjC.PTR, 0);
        if (id != 0) {
            throw new IllegalStateException(what + ": "
                    + drained(() -> new NSError(id).localizedDescription().UTF8String()));
        }
    }

    public NSString localizedDescription() {
        return NSString.of(sendPtr(id, LOCALIZED_DESCRIPTION));
    }
}
