package dev.dov.metalj.pipelines.compute;

import dev.dov.metalj.pipelines.shaders.MTLFunction;
import dev.dov.metalj.pipelines.render.MTLPipelineBufferDescriptorArray;
import dev.dov.metalj.functions.MTLLinkedFunctions;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLComputePipelineDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLComputePipelineDescriptor(long id) {
        super(id);
    }

    public static MTLComputePipelineDescriptor of(long id) {
        return new MTLComputePipelineDescriptor(id);
    }

    public static MTLComputePipelineDescriptor new_() {
        return new MTLComputePipelineDescriptor(sendPtr(ObjC.cls("MTLComputePipelineDescriptor"), "new"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    public MTLFunction computeFunction() {
        return MTLFunction.of(sendPtr(id, "computeFunction"));
    }

    @SneakyThrows
    public void setComputeFunction(MTLFunction computeFunction) {
        P.invokeExact(id, ObjC.sel("setComputeFunction:"), computeFunction.getId());
    }

    public boolean threadGroupSizeIsMultipleOfThreadExecutionWidth() {
        return sendBool(id, "threadGroupSizeIsMultipleOfThreadExecutionWidth");
    }

    @SneakyThrows
    public void setThreadGroupSizeIsMultipleOfThreadExecutionWidth(boolean threadGroupSizeIsMultipleOfThreadExecutionWidth) {
        B.invokeExact(id, ObjC.sel("setThreadGroupSizeIsMultipleOfThreadExecutionWidth:"), threadGroupSizeIsMultipleOfThreadExecutionWidth);
    }

    public long maxTotalThreadsPerThreadgroup() {
        return sendLong(id, "maxTotalThreadsPerThreadgroup");
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerThreadgroup(long maxTotalThreadsPerThreadgroup) {
        L.invokeExact(id, ObjC.sel("setMaxTotalThreadsPerThreadgroup:"), maxTotalThreadsPerThreadgroup);
    }

    public MTLPipelineBufferDescriptorArray buffers() {
        return MTLPipelineBufferDescriptorArray.of(sendPtr(id, "buffers"));
    }

    public boolean supportIndirectCommandBuffers() {
        return sendBool(id, "supportIndirectCommandBuffers");
    }

    @SneakyThrows
    public void setSupportIndirectCommandBuffers(boolean supportIndirectCommandBuffers) {
        B.invokeExact(id, ObjC.sel("setSupportIndirectCommandBuffers:"), supportIndirectCommandBuffers);
    }

    public long maxCallStackDepth() {
        return sendLong(id, "maxCallStackDepth");
    }

    @SneakyThrows
    public void setMaxCallStackDepth(long maxCallStackDepth) {
        L.invokeExact(id, ObjC.sel("setMaxCallStackDepth:"), maxCallStackDepth);
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

    public MTLLinkedFunctions linkedFunctions() {
        return MTLLinkedFunctions.of(sendPtr(id, "linkedFunctions"));
    }

    @SneakyThrows
    public void setLinkedFunctions(MTLLinkedFunctions functions) {
        P.invokeExact(id, ObjC.sel("setLinkedFunctions:"), functions.getId());
    }

    public NSArray binaryArchives() {
        return NSArray.of(sendPtr(id, "binaryArchives"));
    }

    @SneakyThrows
    public void setBinaryArchives(NSArray archives) {
        P.invokeExact(id, ObjC.sel("setBinaryArchives:"), archives.getId());
    }

    public NSArray preloadedLibraries() {
        return NSArray.of(sendPtr(id, "preloadedLibraries"));
    }

    @SneakyThrows
    public void setPreloadedLibraries(NSArray libraries) {
        P.invokeExact(id, ObjC.sel("setPreloadedLibraries:"), libraries.getId());
    }

    public MTLStageInputOutputDescriptor stageInputDescriptor() {
        return MTLStageInputOutputDescriptor.of(sendPtr(id, "stageInputDescriptor"));
    }

    @SneakyThrows
    public void setStageInputDescriptor(MTLStageInputOutputDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setStageInputDescriptor:"), descriptor.getId());
    }
}
