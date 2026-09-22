package dev.dov.metalj.resources.indirect;

import dev.dov.metalj.resources.MTLResource;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIndirectCommandBuffer extends MTLResource {
    private static final long GPU_RESOURCE_ID = ObjC.sel("gpuResourceID");
    private static final long INDIRECT_COMPUTE_COMMAND_AT_INDEX = ObjC.sel("indirectComputeCommandAtIndex:");
    private static final long INDIRECT_RENDER_COMMAND_AT_INDEX = ObjC.sel("indirectRenderCommandAtIndex:");
    private static final long RESET_WITH_RANGE = ObjC.sel("resetWithRange:");
    private static final long SIZE = ObjC.sel("size");

    private static final MethodHandle R = handle(null, NSRange.LAYOUT);
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);

    private MTLIndirectCommandBuffer(long id) {
        super(id);
    }

    public static MTLIndirectCommandBuffer of(long id) {
        return new MTLIndirectCommandBuffer(id);
    }

    public long size() {
        return sendLong(id, SIZE);
    }

    public long gpuResourceID() {
        return sendLong(id, GPU_RESOURCE_ID);
    }

    @SneakyThrows
    public void resetWithRange(MemorySegment range) {
        R.invokeExact(id, RESET_WITH_RANGE, range);
    }

    @SneakyThrows
    public MTLIndirectRenderCommand indirectRenderCommandAtIndex(long index) {
        return MTLIndirectRenderCommand.of((long) P_L.invokeExact(id, INDIRECT_RENDER_COMMAND_AT_INDEX,
                index));
    }

    @SneakyThrows
    public MTLIndirectComputeCommand indirectComputeCommandAtIndex(long index) {
        return MTLIndirectComputeCommand.of((long) P_L.invokeExact(id, INDIRECT_COMPUTE_COMMAND_AT_INDEX,
                index));
    }
}
