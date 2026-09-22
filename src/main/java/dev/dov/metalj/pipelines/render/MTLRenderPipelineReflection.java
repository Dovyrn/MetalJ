package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLRenderPipelineReflection extends NSObject {
    private static final long FRAGMENT_BINDINGS = ObjC.sel("fragmentBindings");
    private static final long MESH_BINDINGS = ObjC.sel("meshBindings");
    private static final long OBJECT_BINDINGS = ObjC.sel("objectBindings");
    private static final long TILE_BINDINGS = ObjC.sel("tileBindings");
    private static final long VERTEX_BINDINGS = ObjC.sel("vertexBindings");

    private MTLRenderPipelineReflection(long id) {
        super(id);
    }

    public static MTLRenderPipelineReflection of(long id) {
        return new MTLRenderPipelineReflection(id);
    }

    public NSArray vertexBindings() {
        return NSArray.of(sendPtr(id, VERTEX_BINDINGS));
    }

    public NSArray fragmentBindings() {
        return NSArray.of(sendPtr(id, FRAGMENT_BINDINGS));
    }

    public NSArray tileBindings() {
        return NSArray.of(sendPtr(id, TILE_BINDINGS));
    }

    public NSArray objectBindings() {
        return NSArray.of(sendPtr(id, OBJECT_BINDINGS));
    }

    public NSArray meshBindings() {
        return NSArray.of(sendPtr(id, MESH_BINDINGS));
    }
}
