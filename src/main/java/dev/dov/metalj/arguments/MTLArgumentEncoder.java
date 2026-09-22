package dev.dov.metalj.arguments;

import dev.dov.metalj.device.MTLDevice;
import dev.dov.metalj.functions.MTLVisibleFunctionTable;
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
    private static final long ALIGNMENT = ObjC.sel("alignment");
    private static final long CONSTANT_DATA_AT_INDEX = ObjC.sel("constantDataAtIndex:");
    private static final long DEVICE = ObjC.sel("device");
    private static final long ENCODED_LENGTH = ObjC.sel("encodedLength");
    private static final long LABEL = ObjC.sel("label");
    private static final long NEW_ARGUMENT_ENCODER_FOR_BUFFER_AT_INDEX = ObjC.sel("newArgumentEncoderForBufferAtIndex:");
    private static final long SET_ACCELERATION_STRUCTURE_AT_INDEX = ObjC.sel("setAccelerationStructure:atIndex:");
    private static final long SET_ARGUMENT_BUFFER_OFFSET = ObjC.sel("setArgumentBuffer:offset:");
    private static final long SET_ARGUMENT_BUFFER_START_OFFSET_ARRAY_ELEMENT = ObjC.sel("setArgumentBuffer:startOffset:arrayElement:");
    private static final long SET_BUFFER_OFFSET_AT_INDEX = ObjC.sel("setBuffer:offset:atIndex:");
    private static final long SET_BUFFERS_OFFSETS_WITH_RANGE = ObjC.sel("setBuffers:offsets:withRange:");
    private static final long SET_COMPUTE_PIPELINE_STATE_AT_INDEX = ObjC.sel("setComputePipelineState:atIndex:");
    private static final long SET_INDIRECT_COMMAND_BUFFER_AT_INDEX = ObjC.sel("setIndirectCommandBuffer:atIndex:");
    private static final long SET_INTERSECTION_FUNCTION_TABLE_AT_INDEX = ObjC.sel("setIntersectionFunctionTable:atIndex:");
    private static final long SET_LABEL = ObjC.sel("setLabel:");
    private static final long SET_RENDER_PIPELINE_STATE_AT_INDEX = ObjC.sel("setRenderPipelineState:atIndex:");
    private static final long SET_SAMPLER_STATE_AT_INDEX = ObjC.sel("setSamplerState:atIndex:");
    private static final long SET_SAMPLER_STATES_WITH_RANGE = ObjC.sel("setSamplerStates:withRange:");
    private static final long SET_TEXTURE_AT_INDEX = ObjC.sel("setTexture:atIndex:");
    private static final long SET_TEXTURES_WITH_RANGE = ObjC.sel("setTextures:withRange:");
    private static final long SET_VISIBLE_FUNCTION_TABLE_AT_INDEX = ObjC.sel("setVisibleFunctionTable:atIndex:");

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
        return MTLDevice.of(sendPtr(id, DEVICE));
    }

    public NSString label() {
        return NSString.of(sendPtr(id, LABEL));
    }

    @SneakyThrows
    public void setLabel(NSString label) {
        P.invokeExact(id, SET_LABEL, label.getId());
    }

    public long encodedLength() {
        return sendLong(id, ENCODED_LENGTH);
    }

    public long alignment() {
        return sendLong(id, ALIGNMENT);
    }

    @SneakyThrows
    public void setArgumentBuffer(MTLBuffer buffer, long offset) {
        PL.invokeExact(id, SET_ARGUMENT_BUFFER_OFFSET, buffer.getId(), offset);
    }

    @SneakyThrows
    public void setArgumentBuffer(MTLBuffer buffer, long startOffset, long arrayElement) {
        PLL.invokeExact(id, SET_ARGUMENT_BUFFER_START_OFFSET_ARRAY_ELEMENT, buffer.getId(), startOffset,
                arrayElement);
    }

    @SneakyThrows
    public void setBuffer(MTLBuffer buffer, long offset, long index) {
        PLL.invokeExact(id, SET_BUFFER_OFFSET_AT_INDEX, buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, SET_BUFFERS_OFFSETS_WITH_RANGE, buffers, offsets, range);
    }

    @SneakyThrows
    public void setTexture(MTLTexture texture, long index) {
        PL.invokeExact(id, SET_TEXTURE_AT_INDEX, texture.getId(), index);
    }

    @SneakyThrows
    public void setTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, SET_TEXTURES_WITH_RANGE, textures, range);
    }

    @SneakyThrows
    public void setSamplerState(MTLSamplerState sampler, long index) {
        PL.invokeExact(id, SET_SAMPLER_STATE_AT_INDEX, sampler.getId(), index);
    }

    @SneakyThrows
    public void setSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, SET_SAMPLER_STATES_WITH_RANGE, samplers, range);
    }

    @SneakyThrows
    public MemorySegment constantDataAtIndex(long index) {
        return (MemorySegment) A_L.invokeExact(id, CONSTANT_DATA_AT_INDEX, index);
    }

    @SneakyThrows
    public void setRenderPipelineState(MTLRenderPipelineState pipeline, long index) {
        PL.invokeExact(id, SET_RENDER_PIPELINE_STATE_AT_INDEX, pipeline.getId(), index);
    }

    @SneakyThrows
    public void setComputePipelineState(MTLComputePipelineState pipeline, long index) {
        PL.invokeExact(id, SET_COMPUTE_PIPELINE_STATE_AT_INDEX, pipeline.getId(), index);
    }

    @SneakyThrows
    public void setIndirectCommandBuffer(MTLIndirectCommandBuffer buffer, long index) {
        PL.invokeExact(id, SET_INDIRECT_COMMAND_BUFFER_AT_INDEX, buffer.getId(), index);
    }

    @SneakyThrows
    public MTLArgumentEncoder newArgumentEncoderForBufferAtIndex(long index) {
        return of((long) P_L.invokeExact(id, NEW_ARGUMENT_ENCODER_FOR_BUFFER_AT_INDEX, index));
    }

    @SneakyThrows
    public void setAccelerationStructure(MTLAccelerationStructure structure, long index) {
        PL.invokeExact(id, SET_ACCELERATION_STRUCTURE_AT_INDEX, structure.getId(), index);
    }

    @SneakyThrows
    public void setIntersectionFunctionTable(MTLIntersectionFunctionTable table, long index) {
        PL.invokeExact(id, SET_INTERSECTION_FUNCTION_TABLE_AT_INDEX, table.getId(), index);
    }

    @SneakyThrows
    public void setVisibleFunctionTable(MTLVisibleFunctionTable table, long index) {
        PL.invokeExact(id, SET_VISIBLE_FUNCTION_TABLE_AT_INDEX, table.getId(), index);
    }
}
