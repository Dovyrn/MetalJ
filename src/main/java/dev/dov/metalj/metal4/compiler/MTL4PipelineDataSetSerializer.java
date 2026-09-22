package dev.dov.metalj.metal4.compiler;

import dev.dov.metalj.objc.NSData;
import dev.dov.metalj.objc.NSError;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSURL;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.Arena;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4PipelineDataSetSerializer extends NSObject {
    private static final long SERIALIZE_AS_ARCHIVE_AND_FLUSH_TO_URL_ERROR = ObjC.sel("serializeAsArchiveAndFlushToURL:error:");
    private static final long SERIALIZE_AS_PIPELINES_SCRIPT_WITH_ERROR = ObjC.sel("serializeAsPipelinesScriptWithError:");

    private static final MethodHandle B_PA = handle(ObjC.BOOL, ObjC.PTR, ValueLayout.ADDRESS);
    private static final MethodHandle P_A = handle(ObjC.PTR, ValueLayout.ADDRESS);

    private MTL4PipelineDataSetSerializer(long id) {
        super(id);
    }

    public static MTL4PipelineDataSetSerializer of(long id) {
        return new MTL4PipelineDataSetSerializer(id);
    }

    @SneakyThrows
    public boolean serializeAsArchiveAndFlushToURL(NSURL url) {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            boolean done = (boolean) B_PA.invokeExact(id, SERIALIZE_AS_ARCHIVE_AND_FLUSH_TO_URL_ERROR,
                    url.getId(), error);
            NSError.check(error, "serializeAsArchiveAndFlushToURL:error:");
            return done;
        }
    }

    @SneakyThrows
    public NSData serializeAsPipelinesScript() {
        try (var arena = Arena.ofConfined()) {
            var error = NSError.slot(arena);
            long data = (long) P_A.invokeExact(id, SERIALIZE_AS_PIPELINES_SCRIPT_WITH_ERROR, error);
            NSError.check(error, "serializeAsPipelinesScriptWithError:");
            return NSData.of(data);
        }
    }
}
