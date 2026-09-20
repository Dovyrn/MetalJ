package dev.dov.metalj.metal4.encoders;

import dev.dov.metalj.metal4.MTL4ArgumentTable;
import dev.dov.metalj.metal4.MTL4BufferRange;
import dev.dov.metalj.metal4.counters.MTL4CounterHeap;
import dev.dov.metalj.metal4.raytracing.MTL4AccelerationStructureDescriptor;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineState;
import dev.dov.metalj.raytracing.MTLAccelerationStructure;
import dev.dov.metalj.resources.MTLOrigin;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.indirect.MTLIndirectCommandBuffer;
import dev.dov.metalj.resources.textures.MTLTexture;
import dev.dov.metalj.tensors.MTLTensor;
import dev.dov.metalj.tensors.MTLTensorExtents;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4ComputeCommandEncoder extends MTL4CommandEncoder {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle LL = handle(null, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLLLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            ObjC.LONG);
    private static final MethodHandle L8 = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LR = handle(null, ObjC.LONG, NSRange.LAYOUT);
    private static final MethodHandle LRB = handle(null, ObjC.LONG, NSRange.LAYOUT, ObjC.BYTE);
    private static final MethodHandle LRLL = handle(null, ObjC.LONG, NSRange.LAYOUT, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle SS = handle(null, MTLSize.LAYOUT, MTLSize.LAYOUT);
    private static final MethodHandle LS = handle(null, ObjC.LONG, MTLSize.LAYOUT);
    private static final MethodHandle TEX_TEX = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT,
            MTLSize.LAYOUT, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT);
    private static final MethodHandle TEX_BUF = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT,
            MTLSize.LAYOUT, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle TEX_BUF_OPT = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT,
            MTLSize.LAYOUT, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle BUF_TEX = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            MTLSize.LAYOUT, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT);
    private static final MethodHandle BUF_TEX_OPT = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            MTLSize.LAYOUT, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTLOrigin.LAYOUT, ObjC.LONG);
    private static final MethodHandle LLB = handle(null, ObjC.LONG, ObjC.LONG, MTL4BufferRange.LAYOUT);
    private static final MethodHandle LLLB = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTL4BufferRange.LAYOUT);
    private static final MethodHandle LLLBL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, MTL4BufferRange.LAYOUT,
            ObjC.LONG);
    private static final MethodHandle LB = handle(null, ObjC.LONG, MTL4BufferRange.LAYOUT);

    private MTL4ComputeCommandEncoder(long id) {
        super(id);
    }

    public static MTL4ComputeCommandEncoder of(long id) {
        return new MTL4ComputeCommandEncoder(id);
    }

    public long stages() {
        return sendLong(id, "stages");
    }

    @SneakyThrows
    public void setComputePipelineState(MTLComputePipelineState state) {
        L.invokeExact(id, ObjC.sel("setComputePipelineState:"), state.getId());
    }

    @SneakyThrows
    public void setArgumentTable(MTL4ArgumentTable table) {
        L.invokeExact(id, ObjC.sel("setArgumentTable:"), table.getId());
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length, long index) {
        LL.invokeExact(id, ObjC.sel("setThreadgroupMemoryLength:atIndex:"), length, index);
    }

    @SneakyThrows
    public void setImageblockWidth(long width, long height) {
        LL.invokeExact(id, ObjC.sel("setImageblockWidth:height:"), width, height);
    }

    @SneakyThrows
    public void dispatchThreads(MemorySegment threadsPerGrid, MemorySegment threadsPerThreadgroup) {
        SS.invokeExact(id, ObjC.sel("dispatchThreads:threadsPerThreadgroup:"), threadsPerGrid,
                threadsPerThreadgroup);
    }

    @SneakyThrows
    public void dispatchThreadgroups(MemorySegment threadgroupsPerGrid, MemorySegment threadsPerThreadgroup) {
        SS.invokeExact(id, ObjC.sel("dispatchThreadgroups:threadsPerThreadgroup:"), threadgroupsPerGrid,
                threadsPerThreadgroup);
    }

    @SneakyThrows
    public void dispatchThreadgroupsWithIndirectBuffer(long indirectBuffer, MemorySegment threadsPerThreadgroup) {
        LS.invokeExact(id, ObjC.sel("dispatchThreadgroupsWithIndirectBuffer:threadsPerThreadgroup:"), indirectBuffer,
                threadsPerThreadgroup);
    }

    @SneakyThrows
    public void dispatchThreadsWithIndirectBuffer(long indirectBuffer) {
        L.invokeExact(id, ObjC.sel("dispatchThreadsWithIndirectBuffer:"), indirectBuffer);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, MemorySegment range) {
        LR.invokeExact(id, ObjC.sel("executeCommandsInBuffer:withRange:"), buffer.getId(), range);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, long indirectBuffer) {
        LL.invokeExact(id, ObjC.sel("executeCommandsInBuffer:indirectBuffer:"), buffer.getId(), indirectBuffer);
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture source, MTLTexture destination) {
        LL.invokeExact(id, ObjC.sel("copyFromTexture:toTexture:"), source.getId(), destination.getId());
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture source, long sourceSlice, long sourceLevel, MTLTexture destination,
            long destinationSlice, long destinationLevel, long sliceCount, long levelCount) {
        L8.invokeExact(id, ObjC.sel("copyFromTexture:sourceSlice:sourceLevel:toTexture:destinationSlice:"
                + "destinationLevel:sliceCount:levelCount:"),
                source.getId(), sourceSlice, sourceLevel, destination.getId(), destinationSlice, destinationLevel,
                sliceCount, levelCount);
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture source, long sourceSlice, long sourceLevel, MemorySegment sourceOrigin,
            MemorySegment sourceSize, MTLTexture destination, long destinationSlice, long destinationLevel,
            MemorySegment destinationOrigin) {
        TEX_TEX.invokeExact(id, ObjC.sel("copyFromTexture:sourceSlice:sourceLevel:sourceOrigin:sourceSize:"
                + "toTexture:destinationSlice:destinationLevel:destinationOrigin:"),
                source.getId(), sourceSlice, sourceLevel, sourceOrigin, sourceSize, destination.getId(),
                destinationSlice, destinationLevel, destinationOrigin);
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture source, long sourceSlice, long sourceLevel, MemorySegment sourceOrigin,
            MemorySegment sourceSize, MTLBuffer destination, long destinationOffset, long destinationBytesPerRow,
            long destinationBytesPerImage) {
        TEX_BUF.invokeExact(id, ObjC.sel("copyFromTexture:sourceSlice:sourceLevel:sourceOrigin:sourceSize:"
                + "toBuffer:destinationOffset:destinationBytesPerRow:destinationBytesPerImage:"),
                source.getId(), sourceSlice, sourceLevel, sourceOrigin, sourceSize, destination.getId(),
                destinationOffset, destinationBytesPerRow, destinationBytesPerImage);
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture source, long sourceSlice, long sourceLevel, MemorySegment sourceOrigin,
            MemorySegment sourceSize, MTLBuffer destination, long destinationOffset, long destinationBytesPerRow,
            long destinationBytesPerImage, long options) {
        TEX_BUF_OPT.invokeExact(id, ObjC.sel("copyFromTexture:sourceSlice:sourceLevel:sourceOrigin:sourceSize:"
                + "toBuffer:destinationOffset:destinationBytesPerRow:destinationBytesPerImage:options:"),
                source.getId(), sourceSlice, sourceLevel, sourceOrigin, sourceSize, destination.getId(),
                destinationOffset, destinationBytesPerRow, destinationBytesPerImage, options);
    }

    @SneakyThrows
    public void copyFromBuffer(MTLBuffer source, long sourceOffset, MTLBuffer destination, long destinationOffset,
            long size) {
        LLLLL.invokeExact(id, ObjC.sel("copyFromBuffer:sourceOffset:toBuffer:destinationOffset:size:"),
                source.getId(), sourceOffset, destination.getId(), destinationOffset, size);
    }

    @SneakyThrows
    public void copyFromBuffer(MTLBuffer source, long sourceOffset, long sourceBytesPerRow, long sourceBytesPerImage,
            MemorySegment sourceSize, MTLTexture destination, long destinationSlice, long destinationLevel,
            MemorySegment destinationOrigin) {
        BUF_TEX.invokeExact(id, ObjC.sel("copyFromBuffer:sourceOffset:sourceBytesPerRow:sourceBytesPerImage:"
                + "sourceSize:toTexture:destinationSlice:destinationLevel:destinationOrigin:"),
                source.getId(), sourceOffset, sourceBytesPerRow, sourceBytesPerImage, sourceSize,
                destination.getId(), destinationSlice, destinationLevel, destinationOrigin);
    }

    @SneakyThrows
    public void copyFromBuffer(MTLBuffer source, long sourceOffset, long sourceBytesPerRow, long sourceBytesPerImage,
            MemorySegment sourceSize, MTLTexture destination, long destinationSlice, long destinationLevel,
            MemorySegment destinationOrigin, long options) {
        BUF_TEX_OPT.invokeExact(id, ObjC.sel("copyFromBuffer:sourceOffset:sourceBytesPerRow:sourceBytesPerImage:"
                + "sourceSize:toTexture:destinationSlice:destinationLevel:destinationOrigin:options:"),
                source.getId(), sourceOffset, sourceBytesPerRow, sourceBytesPerImage, sourceSize,
                destination.getId(), destinationSlice, destinationLevel, destinationOrigin, options);
    }

    @SneakyThrows
    public void copyFromTensor(MTLTensor source, MTLTensorExtents sourceOrigin, MTLTensorExtents sourceDimensions,
            MTLTensor destination, MTLTensorExtents destinationOrigin, MTLTensorExtents destinationDimensions) {
        LLLLLL.invokeExact(id, ObjC.sel("copyFromTensor:sourceOrigin:sourceDimensions:toTensor:destinationOrigin:"
                + "destinationDimensions:"),
                source.getId(), sourceOrigin.getId(), sourceDimensions.getId(), destination.getId(),
                destinationOrigin.getId(), destinationDimensions.getId());
    }

    @SneakyThrows
    public void generateMipmapsForTexture(MTLTexture texture) {
        L.invokeExact(id, ObjC.sel("generateMipmapsForTexture:"), texture.getId());
    }

    @SneakyThrows
    public void fillBuffer(MTLBuffer buffer, MemorySegment range, byte value) {
        LRB.invokeExact(id, ObjC.sel("fillBuffer:range:value:"), buffer.getId(), range, value);
    }

    @SneakyThrows
    public void optimizeContentsForGPUAccess(MTLTexture texture) {
        L.invokeExact(id, ObjC.sel("optimizeContentsForGPUAccess:"), texture.getId());
    }

    @SneakyThrows
    public void optimizeContentsForGPUAccess(MTLTexture texture, long slice, long level) {
        LLL.invokeExact(id, ObjC.sel("optimizeContentsForGPUAccess:slice:level:"), texture.getId(), slice, level);
    }

    @SneakyThrows
    public void optimizeContentsForCPUAccess(MTLTexture texture) {
        L.invokeExact(id, ObjC.sel("optimizeContentsForCPUAccess:"), texture.getId());
    }

    @SneakyThrows
    public void optimizeContentsForCPUAccess(MTLTexture texture, long slice, long level) {
        LLL.invokeExact(id, ObjC.sel("optimizeContentsForCPUAccess:slice:level:"), texture.getId(), slice, level);
    }

    @SneakyThrows
    public void resetCommandsInBuffer(MTLIndirectCommandBuffer buffer, MemorySegment range) {
        LR.invokeExact(id, ObjC.sel("resetCommandsInBuffer:withRange:"), buffer.getId(), range);
    }

    @SneakyThrows
    public void copyIndirectCommandBuffer(MTLIndirectCommandBuffer source, MemorySegment sourceRange,
            MTLIndirectCommandBuffer destination, long destinationIndex) {
        LRLL.invokeExact(id, ObjC.sel("copyIndirectCommandBuffer:sourceRange:destination:destinationIndex:"),
                source.getId(), sourceRange, destination.getId(), destinationIndex);
    }

    @SneakyThrows
    public void optimizeIndirectCommandBuffer(MTLIndirectCommandBuffer buffer, MemorySegment range) {
        LR.invokeExact(id, ObjC.sel("optimizeIndirectCommandBuffer:withRange:"), buffer.getId(), range);
    }

    @SneakyThrows
    public void buildAccelerationStructure(MTLAccelerationStructure structure,
            MTL4AccelerationStructureDescriptor descriptor, MemorySegment scratch) {
        LLB.invokeExact(id, ObjC.sel("buildAccelerationStructure:descriptor:scratchBuffer:"), structure.getId(),
                descriptor.getId(), scratch);
    }

    @SneakyThrows
    public void refitAccelerationStructure(MTLAccelerationStructure source,
            MTL4AccelerationStructureDescriptor descriptor, MTLAccelerationStructure destination,
            MemorySegment scratch) {
        LLLB.invokeExact(id, ObjC.sel("refitAccelerationStructure:descriptor:destination:scratchBuffer:"),
                source.getId(), descriptor.getId(), destination.getId(), scratch);
    }

    @SneakyThrows
    public void refitAccelerationStructure(MTLAccelerationStructure source,
            MTL4AccelerationStructureDescriptor descriptor, MTLAccelerationStructure destination,
            MemorySegment scratch, long options) {
        LLLBL.invokeExact(id, ObjC.sel("refitAccelerationStructure:descriptor:destination:scratchBuffer:options:"),
                source.getId(), descriptor.getId(), destination.getId(), scratch, options);
    }

    @SneakyThrows
    public void copyAccelerationStructure(MTLAccelerationStructure source, MTLAccelerationStructure destination) {
        LL.invokeExact(id, ObjC.sel("copyAccelerationStructure:toAccelerationStructure:"), source.getId(),
                destination.getId());
    }

    @SneakyThrows
    public void writeCompactedAccelerationStructureSize(MTLAccelerationStructure structure, MemorySegment buffer) {
        LB.invokeExact(id, ObjC.sel("writeCompactedAccelerationStructureSize:toBuffer:"), structure.getId(), buffer);
    }

    @SneakyThrows
    public void copyAndCompactAccelerationStructure(MTLAccelerationStructure source,
            MTLAccelerationStructure destination) {
        LL.invokeExact(id, ObjC.sel("copyAndCompactAccelerationStructure:toAccelerationStructure:"), source.getId(),
                destination.getId());
    }

    @SneakyThrows
    public void writeTimestampWithGranularity(long granularity, MTL4CounterHeap heap, long index) {
        LLL.invokeExact(id, ObjC.sel("writeTimestampWithGranularity:intoHeap:atIndex:"), granularity, heap.getId(),
                index);
    }
}
