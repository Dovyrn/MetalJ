package dev.dov.metalj.functions;

import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLFunctionStitchingInputNode extends MTLFunctionStitchingNode {
    private static final long ARGUMENT_INDEX = ObjC.sel("argumentIndex");
    private static final long INIT_WITH_ARGUMENT_INDEX = ObjC.sel("initWithArgumentIndex:");
    private static final long SET_ARGUMENT_INDEX = ObjC.sel("setArgumentIndex:");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);

    private MTLFunctionStitchingInputNode(long id) {
        super(id);
    }

    public static MTLFunctionStitchingInputNode of(long id) {
        return new MTLFunctionStitchingInputNode(id);
    }

    @SneakyThrows
    public static MTLFunctionStitchingInputNode initWithArgumentIndex(long index) {
        long id = (long) P_L.invokeExact(alloc("MTLFunctionStitchingInputNode"),
                INIT_WITH_ARGUMENT_INDEX, index);
        return new MTLFunctionStitchingInputNode(id);
    }

    public long argumentIndex() {
        return sendLong(id, ARGUMENT_INDEX);
    }

    @SneakyThrows
    public void setArgumentIndex(long index) {
        L.invokeExact(id, SET_ARGUMENT_INDEX, index);
    }
}
