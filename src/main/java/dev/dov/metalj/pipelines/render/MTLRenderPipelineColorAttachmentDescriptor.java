package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPipelineColorAttachmentDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);

    private MTLRenderPipelineColorAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLRenderPipelineColorAttachmentDescriptor of(long id) {
        return new MTLRenderPipelineColorAttachmentDescriptor(id);
    }

    public static MTLRenderPipelineColorAttachmentDescriptor new_() {
        return new MTLRenderPipelineColorAttachmentDescriptor(sendPtr(ObjC.cls("MTLRenderPipelineColorAttachmentDescriptor"), "new"));
    }

    public long pixelFormat() {
        return sendLong(id, "pixelFormat");
    }

    @SneakyThrows
    public void setPixelFormat(long pixelFormat) {
        L.invokeExact(id, ObjC.sel("setPixelFormat:"), pixelFormat);
    }

    public boolean isBlendingEnabled() {
        return sendBool(id, "isBlendingEnabled");
    }

    @SneakyThrows
    public void setBlendingEnabled(boolean blendingEnabled) {
        B.invokeExact(id, ObjC.sel("setBlendingEnabled:"), blendingEnabled);
    }

    public long sourceRGBBlendFactor() {
        return sendLong(id, "sourceRGBBlendFactor");
    }

    @SneakyThrows
    public void setSourceRGBBlendFactor(long sourceRGBBlendFactor) {
        L.invokeExact(id, ObjC.sel("setSourceRGBBlendFactor:"), sourceRGBBlendFactor);
    }

    public long destinationRGBBlendFactor() {
        return sendLong(id, "destinationRGBBlendFactor");
    }

    @SneakyThrows
    public void setDestinationRGBBlendFactor(long destinationRGBBlendFactor) {
        L.invokeExact(id, ObjC.sel("setDestinationRGBBlendFactor:"), destinationRGBBlendFactor);
    }

    public long rgbBlendOperation() {
        return sendLong(id, "rgbBlendOperation");
    }

    @SneakyThrows
    public void setRgbBlendOperation(long rgbBlendOperation) {
        L.invokeExact(id, ObjC.sel("setRgbBlendOperation:"), rgbBlendOperation);
    }

    public long sourceAlphaBlendFactor() {
        return sendLong(id, "sourceAlphaBlendFactor");
    }

    @SneakyThrows
    public void setSourceAlphaBlendFactor(long sourceAlphaBlendFactor) {
        L.invokeExact(id, ObjC.sel("setSourceAlphaBlendFactor:"), sourceAlphaBlendFactor);
    }

    public long destinationAlphaBlendFactor() {
        return sendLong(id, "destinationAlphaBlendFactor");
    }

    @SneakyThrows
    public void setDestinationAlphaBlendFactor(long destinationAlphaBlendFactor) {
        L.invokeExact(id, ObjC.sel("setDestinationAlphaBlendFactor:"), destinationAlphaBlendFactor);
    }

    public long alphaBlendOperation() {
        return sendLong(id, "alphaBlendOperation");
    }

    @SneakyThrows
    public void setAlphaBlendOperation(long alphaBlendOperation) {
        L.invokeExact(id, ObjC.sel("setAlphaBlendOperation:"), alphaBlendOperation);
    }

    public long writeMask() {
        return sendLong(id, "writeMask");
    }

    @SneakyThrows
    public void setWriteMask(long writeMask) {
        L.invokeExact(id, ObjC.sel("setWriteMask:"), writeMask);
    }
}
