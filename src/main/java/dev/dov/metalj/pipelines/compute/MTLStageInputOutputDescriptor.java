package dev.dov.metalj.pipelines.compute;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLStageInputOutputDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLStageInputOutputDescriptor(long id) {
        super(id);
    }

    public static MTLStageInputOutputDescriptor of(long id) {
        return new MTLStageInputOutputDescriptor(id);
    }

    public static MTLStageInputOutputDescriptor stageInputOutputDescriptor() {
        return new MTLStageInputOutputDescriptor(
                owned(() -> sendPtr(ObjC.cls("MTLStageInputOutputDescriptor"), "stageInputOutputDescriptor")));
    }

    public MTLBufferLayoutDescriptorArray layouts() {
        return MTLBufferLayoutDescriptorArray.of(sendPtr(id, "layouts"));
    }

    public MTLAttributeDescriptorArray attributes() {
        return MTLAttributeDescriptorArray.of(sendPtr(id, "attributes"));
    }

    public long indexType() {
        return sendLong(id, "indexType");
    }

    @SneakyThrows
    public void setIndexType(long type) {
        L.invokeExact(id, ObjC.sel("setIndexType:"), type);
    }

    public long indexBufferIndex() {
        return sendLong(id, "indexBufferIndex");
    }

    @SneakyThrows
    public void setIndexBufferIndex(long index) {
        L.invokeExact(id, ObjC.sel("setIndexBufferIndex:"), index);
    }

    public void reset() {
        sendVoid(id, "reset");
    }
}
