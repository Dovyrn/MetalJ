package dev.dov.metalj.state;

import dev.dov.metalj.commands.encoders.MTLCommandEncoder;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLOrigin;
import dev.dov.metalj.resources.MTLRegion;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.textures.MTLTexture;
import dev.dov.metalj.sync.MTLFence;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLResourceStateCommandEncoder extends MTLCommandEncoder {
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle MANY = handle(null, ObjC.PTR, ObjC.LONG, ValueLayout.ADDRESS,
            ValueLayout.ADDRESS, ValueLayout.ADDRESS, ObjC.LONG);
    private static final MethodHandle ONE = handle(null, ObjC.PTR, ObjC.LONG, MTLRegion.LAYOUT, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle INDIRECT = handle(null, ObjC.PTR, ObjC.LONG, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle MOVE = handle(null, ObjC.PTR, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT,
            MTLSize.LAYOUT, ObjC.PTR, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT);

    private MTLResourceStateCommandEncoder(long id) {
        super(id);
    }

    public static MTLResourceStateCommandEncoder of(long id) {
        return new MTLResourceStateCommandEncoder(id);
    }

    @SneakyThrows
    public void updateTextureMappings(MTLTexture texture, long mode, MemorySegment regions, MemorySegment levels,
            MemorySegment slices, long count) {
        MANY.invokeExact(id, ObjC.sel("updateTextureMappings:mode:regions:mipLevels:slices:numRegions:"),
                texture.getId(), mode, regions, levels, slices, count);
    }

    @SneakyThrows
    public void updateTextureMapping(MTLTexture texture, long mode, MemorySegment region, long level, long slice) {
        ONE.invokeExact(id, ObjC.sel("updateTextureMapping:mode:region:mipLevel:slice:"), texture.getId(), mode,
                region, level, slice);
    }

    @SneakyThrows
    public void updateTextureMapping(MTLTexture texture, long mode, MTLBuffer indirectBuffer, long offset) {
        INDIRECT.invokeExact(id, ObjC.sel("updateTextureMapping:mode:indirectBuffer:indirectBufferOffset:"),
                texture.getId(), mode, indirectBuffer.getId(), offset);
    }

    @SneakyThrows
    public void moveTextureMappingsFromTexture(MTLTexture source, long sourceSlice, long sourceLevel,
            MemorySegment sourceOrigin, MemorySegment sourceSize, MTLTexture destination, long destinationSlice,
            long destinationLevel, MemorySegment destinationOrigin) {
        MOVE.invokeExact(id, ObjC.sel("moveTextureMappingsFromTexture:sourceSlice:sourceLevel:sourceOrigin:"
                + "sourceSize:toTexture:destinationSlice:destinationLevel:destinationOrigin:"), source.getId(),
                sourceSlice, sourceLevel, sourceOrigin, sourceSize, destination.getId(), destinationSlice,
                destinationLevel, destinationOrigin);
    }

    @SneakyThrows
    public void updateFence(MTLFence fence) {
        P.invokeExact(id, ObjC.sel("updateFence:"), fence.getId());
    }

    @SneakyThrows
    public void waitForFence(MTLFence fence) {
        P.invokeExact(id, ObjC.sel("waitForFence:"), fence.getId());
    }
}
