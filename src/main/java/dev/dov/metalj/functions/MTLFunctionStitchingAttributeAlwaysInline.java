package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLFunctionStitchingAttributeAlwaysInline extends NSObject {
    private MTLFunctionStitchingAttributeAlwaysInline(long id) {
        super(id);
    }

    public static MTLFunctionStitchingAttributeAlwaysInline of(long id) {
        return new MTLFunctionStitchingAttributeAlwaysInline(id);
    }

    public static MTLFunctionStitchingAttributeAlwaysInline new_() {
        return new MTLFunctionStitchingAttributeAlwaysInline(
                sendPtr(ObjC.cls("MTLFunctionStitchingAttributeAlwaysInline"), "new"));
    }
}
