package dev.dov.metalj.commands;

import dev.dov.metalj.commands.passes.MTLComputePassDescriptor;
import dev.dov.metalj.commands.passes.MTLBlitPassDescriptor;
import dev.dov.metalj.commands.passes.MTLRenderPassDescriptor;
import dev.dov.metalj.commands.encoders.MTLComputeCommandEncoder;
import dev.dov.metalj.commands.encoders.MTLRenderCommandEncoder;
import dev.dov.metalj.commands.encoders.MTLBlitCommandEncoder;
import dev.dov.metalj.device.CAMetalDrawable;
import dev.dov.metalj.objc.Block;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSError;
import dev.dov.metalj.raytracing.MTLAccelerationStructureCommandEncoder;
import dev.dov.metalj.commands.encoders.MTLParallelRenderCommandEncoder;
import dev.dov.metalj.residency.MTLResidencySet;
import dev.dov.metalj.state.MTLResourceStateCommandEncoder;
import dev.dov.metalj.state.MTLResourceStatePassDescriptor;
import dev.dov.metalj.sync.MTLEvent;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCommandBuffer extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle D = handle(ObjC.DOUBLE);

    public static final long MTLCommandBufferStatusNotEnqueued = 0;
    public static final long MTLCommandBufferStatusEnqueued = 1;
    public static final long MTLCommandBufferStatusCommitted = 2;
    public static final long MTLCommandBufferStatusScheduled = 3;
    public static final long MTLCommandBufferStatusCompleted = 4;
    public static final long MTLCommandBufferStatusError = 5;

    private MTLCommandBuffer(long id) {
        super(id);
    }

    public static MTLCommandBuffer of(long id) {
        return new MTLCommandBuffer(id);
    }

    public void enqueue() {
        sendVoid(id, "enqueue");
    }

    public void commit() {
        sendVoid(id, "commit");
    }

    public void waitUntilScheduled() {
        sendVoid(id, "waitUntilScheduled");
    }

    public void waitUntilCompleted() {
        sendVoid(id, "waitUntilCompleted");
    }

    @SneakyThrows
    public void presentDrawable(CAMetalDrawable drawable) {
        P.invokeExact(id, ObjC.sel("presentDrawable:"), drawable.getId());
    }

    @SneakyThrows
    public void addScheduledHandler(Block block) {
        P.invokeExact(id, ObjC.sel("addScheduledHandler:"), block.address());
    }

    @SneakyThrows
    public void addCompletedHandler(Block block) {
        P.invokeExact(id, ObjC.sel("addCompletedHandler:"), block.address());
    }

    public long status() {
        return sendLong(id, "status");
    }

    public NSError error() {
        return NSError.of(sendPtr(id, "error"));
    }

    @SneakyThrows
    public double GPUStartTime() {
        return (double) D.invokeExact(id, ObjC.sel("GPUStartTime"));
    }

    @SneakyThrows
    public double GPUEndTime() {
        return (double) D.invokeExact(id, ObjC.sel("GPUEndTime"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    @SneakyThrows
    public void pushDebugGroup(NSString string) {
        P.invokeExact(id, ObjC.sel("pushDebugGroup:"), string.getId());
    }

    public void popDebugGroup() {
        sendVoid(id, "popDebugGroup");
    }

    @SneakyThrows
    public MTLRenderCommandEncoder renderCommandEncoderWithDescriptor(MTLRenderPassDescriptor descriptor) {
        return MTLRenderCommandEncoder.of((long) P_P.invokeExact(id,
                ObjC.sel("renderCommandEncoderWithDescriptor:"), descriptor.getId()));
    }

    public MTLComputeCommandEncoder computeCommandEncoder() {
        return MTLComputeCommandEncoder.of(sendPtr(id, "computeCommandEncoder"));
    }

    @SneakyThrows
    public MTLComputeCommandEncoder computeCommandEncoderWithDescriptor(MTLComputePassDescriptor descriptor) {
        return MTLComputeCommandEncoder.of((long) P_P.invokeExact(id,
                ObjC.sel("computeCommandEncoderWithDescriptor:"), descriptor.getId()));
    }

    public MTLBlitCommandEncoder blitCommandEncoder() {
        return MTLBlitCommandEncoder.of(sendPtr(id, "blitCommandEncoder"));
    }

    @SneakyThrows
    public MTLBlitCommandEncoder blitCommandEncoderWithDescriptor(MTLBlitPassDescriptor descriptor) {
        return MTLBlitCommandEncoder.of((long) P_P.invokeExact(id,
                ObjC.sel("blitCommandEncoderWithDescriptor:"), descriptor.getId()));
    }

    public MTLAccelerationStructureCommandEncoder accelerationStructureCommandEncoder() {
        return MTLAccelerationStructureCommandEncoder.of(sendPtr(id, "accelerationStructureCommandEncoder"));
    }

    @SneakyThrows
    public void encodeSignalEvent(MTLEvent event, long value) {
        PL.invokeExact(id, ObjC.sel("encodeSignalEvent:value:"), event.getId(), value);
    }

    @SneakyThrows
    public void encodeWaitForEvent(MTLEvent event, long value) {
        PL.invokeExact(id, ObjC.sel("encodeWaitForEvent:value:"), event.getId(), value);
    }

    @SneakyThrows
    public void useResidencySet(MTLResidencySet set) {
        P.invokeExact(id, ObjC.sel("useResidencySet:"), set.getId());
    }

    @SneakyThrows
    public MTLParallelRenderCommandEncoder parallelRenderCommandEncoderWithDescriptor(
            MTLRenderPassDescriptor descriptor) {
        return MTLParallelRenderCommandEncoder.of((long) P_P.invokeExact(id,
                ObjC.sel("parallelRenderCommandEncoderWithDescriptor:"), descriptor.getId()));
    }

    public MTLResourceStateCommandEncoder resourceStateCommandEncoder() {
        return MTLResourceStateCommandEncoder.of(sendPtr(id, "resourceStateCommandEncoder"));
    }

    @SneakyThrows
    public MTLResourceStateCommandEncoder resourceStateCommandEncoderWithDescriptor(
            MTLResourceStatePassDescriptor descriptor) {
        return MTLResourceStateCommandEncoder.of((long) P_P.invokeExact(id,
                ObjC.sel("resourceStateCommandEncoderWithDescriptor:"), descriptor.getId()));
    }

    public NSArray logs() {
        return NSArray.of(sendPtr(id, "logs"));
    }
}
