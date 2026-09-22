package dev.dov.metalj.metal4;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4ArgumentTable extends NSObject {
    private static final long DEVICE = ObjC.sel("device");
    private static final long LABEL = ObjC.sel("label");
    private static final long SET_ADDRESS_AT_INDEX = ObjC.sel("setAddress:atIndex:");
    private static final long SET_ADDRESS_ATTRIBUTE_STRIDE_AT_INDEX = ObjC.sel("setAddress:attributeStride:atIndex:");
    private static final long SET_RESOURCE_AT_BUFFER_INDEX = ObjC.sel("setResource:atBufferIndex:");
    private static final long SET_SAMPLER_STATE_AT_INDEX = ObjC.sel("setSamplerState:atIndex:");
    private static final long SET_TEXTURE_AT_INDEX = ObjC.sel("setTexture:atIndex:");

    private static final MethodHandle LL = handle(null, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG);

    private MTL4ArgumentTable(long id) {
        super(id);
    }

    public static MTL4ArgumentTable of(long id) {
        return new MTL4ArgumentTable(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setAddress(long address, long index) {
        LL.invokeExact(id, SET_ADDRESS_AT_INDEX, address, index);
    }

    @SneakyThrows
    public void setAddress(long address, long stride, long index) {
        LLL.invokeExact(id, SET_ADDRESS_ATTRIBUTE_STRIDE_AT_INDEX, address, stride, index);
    }

    @SneakyThrows
    public void setResource(long resource, long index) {
        LL.invokeExact(id, SET_RESOURCE_AT_BUFFER_INDEX, resource, index);
    }

    @SneakyThrows
    public void setTexture(long resource, long index) {
        LL.invokeExact(id, SET_TEXTURE_AT_INDEX, resource, index);
    }

    @SneakyThrows
    public void setSamplerState(long resource, long index) {
        LL.invokeExact(id, SET_SAMPLER_STATE_AT_INDEX, resource, index);
    }
}
