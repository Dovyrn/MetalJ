package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.pipelines.shaders.MTLFunction;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLMeshRenderPipelineDescriptor extends NSObject {
    private static final long MTL_MESH_RENDER_PIPELINE_DESCRIPTOR = ObjC.cls("MTLMeshRenderPipelineDescriptor");

    private static final long COLOR_ATTACHMENTS = ObjC.sel("colorAttachments");
    private static final long DEPTH_ATTACHMENT_PIXEL_FORMAT = ObjC.sel("depthAttachmentPixelFormat");
    private static final long FRAGMENT_BUFFERS = ObjC.sel("fragmentBuffers");
    private static final long FRAGMENT_FUNCTION = ObjC.sel("fragmentFunction");
    private static final long IS_ALPHA_TO_COVERAGE_ENABLED = ObjC.sel("isAlphaToCoverageEnabled");
    private static final long IS_ALPHA_TO_ONE_ENABLED = ObjC.sel("isAlphaToOneEnabled");
    private static final long IS_RASTERIZATION_ENABLED = ObjC.sel("isRasterizationEnabled");
    private static final long LABEL = ObjC.sel("label");
    private static final long MAX_TOTAL_THREADGROUPS_PER_MESH_GRID = ObjC.sel("maxTotalThreadgroupsPerMeshGrid");
    private static final long MAX_TOTAL_THREADS_PER_MESH_THREADGROUP = ObjC.sel("maxTotalThreadsPerMeshThreadgroup");
    private static final long MAX_TOTAL_THREADS_PER_OBJECT_THREADGROUP = ObjC.sel("maxTotalThreadsPerObjectThreadgroup");
    private static final long MAX_VERTEX_AMPLIFICATION_COUNT = ObjC.sel("maxVertexAmplificationCount");
    private static final long MESH_BUFFERS = ObjC.sel("meshBuffers");
    private static final long MESH_FUNCTION = ObjC.sel("meshFunction");
    private static final long MESH_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH = ObjC.sel("meshThreadgroupSizeIsMultipleOfThreadExecutionWidth");
    private static final long NEW = ObjC.sel("new");
    private static final long OBJECT_BUFFERS = ObjC.sel("objectBuffers");
    private static final long OBJECT_FUNCTION = ObjC.sel("objectFunction");
    private static final long OBJECT_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH = ObjC.sel("objectThreadgroupSizeIsMultipleOfThreadExecutionWidth");
    private static final long PAYLOAD_MEMORY_LENGTH = ObjC.sel("payloadMemoryLength");
    private static final long RASTER_SAMPLE_COUNT = ObjC.sel("rasterSampleCount");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_ALPHA_TO_COVERAGE_ENABLED = ObjC.sel("setAlphaToCoverageEnabled:");
    private static final long SET_ALPHA_TO_ONE_ENABLED = ObjC.sel("setAlphaToOneEnabled:");
    private static final long SET_DEPTH_ATTACHMENT_PIXEL_FORMAT = ObjC.sel("setDepthAttachmentPixelFormat:");
    private static final long SET_FRAGMENT_FUNCTION = ObjC.sel("setFragmentFunction:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_MAX_TOTAL_THREADGROUPS_PER_MESH_GRID = ObjC.sel("setMaxTotalThreadgroupsPerMeshGrid:");
    private static final long SET_MAX_TOTAL_THREADS_PER_MESH_THREADGROUP = ObjC.sel("setMaxTotalThreadsPerMeshThreadgroup:");
    private static final long SET_MAX_TOTAL_THREADS_PER_OBJECT_THREADGROUP = ObjC.sel("setMaxTotalThreadsPerObjectThreadgroup:");
    private static final long SET_MAX_VERTEX_AMPLIFICATION_COUNT = ObjC.sel("setMaxVertexAmplificationCount:");
    private static final long SET_MESH_FUNCTION = ObjC.sel("setMeshFunction:");
    private static final long SET_MESH_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH = ObjC.sel("setMeshThreadgroupSizeIsMultipleOfThreadExecutionWidth:");
    private static final long SET_OBJECT_FUNCTION = ObjC.sel("setObjectFunction:");
    private static final long SET_OBJECT_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH = ObjC.sel("setObjectThreadgroupSizeIsMultipleOfThreadExecutionWidth:");
    private static final long SET_PAYLOAD_MEMORY_LENGTH = ObjC.sel("setPayloadMemoryLength:");
    private static final long SET_RASTER_SAMPLE_COUNT = ObjC.sel("setRasterSampleCount:");
    private static final long SET_RASTERIZATION_ENABLED = ObjC.sel("setRasterizationEnabled:");
    private static final long SET_STENCIL_ATTACHMENT_PIXEL_FORMAT = ObjC.sel("setStencilAttachmentPixelFormat:");
    private static final long SET_SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("setSupportIndirectCommandBuffers:");
    private static final long STENCIL_ATTACHMENT_PIXEL_FORMAT = ObjC.sel("stencilAttachmentPixelFormat");
    private static final long SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("supportIndirectCommandBuffers");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLMeshRenderPipelineDescriptor(long id) {
        super(id);
    }

    public static MTLMeshRenderPipelineDescriptor of(long id) {
        return new MTLMeshRenderPipelineDescriptor(id);
    }

    public static MTLMeshRenderPipelineDescriptor new_() {
        return new MTLMeshRenderPipelineDescriptor(sendPtr(MTL_MESH_RENDER_PIPELINE_DESCRIPTOR, NEW));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public MTLFunction objectFunction() {
        return MTLFunction.of(sendPtr(id, OBJECT_FUNCTION));
    }

    @SneakyThrows
    public void setObjectFunction(MTLFunction objectFunction) {
        P.invokeExact(id, SET_OBJECT_FUNCTION, objectFunction.getId());
    }

    public MTLFunction meshFunction() {
        return MTLFunction.of(sendPtr(id, MESH_FUNCTION));
    }

    @SneakyThrows
    public void setMeshFunction(MTLFunction meshFunction) {
        P.invokeExact(id, SET_MESH_FUNCTION, meshFunction.getId());
    }

    public MTLFunction fragmentFunction() {
        return MTLFunction.of(sendPtr(id, FRAGMENT_FUNCTION));
    }

    @SneakyThrows
    public void setFragmentFunction(MTLFunction fragmentFunction) {
        P.invokeExact(id, SET_FRAGMENT_FUNCTION, fragmentFunction.getId());
    }

    public long maxTotalThreadsPerObjectThreadgroup() {
        return sendLong(id, MAX_TOTAL_THREADS_PER_OBJECT_THREADGROUP);
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerObjectThreadgroup(long maxTotalThreadsPerObjectThreadgroup) {
        L.invokeExact(id, SET_MAX_TOTAL_THREADS_PER_OBJECT_THREADGROUP, maxTotalThreadsPerObjectThreadgroup);
    }

    public long maxTotalThreadsPerMeshThreadgroup() {
        return sendLong(id, MAX_TOTAL_THREADS_PER_MESH_THREADGROUP);
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerMeshThreadgroup(long maxTotalThreadsPerMeshThreadgroup) {
        L.invokeExact(id, SET_MAX_TOTAL_THREADS_PER_MESH_THREADGROUP, maxTotalThreadsPerMeshThreadgroup);
    }

    public boolean objectThreadgroupSizeIsMultipleOfThreadExecutionWidth() {
        return sendBool(id, OBJECT_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH);
    }

    @SneakyThrows
    public void setObjectThreadgroupSizeIsMultipleOfThreadExecutionWidth(boolean objectThreadgroupSizeIsMultipleOfThreadExecutionWidth) {
        B.invokeExact(id, SET_OBJECT_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH, objectThreadgroupSizeIsMultipleOfThreadExecutionWidth);
    }

    public boolean meshThreadgroupSizeIsMultipleOfThreadExecutionWidth() {
        return sendBool(id, MESH_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH);
    }

    @SneakyThrows
    public void setMeshThreadgroupSizeIsMultipleOfThreadExecutionWidth(boolean meshThreadgroupSizeIsMultipleOfThreadExecutionWidth) {
        B.invokeExact(id, SET_MESH_THREADGROUP_SIZE_IS_MULTIPLE_OF_THREAD_EXECUTION_WIDTH, meshThreadgroupSizeIsMultipleOfThreadExecutionWidth);
    }

    public long payloadMemoryLength() {
        return sendLong(id, PAYLOAD_MEMORY_LENGTH);
    }

    @SneakyThrows
    public void setPayloadMemoryLength(long payloadMemoryLength) {
        L.invokeExact(id, SET_PAYLOAD_MEMORY_LENGTH, payloadMemoryLength);
    }

    public long maxTotalThreadgroupsPerMeshGrid() {
        return sendLong(id, MAX_TOTAL_THREADGROUPS_PER_MESH_GRID);
    }

    @SneakyThrows
    public void setMaxTotalThreadgroupsPerMeshGrid(long maxTotalThreadgroupsPerMeshGrid) {
        L.invokeExact(id, SET_MAX_TOTAL_THREADGROUPS_PER_MESH_GRID, maxTotalThreadgroupsPerMeshGrid);
    }

    public MTLPipelineBufferDescriptorArray objectBuffers() {
        return MTLPipelineBufferDescriptorArray.of(sendPtr(id, OBJECT_BUFFERS));
    }

    public MTLPipelineBufferDescriptorArray meshBuffers() {
        return MTLPipelineBufferDescriptorArray.of(sendPtr(id, MESH_BUFFERS));
    }

    public MTLPipelineBufferDescriptorArray fragmentBuffers() {
        return MTLPipelineBufferDescriptorArray.of(sendPtr(id, FRAGMENT_BUFFERS));
    }

    public long rasterSampleCount() {
        return sendLong(id, RASTER_SAMPLE_COUNT);
    }

    @SneakyThrows
    public void setRasterSampleCount(long rasterSampleCount) {
        L.invokeExact(id, SET_RASTER_SAMPLE_COUNT, rasterSampleCount);
    }

    public boolean isAlphaToCoverageEnabled() {
        return sendBool(id, IS_ALPHA_TO_COVERAGE_ENABLED);
    }

    @SneakyThrows
    public void setAlphaToCoverageEnabled(boolean alphaToCoverageEnabled) {
        B.invokeExact(id, SET_ALPHA_TO_COVERAGE_ENABLED, alphaToCoverageEnabled);
    }

    public boolean isAlphaToOneEnabled() {
        return sendBool(id, IS_ALPHA_TO_ONE_ENABLED);
    }

    @SneakyThrows
    public void setAlphaToOneEnabled(boolean alphaToOneEnabled) {
        B.invokeExact(id, SET_ALPHA_TO_ONE_ENABLED, alphaToOneEnabled);
    }

    public boolean isRasterizationEnabled() {
        return sendBool(id, IS_RASTERIZATION_ENABLED);
    }

    @SneakyThrows
    public void setRasterizationEnabled(boolean rasterizationEnabled) {
        B.invokeExact(id, SET_RASTERIZATION_ENABLED, rasterizationEnabled);
    }

    public long maxVertexAmplificationCount() {
        return sendLong(id, MAX_VERTEX_AMPLIFICATION_COUNT);
    }

    @SneakyThrows
    public void setMaxVertexAmplificationCount(long maxVertexAmplificationCount) {
        L.invokeExact(id, SET_MAX_VERTEX_AMPLIFICATION_COUNT, maxVertexAmplificationCount);
    }

    public MTLRenderPipelineColorAttachmentDescriptorArray colorAttachments() {
        return MTLRenderPipelineColorAttachmentDescriptorArray.of(sendPtr(id, COLOR_ATTACHMENTS));
    }

    public long depthAttachmentPixelFormat() {
        return sendLong(id, DEPTH_ATTACHMENT_PIXEL_FORMAT);
    }

    @SneakyThrows
    public void setDepthAttachmentPixelFormat(long depthAttachmentPixelFormat) {
        L.invokeExact(id, SET_DEPTH_ATTACHMENT_PIXEL_FORMAT, depthAttachmentPixelFormat);
    }

    public long stencilAttachmentPixelFormat() {
        return sendLong(id, STENCIL_ATTACHMENT_PIXEL_FORMAT);
    }

    @SneakyThrows
    public void setStencilAttachmentPixelFormat(long stencilAttachmentPixelFormat) {
        L.invokeExact(id, SET_STENCIL_ATTACHMENT_PIXEL_FORMAT, stencilAttachmentPixelFormat);
    }

    public boolean supportIndirectCommandBuffers() {
        return sendBool(id, SUPPORT_INDIRECT_COMMAND_BUFFERS);
    }

    @SneakyThrows
    public void setSupportIndirectCommandBuffers(boolean supportIndirectCommandBuffers) {
        B.invokeExact(id, SET_SUPPORT_INDIRECT_COMMAND_BUFFERS, supportIndirectCommandBuffers);
    }

    public void reset() {
        sendVoid(id, RESET);
    }
}
