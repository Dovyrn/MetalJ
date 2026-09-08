package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.shaders.MTLFunction;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLTileRenderPipelineDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLTileRenderPipelineDescriptor(long id) {
        super(id);
    }

    public static MTLTileRenderPipelineDescriptor of(long id) {
        return new MTLTileRenderPipelineDescriptor(id);
    }

    public static MTLTileRenderPipelineDescriptor new_() {
        return new MTLTileRenderPipelineDescriptor(sendPtr(ObjC.cls("MTLTileRenderPipelineDescriptor"), "new"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    public MTLFunction tileFunction() {
        return MTLFunction.of(sendPtr(id, "tileFunction"));
    }

    @SneakyThrows
    public void setTileFunction(MTLFunction function) {
        P.invokeExact(id, ObjC.sel("setTileFunction:"), function.getId());
    }

    public long rasterSampleCount() {
        return sendLong(id, "rasterSampleCount");
    }

    @SneakyThrows
    public void setRasterSampleCount(long count) {
        L.invokeExact(id, ObjC.sel("setRasterSampleCount:"), count);
    }

    public MTLTileRenderPipelineColorAttachmentDescriptorArray colorAttachments() {
        return MTLTileRenderPipelineColorAttachmentDescriptorArray.of(sendPtr(id, "colorAttachments"));
    }

    public boolean threadgroupSizeMatchesTileSize() {
        return sendBool(id, "threadgroupSizeMatchesTileSize");
    }

    @SneakyThrows
    public void setThreadgroupSizeMatchesTileSize(boolean matches) {
        B.invokeExact(id, ObjC.sel("setThreadgroupSizeMatchesTileSize:"), matches);
    }

    public long maxTotalThreadsPerThreadgroup() {
        return sendLong(id, "maxTotalThreadsPerThreadgroup");
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerThreadgroup(long count) {
        L.invokeExact(id, ObjC.sel("setMaxTotalThreadsPerThreadgroup:"), count);
    }

    public boolean supportAddingBinaryFunctions() {
        return sendBool(id, "supportAddingBinaryFunctions");
    }

    @SneakyThrows
    public void setSupportAddingBinaryFunctions(boolean support) {
        B.invokeExact(id, ObjC.sel("setSupportAddingBinaryFunctions:"), support);
    }

    public long maxCallStackDepth() {
        return sendLong(id, "maxCallStackDepth");
    }

    @SneakyThrows
    public void setMaxCallStackDepth(long depth) {
        L.invokeExact(id, ObjC.sel("setMaxCallStackDepth:"), depth);
    }

    public void reset() {
        sendVoid(id, "reset");
    }
}
