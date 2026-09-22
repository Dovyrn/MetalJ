package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4RenderPipelineBinaryFunctionsDescriptor extends NSObject {
    private static final long MTL_4_RENDER_PIPELINE_BINARY_FUNCTIONS_DESCRIPTOR = ObjC.cls("MTL4RenderPipelineBinaryFunctionsDescriptor");

    private static final long FRAGMENT_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("fragmentAdditionalBinaryFunctions");
    private static final long MESH_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("meshAdditionalBinaryFunctions");
    private static final long NEW = ObjC.sel("new");
    private static final long OBJECT_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("objectAdditionalBinaryFunctions");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_FRAGMENT_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("setFragmentAdditionalBinaryFunctions:");
    private static final long SET_MESH_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("setMeshAdditionalBinaryFunctions:");
    private static final long SET_OBJECT_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("setObjectAdditionalBinaryFunctions:");
    private static final long SET_TILE_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("setTileAdditionalBinaryFunctions:");
    private static final long SET_VERTEX_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("setVertexAdditionalBinaryFunctions:");
    private static final long TILE_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("tileAdditionalBinaryFunctions");
    private static final long VERTEX_ADDITIONAL_BINARY_FUNCTIONS = ObjC.sel("vertexAdditionalBinaryFunctions");

    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4RenderPipelineBinaryFunctionsDescriptor(long id) {
        super(id);
    }

    public static MTL4RenderPipelineBinaryFunctionsDescriptor of(long id) {
        return new MTL4RenderPipelineBinaryFunctionsDescriptor(id);
    }

    public static MTL4RenderPipelineBinaryFunctionsDescriptor new_() {
        return new MTL4RenderPipelineBinaryFunctionsDescriptor(
                sendPtr(MTL_4_RENDER_PIPELINE_BINARY_FUNCTIONS_DESCRIPTOR, NEW));
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

    public NSArray objectAdditionalBinaryFunctions() {
        return NSArray.of(sendPtr(id, OBJECT_ADDITIONAL_BINARY_FUNCTIONS));
    }

    @SneakyThrows
    public void setObjectAdditionalBinaryFunctions(NSArray functions) {
        P.invokeExact(id, SET_OBJECT_ADDITIONAL_BINARY_FUNCTIONS, functions.getId());
    }

    public NSArray meshAdditionalBinaryFunctions() {
        return NSArray.of(sendPtr(id, MESH_ADDITIONAL_BINARY_FUNCTIONS));
    }

    @SneakyThrows
    public void setMeshAdditionalBinaryFunctions(NSArray functions) {
        P.invokeExact(id, SET_MESH_ADDITIONAL_BINARY_FUNCTIONS, functions.getId());
    }

    public void reset() {
        sendVoid(id, RESET);
    }
}
