package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPipelineFunctionsDescriptor extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLRenderPipelineFunctionsDescriptor(long id) {
        super(id);
    }

    public static MTLRenderPipelineFunctionsDescriptor of(long id) {
        return new MTLRenderPipelineFunctionsDescriptor(id);
    }

    public static MTLRenderPipelineFunctionsDescriptor new_() {
        return new MTLRenderPipelineFunctionsDescriptor(
                sendPtr(ObjC.cls("MTLRenderPipelineFunctionsDescriptor"), "new"));
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
}
