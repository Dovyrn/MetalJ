package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.resources.MTLSize;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPipelineState extends NSObject {
    private static final MethodHandle L_S = handle(ObjC.LONG, MTLSize.LAYOUT);

    private MTLRenderPipelineState(long id) {
        super(id);
    }

    public static MTLRenderPipelineState of(long id) {
        return new MTLRenderPipelineState(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public long maxTotalThreadsPerThreadgroup() {
        return sendLong(id, "maxTotalThreadsPerThreadgroup");
    }

    public boolean threadgroupSizeMatchesTileSize() {
        return sendBool(id, "threadgroupSizeMatchesTileSize");
    }

    public long imageblockSampleLength() {
        return sendLong(id, "imageblockSampleLength");
    }

    @SneakyThrows
    public long imageblockMemoryLengthForDimensions(MemorySegment size) {
        return (long) L_S.invokeExact(id, ObjC.sel("imageblockMemoryLengthForDimensions:"), size);
    }

    public boolean supportIndirectCommandBuffers() {
        return sendBool(id, "supportIndirectCommandBuffers");
    }

    public long gpuResourceID() {
        return sendLong(id, "gpuResourceID");
    }

    public long maxTotalThreadsPerObjectThreadgroup() {
        return sendLong(id, "maxTotalThreadsPerObjectThreadgroup");
    }

    public long maxTotalThreadsPerMeshThreadgroup() {
        return sendLong(id, "maxTotalThreadsPerMeshThreadgroup");
    }

    public long objectThreadExecutionWidth() {
        return sendLong(id, "objectThreadExecutionWidth");
    }

    public long meshThreadExecutionWidth() {
        return sendLong(id, "meshThreadExecutionWidth");
    }
}
