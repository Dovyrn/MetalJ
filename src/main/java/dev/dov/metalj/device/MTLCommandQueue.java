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
    private static final long ADD_RESIDENCY_SET = ObjC.sel("addResidencySet:");
    private static final long ADD_RESIDENCY_SETS_COUNT = ObjC.sel("addResidencySets:count:");
    private static final long COMMAND_BUFFER = ObjC.sel("commandBuffer");
    private static final long COMMAND_BUFFER_WITH_DESCRIPTOR = ObjC.sel("commandBufferWithDescriptor:");
    private static final long COMMAND_BUFFER_WITH_UNRETAINED_REFERENCES = ObjC.sel("commandBufferWithUnretainedReferences");
    private static final long LABEL = ObjC.sel("label");
    private static final long REMOVE_RESIDENCY_SET = ObjC.sel("removeResidencySet:");
    private static final long REMOVE_RESIDENCY_SETS_COUNT = ObjC.sel("removeResidencySets:count:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");

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
        return MTLCommandBuffer.of(owned(() -> sendPtr(id, COMMAND_BUFFER)));
    }

    @SneakyThrows
    public void addResidencySet(MTLResidencySet set) {
        P.invokeExact(id, ADD_RESIDENCY_SET, set.getId());
    }

    @SneakyThrows
    public void addResidencySets(MemorySegment sets, long count) {
        AL.invokeExact(id, ADD_RESIDENCY_SETS_COUNT, sets, count);
    }

    @SneakyThrows
    public void removeResidencySet(MTLResidencySet set) {
        P.invokeExact(id, REMOVE_RESIDENCY_SET, set.getId());
    }

    @SneakyThrows
    public void removeResidencySets(MemorySegment sets, long count) {
        AL.invokeExact(id, REMOVE_RESIDENCY_SETS_COUNT, sets, count);
    }

    @SneakyThrows
    public MTLCommandBuffer commandBufferWithDescriptor(MTLCommandBufferDescriptor descriptor) {
        return MTLCommandBuffer.of(owned(() -> (long) P_P.invokeExact(id, COMMAND_BUFFER_WITH_DESCRIPTOR,
                descriptor.getId())));
    }

    public MTLCommandBuffer commandBufferWithUnretainedReferences() {
        return MTLCommandBuffer.of(owned(() -> sendPtr(id, COMMAND_BUFFER_WITH_UNRETAINED_REFERENCES)));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }
}
