package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.pipelines.vertex.MTLVertexDescriptor;
import dev.dov.metalj.pipelines.shaders.MTLFunction;
import dev.dov.metalj.functions.MTLLinkedFunctions;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPipelineDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLRenderPipelineDescriptor(long id) {
        super(id);
    }

    public static MTLRenderPipelineDescriptor of(long id) {
        return new MTLRenderPipelineDescriptor(id);
    }

    public static MTLRenderPipelineDescriptor new_() {
        return new MTLRenderPipelineDescriptor(sendPtr(ObjC.cls("MTLRenderPipelineDescriptor"), "new"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    public MTLFunction vertexFunction() {
        return MTLFunction.of(sendPtr(id, "vertexFunction"));
    }

    @SneakyThrows
    public void setVertexFunction(MTLFunction vertexFunction) {
        P.invokeExact(id, ObjC.sel("setVertexFunction:"), vertexFunction.getId());
    }

    public MTLFunction fragmentFunction() {
        return MTLFunction.of(sendPtr(id, "fragmentFunction"));
    }

    @SneakyThrows
    public void setFragmentFunction(MTLFunction fragmentFunction) {
        P.invokeExact(id, ObjC.sel("setFragmentFunction:"), fragmentFunction.getId());
    }

    public MTLVertexDescriptor vertexDescriptor() {
        return MTLVertexDescriptor.of(sendPtr(id, "vertexDescriptor"));
    }

    @SneakyThrows
    public void setVertexDescriptor(MTLVertexDescriptor vertexDescriptor) {
        P.invokeExact(id, ObjC.sel("setVertexDescriptor:"), vertexDescriptor.getId());
    }

    public MTLPipelineBufferDescriptorArray vertexBuffers() {
        return MTLPipelineBufferDescriptorArray.of(sendPtr(id, "vertexBuffers"));
    }

    public MTLPipelineBufferDescriptorArray fragmentBuffers() {
        return MTLPipelineBufferDescriptorArray.of(sendPtr(id, "fragmentBuffers"));
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

    public long sampleCount() {
        return sendLong(id, "sampleCount");
    }

    @SneakyThrows
    public void setSampleCount(long sampleCount) {
        L.invokeExact(id, ObjC.sel("setSampleCount:"), sampleCount);
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

    public long inputPrimitiveTopology() {
        return sendLong(id, "inputPrimitiveTopology");
    }

    @SneakyThrows
    public void setInputPrimitiveTopology(long inputPrimitiveTopology) {
        L.invokeExact(id, ObjC.sel("setInputPrimitiveTopology:"), inputPrimitiveTopology);
    }

    public long tessellationPartitionMode() {
        return sendLong(id, "tessellationPartitionMode");
    }

    @SneakyThrows
    public void setTessellationPartitionMode(long tessellationPartitionMode) {
        L.invokeExact(id, ObjC.sel("setTessellationPartitionMode:"), tessellationPartitionMode);
    }

    public long maxTessellationFactor() {
        return sendLong(id, "maxTessellationFactor");
    }

    @SneakyThrows
    public void setMaxTessellationFactor(long maxTessellationFactor) {
        L.invokeExact(id, ObjC.sel("setMaxTessellationFactor:"), maxTessellationFactor);
    }

    public boolean isTessellationFactorScaleEnabled() {
        return sendBool(id, "isTessellationFactorScaleEnabled");
    }

    @SneakyThrows
    public void setTessellationFactorScaleEnabled(boolean tessellationFactorScaleEnabled) {
        B.invokeExact(id, ObjC.sel("setTessellationFactorScaleEnabled:"), tessellationFactorScaleEnabled);
    }

    public long tessellationFactorFormat() {
        return sendLong(id, "tessellationFactorFormat");
    }

    @SneakyThrows
    public void setTessellationFactorFormat(long tessellationFactorFormat) {
        L.invokeExact(id, ObjC.sel("setTessellationFactorFormat:"), tessellationFactorFormat);
    }

    public long tessellationControlPointIndexType() {
        return sendLong(id, "tessellationControlPointIndexType");
    }

    @SneakyThrows
    public void setTessellationControlPointIndexType(long tessellationControlPointIndexType) {
        L.invokeExact(id, ObjC.sel("setTessellationControlPointIndexType:"), tessellationControlPointIndexType);
    }

    public long tessellationFactorStepFunction() {
        return sendLong(id, "tessellationFactorStepFunction");
    }

    @SneakyThrows
    public void setTessellationFactorStepFunction(long tessellationFactorStepFunction) {
        L.invokeExact(id, ObjC.sel("setTessellationFactorStepFunction:"), tessellationFactorStepFunction);
    }

    public long tessellationOutputWindingOrder() {
        return sendLong(id, "tessellationOutputWindingOrder");
    }

    @SneakyThrows
    public void setTessellationOutputWindingOrder(long tessellationOutputWindingOrder) {
        L.invokeExact(id, ObjC.sel("setTessellationOutputWindingOrder:"), tessellationOutputWindingOrder);
    }

    public long maxVertexAmplificationCount() {
        return sendLong(id, "maxVertexAmplificationCount");
    }

    @SneakyThrows
    public void setMaxVertexAmplificationCount(long maxVertexAmplificationCount) {
        L.invokeExact(id, ObjC.sel("setMaxVertexAmplificationCount:"), maxVertexAmplificationCount);
    }

    public boolean supportIndirectCommandBuffers() {
        return sendBool(id, "supportIndirectCommandBuffers");
    }

    @SneakyThrows
    public void setSupportIndirectCommandBuffers(boolean supportIndirectCommandBuffers) {
        B.invokeExact(id, ObjC.sel("setSupportIndirectCommandBuffers:"), supportIndirectCommandBuffers);
    }

    public boolean supportAddingVertexBinaryFunctions() {
        return sendBool(id, "supportAddingVertexBinaryFunctions");
    }

    @SneakyThrows
    public void setSupportAddingVertexBinaryFunctions(boolean supportAddingVertexBinaryFunctions) {
        B.invokeExact(id, ObjC.sel("setSupportAddingVertexBinaryFunctions:"), supportAddingVertexBinaryFunctions);
    }

    public boolean supportAddingFragmentBinaryFunctions() {
        return sendBool(id, "supportAddingFragmentBinaryFunctions");
    }

    @SneakyThrows
    public void setSupportAddingFragmentBinaryFunctions(boolean supportAddingFragmentBinaryFunctions) {
        B.invokeExact(id, ObjC.sel("setSupportAddingFragmentBinaryFunctions:"), supportAddingFragmentBinaryFunctions);
    }

    public long maxVertexCallStackDepth() {
        return sendLong(id, "maxVertexCallStackDepth");
    }

    @SneakyThrows
    public void setMaxVertexCallStackDepth(long maxVertexCallStackDepth) {
        L.invokeExact(id, ObjC.sel("setMaxVertexCallStackDepth:"), maxVertexCallStackDepth);
    }

    public long maxFragmentCallStackDepth() {
        return sendLong(id, "maxFragmentCallStackDepth");
    }

    @SneakyThrows
    public void setMaxFragmentCallStackDepth(long maxFragmentCallStackDepth) {
        L.invokeExact(id, ObjC.sel("setMaxFragmentCallStackDepth:"), maxFragmentCallStackDepth);
    }

    public long shaderValidation() {
        return sendLong(id, "shaderValidation");
    }

    @SneakyThrows
    public void setShaderValidation(long shaderValidation) {
        L.invokeExact(id, ObjC.sel("setShaderValidation:"), shaderValidation);
    }

    public void reset() {
        sendVoid(id, "reset");
    }

    public MTLLinkedFunctions vertexLinkedFunctions() {
        return MTLLinkedFunctions.of(sendPtr(id, "vertexLinkedFunctions"));
    }

    @SneakyThrows
    public void setVertexLinkedFunctions(MTLLinkedFunctions functions) {
        P.invokeExact(id, ObjC.sel("setVertexLinkedFunctions:"), functions.getId());
    }

    public MTLLinkedFunctions fragmentLinkedFunctions() {
        return MTLLinkedFunctions.of(sendPtr(id, "fragmentLinkedFunctions"));
    }

    @SneakyThrows
    public void setFragmentLinkedFunctions(MTLLinkedFunctions functions) {
        P.invokeExact(id, ObjC.sel("setFragmentLinkedFunctions:"), functions.getId());
    }

    public NSArray binaryArchives() {
        return NSArray.of(sendPtr(id, "binaryArchives"));
    }

    @SneakyThrows
    public void setBinaryArchives(NSArray archives) {
        P.invokeExact(id, ObjC.sel("setBinaryArchives:"), archives.getId());
    }

    public NSArray vertexPreloadedLibraries() {
        return NSArray.of(sendPtr(id, "vertexPreloadedLibraries"));
    }

    @SneakyThrows
    public void setVertexPreloadedLibraries(NSArray libraries) {
        P.invokeExact(id, ObjC.sel("setVertexPreloadedLibraries:"), libraries.getId());
    }

    public NSArray fragmentPreloadedLibraries() {
        return NSArray.of(sendPtr(id, "fragmentPreloadedLibraries"));
    }

    @SneakyThrows
    public void setFragmentPreloadedLibraries(NSArray libraries) {
        P.invokeExact(id, ObjC.sel("setFragmentPreloadedLibraries:"), libraries.getId());
    }

    public MTLLogicalToPhysicalColorAttachmentMap colorAttachmentMap() {
        return MTLLogicalToPhysicalColorAttachmentMap.of(sendPtr(id, "colorAttachmentMap"));
    }

    @SneakyThrows
    public void setColorAttachmentMap(MTLLogicalToPhysicalColorAttachmentMap map) {
        P.invokeExact(id, ObjC.sel("setColorAttachmentMap:"), map.getId());
    }
}
