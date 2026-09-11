package dev.dov.metalj.commands.encoders;

import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLParallelRenderCommandEncoder extends MTLCommandEncoder {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle LL = handle(null, ObjC.LONG, ObjC.LONG);

    private MTLParallelRenderCommandEncoder(long id) {
        super(id);
    }

    public static MTLParallelRenderCommandEncoder of(long id) {
        return new MTLParallelRenderCommandEncoder(id);
    }

    public MTLRenderCommandEncoder renderCommandEncoder() {
        return MTLRenderCommandEncoder.of(owned(() -> sendPtr(id, "renderCommandEncoder")));
    }

    @SneakyThrows
    public void setColorStoreAction(long action, long index) {
        LL.invokeExact(id, ObjC.sel("setColorStoreAction:atIndex:"), action, index);
    }

    @SneakyThrows
    public void setDepthStoreAction(long action) {
        L.invokeExact(id, ObjC.sel("setDepthStoreAction:"), action);
    }

    @SneakyThrows
    public void setStencilStoreAction(long action) {
        L.invokeExact(id, ObjC.sel("setStencilStoreAction:"), action);
    }

    @SneakyThrows
    public void setColorStoreActionOptions(long options, long index) {
        LL.invokeExact(id, ObjC.sel("setColorStoreActionOptions:atIndex:"), options, index);
    }

    @SneakyThrows
    public void setDepthStoreActionOptions(long options) {
        L.invokeExact(id, ObjC.sel("setDepthStoreActionOptions:"), options);
    }

    @SneakyThrows
    public void setStencilStoreActionOptions(long options) {
        L.invokeExact(id, ObjC.sel("setStencilStoreActionOptions:"), options);
    }
}
