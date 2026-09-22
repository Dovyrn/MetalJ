package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLLogicalToPhysicalColorAttachmentMap extends NSObject {
    private static final long MTL_LOGICAL_TO_PHYSICAL_COLOR_ATTACHMENT_MAP = ObjC.cls("MTLLogicalToPhysicalColorAttachmentMap");

    private static final long GET_PHYSICAL_INDEX_FOR_LOGICAL_INDEX = ObjC.sel("getPhysicalIndexForLogicalIndex:");
    private static final long NEW = ObjC.sel("new");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_PHYSICAL_INDEX_FOR_LOGICAL_INDEX = ObjC.sel("setPhysicalIndex:forLogicalIndex:");

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
                sendPtr(MTL_LOGICAL_TO_PHYSICAL_COLOR_ATTACHMENT_MAP, NEW));
    }

    @SneakyThrows
    public void setPhysicalIndex(long physicalIndex, long logicalIndex) {
        LL.invokeExact(id, SET_PHYSICAL_INDEX_FOR_LOGICAL_INDEX, physicalIndex, logicalIndex);
    }

    @SneakyThrows
    public long physicalIndexForLogicalIndex(long logicalIndex) {
        return (long) L_L.invokeExact(id, GET_PHYSICAL_INDEX_FOR_LOGICAL_INDEX, logicalIndex);
    }

    public void reset() {
        sendVoid(id, RESET);
    }
}
