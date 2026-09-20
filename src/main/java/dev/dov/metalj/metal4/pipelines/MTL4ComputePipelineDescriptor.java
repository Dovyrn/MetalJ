package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.metal4.compiler.MTL4FunctionDescriptor;
import dev.dov.metalj.metal4.compiler.MTL4StaticLinkingDescriptor;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4ComputePipelineDescriptor extends MTL4PipelineDescriptor {
    private static final MethodHandle S = handle(null, MTLSize.LAYOUT);
    private static final MethodHandle SIZE = structHandle(MTLSize.LAYOUT);

    private MTL4ComputePipelineDescriptor(long id) {
        super(id);
    }

    public static MTL4ComputePipelineDescriptor of(long id) {
        return new MTL4ComputePipelineDescriptor(id);
    }

    public static MTL4ComputePipelineDescriptor new_() {
        return new MTL4ComputePipelineDescriptor(sendPtr(ObjC.cls("MTL4ComputePipelineDescriptor"), "new"));
    }

    public MTL4FunctionDescriptor computeFunctionDescriptor() {
        return MTL4FunctionDescriptor.of(sendPtr(id, "computeFunctionDescriptor"));
    }

    @SneakyThrows
    public void setComputeFunctionDescriptor(MTL4FunctionDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setComputeFunctionDescriptor:"), descriptor.getId());
    }

    public boolean threadGroupSizeIsMultipleOfThreadExecutionWidth() {
        return sendBool(id, "threadGroupSizeIsMultipleOfThreadExecutionWidth");
    }

    @SneakyThrows
    public void setThreadGroupSizeIsMultipleOfThreadExecutionWidth(boolean multiple) {
        B.invokeExact(id, ObjC.sel("setThreadGroupSizeIsMultipleOfThreadExecutionWidth:"), multiple);
    }

    public long maxTotalThreadsPerThreadgroup() {
        return sendLong(id, "maxTotalThreadsPerThreadgroup");
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerThreadgroup(long threads) {
        L.invokeExact(id, ObjC.sel("setMaxTotalThreadsPerThreadgroup:"), threads);
    }

    @SneakyThrows
    public MemorySegment requiredThreadsPerThreadgroup(SegmentAllocator allocator) {
        return (MemorySegment) SIZE.invokeExact(allocator, id, ObjC.sel("requiredThreadsPerThreadgroup"));
    }

    @SneakyThrows
    public void setRequiredThreadsPerThreadgroup(MemorySegment threads) {
        S.invokeExact(id, ObjC.sel("setRequiredThreadsPerThreadgroup:"), threads);
    }

    public boolean supportBinaryLinking() {
        return sendBool(id, "supportBinaryLinking");
    }

    @SneakyThrows
    public void setSupportBinaryLinking(boolean support) {
        B.invokeExact(id, ObjC.sel("setSupportBinaryLinking:"), support);
    }

    public MTL4StaticLinkingDescriptor staticLinkingDescriptor() {
        return MTL4StaticLinkingDescriptor.of(sendPtr(id, "staticLinkingDescriptor"));
    }

    @SneakyThrows
    public void setStaticLinkingDescriptor(MTL4StaticLinkingDescriptor descriptor) {
        P.invokeExact(id, ObjC.sel("setStaticLinkingDescriptor:"), descriptor.getId());
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
