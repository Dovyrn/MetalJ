package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLFunctionStitchingGraph extends NSObject {
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
                ObjC.sel("initWithFunctionName:nodes:outputNode:attributes:"), functionName.getId(), nodes.getId(),
                outputNode.getId(), attributes.getId());
        return new MTLFunctionStitchingGraph(id);
    }

    public NSString functionName() {
        return NSString.of(sendPtr(id, "functionName"));
    }

    @SneakyThrows
    public void setFunctionName(NSString name) {
        P.invokeExact(id, ObjC.sel("setFunctionName:"), name.getId());
    }

    public NSArray nodes() {
        return NSArray.of(sendPtr(id, "nodes"));
    }

    @SneakyThrows
    public void setNodes(NSArray nodes) {
        P.invokeExact(id, ObjC.sel("setNodes:"), nodes.getId());
    }

    public MTLFunctionStitchingFunctionNode outputNode() {
        return MTLFunctionStitchingFunctionNode.of(sendPtr(id, "outputNode"));
    }

    @SneakyThrows
    public void setOutputNode(MTLFunctionStitchingFunctionNode node) {
        P.invokeExact(id, ObjC.sel("setOutputNode:"), node.getId());
    }

    public NSArray attributes() {
        return NSArray.of(sendPtr(id, "attributes"));
    }

    @SneakyThrows
    public void setAttributes(NSArray attributes) {
        P.invokeExact(id, ObjC.sel("setAttributes:"), attributes.getId());
    }
}
