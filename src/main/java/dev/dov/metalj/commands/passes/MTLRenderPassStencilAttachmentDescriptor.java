package dev.dov.metalj.commands.passes;

import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPassStencilAttachmentDescriptor extends MTLRenderPassAttachmentDescriptor {
    private static final MethodHandle I = handle(null, ObjC.INT);
    private static final MethodHandle L = handle(null, ObjC.LONG);

    public static final long MTLMultisampleStencilResolveFilterSample0 = 0;
    public static final long MTLMultisampleStencilResolveFilterDepthResolvedSample = 1;

    private MTLRenderPassStencilAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLRenderPassStencilAttachmentDescriptor of(long id) {
        return new MTLRenderPassStencilAttachmentDescriptor(id);
    }

    @SneakyThrows
    public void setClearStencil(int clearStencil) {
        I.invokeExact(id, ObjC.sel("setClearStencil:"), clearStencil);
    }

    @SneakyThrows
    public void setStencilResolveFilter(long stencilResolveFilter) {
        L.invokeExact(id, ObjC.sel("setStencilResolveFilter:"), stencilResolveFilter);
    }
}
