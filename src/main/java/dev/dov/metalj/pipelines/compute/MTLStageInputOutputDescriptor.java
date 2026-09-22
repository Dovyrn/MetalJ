package dev.dov.metalj.pipelines.compute;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLStageInputOutputDescriptor extends NSObject {
    private static final long MTL_STAGE_INPUT_OUTPUT_DESCRIPTOR = ObjC.cls("MTLStageInputOutputDescriptor");

    private static final long ATTRIBUTES = ObjC.sel("attributes");
    private static final long INDEX_BUFFER_INDEX = ObjC.sel("indexBufferIndex");
    private static final long INDEX_TYPE = ObjC.sel("indexType");
    private static final long LAYOUTS = ObjC.sel("layouts");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_INDEX_BUFFER_INDEX = ObjC.sel("setIndexBufferIndex:");
    private static final long SET_INDEX_TYPE = ObjC.sel("setIndexType:");
    private static final long STAGE_INPUT_OUTPUT_DESCRIPTOR = ObjC.sel("stageInputOutputDescriptor");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLStageInputOutputDescriptor(long id) {
        super(id);
    }

    public static MTLStageInputOutputDescriptor of(long id) {
        return new MTLStageInputOutputDescriptor(id);
    }

    public static MTLStageInputOutputDescriptor stageInputOutputDescriptor() {
        return new MTLStageInputOutputDescriptor(
                owned(() -> sendPtr(MTL_STAGE_INPUT_OUTPUT_DESCRIPTOR, STAGE_INPUT_OUTPUT_DESCRIPTOR)));
    }

    public MTLBufferLayoutDescriptorArray layouts() {
        return MTLBufferLayoutDescriptorArray.of(sendPtr(id, LAYOUTS));
    }

    public MTLAttributeDescriptorArray attributes() {
        return MTLAttributeDescriptorArray.of(sendPtr(id, ATTRIBUTES));
    }

    public long indexType() {
        return sendLong(id, INDEX_TYPE);
    }

    @SneakyThrows
    public void setIndexType(long type) {
        L.invokeExact(id, SET_INDEX_TYPE, type);
    }

    public long indexBufferIndex() {
        return sendLong(id, INDEX_BUFFER_INDEX);
    }

    @SneakyThrows
    public void setIndexBufferIndex(long index) {
        L.invokeExact(id, SET_INDEX_BUFFER_INDEX, index);
    }

    public void reset() {
        sendVoid(id, RESET);
    }
}
