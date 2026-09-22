package dev.dov.metalj.pipelines.vertex;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLVertexDescriptor extends NSObject {
    private static final long MTL_VERTEX_DESCRIPTOR = ObjC.cls("MTLVertexDescriptor");

    private static final long ATTRIBUTES = ObjC.sel("attributes");
    private static final long LAYOUTS = ObjC.sel("layouts");
    private static final long NEW = ObjC.sel("new");
    private static final long RESET = ObjC.sel("reset");
    private static final long VERTEX_DESCRIPTOR = ObjC.sel("vertexDescriptor");

    private MTLVertexDescriptor(long id) {
        super(id);
    }

    public static MTLVertexDescriptor of(long id) {
        return new MTLVertexDescriptor(id);
    }

    public static MTLVertexDescriptor new_() {
        return new MTLVertexDescriptor(sendPtr(MTL_VERTEX_DESCRIPTOR, NEW));
    }

    public static MTLVertexDescriptor vertexDescriptor() {
        return new MTLVertexDescriptor(owned(() -> sendPtr(MTL_VERTEX_DESCRIPTOR, VERTEX_DESCRIPTOR)));
    }

    public MTLVertexBufferLayoutDescriptorArray layouts() {
        return MTLVertexBufferLayoutDescriptorArray.of(sendPtr(id, LAYOUTS));
    }

    public MTLVertexAttributeDescriptorArray attributes() {
        return MTLVertexAttributeDescriptorArray.of(sendPtr(id, ATTRIBUTES));
    }

    public void reset() {
        sendVoid(id, RESET);
    }
}
