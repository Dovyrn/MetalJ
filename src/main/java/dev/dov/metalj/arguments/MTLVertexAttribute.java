package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLVertexAttribute extends NSObject {
    private MTLVertexAttribute(long id) {
        super(id);
    }

    public static MTLVertexAttribute of(long id) {
        return new MTLVertexAttribute(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    public long attributeIndex() {
        return sendLong(id, "attributeIndex");
    }

    public long attributeType() {
        return sendLong(id, "attributeType");
    }

    public boolean isActive() {
        return sendBool(id, "isActive");
    }

    public boolean isPatchData() {
        return sendBool(id, "isPatchData");
    }

    public boolean isPatchControlPointData() {
        return sendBool(id, "isPatchControlPointData");
    }
}
