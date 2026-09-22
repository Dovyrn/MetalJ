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
    private static final long COPY_FROM_TEXTURE_SOURCE_SLICE_SOURCE_LEVEL_TO_TEXTURE_DESTINATION_SLICE_DESTINATION_LEVEL_SLICE_COUNT_LEVEL_COUNT = ObjC.sel("copyFromTexture:sourceSlice:sourceLevel:toTexture:destinationSlice:"
                + "destinationLevel:sliceCount:levelCount:");
    private static final long COPY_FROM_TEXTURE_SOURCE_SLICE_SOURCE_LEVEL_SOURCE_ORIGIN_SOURCE_SIZE_TO_TEXTURE_DESTINATION_SLICE_DESTINATION_LEVEL_DESTINATION_ORIGIN = ObjC.sel("copyFromTexture:sourceSlice:sourceLevel:sourceOrigin:sourceSize:"
                + "toTexture:destinationSlice:destinationLevel:destinationOrigin:");
    private static final long COPY_FROM_TEXTURE_SOURCE_SLICE_SOURCE_LEVEL_SOURCE_ORIGIN_SOURCE_SIZE_TO_BUFFER_DESTINATION_OFFSET_DESTINATION_BYTES_PER_ROW_DESTINATION_BYTES_PER_IMAGE = ObjC.sel("copyFromTexture:sourceSlice:sourceLevel:sourceOrigin:sourceSize:"
                + "toBuffer:destinationOffset:destinationBytesPerRow:destinationBytesPerImage:");
    private static final long COPY_FROM_TEXTURE_SOURCE_SLICE_SOURCE_LEVEL_SOURCE_ORIGIN_SOURCE_SIZE_TO_BUFFER_DESTINATION_OFFSET_DESTINATION_BYTES_PER_ROW_DESTINATION_BYTES_PER_IMAGE_OPTIONS = ObjC.sel("copyFromTexture:sourceSlice:sourceLevel:sourceOrigin:sourceSize:"
                + "toBuffer:destinationOffset:destinationBytesPerRow:destinationBytesPerImage:options:");
    private static final long COPY_FROM_BUFFER_SOURCE_OFFSET_SOURCE_BYTES_PER_ROW_SOURCE_BYTES_PER_IMAGE_SOURCE_SIZE_TO_TEXTURE_DESTINATION_SLICE_DESTINATION_LEVEL_DESTINATION_ORIGIN = ObjC.sel("copyFromBuffer:sourceOffset:sourceBytesPerRow:sourceBytesPerImage:"
                + "sourceSize:toTexture:destinationSlice:destinationLevel:destinationOrigin:");
    private static final long COPY_FROM_BUFFER_SOURCE_OFFSET_SOURCE_BYTES_PER_ROW_SOURCE_BYTES_PER_IMAGE_SOURCE_SIZE_TO_TEXTURE_DESTINATION_SLICE_DESTINATION_LEVEL_DESTINATION_ORIGIN_OPTIONS = ObjC.sel("copyFromBuffer:sourceOffset:sourceBytesPerRow:sourceBytesPerImage:"
                + "sourceSize:toTexture:destinationSlice:destinationLevel:destinationOrigin:options:");
    private static final long COPY_FROM_TENSOR_SOURCE_ORIGIN_SOURCE_DIMENSIONS_TO_TENSOR_DESTINATION_ORIGIN_DESTINATION_DIMENSIONS = ObjC.sel("copyFromTensor:sourceOrigin:sourceDimensions:toTensor:destinationOrigin:"
                + "destinationDimensions:");

    private static final long BUILD_ACCELERATION_STRUCTURE_DESCRIPTOR_SCRATCH_BUFFER = ObjC.sel("buildAccelerationStructure:descriptor:scratchBuffer:");
    private static final long COPY_ACCELERATION_STRUCTURE_TO_ACCELERATION_STRUCTURE = ObjC.sel("copyAccelerationStructure:toAccelerationStructure:");
    private static final long COPY_AND_COMPACT_ACCELERATION_STRUCTURE_TO_ACCELERATION_STRUCTURE = ObjC.sel("copyAndCompactAccelerationStructure:toAccelerationStructure:");
    private static final long COPY_FROM_BUFFER_SOURCE_OFFSET_TO_BUFFER_DESTINATION_OFFSET_SIZE = ObjC.sel("copyFromBuffer:sourceOffset:toBuffer:destinationOffset:size:");
    private static final long COPY_FROM_TEXTURE_TO_TEXTURE = ObjC.sel("copyFromTexture:toTexture:");
    private static final long COPY_INDIRECT_COMMAND_BUFFER_SOURCE_RANGE_DESTINATION_DESTINATION_INDEX = ObjC.sel("copyIndirectCommandBuffer:sourceRange:destination:destinationIndex:");
    private static final long DISPATCH_THREADGROUPS_THREADS_PER_THREADGROUP = ObjC.sel("dispatchThreadgroups:threadsPerThreadgroup:");
    private static final long DISPATCH_THREADGROUPS_WITH_INDIRECT_BUFFER_THREADS_PER_THREADGROUP = ObjC.sel("dispatchThreadgroupsWithIndirectBuffer:threadsPerThreadgroup:");
    private static final long DISPATCH_THREADS_THREADS_PER_THREADGROUP = ObjC.sel("dispatchThreads:threadsPerThreadgroup:");
    private static final long DISPATCH_THREADS_WITH_INDIRECT_BUFFER = ObjC.sel("dispatchThreadsWithIndirectBuffer:");
    private static final long EXECUTE_COMMANDS_IN_BUFFER_INDIRECT_BUFFER = ObjC.sel("executeCommandsInBuffer:indirectBuffer:");
    private static final long EXECUTE_COMMANDS_IN_BUFFER_WITH_RANGE = ObjC.sel("executeCommandsInBuffer:withRange:");
    private static final long FILL_BUFFER_RANGE_VALUE = ObjC.sel("fillBuffer:range:value:");
    private static final long GENERATE_MIPMAPS_FOR_TEXTURE = ObjC.sel("generateMipmapsForTexture:");
    private static final long OPTIMIZE_CONTENTS_FOR_CPU_ACCESS = ObjC.sel("optimizeContentsForCPUAccess:");
    private static final long OPTIMIZE_CONTENTS_FOR_CPU_ACCESS_SLICE_LEVEL = ObjC.sel("optimizeContentsForCPUAccess:slice:level:");
    private static final long OPTIMIZE_CONTENTS_FOR_GPU_ACCESS = ObjC.sel("optimizeContentsForGPUAccess:");
    private static final long OPTIMIZE_CONTENTS_FOR_GPU_ACCESS_SLICE_LEVEL = ObjC.sel("optimizeContentsForGPUAccess:slice:level:");
    private static final long OPTIMIZE_INDIRECT_COMMAND_BUFFER_WITH_RANGE = ObjC.sel("optimizeIndirectCommandBuffer:withRange:");
    private static final long REFIT_ACCELERATION_STRUCTURE_DESCRIPTOR_DESTINATION_SCRATCH_BUFFER = ObjC.sel("refitAccelerationStructure:descriptor:destination:scratchBuffer:");
    private static final long REFIT_ACCELERATION_STRUCTURE_DESCRIPTOR_DESTINATION_SCRATCH_BUFFER_OPTIONS = ObjC.sel("refitAccelerationStructure:descriptor:destination:scratchBuffer:options:");
    private static final long RESET_COMMANDS_IN_BUFFER_WITH_RANGE = ObjC.sel("resetCommandsInBuffer:withRange:");
    private static final long SET_ARGUMENT_TABLE = ObjC.sel("setArgumentTable:");
    private static final long SET_COMPUTE_PIPELINE_STATE = ObjC.sel("setComputePipelineState:");
    private static final long SET_IMAGEBLOCK_WIDTH_HEIGHT = ObjC.sel("setImageblockWidth:height:");
    private static final long SET_THREADGROUP_MEMORY_LENGTH_AT_INDEX = ObjC.sel("setThreadgroupMemoryLength:atIndex:");
    private static final long STAGES = ObjC.sel("stages");
    private static final long WRITE_COMPACTED_ACCELERATION_STRUCTURE_SIZE_TO_BUFFER = ObjC.sel("writeCompactedAccelerationStructureSize:toBuffer:");
    private static final long WRITE_TIMESTAMP_WITH_GRANULARITY_INTO_HEAP_AT_INDEX = ObjC.sel("writeTimestampWithGranularity:intoHeap:atIndex:");

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
        return sendLong(id, STAGES);
    }

    @SneakyThrows
    public void setComputePipelineState(MTLComputePipelineState state) {
        L.invokeExact(id, SET_COMPUTE_PIPELINE_STATE, state.getId());
    }

    @SneakyThrows
    public void setArgumentTable(MTL4ArgumentTable table) {
        L.invokeExact(id, SET_ARGUMENT_TABLE, table.getId());
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length, long index) {
        LL.invokeExact(id, SET_THREADGROUP_MEMORY_LENGTH_AT_INDEX, length, index);
    }

    @SneakyThrows
    public void setImageblockWidth(long width, long height) {
        LL.invokeExact(id, SET_IMAGEBLOCK_WIDTH_HEIGHT, width, height);
    }

    @SneakyThrows
    public void dispatchThreads(MemorySegment threadsPerGrid, MemorySegment threadsPerThreadgroup) {
        SS.invokeExact(id, DISPATCH_THREADS_THREADS_PER_THREADGROUP, threadsPerGrid,
                threadsPerThreadgroup);
    }

    @SneakyThrows
    public void dispatchThreadgroups(MemorySegment threadgroupsPerGrid, MemorySegment threadsPerThreadgroup) {
        SS.invokeExact(id, DISPATCH_THREADGROUPS_THREADS_PER_THREADGROUP, threadgroupsPerGrid,
                threadsPerThreadgroup);
    }

    @SneakyThrows
    public void dispatchThreadgroupsWithIndirectBuffer(long indirectBuffer, MemorySegment threadsPerThreadgroup) {
        LS.invokeExact(id, DISPATCH_THREADGROUPS_WITH_INDIRECT_BUFFER_THREADS_PER_THREADGROUP, indirectBuffer,
                threadsPerThreadgroup);
    }

    @SneakyThrows
    public void dispatchThreadsWithIndirectBuffer(long indirectBuffer) {
        L.invokeExact(id, DISPATCH_THREADS_WITH_INDIRECT_BUFFER, indirectBuffer);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, MemorySegment range) {
        LR.invokeExact(id, EXECUTE_COMMANDS_IN_BUFFER_WITH_RANGE, buffer.getId(), range);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, long indirectBuffer) {
        LL.invokeExact(id, EXECUTE_COMMANDS_IN_BUFFER_INDIRECT_BUFFER, buffer.getId(), indirectBuffer);
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture source, MTLTexture destination) {
        LL.invokeExact(id, COPY_FROM_TEXTURE_TO_TEXTURE, source.getId(), destination.getId());
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture source, long sourceSlice, long sourceLevel, MTLTexture destination,
            long destinationSlice, long destinationLevel, long sliceCount, long levelCount) {
        L8.invokeExact(id, COPY_FROM_TEXTURE_SOURCE_SLICE_SOURCE_LEVEL_TO_TEXTURE_DESTINATION_SLICE_DESTINATION_LEVEL_SLICE_COUNT_LEVEL_COUNT,
                source.getId(), sourceSlice, sourceLevel, destination.getId(), destinationSlice, destinationLevel,
                sliceCount, levelCount);
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture source, long sourceSlice, long sourceLevel, MemorySegment sourceOrigin,
            MemorySegment sourceSize, MTLTexture destination, long destinationSlice, long destinationLevel,
            MemorySegment destinationOrigin) {
        TEX_TEX.invokeExact(id, COPY_FROM_TEXTURE_SOURCE_SLICE_SOURCE_LEVEL_SOURCE_ORIGIN_SOURCE_SIZE_TO_TEXTURE_DESTINATION_SLICE_DESTINATION_LEVEL_DESTINATION_ORIGIN,
                source.getId(), sourceSlice, sourceLevel, sourceOrigin, sourceSize, destination.getId(),
                destinationSlice, destinationLevel, destinationOrigin);
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture source, long sourceSlice, long sourceLevel, MemorySegment sourceOrigin,
            MemorySegment sourceSize, MTLBuffer destination, long destinationOffset, long destinationBytesPerRow,
            long destinationBytesPerImage) {
        TEX_BUF.invokeExact(id, COPY_FROM_TEXTURE_SOURCE_SLICE_SOURCE_LEVEL_SOURCE_ORIGIN_SOURCE_SIZE_TO_BUFFER_DESTINATION_OFFSET_DESTINATION_BYTES_PER_ROW_DESTINATION_BYTES_PER_IMAGE,
                source.getId(), sourceSlice, sourceLevel, sourceOrigin, sourceSize, destination.getId(),
                destinationOffset, destinationBytesPerRow, destinationBytesPerImage);
    }

    @SneakyThrows
    public void copyFromTexture(MTLTexture source, long sourceSlice, long sourceLevel, MemorySegment sourceOrigin,
            MemorySegment sourceSize, MTLBuffer destination, long destinationOffset, long destinationBytesPerRow,
            long destinationBytesPerImage, long options) {
        TEX_BUF_OPT.invokeExact(id, COPY_FROM_TEXTURE_SOURCE_SLICE_SOURCE_LEVEL_SOURCE_ORIGIN_SOURCE_SIZE_TO_BUFFER_DESTINATION_OFFSET_DESTINATION_BYTES_PER_ROW_DESTINATION_BYTES_PER_IMAGE_OPTIONS,
                source.getId(), sourceSlice, sourceLevel, sourceOrigin, sourceSize, destination.getId(),
                destinationOffset, destinationBytesPerRow, destinationBytesPerImage, options);
    }

    @SneakyThrows
    public void copyFromBuffer(MTLBuffer source, long sourceOffset, MTLBuffer destination, long destinationOffset,
            long size) {
        LLLLL.invokeExact(id, COPY_FROM_BUFFER_SOURCE_OFFSET_TO_BUFFER_DESTINATION_OFFSET_SIZE,
                source.getId(), sourceOffset, destination.getId(), destinationOffset, size);
    }

    @SneakyThrows
    public void copyFromBuffer(MTLBuffer source, long sourceOffset, long sourceBytesPerRow, long sourceBytesPerImage,
            MemorySegment sourceSize, MTLTexture destination, long destinationSlice, long destinationLevel,
            MemorySegment destinationOrigin) {
        BUF_TEX.invokeExact(id, COPY_FROM_BUFFER_SOURCE_OFFSET_SOURCE_BYTES_PER_ROW_SOURCE_BYTES_PER_IMAGE_SOURCE_SIZE_TO_TEXTURE_DESTINATION_SLICE_DESTINATION_LEVEL_DESTINATION_ORIGIN,
                source.getId(), sourceOffset, sourceBytesPerRow, sourceBytesPerImage, sourceSize,
                destination.getId(), destinationSlice, destinationLevel, destinationOrigin);
    }

    @SneakyThrows
    public void copyFromBuffer(MTLBuffer source, long sourceOffset, long sourceBytesPerRow, long sourceBytesPerImage,
            MemorySegment sourceSize, MTLTexture destination, long destinationSlice, long destinationLevel,
            MemorySegment destinationOrigin, long options) {
        BUF_TEX_OPT.invokeExact(id, COPY_FROM_BUFFER_SOURCE_OFFSET_SOURCE_BYTES_PER_ROW_SOURCE_BYTES_PER_IMAGE_SOURCE_SIZE_TO_TEXTURE_DESTINATION_SLICE_DESTINATION_LEVEL_DESTINATION_ORIGIN_OPTIONS,
                source.getId(), sourceOffset, sourceBytesPerRow, sourceBytesPerImage, sourceSize,
                destination.getId(), destinationSlice, destinationLevel, destinationOrigin, options);
    }

    @SneakyThrows
    public void copyFromTensor(MTLTensor source, MTLTensorExtents sourceOrigin, MTLTensorExtents sourceDimensions,
            MTLTensor destination, MTLTensorExtents destinationOrigin, MTLTensorExtents destinationDimensions) {
        LLLLLL.invokeExact(id, COPY_FROM_TENSOR_SOURCE_ORIGIN_SOURCE_DIMENSIONS_TO_TENSOR_DESTINATION_ORIGIN_DESTINATION_DIMENSIONS,
                source.getId(), sourceOrigin.getId(), sourceDimensions.getId(), destination.getId(),
                destinationOrigin.getId(), destinationDimensions.getId());
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
    public void buildAccelerationStructure(MTLAccelerationStructure structure,
            MTL4AccelerationStructureDescriptor descriptor, MemorySegment scratch) {
        LLB.invokeExact(id, BUILD_ACCELERATION_STRUCTURE_DESCRIPTOR_SCRATCH_BUFFER, structure.getId(),
                descriptor.getId(), scratch);
    }

    @SneakyThrows
    public void refitAccelerationStructure(MTLAccelerationStructure source,
            MTL4AccelerationStructureDescriptor descriptor, MTLAccelerationStructure destination,
            MemorySegment scratch) {
        LLLB.invokeExact(id, REFIT_ACCELERATION_STRUCTURE_DESCRIPTOR_DESTINATION_SCRATCH_BUFFER,
                source.getId(), descriptor.getId(), destination.getId(), scratch);
    }

    @SneakyThrows
    public void refitAccelerationStructure(MTLAccelerationStructure source,
            MTL4AccelerationStructureDescriptor descriptor, MTLAccelerationStructure destination,
            MemorySegment scratch, long options) {
        LLLBL.invokeExact(id, REFIT_ACCELERATION_STRUCTURE_DESCRIPTOR_DESTINATION_SCRATCH_BUFFER_OPTIONS,
                source.getId(), descriptor.getId(), destination.getId(), scratch, options);
    }

    @SneakyThrows
    public void copyAccelerationStructure(MTLAccelerationStructure source, MTLAccelerationStructure destination) {
        LL.invokeExact(id, COPY_ACCELERATION_STRUCTURE_TO_ACCELERATION_STRUCTURE, source.getId(),
                destination.getId());
    }

    @SneakyThrows
    public void writeCompactedAccelerationStructureSize(MTLAccelerationStructure structure, MemorySegment buffer) {
        LB.invokeExact(id, WRITE_COMPACTED_ACCELERATION_STRUCTURE_SIZE_TO_BUFFER, structure.getId(), buffer);
    }

    @SneakyThrows
    public void copyAndCompactAccelerationStructure(MTLAccelerationStructure source,
            MTLAccelerationStructure destination) {
        LL.invokeExact(id, COPY_AND_COMPACT_ACCELERATION_STRUCTURE_TO_ACCELERATION_STRUCTURE, source.getId(),
                destination.getId());
    }

    @SneakyThrows
    public void writeTimestampWithGranularity(long granularity, MTL4CounterHeap heap, long index) {
        LLL.invokeExact(id, WRITE_TIMESTAMP_WITH_GRANULARITY_INTO_HEAP_AT_INDEX, granularity, heap.getId(),
                index);
    }
}
