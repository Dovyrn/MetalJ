package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPipelineFunctionsDescriptor extends NSObject {
    private static final long MTL_RENDER_PIPELINE_FUNCTIONS_DESCRIPTOR = ObjC.cls("MTLRenderPipelineFunctionsDescriptor");

    private static final long FRAGMENT_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("fragmentAdditionalBinaryFunctions");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_FRAGMENT_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("setFragmentAdditionalBinaryFunctions:");
    private static final long SET_TILE_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("setTileAdditionalBinaryFunctions:");
    private static final long SET_VERTEX_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("setVertexAdditionalBinaryFunctions:");
    private static final long TILE_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("tileAdditionalBinaryFunctions");
    private static final long VERTEX_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("vertexAdditionalBinaryFunctions");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLRenderPipelineFunctionsDescriptor(long id) {
        super(id);
    }

    public static MTLRenderPipelineFunctionsDescriptor of(long id) {
        return new MTLRenderPipelineFunctionsDescriptor(id);
    }

    public static MTLRenderPipelineFunctionsDescriptor new_() {
        return new MTLRenderPipelineFunctionsDescriptor(
                sendPtr(MTL_RENDER_PIPELINE_FUNCTIONS_DESCRIPTOR, NEW));
    }

    public NSArray vertexAdditionalBinaryFunctions() {
        return NSArray.of(sendPtr(id, VERTEX_ADDITIONAL_BINARY_FUNCTIONS));
    }

    @SneakyThrows
    public void setVertexAdditionalBinaryFunctions(NSArray functions) {
        P.invokeExact(id, SET_VERTEX_ADDITIONAL_BINARY_FUNCTIONS, functions.getId());
    }

    public NSArray fragmentAdditionalBinaryFunctions() {
        return NSArray.of(sendPtr(id, FRAGMENT_ADDITIONAL_BINARY_FUNCTIONS));
    }

    @SneakyThrows
    public void setFragmentAdditionalBinaryFunctions(NSArray functions) {
        P.invokeExact(id, SET_FRAGMENT_ADDITIONAL_BINARY_FUNCTIONS, functions.getId());
    }

    public NSArray tileAdditionalBinaryFunctions() {
        return NSArray.of(sendPtr(id, TILE_ADDITIONAL_BINARY_FUNCTIONS));
    }

    @SneakyThrows
    public void setTileAdditionalBinaryFunctions(NSArray functions) {
        P.invokeExact(id, SET_TILE_ADDITIONAL_BINARY_FUNCTIONS, functions.getId());
    }
}
