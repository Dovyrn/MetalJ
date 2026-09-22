package dev.dov.metalj.pipelines.shaders;

import dev.dov.metalj.objc.NSArray;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLCompileOptions extends NSObject {
    private static final long MTL_COMPILE_OPTIONS = ObjC.cls("MTLCompileOptions");

    private static final long ALLOW_REFERENCING_UNDEFINED_SYMBOLS = ObjC.sel("allowReferencingUndefinedSymbols");
    private static final long COMPILE_SYMBOL_VISIBILITY = ObjC.sel("compileSymbolVisibility");
    private static final long ENABLE_LOGGING = ObjC.sel("enableLogging");
    private static final long FAST_MATH_ENABLED = ObjC.sel("fastMathEnabled");
    private static final long INSTALL_NAME = ObjC.sel("installName");
    private static final long LANGUAGE_VERSION = ObjC.sel("languageVersion");
    private static final long LIBRARIES = ObjC.sel("libraries");
    private static final long LIBRARY_TYPE = ObjC.sel("libraryType");
    private static final long MATH_FLOATING_POINT_FUNCTIONS = ObjC.sel("mathFloatingPointFunctions");
    private static final long MATH_MODE = ObjC.sel("mathMode");
    private static final long MAX_TOTAL_THREADS_PER_THREADGROUP = ObjC.sel("maxTotalThreadsPerThreadgroup");
    private static final long NEW = ObjC.sel("new");
    private static final long OPTIMIZATION_LEVEL = ObjC.sel("optimizationLevel");
    private static final long PRESERVE_INVARIANCE = ObjC.sel("preserveInvariance");
    private static final long SET_ALLOW_REFERENCING_UNDEFINED_SYMBOLS = ObjC.sel("setAllowReferencingUndefinedSymbols:");
    private static final long SET_COMPILE_SYMBOL_VISIBILITY = ObjC.sel("setCompileSymbolVisibility:");
    private static final long SET_ENABLE_LOGGING = ObjC.sel("setEnableLogging:");
    private static final long SET_FAST_MATH_ENABLED = ObjC.sel("setFastMathEnabled:");
    private static final long SET_INSTALL_NAME = ObjC.sel("setInstallName:");
    private static final long SET_LANGUAGE_VERSION = ObjC.sel("setLanguageVersion:");
    private static final long SET_LIBRARIES = ObjC.sel("setLibraries:");
    private static final long SET_LIBRARY_TYPE = ObjC.sel("setLibraryType:");
    private static final long SET_MATH_FLOATING_POINT_FUNCTIONS = ObjC.sel("setMathFloatingPointFunctions:");
    private static final long SET_MATH_MODE = ObjC.sel("setMathMode:");
    private static final long SET_MAX_TOTAL_THREADS_PER_THREADGROUP = ObjC.sel("setMaxTotalThreadsPerThreadgroup:");
    private static final long SET_OPTIMIZATION_LEVEL = ObjC.sel("setOptimizationLevel:");
    private static final long SET_PRESERVE_INVARIANCE = ObjC.sel("setPreserveInvariance:");

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
        return new MTLCompileOptions(sendPtr(MTL_COMPILE_OPTIONS, NEW));
    }

    public long languageVersion() {
        return sendLong(id, LANGUAGE_VERSION);
    }

    @SneakyThrows
    public void setLanguageVersion(long languageVersion) {
        L.invokeExact(id, SET_LANGUAGE_VERSION, languageVersion);
    }

    public boolean fastMathEnabled() {
        return sendBool(id, FAST_MATH_ENABLED);
    }

    @SneakyThrows
    public void setFastMathEnabled(boolean fastMathEnabled) {
        B.invokeExact(id, SET_FAST_MATH_ENABLED, fastMathEnabled);
    }

    public long mathMode() {
        return sendLong(id, MATH_MODE);
    }

    @SneakyThrows
    public void setMathMode(long mathMode) {
        L.invokeExact(id, SET_MATH_MODE, mathMode);
    }

    public long mathFloatingPointFunctions() {
        return sendLong(id, MATH_FLOATING_POINT_FUNCTIONS);
    }

    @SneakyThrows
    public void setMathFloatingPointFunctions(long mathFloatingPointFunctions) {
        L.invokeExact(id, SET_MATH_FLOATING_POINT_FUNCTIONS, mathFloatingPointFunctions);
    }

    public long libraryType() {
        return sendLong(id, LIBRARY_TYPE);
    }

    @SneakyThrows
    public void setLibraryType(long libraryType) {
        L.invokeExact(id, SET_LIBRARY_TYPE, libraryType);
    }

    public NSString installName() {
        return NSString.of(sendPtr(id, INSTALL_NAME));
    }

    @SneakyThrows
    public void setInstallName(NSString installName) {
        P.invokeExact(id, SET_INSTALL_NAME, installName.getId());
    }

    public boolean preserveInvariance() {
        return sendBool(id, PRESERVE_INVARIANCE);
    }

    @SneakyThrows
    public void setPreserveInvariance(boolean preserveInvariance) {
        B.invokeExact(id, SET_PRESERVE_INVARIANCE, preserveInvariance);
    }

    public long optimizationLevel() {
        return sendLong(id, OPTIMIZATION_LEVEL);
    }

    @SneakyThrows
    public void setOptimizationLevel(long optimizationLevel) {
        L.invokeExact(id, SET_OPTIMIZATION_LEVEL, optimizationLevel);
    }

    public long compileSymbolVisibility() {
        return sendLong(id, COMPILE_SYMBOL_VISIBILITY);
    }

    @SneakyThrows
    public void setCompileSymbolVisibility(long compileSymbolVisibility) {
        L.invokeExact(id, SET_COMPILE_SYMBOL_VISIBILITY, compileSymbolVisibility);
    }

    public boolean allowReferencingUndefinedSymbols() {
        return sendBool(id, ALLOW_REFERENCING_UNDEFINED_SYMBOLS);
    }

    @SneakyThrows
    public void setAllowReferencingUndefinedSymbols(boolean allowReferencingUndefinedSymbols) {
        B.invokeExact(id, SET_ALLOW_REFERENCING_UNDEFINED_SYMBOLS, allowReferencingUndefinedSymbols);
    }

    public long maxTotalThreadsPerThreadgroup() {
        return sendLong(id, MAX_TOTAL_THREADS_PER_THREADGROUP);
    }

    @SneakyThrows
    public void setMaxTotalThreadsPerThreadgroup(long maxTotalThreadsPerThreadgroup) {
        L.invokeExact(id, SET_MAX_TOTAL_THREADS_PER_THREADGROUP, maxTotalThreadsPerThreadgroup);
    }

    public boolean enableLogging() {
        return sendBool(id, ENABLE_LOGGING);
    }

    @SneakyThrows
    public void setEnableLogging(boolean enableLogging) {
        B.invokeExact(id, SET_ENABLE_LOGGING, enableLogging);
    }

    public NSArray libraries() {
        return NSArray.of(sendPtr(id, LIBRARIES));
    }

    @SneakyThrows
    public void setLibraries(NSArray libraries) {
        P.invokeExact(id, SET_LIBRARIES, libraries.getId());
    }
}
