package dev.dov.metalj.device;

import dev.dov.metalj.commands.MTLCommandBuffer;
import dev.dov.metalj.commands.MTLCommandBufferDescriptor;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.residency.MTLResidencySet;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCommandQueue extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);
    private static final MethodHandle AL = handle(null, ValueLayout.ADDRESS, ObjC.LONG);

    private MTLCommandQueue(long id) {
        super(id);
    }

    public static MTLCommandQueue of(long id) {
        return new MTLCommandQueue(id);
    }

    public MTLCommandBuffer commandBuffer() {
        return MTLCommandBuffer.of(owned(() -> sendPtr(id, "commandBuffer")));
    }

    @SneakyThrows
    public void addResidencySet(MTLResidencySet set) {
        P.invokeExact(id, ObjC.sel("addResidencySet:"), set.getId());
    }

    @SneakyThrows
    public void addResidencySets(MemorySegment sets, long count) {
        AL.invokeExact(id, ObjC.sel("addResidencySets:count:"), sets, count);
    }

    @SneakyThrows
    public void removeResidencySet(MTLResidencySet set) {
        P.invokeExact(id, ObjC.sel("removeResidencySet:"), set.getId());
    }

    @SneakyThrows
    public void removeResidencySets(MemorySegment sets, long count) {
        AL.invokeExact(id, ObjC.sel("removeResidencySets:count:"), sets, count);
    }

    @SneakyThrows
    public MTLCommandBuffer commandBufferWithDescriptor(MTLCommandBufferDescriptor descriptor) {
        return MTLCommandBuffer.of(owned(() -> (long) P_P.invokeExact(id, ObjC.sel("commandBufferWithDescriptor:"),
                descriptor.getId())));
    }

    public MTLCommandBuffer commandBufferWithUnretainedReferences() {
        return MTLCommandBuffer.of(owned(() -> sendPtr(id, "commandBufferWithUnretainedReferences")));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }
}
