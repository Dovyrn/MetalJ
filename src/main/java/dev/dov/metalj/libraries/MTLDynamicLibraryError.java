package dev.dov.metalj.libraries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLDynamicLibraryError {
    public final long MTLDynamicLibraryErrorNone = 0;
    public final long MTLDynamicLibraryErrorInvalidFile = 1;
    public final long MTLDynamicLibraryErrorCompilationFailure = 2;
    public final long MTLDynamicLibraryErrorUnresolvedInstallName = 3;
    public final long MTLDynamicLibraryErrorDependencyLoadFailure = 4;
    public final long MTLDynamicLibraryErrorUnsupported = 5;
}
