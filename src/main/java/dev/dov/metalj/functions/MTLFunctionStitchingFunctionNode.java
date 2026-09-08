package dev.dov.metalj.functions;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLFunctionStitchingFunctionNode extends MTLFunctionStitchingNode {
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
                ObjC.sel("initWithName:arguments:controlDependencies:"), name.getId(), arguments.getId(),
                controlDependencies.getId());
        return new MTLFunctionStitchingFunctionNode(id);
    }

    public NSString name() {
        return NSString.of(sendPtr(id, "name"));
    }

    @SneakyThrows
    public void setName(NSString name) {
        P.invokeExact(id, ObjC.sel("setName:"), name.getId());
    }

    public NSArray arguments() {
        return NSArray.of(sendPtr(id, "arguments"));
    }

    @SneakyThrows
    public void setArguments(NSArray arguments) {
        P.invokeExact(id, ObjC.sel("setArguments:"), arguments.getId());
    }

    public NSArray controlDependencies() {
        return NSArray.of(sendPtr(id, "controlDependencies"));
    }

    @SneakyThrows
    public void setControlDependencies(NSArray dependencies) {
        P.invokeExact(id, ObjC.sel("setControlDependencies:"), dependencies.getId());
    }
}
