package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.functions.MTLFunctionStitchingGraph;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.ObjC;
import lombok.SneakyThrows;

public class MTL4StitchedFunctionDescriptor extends MTL4FunctionDescriptor {
    private MTL4StitchedFunctionDescriptor(long id) {
        super(id);
    }

    public static MTL4StitchedFunctionDescriptor of(long id) {
        return new MTL4StitchedFunctionDescriptor(id);
    }

    public static MTL4StitchedFunctionDescriptor new_() {
        return new MTL4StitchedFunctionDescriptor(sendPtr(ObjC.cls("MTL4StitchedFunctionDescriptor"), "new"));
    }

    public MTLFunctionStitchingGraph functionGraph() {
        return MTLFunctionStitchingGraph.of(sendPtr(id, "functionGraph"));
    }

    @SneakyThrows
    public void setFunctionGraph(MTLFunctionStitchingGraph graph) {
        P.invokeExact(id, ObjC.sel("setFunctionGraph:"), graph.getId());
    }

    public NSArray functionDescriptors() {
        return NSArray.of(sendPtr(id, "functionDescriptors"));
    }

    @SneakyThrows
    public void setFunctionDescriptors(NSArray descriptors) {
        P.invokeExact(id, ObjC.sel("setFunctionDescriptors:"), descriptors.getId());
    }
}
