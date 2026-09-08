package dev.dov.metalj.libraries;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MTLBinaryArchiveError {
    public final long MTLBinaryArchiveErrorNone = 0;
    public final long MTLBinaryArchiveErrorInvalidFile = 1;
    public final long MTLBinaryArchiveErrorUnexpectedElement = 2;
    public final long MTLBinaryArchiveErrorCompilationFailure = 3;
    public final long MTLBinaryArchiveErrorInternalError = 4;
}
