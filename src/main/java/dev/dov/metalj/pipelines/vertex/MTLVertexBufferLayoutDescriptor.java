package dev.dov.metalj.pipelines.vertex;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLVertexBufferLayoutDescriptor extends NSObject {
    private static final long MTL_VERTEX_BUFFER_LAYOUT_DESCRIPTOR = ObjC.cls("MTLVertexBufferLayoutDescriptor");

    private static final long NEW = ObjC.sel("new");
    private static final long SET_STEP_FUNCTION = ObjC.sel("setStepFunction:");
    private static final long SET_STEP_RATE = ObjC.sel("setStepRate:");
    private static final long SET_STRIDE = ObjC.sel("setStride:");
    private static final long STEP_FUNCTION = ObjC.sel("stepFunction");
    private static final long STEP_RATE = ObjC.sel("stepRate");
    private static final long STRIDE = ObjC.sel("stride");

    private static final MethodHandle L = handle(null, ObjC.LONG);

    private MTLVertexBufferLayoutDescriptor(long id) {
        super(id);
    }

    public static MTLVertexBufferLayoutDescriptor of(long id) {
        return new MTLVertexBufferLayoutDescriptor(id);
    }

    public static MTLVertexBufferLayoutDescriptor new_() {
        return new MTLVertexBufferLayoutDescriptor(sendPtr(MTL_VERTEX_BUFFER_LAYOUT_DESCRIPTOR, NEW));
    }

    public long stride() {
        return sendLong(id, STRIDE);
    }

    @SneakyThrows
    public void setStride(long stride) {
        L.invokeExact(id, SET_STRIDE, stride);
    }

    public long stepFunction() {
        return sendLong(id, STEP_FUNCTION);
    }

    @SneakyThrows
    public void setStepFunction(long stepFunction) {
        L.invokeExact(id, SET_STEP_FUNCTION, stepFunction);
    }

    public long stepRate() {
        return sendLong(id, STEP_RATE);
    }

    @SneakyThrows
    public void setStepRate(long stepRate) {
        L.invokeExact(id, SET_STEP_RATE, stepRate);
    }
}
