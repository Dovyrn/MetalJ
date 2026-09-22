package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.metal4.compiler.MTL4FunctionDescriptor;
import dev.dov.metalj.metal4.compiler.MTL4StaticLinkingDescriptor;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.vertex.MTLVertexDescriptor;
import lombok.SneakyThrows;

public class MTL4RenderPipelineDescriptor extends MTL4PipelineDescriptor {
    private static final long MTL_4_RENDER_PIPELINE_DESCRIPTOR = ObjC.cls("MTL4RenderPipelineDescriptor");

    private static final long ALPHA_TO_COVERAGE_STATE = ObjC.sel("alphaToCoverageState");
    private static final long ALPHA_TO_ONE_STATE = ObjC.sel("alphaToOneState");
    private static final long COLOR_ATTACHMENT_MAPPING_STATE = ObjC.sel("colorAttachmentMappingState");
    private static final long COLOR_ATTACHMENTS = ObjC.sel("colorAttachments");
    private static final long FRAGMENT_FUNCTION_DESCRIPTOR = ObjC.sel("fragmentFunctionDescriptor");
    private static final long FRAGMENT_STATIC_LINKING_DESCRIPTOR = ObjC.sel("fragmentStaticLinkingDescriptor");
    private static final long INPUT_PRIMITIVE_TOPOLOGY = ObjC.sel("inputPrimitiveTopology");
    private static final long IS_RASTERIZATION_ENABLED = ObjC.sel("isRasterizationEnabled");
    private static final long MAX_VERTEX_AMPLIFICATION_COUNT = ObjC.sel("maxVertexAmplificationCount");
    private static final long NEW = ObjC.sel("new");
    private static final long RASTER_SAMPLE_COUNT = ObjC.sel("rasterSampleCount");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_ALPHA_TO_COVERAGE_STATE = ObjC.sel("setAlphaToCoverageState:");
    private static final long SET_ALPHA_TO_ONE_STATE = ObjC.sel("setAlphaToOneState:");
    private static final long SET_COLOR_ATTACHMENT_MAPPING_STATE = ObjC.sel("setColorAttachmentMappingState:");
    private static final long SET_FRAGMENT_FUNCTION_DESCRIPTOR = ObjC.sel("setFragmentFunctionDescriptor:");
    private static final long SET_FRAGMENT_STATIC_LINKING_DESCRIPTOR = ObjC.sel("setFragmentStaticLinkingDescriptor:");
    private static final long SET_INPUT_PRIMITIVE_TOPOLOGY = ObjC.sel("setInputPrimitiveTopology:");
    private static final long SET_MAX_VERTEX_AMPLIFICATION_COUNT = ObjC.sel("setMaxVertexAmplificationCount:");
    private static final long SET_RASTER_SAMPLE_COUNT = ObjC.sel("setRasterSampleCount:");
    private static final long SET_RASTERIZATION_ENABLED = ObjC.sel("setRasterizationEnabled:");
    private static final long SET_SUPPORT_FRAGMENT_BINARY_LINKING = ObjC.sel("setSupportFragmentBinaryLinking:");
    private static final long SET_SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("setSupportIndirectCommandBuffers:");
    private static final long SET_SUPPORT_VERTEX_BINARY_LINKING = ObjC.sel("setSupportVertexBinaryLinking:");
    private static final long SET_VERTEX_DESCRIPTOR = ObjC.sel("setVertexDescriptor:");
    private static final long SET_VERTEX_FUNCTION_DESCRIPTOR = ObjC.sel("setVertexFunctionDescriptor:");
    private static final long SET_VERTEX_STATIC_LINKING_DESCRIPTOR = ObjC.sel("setVertexStaticLinkingDescriptor:");
    private static final long SUPPORT_FRAGMENT_BINARY_LINKING = ObjC.sel("supportFragmentBinaryLinking");
    private static final long SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("supportIndirectCommandBuffers");
    private static final long SUPPORT_VERTEX_BINARY_LINKING = ObjC.sel("supportVertexBinaryLinking");
    private static final long VERTEX_DESCRIPTOR = ObjC.sel("vertexDescriptor");
    private static final long VERTEX_FUNCTION_DESCRIPTOR = ObjC.sel("vertexFunctionDescriptor");
    private static final long VERTEX_STATIC_LINKING_DESCRIPTOR = ObjC.sel("vertexStaticLinkingDescriptor");

    private MTL4RenderPipelineDescriptor(long id) {
        super(id);
    }

    public static MTL4RenderPipelineDescriptor of(long id) {
        return new MTL4RenderPipelineDescriptor(id);
    }

    public static MTL4RenderPipelineDescriptor new_() {
        return new MTL4RenderPipelineDescriptor(sendPtr(MTL_4_RENDER_PIPELINE_DESCRIPTOR, NEW));
    }

    public MTL4FunctionDescriptor vertexFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, VERTEX_FUNCTION_DESCRIPTOR));
    }

    @SneakyThrows
    public void setVertexFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, SET_VERTEX_FUNCTION_DESCRIPTOR, descriptor.getId());
    }

    public MTL4FunctionDescriptor fragmentFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, FRAGMENT_FUNCTION_DESCRIPTOR));
    }

    @SneakyThrows
    public void setFragmentFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, SET_FRAGMENT_FUNCTION_DESCRIPTOR, descriptor.getId());
    }

    public MTLVertexDescriptor vertexDescriptor() {
        return MTLVertexDescriptor.of(sendPtr(id, VERTEX_DESCRIPTOR));
    }

    @SneakyThrows
    public void setVertexDescriptor(MTLVertexDescriptor descriptor) {
        P.invokeExact(id, SET_VERTEX_DESCRIPTOR, descriptor.getId());
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

    public long inputPrimitiveTopology() {
        return sendLong(id, INPUT_PRIMITIVE_TOPOLOGY);
    }

    @SneakyThrows
    public void setInputPrimitiveTopology(long topology) {
        L.invokeExact(id, SET_INPUT_PRIMITIVE_TOPOLOGY, topology);
    }

    public MTL4StaticLinkingDescriptor vertexStaticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, VERTEX_STATIC_LINKING_DESCRIPTOR));
    }

    @SneakyThrows
    public void setVertexStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, SET_VERTEX_STATIC_LINKING_DESCRIPTOR, descriptor.getId());
    }

    public MTL4StaticLinkingDescriptor fragmentStaticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, FRAGMENT_STATIC_LINKING_DESCRIPTOR));
    }

    @SneakyThrows
    public void setFragmentStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, SET_FRAGMENT_STATIC_LINKING_DESCRIPTOR, descriptor.getId());
    }

    public boolean supportVertexBinaryLinking() {
        return sendBool(id, SUPPORT_VERTEX_BINARY_LINKING);
    }

    @SneakyThrows
    public void setSupportVertexBinaryLinking(boolean support) {
        B.invokeExact(id, SET_SUPPORT_VERTEX_BINARY_LINKING, support);
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
