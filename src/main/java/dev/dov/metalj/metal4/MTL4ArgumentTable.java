package dev.dov.metalj.metal4;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4ArgumentTable extends NSObject {
    private static final MethodHandle LL = handle(null, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG);

    private MTL4ArgumentTable(long id) {
        super(id);
    }

    public static MTL4ArgumentTable of(long id) {
        return new MTL4ArgumentTable(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setAddress(long address, long index) {
        LL.invokeExact(id, ObjC.sel("setAddress:atIndex:"), address, index);
    }

    @SneakyThrows
    public void setAddress(long address, long stride, long index) {
        LLL.invokeExact(id, ObjC.sel("setAddress:attributeStride:atIndex:"), address, stride, index);
    }

    @SneakyThrows
    public void setResource(long resource, long index) {
        LL.invokeExact(id, ObjC.sel("setResource:atBufferIndex:"), resource, index);
    }

    @SneakyThrows
    public void setTexture(long resource, long index) {
        LL.invokeExact(id, ObjC.sel("setTexture:atIndex:"), resource, index);
    }

    @SneakyThrows
    public void setSamplerState(long resource, long index) {
        LL.invokeExact(id, ObjC.sel("setSamplerState:atIndex:"), resource, index);
    }
}
