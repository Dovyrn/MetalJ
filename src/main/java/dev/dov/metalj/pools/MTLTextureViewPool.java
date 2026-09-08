package dev.dov.metalj.pools;

import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.textures.MTLTexture;
import dev.dov.metalj.resources.textures.MTLTextureDescriptor;
import dev.dov.metalj.resources.textures.MTLTextureViewDescriptor;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTextureViewPool extends MTLResourceViewPool {
    private static final MethodHandle L_PL = handle(ObjC.LONG, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle L_PPL = handle(ObjC.LONG, ObjC.PTR, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle L_PPLLL = handle(ObjC.LONG, ObjC.PTR, ObjC.PTR, ObjC.LONG, ObjC.LONG,
            ObjC.LONG);

    private MTLTextureViewPool(long id) {
        super(id);
    }

    public static MTLTextureViewPool of(long id) {
        return new MTLTextureViewPool(id);
    }

    @SneakyThrows
    public long setTextureView(MTLTexture texture, long index) {
        return (long) L_PL.invokeExact(id, ObjC.sel("setTextureView:atIndex:"), texture.getId(), index);
    }

    @SneakyThrows
    public long setTextureView(MTLTexture texture, MTLTextureViewDescriptor descriptor, long index) {
        return (long) L_PPL.invokeExact(id, ObjC.sel("setTextureView:descriptor:atIndex:"), texture.getId(),
                descriptor.getId(), index);
    }

    @SneakyThrows
    public long setTextureViewFromBuffer(MTLBuffer buffer, MTLTextureDescriptor descriptor, long offset,
            long bytesPerRow, long index) {
        return (long) L_PPLLL.invokeExact(id, ObjC.sel("setTextureViewFromBuffer:descriptor:offset:bytesPerRow:"
                + "atIndex:"), buffer.getId(), descriptor.getId(), offset, bytesPerRow, index);
    }
}
