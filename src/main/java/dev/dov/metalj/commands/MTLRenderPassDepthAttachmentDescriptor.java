package dev.dov.metalj.commands;

import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPassDepthAttachmentDescriptor extends MTLRenderPassAttachmentDescriptor {
    private static final MethodHandle D = handle(null, ObjC.DOUBLE);
    private static final MethodHandle L = handle(null, ObjC.LONG);

    public static final long MTLMultisampleDepthResolveFilterSample0 = 0;
    public static final long MTLMultisampleDepthResolveFilterMin = 1;
    public static final long MTLMultisampleDepthResolveFilterMax = 2;

    private MTLRenderPassDepthAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLRenderPassDepthAttachmentDescriptor of(long id) {
        return new MTLRenderPassDepthAttachmentDescriptor(id);
    }

    @SneakyThrows
    public void setClearDepth(double clearDepth) {
        D.invokeExact(id, ObjC.sel("setClearDepth:"), clearDepth);
    }

    @SneakyThrows
    public void setDepthResolveFilter(long depthResolveFilter) {
        L.invokeExact(id, ObjC.sel("setDepthResolveFilter:"), depthResolveFilter);
    }
}
