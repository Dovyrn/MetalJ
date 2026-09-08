package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;

public class MTLAttribute extends NSObject {
    private MTLAttribute(long id) {
        super(id);
    }

    public static MTLAttribute of(long id) {
        return new MTLAttribute(id);
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
