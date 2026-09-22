package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.functions.MTLFunctionStitchingGraph;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.ObjC;
import lombok.SneakyThrows;

public class MTL4StitchedFunctionDescriptor extends MTL4FunctionDescriptor {
    private static final long MTL_4_STITCHED_FUNCTION_DESCRIPTOR = ObjC.cls("MTL4StitchedFunctionDescriptor");

    private static final long FUNCTION_DESCRIPTORS = ObjC.sel("functionDescriptors");
    private static final long FUNCTION_GRAPH = ObjC.sel("functionGraph");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_FUNCTION_DESCRIPTORS = ObjC.sel("setFunctionDescriptors:");
    private static final long SET_FUNCTION_GRAPH = ObjC.sel("setFunctionGraph:");

    private MTL4StitchedFunctionDescriptor(long id) {
        super(id);
    }

    public static MTL4StitchedFunctionDescriptor of(long id) {
        return new MTL4StitchedFunctionDescriptor(id);
    }

    public static MTL4StitchedFunctionDescriptor new_() {
        return new MTL4StitchedFunctionDescriptor(sendPtr(MTL_4_STITCHED_FUNCTION_DESCRIPTOR, NEW));
    }

    public MTLFunctionStitchingGraph functionGraph() {
        return MTLFunctionStitchingGraph.of(sendPtr(id, FUNCTION_GRAPH));
    }

    @SneakyThrows
    public void setFunctionGraph(MTLFunctionStitchingGraph graph) {
        P.invokeExact(id, SET_FUNCTION_GRAPH, graph.getId());
    }

    public NSArray functionDescriptors() {
        return NSArray.of(sendPtr(id, FUNCTION_DESCRIPTORS));
    }

    @SneakyThrows
    public void setFunctionDescriptors(NSArray descriptors) {
        P.invokeExact(id, SET_FUNCTION_DESCRIPTORS, descriptors.getId());
    }
}
