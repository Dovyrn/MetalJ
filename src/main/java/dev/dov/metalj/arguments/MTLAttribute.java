package dev.dov.metalj.arguments;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;

public class MTLAttribute extends NSObject {
    private static final long ATTRIBUTE_INDEX = ObjC.sel("attributeIndex");
    private static final long ATTRIBUTE_TYPE = ObjC.sel("attributeType");
    private static final long IS_ACTIVE = ObjC.sel("isActive");
    private static final long IS_PATCH_CONTROL_POINT_DATA = ObjC.sel("isPatchControlPointData");
    private static final long IS_PATCH_DATA = ObjC.sel("isPatchData");
    private static final long NAME = ObjC.sel("name");

    private MTLAttribute(long id) {
        super(id);
    }

    public static MTLAttribute of(long id) {
        return new MTLAttribute(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    public long attributeIndex() {
        return sendLong(id, ATTRIBUTE_INDEX);
    }

    public long attributeType() {
        return sendLong(id, ATTRIBUTE_TYPE);
    }

    public boolean isActive() {
        return sendBool(id, IS_ACTIVE);
    }

    public boolean isPatchData() {
        return sendBool(id, IS_PATCH_DATA);
    }

    public boolean isPatchControlPointData() {
        return sendBool(id, IS_PATCH_CONTROL_POINT_DATA);
    }
}
