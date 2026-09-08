package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import dev.dov.metalj.device.Metal;
import dev.dov.metalj.objc.NSNumber;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.rate.MTLCoordinate2D;
import dev.dov.metalj.rate.MTLRasterizationRateLayerDescriptor;
import dev.dov.metalj.rate.MTLRasterizationRateMapDescriptor;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.heaps.MTLSizeAndAlign;
import java.lang.foreign.Arena;
import org.junit.jupiter.api.Test;

class RateTest {
    @Test
    void map() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        assumeTrue(device.supportsRasterizationRateMapWithLayerCount(1));
        try (var arena = Arena.ofConfined()) {
            var layer = MTLRasterizationRateLayerDescriptor.initWithSampleCount(MTLSize.of(arena, 4, 4, 0));
            for (long i = 0; i < 4; i++) {
                layer.horizontal().setObjectAtIndexedSubscript(NSNumber.numberWithFloat(i < 2 ? 1 : 0.25f), i);
                layer.vertical().setObjectAtIndexedSubscript(NSNumber.numberWithFloat(i < 2 ? 1 : 0.25f), i);
            }
            assertEquals(0.25f, layer.horizontal().objectAtIndexedSubscript(3).floatValue());
            var descriptor = MTLRasterizationRateMapDescriptor.rasterizationRateMapDescriptor(
                    MTLSize.of(arena, 960, 540, 0), layer);
            assertEquals(1, descriptor.layerCount());
            var map = device.newRasterizationRateMapWithDescriptor(descriptor);
            assertEquals(960, map.screenSize(arena).get(ObjC.LONG, 0));
            var physical = map.physicalSizeForLayer(arena, 0);
            assertTrue(physical.get(ObjC.LONG, 0) < 960);
            var sizes = map.parameterBufferSizeAndAlign(arena);
            var bytes = sizes.get(ObjC.LONG, 0);
            assertTrue(bytes > 0);
            var buffer = device.newBufferWithLength(bytes, MTLResourceOptions.MTLResourceStorageModeShared);
            map.copyParameterDataToBuffer(buffer, 0);
            var screen = MTLCoordinate2D.of(arena, 480, 270);
            var mapped = map.mapScreenToPhysicalCoordinates(arena, screen, 0);
            assertTrue(MTLCoordinate2D.x(mapped) > 0);
            var back = map.mapPhysicalToScreenCoordinates(arena, mapped, 0);
            assertEquals(480, MTLCoordinate2D.x(back), 1);
        }
    }
}
