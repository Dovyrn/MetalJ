package dev.dov.metalj.pipelines.shaders;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.functions.MTLFunctionDescriptor;
import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSError;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLLibrary extends NSObject {
    private static final long DEVICE = ObjC.sel("device");
    private static final long FUNCTION_NAMES = ObjC.sel("functionNames");
    private static final long INSTALL_NAME = ObjC.sel("installName");
    private static final long LABEL = ObjC.sel("label");
    private static final long NEW_FUNCTION_WITH_DESCRIPTOR_ERROR = ObjC.sel("newFunctionWithDescriptor:error:");
    private static final long NEW_FUNCTION_WITH_NAME = ObjC.sel("newFunctionWithName:");
    private static final long NEW_FUNCTION_WITH_NAME_CONSTANT_VALUES_ERROR = ObjC.sel("newFunctionWithName:constantValues:error:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long TYPE = ObjC.sel("type");

    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle P_P = handle(ObjC.PTR, ObjC.PTR);
    private static final MethodHandle P_PPA = handle(ObjC.PTR, ObjC.PTR, ObjC.PTR, ValueLayout.ADDRESS);
    private static final MethodHandle P_PA = handle(ObjC.PTR, ObjC.PTR, ValueLayout.ADDRESS);

    private MTLLibrary(long id) {
        super(id);
    }

    public static MTLLibrary of(long id) {
        return new MTLLibrary(id);
    }

    @SneakyThrows
    public MTLFunction newFunctionWithName(NSString name) {
        return MTLFunction.of((long) P_P.invokeExact(id, NEW_FUNCTION_WITH_NAME, name.getId()));
    }

    @SneakyThrows
    public MTLFunction newFunctionWithName(NSString name, MTLFunctionConstantValues constantValues) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long function = (long) P_PPA.invokeExact(id, NEW_FUNCTION_WITH_NAME_CONSTANT_VALUES_ERROR,
                    name.getId(), constantValues.getId(), error);
            NSError.check(error, "newFunctionWithName:constantValues:error:");
            return MTLFunction.of(function);
        }
    }

    public NSArray functionNames() {
        return NSArray.of(sendPtr(id, FUNCTION_NAMES));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public long type() {
        return sendLong(id, TYPE);
    }

    public NSString installName() {
        return NSString.of(sendPtr(id, INSTALL_NAME));
    }

    @SneakyThrows
    public MTLFunction newFunctionWithDescriptor(MTLFunctionDescriptor descriptor) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long function = (long) P_PA.invokeExact(id, NEW_FUNCTION_WITH_DESCRIPTOR_ERROR,
                    descriptor.getId(), error);
            NSError.check(error, "newFunctionWithDescriptor:error:");
            return MTLFunction.of(function);
        }
    }
}
