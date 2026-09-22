package dev.dov.metalj.debug;

import dev.dov.metalj.objc.Block;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import lombok.SneakyThrows;

public class MTLLogState extends NSObject {
    private static final long ADD_LOG_HANDLER = ObjC.sel("addLogHandler:");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    public interface Handler {
        void log(String subSystem, String category, long level, String message);
    }

    private MTLLogState(long id) {
        super(id);
    }

    public static MTLLogState of(long id) {
        return new MTLLogState(id);
    }

    @SneakyThrows
    public static Block handler(Handler handler) {
        var target = MethodHandles.lookup().findStatic(MTLLogState.class, "call",
                MethodType.methodType(void.class, Handler.class, MemorySegment.class, long.class, long.class,
                        long.class, long.class));
        var descriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ObjC.PTR, ObjC.PTR, ObjC.LONG, ObjC.PTR);
        return Block.of(target.bindTo(handler), descriptor);
    }

    private static void call(Handler handler, MemorySegment block, long subSystem, long category, long level,
            long message) {
        handler.log(NSString.of(subSystem).UTF8String(), NSString.of(category).UTF8String(), level,
                NSString.of(message).UTF8String());
    }

    @SneakyThrows
    public void addLogHandler(Block handler) {
        P.invokeExact(id, ADD_LOG_HANDLER, handler.address());
    }
}
