package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.residency.MTLResidencySetDescriptor;
import dev.dov.metalj.resources.MTLResourceOptions;
import org.junit.jupiter.api.Test;

class ResidencyTest {
    @Test
    void allocations() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var descriptor = MTLResidencySetDescriptor.new_();
        descriptor.setLabel(NSString.stringWithUTF8String("world"));
        descriptor.setInitialCapacity(4);
        assertEquals("world", descriptor.label().UTF8String());
        var set = device.newResidencySetWithDescriptor(descriptor);
        var buffer = device.newBufferWithLength(4096, MTLResourceOptions.MTLResourceStorageModeShared);
        set.addAllocation(buffer);
        set.commit();
        assertEquals(1, set.allocationCount());
        assertTrue(set.containsAllocation(buffer));
        assertTrue(set.allocatedSize() >= 4096);
        set.requestResidency();
        device.newCommandQueue().addResidencySet(set);
        set.removeAllocation(buffer);
        set.commit();
        assertFalse(set.containsAllocation(buffer));
        assertEquals(0, set.allocationCount());
        set.endResidency();
    }
}
