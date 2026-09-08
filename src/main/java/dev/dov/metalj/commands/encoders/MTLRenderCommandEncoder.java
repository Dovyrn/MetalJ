package dev.dov.metalj.commands.encoders;

import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.raytracing.MTLAccelerationStructure;
import dev.dov.metalj.raytracing.MTLIntersectionFunctionTable;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.depth.MTLDepthStencilState;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineState;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.indirect.MTLIndirectCommandBuffer;
import dev.dov.metalj.resources.MTLResource;
import dev.dov.metalj.resources.samplers.MTLSamplerState;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.textures.MTLTexture;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderCommandEncoder extends MTLCommandEncoder {
    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle LL = handle(null, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLLLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            ObjC.LONG);
    private static final MethodHandle LLLLLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLLLLLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLLLLLLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLB = handle(null, ObjC.LONG, ObjC.LONG, ObjC.BOOL);
    private static final MethodHandle LFFL = handle(null, ObjC.LONG, ObjC.FLOAT, ObjC.FLOAT, ObjC.LONG);
    private static final MethodHandle LA = handle(null, ObjC.LONG, ValueLayout.ADDRESS);
    private static final MethodHandle LR = handle(null, ObjC.LONG, NSRange.LAYOUT);
    private static final MethodHandle AL = handle(null, ValueLayout.ADDRESS, ObjC.LONG);
    private static final MethodHandle ALL = handle(null, ValueLayout.ADDRESS, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle ALLL = handle(null, ValueLayout.ADDRESS, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle AR = handle(null, ValueLayout.ADDRESS, NSRange.LAYOUT);
    private static final MethodHandle AAR = handle(null, ValueLayout.ADDRESS, ValueLayout.ADDRESS, NSRange.LAYOUT);
    private static final MethodHandle AAAR = handle(null, ValueLayout.ADDRESS, ValueLayout.ADDRESS,
            ValueLayout.ADDRESS, NSRange.LAYOUT);
    private static final MethodHandle F = handle(null, ObjC.FLOAT);
    private static final MethodHandle FF = handle(null, ObjC.FLOAT, ObjC.FLOAT);
    private static final MethodHandle FFF = handle(null, ObjC.FLOAT, ObjC.FLOAT, ObjC.FLOAT);
    private static final MethodHandle FFFF = handle(null, ObjC.FLOAT, ObjC.FLOAT, ObjC.FLOAT, ObjC.FLOAT);
    private static final MethodHandle I = handle(null, ObjC.INT);
    private static final MethodHandle II = handle(null, ObjC.INT, ObjC.INT);
    private static final MethodHandle VIEWPORT = handle(null, MTLViewport.LAYOUT);
    private static final MethodHandle SCISSOR = handle(null, MTLScissorRect.LAYOUT);
    private static final MethodHandle S = handle(null, MTLSize.LAYOUT);
    private static final MethodHandle SSS = handle(null, MTLSize.LAYOUT, MTLSize.LAYOUT, MTLSize.LAYOUT);
    private static final MethodHandle LLSS = handle(null, ObjC.LONG, ObjC.LONG, MTLSize.LAYOUT, MTLSize.LAYOUT);

    public static final long MTLPrimitiveTypePoint = 0;
    public static final long MTLPrimitiveTypeLine = 1;
    public static final long MTLPrimitiveTypeLineStrip = 2;
    public static final long MTLPrimitiveTypeTriangle = 3;
    public static final long MTLPrimitiveTypeTriangleStrip = 4;
    public static final long MTLIndexTypeUInt16 = 0;
    public static final long MTLIndexTypeUInt32 = 1;
    public static final long MTLCullModeNone = 0;
    public static final long MTLCullModeFront = 1;
    public static final long MTLCullModeBack = 2;
    public static final long MTLWindingClockwise = 0;
    public static final long MTLWindingCounterClockwise = 1;
    public static final long MTLTriangleFillModeFill = 0;
    public static final long MTLTriangleFillModeLines = 1;
    public static final long MTLDepthClipModeClip = 0;
    public static final long MTLDepthClipModeClamp = 1;
    public static final long MTLVisibilityResultModeDisabled = 0;
    public static final long MTLVisibilityResultModeBoolean = 1;
    public static final long MTLVisibilityResultModeCounting = 2;

    private MTLRenderCommandEncoder(long id) {
        super(id);
    }

    public static MTLRenderCommandEncoder of(long id) {
        return new MTLRenderCommandEncoder(id);
    }

    @SneakyThrows
    public void setRenderPipelineState(MTLRenderPipelineState pipelineState) {
        L.invokeExact(id, ObjC.sel("setRenderPipelineState:"), pipelineState.getId());
    }

    @SneakyThrows
    public void setVertexBytes(MemorySegment bytes, long length, long index) {
        ALL.invokeExact(id, ObjC.sel("setVertexBytes:length:atIndex:"), bytes, length, index);
    }

    @SneakyThrows
    public void setVertexBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, ObjC.sel("setVertexBuffer:offset:atIndex:"), buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setVertexBufferOffset(long offset, long index) {
        LL.invokeExact(id, ObjC.sel("setVertexBufferOffset:atIndex:"), offset, index);
    }

    @SneakyThrows
    public void setVertexBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, ObjC.sel("setVertexBuffers:offsets:withRange:"), buffers, offsets, range);
    }

    @SneakyThrows
    public void setVertexTexture(MTLTexture texture, long index) {
        LL.invokeExact(id, ObjC.sel("setVertexTexture:atIndex:"), texture.getId(), index);
    }

    @SneakyThrows
    public void setVertexTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setVertexTextures:withRange:"), textures, range);
    }

    @SneakyThrows
    public void setVertexSamplerState(MTLSamplerState sampler, long index) {
        LL.invokeExact(id, ObjC.sel("setVertexSamplerState:atIndex:"), sampler.getId(), index);
    }

    @SneakyThrows
    public void setVertexSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setVertexSamplerStates:withRange:"), samplers, range);
    }

    @SneakyThrows
    public void setVertexSamplerState(MTLSamplerState sampler, float lodMinClamp, float lodMaxClamp, long index) {
        LFFL.invokeExact(id, ObjC.sel("setVertexSamplerState:lodMinClamp:lodMaxClamp:atIndex:"), sampler.getId(),
                lodMinClamp, lodMaxClamp, index);
    }

    @SneakyThrows
    public void setVertexSamplerStates(MemorySegment samplers, MemorySegment lodMinClamps,
            MemorySegment lodMaxClamps, MemorySegment range) {
        AAAR.invokeExact(id, ObjC.sel("setVertexSamplerStates:lodMinClamps:lodMaxClamps:withRange:"), samplers,
                lodMinClamps, lodMaxClamps, range);
    }

    @SneakyThrows
    public void setFragmentBytes(MemorySegment bytes, long length, long index) {
        ALL.invokeExact(id, ObjC.sel("setFragmentBytes:length:atIndex:"), bytes, length, index);
    }

    @SneakyThrows
    public void setFragmentBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, ObjC.sel("setFragmentBuffer:offset:atIndex:"), buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setFragmentBufferOffset(long offset, long index) {
        LL.invokeExact(id, ObjC.sel("setFragmentBufferOffset:atIndex:"), offset, index);
    }

    @SneakyThrows
    public void setFragmentBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, ObjC.sel("setFragmentBuffers:offsets:withRange:"), buffers, offsets, range);
    }

    @SneakyThrows
    public void setFragmentTexture(MTLTexture texture, long index) {
        LL.invokeExact(id, ObjC.sel("setFragmentTexture:atIndex:"), texture.getId(), index);
    }

    @SneakyThrows
    public void setFragmentTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setFragmentTextures:withRange:"), textures, range);
    }

    @SneakyThrows
    public void setFragmentSamplerState(MTLSamplerState sampler, long index) {
        LL.invokeExact(id, ObjC.sel("setFragmentSamplerState:atIndex:"), sampler.getId(), index);
    }

    @SneakyThrows
    public void setFragmentSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setFragmentSamplerStates:withRange:"), samplers, range);
    }

    @SneakyThrows
    public void setFragmentSamplerState(MTLSamplerState sampler, float lodMinClamp, float lodMaxClamp, long index) {
        LFFL.invokeExact(id, ObjC.sel("setFragmentSamplerState:lodMinClamp:lodMaxClamp:atIndex:"), sampler.getId(),
                lodMinClamp, lodMaxClamp, index);
    }

    @SneakyThrows
    public void setFragmentSamplerStates(MemorySegment samplers, MemorySegment lodMinClamps,
            MemorySegment lodMaxClamps, MemorySegment range) {
        AAAR.invokeExact(id, ObjC.sel("setFragmentSamplerStates:lodMinClamps:lodMaxClamps:withRange:"), samplers,
                lodMinClamps, lodMaxClamps, range);
    }

    @SneakyThrows
    public void setTileBytes(MemorySegment bytes, long length, long index) {
        ALL.invokeExact(id, ObjC.sel("setTileBytes:length:atIndex:"), bytes, length, index);
    }

    @SneakyThrows
    public void setTileBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, ObjC.sel("setTileBuffer:offset:atIndex:"), buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setTileBufferOffset(long offset, long index) {
        LL.invokeExact(id, ObjC.sel("setTileBufferOffset:atIndex:"), offset, index);
    }

    @SneakyThrows
    public void setTileBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, ObjC.sel("setTileBuffers:offsets:withRange:"), buffers, offsets, range);
    }

    @SneakyThrows
    public void setTileTexture(MTLTexture texture, long index) {
        LL.invokeExact(id, ObjC.sel("setTileTexture:atIndex:"), texture.getId(), index);
    }

    @SneakyThrows
    public void setTileTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setTileTextures:withRange:"), textures, range);
    }

    @SneakyThrows
    public void setTileSamplerState(MTLSamplerState sampler, long index) {
        LL.invokeExact(id, ObjC.sel("setTileSamplerState:atIndex:"), sampler.getId(), index);
    }

    @SneakyThrows
    public void setTileSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setTileSamplerStates:withRange:"), samplers, range);
    }

    @SneakyThrows
    public void setTileSamplerState(MTLSamplerState sampler, float lodMinClamp, float lodMaxClamp, long index) {
        LFFL.invokeExact(id, ObjC.sel("setTileSamplerState:lodMinClamp:lodMaxClamp:atIndex:"), sampler.getId(),
                lodMinClamp, lodMaxClamp, index);
    }

    @SneakyThrows
    public void setTileSamplerStates(MemorySegment samplers, MemorySegment lodMinClamps,
            MemorySegment lodMaxClamps, MemorySegment range) {
        AAAR.invokeExact(id, ObjC.sel("setTileSamplerStates:lodMinClamps:lodMaxClamps:withRange:"), samplers,
                lodMinClamps, lodMaxClamps, range);
    }

    @SneakyThrows
    public void setObjectBytes(MemorySegment bytes, long length, long index) {
        ALL.invokeExact(id, ObjC.sel("setObjectBytes:length:atIndex:"), bytes, length, index);
    }

    @SneakyThrows
    public void setObjectBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, ObjC.sel("setObjectBuffer:offset:atIndex:"), buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setObjectBufferOffset(long offset, long index) {
        LL.invokeExact(id, ObjC.sel("setObjectBufferOffset:atIndex:"), offset, index);
    }

    @SneakyThrows
    public void setObjectBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, ObjC.sel("setObjectBuffers:offsets:withRange:"), buffers, offsets, range);
    }

    @SneakyThrows
    public void setObjectTexture(MTLTexture texture, long index) {
        LL.invokeExact(id, ObjC.sel("setObjectTexture:atIndex:"), texture.getId(), index);
    }

    @SneakyThrows
    public void setObjectTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setObjectTextures:withRange:"), textures, range);
    }

    @SneakyThrows
    public void setObjectSamplerState(MTLSamplerState sampler, long index) {
        LL.invokeExact(id, ObjC.sel("setObjectSamplerState:atIndex:"), sampler.getId(), index);
    }

    @SneakyThrows
    public void setObjectSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setObjectSamplerStates:withRange:"), samplers, range);
    }

    @SneakyThrows
    public void setObjectSamplerState(MTLSamplerState sampler, float lodMinClamp, float lodMaxClamp, long index) {
        LFFL.invokeExact(id, ObjC.sel("setObjectSamplerState:lodMinClamp:lodMaxClamp:atIndex:"), sampler.getId(),
                lodMinClamp, lodMaxClamp, index);
    }

    @SneakyThrows
    public void setObjectSamplerStates(MemorySegment samplers, MemorySegment lodMinClamps,
            MemorySegment lodMaxClamps, MemorySegment range) {
        AAAR.invokeExact(id, ObjC.sel("setObjectSamplerStates:lodMinClamps:lodMaxClamps:withRange:"), samplers,
                lodMinClamps, lodMaxClamps, range);
    }

    @SneakyThrows
    public void setMeshBytes(MemorySegment bytes, long length, long index) {
        ALL.invokeExact(id, ObjC.sel("setMeshBytes:length:atIndex:"), bytes, length, index);
    }

    @SneakyThrows
    public void setMeshBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, ObjC.sel("setMeshBuffer:offset:atIndex:"), buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setMeshBufferOffset(long offset, long index) {
        LL.invokeExact(id, ObjC.sel("setMeshBufferOffset:atIndex:"), offset, index);
    }

    @SneakyThrows
    public void setMeshBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, ObjC.sel("setMeshBuffers:offsets:withRange:"), buffers, offsets, range);
    }

    @SneakyThrows
    public void setMeshTexture(MTLTexture texture, long index) {
        LL.invokeExact(id, ObjC.sel("setMeshTexture:atIndex:"), texture.getId(), index);
    }

    @SneakyThrows
    public void setMeshTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setMeshTextures:withRange:"), textures, range);
    }

    @SneakyThrows
    public void setMeshSamplerState(MTLSamplerState sampler, long index) {
        LL.invokeExact(id, ObjC.sel("setMeshSamplerState:atIndex:"), sampler.getId(), index);
    }

    @SneakyThrows
    public void setMeshSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, ObjC.sel("setMeshSamplerStates:withRange:"), samplers, range);
    }

    @SneakyThrows
    public void setMeshSamplerState(MTLSamplerState sampler, float lodMinClamp, float lodMaxClamp, long index) {
        LFFL.invokeExact(id, ObjC.sel("setMeshSamplerState:lodMinClamp:lodMaxClamp:atIndex:"), sampler.getId(),
                lodMinClamp, lodMaxClamp, index);
    }

    @SneakyThrows
    public void setMeshSamplerStates(MemorySegment samplers, MemorySegment lodMinClamps,
            MemorySegment lodMaxClamps, MemorySegment range) {
        AAAR.invokeExact(id, ObjC.sel("setMeshSamplerStates:lodMinClamps:lodMaxClamps:withRange:"), samplers,
                lodMinClamps, lodMaxClamps, range);
    }

    @SneakyThrows
    public void setViewport(MemorySegment viewport) {
        VIEWPORT.invokeExact(id, ObjC.sel("setViewport:"), viewport);
    }

    @SneakyThrows
    public void setViewports(MemorySegment viewports, long count) {
        AL.invokeExact(id, ObjC.sel("setViewports:count:"), viewports, count);
    }

    @SneakyThrows
    public void setFrontFacingWinding(long frontFacingWinding) {
        L.invokeExact(id, ObjC.sel("setFrontFacingWinding:"), frontFacingWinding);
    }

    @SneakyThrows
    public void setVertexAmplificationCount(long count, MemorySegment viewMappings) {
        LA.invokeExact(id, ObjC.sel("setVertexAmplificationCount:viewMappings:"), count, viewMappings);
    }

    @SneakyThrows
    public void setCullMode(long cullMode) {
        L.invokeExact(id, ObjC.sel("setCullMode:"), cullMode);
    }

    @SneakyThrows
    public void setDepthClipMode(long depthClipMode) {
        L.invokeExact(id, ObjC.sel("setDepthClipMode:"), depthClipMode);
    }

    @SneakyThrows
    public void setDepthBias(float depthBias, float slopeScale, float clamp) {
        FFF.invokeExact(id, ObjC.sel("setDepthBias:slopeScale:clamp:"), depthBias, slopeScale, clamp);
    }

    @SneakyThrows
    public void setDepthTestMinBound(float minBound, float maxBound) {
        FF.invokeExact(id, ObjC.sel("setDepthTestMinBound:maxBound:"), minBound, maxBound);
    }

    @SneakyThrows
    public void setScissorRect(MemorySegment rect) {
        SCISSOR.invokeExact(id, ObjC.sel("setScissorRect:"), rect);
    }

    @SneakyThrows
    public void setScissorRects(MemorySegment scissorRects, long count) {
        AL.invokeExact(id, ObjC.sel("setScissorRects:count:"), scissorRects, count);
    }

    @SneakyThrows
    public void setTriangleFillMode(long fillMode) {
        L.invokeExact(id, ObjC.sel("setTriangleFillMode:"), fillMode);
    }

    @SneakyThrows
    public void setBlendColorRed(float red, float green, float blue, float alpha) {
        FFFF.invokeExact(id, ObjC.sel("setBlendColorRed:green:blue:alpha:"), red, green, blue, alpha);
    }

    @SneakyThrows
    public void setDepthStencilState(MTLDepthStencilState depthStencilState) {
        L.invokeExact(id, ObjC.sel("setDepthStencilState:"), depthStencilState.getId());
    }

    @SneakyThrows
    public void setStencilReferenceValue(int referenceValue) {
        I.invokeExact(id, ObjC.sel("setStencilReferenceValue:"), referenceValue);
    }

    @SneakyThrows
    public void setStencilFrontReferenceValue(int frontReferenceValue, int backReferenceValue) {
        II.invokeExact(id, ObjC.sel("setStencilFrontReferenceValue:backReferenceValue:"), frontReferenceValue,
                backReferenceValue);
    }

    @SneakyThrows
    public void setVisibilityResultMode(long mode, long offset) {
        LL.invokeExact(id, ObjC.sel("setVisibilityResultMode:offset:"), mode, offset);
    }

    @SneakyThrows
    public void setColorStoreAction(long storeAction, long colorAttachmentIndex) {
        LL.invokeExact(id, ObjC.sel("setColorStoreAction:atIndex:"), storeAction, colorAttachmentIndex);
    }

    @SneakyThrows
    public void setDepthStoreAction(long storeAction) {
        L.invokeExact(id, ObjC.sel("setDepthStoreAction:"), storeAction);
    }

    @SneakyThrows
    public void setStencilStoreAction(long storeAction) {
        L.invokeExact(id, ObjC.sel("setStencilStoreAction:"), storeAction);
    }

    @SneakyThrows
    public void setColorStoreActionOptions(long storeActionOptions, long colorAttachmentIndex) {
        LL.invokeExact(id, ObjC.sel("setColorStoreActionOptions:atIndex:"), storeActionOptions,
                colorAttachmentIndex);
    }

    @SneakyThrows
    public void setDepthStoreActionOptions(long storeActionOptions) {
        L.invokeExact(id, ObjC.sel("setDepthStoreActionOptions:"), storeActionOptions);
    }

    @SneakyThrows
    public void setStencilStoreActionOptions(long storeActionOptions) {
        L.invokeExact(id, ObjC.sel("setStencilStoreActionOptions:"), storeActionOptions);
    }

    @SneakyThrows
    public void setObjectThreadgroupMemoryLength(long length, long index) {
        LL.invokeExact(id, ObjC.sel("setObjectThreadgroupMemoryLength:atIndex:"), length, index);
    }

    @SneakyThrows
    public void drawMeshThreadgroups(MemorySegment threadgroupsPerGrid, MemorySegment threadsPerObjectThreadgroup,
            MemorySegment threadsPerMeshThreadgroup) {
        SSS.invokeExact(id, ObjC.sel("drawMeshThreadgroups:threadsPerObjectThreadgroup:threadsPerMeshThreadgroup:"),
                threadgroupsPerGrid, threadsPerObjectThreadgroup, threadsPerMeshThreadgroup);
    }

    @SneakyThrows
    public void drawMeshThreads(MemorySegment threadsPerGrid, MemorySegment threadsPerObjectThreadgroup,
            MemorySegment threadsPerMeshThreadgroup) {
        SSS.invokeExact(id, ObjC.sel("drawMeshThreads:threadsPerObjectThreadgroup:threadsPerMeshThreadgroup:"),
                threadsPerGrid, threadsPerObjectThreadgroup, threadsPerMeshThreadgroup);
    }

    @SneakyThrows
    public void drawMeshThreadgroupsWithIndirectBuffer(MTLBuffer indirectBuffer, long indirectBufferOffset,
            MemorySegment threadsPerObjectThreadgroup, MemorySegment threadsPerMeshThreadgroup) {
        LLSS.invokeExact(id, ObjC.sel("drawMeshThreadgroupsWithIndirectBuffer:indirectBufferOffset:"
                + "threadsPerObjectThreadgroup:threadsPerMeshThreadgroup:"),
                indirectBuffer.getId(), indirectBufferOffset, threadsPerObjectThreadgroup, threadsPerMeshThreadgroup);
    }

    @SneakyThrows
    public void drawPrimitives(long primitiveType, long vertexStart, long vertexCount, long instanceCount) {
        LLLL.invokeExact(id, ObjC.sel("drawPrimitives:vertexStart:vertexCount:instanceCount:"), primitiveType,
                vertexStart, vertexCount, instanceCount);
    }

    @SneakyThrows
    public void drawPrimitives(long primitiveType, long vertexStart, long vertexCount) {
        LLL.invokeExact(id, ObjC.sel("drawPrimitives:vertexStart:vertexCount:"), primitiveType, vertexStart,
                vertexCount);
    }

    @SneakyThrows
    public void drawIndexedPrimitives(long primitiveType, long indexCount, long indexType, MTLBuffer indexBuffer,
            long indexBufferOffset, long instanceCount) {
        LLLLLL.invokeExact(id, ObjC.sel("drawIndexedPrimitives:indexCount:indexType:indexBuffer:indexBufferOffset:"
                + "instanceCount:"),
                primitiveType, indexCount, indexType, indexBuffer.getId(), indexBufferOffset, instanceCount);
    }

    @SneakyThrows
    public void drawIndexedPrimitives(long primitiveType, long indexCount, long indexType, MTLBuffer indexBuffer,
            long indexBufferOffset) {
        LLLLL.invokeExact(id, ObjC.sel("drawIndexedPrimitives:indexCount:indexType:indexBuffer:indexBufferOffset:"),
                primitiveType, indexCount, indexType, indexBuffer.getId(), indexBufferOffset);
    }

    @SneakyThrows
    public void drawPrimitives(long primitiveType, long vertexStart, long vertexCount, long instanceCount,
            long baseInstance) {
        LLLLL.invokeExact(id, ObjC.sel("drawPrimitives:vertexStart:vertexCount:instanceCount:baseInstance:"),
                primitiveType, vertexStart, vertexCount, instanceCount, baseInstance);
    }

    @SneakyThrows
    public void drawIndexedPrimitives(long primitiveType, long indexCount, long indexType, MTLBuffer indexBuffer,
            long indexBufferOffset, long instanceCount, long baseVertex, long baseInstance) {
        LLLLLLLL.invokeExact(id, ObjC.sel("drawIndexedPrimitives:indexCount:indexType:indexBuffer:indexBufferOffset:"
                + "instanceCount:baseVertex:baseInstance:"),
                primitiveType, indexCount, indexType, indexBuffer.getId(), indexBufferOffset, instanceCount,
                baseVertex, baseInstance);
    }

    @SneakyThrows
    public void drawPrimitives(long primitiveType, MTLBuffer indirectBuffer, long indirectBufferOffset) {
        LLL.invokeExact(id, ObjC.sel("drawPrimitives:indirectBuffer:indirectBufferOffset:"), primitiveType,
                indirectBuffer.getId(), indirectBufferOffset);
    }

    @SneakyThrows
    public void drawIndexedPrimitives(long primitiveType, long indexType, MTLBuffer indexBuffer,
            long indexBufferOffset, MTLBuffer indirectBuffer, long indirectBufferOffset) {
        LLLLLL.invokeExact(id, ObjC.sel("drawIndexedPrimitives:indexType:indexBuffer:indexBufferOffset:"
                + "indirectBuffer:indirectBufferOffset:"),
                primitiveType, indexType, indexBuffer.getId(), indexBufferOffset, indirectBuffer.getId(),
                indirectBufferOffset);
    }

    @SneakyThrows
    public void setTessellationFactorBuffer(MTLBuffer buffer, long offset, long instanceStride) {
        LLL.invokeExact(id, ObjC.sel("setTessellationFactorBuffer:offset:instanceStride:"), buffer.getId(), offset,
                instanceStride);
    }

    @SneakyThrows
    public void setTessellationFactorScale(float scale) {
        F.invokeExact(id, ObjC.sel("setTessellationFactorScale:"), scale);
    }

    @SneakyThrows
    public void drawPatches(long numberOfPatchControlPoints, long patchStart, long patchCount,
            MTLBuffer patchIndexBuffer, long patchIndexBufferOffset, long instanceCount, long baseInstance) {
        LLLLLLL.invokeExact(id, ObjC.sel("drawPatches:patchStart:patchCount:patchIndexBuffer:patchIndexBufferOffset:"
                + "instanceCount:baseInstance:"),
                numberOfPatchControlPoints, patchStart, patchCount, patchIndexBuffer.getId(), patchIndexBufferOffset,
                instanceCount, baseInstance);
    }

    @SneakyThrows
    public void drawPatches(long numberOfPatchControlPoints, MTLBuffer patchIndexBuffer, long patchIndexBufferOffset,
            MTLBuffer indirectBuffer, long indirectBufferOffset) {
        LLLLL.invokeExact(id, ObjC.sel("drawPatches:patchIndexBuffer:patchIndexBufferOffset:indirectBuffer:"
                + "indirectBufferOffset:"),
                numberOfPatchControlPoints, patchIndexBuffer.getId(), patchIndexBufferOffset, indirectBuffer.getId(),
                indirectBufferOffset);
    }

    @SneakyThrows
    public void drawIndexedPatches(long numberOfPatchControlPoints, long patchStart, long patchCount,
            MTLBuffer patchIndexBuffer, long patchIndexBufferOffset, MTLBuffer controlPointIndexBuffer,
            long controlPointIndexBufferOffset, long instanceCount, long baseInstance) {
        LLLLLLLLL.invokeExact(id, ObjC.sel("drawIndexedPatches:patchStart:patchCount:patchIndexBuffer:"
                + "patchIndexBufferOffset:controlPointIndexBuffer:controlPointIndexBufferOffset:instanceCount:"
                + "baseInstance:"),
                numberOfPatchControlPoints, patchStart, patchCount, patchIndexBuffer.getId(), patchIndexBufferOffset,
                controlPointIndexBuffer.getId(), controlPointIndexBufferOffset, instanceCount, baseInstance);
    }

    @SneakyThrows
    public void drawIndexedPatches(long numberOfPatchControlPoints, MTLBuffer patchIndexBuffer,
            long patchIndexBufferOffset, MTLBuffer controlPointIndexBuffer, long controlPointIndexBufferOffset,
            MTLBuffer indirectBuffer, long indirectBufferOffset) {
        LLLLLLL.invokeExact(id, ObjC.sel("drawIndexedPatches:patchIndexBuffer:patchIndexBufferOffset:"
                + "controlPointIndexBuffer:controlPointIndexBufferOffset:indirectBuffer:indirectBufferOffset:"),
                numberOfPatchControlPoints, patchIndexBuffer.getId(), patchIndexBufferOffset,
                controlPointIndexBuffer.getId(), controlPointIndexBufferOffset, indirectBuffer.getId(),
                indirectBufferOffset);
    }

    public long tileWidth() {
        return sendLong(id, "tileWidth");
    }

    public long tileHeight() {
        return sendLong(id, "tileHeight");
    }

    @SneakyThrows
    public void dispatchThreadsPerTile(MemorySegment threadsPerTile) {
        S.invokeExact(id, ObjC.sel("dispatchThreadsPerTile:"), threadsPerTile);
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length, long offset, long index) {
        LLL.invokeExact(id, ObjC.sel("setThreadgroupMemoryLength:offset:atIndex:"), length, offset, index);
    }

    @SneakyThrows
    public void useResource(MTLResource resource, long usage) {
        LL.invokeExact(id, ObjC.sel("useResource:usage:"), resource.getId(), usage);
    }

    @SneakyThrows
    public void useResources(MemorySegment resources, long count, long usage) {
        ALL.invokeExact(id, ObjC.sel("useResources:count:usage:"), resources, count, usage);
    }

    @SneakyThrows
    public void useResource(MTLResource resource, long usage, long stages) {
        LLL.invokeExact(id, ObjC.sel("useResource:usage:stages:"), resource.getId(), usage, stages);
    }

    @SneakyThrows
    public void useResources(MemorySegment resources, long count, long usage, long stages) {
        ALLL.invokeExact(id, ObjC.sel("useResources:count:usage:stages:"), resources, count, usage, stages);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, MemorySegment range) {
        LR.invokeExact(id, ObjC.sel("executeCommandsInBuffer:withRange:"), buffer.getId(), range);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, MTLBuffer indirectBuffer,
            long indirectBufferOffset) {
        LLL.invokeExact(id, ObjC.sel("executeCommandsInBuffer:indirectBuffer:indirectBufferOffset:"),
                buffer.getId(), indirectBuffer.getId(), indirectBufferOffset);
    }

    @SneakyThrows
    public void memoryBarrierWithScope(long scope, long after, long before) {
        LLL.invokeExact(id, ObjC.sel("memoryBarrierWithScope:afterStages:beforeStages:"), scope, after, before);
    }

    @SneakyThrows
    public void memoryBarrierWithResources(MemorySegment resources, long count, long after, long before) {
        ALLL.invokeExact(id, ObjC.sel("memoryBarrierWithResources:count:afterStages:beforeStages:"), resources,
                count, after, before);
    }

    @SneakyThrows
    public void sampleCountersInBuffer(MTLCounterSampleBuffer sampleBuffer, long sampleIndex, boolean barrier) {
        LLB.invokeExact(id, ObjC.sel("sampleCountersInBuffer:atSampleIndex:withBarrier:"), sampleBuffer.getId(),
                sampleIndex, barrier);
    }

    @SneakyThrows
    public void setVertexAccelerationStructure(MTLAccelerationStructure structure, long index) {
        LL.invokeExact(id, ObjC.sel("setVertexAccelerationStructure:atBufferIndex:"), structure.getId(), index);
    }

    @SneakyThrows
    public void setFragmentAccelerationStructure(MTLAccelerationStructure structure, long index) {
        LL.invokeExact(id, ObjC.sel("setFragmentAccelerationStructure:atBufferIndex:"), structure.getId(), index);
    }

    @SneakyThrows
    public void setTileAccelerationStructure(MTLAccelerationStructure structure, long index) {
        LL.invokeExact(id, ObjC.sel("setTileAccelerationStructure:atBufferIndex:"), structure.getId(), index);
    }

    @SneakyThrows
    public void setVertexIntersectionFunctionTable(MTLIntersectionFunctionTable table, long index) {
        LL.invokeExact(id, ObjC.sel("setVertexIntersectionFunctionTable:atBufferIndex:"), table.getId(), index);
    }

    @SneakyThrows
    public void setFragmentIntersectionFunctionTable(MTLIntersectionFunctionTable table, long index) {
        LL.invokeExact(id, ObjC.sel("setFragmentIntersectionFunctionTable:atBufferIndex:"), table.getId(), index);
    }
}
