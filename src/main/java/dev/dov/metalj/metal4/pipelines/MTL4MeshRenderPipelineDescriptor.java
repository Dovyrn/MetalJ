package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.metal4.compiler.MTL4FunctionDescriptor;
import dev.dov.metalj.metal4.compiler.MTL4StaticLinkingDescriptor;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4MeshRenderPipelineDescriptor extends MTL4PipelineDescriptor {
    private static final long MTL_4_MESH_RENDER_PIPELINE_DESCRIPTOR = ObjC.cls("MTL4MeshRenderPipelineDescriptor");

    private static final long ALPHA_TO_COVERAGE_STATE = ObjC.sel("alphaToCoverageState");
    private static final long ALPHA_TO_ONE_STATE = ObjC.sel("alphaToOneState");
    private static final long COLOR_ATTACHMENT_MAPPING_STATE = ObjC.sel("colorAttachmentMappingState");
    private static final long COLOR_ATTACHMENTS = ObjC.sel("colorAttachments");
    private static final long FRAGMENT_FUNCTION_DESCRIPTOR = ObjC.sel("fragmentFunctionDescriptor");
    private static final long FRAGMENT_STATIC_LINKING_DESCRIPTOR = ObjC.sel("fragmentStaticLinkingDescriptor");
    private static final long IS_RASTERIZATION_ENABLED = ObjC.sel("isRasterizationEnabled");
    private static final long MAX_TOTAL_THREADGROUPS_PER_MESH_GRID = ObjC.sel("maxTotalThreadgroupsPerMeshGrid");
    private static final long MAX_TOTAL_THREADS_PER_MESH_THREADGROUP = ObjC.sel("maxTotalThreadsPerMeshThreadgroup");
    private static final long MAX_TOTAL_THREADS_PER_OBJECT_THREADGROUP = ObjC.sel("maxTotalThreadsPerObjectThreadgroup");
    private static final long MAX_VERTEX_AMPLIFICATION_COUNT = ObjC.sel("maxVertexAmplificationCount");
    private static final long MESH_FUNCTION_DESCRIPTOR = ObjC.sel("meshFunctionDescriptor");
    private static final long MESH_STATIC_LINKING_DESCRIPTOR = ObjC.sel("meshStaticLinkingDescriptor");
    private static final long MESH_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH = ObjC.sel("meshThreadgroupSizeIsMultipleOfThreadExecutionWidth");
    private static final long NEW = ObjC.sel("new");
    private static final long OBJECT_FUNCTION_DESCRIPTOR = ObjC.sel("objectFunctionDescriptor");
    private static final long OBJECT_STATIC_LINKING_DESCRIPTOR = ObjC.sel("objectStaticLinkingDescriptor");
    private static final long OBJECT_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH = ObjC.sel("objectThreadgroupSizeIsMultipleOfThreadExecutionWidth");
    private static final long PAYLOAD_MEMORY_LENGTH = ObjC.sel("payloadMemoryLength");
    private static final long RASTER_SAMPLE_COUNT = ObjC.sel("rasterSampleCount");
    private static final long REQUIRED_THREADS_PER_MESH_THREADGROUP = ObjC.sel("requiredThreadsPerMeshThreadgroup");
    private static final long REQUIRED_THREADS_PER_OBJECT_THREADGROUP = ObjC.sel("requiredThreadsPerObjectThreadgroup");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_ALPHA_TO_COVERAGE_STATE = ObjC.sel("setAlphaToCoverageState:");
    private static final long SET_ALPHA_TO_ONE_STATE = ObjC.sel("setAlphaToOneState:");
    private static final long SET_COLOR_ATTACHMENT_MAPPING_STATE = ObjC.sel("setColorAttachmentMappingState:");
    private static final long SET_FRAGMENT_FUNCTION_DESCRIPTOR = ObjC.sel("setFragmentFunctionDescriptor:");
    private static final long SET_FRAGMENT_STATIC_LINKING_DESCRIPTOR = ObjC.sel("setFragmentStaticLinkingDescriptor:");
    private static final long SET_MAX_TOTAL_THREADGROUPS_PER_MESH_GRID = ObjC.sel("setMaxTotalThreadgroupsPerMeshGrid:");
    private static final long SET_MAX_TOTAL_THREADS_PER_MESH_THREADGROUP = ObjC.sel("setMaxTotalThreadsPerMeshThreadgroup:");
    private static final long SET_MAX_TOTAL_THREADS_PER_OBJECT_THREADGROUP = ObjC.sel("setMaxTotalThreadsPerObjectThreadgroup:");
    private static final long SET_MAX_VERTEX_AMPLIFICATION_COUNT = ObjC.sel("setMaxVertexAmplificationCount:");
    private static final long SET_MESH_FUNCTION_DESCRIPTOR = ObjC.sel("setMeshFunctionDescriptor:");
    private static final long SET_MESH_STATIC_LINKING_DESCRIPTOR = ObjC.sel("setMeshStaticLinkingDescriptor:");
    private static final long SET_MESH_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH = ObjC.sel("setMeshThreadgroupSizeIsMultipleOfThreadExecutionWidth:");
    private static final long SET_OBJECT_FUNCTION_DESCRIPTOR = ObjC.sel("setObjectFunctionDescriptor:");
    private static final long SET_OBJECT_STATIC_LINKING_DESCRIPTOR = ObjC.sel("setObjectStaticLinkingDescriptor:");
    private static final long SET_OBJECT_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH = ObjC.sel("setObjectThreadgroupSizeIsMultipleOfThreadExecutionWidth:");
    private static final long SET_PAYLOAD_MEMORY_LENGTH = ObjC.sel("setPayloadMemoryLength:");
    private static final long SET_RASTER_SAMPLE_COUNT = ObjC.sel("setRasterSampleCount:");
    private static final long SET_RASTERIZATION_ENABLED = ObjC.sel("setRasterizationEnabled:");
    private static final long SET_REQUIRED_THREADS_PER_MESH_THREADGROUP = ObjC.sel("setRequiredThreadsPerMeshThreadgroup:");
    private static final long SET_REQUIRED_THREADS_PER_OBJECT_THREADGROUP = ObjC.sel("setRequiredThreadsPerObjectThreadgroup:");
    private static final long SET_SUPPORT_FRAGMENT_BINARY_LINKING = ObjC.sel("setSupportFragmentBinaryLinking:");
    private static final long SET_SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("setSupportIndirectCommandBuffers:");
    private static final long SET_SUPPORT_MESH_BINARY_LINKING = ObjC.sel("setSupportMeshBinaryLinking:");
    private static final long SET_SUPPORT_OBJECT_BINARY_LINKING = ObjC.sel("setSupportObjectBinaryLinking:");
    private static final long SUPPORT_FRAGMENT_BINARY_LINKING = ObjC.sel("supportFragmentBinaryLinking");
    private static final long SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("supportIndirectCommandBuffers");
    private static final long SUPPORT_MESH_BINARY_LINKING = ObjC.sel("supportMeshBinaryLinking");
    private static final long SUPPORT_OBJECT_BINARY_LINKING = ObjC.sel("supportObjectBinaryLinking");

    private static final MethodHandle S = handle(null, MTLSize.LAYOUT);
    private static final MethodHandle SIZE = structHandle(MTLSize.LAYOUT);

    private MTL4MeshRenderPipelineDescriptor(long id) {
        super(id);
    }

    public static MTL4MeshRenderPipelineDescriptor of(long id) {
        return new MTL4MeshRenderPipelineDescriptor(id);
    }

    public static MTL4MeshRenderPipelineDescriptor new_() {
        return new MTL4MeshRenderPipelineDescriptor(sendPtr(MTL_4_MESH_RENDER_PIPELINE_DESCRIPTOR, NEW));
    }

    public MTL4FunctionDescriptor objectFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, OBJECT_FUNCTION_DESCRIPTOR));
    }

    @SneakyThrows
    public void setObjectFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, SET_OBJECT_FUNCTION_DESCRIPTOR, descriptor.getId());
    }

    public MTL4FunctionDescriptor meshFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, MESH_FUNCTION_DESCRIPTOR));
    }

    @SneakyThrows
    public void setMeshFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, SET_MESH_FUNCTION_DESCRIPTOR, descriptor.getId());
    }

    public MTL4FunctionDescriptor fragmentFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, FRAGMENT_FUNCTION_DESCRIPTOR));
    }

    @SneakyThrows
    public void setFragmentFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, SET_FRAGMENT_FUNCTION_DESCRIPTOR, descriptor.getId());
    }

    public long maxTotalThreadsPerObjectThreadgroup() {
        return sendLong(id, MAX_TOTAL_THREADS_PER_OBJECT_THREADGROUP);
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerObjectThreadgroup(long threads) {
        L.invokeExact(id, SET_MAX_TOTAL_THREADS_PER_OBJECT_THREADGROUP, threads);
    }

    public long maxTotalThreadsPerMeshThreadgroup() {
        return sendLong(id, MAX_TOTAL_THREADS_PER_MESH_THREADGROUP);
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerMeshThreadgroup(long threads) {
        L.invokeExact(id, SET_MAX_TOTAL_THREADS_PER_MESH_THREADGROUP, threads);
    }

    @SneakyThrows
    public MemorySegment requiredThreadsPerObjectThreadgroup(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, REQUIRED_THREADS_PER_OBJECT_THREADGROUP);
    }

    @SneakyThrows
    public void setRequiredThreadsPerObjectThreadgroup(MemorySegment threads) {
        S.invokeExact(id, SET_REQUIRED_THREADS_PER_OBJECT_THREADGROUP, threads);
    }

    @SneakyThrows
    public MemorySegment requiredThreadsPerMeshThreadgroup(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, REQUIRED_THREADS_PER_MESH_THREADGROUP);
    }

    @SneakyThrows
    public void setRequiredThreadsPerMeshThreadgroup(MemorySegment threads) {
        S.invokeExact(id, SET_REQUIRED_THREADS_PER_MESH_THREADGROUP, threads);
    }

    public boolean objectThreadgroupSizeIsMultipleOfThreadExecutionWidth() {
        return sendBool(id, OBJECT_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH);
    }

    @SneakyThrows
    public void setObjectThreadgroupSizeIsMultipleOfThreadExecutionWidth(boolean multiple) {
        B.invokeExact(id, SET_OBJECT_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH, multiple);
    }

    public boolean meshThreadgroupSizeIsMultipleOfThreadExecutionWidth() {
        return sendBool(id, MESH_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH);
    }

    @SneakyThrows
    public void setMeshThreadgroupSizeIsMultipleOfThreadExecutionWidth(boolean multiple) {
        B.invokeExact(id, SET_MESH_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH, multiple);
    }

    public long payloadMemoryLength() {
        return sendLong(id, PAYLOAD_MEMORY_LENGTH);
    }

    @SneakyThrows
    public void setPayloadMemoryLength(long length) {
        L.invokeExact(id, SET_PAYLOAD_MEMORY_LENGTH, length);
    }

    public long maxTotalThreadgroupsPerMeshGrid() {
        return sendLong(id, MAX_TOTAL_THREADGROUPS_PER_MESH_GRID);
    }

    @SneakyThrows
    public void setMaxTotalThreadgroupsPerMeshGrid(long threadgroups) {
        L.invokeExact(id, SET_MAX_TOTAL_THREADGROUPS_PER_MESH_GRID, threadgroups);
    }

    public long rasterSampleCount() {
        return sendLong(id, RASTER_SAMPLE_COUNT);
    }

    @SneakyThrows
    public void setRasterSampleCount(long count) {
        L.invokeExact(id, SET_RASTER_SAMPLE_COUNT, count);
    }

    public long alphaToCoverageState() {
        return sendLong(id, ALPHA_TO_COVERAGE_STATE);
    }

    @SneakyThrows
    public void setAlphaToCoverageState(long state) {
        L.invokeExact(id, SET_ALPHA_TO_COVERAGE_STATE, state);
    }

    public long alphaToOneState() {
        return sendLong(id, ALPHA_TO_ONE_STATE);
    }

    @SneakyThrows
    public void setAlphaToOneState(long state) {
        L.invokeExact(id, SET_ALPHA_TO_ONE_STATE, state);
    }

    public boolean isRasterizationEnabled() {
        return sendBool(id, IS_RASTERIZATION_ENABLED);
    }

    @SneakyThrows
    public void setRasterizationEnabled(boolean enabled) {
        B.invokeExact(id, SET_RASTERIZATION_ENABLED, enabled);
    }

    public long maxVertexAmplificationCount() {
        return sendLong(id, MAX_VERTEX_AMPLIFICATION_COUNT);
    }

    @SneakyThrows
    public void setMaxVertexAmplificationCount(long count) {
        L.invokeExact(id, SET_MAX_VERTEX_AMPLIFICATION_COUNT, count);
    }

    public MTL4RenderPipelineColorAttachmentDescriptorArray colorAttachments() {
        return MTL4RenderPipelineColorAttachmentDescriptorArray.of(sendPtr(id, COLOR_ATTACHMENTS));
    }

    public MTL4StaticLinkingDescriptor objectStaticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, OBJECT_STATIC_LINKING_DESCRIPTOR));
    }

    @SneakyThrows
    public void setObjectStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, SET_OBJECT_STATIC_LINKING_DESCRIPTOR, descriptor.getId());
    }

    public MTL4StaticLinkingDescriptor meshStaticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, MESH_STATIC_LINKING_DESCRIPTOR));
    }

    @SneakyThrows
    public void setMeshStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, SET_MESH_STATIC_LINKING_DESCRIPTOR, descriptor.getId());
    }

    public MTL4StaticLinkingDescriptor fragmentStaticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, FRAGMENT_STATIC_LINKING_DESCRIPTOR));
    }

    @SneakyThrows
    public void setFragmentStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, SET_FRAGMENT_STATIC_LINKING_DESCRIPTOR, descriptor.getId());
    }

    public boolean supportObjectBinaryLinking() {
        return sendBool(id, SUPPORT_OBJECT_BINARY_LINKING);
    }

    @SneakyThrows
    public void setSupportObjectBinaryLinking(boolean support) {
        B.invokeExact(id, SET_SUPPORT_OBJECT_BINARY_LINKING, support);
    }

    public boolean supportMeshBinaryLinking() {
        return sendBool(id, SUPPORT_MESH_BINARY_LINKING);
    }

    @SneakyThrows
    public void setSupportMeshBinaryLinking(boolean support) {
        B.invokeExact(id, SET_SUPPORT_MESH_BINARY_LINKING, support);
    }

    public boolean supportFragmentBinaryLinking() {
        return sendBool(id, SUPPORT_FRAGMENT_BINARY_LINKING);
    }

    @SneakyThrows
    public void setSupportFragmentBinaryLinking(boolean support) {
        B.invokeExact(id, SET_SUPPORT_FRAGMENT_BINARY_LINKING, support);
    }

    public long colorAttachmentMappingState() {
        return sendLong(id, COLOR_ATTACHMENT_MAPPING_STATE);
    }

    @SneakyThrows
    public void setColorAttachmentMappingState(long state) {
        L.invokeExact(id, SET_COLOR_ATTACHMENT_MAPPING_STATE, state);
    }

    public long supportIndirectCommandBuffers() {
        return sendLong(id, SUPPORT_INDIRECT_COMMAND_BUFFERS);
    }

    @SneakyThrows
    public void setSupportIndirectCommandBuffers(long state) {
        L.invokeExact(id, SET_SUPPORT_INDIRECT_COMMAND_BUFFERS, state);
    }

    public void reset() {
        sendVoid(id, RESET);
    }
}
