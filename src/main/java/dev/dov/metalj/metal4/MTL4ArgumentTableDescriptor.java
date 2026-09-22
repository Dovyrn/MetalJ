package dev.dov.metalj.metal4;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4ArgumentTableDescriptor extends NSObject {
    private static final long MTL_4_ARGUMENT_TABLE_DESCRIPTOR = ObjC.cls("MTL4ArgumentTableDescriptor");

    private static final long INITIALIZE_BINDINGS = ObjC.sel("initializeBindings");
    private static final long LABEL = ObjC.sel("label");
    private static final long MAX_BUFFER_BIND_COUNT = ObjC.sel("maxBufferBindCount");
    private static final long MAX_SAMPLER_STATE_BIND_COUNT = ObjC.sel("maxSamplerStateBindCount");
    private static final long MAX_TEXTURE_BIND_COUNT = ObjC.sel("maxTextureBindCount");
    private static final long NEW = ObjC.sel("new");
    private static final long SET_INITIALIZE_BINDINGS = ObjC.sel("setInitializeBindings:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_MAX_BUFFER_BIND_COUNT = ObjC.sel("setMaxBufferBindCount:");
    private static final long SET_MAX_SAMPLER_STATE_BIND_COUNT = ObjC.sel("setMaxSamplerStateBindCount:");
    private static final long SET_MAX_TEXTURE_BIND_COUNT = ObjC.sel("setMaxTextureBindCount:");
    private static final long SET_SUPPORT_ATTRIBUTE_STRIDES = ObjC.sel("setSupportAttributeStrides:");
    private static final long SUPPORT_ATTRIBUTE_STRIDES = ObjC.sel("supportAttributeStrides");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTL4ArgumentTableDescriptor(long id) {
        super(id);
    }

    public static MTL4ArgumentTableDescriptor of(long id) {
        return new MTL4ArgumentTableDescriptor(id);
    }

    public static MTL4ArgumentTableDescriptor new_() {
        return new MTL4ArgumentTableDescriptor(sendPtr(MTL_4_ARGUMENT_TABLE_DESCRIPTOR, NEW));
    }

    public long maxBufferBindCount() {
        return sendLong(id, MAX_BUFFER_BIND_COUNT);
    }

    @SneakyThrows
    public void setMaxBufferBindCount(long count) {
        L.invokeExact(id, SET_MAX_BUFFER_BIND_COUNT, count);
    }

    public long maxTextureBindCount() {
        return sendLong(id, MAX_TEXTURE_BIND_COUNT);
    }

    @SneakyThrows
    public void setMaxTextureBindCount(long count) {
        L.invokeExact(id, SET_MAX_TEXTURE_BIND_COUNT, count);
    }

    public long maxSamplerStateBindCount() {
        return sendLong(id, MAX_SAMPLER_STATE_BIND_COUNT);
    }

    @SneakyThrows
    public void setMaxSamplerStateBindCount(long count) {
        L.invokeExact(id, SET_MAX_SAMPLER_STATE_BIND_COUNT, count);
    }

    public boolean initializeBindings() {
        return sendBool(id, INITIALIZE_BINDINGS);
    }

    @SneakyThrows
    public void setInitializeBindings(boolean initialize) {
        B.invokeExact(id, SET_INITIALIZE_BINDINGS, initialize);
    }

    public boolean supportAttributeStrides() {
        return sendBool(id, SUPPORT_ATTRIBUTE_STRIDES);
    }

    @SneakyThrows
    public void setSupportAttributeStrides(boolean support) {
        B.invokeExact(id, SET_SUPPORT_ATTRIBUTE_STRIDES, support);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }
}
