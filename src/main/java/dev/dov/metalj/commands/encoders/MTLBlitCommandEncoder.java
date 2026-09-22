package dev.dov.metalj.commands.encoders;

import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.sync.MTLFence;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.indirect.MTLIndirectCommandBuffer;
import dev.dov.metalj.resources.MTLOrigin;
import dev.dov.metalj.resources.MTLResource;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.textures.MTLTexture;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLBlitCommandEncoder extends MTLCommandEncoder {
    private static final long COPY_FROM_BUFFER_SOURCE_OFFSET_TO_BUFFER_DESTINATION_OFFSET_SIZE = ObjC.sel("copyFromBuffer:sourceOffset:toBuffer:destinationOffset:size:");
    private static final long COPY_FROM_TEXTURE_TO_TEXTURE = ObjC.sel("copyFromTexture:toTexture:");
    private static final long COPY_INDIRECT_COMMAND_BUFFER_SOURCE_RANGE_DESTINATION_DESTINATION_INDEX = ObjC.sel("copyIndirectCommandBuffer:sourceRange:destination:destinationIndex:");
    private static final long FILL_BUFFER_RANGE_VALUE = ObjC.sel("fillBuffer:range:value:");
    private static final long GENERATE_MIPMAPS_FOR_TEXTURE = ObjC.sel("generateMipmapsForTexture:");
    private static final long OPTIMIZE_CONTENTS_FOR_CPU_ACCESS = ObjC.sel("optimizeContentsForCPUAccess:");
    private static final long OPTIMIZE_CONTENTS_FOR_CPU_ACCESS_SLICE_LEVEL = ObjC.sel("optimizeContentsForCPUAccess:slice:level:");
    private static final long OPTIMIZE_CONTENTS_FOR_GPU_ACCESS = ObjC.sel("optimizeContentsForGPUAccess:");
    private static final long OPTIMIZE_CONTENTS_FOR_GPU_ACCESS_SLICE_LEVEL = ObjC.sel("optimizeContentsForGPUAccess:slice:level:");
    private static final long OPTIMIZE_INDIRECT_COMMAND_BUFFER_WITH_RANGE = ObjC.sel("optimizeIndirectCommandBuffer:withRange:");
    private static final long RESET_COMMANDS_IN_BUFFER_WITH_RANGE = ObjC.sel("resetCommandsInBuffer:withRange:");
    private static final long RESOLVE_COUNTERS_IN_RANGE_DESTINATION_BUFFER_DESTINATION_OFFSET = ObjC.sel("resolveCounters:inRange:destinationBuffer:destinationOffset:");
    private static final long SAMPLE_COUNTERS_IN_BUFFER_AT_SAMPLE_INDEX_WITH_BARRIER = ObjC.sel("sampleCountersInBuffer:atSampleIndex:withBarrier:");
    private static final long SYNCHRONIZE_RESOURCE = ObjC.sel("synchronizeResource:");
    private static final long SYNCHRONIZE_TEXTURE_SLICE_LEVEL = ObjC.sel("synchronizeTexture:slice:level:");
    private static final long UPDATE_FENCE = ObjC.sel("updateFence:");
    private static final long WAIT_FOR_FENCE = ObjC.sel("waitForFence:");

    private static final MethodHandle L = handle(null, ObjC.PTR);
    private static final MethodHandle LL = handle(null, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLB = handle(null, ObjC.LONG, ObjC.LONG, ObjC.BOOL);
    private static final MethodHandle LLLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLLLLLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LRB = handle(null, ObjC.LONG, NSRange.LAYOUT, ValueLayout.JAVA_BYTE);
    private static final MethodHandle LR = handle(null, ObjC.LONG, NSRange.LAYOUT);
    private static final MethodHandle LRLL = handle(null, ObjC.LONG, NSRange.LAYOUT, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle TEX_TEX = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT,
            MTLSize.LAYOUT, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT);
    private static final MethodHandle BUF_TEX = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            MTLSize.LAYOUT, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT);
    private static final MethodHandle BUF_TEX_OPT = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            MTLSize.LAYOUT, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT, ObjC.LONG);
    private static final MethodHandle TEX_BUF = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT,
            MTLSize.LAYOUT, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle TEX_BUF_OPT = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT,
            MTLSize.LAYOUT, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);

    public static final long MTLBlitOptionNone = 0;
    public static final long MTLBlitOptionDepthFromDepthStencil = 1 << 0;
    public static final long MTLBlitOptionStencilFromDepthStencil = 1 << 1;

    private MTLBlitCommandEncoder(long id) {
        super(id);
    }

    public static MTLBlitCommandEncoder of(long id) {
        return new MTLBlitCommandEncoder(id);
    }

    @SneakyThrows
    public void synchronizeResource(MTLResource resource) {
        L.invokeExact(id, SYNCHRONIZE_RESOURCE, resource.getId());
    }

    @SneakyThrows
    public void synchronizeTexture(MTLTexture texture, long slice, long level) {
        LLL.invokeExact(id, SYNCHRONIZE_TEXTURE_SLICE_LEVEL, texture.getId(), slice, level);
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture sourceTexture, long sourceSlice, long sourceLevel,
            MemorySegment sourceOrigin, MemorySegment sourceSize, MTLTexture destinationTexture,
            long destinationSlice, long destinationLevel, MemorySegment destinationOrigin) {
        TEX_TEX.invokeExact(id, ObjC.sel("copyFromTexture:sourceSlice:sourceLevel:sourceOrigin:sourceSize:"
                + "toTexture:destinationSlice:destinationLevel:destinationOrigin:"),
                sourceTexture.getId(), sourceSlice, sourceLevel, sourceOrigin, sourceSize,
                destinationTexture.getId(), destinationSlice, destinationLevel, destinationOrigin);
    }

    @SneakyThrows
    public void copyFromBuffer(MTLBuffer sourceBuffer, long sourceOffset, long sourceBytesPerRow,
            long sourceBytesPerImage, MemorySegment sourceSize, MTLTexture destinationTexture,
            long destinationSlice, long destinationLevel, MemorySegment destinationOrigin) {
        BUF_TEX.invokeExact(id, ObjC.sel("copyFromBuffer:sourceOffset:sourceBytesPerRow:sourceBytesPerImage:"
                + "sourceSize:toTexture:destinationSlice:destinationLevel:destinationOrigin:"),
                sourceBuffer.getId(), sourceOffset, sourceBytesPerRow, sourceBytesPerImage, sourceSize,
                destinationTexture.getId(), destinationSlice, destinationLevel, destinationOrigin);
    }

    @SneakyThrows
    public void copyFromBuffer(MTLBuffer sourceBuffer, long sourceOffset, long sourceBytesPerRow,
            long sourceBytesPerImage, MemorySegment sourceSize, MTLTexture destinationTexture,
            long destinationSlice, long destinationLevel, MemorySegment destinationOrigin, long options) {
        BUF_TEX_OPT.invokeExact(id, ObjC.sel("copyFromBuffer:sourceOffset:sourceBytesPerRow:sourceBytesPerImage:"
                + "sourceSize:toTexture:destinationSlice:destinationLevel:destinationOrigin:options:"),
                sourceBuffer.getId(), sourceOffset, sourceBytesPerRow, sourceBytesPerImage, sourceSize,
                destinationTexture.getId(), destinationSlice, destinationLevel, destinationOrigin, options);
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture sourceTexture, long sourceSlice, long sourceLevel,
            MemorySegment sourceOrigin, MemorySegment sourceSize, MTLBuffer destinationBuffer,
            long destinationOffset, long destinationBytesPerRow, long destinationBytesPerImage) {
        TEX_BUF.invokeExact(id, ObjC.sel("copyFromTexture:sourceSlice:sourceLevel:sourceOrigin:sourceSize:"
                + "toBuffer:destinationOffset:destinationBytesPerRow:destinationBytesPerImage:"),
                sourceTexture.getId(), sourceSlice, sourceLevel, sourceOrigin, sourceSize,
                destinationBuffer.getId(), destinationOffset, destinationBytesPerRow, destinationBytesPerImage);
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture sourceTexture, long sourceSlice, long sourceLevel,
            MemorySegment sourceOrigin, MemorySegment sourceSize, MTLBuffer destinationBuffer,
            long destinationOffset, long destinationBytesPerRow, long destinationBytesPerImage, long options) {
        TEX_BUF_OPT.invokeExact(id, ObjC.sel("copyFromTexture:sourceSlice:sourceLevel:sourceOrigin:sourceSize:"
                + "toBuffer:destinationOffset:destinationBytesPerRow:destinationBytesPerImage:options:"),
                sourceTexture.getId(), sourceSlice, sourceLevel, sourceOrigin, sourceSize,
                destinationBuffer.getId(), destinationOffset, destinationBytesPerRow, destinationBytesPerImage,
                options);
    }

    @SneakyThrows
    public void generateMipmapsForTexture(MTLTexture texture) {
        L.invokeExact(id, GENERATE_MIPMAPS_FOR_TEXTURE, texture.getId());
    }

    @SneakyThrows
    public void fillBuffer(MTLBuffer buffer, MemorySegment range, byte value) {
        LRB.invokeExact(id, FILL_BUFFER_RANGE_VALUE, buffer.getId(), range, value);
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture sourceTexture, long sourceSlice, long sourceLevel,
            MTLTexture destinationTexture, long destinationSlice, long destinationLevel, long sliceCount,
            long levelCount) {
        LLLLLLLL.invokeExact(id, ObjC.sel("copyFromTexture:sourceSlice:sourceLevel:toTexture:destinationSlice:"
                + "destinationLevel:sliceCount:levelCount:"),
                sourceTexture.getId(), sourceSlice, sourceLevel, destinationTexture.getId(), destinationSlice,
                destinationLevel, sliceCount, levelCount);
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture sourceTexture, MTLTexture destinationTexture) {
        LL.invokeExact(id, COPY_FROM_TEXTURE_TO_TEXTURE, sourceTexture.getId(), destinationTexture.getId());
    }

    @SneakyThrows
    public void copyFromBuffer(MTLBuffer sourceBuffer, long sourceOffset, MTLBuffer destinationBuffer,
            long destinationOffset, long size) {
        LLLLL.invokeExact(id, COPY_FROM_BUFFER_SOURCE_OFFSET_TO_BUFFER_DESTINATION_OFFSET_SIZE,
                sourceBuffer.getId(), sourceOffset, destinationBuffer.getId(), destinationOffset, size);
    }

    @SneakyThrows
    public void optimizeContentsForGPUAccess(MTLTexture texture) {
        L.invokeExact(id, OPTIMIZE_CONTENTS_FOR_GPU_ACCESS, texture.getId());
    }

    @SneakyThrows
    public void optimizeContentsForGPUAccess(MTLTexture texture, long slice, long level) {
        LLL.invokeExact(id, OPTIMIZE_CONTENTS_FOR_GPU_ACCESS_SLICE_LEVEL, texture.getId(), slice, level);
    }

    @SneakyThrows
    public void optimizeContentsForCPUAccess(MTLTexture texture) {
        L.invokeExact(id, OPTIMIZE_CONTENTS_FOR_CPU_ACCESS, texture.getId());
    }

    @SneakyThrows
    public void optimizeContentsForCPUAccess(MTLTexture texture, long slice, long level) {
        LLL.invokeExact(id, OPTIMIZE_CONTENTS_FOR_CPU_ACCESS_SLICE_LEVEL, texture.getId(), slice, level);
    }

    @SneakyThrows
    public void resetCommandsInBuffer(MTLIndirectCommandBuffer buffer, MemorySegment range) {
        LR.invokeExact(id, RESET_COMMANDS_IN_BUFFER_WITH_RANGE, buffer.getId(), range);
    }

    @SneakyThrows
    public void copyIndirectCommandBuffer(MTLIndirectCommandBuffer source, MemorySegment sourceRange,
            MTLIndirectCommandBuffer destination, long destinationIndex) {
        LRLL.invokeExact(id, COPY_INDIRECT_COMMAND_BUFFER_SOURCE_RANGE_DESTINATION_DESTINATION_INDEX,
                source.getId(), sourceRange, destination.getId(), destinationIndex);
    }

    @SneakyThrows
    public void optimizeIndirectCommandBuffer(MTLIndirectCommandBuffer buffer, MemorySegment range) {
        LR.invokeExact(id, OPTIMIZE_INDIRECT_COMMAND_BUFFER_WITH_RANGE, buffer.getId(), range);
    }

    @SneakyThrows
    public void sampleCountersInBuffer(MTLCounterSampleBuffer sampleBuffer, long sampleIndex, boolean barrier) {
        LLB.invokeExact(id, SAMPLE_COUNTERS_IN_BUFFER_AT_SAMPLE_INDEX_WITH_BARRIER, sampleBuffer.getId(),
                sampleIndex, barrier);
    }

    @SneakyThrows
    public void resolveCounters(MTLCounterSampleBuffer sampleBuffer, MemorySegment range,
            MTLBuffer destinationBuffer, long destinationOffset) {
        LRLL.invokeExact(id, RESOLVE_COUNTERS_IN_RANGE_DESTINATION_BUFFER_DESTINATION_OFFSET,
                sampleBuffer.getId(), range, destinationBuffer.getId(), destinationOffset);
    }

    @SneakyThrows
    public void updateFence(MTLFence fence) {
        L.invokeExact(id, UPDATE_FENCE, fence.getId());
    }

    @SneakyThrows
    public void waitForFence(MTLFence fence) {
        L.invokeExact(id, WAIT_FOR_FENCE, fence.getId());
    }
}
