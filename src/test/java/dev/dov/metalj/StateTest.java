package dev.dov.metalj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.dov.metalj.commands.passes.MTLRenderPassDescriptor;
import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.device.Metal;
import dev.dov.metalj.device.NSProcessInfo;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.pipelines.compute.MTLStageInputOutputDescriptor;
import dev.dov.metalj.pipelines.compute.MTLStepFunction;
import dev.dov.metalj.pipelines.vertex.MTLAttributeFormat;
import dev.dov.metalj.pools.MTLResourceViewPoolDescriptor;
import dev.dov.metalj.resources.MTLResourceOptions;
import dev.dov.metalj.resources.textures.MTLPixelFormat;
import dev.dov.metalj.resources.textures.MTLTextureDescriptor;
import dev.dov.metalj.state.MTLResourceStatePassDescriptor;
import dev.dov.metalj.tensors.MTLTensorDataType;
import dev.dov.metalj.tensors.MTLTensorDescriptor;
import dev.dov.metalj.tensors.MTLTensorExtents;
import dev.dov.metalj.tensors.MTLTensorUsage;
import org.junit.jupiter.api.Test;

class StateTest {
    @Test
    void encoders() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var queue = device.newCommandQueue();
        var cmd = queue.commandBuffer();
        var parallel = cmd.parallelRenderCommandEncoderWithDescriptor(pass(device));
        var first = parallel.renderCommandEncoder();
        var second = parallel.renderCommandEncoder();
        assertFalse(first.equals(second));
        first.endEncoding();
        second.endEncoding();
        parallel.endEncoding();
        var state = cmd.resourceStateCommandEncoderWithDescriptor(
                MTLResourceStatePassDescriptor.resourceStatePassDescriptor());
        state.endEncoding();
        cmd.commit();
        cmd.waitUntilCompleted();
    }

    private static MTLRenderPassDescriptor pass(MTLDevice device) {
        var descriptor = MTLTextureDescriptor.texture2DDescriptorWithPixelFormat(
                MTLPixelFormat.MTLPixelFormatBGRA8Unorm, 64, 64, false);
        descriptor.setUsage(4);
        var pass = MTLRenderPassDescriptor.renderPassDescriptor();
        pass.colorAttachments()
                .objectAtIndexedSubscript(0)
                .setTexture(device.newTextureWithDescriptor(descriptor));
        return pass;
    }

    @Test
    void stageInput() {
        var descriptor = MTLStageInputOutputDescriptor.stageInputOutputDescriptor();
        descriptor.layouts()
                .objectAtIndexedSubscript(0)
                .setStride(16);
        descriptor.layouts()
                .objectAtIndexedSubscript(0)
                .setStepFunction(MTLStepFunction.MTLStepFunctionThreadPositionInGridX);
        descriptor.attributes()
                .objectAtIndexedSubscript(0)
                .setFormat(MTLAttributeFormat.MTLAttributeFormatFloat3);
        assertEquals(16, descriptor.layouts().objectAtIndexedSubscript(0).stride());
        assertEquals(MTLAttributeFormat.MTLAttributeFormatFloat3,
                descriptor.attributes().objectAtIndexedSubscript(0).format());
    }

    @Test
    void tensor() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var descriptor = MTLTensorDescriptor.new_();
        descriptor.setDimensions(MTLTensorExtents.initWithRank(4, 8));
        descriptor.setDataType(MTLTensorDataType.MTLTensorDataTypeFloat32);
        descriptor.setUsage(MTLTensorUsage.MTLTensorUsageCompute);
        descriptor.setResourceOptions(MTLResourceOptions.MTLResourceStorageModeShared);
        var tensor = device.newTensorWithDescriptor(descriptor);
        assertEquals(2, tensor.dimensions().rank());
        assertEquals(8, tensor.dimensions().extentAtDimensionIndex(1));
        assertTrue(tensor.gpuResourceID() != 0);
    }

    @Test
    void pool() {
        var device = Metal.MTLCreateSystemDefaultDevice();
        var descriptor = MTLResourceViewPoolDescriptor.new_();
        descriptor.setResourceViewCount(4);
        descriptor.setLabel(NSString.stringWithUTF8String("views"));
        var pool = device.newTextureViewPoolWithDescriptor(descriptor);
        assertEquals(4, pool.resourceViewCount());
        var texture = MTLTextureDescriptor.texture2DDescriptorWithPixelFormat(
                MTLPixelFormat.MTLPixelFormatRGBA8Unorm, 8, 8, false);
        assertTrue(pool.setTextureView(device.newTextureWithDescriptor(texture), 0) != 0);
    }

    @Test
    void certification() {
        assertTrue(NSProcessInfo.processInfo().hasPerformanceProfile(
                NSProcessInfo.NSProcessPerformanceProfileDefault)
                || !NSProcessInfo.processInfo().hasPerformanceProfile(
                        NSProcessInfo.NSProcessPerformanceProfileSustained));
    }
}
