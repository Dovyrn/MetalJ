package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.resources.MTLAllocation;

public class MTL4MachineLearningPipelineState extends MTLAllocation {
    private MTL4MachineLearningPipelineState(long id) {
        super(id);
    }

    public static MTL4MachineLearningPipelineState of(long id) {
        return new MTL4MachineLearningPipelineState(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public MTL4MachineLearningPipelineReflection reflection() {
        return MTL4MachineLearningPipelineReflection.of(sendPtr(id, "reflection"));
    }

    public long intermediatesHeapSize() {
        return sendLong(id, "intermediatesHeapSize");
    }
}
