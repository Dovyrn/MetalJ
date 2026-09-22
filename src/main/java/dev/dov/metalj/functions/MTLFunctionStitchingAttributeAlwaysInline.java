package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;

public class MTLFunctionStitchingAttributeAlwaysInline extends NSObject {
    private static final long MTL_FUNCTION_STITCHING_ATTRIBUTE_ALWAYS_INLINE = ObjC.cls("MTLFunctionStitchingAttributeAlwaysInline");

    private static final long NEW = ObjC.sel("new");

    private MTLFunctionStitchingAttributeAlwaysInline(long id) {
        super(id);
    }

    public static MTLFunctionStitchingAttributeAlwaysInline of(long id) {
        return new MTLFunctionStitchingAttributeAlwaysInline(id);
    }

    public static MTLFunctionStitchingAttributeAlwaysInline new_() {
        return new MTLFunctionStitchingAttributeAlwaysInline(
                sendPtr(MTL_FUNCTION_STITCHING_ATTRIBUTE_ALWAYS_INLINE, NEW));
    }
}
