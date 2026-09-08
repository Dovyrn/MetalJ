package dev.dov.metalj.pipelines.shaders;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCompileOptions extends NSObject {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle B = handle(null, ObjC.BOOL);
    private static final MethodHandle P = handle(null, ObjC.PTR);

    private MTLCompileOptions(long id) {
        super(id);
    }

    public static MTLCompileOptions of(long id) {
        return new MTLCompileOptions(id);
    }

    public static MTLCompileOptions new_() {
        return new MTLCompileOptions(sendPtr(ObjC.cls("MTLCompileOptions"), "new"));
    }

    public long languageVersion() {
        return sendLong(id, "languageVersion");
    }

    @SneakyThrows
    public void setLanguageVersion(long languageVersion) {
        L.invokeExact(id, ObjC.sel("setLanguageVersion:"), languageVersion);
    }

    public boolean fastMathEnabled() {
        return sendBool(id, "fastMathEnabled");
    }

    @SneakyThrows
    public void setFastMathEnabled(boolean fastMathEnabled) {
        B.invokeExact(id, ObjC.sel("setFastMathEnabled:"), fastMathEnabled);
    }

    public long mathMode() {
        return sendLong(id, "mathMode");
    }

    @SneakyThrows
    public void setMathMode(long mathMode) {
        L.invokeExact(id, ObjC.sel("setMathMode:"), mathMode);
    }

    public long mathFloatingPointFunctions() {
        return sendLong(id, "mathFloatingPointFunctions");
    }

    @SneakyThrows
    public void setMathFloatingPointFunctions(long mathFloatingPointFunctions) {
        L.invokeExact(id, ObjC.sel("setMathFloatingPointFunctions:"), mathFloatingPointFunctions);
    }

    public long libraryType() {
        return sendLong(id, "libraryType");
    }

    @SneakyThrows
    public void setLibraryType(long libraryType) {
        L.invokeExact(id, ObjC.sel("setLibraryType:"), libraryType);
    }

    public NSString installName() {
        return NSString.of(sendPtr(id, "installName"));
    }

    @SneakyThrows
    public void setInstallName(NSString installName) {
        P.invokeExact(id, ObjC.sel("setInstallName:"), installName.getId());
    }

    public boolean preserveInvariance() {
        return sendBool(id, "preserveInvariance");
    }

    @SneakyThrows
    public void setPreserveInvariance(boolean preserveInvariance) {
        B.invokeExact(id, ObjC.sel("setPreserveInvariance:"), preserveInvariance);
    }

    public long optimizationLevel() {
        return sendLong(id, "optimizationLevel");
    }

    @SneakyThrows
    public void setOptimizationLevel(long optimizationLevel) {
        L.invokeExact(id, ObjC.sel("setOptimizationLevel:"), optimizationLevel);
    }

    public long compileSymbolVisibility() {
        return sendLong(id, "compileSymbolVisibility");
    }

    @SneakyThrows
    public void setCompileSymbolVisibility(long compileSymbolVisibility) {
        L.invokeExact(id, ObjC.sel("setCompileSymbolVisibility:"), compileSymbolVisibility);
    }

    public boolean allowReferencingUndefinedSymbols() {
        return sendBool(id, "allowReferencingUndefinedSymbols");
    }

    @SneakyThrows
    public void setAllowReferencingUndefinedSymbols(boolean allowReferencingUndefinedSymbols) {
        B.invokeExact(id, ObjC.sel("setAllowReferencingUndefinedSymbols:"), allowReferencingUndefinedSymbols);
    }

    public long maxTotalThreadsPerThreadgroup() {
        return sendLong(id, "maxTotalThreadsPerThreadgroup");
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerThreadgroup(long maxTotalThreadsPerThreadgroup) {
        L.invokeExact(id, ObjC.sel("setMaxTotalThreadsPerThreadgroup:"), maxTotalThreadsPerThreadgroup);
    }

    public boolean enableLogging() {
        return sendBool(id, "enableLogging");
    }

    @SneakyThrows
    public void setEnableLogging(boolean enableLogging) {
        B.invokeExact(id, ObjC.sel("setEnableLogging:"), enableLogging);
    }

    public NSArray libraries() {
        return NSArray.of(sendPtr(id, "libraries"));
    }

    @SneakyThrows
    public void setLibraries(NSArray libraries) {
        P.invokeExact(id, ObjC.sel("setLibraries:"), libraries.getId());
    }
}
