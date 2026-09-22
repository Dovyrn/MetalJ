package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLFunctionStitchingFunctionNode extends MTLFunctionStitchingNode {
    private static final long ARGUMENTS = ObjC.sel("arguments");
    private static final long CONTROL_DEPENDENCIES = ObjC.sel("controlDependencies");
    private static final long INIT_WITH_NAME_ARGUMENTS_CONTROL_DEPENDENCIES = ObjC.sel("initWithName:arguments:controlDependencies:");
    private static final long NAME = ObjC.sel("name");
    private static final long SET_ARGUMENTS = ObjC.sel("setArguments:");
    private static final long SET_CONTROL_DEPENDENCIES = ObjC.sel("setControlDependencies:");
    private static final long SET_NAME = ObjC.sel("setName:");

    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle P_PPP = handle(ObjC.PTR, ObjC.PTR, ObjC.PTR, ObjC.PTR);

    private MTLFunctionStitchingFunctionNode(long id) {
        super(id);
    }

    public static MTLFunctionStitchingFunctionNode of(long id) {
        return new MTLFunctionStitchingFunctionNode(id);
    }

    @SneakyThrows
    public static MTLFunctionStitchingFunctionNode initWithName(NSString name, NSArray arguments,
            NSArray controlDependencies) {
        long id = (long) P_PPP.invokeExact(alloc("MTLFunctionStitchingFunctionNode"),
                INIT_WITH_NAME_ARGUMENTS_CONTROL_DEPENDENCIES, name.getId(), arguments.getId(),
                controlDependencies.getId());
        return new MTLFunctionStitchingFunctionNode(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, NAME));
    }

    @SneakyThrows
    public void setName(NSString name) {
        P.invokeExact(id, SET_NAME, name.getId());
    }

    public NSArray arguments() {
        return NSArray.of(sendPtr(id, ARGUMENTS));
    }

    @SneakyThrows
    public void setArguments(NSArray arguments) {
        P.invokeExact(id, SET_ARGUMENTS, arguments.getId());
    }

    public NSArray controlDependencies() {
        return NSArray.of(sendPtr(id, CONTROL_DEPENDENCIES));
    }

    @SneakyThrows
    public void setControlDependencies(NSArray dependencies) {
        P.invokeExact(id, SET_CONTROL_DEPENDENCIES, dependencies.getId());
    }
}
