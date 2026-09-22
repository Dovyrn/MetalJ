package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLAllocation;

public class MTL4MachineLearningPipelineState extends MTLAllocation {
    private static final long DEVICE = ObjC.sel("device");
    private static final long INTERMEDIATES_HEAP_SIZE = ObjC.sel("intermediatesHeapSize");
    private static final long LABEL = ObjC.sel("label");
    private static final long REFLECTION = ObjC.sel("reflection");

    private MTL4MachineLearningPipelineState(long id) {
        super(id);
    }

    public static MTL4MachineLearningPipelineState of(long id) {
        return new MTL4MachineLearningPipelineState(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public MTL4MachineLearningPipelineReflection reflection() {
        return MTL4MachineLearningPipelineReflection.of(sendPtr(id, REFLECTION));
    }

    public long intermediatesHeapSize() {
        return sendLong(id, INTERMEDIATES_HEAP_SIZE);
    }
}
