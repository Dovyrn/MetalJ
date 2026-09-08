package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLLogicalToPhysicalColorAttachmentMap extends NSObject {
    private static final MethodHandle LL = handle(null, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle L_L = handle(ObjC.LONG, ObjC.LONG);

    private MTLLogicalToPhysicalColorAttachmentMap(long id) {
        super(id);
    }

    public static MTLLogicalToPhysicalColorAttachmentMap of(long id) {
        return new MTLLogicalToPhysicalColorAttachmentMap(id);
    }

    public static MTLLogicalToPhysicalColorAttachmentMap new_() {
        return new MTLLogicalToPhysicalColorAttachmentMap(
                sendPtr(ObjC.cls("MTLLogicalToPhysicalColorAttachmentMap"), "new"));
    }

    @SneakyThrows
    public void setPhysicalIndex(long physicalIndex, long logicalIndex) {
        LL.invokeExact(id, ObjC.sel("setPhysicalIndex:forLogicalIndex:"), physicalIndex, logicalIndex);
    }

    @SneakyThrows
    public long physicalIndexForLogicalIndex(long logicalIndex) {
        return (long) L_L.invokeExact(id, ObjC.sel("getPhysicalIndexForLogicalIndex:"), logicalIndex);
    }

    public void reset() {
        sendVoid(id, "reset");
    }
}
