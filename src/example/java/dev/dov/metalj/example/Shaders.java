package dev.dov.metalj.example;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.pipelines.shaders.MTLCompileOptions;
import dev.dov.metalj.pipelines.shaders.MTLLanguageVersion;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;

public class Shaders {
    @SneakyThrows
    public static void main(String[] args) {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var options = MTLCompileOptions.new_();
        options.setLanguageVersion(MTLLanguageVersion.MTLLanguageVersion3_1);
        var library = device.newLibraryWithSource(NSString.stringWithUTF8String(source()), options);
        var names = library.functionNames();
        for (long i = 0; i < names.count(); i++) {
            var name = NSString.of(names.objectAtIndex(i));
            var function = library.newFunctionWithName(name);
            System.out.println(name.UTF8String() + " type " + function.functionType());
        }
    }

    @SneakyThrows
    private static String source() {
        try (var stream = Shaders.class.getResourceAsStream("/kernel.metal")) {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
