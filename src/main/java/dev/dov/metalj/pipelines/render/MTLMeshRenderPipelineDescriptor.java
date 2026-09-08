package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.pipelines.shaders.MTLFunction;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLMeshRenderPipelineDescriptor extends NSObject {
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
        return new MTLMeshRenderPipelineDescriptor(sendPtr(ObjC.cls("MTLMeshRenderPipelineDescriptor"), "new"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    public MTLFunction objectFunction() {
        return MTLFunction.of(sendPtr(id, "objectFunction"));
    }

    @SneakyThrows
    public void setObjectFunction(MTLFunction objectFunction) {
        P.invokeExact(id, ObjC.sel("setObjectFunction:"), objectFunction.getId());
    }

    public MTLFunction meshFunction() {
        return MTLFunction.of(sendPtr(id, "meshFunction"));
    }

    @SneakyThrows
    public void setMeshFunction(MTLFunction meshFunction) {
        P.invokeExact(id, ObjC.sel("setMeshFunction:"), meshFunction.getId());
    }

    public MTLFunction fragmentFunction() {
        return MTLFunction.of(sendPtr(id, "fragmentFunction"));
    }

    @SneakyThrows
    public void setFragmentFunction(MTLFunction fragmentFunction) {
        P.invokeExact(id, ObjC.sel("setFragmentFunction:"), fragmentFunction.getId());
    }

    public long maxTotalThreadsPerObjectThreadgroup() {
        return sendLong(id, "maxTotalThreadsPerObjectThreadgroup");
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerObjectThreadgroup(long maxTotalThreadsPerObjectThreadgroup) {
        L.invokeExact(id, ObjC.sel("setMaxTotalThreadsPerObjectThreadgroup:"), maxTotalThreadsPerObjectThreadgroup);
    }

    public long maxTotalThreadsPerMeshThreadgroup() {
        return sendLong(id, "maxTotalThreadsPerMeshThreadgroup");
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerMeshThreadgroup(long maxTotalThreadsPerMeshThreadgroup) {
        L.invokeExact(id, ObjC.sel("setMaxTotalThreadsPerMeshThreadgroup:"), maxTotalThreadsPerMeshThreadgroup);
    }

    public boolean objectThreadgroupSizeIsMultipleOfThreadExecutionWidth() {
        return sendBool(id, "objectThreadgroupSizeIsMultipleOfThreadExecutionWidth");
    }

    @SneakyThrows
    public void setObjectThreadgroupSizeIsMultipleOfThreadExecutionWidth(boolean objectThreadgroupSizeIsMultipleOfThreadExecutionWidth) {
        B.invokeExact(id, ObjC.sel("setObjectThreadgroupSizeIsMultipleOfThreadExecutionWidth:"), objectThreadgroupSizeIsMultipleOfThreadExecutionWidth);
    }

    public boolean meshThreadgroupSizeIsMultipleOfThreadExecutionWidth() {
        return sendBool(id, "meshThreadgroupSizeIsMultipleOfThreadExecutionWidth");
    }

    @SneakyThrows
    public void setMeshThreadgroupSizeIsMultipleOfThreadExecutionWidth(boolean meshThreadgroupSizeIsMultipleOfThreadExecutionWidth) {
        B.invokeExact(id, ObjC.sel("setMeshThreadgroupSizeIsMultipleOfThreadExecutionWidth:"), meshThreadgroupSizeIsMultipleOfThreadExecutionWidth);
    }

    public long payloadMemoryLength() {
        return sendLong(id, "payloadMemoryLength");
    }

    @SneakyThrows
    public void setPayloadMemoryLength(long payloadMemoryLength) {
        L.invokeExact(id, ObjC.sel("setPayloadMemoryLength:"), payloadMemoryLength);
    }

    public long maxTotalThreadgroupsPerMeshGrid() {
        return sendLong(id, "maxTotalThreadgroupsPerMeshGrid");
    }

    @SneakyThrows
    public void setMaxTotalThreadgroupsPerMeshGrid(long maxTotalThreadgroupsPerMeshGrid) {
        L.invokeExact(id, ObjC.sel("setMaxTotalThreadgroupsPerMeshGrid:"), maxTotalThreadgroupsPerMeshGrid);
    }

    public MTLPipelineBufferDescriptorArray objectBuffers() {
        return MTLPipelineBufferDescriptorArray.of(sendPtr(id, "objectBuffers"));
    }

    public MTLPipelineBufferDescriptorArray meshBuffers() {
        return MTLPipelineBufferDescriptorArray.of(sendPtr(id, "meshBuffers"));
    }

    public MTLPipelineBufferDescriptorArray fragmentBuffers() {
        return MTLPipelineBufferDescriptorArray.of(sendPtr(id, "fragmentBuffers"));
    }

    public long rasterSampleCount() {
        return sendLong(id, "rasterSampleCount");
    }

    @SneakyThrows
    public void setRasterSampleCount(long rasterSampleCount) {
        L.invokeExact(id, ObjC.sel("setRasterSampleCount:"), rasterSampleCount);
    }

    public boolean isAlphaToCoverageEnabled() {
        return sendBool(id, "isAlphaToCoverageEnabled");
    }

    @SneakyThrows
    public void setAlphaToCoverageEnabled(boolean alphaToCoverageEnabled) {
        B.invokeExact(id, ObjC.sel("setAlphaToCoverageEnabled:"), alphaToCoverageEnabled);
    }

    public boolean isAlphaToOneEnabled() {
        return sendBool(id, "isAlphaToOneEnabled");
    }

    @SneakyThrows
    public void setAlphaToOneEnabled(boolean alphaToOneEnabled) {
        B.invokeExact(id, ObjC.sel("setAlphaToOneEnabled:"), alphaToOneEnabled);
    }

    public boolean isRasterizationEnabled() {
        return sendBool(id, "isRasterizationEnabled");
    }

    @SneakyThrows
    public void setRasterizationEnabled(boolean rasterizationEnabled) {
        B.invokeExact(id, ObjC.sel("setRasterizationEnabled:"), rasterizationEnabled);
    }

    public long maxVertexAmplificationCount() {
        return sendLong(id, "maxVertexAmplificationCount");
    }

    @SneakyThrows
    public void setMaxVertexAmplificationCount(long maxVertexAmplificationCount) {
        L.invokeExact(id, ObjC.sel("setMaxVertexAmplificationCount:"), maxVertexAmplificationCount);
    }

    public MTLRenderPipelineColorAttachmentDescriptorArray colorAttachments() {
        return MTLRenderPipelineColorAttachmentDescriptorArray.of(sendPtr(id, "colorAttachments"));
    }

    public long depthAttachmentPixelFormat() {
        return sendLong(id, "depthAttachmentPixelFormat");
    }

    @SneakyThrows
    public void setDepthAttachmentPixelFormat(long depthAttachmentPixelFormat) {
        L.invokeExact(id, ObjC.sel("setDepthAttachmentPixelFormat:"), depthAttachmentPixelFormat);
    }

    public long stencilAttachmentPixelFormat() {
        return sendLong(id, "stencilAttachmentPixelFormat");
    }

    @SneakyThrows
    public void setStencilAttachmentPixelFormat(long stencilAttachmentPixelFormat) {
        L.invokeExact(id, ObjC.sel("setStencilAttachmentPixelFormat:"), stencilAttachmentPixelFormat);
    }

    public boolean supportIndirectCommandBuffers() {
        return sendBool(id, "supportIndirectCommandBuffers");
    }

    @SneakyThrows
    public void setSupportIndirectCommandBuffers(boolean supportIndirectCommandBuffers) {
        B.invokeExact(id, ObjC.sel("setSupportIndirectCommandBuffers:"), supportIndirectCommandBuffers);
    }

    public void reset() {
        sendVoid(id, "reset");
    }
}
