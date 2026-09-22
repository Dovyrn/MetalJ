package dev.dov.metalj.metal4;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.metal4.counters.MTL4CounterHeap;
import dev.dov.metalj.metal4.encoders.MTL4ComputeCommandEncoder;
import dev.dov.metalj.metal4.encoders.MTL4MachineLearningCommandEncoder;
import dev.dov.metalj.metal4.encoders.MTL4RenderCommandEncoder;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.residency.MTLResidencySet;
import dev.dov.metalj.sync.MTLFence;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CommandBuffer extends NSObject {
    private static final long BEGIN_COMMAND_BUFFER_WITH_ALLOCATOR = ObjC.sel("beginCommandBufferWithAllocator:");
    private static final long BEGIN_COMMAND_BUFFER_WITH_ALLOCATOR_OPTIONS = ObjC.sel("beginCommandBufferWithAllocator:options:");
    private static final long COMPUTE_COMMAND_ENCODER = ObjC.sel("computeCommandEncoder");
    private static final long DEVICE = ObjC.sel("device");
    private static final long END_COMMAND_BUFFER = ObjC.sel("endCommandBuffer");
    private static final long LABEL = ObjC.sel("label");
    private static final long MACHINE_LEARNING_COMMAND_ENCODER = ObjC.sel("machineLearningCommandEncoder");
    private static final long POP_DEBUG_GROUP = ObjC.sel("popDebugGroup");
    private static final long PUSH_DEBUG_GROUP = ObjC.sel("pushDebugGroup:");
    private static final long RENDER_COMMAND_ENCODER_WITH_DESCRIPTOR = ObjC.sel("renderCommandEncoderWithDescriptor:");
    private static final long RENDER_COMMAND_ENCODER_WITH_DESCRIPTOR_OPTIONS = ObjC.sel("renderCommandEncoderWithDescriptor:options:");
    private static final long RESOLVE_COUNTER_HEAP_WITH_RANGE_INTO_BUFFER_WAIT_FENCE_UPDATE_FENCE = ObjC.sel("resolveCounterHeap:withRange:intoBuffer:waitFence:updateFence:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long USE_RESIDENCY_SET = ObjC.sel("useResidencySet:");
    private static final long USE_RESIDENCY_SETS_COUNT = ObjC.sel("useResidencySets:count:");
    private static final long WRITE_TIMESTAMP_INTO_HEAP_AT_INDEX = ObjC.sel("writeTimestampIntoHeap:atIndex:");

    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle PP = handle(null, ObjC.PTR, ObjC.PTR);
    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);
    private static final MethodHandle P_PL = handle(ObjC.PTR, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle AL = handle(null, ValueLayout.ADDRESS, ObjC.LONG);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PRBPP = handle(null, ObjC.PTR, NSRange.LAYOUT, MTL4BufferRange.LAYOUT,
            ObjC.PTR, ObjC.PTR);

    private MTL4CommandBuffer(long id) {
        super(id);
    }

    public static MTL4CommandBuffer of(long id) {
        return new MTL4CommandBuffer(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    @SneakyThrows
    public void beginCommandBufferWithAllocator(MTL4CommandAllocator allocator) {
        P.invokeExact(id, BEGIN_COMMAND_BUFFER_WITH_ALLOCATOR, allocator.getId());
    }

    @SneakyThrows
    public void beginCommandBufferWithAllocator(MTL4CommandAllocator allocator,
            MTL4CommandBufferOptions options) {
        PP.invokeExact(id, BEGIN_COMMAND_BUFFER_WITH_ALLOCATOR_OPTIONS, allocator.getId(), options.getId());
    }

    public void endCommandBuffer() {
        sendVoid(id, END_COMMAND_BUFFER);
    }

    @SneakyThrows
    public MTL4RenderCommandEncoder renderCommandEncoderWithDescriptor(MTL4RenderPassDescriptor descriptor) {
        return MTL4RenderCommandEncoder.of((long) P_P.invokeExact(id, RENDER_COMMAND_ENCODER_WITH_DESCRIPTOR,
                descriptor.getId()));
    }

    @SneakyThrows
    public MTL4RenderCommandEncoder renderCommandEncoderWithDescriptor(MTL4RenderPassDescriptor descriptor,
            long options) {
        return MTL4RenderCommandEncoder.of((long) P_PL.invokeExact(id,
                RENDER_COMMAND_ENCODER_WITH_DESCRIPTOR_OPTIONS, descriptor.getId(), options));
    }

    public MTL4ComputeCommandEncoder computeCommandEncoder() {
        return MTL4ComputeCommandEncoder.of(sendPtr(id, COMPUTE_COMMAND_ENCODER));
    }

    public MTL4MachineLearningCommandEncoder machineLearningCommandEncoder() {
        return MTL4MachineLearningCommandEncoder.of(sendPtr(id, MACHINE_LEARNING_COMMAND_ENCODER));
    }

    @SneakyThrows
    public void useResidencySet(MTLResidencySet set) {
        P.invokeExact(id, USE_RESIDENCY_SET, set.getId());
    }

    @SneakyThrows
    public void useResidencySets(MemorySegment sets, long count) {
        AL.invokeExact(id, USE_RESIDENCY_SETS_COUNT, sets, count);
    }

    @SneakyThrows
    public void pushDebugGroup(NSString string) {
        P.invokeExact(id, PUSH_DEBUG_GROUP, string.getId());
    }

    public void popDebugGroup() {
        sendVoid(id, POP_DEBUG_GROUP);
    }

    @SneakyThrows
    public void writeTimestampIntoHeap(MTL4CounterHeap heap, long index) {
        PL.invokeExact(id, WRITE_TIMESTAMP_INTO_HEAP_AT_INDEX, heap.getId(), index);
    }

    @SneakyThrows
    public void resolveCounterHeap(MTL4CounterHeap heap, MemorySegment range, MemorySegment buffer, MTLFence wait,
            MTLFence update) {
        PRBPP.invokeExact(id, RESOLVE_COUNTER_HEAP_WITH_RANGE_INTO_BUFFER_WAIT_FENCE_UPDATE_FENCE,
                heap.getId(), range, buffer, wait.getId(), update.getId());
    }
}
