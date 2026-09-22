package dev.dov.metalj.commands.encoders;

import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLParallelRenderCommandEncoder extends MTLCommandEncoder {
    private static final long RENDER_COMMAND_ENCODER = ObjC.sel("renderCommandEncoder");
    private static final long SET_COLOR_STORE_ACTION_AT_INDEX = ObjC.sel("setColorStoreAction:atIndex:");
    private static final long SET_COLOR_STORE_ACTION_OPTIONS_AT_INDEX = ObjC.sel("setColorStoreActionOptions:atIndex:");
    private static final long SET_DEPTH_STORE_ACTION = ObjC.sel("setDepthStoreAction:");
    private static final long SET_DEPTH_STORE_ACTION_OPTIONS = ObjC.sel("setDepthStoreActionOptions:");
    private static final long SET_STENCIL_STORE_ACTION = ObjC.sel("setStencilStoreAction:");
    private static final long SET_STENCIL_STORE_ACTION_OPTIONS = ObjC.sel("setStencilStoreActionOptions:");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle LL = handle(null, ObjC.LONG, ObjC.LONG);

    private MTLParallelRenderCommandEncoder(long id) {
        super(id);
    }

    public static MTLParallelRenderCommandEncoder of(long id) {
        return new MTLParallelRenderCommandEncoder(id);
    }

    public MTLRenderCommandEncoder renderCommandEncoder() {
        return MTLRenderCommandEncoder.of(owned(() -> sendPtr(id, RENDER_COMMAND_ENCODER)));
    }

    @SneakyThrows
    public void setColorStoreAction(long action, long index) {
        LL.invokeExact(id, SET_COLOR_STORE_ACTION_AT_INDEX, action, index);
    }

    @SneakyThrows
    public void setDepthStoreAction(long action) {
        L.invokeExact(id, SET_DEPTH_STORE_ACTION, action);
    }

    @SneakyThrows
    public void setStencilStoreAction(long action) {
        L.invokeExact(id, SET_STENCIL_STORE_ACTION, action);
    }

    @SneakyThrows
    public void setColorStoreActionOptions(long options, long index) {
        LL.invokeExact(id, SET_COLOR_STORE_ACTION_OPTIONS_AT_INDEX, options, index);
    }

    @SneakyThrows
    public void setDepthStoreActionOptions(long options) {
        L.invokeExact(id, SET_DEPTH_STORE_ACTION_OPTIONS, options);
    }

    @SneakyThrows
    public void setStencilStoreActionOptions(long options) {
        L.invokeExact(id, SET_STENCIL_STORE_ACTION_OPTIONS, options);
    }
}
