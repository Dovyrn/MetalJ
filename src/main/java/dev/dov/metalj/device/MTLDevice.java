package dev.dov.metalj.device;

import dev.dov.metalj.arguments.MTLArgumentEncoder;
import dev.dov.metalj.io.MTLIOCommandQueue;
import dev.dov.metalj.io.MTLIOCommandQueueDescriptor;
import dev.dov.metalj.io.MTLIOFileHandle;
import dev.dov.metalj.libraries.MTLBinaryArchive;
import dev.dov.metalj.libraries.MTLBinaryArchiveDescriptor;
import dev.dov.metalj.libraries.MTLDynamicLibrary;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.raytracing.MTLAccelerationStructure;
import dev.dov.metalj.residency.MTLResidencySet;
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
import dev.dov.metalj.pipelines.shaders.MTLLibrary;
import dev.dov.metalj.pipelines.render.MTLMeshRenderPipelineDescriptor;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineDescriptor;
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
import dev.dov.metalj.resources.textures.MTLTexture;
import dev.dov.metalj.resources.textures.MTLTextureDescriptor;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLDevice extends NSObject {
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
        return NSString.of(sendPtr(id, "name"));
    }

    public long registryID() {
        return sendLong(id, "registryID");
    }

    @SneakyThrows
    public MemorySegment maxThreadsPerThreadgroup(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, ObjC.sel("maxThreadsPerThreadgroup"));
    }

    public boolean isLowPower() {
        return sendBool(id, "isLowPower");
    }

    public boolean isHeadless() {
        return sendBool(id, "isHeadless");
    }

    public boolean isRemovable() {
        return sendBool(id, "isRemovable");
    }

    public boolean hasUnifiedMemory() {
        return sendBool(id, "hasUnifiedMemory");
    }

    public long recommendedMaxWorkingSetSize() {
        return sendLong(id, "recommendedMaxWorkingSetSize");
    }

    public long location() {
        return sendLong(id, "location");
    }

    public long locationNumber() {
        return sendLong(id, "locationNumber");
    }

    public long maxTransferRate() {
        return sendLong(id, "maxTransferRate");
    }

    public boolean isDepth24Stencil8PixelFormatSupported() {
        return sendBool(id, "isDepth24Stencil8PixelFormatSupported");
    }

    public long readWriteTextureSupport() {
        return sendLong(id, "readWriteTextureSupport");
    }

    @SneakyThrows
    public MTLArgumentEncoder newArgumentEncoderWithArguments(NSArray arguments) {
        return MTLArgumentEncoder.of((long) P_P.invokeExact(id, ObjC.sel("newArgumentEncoderWithArguments:"),
                arguments.getId()));
    }

    public long argumentBuffersSupport() {
        return sendLong(id, "argumentBuffersSupport");
    }

    public boolean areRasterOrderGroupsSupported() {
        return sendBool(id, "areRasterOrderGroupsSupported");
    }

    public boolean supports32BitFloatFiltering() {
        return sendBool(id, "supports32BitFloatFiltering");
    }

    public boolean supports32BitMSAA() {
        return sendBool(id, "supports32BitMSAA");
    }

    public boolean supportsQueryTextureLOD() {
        return sendBool(id, "supportsQueryTextureLOD");
    }

    public boolean supportsBCTextureCompression() {
        return sendBool(id, "supportsBCTextureCompression");
    }

    public boolean supportsPullModelInterpolation() {
        return sendBool(id, "supportsPullModelInterpolation");
    }

    public boolean areBarycentricCoordsSupported() {
        return sendBool(id, "areBarycentricCoordsSupported");
    }

    public boolean supportsShaderBarycentricCoordinates() {
        return sendBool(id, "supportsShaderBarycentricCoordinates");
    }

    public long currentAllocatedSize() {
        return sendLong(id, "currentAllocatedSize");
    }

    @SneakyThrows
    public MTLCommandQueue newCommandQueueWithDescriptor(MTLCommandQueueDescriptor descriptor) {
        return MTLCommandQueue.of((long) P_P.invokeExact(id, ObjC.sel("newCommandQueueWithDescriptor:"),
                descriptor.getId()));
    }

    @SneakyThrows
    public MTLRenderPipelineState newRenderPipelineStateWithTileDescriptor(
            MTLTileRenderPipelineDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PLAA.invokeExact(id,
                    ObjC.sel("newRenderPipelineStateWithTileDescriptor:options:reflection:error:"),
                    descriptor.getId(), 0L, MemorySegment.NULL, error);
            NSError.check(error, "newRenderPipelineStateWithTileDescriptor:options:reflection:error:");
            return MTLRenderPipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLResidencySet newResidencySetWithDescriptor(MTLResidencySetDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long set = (long) P_PA.invokeExact(id, ObjC.sel("newResidencySetWithDescriptor:error:"),
                    descriptor.getId(), error);
            NSError.check(error, "newResidencySetWithDescriptor:error:");
            return MTLResidencySet.of(set);
        }
    }

    @SneakyThrows
    public MTLIOCommandQueue newIOCommandQueueWithDescriptor(MTLIOCommandQueueDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long queue = (long) P_PA.invokeExact(id, ObjC.sel("newIOCommandQueueWithDescriptor:error:"),
                    descriptor.getId(), error);
            NSError.check(error, "newIOCommandQueueWithDescriptor:error:");
            return MTLIOCommandQueue.of(queue);
        }
    }

    @SneakyThrows
    public MTLIOFileHandle newIOFileHandleWithURL(NSURL url) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long handle = (long) P_PA.invokeExact(id, ObjC.sel("newIOFileHandleWithURL:error:"), url.getId(), error);
            NSError.check(error, "newIOFileHandleWithURL:error:");
            return MTLIOFileHandle.of(handle);
        }
    }

    @SneakyThrows
    public MTLIOFileHandle newIOFileHandleWithURL(NSURL url, long compressionMethod) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long handle = (long) P_PLA.invokeExact(id, ObjC.sel("newIOFileHandleWithURL:compressionMethod:error:"),
                    url.getId(), compressionMethod, error);
            NSError.check(error, "newIOFileHandleWithURL:compressionMethod:error:");
            return MTLIOFileHandle.of(handle);
        }
    }

    public MTLFence newFence() {
        return MTLFence.of(sendPtr(id, "newFence"));
    }

    public MTLEvent newEvent() {
        return MTLEvent.of(sendPtr(id, "newEvent"));
    }

    public MTLSharedEvent newSharedEvent() {
        return MTLSharedEvent.of(sendPtr(id, "newSharedEvent"));
    }

    @SneakyThrows
    public MTLSharedEvent newSharedEventWithHandle(MTLSharedEventHandle handle) {
        return MTLSharedEvent.of((long) P_P.invokeExact(id, ObjC.sel("newSharedEventWithHandle:"), handle.getId()));
    }

    public MTLCommandQueue newCommandQueue() {
        return MTLCommandQueue.of(sendPtr(id, "newCommandQueue"));
    }

    @SneakyThrows
    public MemorySegment heapTextureSizeAndAlignWithDescriptor(SegmentAllocator allocator,
            MTLTextureDescriptor descriptor) {
        return (MemorySegment) SA_P.invokeExact(allocator, id, ObjC.sel("heapTextureSizeAndAlignWithDescriptor:"),
                descriptor.getId());
    }

    @SneakyThrows
    public MemorySegment heapBufferSizeAndAlignWithLength(SegmentAllocator allocator, long length, long options) {
        return (MemorySegment) SA_LL.invokeExact(allocator, id, ObjC.sel("heapBufferSizeAndAlignWithLength:options:"),
                length, options);
    }

    @SneakyThrows
    public MTLHeap newHeapWithDescriptor(MTLHeapDescriptor descriptor) {
        return MTLHeap.of((long) P_P.invokeExact(id, ObjC.sel("newHeapWithDescriptor:"), descriptor.getId()));
    }

    @SneakyThrows
    public MTLBuffer newBufferWithLength(long length, long options) {
        return MTLBuffer.of((long) P_LL.invokeExact(id, ObjC.sel("newBufferWithLength:options:"), length, options));
    }

    @SneakyThrows
    public MTLBuffer newBufferWithBytes(MemorySegment pointer, long length, long options) {
        return MTLBuffer.of((long) P_ALL.invokeExact(id, ObjC.sel("newBufferWithBytes:length:options:"), pointer,
                length, options));
    }

    @SneakyThrows
    public MTLBuffer newBufferWithBytesNoCopy(MemorySegment pointer, long length, long options, long deallocator) {
        return MTLBuffer.of((long) P_ALLP.invokeExact(id,
                ObjC.sel("newBufferWithBytesNoCopy:length:options:deallocator:"), pointer, length, options,
                deallocator));
    }

    @SneakyThrows
    public MTLTexture newTextureWithDescriptor(MTLTextureDescriptor descriptor) {
        return MTLTexture.of((long) P_P.invokeExact(id, ObjC.sel("newTextureWithDescriptor:"), descriptor.getId()));
    }

    @SneakyThrows
    public MTLTexture newSharedTextureWithDescriptor(MTLTextureDescriptor descriptor) {
        return MTLTexture.of((long) P_P.invokeExact(id, ObjC.sel("newSharedTextureWithDescriptor:"),
                descriptor.getId()));
    }

    @SneakyThrows
    public MTLSamplerState newSamplerStateWithDescriptor(MTLSamplerDescriptor descriptor) {
        return MTLSamplerState.of((long) P_P.invokeExact(id, ObjC.sel("newSamplerStateWithDescriptor:"),
                descriptor.getId()));
    }

    @SneakyThrows
    public boolean supportsFamily(long family) {
        return (boolean) FAMILY.invokeExact(id, ObjC.sel("supportsFamily:"), family);
    }

    @SneakyThrows
    public boolean supportsTextureSampleCount(long sampleCount) {
        return (boolean) B_L.invokeExact(id, ObjC.sel("supportsTextureSampleCount:"), sampleCount);
    }

    @SneakyThrows
    public long minimumLinearTextureAlignmentForPixelFormat(long format) {
        return (long) L_L.invokeExact(id, ObjC.sel("minimumLinearTextureAlignmentForPixelFormat:"), format);
    }

    @SneakyThrows
    public long minimumTextureBufferAlignmentForPixelFormat(long format) {
        return (long) L_L.invokeExact(id, ObjC.sel("minimumTextureBufferAlignmentForPixelFormat:"), format);
    }

    public long maxThreadgroupMemoryLength() {
        return sendLong(id, "maxThreadgroupMemoryLength");
    }

    public long maxArgumentBufferSamplerCount() {
        return sendLong(id, "maxArgumentBufferSamplerCount");
    }

    public boolean areProgrammableSamplePositionsSupported() {
        return sendBool(id, "areProgrammableSamplePositionsSupported");
    }

    @SneakyThrows
    public MTLIndirectCommandBuffer newIndirectCommandBufferWithDescriptor(
            MTLIndirectCommandBufferDescriptor descriptor, long maxCount, long options) {
        return MTLIndirectCommandBuffer.of((long) P_PLL.invokeExact(id,
                ObjC.sel("newIndirectCommandBufferWithDescriptor:maxCommandCount:options:"), descriptor.getId(),
                maxCount, options));
    }

    public long peerGroupID() {
        return sendLong(id, "peerGroupID");
    }

    public long peerIndex() {
        return sendLong(id, "peerIndex") & 0xffffffffL;
    }

    public long peerCount() {
        return sendLong(id, "peerCount") & 0xffffffffL;
    }

    public long sparseTileSizeInBytes() {
        return sendLong(id, "sparseTileSizeInBytes");
    }

    public long maxBufferLength() {
        return sendLong(id, "maxBufferLength");
    }

    public NSArray counterSets() {
        return NSArray.of(sendPtr(id, "counterSets"));
    }

    public MTLCounterSet counterSetAtIndex(long index) {
        return MTLCounterSet.of(counterSets().objectAtIndex(index));
    }

    @SneakyThrows
    public MTLCounterSampleBuffer newCounterSampleBufferWithDescriptor(MTLCounterSampleBufferDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long buffer = (long) P_PA.invokeExact(id, ObjC.sel("newCounterSampleBufferWithDescriptor:error:"),
                    descriptor.getId(), error);
            NSError.check(error, "newCounterSampleBufferWithDescriptor:error:");
            return MTLCounterSampleBuffer.of(buffer);
        }
    }

    @SneakyThrows
    public MTLLogState newLogStateWithDescriptor(MTLLogStateDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PA.invokeExact(id, ObjC.sel("newLogStateWithDescriptor:error:"),
                    descriptor.getId(), error);
            NSError.check(error, "newLogStateWithDescriptor:error:");
            return MTLLogState.of(state);
        }
    }

    @SneakyThrows
    public void sampleTimestamps(MemorySegment cpu, MemorySegment gpu) {
        TIMESTAMPS.invokeExact(id, ObjC.sel("sampleTimestamps:gpuTimestamp:"), cpu, gpu);
    }

    @SneakyThrows
    public boolean supportsCounterSampling(long samplingPoint) {
        return (boolean) B_L.invokeExact(id, ObjC.sel("supportsCounterSampling:"), samplingPoint);
    }

    @SneakyThrows
    public MTLDynamicLibrary newDynamicLibrary(MTLLibrary library) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long result = (long) P_PA.invokeExact(id, ObjC.sel("newDynamicLibrary:error:"), library.getId(), error);
            NSError.check(error, "newDynamicLibrary:error:");
            return MTLDynamicLibrary.of(result);
        }
    }

    @SneakyThrows
    public MTLDynamicLibrary newDynamicLibraryWithURL(NSURL url) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long result = (long) P_PA.invokeExact(id, ObjC.sel("newDynamicLibraryWithURL:error:"), url.getId(), error);
            NSError.check(error, "newDynamicLibraryWithURL:error:");
            return MTLDynamicLibrary.of(result);
        }
    }

    @SneakyThrows
    public MTLBinaryArchive newBinaryArchiveWithDescriptor(MTLBinaryArchiveDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long archive = (long) P_PA.invokeExact(id, ObjC.sel("newBinaryArchiveWithDescriptor:error:"),
                    descriptor.getId(), error);
            NSError.check(error, "newBinaryArchiveWithDescriptor:error:");
            return MTLBinaryArchive.of(archive);
        }
    }

    public boolean supportsDynamicLibraries() {
        return sendBool(id, "supportsDynamicLibraries");
    }

    public boolean supportsRenderDynamicLibraries() {
        return sendBool(id, "supportsRenderDynamicLibraries");
    }

    public boolean supportsRaytracing() {
        return sendBool(id, "supportsRaytracing");
    }

    @SneakyThrows
    public MTLAccelerationStructure newAccelerationStructureWithSize(long size) {
        return MTLAccelerationStructure.of(
                (long) L_L.invokeExact(id, ObjC.sel("newAccelerationStructureWithSize:"), size));
    }

    @SneakyThrows
    public MTLAccelerationStructure newAccelerationStructureWithDescriptor(
            MTLAccelerationStructureDescriptor descriptor) {
        return MTLAccelerationStructure.of(
                (long) P_P.invokeExact(id, ObjC.sel("newAccelerationStructureWithDescriptor:"), descriptor.getId()));
    }

    @SneakyThrows
    public MemorySegment accelerationStructureSizesWithDescriptor(SegmentAllocator allocator,
            MTLAccelerationStructureDescriptor descriptor) {
        return (MemorySegment) SIZES_P.invokeExact(allocator, id,
                ObjC.sel("accelerationStructureSizesWithDescriptor:"), descriptor.getId());
    }

    @SneakyThrows
    public MemorySegment heapAccelerationStructureSizeAndAlignWithSize(SegmentAllocator allocator, long size) {
        return (MemorySegment) SA_L.invokeExact(allocator, id,
                ObjC.sel("heapAccelerationStructureSizeAndAlignWithSize:"), size);
    }

    public boolean supportsFunctionPointers() {
        return sendBool(id, "supportsFunctionPointers");
    }

    public boolean supportsFunctionPointersFromRender() {
        return sendBool(id, "supportsFunctionPointersFromRender");
    }

    public boolean supportsRaytracingFromRender() {
        return sendBool(id, "supportsRaytracingFromRender");
    }

    public boolean supportsPrimitiveMotionBlur() {
        return sendBool(id, "supportsPrimitiveMotionBlur");
    }

    public long maximumConcurrentCompilationTaskCount() {
        return sendLong(id, "maximumConcurrentCompilationTaskCount");
    }

    @SneakyThrows
    public MTLLibrary newLibraryWithSource(NSString source, MTLCompileOptions options) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long library = (long) P_PPA.invokeExact(id, ObjC.sel("newLibraryWithSource:options:error:"),
                    source.getId(), options.getId(), error);
            NSError.check(error, "newLibraryWithSource:options:error:");
            return MTLLibrary.of(library);
        }
    }

    public MTLLibrary newDefaultLibrary() {
        return MTLLibrary.of(sendPtr(id, "newDefaultLibrary"));
    }

    @SneakyThrows
    public MTLRenderPipelineState newRenderPipelineStateWithDescriptor(MTLRenderPipelineDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PA.invokeExact(id, ObjC.sel("newRenderPipelineStateWithDescriptor:error:"),
                    descriptor.getId(), error);
            NSError.check(error, "newRenderPipelineStateWithDescriptor:error:");
            return MTLRenderPipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLRenderPipelineState newRenderPipelineStateWithMeshDescriptor(
            MTLMeshRenderPipelineDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PLAA.invokeExact(id,
                    ObjC.sel("newRenderPipelineStateWithMeshDescriptor:options:reflection:error:"),
                    descriptor.getId(), 0L, MemorySegment.NULL, error);
            NSError.check(error, "newRenderPipelineStateWithMeshDescriptor:options:reflection:error:");
            return MTLRenderPipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLComputePipelineState newComputePipelineStateWithFunction(MTLFunction function) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long state = (long) P_PA.invokeExact(id, ObjC.sel("newComputePipelineStateWithFunction:error:"),
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
                    ObjC.sel("newComputePipelineStateWithDescriptor:options:reflection:error:"), descriptor.getId(),
                    0L, MemorySegment.NULL, error);
            NSError.check(error, "newComputePipelineStateWithDescriptor:options:reflection:error:");
            return MTLComputePipelineState.of(state);
        }
    }

    @SneakyThrows
    public MTLDepthStencilState newDepthStencilStateWithDescriptor(MTLDepthStencilDescriptor descriptor) {
        return MTLDepthStencilState.of((long) P_P.invokeExact(id, ObjC.sel("newDepthStencilStateWithDescriptor:"),
                descriptor.getId()));
    }

    @SneakyThrows
    public boolean supportsRasterizationRateMapWithLayerCount(long layerCount) {
        return (boolean) B_L.invokeExact(id, ObjC.sel("supportsRasterizationRateMapWithLayerCount:"), layerCount);
    }
}
