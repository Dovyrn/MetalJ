package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLFunctionStitchingGraph extends NSObject {
    private static final long ATTRIBUTES = ObjC.sel("attributes");
    private static final long FUNCTION_NAME = ObjC.sel("functionName");
    private static final long INIT_WITH_FUNCTION_NAME_NODES_OUTPUT_NODE_ATTRIBUTES = ObjC.sel("initWithFunctionName:nodes:outputNode:attributes:");
    private static final long NODES = ObjC.sel("nodes");
    private static final long OUTPUT_NODE = ObjC.sel("outputNode");
    private static final long SET_ATTRIBUTES = ObjC.sel("setAttributes:");
    private static final long SET_FUNCTION_NAME = ObjC.sel("setFunctionName:");
    private static final long SET_NODES = ObjC.sel("setNodes:");
    private static final long SET_OUTPUT_NODE = ObjC.sel("setOutputNode:");

    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle P_PPPP = handle(ObjC.PTR, ObjC.PTR, ObjC.PTR, ObjC.PTR, ObjC.PTR);

    private MTLFunctionStitchingGraph(long id) {
        super(id);
    }

    public static MTLFunctionStitchingGraph of(long id) {
        return new MTLFunctionStitchingGraph(id);
    }

    @SneakyThrows
    public static MTLFunctionStitchingGraph initWithFunctionName(NSString functionName, NSArray nodes,
            MTLFunctionStitchingFunctionNode outputNode, NSArray attributes) {
        long id = (long) P_PPPP.invokeExact(alloc("MTLFunctionStitchingGraph"),
                INIT_WITH_FUNCTION_NAME_NODES_OUTPUT_NODE_ATTRIBUTES, functionName.getId(), nodes.getId(),
                outputNode.getId(), attributes.getId());
        return new MTLFunctionStitchingGraph(id);
    }

    public NSString functionName() {
        return NSString.of(sendPtr(id, FUNCTION_NAME));
    }

    @SneakyThrows
    public void setFunctionName(NSString name) {
        P.invokeExact(id, SET_FUNCTION_NAME, name.getId());
    }

    public NSArray nodes() {
        return NSArray.of(sendPtr(id, NODES));
    }

    @SneakyThrows
    public void setNodes(NSArray nodes) {
        P.invokeExact(id, SET_NODES, nodes.getId());
    }

    public MTLFunctionStitchingFunctionNode outputNode() {
        return MTLFunctionStitchingFunctionNode.of(sendPtr(id, OUTPUT_NODE));
    }

    @SneakyThrows
    public void setOutputNode(MTLFunctionStitchingFunctionNode node) {
        P.invokeExact(id, SET_OUTPUT_NODE, node.getId());
    }

    public NSArray attributes() {
        return NSArray.of(sendPtr(id, ATTRIBUTES));
    }

    @SneakyThrows
    public void setAttributes(NSArray attributes) {
        P.invokeExact(id, SET_ATTRIBUTES, attributes.getId());
    }
}
