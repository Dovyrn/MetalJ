package dev.dov.metalj.pipelines.vertex;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLVertexBufferLayoutDescriptor extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLVertexBufferLayoutDescriptor(long id) {
        super(id);
    }

    public static MTLVertexBufferLayoutDescriptor of(long id) {
        return new MTLVertexBufferLayoutDescriptor(id);
    }

    public static MTLVertexBufferLayoutDescriptor new_() {
        return new MTLVertexBufferLayoutDescriptor(sendPtr(ObjC.cls("MTLVertexBufferLayoutDescriptor"), "new"));
    }

    public long stride() {
        return sendLong(id, "stride");
    }

    @SneakyThrows
    public void setStride(long stride) {
        L.invokeExact(id, ObjC.sel("setStride:"), stride);
    }

    public long stepFunction() {
        return sendLong(id, "stepFunction");
    }

    @SneakyThrows
    public void setStepFunction(long stepFunction) {
        L.invokeExact(id, ObjC.sel("setStepFunction:"), stepFunction);
    }

    public long stepRate() {
        return sendLong(id, "stepRate");
    }

    @SneakyThrows
    public void setStepRate(long stepRate) {
        L.invokeExact(id, ObjC.sel("setStepRate:"), stepRate);
    }
}
