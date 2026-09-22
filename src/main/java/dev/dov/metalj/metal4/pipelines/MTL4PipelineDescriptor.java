package dev.dov.metalj.metal4.pipelines;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4PipelineDescriptor extends NSObject {
    private static final long LABEL = ObjC.sel("label");
    private static final long OPTIONS = ObjC.sel("options");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_OPTIONS = ObjC.sel("setOptions:");

    protected static final MethodHandle L = handle(null, ObjC.LONG);
    protected static final MethodHandle B = handle(null, ObjC.BOOL);
    protected static final MethodHandle P = handle(null, ObjC.PTR);

    protected MTL4PipelineDescriptor(long id) {
        super(id);
    }

    public static MTL4PipelineDescriptor of(long id) {
        return new MTL4PipelineDescriptor(id);
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public MTL4PipelineOptions options() {
        return MTL4PipelineOptions.of(sendPtr(id, OPTIONS));
    }

    @SneakyThrows
    public void setOptions(MTL4PipelineOptions options) {
        P.invokeExact(id, SET_OPTIONS, options.getId());
    }
}
