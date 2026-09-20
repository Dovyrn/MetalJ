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
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    @SneakyThrows
    public void beginCommandBufferWithAllocator(MTL4CommandAllocator allocator) {
        P.invokeExact(id, ObjC.sel("beginCommandBufferWithAllocator:"), allocator.getId());
    }

    @SneakyThrows
    public void beginCommandBufferWithAllocator(MTL4CommandAllocator allocator,
            MTL4CommandBufferOptions options) {
        PP.invokeExact(id, ObjC.sel("beginCommandBufferWithAllocator:options:"), allocator.getId(), options.getId());
    }

    public void endCommandBuffer() {
        sendVoid(id, "endCommandBuffer");
    }

    @SneakyThrows
    public MTL4RenderCommandEncoder renderCommandEncoderWithDescriptor(MTL4RenderPassDescriptor descriptor) {
        return MTL4RenderCommandEncoder.of((long) P_P.invokeExact(id, ObjC.sel("renderCommandEncoderWithDescriptor:"),
                descriptor.getId()));
    }

    @SneakyThrows
    public MTL4RenderCommandEncoder renderCommandEncoderWithDescriptor(MTL4RenderPassDescriptor descriptor,
            long options) {
        return MTL4RenderCommandEncoder.of((long) P_PL.invokeExact(id,
                ObjC.sel("renderCommandEncoderWithDescriptor:options:"), descriptor.getId(), options));
    }

    public MTL4ComputeCommandEncoder computeCommandEncoder() {
        return MTL4ComputeCommandEncoder.of(sendPtr(id, "computeCommandEncoder"));
    }

    public MTL4MachineLearningCommandEncoder machineLearningCommandEncoder() {
        return MTL4MachineLearningCommandEncoder.of(sendPtr(id, "machineLearningCommandEncoder"));
    }

    @SneakyThrows
    public void useResidencySet(MTLResidencySet set) {
        P.invokeExact(id, ObjC.sel("useResidencySet:"), set.getId());
    }

    @SneakyThrows
    public void useResidencySets(MemorySegment sets, long count) {
        AL.invokeExact(id, ObjC.sel("useResidencySets:count:"), sets, count);
    }

    @SneakyThrows
    public void pushDebugGroup(NSString string) {
        P.invokeExact(id, ObjC.sel("pushDebugGroup:"), string.getId());
    }

    public void popDebugGroup() {
        sendVoid(id, "popDebugGroup");
    }

    @SneakyThrows
    public void writeTimestampIntoHeap(MTL4CounterHeap heap, long index) {
        PL.invokeExact(id, ObjC.sel("writeTimestampIntoHeap:atIndex:"), heap.getId(), index);
    }

    @SneakyThrows
    public void resolveCounterHeap(MTL4CounterHeap heap, MemorySegment range, MemorySegment buffer, MTLFence wait,
            MTLFence update) {
        PRBPP.invokeExact(id, ObjC.sel("resolveCounterHeap:withRange:intoBuffer:waitFence:updateFence:"),
                heap.getId(), range, buffer, wait.getId(), update.getId());
    }
}
