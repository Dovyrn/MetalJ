package dev.dov.metalj.arguments;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.NSString;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.compute.MTLComputePipelineState;
import dev.dov.metalj.raytracing.MTLAccelerationStructure;
import dev.dov.metalj.raytracing.MTLIntersectionFunctionTable;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineState;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.indirect.MTLIndirectCommandBuffer;
import dev.dov.metalj.resources.samplers.MTLSamplerState;
import dev.dov.metalj.resources.textures.MTLTexture;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLArgumentEncoder extends NSObject {
    private static final MethodHandle P = handle(null, ObjC.PTR);
    private static final MethodHandle PL = handle(null, ObjC.PTR, ObjC.LONG);
    private static final MethodHandle PLL = handle(null, ObjC.PTR, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle AR = handle(null, ValueLayout.ADDRESS, NSRange.LAYOUT);
    private static final MethodHandle AAR = handle(null, ValueLayout.ADDRESS, ValueLayout.ADDRESS, NSRange.LAYOUT);
    private static final MethodHandle A_L = handle(ValueLayout.ADDRESS, ObjC.LONG);
    private static final MethodHandle P_L = handle(ObjC.PTR, ObjC.LONG);

    private MTLArgumentEncoder(long id) {
        super(id);
    }

    public static MTLArgumentEncoder of(long id) {
        return new MTLArgumentEncoder(id);
    }

    public MTLDevice device() {
        return MTLDevice.of(sendPtr(id, "device"));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, "label"));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, ObjC.sel("setLabel:"), label.getId());
    }

    public long encodedLength() {
        return sendLong(id, "encodedLength");
    }

    public long alignment() {
        return sendLong(id, "alignment");
    }

    @SneakyThrows
    public void setArgumentBuffer(MTLBuffer buffer, long offset) {
        PL.invokeExact(id, ObjC.sel("setArgumentBuffer:offset:"), buffer.getId(), offset);
    }

    @SneakyThrows
    public void setArgumentBuffer(MTLBuffer buffer, long startOffset, long arrayElement) {
        PLL.invokeExact(id, ObjC.sel("setArgumentBuffer:startOffset:arrayElement:"), buffer.getId(), startOffset,
                arrayElement);
    }

    @SneakyThrows
    public void setBuffer(MTLBuffer buffer, long offset, long index) {
        PLL.invokeExact(id, ObjC.sel("setBuffer:offset:atIndex:"), buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, ObjC.sel("setBuffers:offsets:withRange:"), buffers, offsets, range);
    }

    @SneakyThrows
    public void setTexture(MTLTexture texture, long index) {
        PL.invokeExact(id, ObjC.sel("setTexture:atIndex:"), texture.getId(), index);
    }

    @SneakyThrows
    public void setTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setTextures:withRange:"), textures, range);
    }

    @SneakyThrows
    public void setSamplerState(MTLSamplerState sampler, long index) {
        PL.invokeExact(id, ObjC.sel("setSamplerState:atIndex:"), sampler.getId(), index);
    }

    @SneakyThrows
    public void setSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setSamplerStates:withRange:"), samplers, range);
    }

    @SneakyThrows
    public MemorySegment constantDataAtIndex(long index) {
        return (MemorySegment) A_L.invokeExact(id, ObjC.sel("constantDataAtIndex:"), index);
    }

    @SneakyThrows
    public void setRenderPipelineState(MTLRenderPipelineState pipeline, long index) {
        PL.invokeExact(id, ObjC.sel("setRenderPipelineState:atIndex:"), pipeline.getId(), index);
    }

    @SneakyThrows
    public void setComputePipelineState(MTLComputePipelineState pipeline, long index) {
        PL.invokeExact(id, ObjC.sel("setComputePipelineState:atIndex:"), pipeline.getId(), index);
    }

    @SneakyThrows
    public void setIndirectCommandBuffer(MTLIndirectCommandBuffer buffer, long index) {
        PL.invokeExact(id, ObjC.sel("setIndirectCommandBuffer:atIndex:"), buffer.getId(), index);
    }

    @SneakyThrows
    public MTLArgumentEncoder newArgumentEncoderForBufferAtIndex(long index) {
        return of((long) P_L.invokeExact(id, ObjC.sel("newArgumentEncoderForBufferAtIndex:"), index));
    }

    @SneakyThrows
    public void setAccelerationStructure(MTLAccelerationStructure structure, long index) {
        PL.invokeExact(id, ObjC.sel("setAccelerationStructure:atIndex:"), structure.getId(), index);
    }

    @SneakyThrows
    public void setIntersectionFunctionTable(MTLIntersectionFunctionTable table, long index) {
        PL.invokeExact(id, ObjC.sel("setIntersectionFunctionTable:atIndex:"), table.getId(), index);
    }
}
