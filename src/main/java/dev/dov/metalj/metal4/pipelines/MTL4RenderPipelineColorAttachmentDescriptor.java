package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4RenderPipelineColorAttachmentDescriptor extends NSObject {
    private static final long MTL_4_RENDER_PIPELINE_COLOR_ATTACHMENT_DESCRIPTOR = ObjC.cls("MTL4RenderPipelineColorAttachmentDescriptor");

    private static final long ALPHA_BLEND_OPERATION = ObjC.sel("alphaBlendOperation");
    private static final long BLENDING_STATE = ObjC.sel("blendingState");
    private static final long DESTINATION_ALPHA_BLEND_FACTOR = ObjC.sel("destinationAlphaBlendFactor");
    private static final long DESTINATION_RGB_BLEND_FACTOR = ObjC.sel("destinationRGBBlendFactor");
    private static final long NEW = ObjC.sel("new");
    private static final long PIXEL_FORMAT = ObjC.sel("pixelFormat");
    private static final long RESET = ObjC.sel("reset");
    private static final long RGB_BLEND_OPERATION = ObjC.sel("rgbBlendOperation");
    private static final long SET_ALPHA_BLEND_OPERATION = ObjC.sel("setAlphaBlendOperation:");
    private static final long SET_BLENDING_STATE = ObjC.sel("setBlendingState:");
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

    private MTL4RenderPipelineColorAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTL4RenderPipelineColorAttachmentDescriptor of(long id) {
        return new MTL4RenderPipelineColorAttachmentDescriptor(id);
    }

    public static MTL4RenderPipelineColorAttachmentDescriptor new_() {
        return new MTL4RenderPipelineColorAttachmentDescriptor(
                sendPtr(MTL_4_RENDER_PIPELINE_COLOR_ATTACHMENT_DESCRIPTOR, NEW));
    }

    public long pixelFormat() {
        return sendLong(id, PIXEL_FORMAT);
    }

    @SneakyThrows
    public void setPixelFormat(long format) {
        L.invokeExact(id, SET_PIXEL_FORMAT, format);
    }

    public long blendingState() {
        return sendLong(id, BLENDING_STATE);
    }

    @SneakyThrows
    public void setBlendingState(long state) {
        L.invokeExact(id, SET_BLENDING_STATE, state);
    }

    public long sourceRGBBlendFactor() {
        return sendLong(id, SOURCE_RGB_BLEND_FACTOR);
    }

    @SneakyThrows
    public void setSourceRGBBlendFactor(long factor) {
        L.invokeExact(id, SET_SOURCE_RGB_BLEND_FACTOR, factor);
    }

    public long destinationRGBBlendFactor() {
        return sendLong(id, DESTINATION_RGB_BLEND_FACTOR);
    }

    @SneakyThrows
    public void setDestinationRGBBlendFactor(long factor) {
        L.invokeExact(id, SET_DESTINATION_RGB_BLEND_FACTOR, factor);
    }

    public long rgbBlendOperation() {
        return sendLong(id, RGB_BLEND_OPERATION);
    }

    @SneakyThrows
    public void setRgbBlendOperation(long operation) {
        L.invokeExact(id, SET_RGB_BLEND_OPERATION, operation);
    }

    public long sourceAlphaBlendFactor() {
        return sendLong(id, SOURCE_ALPHA_BLEND_FACTOR);
    }

    @SneakyThrows
    public void setSourceAlphaBlendFactor(long factor) {
        L.invokeExact(id, SET_SOURCE_ALPHA_BLEND_FACTOR, factor);
    }

    public long destinationAlphaBlendFactor() {
        return sendLong(id, DESTINATION_ALPHA_BLEND_FACTOR);
    }

    @SneakyThrows
    public void setDestinationAlphaBlendFactor(long factor) {
        L.invokeExact(id, SET_DESTINATION_ALPHA_BLEND_FACTOR, factor);
    }

    public long alphaBlendOperation() {
        return sendLong(id, ALPHA_BLEND_OPERATION);
    }

    @SneakyThrows
    public void setAlphaBlendOperation(long operation) {
        L.invokeExact(id, SET_ALPHA_BLEND_OPERATION, operation);
    }

    public long writeMask() {
        return sendLong(id, WRITE_MASK);
    }

    @SneakyThrows
    public void setWriteMask(long mask) {
        L.invokeExact(id, SET_WRITE_MASK, mask);
    }

    public void reset() {
        sendVoid(id, RESET);
    }
}
