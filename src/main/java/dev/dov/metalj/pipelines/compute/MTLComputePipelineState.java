package dev.dov.metalj.pipelines.compute;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.pipelines.shaders.MTLFunction;
import dev.dov.metalj.pipelines.shaders.MTLFunctionHandle;
import dev.dov.metalj.raytracing.MTLIntersectionFunctionTable;
import dev.dov.metalj.raytracing.MTLIntersectionFunctionTableDescriptor;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLComputePipelineState extends NSObject {
    private static final MethodHandle L_S = handle(ObjC.LONG, MTLSize.LAYOUT);
    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);

    private MTLComputePipelineState(long id) {
        super(id);
    }

    public static MTLComputePipelineState of(long id) {
        return new MTLComputePipelineState(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public long maxTotalThreadsPerThreadgroup() {
        return sendLong(id, "maxTotalThreadsPerThreadgroup");
    }

    public long threadExecutionWidth() {
        return sendLong(id, "threadExecutionWidth");
    }

    public long staticThreadgroupMemoryLength() {
        return sendLong(id, "staticThreadgroupMemoryLength");
    }

    @SneakyThrows
    public long imageblockMemoryLengthForDimensions(MemorySegment size) {
        return (long) L_S.invokeExact(id, ObjC.sel("imageblockMemoryLengthForDimensions:"), size);
    }

    public boolean supportIndirectCommandBuffers() {
        return sendBool(id, "supportIndirectCommandBuffers");
    }

    public long gpuResourceID() {
        return sendLong(id, "gpuResourceID");
    }

    @SneakyThrows
    public MTLFunctionHandle functionHandleWithFunction(MTLFunction function) {
        return MTLFunctionHandle.of((long) P_P.invokeExact(id, ObjC.sel("functionHandleWithFunction:"),
                function.getId()));
    }

    @SneakyThrows
    public MTLIntersectionFunctionTable newIntersectionFunctionTableWithDescriptor(
            MTLIntersectionFunctionTableDescriptor descriptor) {
        return MTLIntersectionFunctionTable.of((long) P_P.invokeExact(id,
                ObjC.sel("newIntersectionFunctionTableWithDescriptor:"), descriptor.getId()));
    }
}
