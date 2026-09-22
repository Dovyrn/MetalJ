package dev.dov.metalj.metal4;

import dev.dov.metalj.objc.Block;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Consumer;
import lombok.SneakyThrows;

public class MTL4CommitOptions extends NSObject {
    private static final long MTL_4_COMMIT_OPTIONS = ObjC.cls("MTL4CommitOptions");

    private static final long ADD_FEEDBACK_HANDLER = ObjC.sel("addFeedbackHandler:");
    private static final long NEW = ObjC.sel("new");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4CommitOptions(long id) {
        super(id);
    }

    public static MTL4CommitOptions of(long id) {
        return new MTL4CommitOptions(id);
    }

    public static MTL4CommitOptions new_() {
        return new MTL4CommitOptions(sendPtr(MTL_4_COMMIT_OPTIONS, NEW));
    }

    @SneakyThrows
    public void addFeedbackHandler(Block block) {
        P.invokeExact(id, ADD_FEEDBACK_HANDLER, block.address());
    }

    @SneakyThrows
    public static Block feedback(Consumer<MTL4CommitFeedback> done) {
        var target = MethodHandles.lookup().findStatic(MTL4CommitOptions.class, "call",
                MethodType.methodType(void.class, Consumer.class, MemorySegment.class, long.class));
        var descriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ObjC.PTR);
        return Block.of(target.bindTo(done), descriptor);
    }

    private static void call(Consumer<MTL4CommitFeedback> done, MemorySegment block, long feedback) {
        done.accept(MTL4CommitFeedback.of(feedback));
    }
}
