package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTL4RenderPipelineDynamicLinkingDescriptor extends NSObject {
    private static final long MTL_4_RENDER_PIPELINE_DYNAMIC_LINKING_DESCRIPTOR = ObjC.cls("MTL4RenderPipelineDynamicLinkingDescriptor");

    private static final long FRAGMENT_LINKING_DESCRIPTOR = ObjC.sel("fragmentLinkingDescriptor");
    private static final long MESH_LINKING_DESCRIPTOR = ObjC.sel("meshLinkingDescriptor");
    private static final long NEW = ObjC.sel("new");
    private static final long OBJECT_LINKING_DESCRIPTOR = ObjC.sel("objectLinkingDescriptor");
    private static final long TILE_LINKING_DESCRIPTOR = ObjC.sel("tileLinkingDescriptor");
    private static final long VERTEX_LINKING_DESCRIPTOR = ObjC.sel("vertexLinkingDescriptor");

    private MTL4RenderPipelineDynamicLinkingDescriptor(long id) {
        super(id);
    }

    public static MTL4RenderPipelineDynamicLinkingDescriptor of(long id) {
        return new MTL4RenderPipelineDynamicLinkingDescriptor(id);
    }

    public static MTL4RenderPipelineDynamicLinkingDescriptor new_() {
        return new MTL4RenderPipelineDynamicLinkingDescriptor(
                sendPtr(MTL_4_RENDER_PIPELINE_DYNAMIC_LINKING_DESCRIPTOR, NEW));
    }

    public MTL4PipelineStageDynamicLinkingDescriptor vertexLinkingDescriptor() {
        return MTL4PipelineStageDynamicLinkingDescriptor.of(sendPtr(id, VERTEX_LINKING_DESCRIPTOR));
    }

    public MTL4PipelineStageDynamicLinkingDescriptor fragmentLinkingDescriptor() {
        return MTL4PipelineStageDynamicLinkingDescriptor.of(sendPtr(id, FRAGMENT_LINKING_DESCRIPTOR));
    }

    public MTL4PipelineStageDynamicLinkingDescriptor tileLinkingDescriptor() {
        return MTL4PipelineStageDynamicLinkingDescriptor.of(sendPtr(id, TILE_LINKING_DESCRIPTOR));
    }

    public MTL4PipelineStageDynamicLinkingDescriptor objectLinkingDescriptor() {
        return MTL4PipelineStageDynamicLinkingDescriptor.of(sendPtr(id, OBJECT_LINKING_DESCRIPTOR));
    }

    public MTL4PipelineStageDynamicLinkingDescriptor meshLinkingDescriptor() {
        return MTL4PipelineStageDynamicLinkingDescriptor.of(sendPtr(id, MESH_LINKING_DESCRIPTOR));
    }
}
