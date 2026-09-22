package dev.dov.metalj.device;

import dev.dov.metalj.arguments.MTLArgumentEncoder;
import dev.dov.metalj.io.MTLIOCommandQueue;
import dev.dov.metalj.io.MTLIOCommandQueueDescriptor;
import dev.dov.metalj.io.MTLIOFileHandle;
import dev.dov.metalj.libraries.MTLBinaryArchive;
import dev.dov.metalj.libraries.MTLBinaryArchiveDescriptor;
import dev.dov.metalj.libraries.MTLDynamicLibrary;
import dev.dov.metalj.metal4.MTL4ArgumentTable;
import dev.dov.metalj.metal4.MTL4ArgumentTableDescriptor;
import dev.dov.metalj.metal4.MTL4CommandAllocator;
import dev.dov.metalj.metal4.MTL4CommandAllocatorDescriptor;
import dev.dov.metalj.metal4.MTL4CommandBuffer;
import dev.dov.metalj.metal4.MTL4CommandQueue;
import dev.dov.metalj.metal4.MTL4CommandQueueDescriptor;
import dev.dov.metalj.metal4.compiler.MTL4Archive;
import dev.dov.metalj.metal4.compiler.MTL4BinaryFunction;
import dev.dov.metalj.metal4.compiler.MTL4Compiler;
import dev.dov.metalj.metal4.compiler.MTL4CompilerDescriptor;
import dev.dov.metalj.metal4.compiler.MTL4PipelineDataSetSerializer;
import dev.dov.metalj.metal4.compiler.MTL4PipelineDataSetSerializerDescriptor;
import dev.dov.metalj.metal4.counters.MTL4CounterHeap;
import dev.dov.metalj.metal4.counters.MTL4CounterHeapDescriptor;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.rate.MTLRasterizationRateMap;
import dev.dov.metalj.rate.MTLRasterizationRateMapDescriptor;
import dev.dov.metalj.raytracing.MTLAccelerationStructure;
import dev.dov.metalj.pools.MTLResourceViewPoolDescriptor;
import dev.dov.metalj.pools.MTLTextureViewPool;
import dev.dov.metalj.residency.MTLResidencySet;
import dev.dov.metalj.tensors.MTLTensor;
import dev.dov.metalj.tensors.MTLTensorDescriptor;
import dev.dov.metalj.residency.MTLResidencySetDescriptor;
import dev.dov.metalj.sync.MTLEvent;
import dev.dov.metalj.sync.MTLFence;
import dev.dov.metalj.sync.MTLSharedEvent;
import dev.dov.metalj.sync.MTLSharedEventHandle;
import dev.dov.metalj.raytracing.MTLAccelerationStructureDescriptor;
import dev.dov.metalj.raytracing.MTLAccelerationStructureSizes;
import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.debug.MTLCounterSampleBufferDescriptor;
import dev.dov.metalj.debug.MTLCounterSet;
import dev.dov.metalj.debug.MTLLogState;
import dev.dov.metalj.debug.MTLLogStateDescriptor;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSError;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineDescriptor;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineState;
import dev.dov.metalj.pipelines.depth.MTLDepthStencilDescriptor;
import dev.dov.metalj.pipelines.depth.MTLDepthStencilState;
import dev.dov.metalj.pipelines.shaders.MTLFunction;
import dev.dov.metalj.pipelines.shaders.MTLFunctionHandle;
import dev.dov.metalj.pipelines.shaders.MTLLibrary;
import dev.dov.metalj.pipelines.render.MTLMeshRenderPipelineDescriptor;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineDescriptor;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineReflection;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineReflection;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineState;
import dev.dov.metalj.pipelines.render.MTLTileRenderPipelineDescriptor;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.heaps.MTLHeap;
import dev.dov.metalj.resources.heaps.MTLHeapDescriptor;
import dev.dov.metalj.resources.indirect.MTLIndirectCommandBuffer;
import dev.dov.metalj.resources.indirect.MTLIndirectCommandBufferDescriptor;
import dev.dov.metalj.resources.samplers.MTLSamplerDescriptor;
import dev.dov.metalj.resources.samplers.MTLSamplerState;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.heaps.MTLSizeAndAlign;
import dev.dov.metalj.resources.textures.MTLSharedTextureHandle;
import dev.dov.metalj.resources.textures.MTLTexture;
import dev.dov.metalj.resources.textures.MTLTextureDescriptor;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLDevice extends NSObject {
    private static final long ACCELERATION_STRUCTURE_SIZES_WITH_DESCRIPTOR = ObjC.sel("accelerationStructureSizesWithDescriptor:");
    private static final long ARCHITECTURE = ObjC.sel("architecture");
    private static final long ARE_BARYCENTRIC_COORDS_SUPPORTED = ObjC.sel("areBarycentricCoordsSupported");
    private static final long ARE_PROGRAMMABLE_SAMPLE_POSITIONS_SUPPORTED = ObjC.sel("areProgrammableSamplePositionsSupported");
    private static final long ARE_RASTER_ORDER_GROUPS_SUPPORTED = ObjC.sel("areRasterOrderGroupsSupported");
    private static final long ARGUMENT_BUFFERS_SUPPORT = ObjC.sel("argumentBuffersSupport");
    private static final long COUNTER_SETS = ObjC.sel("counterSets");
    private static final long CURRENT_ALLOCATED_SIZE = ObjC.sel("currentAllocatedSize");
    private static final long FUNCTION_HANDLE_WITH_BINARY_FUNCTION = ObjC.sel("functionHandleWithBinaryFunction:");
    private static final long HAS_UNIFIED_MEMORY = ObjC.sel("hasUnifiedMemory");
    private static final long HEAP_ACCELERATION_STRUCTURE_SIZE_AND_ALIGN_WITH_SIZE = ObjC.sel("heapAccelerationStructureSizeAndAlignWithSize:");
    private static final long HEAP_BUFFER_SIZE_AND_ALIGN_WITH_LENGTH_OPTIONS = ObjC.sel("heapBufferSizeAndAlignWithLength:options:");
    private static final long HEAP_TEXTURE_SIZE_AND_ALIGN_WITH_DESCRIPTOR = ObjC.sel("heapTextureSizeAndAlignWithDescriptor:");
    private static final long IS_DEPTH24_STENCIL8_PIXEL_FORMAT_SUPPORTED = ObjC.sel("isDepth24Stencil8PixelFormatSupported");
    private static final long IS_HEADLESS = ObjC.sel("isHeadless");
    private static final long IS_LOW_POWER = ObjC.sel("isLowPower");
    private static final long IS_REMOVABLE = ObjC.sel("isRemovable");
    private static final long LOCATION = ObjC.sel("location");
    private static final long LOCATION_NUMBER = ObjC.sel("locationNumber");
    private static final long MAX_ARGUMENT_BUFFER_SAMPLER_COUNT = ObjC.sel("maxArgumentBufferSamplerCount");
    private static final long MAX_BUFFER_LENGTH = ObjC.sel("maxBufferLength");
    private static final long MAX_THREADGROUP_MEMORY_LENGTH = ObjC.sel("maxThreadgroupMemoryLength");
    private static final long MAX_THREADS_PER_THREADGROUP = ObjC.sel("maxThreadsPerThreadgroup");
    private static final long MAX_TRANSFER_RATE = ObjC.sel("maxTransferRate");
    private static final long MAXIMUM_CONCURRENT_COMPILATION_TASK_COUNT = ObjC.sel("maximumConcurrentCompilationTaskCount");
    private static final long MINIMUM_LINEAR_TEXTURE_ALIGNMENT_FOR_PIXEL_FORMAT = ObjC.sel("minimumLinearTextureAlignmentForPixelFormat:");
    private static final long MINIMUM_TEXTURE_BUFFER_ALIGNMENT_FOR_PIXEL_FORMAT = ObjC.sel("minimumTextureBufferAlignmentForPixelFormat:");
    private static final long NAME = ObjC.sel("name");
    private static final long NEW_ACCELERATION_STRUCTURE_WITH_DESCRIPTOR = ObjC.sel("newAccelerationStructureWithDescriptor:");
    private static final long NEW_ACCELERATION_STRUCTURE_WITH_SIZE = ObjC.sel("newAccelerationStructureWithSize:");
    private static final long NEW_ARCHIVE_WITH_URL_ERROR = ObjC.sel("newArchiveWithURL:error:");
    private static final long NEW_ARGUMENT_ENCODER_WITH_ARGUMENTS = ObjC.sel("newArgumentEncoderWithArguments:");
    private static final long NEW_ARGUMENT_TABLE_WITH_DESCRIPTOR_ERROR = ObjC.sel("newArgumentTableWithDescriptor:error:");
    private static final long NEW_BINARY_ARCHIVE_WITH_DESCRIPTOR_ERROR = ObjC.sel("newBinaryArchiveWithDescriptor:error:");
    private static final long NEW_BUFFER_WITH_BYTES_LENGTH_OPTIONS = ObjC.sel("newBufferWithBytes:length:options:");
    private static final long NEW_BUFFER_WITH_BYTES_NO_COPY_LENGTH_OPTIONS_DEALLOCATOR = ObjC.sel("newBufferWithBytesNoCopy:length:options:deallocator:");
    private static final long NEW_BUFFER_WITH_LENGTH_OPTIONS = ObjC.sel("newBufferWithLength:options:");
    private static final long NEW_COMMAND_ALLOCATOR = ObjC.sel("newCommandAllocator");
    private static final long NEW_COMMAND_ALLOCATOR_WITH_DESCRIPTOR_ERROR = ObjC.sel("newCommandAllocatorWithDescriptor:error:");
    private static final long NEW_COMMAND_BUFFER = ObjC.sel("newCommandBuffer");
    private static final long NEW_COMMAND_QUEUE = ObjC.sel("newCommandQueue");
    private static final long NEW_COMMAND_QUEUE_WITH_DESCRIPTOR = ObjC.sel("newCommandQueueWithDescriptor:");
    private static final long NEW_COMPILER_WITH_DESCRIPTOR_ERROR = ObjC.sel("newCompilerWithDescriptor:error:");
    private static final long NEW_COMPUTE_PIPELINE_STATE_WITH_DESCRIPTOR_OPTIONS_REFLECTION_ERROR = ObjC.sel("newComputePipelineStateWithDescriptor:options:reflection:error:");
    private static final long NEW_COMPUTE_PIPELINE_STATE_WITH_FUNCTION_ERROR = ObjC.sel("newComputePipelineStateWithFunction:error:");
    private static final long NEW_COMPUTE_PIPELINE_STATE_WITH_FUNCTION_OPTIONS_REFLECTION_ERROR = ObjC.sel("newComputePipelineStateWithFunction:options:reflection:error:");
    private static final long NEW_COUNTER_HEAP_WITH_DESCRIPTOR_ERROR = ObjC.sel("newCounterHeapWithDescriptor:error:");
    private static final long NEW_COUNTER_SAMPLE_BUFFER_WITH_DESCRIPTOR_ERROR = ObjC.sel("newCounterSampleBufferWithDescriptor:error:");
    private static final long NEW_DEFAULT_LIBRARY = ObjC.sel("newDefaultLibrary");
    private static final long NEW_DEPTH_STENCIL_STATE_WITH_DESCRIPTOR = ObjC.sel("newDepthStencilStateWithDescriptor:");
    private static final long NEW_DYNAMIC_LIBRARY_ERROR = ObjC.sel("newDynamicLibrary:error:");
    private static final long NEW_DYNAMIC_LIBRARY_WITH_URL_ERROR = ObjC.sel("newDynamicLibraryWithURL:error:");
    private static final long NEW_EVENT = ObjC.sel("newEvent");
    private static final long NEW_FENCE = ObjC.sel("newFence");
    private static final long NEW_HEAP_WITH_DESCRIPTOR = ObjC.sel("newHeapWithDescriptor:");
    private static final long NEW_IO_COMMAND_QUEUE_WITH_DESCRIPTOR_ERROR = ObjC.sel("newIOCommandQueueWithDescriptor:error:");
    private static final long NEW_IO_FILE_HANDLE_WITH_URL_COMPRESSION_METHOD_ERROR = ObjC.sel("newIOFileHandleWithURL:compressionMethod:error:");
    private static final long NEW_IO_FILE_HANDLE_WITH_URL_ERROR = ObjC.sel("newIOFileHandleWithURL:error:");
    private static final long NEW_INDIRECT_COMMAND_BUFFER_WITH_DESCRIPTOR_MAX_COMMAND_COUNT_OPTIONS = ObjC.sel("newIndirectCommandBufferWithDescriptor:maxCommandCount:options:");
    private static final long NEW_LIBRARY_WITH_SOURCE_OPTIONS_ERROR = ObjC.sel("newLibraryWithSource:options:error:");
    private static final long NEW_LOG_STATE_WITH_DESCRIPTOR_ERROR = ObjC.sel("newLogStateWithDescriptor:error:");
    private static final long NEW_MTL_4_COMMAND_QUEUE = ObjC.sel("newMTL4CommandQueue");
    private static final long NEW_MTL_4_COMMAND_QUEUE_WITH_DESCRIPTOR_ERROR = ObjC.sel("newMTL4CommandQueueWithDescriptor:error:");
    private static final long NEW_PIPELINE_DATA_SET_SERIALIZER_WITH_DESCRIPTOR = ObjC.sel("newPipelineDataSetSerializerWithDescriptor:");
    private static final long NEW_RASTERIZATION_RATE_MAP_WITH_DESCRIPTOR = ObjC.sel("newRasterizationRateMapWithDescriptor:");
    private static final long NEW_RENDER_PIPELINE_STATE_WITH_DESCRIPTOR_ERROR = ObjC.sel("newRenderPipelineStateWithDescriptor:error:");
    private static final long NEW_RENDER_PIPELINE_STATE_WITH_DESCRIPTOR_OPTIONS_REFLECTION_ERROR = ObjC.sel("newRenderPipelineStateWithDescriptor:options:reflection:error:");
    private static final long NEW_RENDER_PIPELINE_STATE_WITH_MESH_DESCRIPTOR_OPTIONS_REFLECTION_ERROR = ObjC.sel("newRenderPipelineStateWithMeshDescriptor:options:reflection:error:");
    private static final long NEW_RENDER_PIPELINE_STATE_WITH_TILE_DESCRIPTOR_OPTIONS_REFLECTION_ERROR = ObjC.sel("newRenderPipelineStateWithTileDescriptor:options:reflection:error:");
    private static final long NEW_RESIDENCY_SET_WITH_DESCRIPTOR_ERROR = ObjC.sel("newResidencySetWithDescriptor:error:");
    private static final long NEW_SAMPLER_STATE_WITH_DESCRIPTOR = ObjC.sel("newSamplerStateWithDescriptor:");
    private static final long NEW_SHARED_EVENT = ObjC.sel("newSharedEvent");
    private static final long NEW_SHARED_EVENT_WITH_HANDLE = ObjC.sel("newSharedEventWithHandle:");
    private static final long NEW_SHARED_TEXTURE_WITH_DESCRIPTOR = ObjC.sel("newSharedTextureWithDescriptor:");
    private static final long NEW_SHARED_TEXTURE_WITH_HANDLE = ObjC.sel("newSharedTextureWithHandle:");
    private static final long NEW_TENSOR_WITH_DESCRIPTOR_ERROR = ObjC.sel("newTensorWithDescriptor:error:");
    private static final long NEW_TEXTURE_VIEW_POOL_WITH_DESCRIPTOR_ERROR = ObjC.sel("newTextureViewPoolWithDescriptor:error:");
    private static final long NEW_TEXTURE_WITH_DESCRIPTOR = ObjC.sel("newTextureWithDescriptor:");
    private static final long PEER_COUNT = ObjC.sel("peerCount");
    private static final long PEER_GROUP_ID = ObjC.sel("peerGroupID");
    private static final long PEER_INDEX = ObjC.sel("peerIndex");
    private static final long QUERY_TIMESTAMP_FREQUENCY = ObjC.sel("queryTimestampFrequency");
    private static final long READ_WRITE_TEXTURE_SUPPORT = ObjC.sel("readWriteTextureSupport");
    private static final long RECOMMENDED_MAX_WORKING_SET_SIZE = ObjC.sel("recommendedMaxWorkingSetSize");
    private static final long REGISTRY_ID = ObjC.sel("registryID");
    private static final long SAMPLE_TIMESTAMPS_GPU_TIMESTAMP = ObjC.sel("sampleTimestamps:gpuTimestamp:");
    private static final long SIZE_OF_COUNTER_HEAP_ENTRY = ObjC.sel("sizeOfCounterHeapEntry:");
    private static final long SPARSE_TILE_SIZE_IN_BYTES = ObjC.sel("sparseTileSizeInBytes");
    private static final long SUPPORTS32_BIT_FLOAT_FILTERING = ObjC.sel("supports32BitFloatFiltering");
    private static final long SUPPORTS32_BIT_MSAA = ObjC.sel("supports32BitMSAA");
    private static final long SUPPORTS_BC_TEXTURE_COMPRESSION = ObjC.sel("supportsBCTextureCompression");
    private static final long SUPPORTS_COUNTER_SAMPLING = ObjC.sel("supportsCounterSampling:");
    private static final long SUPPORTS_DYNAMIC_LIBRARIES = ObjC.sel("supportsDynamicLibraries");
    private static final long SUPPORTS_FAMILY = ObjC.sel("supportsFamily:");
    private static final long SUPPORTS_FUNCTION_POINTERS = ObjC.sel("supportsFunctionPointers");
    private static final long SUPPORTS_FUNCTION_POINTERS_FROM_RENDER = ObjC.sel("supportsFunctionPointersFromRender");
    private static final long SUPPORTS_PRIMITIVE_MOTION_BLUR = ObjC.sel("supportsPrimitiveMotionBlur");
    private static final long SUPPORTS_PULL_MODEL_INTERPOLATION = ObjC.sel("supportsPullModelInterpolation");
    private static final long SUPPORTS_QUERY_TEXTURE_LOD = ObjC.sel("supportsQueryTextureLOD");
    private static final long SUPPORTS_RASTERIZATION_RATE_MAP_WITH_LAYER_COUNT = ObjC.sel("supportsRasterizationRateMapWithLayerCount:");
    private static final long SUPPORTS_RAYTRACING = ObjC.sel("supportsRaytracing");
    private static final long SUPPORTS_RAYTRACING_FROM_RENDER = ObjC.sel("supportsRaytracingFromRender");
    private static final long SUPPORTS_RENDER_DYNAMIC_LIBRARIES = ObjC.sel("supportsRenderDynamicLibraries");
    private static final long SUPPORTS_SHADER_BARYCENTRIC_COORDINATES = ObjC.sel("supportsShaderBarycentricCoordinates");
    private static final long SUPPORTS_TEXTURE_SAMPLE_COUNT = ObjC.sel("supportsTextureSampleCount:");

    private static final MethodHandle FAMILY = handle(ObjC.BOOL, ObjC.LONG);
    private static final MethodHandle TIMESTAMPS = handle(null, ValueLayout.ADDRESS, ValueLayout.ADDRESS);
    private static final MethodHandle P_LL = handle(ObjC.PTR, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle P_ALL = handle(ObjC.PTR, ValueLayout.ADDRESS, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle P_ALLP = handle(ObjC.PTR, ValueLayout.ADDRESS, ObjC.LONG, ObjC.LONG, ObjC.PTR);
    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);
    private static final MethodHandle P_PLL = handle(ObjC.PTR, ObjC.PTR, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle P_PA = handle(ObjC.PTR, ObjC.PTR, ValueLayout.ADDRESS);
    private static final MethodHandle P_PLA = handle(ObjC.PTR, ObjC.PTR, ObjC.LONG, ValueLayout.ADDRESS);
    private static final MethodHandle P_PPA = handle(ObjC.PTR, ObjC.PTR, ObjC.PTR, ValueLayout.ADDRESS);
    private static final MethodHandle P_PLAA = handle(ObjC.PTR, ObjC.PTR, ObjC.LONG, ValueLayout.ADDRESS,
            ValueLayout.ADDRESS);
    private static final MethodHandle L_L = handle(ObjC.LONG, ObjC.LONG);
    private static final MethodHandle B_L = handle(ObjC.BOOL, ObjC.LONG);
    private static final MethodHandle SA_LL = structHandle(MTLSizeAndAlign.LAYOUT, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle SA_P = structHandle(MTLSizeAndAlign.LAYOUT, ObjC.PTR);
    private static final MethodHandle SA_L = structHandle(MTLSizeAndAlign.LAYOUT, ObjC.LONG);
    private static final MethodHandle SIZE = structHandle(MTLSize.LAYOUT);
    private static final MethodHandle SIZES_P = structHandle(MTLAccelerationStructureSizes.LAYOUT, ObjC.PTR);

    public static final long MTLGPUFamilyApple7 = 1007;
    public static final long MTLGPUFamilyApple9 = 1009;
    public static final long MTLGPUFamilyMetal3 = 5001;
    public static final long MTLDeviceLocationBuiltIn = 0;
    public static final long MTLDeviceLocationSlot = 1;
    public static final long MTLDeviceLocationExternal = 2;
    public static final long MTLDeviceLocationUnspecified = -1;
    public static final long MTLReadWriteTextureTierNone = 0;
    public static final long MTLReadWriteTextureTier1 = 1;
    public static final long MTLReadWriteTextureTier2 = 2;
    public static final long MTLArgumentBuffersTier1 = 0;
    public static final long MTLArgumentBuffersTier2 = 1;

    private MTLDevice(long id) {
        super(id);
    }

    public static MTLDevice of(long id) {
        return new MTLDevice(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    public long registryID() {
        return sendLong(id, REGISTRY_ID);
    }

    @SneakyThrows
    public MemorySegment maxThreadsPerThreadgroup(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, MAX_THREADS_PER_THREADGROUP);
    }

    public boolean isLowPower() {
        return sendBool(id, IS_LOW_POWER);
    }

    public boolean isHeadless() {
        return sendBool(id, IS_HEADLESS);
    }

    public boolean isRemovable() {
        return sendBool(id, IS_REMOVABLE);
    }

    public boolean hasUnifiedMemory() {
        return sendBool(id, HAS_UNIFIED_MEMORY);
    }

    public long recommendedMaxWorkingSetSize() {
        return sendLong(id, RECOMMENDED_MAX_WORKING_SET_SIZE);
    }

    public long location() {
        return sendLong(id, LOCATION);
    }

    public long locationNumber() {
        return sendLong(id, LOCATION_NUMBER);
    }

    public long maxTransferRate() {
        return sendLong(id, MAX_TRANSFER_RATE);
    }

    public boolean isDepth24Stencil8PixelFormatSupported() {
        return sendBool(id, IS_DEPTH24_STENCIL8_PIXEL_FORMAT_SUPPORTED);
    }

    public long readWriteTextureSupport() {
        return sendLong(id, READ_WRITE_TEXTURE_SUPPORT);
    }

    @SneakyThrows
    public MTLArgumentEncoder newArgumentEncoderWithArguments(NSArray arguments) {
        return MTLArgumentEncoder.of((long) P_P.invokeExact(id, NEW_ARGUMENT_ENCODER_WITH_ARGUMENTS,
                arguments.getId()));
    }

    public long argumentBuffersSupport() {
        return sendLong(id, ARGUMENT_BUFFERS_SUPPORT);
    }

    public boolean areRasterOrderGroupsSupported() {
        return sendBool(id, ARE_RASTER_ORDER_GROUPS_SUPPORTED);
    }

    public boolean supports32BitFloatFiltering() {
        return sendBool(id, SUPPORTS32_BIT_FLOAT_FILTERING);
    }

    public boolean supports32BitMSAA() {
        return sendBool(id, SUPPORTS32_BIT_MSAA);
    }

    public boolean supportsQueryTextureLOD() {
        return sendBool(id, SUPPORTS_QUERY_TEXTURE_LOD);
    }

    public boolean supportsBCTextureCompression() {
        return sendBool(id, SUPPORTS_BC_TEXTURE_COMPRESSION);
    }

    public boolean supportsPullModelInterpolation() {
        return sendBool(id, SUPPORTS_PULL_MODEL_INTERPOLATION);
    }

    public boolean areBarycentricCoordsSupported() {
        return sendBool(id, ARE_BARYCENTRIC_COORDS_SUPPORTED);
    }

    public boolean supportsShaderBarycentricCoordinates() {
        return sendBool(id, SUPPORTS_SHADER_BARYCENTRIC_COORDINATES);
    }

    public long currentAllocatedSize() {
        return sendLong(id, CURRENT_ALLOCATED_SIZE);
    }

    @SneakyThrows
    public MTLCommandQueue newCommandQueueWithDescriptor(MTLCommandQueueDescriptor descriptor) {
        return MTLCommandQueue.of((long) P_P.invokeExact(id, NEW_COMMAND_QUEUE_WITH_DESCRIPTOR,
                descriptor.getId()));
    }

    @SneakyThrows
    public MTLRenderPipelineState newRenderPipelineStateWithTileDescriptor(
            MTLTileRenderPipelineDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PLAA.invokeExact(id,
                    NEW_RENDER_PIPELINE_STATE_WITH_TILE_DESCRIPTOR_OPTIONS_REFLECTION_ERROR,
                    descriptor.getId(), 0L, MemorySegment.NULL, error);
            NSError.check(error, "newRenderPipelineStateWithTileDescriptor:options:reflection:error:");
            return MTLRenderPipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLResidencySet newResidencySetWithDescriptor(MTLResidencySetDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long set = (long) P_PA.invokeExact(id, NEW_RESIDENCY_SET_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newResidencySetWithDescriptor:error:");
            return MTLResidencySet.of(set);
        }
    }

    @SneakyThrows
    public MTLIOCommandQueue newIOCommandQueueWithDescriptor(MTLIOCommandQueueDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long queue = (long) P_PA.invokeExact(id, NEW_IO_COMMAND_QUEUE_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newIOCommandQueueWithDescriptor:error:");
            return MTLIOCommandQueue.of(queue);
        }
    }

    @SneakyThrows
    public MTLIOFileHandle newIOFileHandleWithURL(NSURL url) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long handle = (long) P_PA.invokeExact(id, NEW_IO_FILE_HANDLE_WITH_URL_ERROR, url.getId(), error);
            NSError.check(error, "newIOFileHandleWithURL:error:");
            return MTLIOFileHandle.of(handle);
        }
    }

    @SneakyThrows
    public MTLIOFileHandle newIOFileHandleWithURL(NSURL url, long compressionMethod) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long handle = (long) P_PLA.invokeExact(id, NEW_IO_FILE_HANDLE_WITH_URL_COMPRESSION_METHOD_ERROR,
                    url.getId(), compressionMethod, error);
            NSError.check(error, "newIOFileHandleWithURL:compressionMethod:error:");
            return MTLIOFileHandle.of(handle);
        }
    }

    @SneakyThrows
    public MTLTensor newTensorWithDescriptor(MTLTensorDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long tensor = (long) P_PA.invokeExact(id, NEW_TENSOR_WITH_DESCRIPTOR_ERROR, descriptor.getId(),
                    error);
            NSError.check(error, "newTensorWithDescriptor:error:");
            return MTLTensor.of(tensor);
        }
    }

    @SneakyThrows
    public MTLTextureViewPool newTextureViewPoolWithDescriptor(MTLResourceViewPoolDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long pool = (long) P_PA.invokeExact(id, NEW_TEXTURE_VIEW_POOL_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newTextureViewPoolWithDescriptor:error:");
            return MTLTextureViewPool.of(pool);
        }
    }

    public MTLFence newFence() {
        return MTLFence.of(sendPtr(id, NEW_FENCE));
    }

    public MTLEvent newEvent() {
        return MTLEvent.of(sendPtr(id, NEW_EVENT));
    }

    public MTLSharedEvent newSharedEvent() {
        return MTLSharedEvent.of(sendPtr(id, NEW_SHARED_EVENT));
    }

    @SneakyThrows
    public MTLSharedEvent newSharedEventWithHandle(MTLSharedEventHandle handle) {
        return MTLSharedEvent.of((long) P_P.invokeExact(id, NEW_SHARED_EVENT_WITH_HANDLE, handle.getId()));
    }

    public MTLCommandQueue newCommandQueue() {
        return MTLCommandQueue.of(sendPtr(id, NEW_COMMAND_QUEUE));
    }

    @SneakyThrows
    public MemorySegment heapTextureSizeAndAlignWithDescriptor(SegmentAllocator allocator,
            MTLTextureDescriptor descriptor) {
        return (MemorySegment) SA_P.invokeExact(allocator, id, HEAP_TEXTURE_SIZE_AND_ALIGN_WITH_DESCRIPTOR,
                descriptor.getId());
    }

    @SneakyThrows
    public MemorySegment heapBufferSizeAndAlignWithLength(SegmentAllocator allocator, long length, long options) {
        return (MemorySegment) SA_LL.invokeExact(allocator, id, HEAP_BUFFER_SIZE_AND_ALIGN_WITH_LENGTH_OPTIONS,
                length, options);
    }

    @SneakyThrows
    public MTLHeap newHeapWithDescriptor(MTLHeapDescriptor descriptor) {
        return MTLHeap.of((long) P_P.invokeExact(id, NEW_HEAP_WITH_DESCRIPTOR, descriptor.getId()));
    }

    @SneakyThrows
    public MTLBuffer newBufferWithLength(long length, long options) {
        return MTLBuffer.of((long) P_LL.invokeExact(id, NEW_BUFFER_WITH_LENGTH_OPTIONS, length, options));
    }

    @SneakyThrows
    public MTLBuffer newBufferWithBytes(MemorySegment pointer, long length, long options) {
        return MTLBuffer.of((long) P_ALL.invokeExact(id, NEW_BUFFER_WITH_BYTES_LENGTH_OPTIONS, pointer,
                length, options));
    }

    @SneakyThrows
    public MTLBuffer newBufferWithBytesNoCopy(MemorySegment pointer, long length, long options, long deallocator) {
        return MTLBuffer.of((long) P_ALLP.invokeExact(id,
                NEW_BUFFER_WITH_BYTES_NO_COPY_LENGTH_OPTIONS_DEALLOCATOR, pointer, length, options,
                deallocator));
    }

    @SneakyThrows
    public MTLTexture newTextureWithDescriptor(MTLTextureDescriptor descriptor) {
        return MTLTexture.of((long) P_P.invokeExact(id, NEW_TEXTURE_WITH_DESCRIPTOR, descriptor.getId()));
    }

    @SneakyThrows
    public MTLTexture newSharedTextureWithDescriptor(MTLTextureDescriptor descriptor) {
        return MTLTexture.of((long) P_P.invokeExact(id, NEW_SHARED_TEXTURE_WITH_DESCRIPTOR,
                descriptor.getId()));
    }

    @SneakyThrows
    public MTLSamplerState newSamplerStateWithDescriptor(MTLSamplerDescriptor descriptor) {
        return MTLSamplerState.of((long) P_P.invokeExact(id, NEW_SAMPLER_STATE_WITH_DESCRIPTOR,
                descriptor.getId()));
    }

    @SneakyThrows
    public boolean supportsFamily(long family) {
        return (boolean) FAMILY.invokeExact(id, SUPPORTS_FAMILY, family);
    }

    @SneakyThrows
    public boolean supportsTextureSampleCount(long sampleCount) {
        return (boolean) B_L.invokeExact(id, SUPPORTS_TEXTURE_SAMPLE_COUNT, sampleCount);
    }

    @SneakyThrows
    public long minimumLinearTextureAlignmentForPixelFormat(long format) {
        return (long) L_L.invokeExact(id, MINIMUM_LINEAR_TEXTURE_ALIGNMENT_FOR_PIXEL_FORMAT, format);
    }

    @SneakyThrows
    public long minimumTextureBufferAlignmentForPixelFormat(long format) {
        return (long) L_L.invokeExact(id, MINIMUM_TEXTURE_BUFFER_ALIGNMENT_FOR_PIXEL_FORMAT, format);
    }

    public long maxThreadgroupMemoryLength() {
        return sendLong(id, MAX_THREADGROUP_MEMORY_LENGTH);
    }

    public long maxArgumentBufferSamplerCount() {
        return sendLong(id, MAX_ARGUMENT_BUFFER_SAMPLER_COUNT);
    }

    public boolean areProgrammableSamplePositionsSupported() {
        return sendBool(id, ARE_PROGRAMMABLE_SAMPLE_POSITIONS_SUPPORTED);
    }

    @SneakyThrows
    public MTLIndirectCommandBuffer newIndirectCommandBufferWithDescriptor(
            MTLIndirectCommandBufferDescriptor descriptor, long maxCount, long options) {
        return MTLIndirectCommandBuffer.of((long) P_PLL.invokeExact(id,
                NEW_INDIRECT_COMMAND_BUFFER_WITH_DESCRIPTOR_MAX_COMMAND_COUNT_OPTIONS, descriptor.getId(),
                maxCount, options));
    }

    public long peerGroupID() {
        return sendLong(id, PEER_GROUP_ID);
    }

    public long peerIndex() {
        return sendLong(id, PEER_INDEX) & 0xffffffffL;
    }

    public long peerCount() {
        return sendLong(id, PEER_COUNT) & 0xffffffffL;
    }

    public long sparseTileSizeInBytes() {
        return sendLong(id, SPARSE_TILE_SIZE_IN_BYTES);
    }

    public long maxBufferLength() {
        return sendLong(id, MAX_BUFFER_LENGTH);
    }

    public NSArray counterSets() {
        return NSArray.of(sendPtr(id, COUNTER_SETS));
    }

    public MTLCounterSet counterSetAtIndex(long index) {
        return MTLCounterSet.of(counterSets().objectAtIndex(index));
    }

    @SneakyThrows
    public MTLCounterSampleBuffer newCounterSampleBufferWithDescriptor(MTLCounterSampleBufferDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long buffer = (long) P_PA.invokeExact(id, NEW_COUNTER_SAMPLE_BUFFER_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newCounterSampleBufferWithDescriptor:error:");
            return MTLCounterSampleBuffer.of(buffer);
        }
    }

    @SneakyThrows
    public MTLLogState newLogStateWithDescriptor(MTLLogStateDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PA.invokeExact(id, NEW_LOG_STATE_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newLogStateWithDescriptor:error:");
            return MTLLogState.of(state);
        }
    }

    @SneakyThrows
    public void sampleTimestamps(MemorySegment cpu, MemorySegment gpu) {
        TIMESTAMPS.invokeExact(id, SAMPLE_TIMESTAMPS_GPU_TIMESTAMP, cpu, gpu);
    }

    @SneakyThrows
    public boolean supportsCounterSampling(long samplingPoint) {
        return (boolean) B_L.invokeExact(id, SUPPORTS_COUNTER_SAMPLING, samplingPoint);
    }

    @SneakyThrows
    public MTLDynamicLibrary newDynamicLibrary(MTLLibrary library) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long result = (long) P_PA.invokeExact(id, NEW_DYNAMIC_LIBRARY_ERROR, library.getId(), error);
            NSError.check(error, "newDynamicLibrary:error:");
            return MTLDynamicLibrary.of(result);
        }
    }

    @SneakyThrows
    public MTLDynamicLibrary newDynamicLibraryWithURL(NSURL url) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long result = (long) P_PA.invokeExact(id, NEW_DYNAMIC_LIBRARY_WITH_URL_ERROR, url.getId(), error);
            NSError.check(error, "newDynamicLibraryWithURL:error:");
            return MTLDynamicLibrary.of(result);
        }
    }

    @SneakyThrows
    public MTLBinaryArchive newBinaryArchiveWithDescriptor(MTLBinaryArchiveDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long archive = (long) P_PA.invokeExact(id, NEW_BINARY_ARCHIVE_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newBinaryArchiveWithDescriptor:error:");
            return MTLBinaryArchive.of(archive);
        }
    }

    public boolean supportsDynamicLibraries() {
        return sendBool(id, SUPPORTS_DYNAMIC_LIBRARIES);
    }

    public boolean supportsRenderDynamicLibraries() {
        return sendBool(id, SUPPORTS_RENDER_DYNAMIC_LIBRARIES);
    }

    public boolean supportsRaytracing() {
        return sendBool(id, SUPPORTS_RAYTRACING);
    }

    @SneakyThrows
    public MTLAccelerationStructure newAccelerationStructureWithSize(long size) {
        return MTLAccelerationStructure.of(
                (long) L_L.invokeExact(id, NEW_ACCELERATION_STRUCTURE_WITH_SIZE, size));
    }

    @SneakyThrows
    public MTLAccelerationStructure newAccelerationStructureWithDescriptor(
            MTLAccelerationStructureDescriptor descriptor) {
        return MTLAccelerationStructure.of(
                (long) P_P.invokeExact(id, NEW_ACCELERATION_STRUCTURE_WITH_DESCRIPTOR, descriptor.getId()));
    }

    @SneakyThrows
    public MemorySegment accelerationStructureSizesWithDescriptor(SegmentAllocator allocator,
            MTLAccelerationStructureDescriptor descriptor) {
        return (MemorySegment) SIZES_P.invokeExact(allocator, id,
                ACCELERATION_STRUCTURE_SIZES_WITH_DESCRIPTOR, descriptor.getId());
    }

    @SneakyThrows
    public MemorySegment heapAccelerationStructureSizeAndAlignWithSize(SegmentAllocator allocator, long size) {
        return (MemorySegment) SA_L.invokeExact(allocator, id,
                HEAP_ACCELERATION_STRUCTURE_SIZE_AND_ALIGN_WITH_SIZE, size);
    }

    public boolean supportsFunctionPointers() {
        return sendBool(id, SUPPORTS_FUNCTION_POINTERS);
    }

    public boolean supportsFunctionPointersFromRender() {
        return sendBool(id, SUPPORTS_FUNCTION_POINTERS_FROM_RENDER);
    }

    public boolean supportsRaytracingFromRender() {
        return sendBool(id, SUPPORTS_RAYTRACING_FROM_RENDER);
    }

    public boolean supportsPrimitiveMotionBlur() {
        return sendBool(id, SUPPORTS_PRIMITIVE_MOTION_BLUR);
    }

    public long maximumConcurrentCompilationTaskCount() {
        return sendLong(id, MAXIMUM_CONCURRENT_COMPILATION_TASK_COUNT);
    }

    @SneakyThrows
    public MTLLibrary newLibraryWithSource(NSString source, MTLCompileOptions options) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long library = (long) P_PPA.invokeExact(id, NEW_LIBRARY_WITH_SOURCE_OPTIONS_ERROR,
                    source.getId(), options.getId(), error);
            NSError.check(error, "newLibraryWithSource:options:error:");
            return MTLLibrary.of(library);
        }
    }

    public MTLLibrary newDefaultLibrary() {
        return MTLLibrary.of(sendPtr(id, NEW_DEFAULT_LIBRARY));
    }

    @SneakyThrows
    public MTLRenderPipelineState newRenderPipelineStateWithDescriptor(MTLRenderPipelineDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PA.invokeExact(id, NEW_RENDER_PIPELINE_STATE_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newRenderPipelineStateWithDescriptor:error:");
            return MTLRenderPipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLRenderPipelineState newRenderPipelineStateWithDescriptor(MTLRenderPipelineDescriptor descriptor,
            long options, MTLRenderPipelineReflection[] reflection) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            var slot = arena.allocate(ObjC.PTR);
            long state = (long) P_PLAA.invokeExact(id,
                    NEW_RENDER_PIPELINE_STATE_WITH_DESCRIPTOR_OPTIONS_REFLECTION_ERROR, descriptor.getId(),
                    options, slot, error);
            NSError.check(error, "newRenderPipelineStateWithDescriptor:options:reflection:error:");
            reflection[0] = MTLRenderPipelineReflection.of(slot.get(ObjC.PTR, 0));
            return MTLRenderPipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLComputePipelineState newComputePipelineStateWithFunction(MTLFunction function, long options,
            MTLComputePipelineReflection[] reflection) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            var slot = arena.allocate(ObjC.PTR);
            long state = (long) P_PLAA.invokeExact(id,
                    NEW_COMPUTE_PIPELINE_STATE_WITH_FUNCTION_OPTIONS_REFLECTION_ERROR, function.getId(),
                    options, slot, error);
            NSError.check(error, "newComputePipelineStateWithFunction:options:reflection:error:");
            reflection[0] = MTLComputePipelineReflection.of(slot.get(ObjC.PTR, 0));
            return MTLComputePipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLComputePipelineState newComputePipelineStateWithDescriptor(MTLComputePipelineDescriptor descriptor,
            long options, MTLComputePipelineReflection[] reflection) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            var slot = arena.allocate(ObjC.PTR);
            long state = (long) P_PLAA.invokeExact(id,
                    NEW_COMPUTE_PIPELINE_STATE_WITH_DESCRIPTOR_OPTIONS_REFLECTION_ERROR, descriptor.getId(),
                    options, slot, error);
            NSError.check(error, "newComputePipelineStateWithDescriptor:options:reflection:error:");
            reflection[0] = MTLComputePipelineReflection.of(slot.get(ObjC.PTR, 0));
            return MTLComputePipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLTexture newSharedTextureWithHandle(MTLSharedTextureHandle handle) {
        return MTLTexture.of((long) P_P.invokeExact(id, NEW_SHARED_TEXTURE_WITH_HANDLE, handle.getId()));
    }

    public MTLArchitecture architecture() {
        return MTLArchitecture.of(sendPtr(id, ARCHITECTURE));
    }

    @SneakyThrows
    public MTLRenderPipelineState newRenderPipelineStateWithMeshDescriptor(
            MTLMeshRenderPipelineDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PLAA.invokeExact(id,
                    NEW_RENDER_PIPELINE_STATE_WITH_MESH_DESCRIPTOR_OPTIONS_REFLECTION_ERROR,
                    descriptor.getId(), 0L, MemorySegment.NULL, error);
            NSError.check(error, "newRenderPipelineStateWithMeshDescriptor:options:reflection:error:");
            return MTLRenderPipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLComputePipelineState newComputePipelineStateWithFunction(MTLFunction function) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PA.invokeExact(id, NEW_COMPUTE_PIPELINE_STATE_WITH_FUNCTION_ERROR,
                    function.getId(), error);
            NSError.check(error, "newComputePipelineStateWithFunction:error:");
            return MTLComputePipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLComputePipelineState newComputePipelineStateWithDescriptor(MTLComputePipelineDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PLAA.invokeExact(id,
                    NEW_COMPUTE_PIPELINE_STATE_WITH_DESCRIPTOR_OPTIONS_REFLECTION_ERROR, descriptor.getId(),
                    0L, MemorySegment.NULL, error);
            NSError.check(error, "newComputePipelineStateWithDescriptor:options:reflection:error:");
            return MTLComputePipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLDepthStencilState newDepthStencilStateWithDescriptor(MTLDepthStencilDescriptor descriptor) {
        return MTLDepthStencilState.of((long) P_P.invokeExact(id, NEW_DEPTH_STENCIL_STATE_WITH_DESCRIPTOR,
                descriptor.getId()));
    }

    @SneakyThrows
    public MTLRasterizationRateMap newRasterizationRateMapWithDescriptor(
            MTLRasterizationRateMapDescriptor descriptor) {
        return MTLRasterizationRateMap.of((long) P_P.invokeExact(id,
                NEW_RASTERIZATION_RATE_MAP_WITH_DESCRIPTOR, descriptor.getId()));
    }

    @SneakyThrows
    public boolean supportsRasterizationRateMapWithLayerCount(long layerCount) {
        return (boolean) B_L.invokeExact(id, SUPPORTS_RASTERIZATION_RATE_MAP_WITH_LAYER_COUNT, layerCount);
    }

    public MTL4CommandAllocator newCommandAllocator() {
        return MTL4CommandAllocator.of(sendPtr(id, NEW_COMMAND_ALLOCATOR));
    }

    @SneakyThrows
    public MTL4CommandAllocator newCommandAllocatorWithDescriptor(MTL4CommandAllocatorDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long allocator = (long) P_PA.invokeExact(id, NEW_COMMAND_ALLOCATOR_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newCommandAllocatorWithDescriptor:error:");
            return MTL4CommandAllocator.of(allocator);
        }
    }

    public MTL4CommandQueue newMTL4CommandQueue() {
        return MTL4CommandQueue.of(sendPtr(id, NEW_MTL_4_COMMAND_QUEUE));
    }

    @SneakyThrows
    public MTL4CommandQueue newMTL4CommandQueueWithDescriptor(MTL4CommandQueueDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long queue = (long) P_PA.invokeExact(id, NEW_MTL_4_COMMAND_QUEUE_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newMTL4CommandQueueWithDescriptor:error:");
            return MTL4CommandQueue.of(queue);
        }
    }

    public MTL4CommandBuffer newCommandBuffer() {
        return MTL4CommandBuffer.of(sendPtr(id, NEW_COMMAND_BUFFER));
    }

    @SneakyThrows
    public MTL4ArgumentTable newArgumentTableWithDescriptor(MTL4ArgumentTableDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long table = (long) P_PA.invokeExact(id, NEW_ARGUMENT_TABLE_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newArgumentTableWithDescriptor:error:");
            return MTL4ArgumentTable.of(table);
        }
    }

    @SneakyThrows
    public MTL4Compiler newCompilerWithDescriptor(MTL4CompilerDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long compiler = (long) P_PA.invokeExact(id, NEW_COMPILER_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newCompilerWithDescriptor:error:");
            return MTL4Compiler.of(compiler);
        }
    }

    @SneakyThrows
    public MTL4Archive newArchiveWithURL(NSURL url) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long archive = (long) P_PA.invokeExact(id, NEW_ARCHIVE_WITH_URL_ERROR, url.getId(), error);
            NSError.check(error, "newArchiveWithURL:error:");
            return MTL4Archive.of(archive);
        }
    }

    @SneakyThrows
    public MTL4PipelineDataSetSerializer newPipelineDataSetSerializerWithDescriptor(
            MTL4PipelineDataSetSerializerDescriptor descriptor) {
        return MTL4PipelineDataSetSerializer.of((long) P_P.invokeExact(id,
                NEW_PIPELINE_DATA_SET_SERIALIZER_WITH_DESCRIPTOR, descriptor.getId()));
    }

    @SneakyThrows
    public MTL4CounterHeap newCounterHeapWithDescriptor(MTL4CounterHeapDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long heap = (long) P_PA.invokeExact(id, NEW_COUNTER_HEAP_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newCounterHeapWithDescriptor:error:");
            return MTL4CounterHeap.of(heap);
        }
    }

    @SneakyThrows
    public long sizeOfCounterHeapEntry(long type) {
        return (long) L_L.invokeExact(id, SIZE_OF_COUNTER_HEAP_ENTRY, type);
    }

    public long queryTimestampFrequency() {
        return sendLong(id, QUERY_TIMESTAMP_FREQUENCY);
    }

    @SneakyThrows
    public MTLFunctionHandle functionHandleWithBinaryFunction(MTL4BinaryFunction function) {
        return MTLFunctionHandle.of((long) P_P.invokeExact(id, FUNCTION_HANDLE_WITH_BINARY_FUNCTION,
                function.getId()));
    }
}
