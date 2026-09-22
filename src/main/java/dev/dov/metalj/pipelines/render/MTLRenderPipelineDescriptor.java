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
    private static final long MTL_RENDER_PIPELINE_DESCRIPTOR = ObjC.cls("MTLRenderPipelineDescriptor");

    private static final long BINARY_ARCHIVES = ObjC.sel("binaryArchives");
    private static final long COLOR_ATTACHMENT_MAP = ObjC.sel("colorAttachmentMap");
    private static final long COLOR_ATTACHMENTS = ObjC.sel("colorAttachments");
    private static final long DEPTH_ATTACHMENT_PIXEL_FORMAT = ObjC.sel("depthAttachmentPixelFormat");
    private static final long FRAGMENT_BUFFERS = ObjC.sel("fragmentBuffers");
    private static final long FRAGMENT_FUNCTION = ObjC.sel("fragmentFunction");
    private static final long FRAGMENT_LINKED_FUNCTIONS = ObjC.sel("fragmentLinkedFunctions");
    private static final long FRAGMENT_PRELOADED_LIBRARIES = ObjC.sel("fragmentPreloadedLibraries");
    private static final long INPUT_PRIMITIVE_TOPOLOGY = ObjC.sel("inputPrimitiveTopology");
    private static final long IS_ALPHA_TO_COVERAGE_ENABLED = ObjC.sel("isAlphaToCoverageEnabled");
    private static final long IS_ALPHA_TO_ONE_ENABLED = ObjC.sel("isAlphaToOneEnabled");
    private static final long IS_RASTERIZATION_ENABLED = ObjC.sel("isRasterizationEnabled");
    private static final long IS_TESSELLATION_FACTOR_SCALE_ENABLED = ObjC.sel("isTessellationFactorScaleEnabled");
    private static final long LABEL = ObjC.sel("label");
    private static final long MAX_FRAGMENT_CALL_STACK_DEPTH = ObjC.sel("maxFragmentCallStackDepth");
    private static final long MAX_TESSELLATION_FACTOR = ObjC.sel("maxTessellationFactor");
    private static final long MAX_VERTEX_AMPLIFICATION_COUNT = ObjC.sel("maxVertexAmplificationCount");
    private static final long MAX_VERTEX_CALL_STACK_DEPTH = ObjC.sel("maxVertexCallStackDepth");
    private static final long NEW = ObjC.sel("new");
    private static final long RASTER_SAMPLE_COUNT = ObjC.sel("rasterSampleCount");
    private static final long RESET = ObjC.sel("reset");
    private static final long SAMPLE_COUNT = ObjC.sel("sampleCount");
    private static final long SET_ALPHA_TO_COVERAGE_ENABLED = ObjC.sel("setAlphaToCoverageEnabled:");
    private static final long SET_ALPHA_TO_ONE_ENABLED = ObjC.sel("setAlphaToOneEnabled:");
    private static final long SET_BINARY_ARCHIVES = ObjC.sel("setBinaryArchives:");
    private static final long SET_COLOR_ATTACHMENT_MAP = ObjC.sel("setColorAttachmentMap:");
    private static final long SET_DEPTH_ATTACHMENT_PIXEL_FORMAT = ObjC.sel("setDepthAttachmentPixelFormat:");
    private static final long SET_FRAGMENT_FUNCTION = ObjC.sel("setFragmentFunction:");
    private static final long SET_FRAGMENT_LINKED_FUNCTIONS = ObjC.sel("setFragmentLinkedFunctions:");
    private static final long SET_FRAGMENT_PRELOADED_LIBRARIES = ObjC.sel("setFragmentPreloadedLibraries:");
    private static final long SET_INPUT_PRIMITIVE_TOPOLOGY = ObjC.sel("setInputPrimitiveTopology:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_MAX_FRAGMENT_CALL_STACK_DEPTH = ObjC.sel("setMaxFragmentCallStackDepth:");
    private static final long SET_MAX_TESSELLATION_FACTOR = ObjC.sel("setMaxTessellationFactor:");
    private static final long SET_MAX_VERTEX_AMPLIFICATION_COUNT = ObjC.sel("setMaxVertexAmplificationCount:");
    private static final long SET_MAX_VERTEX_CALL_STACK_DEPTH = ObjC.sel("setMaxVertexCallStackDepth:");
    private static final long SET_RASTER_SAMPLE_COUNT = ObjC.sel("setRasterSampleCount:");
    private static final long SET_RASTERIZATION_ENABLED = ObjC.sel("setRasterizationEnabled:");
    private static final long SET_SAMPLE_COUNT = ObjC.sel("setSampleCount:");
    private static final long SET_SHADER_VALIDATION = ObjC.sel("setShaderValidation:");
    private static final long SET_STENCIL_ATTACHMENT_PIXEL_FORMAT = ObjC.sel("setStencilAttachmentPixelFormat:");
    private static final long SET_SUPPORT_ADDING_FRAGMENT_BINARY_FUNCTIONS = ObjC.sel("setSupportAddingFragmentBinaryFunctions:");
    private static final long SET_SUPPORT_ADDING_VERTEX_BINARY_FUNCTIONS = ObjC.sel("setSupportAddingVertexBinaryFunctions:");
    private static final long SET_SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("setSupportIndirectCommandBuffers:");
    private static final long SET_TESSELLATION_CONTROL_POINT_INDEX_TYPE = ObjC.sel("setTessellationControlPointIndexType:");
    private static final long SET_TESSELLATION_FACTOR_FORMAT = ObjC.sel("setTessellationFactorFormat:");
    private static final long SET_TESSELLATION_FACTOR_SCALE_ENABLED = ObjC.sel("setTessellationFactorScaleEnabled:");
    private static final long SET_TESSELLATION_FACTOR_STEP_FUNCTION = ObjC.sel("setTessellationFactorStepFunction:");
    private static final long SET_TESSELLATION_OUTPUT_WINDING_ORDER = ObjC.sel("setTessellationOutputWindingOrder:");
    private static final long SET_TESSELLATION_PARTITION_MODE = ObjC.sel("setTessellationPartitionMode:");
    private static final long SET_VERTEX_DESCRIPTOR = ObjC.sel("setVertexDescriptor:");
    private static final long SET_VERTEX_FUNCTION = ObjC.sel("setVertexFunction:");
    private static final long SET_VERTEX_LINKED_FUNCTIONS = ObjC.sel("setVertexLinkedFunctions:");
    private static final long SET_VERTEX_PRELOADED_LIBRARIES = ObjC.sel("setVertexPreloadedLibraries:");
    private static final long SHADER_VALIDATION = ObjC.sel("shaderValidation");
    private static final long STENCIL_ATTACHMENT_PIXEL_FORMAT = ObjC.sel("stencilAttachmentPixelFormat");
    private static final long SUPPORT_ADDING_FRAGMENT_BINARY_FUNCTIONS = ObjC.sel("supportAddingFragmentBinaryFunctions");
    private static final long SUPPORT_ADDING_VERTEX_BINARY_FUNCTIONS = ObjC.sel("supportAddingVertexBinaryFunctions");
    private static final long SUPPORT_INDIRECT_COMMAND_BUFFERS = ObjC.sel("supportIndirectCommandBuffers");
    private static final long TESSELLATION_CONTROL_POINT_INDEX_TYPE = ObjC.sel("tessellationControlPointIndexType");
    private static final long TESSELLATION_FACTOR_FORMAT = ObjC.sel("tessellationFactorFormat");
    private static final long TESSELLATION_FACTOR_STEP_FUNCTION = ObjC.sel("tessellationFactorStepFunction");
    private static final long TESSELLATION_OUTPUT_WINDING_ORDER = ObjC.sel("tessellationOutputWindingOrder");
    private static final long TESSELLATION_PARTITION_MODE = ObjC.sel("tessellationPartitionMode");
    private static final long VERTEX_BUFFERS = ObjC.sel("vertexBuffers");
    private static final long VERTEX_DESCRIPTOR = ObjC.sel("vertexDescriptor");
    private static final long VERTEX_FUNCTION = ObjC.sel("vertexFunction");
    private static final long VERTEX_LINKED_FUNCTIONS = ObjC.sel("vertexLinkedFunctions");
    private static final long VERTEX_PRELOADED_LIBRARIES = ObjC.sel("vertexPreloadedLibraries");

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
        return new MTLRenderPipelineDescriptor(sendPtr(MTL_RENDER_PIPELINE_DESCRIPTOR, NEW));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public MTLFunction vertexFunction() {
        return MTLFunction.of(sendPtr(id, VERTEX_FUNCTION));
    }

    @SneakyThrows
    public void setVertexFunction(MTLFunction vertexFunction) {
        P.invokeExact(id, SET_VERTEX_FUNCTION, vertexFunction.getId());
    }

    public MTLFunction fragmentFunction() {
        return MTLFunction.of(sendPtr(id, FRAGMENT_FUNCTION));
    }

    @SneakyThrows
    public void setFragmentFunction(MTLFunction fragmentFunction) {
        P.invokeExact(id, SET_FRAGMENT_FUNCTION, fragmentFunction.getId());
    }

    public MTLVertexDescriptor vertexDescriptor() {
        return MTLVertexDescriptor.of(sendPtr(id, VERTEX_DESCRIPTOR));
    }

    @SneakyThrows
    public void setVertexDescriptor(MTLVertexDescriptor vertexDescriptor) {
        P.invokeExact(id, SET_VERTEX_DESCRIPTOR, vertexDescriptor.getId());
    }

    public MTLPipelineBufferDescriptorArray vertexBuffers() {
        return MTLPipelineBufferDescriptorArray.of(sendPtr(id, VERTEX_BUFFERS));
    }

    public MTLPipelineBufferDescriptorArray fragmentBuffers() {
        return MTLPipelineBufferDescriptorArray.of(sendPtr(id, FRAGMENT_BUFFERS));
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

    public long sampleCount() {
        return sendLong(id, SAMPLE_COUNT);
    }

    @SneakyThrows
    public void setSampleCount(long sampleCount) {
        L.invokeExact(id, SET_SAMPLE_COUNT, sampleCount);
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

    public long inputPrimitiveTopology() {
        return sendLong(id, INPUT_PRIMITIVE_TOPOLOGY);
    }

    @SneakyThrows
    public void setInputPrimitiveTopology(long inputPrimitiveTopology) {
        L.invokeExact(id, SET_INPUT_PRIMITIVE_TOPOLOGY, inputPrimitiveTopology);
    }

    public long tessellationPartitionMode() {
        return sendLong(id, TESSELLATION_PARTITION_MODE);
    }

    @SneakyThrows
    public void setTessellationPartitionMode(long tessellationPartitionMode) {
        L.invokeExact(id, SET_TESSELLATION_PARTITION_MODE, tessellationPartitionMode);
    }

    public long maxTessellationFactor() {
        return sendLong(id, MAX_TESSELLATION_FACTOR);
    }

    @SneakyThrows
    public void setMaxTessellationFactor(long maxTessellationFactor) {
        L.invokeExact(id, SET_MAX_TESSELLATION_FACTOR, maxTessellationFactor);
    }

    public boolean isTessellationFactorScaleEnabled() {
        return sendBool(id, IS_TESSELLATION_FACTOR_SCALE_ENABLED);
    }

    @SneakyThrows
    public void setTessellationFactorScaleEnabled(boolean tessellationFactorScaleEnabled) {
        B.invokeExact(id, SET_TESSELLATION_FACTOR_SCALE_ENABLED, tessellationFactorScaleEnabled);
    }

    public long tessellationFactorFormat() {
        return sendLong(id, TESSELLATION_FACTOR_FORMAT);
    }

    @SneakyThrows
    public void setTessellationFactorFormat(long tessellationFactorFormat) {
        L.invokeExact(id, SET_TESSELLATION_FACTOR_FORMAT, tessellationFactorFormat);
    }

    public long tessellationControlPointIndexType() {
        return sendLong(id, TESSELLATION_CONTROL_POINT_INDEX_TYPE);
    }

    @SneakyThrows
    public void setTessellationControlPointIndexType(long tessellationControlPointIndexType) {
        L.invokeExact(id, SET_TESSELLATION_CONTROL_POINT_INDEX_TYPE, tessellationControlPointIndexType);
    }

    public long tessellationFactorStepFunction() {
        return sendLong(id, TESSELLATION_FACTOR_STEP_FUNCTION);
    }

    @SneakyThrows
    public void setTessellationFactorStepFunction(long tessellationFactorStepFunction) {
        L.invokeExact(id, SET_TESSELLATION_FACTOR_STEP_FUNCTION, tessellationFactorStepFunction);
    }

    public long tessellationOutputWindingOrder() {
        return sendLong(id, TESSELLATION_OUTPUT_WINDING_ORDER);
    }

    @SneakyThrows
    public void setTessellationOutputWindingOrder(long tessellationOutputWindingOrder) {
        L.invokeExact(id, SET_TESSELLATION_OUTPUT_WINDING_ORDER, tessellationOutputWindingOrder);
    }

    public long maxVertexAmplificationCount() {
        return sendLong(id, MAX_VERTEX_AMPLIFICATION_COUNT);
    }

    @SneakyThrows
    public void setMaxVertexAmplificationCount(long maxVertexAmplificationCount) {
        L.invokeExact(id, SET_MAX_VERTEX_AMPLIFICATION_COUNT, maxVertexAmplificationCount);
    }

    public boolean supportIndirectCommandBuffers() {
        return sendBool(id, SUPPORT_INDIRECT_COMMAND_BUFFERS);
    }

    @SneakyThrows
    public void setSupportIndirectCommandBuffers(boolean supportIndirectCommandBuffers) {
        B.invokeExact(id, SET_SUPPORT_INDIRECT_COMMAND_BUFFERS, supportIndirectCommandBuffers);
    }

    public boolean supportAddingVertexBinaryFunctions() {
        return sendBool(id, SUPPORT_ADDING_VERTEX_BINARY_FUNCTIONS);
    }

    @SneakyThrows
    public void setSupportAddingVertexBinaryFunctions(boolean supportAddingVertexBinaryFunctions) {
        B.invokeExact(id, SET_SUPPORT_ADDING_VERTEX_BINARY_FUNCTIONS, supportAddingVertexBinaryFunctions);
    }

    public boolean supportAddingFragmentBinaryFunctions() {
        return sendBool(id, SUPPORT_ADDING_FRAGMENT_BINARY_FUNCTIONS);
    }

    @SneakyThrows
    public void setSupportAddingFragmentBinaryFunctions(boolean supportAddingFragmentBinaryFunctions) {
        B.invokeExact(id, SET_SUPPORT_ADDING_FRAGMENT_BINARY_FUNCTIONS, supportAddingFragmentBinaryFunctions);
    }

    public long maxVertexCallStackDepth() {
        return sendLong(id, MAX_VERTEX_CALL_STACK_DEPTH);
    }

    @SneakyThrows
    public void setMaxVertexCallStackDepth(long maxVertexCallStackDepth) {
        L.invokeExact(id, SET_MAX_VERTEX_CALL_STACK_DEPTH, maxVertexCallStackDepth);
    }

    public long maxFragmentCallStackDepth() {
        return sendLong(id, MAX_FRAGMENT_CALL_STACK_DEPTH);
    }

    @SneakyThrows
    public void setMaxFragmentCallStackDepth(long maxFragmentCallStackDepth) {
        L.invokeExact(id, SET_MAX_FRAGMENT_CALL_STACK_DEPTH, maxFragmentCallStackDepth);
    }

    public long shaderValidation() {
        return sendLong(id, SHADER_VALIDATION);
    }

    @SneakyThrows
    public void setShaderValidation(long shaderValidation) {
        L.invokeExact(id, SET_SHADER_VALIDATION, shaderValidation);
    }

    public void reset() {
        sendVoid(id, RESET);
    }

    public MTLLinkedFunctions vertexLinkedFunctions() {
        return MTLLinkedFunctions.of(sendPtr(id, VERTEX_LINKED_FUNCTIONS));
    }

    @SneakyThrows
    public void setVertexLinkedFunctions(MTLLinkedFunctions functions) {
        P.invokeExact(id, SET_VERTEX_LINKED_FUNCTIONS, functions.getId());
    }

    public MTLLinkedFunctions fragmentLinkedFunctions() {
        return MTLLinkedFunctions.of(sendPtr(id, FRAGMENT_LINKED_FUNCTIONS));
    }

    @SneakyThrows
    public void setFragmentLinkedFunctions(MTLLinkedFunctions functions) {
        P.invokeExact(id, SET_FRAGMENT_LINKED_FUNCTIONS, functions.getId());
    }

    public NSArray binaryArchives() {
        return NSArray.of(sendPtr(id, BINARY_ARCHIVES));
    }

    @SneakyThrows
    public void setBinaryArchives(NSArray archives) {
        P.invokeExact(id, SET_BINARY_ARCHIVES, archives.getId());
    }

    public NSArray vertexPreloadedLibraries() {
        return NSArray.of(sendPtr(id, VERTEX_PRELOADED_LIBRARIES));
    }

    @SneakyThrows
    public void setVertexPreloadedLibraries(NSArray libraries) {
        P.invokeExact(id, SET_VERTEX_PRELOADED_LIBRARIES, libraries.getId());
    }

    public NSArray fragmentPreloadedLibraries() {
        return NSArray.of(sendPtr(id, FRAGMENT_PRELOADED_LIBRARIES));
    }

    @SneakyThrows
    public void setFragmentPreloadedLibraries(NSArray libraries) {
        P.invokeExact(id, SET_FRAGMENT_PRELOADED_LIBRARIES, libraries.getId());
    }

    public MTLLogicalToPhysicalColorAttachmentMap colorAttachmentMap() {
        return MTLLogicalToPhysicalColorAttachmentMap.of(sendPtr(id, COLOR_ATTACHMENT_MAP));
    }

    @SneakyThrows
    public void setColorAttachmentMap(MTLLogicalToPhysicalColorAttachmentMap map) {
        P.invokeExact(id, SET_COLOR_ATTACHMENT_MAP, map.getId());
    }
}
