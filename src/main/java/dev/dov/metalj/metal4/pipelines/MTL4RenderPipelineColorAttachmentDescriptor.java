package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4RenderPipelineColorAttachmentDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTL4RenderPipelineColorAttachmentDescriptor(long id) {
        super(id);
    }

    public static MTL4RenderPipelineColorAttachmentDescriptor of(long id) {
        return new MTL4RenderPipelineColorAttachmentDescriptor(id);
    }

    public static MTL4RenderPipelineColorAttachmentDescriptor new_() {
        return new MTL4RenderPipelineColorAttachmentDescriptor(
                sendPtr(ObjC.cls("MTL4RenderPipelineColorAttachmentDescriptor"), "new"));
    }

    public long pixelFormat() {
        return sendLong(id, "pixelFormat");
    }

    @SneakyThrows
    public void setPixelFormat(long format) {
        L.invokeExact(id, ObjC.sel("setPixelFormat:"), format);
    }

    public long blendingState() {
        return sendLong(id, "blendingState");
    }

    @SneakyThrows
    public void setBlendingState(long state) {
        L.invokeExact(id, ObjC.sel("setBlendingState:"), state);
    }

    public long sourceRGBBlendFactor() {
        return sendLong(id, "sourceRGBBlendFactor");
    }

    @SneakyThrows
    public void setSourceRGBBlendFactor(long factor) {
        L.invokeExact(id, ObjC.sel("setSourceRGBBlendFactor:"), factor);
    }

    public long destinationRGBBlendFactor() {
        return sendLong(id, "destinationRGBBlendFactor");
    }

    @SneakyThrows
    public void setDestinationRGBBlendFactor(long factor) {
        L.invokeExact(id, ObjC.sel("setDestinationRGBBlendFactor:"), factor);
    }

    public long rgbBlendOperation() {
        return sendLong(id, "rgbBlendOperation");
    }

    @SneakyThrows
    public void setRgbBlendOperation(long operation) {
        L.invokeExact(id, ObjC.sel("setRgbBlendOperation:"), operation);
    }

    public long sourceAlphaBlendFactor() {
        return sendLong(id, "sourceAlphaBlendFactor");
    }

    @SneakyThrows
    public void setSourceAlphaBlendFactor(long factor) {
        L.invokeExact(id, ObjC.sel("setSourceAlphaBlendFactor:"), factor);
    }

    public long destinationAlphaBlendFactor() {
        return sendLong(id, "destinationAlphaBlendFactor");
    }

    @SneakyThrows
    public void setDestinationAlphaBlendFactor(long factor) {
        L.invokeExact(id, ObjC.sel("setDestinationAlphaBlendFactor:"), factor);
    }

    public long alphaBlendOperation() {
        return sendLong(id, "alphaBlendOperation");
    }

    @SneakyThrows
    public void setAlphaBlendOperation(long operation) {
        L.invokeExact(id, ObjC.sel("setAlphaBlendOperation:"), operation);
    }

    public long writeMask() {
        return sendLong(id, "writeMask");
    }

    @SneakyThrows
    public void setWriteMask(long mask) {
        L.invokeExact(id, ObjC.sel("setWriteMask:"), mask);
    }

    public void reset() {
        sendVoid(id, "reset");
    }
}
