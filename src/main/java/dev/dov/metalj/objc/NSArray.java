package dev.dov.metalj.objc;

import java.lang.foreign.Arena;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class NSArray extends NSObject {
    private static final long NS_ARRAY = ObjC.cls("NSArray");

    private static final long ARRAY_WITH_OBJECTS_COUNT = ObjC.sel("arrayWithObjects:count:");
    private static final long COUNT = ObjC.sel("count");
    private static final long OBJECT_AT_INDEX = ObjC.sel("objectAtIndex:");

    private static final MethodHandle AT = handle(ObjC.PTR, ObjC.LONG);
    private static final MethodHandle WITH = handle(ObjC.PTR, ValueLayout.ADDRESS, ObjC.LONG);

    private NSArray(long id) {
        super(id);
    }

    public static NSArray of(long id) {
        return new NSArray(id);
    }

    @SneakyThrows
    public static NSArray arrayWithObjects(NSObject... objects) {
        try (var arena = Arena.ofConfined()) {
            var ids = arena.allocate(ObjC.PTR, objects.length);
            for (int i = 0; i < objects.length; i++) {
                ids.setAtIndex(ObjC.PTR, i, objects[i].getId());
            }
            long id = owned(() -> (long) WITH.invokeExact(NS_ARRAY, ARRAY_WITH_OBJECTS_COUNT,
                    ids, (long) objects.length));
            return new NSArray(id);
        }
    }

    public long count() {
        return sendLong(id, COUNT);
    }

    @SneakyThrows
    public long objectAtIndex(long index) {
        return (long) AT.invokeExact(id, OBJECT_AT_INDEX, index);
    }
}
