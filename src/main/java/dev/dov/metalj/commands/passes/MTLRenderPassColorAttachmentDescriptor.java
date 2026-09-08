package dev.dov.metalj.commands.passes;

import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPassColorAttachmentDescriptor extends MTLRenderPassAttachmentDescriptor {
    private static final MethodHandle COLOR = handle(null, MTLClearColor.LAYOUT);

    private MTLRenderPassColorAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLRenderPassColorAttachmentDescriptor of(long id) {
        return new MTLRenderPassColorAttachmentDescriptor(id);
    }

    @SneakyThrows
    public void setClearColor(MemorySegment clearColor) {
        COLOR.invokeExact(id, ObjC.sel("setClearColor:"), clearColor);
    }
}
