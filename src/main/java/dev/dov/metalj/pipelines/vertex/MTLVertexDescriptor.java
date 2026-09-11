package dev.dov.metalj.pipelines.vertex;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLVertexDescriptor extends NSObject {
    private MTLVertexDescriptor(long id) {
        super(id);
    }

    public static MTLVertexDescriptor of(long id) {
        return new MTLVertexDescriptor(id);
    }

    public static MTLVertexDescriptor new_() {
        return new MTLVertexDescriptor(sendPtr(ObjC.cls("MTLVertexDescriptor"), "new"));
    }

    public static MTLVertexDescriptor vertexDescriptor() {
        return new MTLVertexDescriptor(owned(() -> sendPtr(ObjC.cls("MTLVertexDescriptor"), "vertexDescriptor")));
    }

    public MTLVertexBufferLayoutDescriptorArray layouts() {
        return MTLVertexBufferLayoutDescriptorArray.of(sendPtr(id, "layouts"));
    }

    public MTLVertexAttributeDescriptorArray attributes() {
        return MTLVertexAttributeDescriptorArray.of(sendPtr(id, "attributes"));
    }

    public void reset() {
        sendVoid(id, "reset");
    }
}
