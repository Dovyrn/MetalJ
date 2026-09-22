package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.dov.metalj.objc.Block;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;

class ObjCTest {
    private static final long INIT = ObjC.sel("init");

    @Test
    void string() {
        assertEquals("hello metal", NSString.stringWithUTF8String("hello metal").UTF8String());
    }

    @Test
    void object() {
        long object = NSObject.sendPtr(NSObject.alloc("NSObject"), INIT);
        assertTrue(new NSObject(object) {}.description().startsWith("<NSObject"));
    }

    @Test
    void block() throws Throwable {
        var ran = new AtomicBoolean();
        var block = Block.once(() -> ran.set(true));
        var literal = MemorySegment.ofAddress(block.address()).reinterpret(32);
        var invoke = ObjC.LINKER.downcallHandle(MemorySegment.ofAddress(literal.get(ObjC.PTR, 16)),
                FunctionDescriptor.ofVoid(ValueLayout.ADDRESS, ValueLayout.ADDRESS));
        invoke.invokeExact(literal, MemorySegment.NULL);
        assertTrue(ran.get());
        block.close();
    }
}
