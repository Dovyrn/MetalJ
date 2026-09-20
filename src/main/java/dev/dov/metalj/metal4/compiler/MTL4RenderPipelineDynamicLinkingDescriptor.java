package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTL4RenderPipelineDynamicLinkingDescriptor extends NSObject {
    private MTL4RenderPipelineDynamicLinkingDescriptor(long id) {
        super(id);
    }

    public static MTL4RenderPipelineDynamicLinkingDescriptor of(long id) {
        return new MTL4RenderPipelineDynamicLinkingDescriptor(id);
    }

    public static MTL4RenderPipelineDynamicLinkingDescriptor new_() {
        return new MTL4RenderPipelineDynamicLinkingDescriptor(
                sendPtr(ObjC.cls("MTL4RenderPipelineDynamicLinkingDescriptor"), "new"));
    }

    public MTL4PipelineStageDynamicLinkingDescriptor vertexLinkingDescriptor() {
        return MTL4PipelineStageDynamicLinkingDescriptor.of(sendPtr(id, "vertexLinkingDescriptor"));
    }

    public MTL4PipelineStageDynamicLinkingDescriptor fragmentLinkingDescriptor() {
        return MTL4PipelineStageDynamicLinkingDescriptor.of(sendPtr(id, "fragmentLinkingDescriptor"));
    }

    public MTL4PipelineStageDynamicLinkingDescriptor tileLinkingDescriptor() {
        return MTL4PipelineStageDynamicLinkingDescriptor.of(sendPtr(id, "tileLinkingDescriptor"));
    }

    public MTL4PipelineStageDynamicLinkingDescriptor objectLinkingDescriptor() {
        return MTL4PipelineStageDynamicLinkingDescriptor.of(sendPtr(id, "objectLinkingDescriptor"));
    }

    public MTL4PipelineStageDynamicLinkingDescriptor meshLinkingDescriptor() {
        return MTL4PipelineStageDynamicLinkingDescriptor.of(sendPtr(id, "meshLinkingDescriptor"));
    }
}
