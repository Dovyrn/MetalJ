package dev.dov.metalj.objc;

import dev.dov.metalj.device.Metal;
import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjC {
    public final Linker LINKER = Linker.nativeLinker();
    public final Arena ARENA = Arena.global();
    public final ValueLayout.OfLong LONG = ValueLayout.JAVA_LONG;
    public final ValueLayout.OfInt INT = ValueLayout.JAVA_INT;
    public final ValueLayout.OfDouble DOUBLE = ValueLayout.JAVA_DOUBLE;
    public final ValueLayout.OfFloat FLOAT = ValueLayout.JAVA_FLOAT;
    public final ValueLayout.OfBoolean BOOL = ValueLayout.JAVA_BOOLEAN;
    public final ValueLayout.OfLong PTR = ValueLayout.JAVA_LONG;

    private final SymbolLookup RUNTIME = SymbolLookup.libraryLookup("/usr/lib/libobjc.A.dylib", ARENA);
    private final SymbolLookup FOUNDATION = framework("Foundation");
    private final SymbolLookup METAL = framework("Metal");
    private final SymbolLookup QUARTZ = framework("QuartzCore");
    private final SymbolLookup APPKIT = framework("AppKit");

    private final MethodHandle SEL = downcall(RUNTIME, "sel_registerName",
            FunctionDescriptor.of(PTR, ValueLayout.ADDRESS));
    private final MethodHandle CLASS = downcall(RUNTIME, "objc_getClass",
            FunctionDescriptor.of(PTR, ValueLayout.ADDRESS));
    private final MemorySegment SEND = RUNTIME.find("objc_msgSend").orElseThrow();
    private final MemorySegment SEND_STRET = RUNTIME.find("objc_msgSend_stret").orElse(SEND);
    private final boolean ARM = System.getProperty("os.arch").contains("aarch64");

    private final Map<String, Long> selectors = new ConcurrentHashMap<>();
    private final Map<String, Long> classes = new ConcurrentHashMap<>();
    private final Map<FunctionDescriptor, MethodHandle> sends = new ConcurrentHashMap<>();
    private final Map<FunctionDescriptor, MethodHandle> strets = new ConcurrentHashMap<>();

    private SymbolLookup framework(String name) {
        return SymbolLookup.libraryLookup("/System/Library/Frameworks/" + name + ".framework/" + name, ARENA);
    }

    public MethodHandle downcall(SymbolLookup lookup, String name, FunctionDescriptor descriptor) {
        return LINKER.downcallHandle(lookup.find(name).orElseThrow(), descriptor);
    }

    public MemorySegment symbol(String name) {
        for (var lookup : new SymbolLookup[] {METAL, QUARTZ, FOUNDATION, APPKIT}) {
            var found = lookup.find(name);
            if (found.isPresent()) {
                return found.get();
            }
        }
        throw new IllegalArgumentException("no symbol " + name);
    }

    public MethodHandle function(String name, FunctionDescriptor descriptor) {
        return LINKER.downcallHandle(symbol(name), descriptor);
    }

    @SneakyThrows
    public long sel(String name) {
        return selectors.computeIfAbsent(name, n -> {
            try (var arena = Arena.ofConfined()) {
                return (long) SEL.invokeExact(arena.allocateFrom(n));
            } catch (Throwable t) {
                throw new IllegalStateException(t);
            }
        });
    }

    @SneakyThrows
    public long cls(String name) {
        return classes.computeIfAbsent(name, n -> {
            try (var arena = Arena.ofConfined()) {
                long found = (long) CLASS.invokeExact(arena.allocateFrom(n));
                if (found == 0) {
                    throw new IllegalArgumentException("no class " + n);
                }
                return found;
            } catch (Throwable t) {
                throw new IllegalStateException(t);
            }
        });
    }

    public MethodHandle send(FunctionDescriptor descriptor) {
        return sends.computeIfAbsent(descriptor, d -> LINKER.downcallHandle(SEND, d));
    }

    public MethodHandle sendStruct(FunctionDescriptor descriptor) {
        var large = descriptor.returnLayout().map(MemoryLayout::byteSize).orElse(0L) > 16;
        var target = !ARM && large ? SEND_STRET : SEND;
        return strets.computeIfAbsent(descriptor, d -> LINKER.downcallHandle(target, d));
    }

    public FunctionDescriptor of(MemoryLayout result, MemoryLayout... args) {
        var layouts = new MemoryLayout[args.length + 2];
        layouts[0] = PTR;
        layouts[1] = PTR;
        System.arraycopy(args, 0, layouts, 2, args.length);
        return result == null ? FunctionDescriptor.ofVoid(layouts) : FunctionDescriptor.of(result, layouts);
    }
}
