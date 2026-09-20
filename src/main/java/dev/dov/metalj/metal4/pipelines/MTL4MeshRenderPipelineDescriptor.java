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
    private static final MethodHandle S = handle(null, MTLSize.LAYOUT);
    private static final MethodHandle SIZE = structHandle(MTLSize.LAYOUT);

    private MTL4MeshRenderPipelineDescriptor(long id) {
        super(id);
    }

    public static MTL4MeshRenderPipelineDescriptor of(long id) {
        return new MTL4MeshRenderPipelineDescriptor(id);
    }

    public static MTL4MeshRenderPipelineDescriptor new_() {
        return new MTL4MeshRenderPipelineDescriptor(sendPtr(ObjC.cls("MTL4MeshRenderPipelineDescriptor"), "new"));
    }

    public MTL4FunctionDescriptor objectFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, "objectFunctionDescriptor"));
    }

    @SneakyThrows
    public void setObjectFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setObjectFunctionDescriptor:"), descriptor.getId());
    }

    public MTL4FunctionDescriptor meshFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, "meshFunctionDescriptor"));
    }

    @SneakyThrows
    public void setMeshFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setMeshFunctionDescriptor:"), descriptor.getId());
    }

    public MTL4FunctionDescriptor fragmentFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, "fragmentFunctionDescriptor"));
    }

    @SneakyThrows
    public void setFragmentFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setFragmentFunctionDescriptor:"), descriptor.getId());
    }

    public long maxTotalThreadsPerObjectThreadgroup() {
        return sendLong(id, "maxTotalThreadsPerObjectThreadgroup");
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerObjectThreadgroup(long threads) {
        L.invokeExact(id, ObjC.sel("setMaxTotalThreadsPerObjectThreadgroup:"), threads);
    }

    public long maxTotalThreadsPerMeshThreadgroup() {
        return sendLong(id, "maxTotalThreadsPerMeshThreadgroup");
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerMeshThreadgroup(long threads) {
        L.invokeExact(id, ObjC.sel("setMaxTotalThreadsPerMeshThreadgroup:"), threads);
    }

    @SneakyThrows
    public MemorySegment requiredThreadsPerObjectThreadgroup(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, ObjC.sel("requiredThreadsPerObjectThreadgroup"));
    }

    @SneakyThrows
    public void setRequiredThreadsPerObjectThreadgroup(MemorySegment threads) {
        S.invokeExact(id, ObjC.sel("setRequiredThreadsPerObjectThreadgroup:"), threads);
    }

    @SneakyThrows
    public MemorySegment requiredThreadsPerMeshThreadgroup(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, ObjC.sel("requiredThreadsPerMeshThreadgroup"));
    }

    @SneakyThrows
    public void setRequiredThreadsPerMeshThreadgroup(MemorySegment threads) {
        S.invokeExact(id, ObjC.sel("setRequiredThreadsPerMeshThreadgroup:"), threads);
    }

    public boolean objectThreadgroupSizeIsMultipleOfThreadExecutionWidth() {
        return sendBool(id, "objectThreadgroupSizeIsMultipleOfThreadExecutionWidth");
    }

    @SneakyThrows
    public void setObjectThreadgroupSizeIsMultipleOfThreadExecutionWidth(boolean multiple) {
        B.invokeExact(id, ObjC.sel("setObjectThreadgroupSizeIsMultipleOfThreadExecutionWidth:"), multiple);
    }

    public boolean meshThreadgroupSizeIsMultipleOfThreadExecutionWidth() {
        return sendBool(id, "meshThreadgroupSizeIsMultipleOfThreadExecutionWidth");
    }

    @SneakyThrows
    public void setMeshThreadgroupSizeIsMultipleOfThreadExecutionWidth(boolean multiple) {
        B.invokeExact(id, ObjC.sel("setMeshThreadgroupSizeIsMultipleOfThreadExecutionWidth:"), multiple);
    }

    public long payloadMemoryLength() {
        return sendLong(id, "payloadMemoryLength");
    }

    @SneakyThrows
    public void setPayloadMemoryLength(long length) {
        L.invokeExact(id, ObjC.sel("setPayloadMemoryLength:"), length);
    }

    public long maxTotalThreadgroupsPerMeshGrid() {
        return sendLong(id, "maxTotalThreadgroupsPerMeshGrid");
    }

    @SneakyThrows
    public void setMaxTotalThreadgroupsPerMeshGrid(long threadgroups) {
        L.invokeExact(id, ObjC.sel("setMaxTotalThreadgroupsPerMeshGrid:"), threadgroups);
    }

    public long rasterSampleCount() {
        return sendLong(id, "rasterSampleCount");
    }

    @SneakyThrows
    public void setRasterSampleCount(long count) {
        L.invokeExact(id, ObjC.sel("setRasterSampleCount:"), count);
    }

    public long alphaToCoverageState() {
        return sendLong(id, "alphaToCoverageState");
    }

    @SneakyThrows
    public void setAlphaToCoverageState(long state) {
        L.invokeExact(id, ObjC.sel("setAlphaToCoverageState:"), state);
    }

    public long alphaToOneState() {
        return sendLong(id, "alphaToOneState");
    }

    @SneakyThrows
    public void setAlphaToOneState(long state) {
        L.invokeExact(id, ObjC.sel("setAlphaToOneState:"), state);
    }

    public boolean isRasterizationEnabled() {
        return sendBool(id, "isRasterizationEnabled");
    }

    @SneakyThrows
    public void setRasterizationEnabled(boolean enabled) {
        B.invokeExact(id, ObjC.sel("setRasterizationEnabled:"), enabled);
    }

    public long maxVertexAmplificationCount() {
        return sendLong(id, "maxVertexAmplificationCount");
    }

    @SneakyThrows
    public void setMaxVertexAmplificationCount(long count) {
        L.invokeExact(id, ObjC.sel("setMaxVertexAmplificationCount:"), count);
    }

    public MTL4RenderPipelineColorAttachmentDescriptorArray colorAttachments() {
        return MTL4RenderPipelineColorAttachmentDescriptorArray.of(sendPtr(id, "colorAttachments"));
    }

    public MTL4StaticLinkingDescriptor objectStaticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, "objectStaticLinkingDescriptor"));
    }

    @SneakyThrows
    public void setObjectStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setObjectStaticLinkingDescriptor:"), descriptor.getId());
    }

    public MTL4StaticLinkingDescriptor meshStaticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, "meshStaticLinkingDescriptor"));
    }

    @SneakyThrows
    public void setMeshStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setMeshStaticLinkingDescriptor:"), descriptor.getId());
    }

    public MTL4StaticLinkingDescriptor fragmentStaticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, "fragmentStaticLinkingDescriptor"));
    }

    @SneakyThrows
    public void setFragmentStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setFragmentStaticLinkingDescriptor:"), descriptor.getId());
    }

    public boolean supportObjectBinaryLinking() {
        return sendBool(id, "supportObjectBinaryLinking");
    }

    @SneakyThrows
    public void setSupportObjectBinaryLinking(boolean support) {
        B.invokeExact(id, ObjC.sel("setSupportObjectBinaryLinking:"), support);
    }

    public boolean supportMeshBinaryLinking() {
        return sendBool(id, "supportMeshBinaryLinking");
    }

    @SneakyThrows
    public void setSupportMeshBinaryLinking(boolean support) {
        B.invokeExact(id, ObjC.sel("setSupportMeshBinaryLinking:"), support);
    }

    public boolean supportFragmentBinaryLinking() {
        return sendBool(id, "supportFragmentBinaryLinking");
    }

    @SneakyThrows
    public void setSupportFragmentBinaryLinking(boolean support) {
        B.invokeExact(id, ObjC.sel("setSupportFragmentBinaryLinking:"), support);
    }

    public long colorAttachmentMappingState() {
        return sendLong(id, "colorAttachmentMappingState");
    }

    @SneakyThrows
    public void setColorAttachmentMappingState(long state) {
        L.invokeExact(id, ObjC.sel("setColorAttachmentMappingState:"), state);
    }

    public long supportIndirectCommandBuffers() {
        return sendLong(id, "supportIndirectCommandBuffers");
    }

    @SneakyThrows
    public void setSupportIndirectCommandBuffers(long state) {
        L.invokeExact(id, ObjC.sel("setSupportIndirectCommandBuffers:"), state);
    }

    public void reset() {
        sendVoid(id, "reset");
    }
}
