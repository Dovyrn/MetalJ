package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.metal4.compiler.MTL4FunctionDescriptor;
import dev.dov.metalj.metal4.compiler.MTL4StaticLinkingDescriptor;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.vertex.MTLVertexDescriptor;
import lombok.SneakyThrows;

public class MTL4RenderPipelineDescriptor extends MTL4PipelineDescriptor {
    private MTL4RenderPipelineDescriptor(long id) {
        super(id);
    }

    public static MTL4RenderPipelineDescriptor of(long id) {
        return new MTL4RenderPipelineDescriptor(id);
    }

    public static MTL4RenderPipelineDescriptor new_() {
        return new MTL4RenderPipelineDescriptor(sendPtr(ObjC.cls("MTL4RenderPipelineDescriptor"), "new"));
    }

    public MTL4FunctionDescriptor vertexFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, "vertexFunctionDescriptor"));
    }

    @SneakyThrows
    public void setVertexFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setVertexFunctionDescriptor:"), descriptor.getId());
    }

    public MTL4FunctionDescriptor fragmentFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, "fragmentFunctionDescriptor"));
    }

    @SneakyThrows
    public void setFragmentFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setFragmentFunctionDescriptor:"), descriptor.getId());
    }

    public MTLVertexDescriptor vertexDescriptor() {
        return MTLVertexDescriptor.of(sendPtr(id, "vertexDescriptor"));
    }

    @SneakyThrows
    public void setVertexDescriptor(MTLVertexDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setVertexDescriptor:"), descriptor.getId());
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

    public long inputPrimitiveTopology() {
        return sendLong(id, "inputPrimitiveTopology");
    }

    @SneakyThrows
    public void setInputPrimitiveTopology(long topology) {
        L.invokeExact(id, ObjC.sel("setInputPrimitiveTopology:"), topology);
    }

    public MTL4StaticLinkingDescriptor vertexStaticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, "vertexStaticLinkingDescriptor"));
    }

    @SneakyThrows
    public void setVertexStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setVertexStaticLinkingDescriptor:"), descriptor.getId());
    }

    public MTL4StaticLinkingDescriptor fragmentStaticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, "fragmentStaticLinkingDescriptor"));
    }

    @SneakyThrows
    public void setFragmentStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setFragmentStaticLinkingDescriptor:"), descriptor.getId());
    }

    public boolean supportVertexBinaryLinking() {
        return sendBool(id, "supportVertexBinaryLinking");
    }

    @SneakyThrows
    public void setSupportVertexBinaryLinking(boolean support) {
        B.invokeExact(id, ObjC.sel("setSupportVertexBinaryLinking:"), support);
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
