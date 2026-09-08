package dev.dov.metalj.device;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLBuffer;
import dev.dov.metalj.resources.MTLHeap;
import dev.dov.metalj.resources.MTLHeapDescriptor;
import dev.dov.metalj.resources.MTLIndirectCommandBuffer;
import dev.dov.metalj.resources.MTLIndirectCommandBufferDescriptor;
import dev.dov.metalj.resources.MTLSamplerDescriptor;
import dev.dov.metalj.resources.MTLSamplerState;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.MTLSizeAndAlign;
import dev.dov.metalj.resources.MTLTexture;
import dev.dov.metalj.resources.MTLTextureDescriptor;
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
    private static final MethodHandle L_L = handle(ObjC.LONG, ObjC.LONG);
    private static final MethodHandle B_L = handle(ObjC.BOOL, ObjC.LONG);
    private static final MethodHandle SA_LL = structHandle(MTLSizeAndAlign.LAYOUT, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle SA_P = structHandle(MTLSizeAndAlign.LAYOUT, ObjC.PTR);
    private static final MethodHandle SIZE = structHandle(MTLSize.LAYOUT);

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

    @SneakyThrows
    public void sampleTimestamps(MemorySegment cpu, MemorySegment gpu) {
        TIMESTAMPS.invokeExact(id, ObjC.sel("sampleTimestamps:gpuTimestamp:"), cpu, gpu);
    }

    @SneakyThrows
    public boolean supportsCounterSampling(long samplingPoint) {
        return (boolean) B_L.invokeExact(id, ObjC.sel("supportsCounterSampling:"), samplingPoint);
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
}
