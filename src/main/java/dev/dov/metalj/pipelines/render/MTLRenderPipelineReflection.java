package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;

public class MTLRenderPipelineReflection extends NSObject {
    private MTLRenderPipelineReflection(long id) {
        super(id);
    }

    public static MTLRenderPipelineReflection of(long id) {
        return new MTLRenderPipelineReflection(id);
    }

    public NSArray vertexBindings() {
        return NSArray.of(sendPtr(id, "vertexBindings"));
    }

    public NSArray fragmentBindings() {
        return NSArray.of(sendPtr(id, "fragmentBindings"));
    }

    public NSArray tileBindings() {
        return NSArray.of(sendPtr(id, "tileBindings"));
    }

    public NSArray objectBindings() {
        return NSArray.of(sendPtr(id, "objectBindings"));
    }

    public NSArray meshBindings() {
        return NSArray.of(sendPtr(id, "meshBindings"));
    }
}
