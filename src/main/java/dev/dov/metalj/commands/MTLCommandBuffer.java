package dev.dov.metalj.commands;

import dev.dov.metalj.commands.passes.MTLComputePassDescriptor;
import dev.dov.metalj.commands.passes.MTLBlitPassDescriptor;
import dev.dov.metalj.commands.passes.MTLRenderPassDescriptor;
import dev.dov.metalj.commands.encoders.MTLComputeCommandEncoder;
import dev.dov.metalj.commands.encoders.MTLRenderCommandEncoder;
import dev.dov.metalj.commands.encoders.MTLBlitCommandEncoder;
import dev.dov.metalj.device.CAMetalDrawable;
import dev.dov.metalj.device.MTLCommandQueue;
import dev.dov.metalj.device.MTLDevice;
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
    private static final long GPU_END_TIME = ObjC.sel("GPUEndTime");
    private static final long GPU_START_TIME = ObjC.sel("GPUStartTime");
    private static final long ACCELERATION_STRUCTURE_COMMAND_ENCODER = ObjC.sel("accelerationStructureCommandEncoder");
    private static final long ADD_COMPLETED_HANDLER = ObjC.sel("addCompletedHandler:");
    private static final long ADD_SCHEDULED_HANDLER = ObjC.sel("addScheduledHandler:");
    private static final long BLIT_COMMAND_ENCODER = ObjC.sel("blitCommandEncoder");
    private static final long BLIT_COMMAND_ENCODER_WITH_DESCRIPTOR = ObjC.sel("blitCommandEncoderWithDescriptor:");
    private static final long COMMAND_QUEUE = ObjC.sel("commandQueue");
    private static final long COMMIT = ObjC.sel("commit");
    private static final long COMPUTE_COMMAND_ENCODER = ObjC.sel("computeCommandEncoder");
    private static final long COMPUTE_COMMAND_ENCODER_WITH_DESCRIPTOR = ObjC.sel("computeCommandEncoderWithDescriptor:");
    private static final long DEVICE = ObjC.sel("device");
    private static final long ENCODE_SIGNAL_EVENT_VALUE = ObjC.sel("encodeSignalEvent:value:");
    private static final long ENCODE_WAIT_FOR_EVENT_VALUE = ObjC.sel("encodeWaitForEvent:value:");
    private static final long ENQUEUE = ObjC.sel("enqueue");
    private static final long ERROR = ObjC.sel("error");
    private static final long LABEL = ObjC.sel("label");
    private static final long LOGS = ObjC.sel("logs");
    private static final long PARALLEL_RENDER_COMMAND_ENCODER_WITH_DESCRIPTOR = ObjC.sel("parallelRenderCommandEncoderWithDescriptor:");
    private static final long POP_DEBUG_GROUP = ObjC.sel("popDebugGroup");
    private static final long PRESENT_DRAWABLE = ObjC.sel("presentDrawable:");
    private static final long PUSH_DEBUG_GROUP = ObjC.sel("pushDebugGroup:");
    private static final long RENDER_COMMAND_ENCODER_WITH_DESCRIPTOR = ObjC.sel("renderCommandEncoderWithDescriptor:");
    private static final long RESOURCE_STATE_COMMAND_ENCODER = ObjC.sel("resourceStateCommandEncoder");
    private static final long RESOURCE_STATE_COMMAND_ENCODER_WITH_DESCRIPTOR = ObjC.sel("resourceStateCommandEncoderWithDescriptor:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long STATUS = ObjC.sel("status");
    private static final long USE_RESIDENCY_SET = ObjC.sel("useResidencySet:");
    private static final long WAIT_UNTIL_COMPLETED = ObjC.sel("waitUntilCompleted");
    private static final long WAIT_UNTIL_SCHEDULED = ObjC.sel("waitUntilScheduled");

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

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public MTLCommandQueue commandQueue() {
        return MTLCommandQueue.of(sendPtr(id, COMMAND_QUEUE));
    }

    public void enqueue() {
        sendVoid(id, ENQUEUE);
    }

    public void commit() {
        sendVoid(id, COMMIT);
    }

    public void waitUntilScheduled() {
        sendVoid(id, WAIT_UNTIL_SCHEDULED);
    }

    public void waitUntilCompleted() {
        sendVoid(id, WAIT_UNTIL_COMPLETED);
    }

    @SneakyThrows
    public void presentDrawable(CAMetalDrawable drawable) {
        P.invokeExact(id, PRESENT_DRAWABLE, drawable.getId());
    }

    @SneakyThrows
    public void addScheduledHandler(Block block) {
        P.invokeExact(id, ADD_SCHEDULED_HANDLER, block.address());
    }

    @SneakyThrows
    public void addCompletedHandler(Block block) {
        P.invokeExact(id, ADD_COMPLETED_HANDLER, block.address());
    }

    public long status() {
        return sendLong(id, STATUS);
    }

    public NSError error() {
        return NSError.of(sendPtr(id, ERROR));
    }

    @SneakyThrows
    public double GPUStartTime() {
        return (double) D.invokeExact(id, GPU_START_TIME);
    }

    @SneakyThrows
    public double GPUEndTime() {
        return (double) D.invokeExact(id, GPU_END_TIME);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    @SneakyThrows
    public void pushDebugGroup(NSString string) {
        P.invokeExact(id, PUSH_DEBUG_GROUP, string.getId());
    }

    public void popDebugGroup() {
        sendVoid(id, POP_DEBUG_GROUP);
    }

    @SneakyThrows
    public MTLRenderCommandEncoder renderCommandEncoderWithDescriptor(MTLRenderPassDescriptor descriptor) {
        return MTLRenderCommandEncoder.of(owned(() -> (long) P_P.invokeExact(id,
                RENDER_COMMAND_ENCODER_WITH_DESCRIPTOR, descriptor.getId())));
    }

    public MTLComputeCommandEncoder computeCommandEncoder() {
        return MTLComputeCommandEncoder.of(owned(() -> sendPtr(id, COMPUTE_COMMAND_ENCODER)));
    }

    @SneakyThrows
    public MTLComputeCommandEncoder computeCommandEncoderWithDescriptor(MTLComputePassDescriptor descriptor) {
        return MTLComputeCommandEncoder.of(owned(() -> (long) P_P.invokeExact(id,
                COMPUTE_COMMAND_ENCODER_WITH_DESCRIPTOR, descriptor.getId())));
    }

    public MTLBlitCommandEncoder blitCommandEncoder() {
        return MTLBlitCommandEncoder.of(owned(() -> sendPtr(id, BLIT_COMMAND_ENCODER)));
    }

    @SneakyThrows
    public MTLBlitCommandEncoder blitCommandEncoderWithDescriptor(MTLBlitPassDescriptor descriptor) {
        return MTLBlitCommandEncoder.of(owned(() -> (long) P_P.invokeExact(id,
                BLIT_COMMAND_ENCODER_WITH_DESCRIPTOR, descriptor.getId())));
    }

    public MTLAccelerationStructureCommandEncoder accelerationStructureCommandEncoder() {
        return MTLAccelerationStructureCommandEncoder.of(owned(() -> sendPtr(id, ACCELERATION_STRUCTURE_COMMAND_ENCODER)));
    }

    @SneakyThrows
    public void encodeSignalEvent(MTLEvent event, long value) {
        PL.invokeExact(id, ENCODE_SIGNAL_EVENT_VALUE, event.getId(), value);
    }

    @SneakyThrows
    public void encodeWaitForEvent(MTLEvent event, long value) {
        PL.invokeExact(id, ENCODE_WAIT_FOR_EVENT_VALUE, event.getId(), value);
    }

    @SneakyThrows
    public void useResidencySet(MTLResidencySet set) {
        P.invokeExact(id, USE_RESIDENCY_SET, set.getId());
    }

    @SneakyThrows
    public MTLParallelRenderCommandEncoder parallelRenderCommandEncoderWithDescriptor(
            MTLRenderPassDescriptor descriptor) {
        return MTLParallelRenderCommandEncoder.of(owned(() -> (long) P_P.invokeExact(id,
                PARALLEL_RENDER_COMMAND_ENCODER_WITH_DESCRIPTOR, descriptor.getId())));
    }

    public MTLResourceStateCommandEncoder resourceStateCommandEncoder() {
        return MTLResourceStateCommandEncoder.of(owned(() -> sendPtr(id, RESOURCE_STATE_COMMAND_ENCODER)));
    }

    @SneakyThrows
    public MTLResourceStateCommandEncoder resourceStateCommandEncoderWithDescriptor(
            MTLResourceStatePassDescriptor descriptor) {
        return MTLResourceStateCommandEncoder.of(owned(() -> (long) P_P.invokeExact(id,
                RESOURCE_STATE_COMMAND_ENCODER_WITH_DESCRIPTOR, descriptor.getId())));
    }

    public NSArray logs() {
        return NSArray.of(sendPtr(id, LOGS));
    }
}
