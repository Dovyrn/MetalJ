package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTileRenderPipelineColorAttachmentDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLTileRenderPipelineColorAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLTileRenderPipelineColorAttachmentDescriptor of(long id) {
        return new MTLTileRenderPipelineColorAttachmentDescriptor(id);
    }

    public long pixelFormat() {
        return sendLong(id, "pixelFormat");
    }

    @SneakyThrows
    public void setPixelFormat(long format) {
        L.invokeExact(id, ObjC.sel("setPixelFormat:"), format);
    }
}
