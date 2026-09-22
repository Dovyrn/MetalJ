package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPipelineState extends NSObject {
    private static final long DEVICE = ObjC.sel("device");
    private static final long GPU_RESOURCE_ID = ObjC.sel("gpuResourceID");
    private static final long IMAGEBLOCK_MEMORY_LENGTH_FOR_DIMENSIONS = ObjC.sel("imageblockMemoryLengthForDimensions:");
    private static final long IMAGEBLOCK_SAMPLE_LENGTH = ObjC.sel("imageblockSampleLength");
    private static final long LABEL = ObjC.sel("label");
    private static final long MAX_TOTAL_THREADS_PER_MESH_THREADGROUP = ObjC.sel("maxTotalThreadsPerMeshThreadgroup");
    private static final long MAX_TOTAL_THREADS_PER_OBJECT_THREADGROUP = ObjC.sel("maxTotalThreadsPerObjectThreadgroup");
    private static final long MAX_TOTAL_THREADS_PER_THREADGROUP = ObjC.sel("maxTotalThreadsPerThreadgroup");
    private static final long MESH_THREAD_EXECUTION_WIDTH = ObjC.sel("meshThreadExecutionWidth");
    private static final long OBJECT_THREAD_EXECUTION_WIDTH = ObjC.sel("objectThreadExecutionWidth");
    private static final long SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("supportIndirectCommandBuffers");
    private static final long THREADGROUP_SIZE_MATCHES_TILE_SIZE = ObjC.sel("threadgroupSizeMatchesTileSize");

    private static final MethodHandle L_S = handle(ObjC.LONG, MTLSize.LAYOUT);

    private MTLRenderPipelineState(long id) {
        super(id);
    }

    public static MTLRenderPipelineState of(long id) {
        return new MTLRenderPipelineState(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public long maxTotalThreadsPerThreadgroup() {
        return sendLong(id, MAX_TOTAL_THREADS_PER_THREADGROUP);
    }

    public boolean threadgroupSizeMatchesTileSize() {
        return sendBool(id, THREADGROUP_SIZE_MATCHES_TILE_SIZE);
    }

    public long imageblockSampleLength() {
        return sendLong(id, IMAGEBLOCK_SAMPLE_LENGTH);
    }

    @SneakyThrows
    public long imageblockMemoryLengthForDimensions(MemorySegment size) {
        return (long) L_S.invokeExact(id, IMAGEBLOCK_MEMORY_LENGTH_FOR_DIMENSIONS, size);
    }

    public boolean supportIndirectCommandBuffers() {
        return sendBool(id, SUPPORT_INDIRECT_COMMAND_BUFFERS);
    }

    public long gpuResourceID() {
        return sendLong(id, GPU_RESOURCE_ID);
    }

    public long maxTotalThreadsPerObjectThreadgroup() {
        return sendLong(id, MAX_TOTAL_THREADS_PER_OBJECT_THREADGROUP);
    }

    public long maxTotalThreadsPerMeshThreadgroup() {
        return sendLong(id, MAX_TOTAL_THREADS_PER_MESH_THREADGROUP);
    }

    public long objectThreadExecutionWidth() {
        return sendLong(id, OBJECT_THREAD_EXECUTION_WIDTH);
    }

    public long meshThreadExecutionWidth() {
        return sendLong(id, MESH_THREAD_EXECUTION_WIDTH);
    }
}
