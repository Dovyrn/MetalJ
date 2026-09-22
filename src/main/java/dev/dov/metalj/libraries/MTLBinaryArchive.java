package dev.dov.metalj.libraries;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.functions.MTLFunctionDescriptor;
import dev.dov.metalj.objc.NSError;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineDescriptor;
import dev.dov.metalj.pipelines.render.MTLMeshRenderPipelineDescriptor;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineDescriptor;
import dev.dov.metalj.pipelines.render.MTLTileRenderPipelineDescriptor;
import java.lang.foreign.Arena;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLBinaryArchive extends NSObject {
    private static final long ADD_FUNCTION_WITH_DESCRIPTOR_LIBRARY_ERROR = ObjC.sel("addFunctionWithDescriptor:library:error:");
    private static final long DEVICE = ObjC.sel("device");
    private static final long LABEL = ObjC.sel("label");
    private static final long SET_LABEL = ObjC.sel("setLabel:");

    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle B_PA = handle(ObjC.BOOL, ObjC.PTR, ValueLayout.ADDRESS);
    private static final MethodHandle B_PPA = handle(ObjC.BOOL, ObjC.PTR, ObjC.PTR, ValueLayout.ADDRESS);

    private MTLBinaryArchive(long id) {
        super(id);
    }

    public static MTLBinaryArchive of(long id) {
        return new MTLBinaryArchive(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public void addComputePipelineFunctions(MTLComputePipelineDescriptor descriptor) {
        add("addComputePipelineFunctionsWithDescriptor:error:", descriptor.getId());
    }

    public void addRenderPipelineFunctions(MTLRenderPipelineDescriptor descriptor) {
        add("addRenderPipelineFunctionsWithDescriptor:error:", descriptor.getId());
    }

    public void addTileRenderPipelineFunctions(MTLTileRenderPipelineDescriptor descriptor) {
        add("addTileRenderPipelineFunctionsWithDescriptor:error:", descriptor.getId());
    }

    public void addMeshRenderPipelineFunctions(MTLMeshRenderPipelineDescriptor descriptor) {
        add("addMeshRenderPipelineFunctionsWithDescriptor:error:", descriptor.getId());
    }

    @SneakyThrows
    public void addLibrary(MTLFunctionDescriptor descriptor) {
        add("addLibraryWithDescriptor:error:", descriptor.getId());
    }

    @SneakyThrows
    public void addFunction(MTLFunctionDescriptor descriptor, long library) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            boolean ok = (boolean) B_PPA.invokeExact(id, ADD_FUNCTION_WITH_DESCRIPTOR_LIBRARY_ERROR,
                    descriptor.getId(), library, error);
            NSError.check(error, "addFunctionWithDescriptor:library:error:");
            if (!ok) {
                throw new IllegalStateException("addFunctionWithDescriptor failed");
            }
        }
    }

    @SneakyThrows
    public void serializeToURL(NSURL url) {
        add("serializeToURL:error:", url.getId());
    }

    @SneakyThrows
    private void add(String selector, long argument) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            boolean ok = (boolean) B_PA.invokeExact(id, ObjC.sel(selector), argument, error);
            NSError.check(error, selector);
            if (!ok) {
                throw new IllegalStateException(selector + " failed");
            }
        }
    }
}
