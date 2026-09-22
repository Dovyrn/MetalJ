package dev.dov.metalj.pipelines.render;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderPipelineColorAttachmentDescriptor extends NSObject {
    private static final long MTL_RENDER_PIPELINE_COLOR_ATTACHMENT_DESCRIPTOR = ObjC.cls("MTLRenderPipelineColorAttachmentDescriptor");

    private static final long ALPHA_BLEND_OPERATION = ObjC.sel("alphaBlendOperation");
    private static final long DESTINATION_ALPHA_BLEND_FACTOR = ObjC.sel("destinationAlphaBlendFactor");
    private static final long DESTINATION_RGB_BLEND_FACTOR = ObjC.sel("destinationRGBBlendFactor");
    private static final long IS_BLENDING_ENABLED = ObjC.sel("isBlendingEnabled");
    private static final long NEW = ObjC.sel("new");
    private static final long PIXEL_FORMAT = ObjC.sel("pixelFormat");
    private static final long RGB_BLEND_OPERATION = ObjC.sel("rgbBlendOperation");
    private static final long SET_ALPHA_BLEND_OPERATION = ObjC.sel("setAlphaBlendOperation:");
    private static final long SET_BLENDING_ENABLED = ObjC.sel("setBlendingEnabled:");
    private static final long SET_DESTINATION_ALPHA_BLEND_FACTOR = ObjC.sel("setDestinationAlphaBlendFactor:");
    private static final long SET_DESTINATION_RGB_BLEND_FACTOR = ObjC.sel("setDestinationRGBBlendFactor:");
    private static final long SET_PIXEL_FORMAT = ObjC.sel("setPixelFormat:");
    private static final long SET_RGB_BLEND_OPERATION = ObjC.sel("setRgbBlendOperation:");
    private static final long SET_SOURCE_ALPHA_BLEND_FACTOR = ObjC.sel("setSourceAlphaBlendFactor:");
    private static final long SET_SOURCE_RGB_BLEND_FACTOR = ObjC.sel("setSourceRGBBlendFactor:");
    private static final long SET_WRITE_MASK = ObjC.sel("setWriteMask:");
    private static final long SOURCE_ALPHA_BLEND_FACTOR = ObjC.sel("sourceAlphaBlendFactor");
    private static final long SOURCE_RGB_BLEND_FACTOR = ObjC.sel("sourceRGBBlendFactor");
    private static final long WRITE_MASK = ObjC.sel("writeMask");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);

    private MTLRenderPipelineColorAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTLRenderPipelineColorAttachmentDescriptor of(long id) {
        return new MTLRenderPipelineColorAttachmentDescriptor(id);
    }

    public static MTLRenderPipelineColorAttachmentDescriptor new_() {
        return new MTLRenderPipelineColorAttachmentDescriptor(sendPtr(MTL_RENDER_PIPELINE_COLOR_ATTACHMENT_DESCRIPTOR, NEW));
    }

    public long pixelFormat() {
        return sendLong(id, PIXEL_FORMAT);
    }

    @SneakyThrows
    public void setPixelFormat(long pixelFormat) {
        L.invokeExact(id, SET_PIXEL_FORMAT, pixelFormat);
    }

    public boolean isBlendingEnabled() {
        return sendBool(id, IS_BLENDING_ENABLED);
    }

    @SneakyThrows
    public void setBlendingEnabled(boolean blendingEnabled) {
        B.invokeExact(id, SET_BLENDING_ENABLED, blendingEnabled);
    }

    public long sourceRGBBlendFactor() {
        return sendLong(id, SOURCE_RGB_BLEND_FACTOR);
    }

    @SneakyThrows
    public void setSourceRGBBlendFactor(long sourceRGBBlendFactor) {
        L.invokeExact(id, SET_SOURCE_RGB_BLEND_FACTOR, sourceRGBBlendFactor);
    }

    public long destinationRGBBlendFactor() {
        return sendLong(id, DESTINATION_RGB_BLEND_FACTOR);
    }

    @SneakyThrows
    public void setDestinationRGBBlendFactor(long destinationRGBBlendFactor) {
        L.invokeExact(id, SET_DESTINATION_RGB_BLEND_FACTOR, destinationRGBBlendFactor);
    }

    public long rgbBlendOperation() {
        return sendLong(id, RGB_BLEND_OPERATION);
    }

    @SneakyThrows
    public void setRgbBlendOperation(long rgbBlendOperation) {
        L.invokeExact(id, SET_RGB_BLEND_OPERATION, rgbBlendOperation);
    }

    public long sourceAlphaBlendFactor() {
        return sendLong(id, SOURCE_ALPHA_BLEND_FACTOR);
    }

    @SneakyThrows
    public void setSourceAlphaBlendFactor(long sourceAlphaBlendFactor) {
        L.invokeExact(id, SET_SOURCE_ALPHA_BLEND_FACTOR, sourceAlphaBlendFactor);
    }

    public long destinationAlphaBlendFactor() {
        return sendLong(id, DESTINATION_ALPHA_BLEND_FACTOR);
    }

    @SneakyThrows
    public void setDestinationAlphaBlendFactor(long destinationAlphaBlendFactor) {
        L.invokeExact(id, SET_DESTINATION_ALPHA_BLEND_FACTOR, destinationAlphaBlendFactor);
    }

    public long alphaBlendOperation() {
        return sendLong(id, ALPHA_BLEND_OPERATION);
    }

    @SneakyThrows
    public void setAlphaBlendOperation(long alphaBlendOperation) {
        L.invokeExact(id, SET_ALPHA_BLEND_OPERATION, alphaBlendOperation);
    }

    public long writeMask() {
        return sendLong(id, WRITE_MASK);
    }

    @SneakyThrows
    public void setWriteMask(long writeMask) {
        L.invokeExact(id, SET_WRITE_MASK, writeMask);
    }
}
