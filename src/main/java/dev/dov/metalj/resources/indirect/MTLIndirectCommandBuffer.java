package dev.dov.metalj.resources.indirect;

import dev.dov.metalj.resources.MTLResource;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIndirectCommandBuffer extends MTLResource {
    private static final MethodHandle R = handle(null, NSRange.LAYOUT);
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);

    private MTLIndirectCommandBuffer(long id) {
        super(id);
    }

    public static MTLIndirectCommandBuffer of(long id) {
        return new MTLIndirectCommandBuffer(id);
    }

    public long size() {
        return sendLong(id, "size");
    }

    public long gpuResourceID() {
        return sendLong(id, "gpuResourceID");
    }

    @SneakyThrows
    public void resetWithRange(MemorySegment range) {
        R.invokeExact(id, ObjC.sel("resetWithRange:"), range);
    }

    @SneakyThrows
    public MTLIndirectRenderCommand indirectRenderCommandAtIndex(long index) {
        return MTLIndirectRenderCommand.of((long) P_L.invokeExact(id, ObjC.sel("indirectRenderCommandAtIndex:"),
                index));
    }

    @SneakyThrows
    public MTLIndirectComputeCommand indirectComputeCommandAtIndex(long index) {
        return MTLIndirectComputeCommand.of((long) P_L.invokeExact(id, ObjC.sel("indirectComputeCommandAtIndex:"),
                index));
    }
}
