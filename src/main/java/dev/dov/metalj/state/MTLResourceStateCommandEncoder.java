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
    private static final long MOVE_TEXTURE_MAPPINGS_FROM_TEXTURE_SOURCE_SLICE_SOURCE_LEVEL_SOURCE_ORIGIN_SOURCE_SIZE_TO_TEXTURE_DESTINATION_SLICE_DESTINATION_LEVEL_DESTINATION_ORIGIN = ObjC.sel("moveTextureMappingsFromTexture:sourceSlice:sourceLevel:sourceOrigin:"
                + "sourceSize:toTexture:destinationSlice:destinationLevel:destinationOrigin:");

    private static final long UPDATE_FENCE = ObjC.sel("updateFence:");
    private static final long UPDATE_TEXTURE_MAPPING_MODE_INDIRECT_BUFFER_INDIRECT_BUFFER_OFFSET = ObjC.sel("updateTextureMapping:mode:indirectBuffer:indirectBufferOffset:");
    private static final long UPDATE_TEXTURE_MAPPING_MODE_REGION_MIP_LEVEL_SLICE = ObjC.sel("updateTextureMapping:mode:region:mipLevel:slice:");
    private static final long UPDATE_TEXTURE_MAPPINGS_MODE_REGIONS_MIP_LEVELS_SLICES_NUM_REGIONS = ObjC.sel("updateTextureMappings:mode:regions:mipLevels:slices:numRegions:");
    private static final long WAIT_FOR_FENCE = ObjC.sel("waitForFence:");

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
        MANY.invokeExact(id, UPDATE_TEXTURE_MAPPINGS_MODE_REGIONS_MIP_LEVELS_SLICES_NUM_REGIONS,
                texture.getId(), mode, regions, levels, slices, count);
    }

    @SneakyThrows
    public void updateTextureMapping(MTLTexture texture, long mode, MemorySegment region, long level, long slice) {
        ONE.invokeExact(id, UPDATE_TEXTURE_MAPPING_MODE_REGION_MIP_LEVEL_SLICE, texture.getId(), mode,
                region, level, slice);
    }

    @SneakyThrows
    public void updateTextureMapping(MTLTexture texture, long mode, MTLBuffer indirectBuffer, long offset) {
        INDIRECT.invokeExact(id, UPDATE_TEXTURE_MAPPING_MODE_INDIRECT_BUFFER_INDIRECT_BUFFER_OFFSET,
                texture.getId(), mode, indirectBuffer.getId(), offset);
    }

    @SneakyThrows
    public void moveTextureMappingsFromTexture(MTLTexture source, long sourceSlice, long sourceLevel,
            MemorySegment sourceOrigin, MemorySegment sourceSize, MTLTexture destination, long destinationSlice,
            long destinationLevel, MemorySegment destinationOrigin) {
        MOVE.invokeExact(id, MOVE_TEXTURE_MAPPINGS_FROM_TEXTURE_SOURCE_SLICE_SOURCE_LEVEL_SOURCE_ORIGIN_SOURCE_SIZE_TO_TEXTURE_DESTINATION_SLICE_DESTINATION_LEVEL_DESTINATION_ORIGIN, source.getId(),
                sourceSlice, sourceLevel, sourceOrigin, sourceSize, destination.getId(), destinationSlice,
                destinationLevel, destinationOrigin);
    }

    @SneakyThrows
    public void updateFence(MTLFence fence) {
        P.invokeExact(id, UPDATE_FENCE, fence.getId());
    }

    @SneakyThrows
    public void waitForFence(MTLFence fence) {
        P.invokeExact(id, WAIT_FOR_FENCE, fence.getId());
    }
}
