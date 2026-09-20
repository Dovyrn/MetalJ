package dev.dov.metalj.metal4;

import dev.dov.metalj.objc.NSError;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4CommitFeedback extends NSObject {
    private static final MethodHandle D = handle(ObjC.DOUBLE);

    private MTL4CommitFeedback(long id) {
        super(id);
    }

    public static MTL4CommitFeedback of(long id) {
        return new MTL4CommitFeedback(id);
    }

    public NSError error() {
        return NSError.of(sendPtr(id, "error"));
    }

    @SneakyThrows
    public double GPUStartTime() {
        return (double) D.invokeExact(id, ObjC.sel("GPUStartTime"));
    }

    @SneakyThrows
    public double GPUEndTime() {
        return (double) D.invokeExact(id, ObjC.sel("GPUEndTime"));
    }
}
