package dev.dov.metalj.commands.passes;

import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPassDepthAttachmentDescriptor extends MTLRenderPassAttachmentDescriptor {
    private static final long SET_CLEAR_DEPTH = ObjC.sel("setClearDepth:");
    private static final long SET_DEPTH_RESOLVE_FILTER = ObjC.sel("setDepthResolveFilter:");

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
        D.invokeExact(id, SET_CLEAR_DEPTH, clearDepth);
    }

    @SneakyThrows
    public void setDepthResolveFilter(long depthResolveFilter) {
        L.invokeExact(id, SET_DEPTH_RESOLVE_FILTER, depthResolveFilter);
    }
}
