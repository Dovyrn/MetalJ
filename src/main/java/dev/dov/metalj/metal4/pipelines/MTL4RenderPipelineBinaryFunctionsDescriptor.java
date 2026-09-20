package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4RenderPipelineBinaryFunctionsDescriptor extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4RenderPipelineBinaryFunctionsDescriptor(long id) {
        super(id);
    }

    public static MTL4RenderPipelineBinaryFunctionsDescriptor of(long id) {
        return new MTL4RenderPipelineBinaryFunctionsDescriptor(id);
    }

    public static MTL4RenderPipelineBinaryFunctionsDescriptor new_() {
        return new MTL4RenderPipelineBinaryFunctionsDescriptor(
                sendPtr(ObjC.cls("MTL4RenderPipelineBinaryFunctionsDescriptor"), "new"));
    }

    public NSArray vertexAdditionalBinaryFunctions() {
        return NSArray.of(sendPtr(id, "vertexAdditionalBinaryFunctions"));
    }

    @SneakyThrows
    public void setVertexAdditionalBinaryFunctions(NSArray functions) {
        P.invokeExact(id, ObjC.sel("setVertexAdditionalBinaryFunctions:"), functions.getId());
    }

    public NSArray fragmentAdditionalBinaryFunctions() {
        return NSArray.of(sendPtr(id, "fragmentAdditionalBinaryFunctions"));
    }

    @SneakyThrows
    public void setFragmentAdditionalBinaryFunctions(NSArray functions) {
        P.invokeExact(id, ObjC.sel("setFragmentAdditionalBinaryFunctions:"), functions.getId());
    }

    public NSArray tileAdditionalBinaryFunctions() {
        return NSArray.of(sendPtr(id, "tileAdditionalBinaryFunctions"));
    }

    @SneakyThrows
    public void setTileAdditionalBinaryFunctions(NSArray functions) {
        P.invokeExact(id, ObjC.sel("setTileAdditionalBinaryFunctions:"), functions.getId());
    }

    public NSArray objectAdditionalBinaryFunctions() {
        return NSArray.of(sendPtr(id, "objectAdditionalBinaryFunctions"));
    }

    @SneakyThrows
    public void setObjectAdditionalBinaryFunctions(NSArray functions) {
        P.invokeExact(id, ObjC.sel("setObjectAdditionalBinaryFunctions:"), functions.getId());
    }

    public NSArray meshAdditionalBinaryFunctions() {
        return NSArray.of(sendPtr(id, "meshAdditionalBinaryFunctions"));
    }

    @SneakyThrows
    public void setMeshAdditionalBinaryFunctions(NSArray functions) {
        P.invokeExact(id, ObjC.sel("setMeshAdditionalBinaryFunctions:"), functions.getId());
    }

    public void reset() {
        sendVoid(id, "reset");
    }
}
