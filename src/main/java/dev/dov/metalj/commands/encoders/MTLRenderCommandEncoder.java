package dev.dov.metalj.commands.encoders;

import dev.dov.metalj.debug.MTLCounterSampleBuffer;
import dev.dov.metalj.raytracing.MTLAccelerationStructure;
import dev.dov.metalj.raytracing.MTLIntersectionFunctionTable;
import dev.dov.metalj.functions.MTLVisibleFunctionTable;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.sync.MTLFence;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.depth.MTLDepthStencilState;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineState;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.resources.indirect.MTLIndirectCommandBuffer;
import dev.dov.metalj.resources.MTLResource;
import dev.dov.metalj.resources.heaps.MTLHeap;
import dev.dov.metalj.resources.samplers.MTLSamplerState;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.textures.MTLTexture;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLRenderCommandEncoder extends MTLCommandEncoder {
    private static final long DISPATCH_THREADS_PER_TILE = ObjC.sel("dispatchThreadsPerTile:");
    private static final long DRAW_INDEXED_PRIMITIVES_INDEX_COUNT_INDEX_TYPE_INDEX_BUFFER_INDEX_BUFFER_OFFSET = ObjC.sel("drawIndexedPrimitives:indexCount:indexType:indexBuffer:indexBufferOffset:");
    private static final long DRAW_MESH_THREADGROUPS_THREADS_PER_OBJECT_THREADGROUP_THREADS_PER_MESH_THREADGROUP = ObjC.sel("drawMeshThreadgroups:threadsPerObjectThreadgroup:threadsPerMeshThreadgroup:");
    private static final long DRAW_MESH_THREADS_THREADS_PER_OBJECT_THREADGROUP_THREADS_PER_MESH_THREADGROUP = ObjC.sel("drawMeshThreads:threadsPerObjectThreadgroup:threadsPerMeshThreadgroup:");
    private static final long DRAW_PRIMITIVES_INDIRECT_BUFFER_INDIRECT_BUFFER_OFFSET = ObjC.sel("drawPrimitives:indirectBuffer:indirectBufferOffset:");
    private static final long DRAW_PRIMITIVES_VERTEX_START_VERTEX_COUNT = ObjC.sel("drawPrimitives:vertexStart:vertexCount:");
    private static final long DRAW_PRIMITIVES_VERTEX_START_VERTEX_COUNT_INSTANCE_COUNT = ObjC.sel("drawPrimitives:vertexStart:vertexCount:instanceCount:");
    private static final long DRAW_PRIMITIVES_VERTEX_START_VERTEX_COUNT_INSTANCE_COUNT_BASE_INSTANCE = ObjC.sel("drawPrimitives:vertexStart:vertexCount:instanceCount:baseInstance:");
    private static final long EXECUTE_COMMANDS_IN_BUFFER_INDIRECT_BUFFER_INDIRECT_BUFFER_OFFSET = ObjC.sel("executeCommandsInBuffer:indirectBuffer:indirectBufferOffset:");
    private static final long EXECUTE_COMMANDS_IN_BUFFER_WITH_RANGE = ObjC.sel("executeCommandsInBuffer:withRange:");
    private static final long MEMORY_BARRIER_WITH_RESOURCES_COUNT_AFTER_STAGES_BEFORE_STAGES = ObjC.sel("memoryBarrierWithResources:count:afterStages:beforeStages:");
    private static final long MEMORY_BARRIER_WITH_SCOPE_AFTER_STAGES_BEFORE_STAGES = ObjC.sel("memoryBarrierWithScope:afterStages:beforeStages:");
    private static final long SAMPLE_COUNTERS_IN_BUFFER_AT_SAMPLE_INDEX_WITH_BARRIER = ObjC.sel("sampleCountersInBuffer:atSampleIndex:withBarrier:");
    private static final long SET_BLEND_COLOR_RED_GREEN_BLUE_ALPHA = ObjC.sel("setBlendColorRed:green:blue:alpha:");
    private static final long SET_COLOR_STORE_ACTION_AT_INDEX = ObjC.sel("setColorStoreAction:atIndex:");
    private static final long SET_COLOR_STORE_ACTION_OPTIONS_AT_INDEX = ObjC.sel("setColorStoreActionOptions:atIndex:");
    private static final long SET_CULL_MODE = ObjC.sel("setCullMode:");
    private static final long SET_DEPTH_BIAS_SLOPE_SCALE_CLAMP = ObjC.sel("setDepthBias:slopeScale:clamp:");
    private static final long SET_DEPTH_CLIP_MODE = ObjC.sel("setDepthClipMode:");
    private static final long SET_DEPTH_STENCIL_STATE = ObjC.sel("setDepthStencilState:");
    private static final long SET_DEPTH_STORE_ACTION = ObjC.sel("setDepthStoreAction:");
    private static final long SET_DEPTH_STORE_ACTION_OPTIONS = ObjC.sel("setDepthStoreActionOptions:");
    private static final long SET_DEPTH_TEST_MIN_BOUND_MAX_BOUND = ObjC.sel("setDepthTestMinBound:maxBound:");
    private static final long SET_FRAGMENT_ACCELERATION_STRUCTURE_AT_BUFFER_INDEX = ObjC.sel("setFragmentAccelerationStructure:atBufferIndex:");
    private static final long SET_FRAGMENT_BUFFER_OFFSET_AT_INDEX = ObjC.sel("setFragmentBuffer:offset:atIndex:");
    private static final long SETFRAGMENTBUFFEROFFSET_ATINDEX = ObjC.sel("setFragmentBufferOffset:atIndex:");
    private static final long SET_FRAGMENT_BUFFERS_OFFSETS_WITH_RANGE = ObjC.sel("setFragmentBuffers:offsets:withRange:");
    private static final long SET_FRAGMENT_BYTES_LENGTH_AT_INDEX = ObjC.sel("setFragmentBytes:length:atIndex:");
    private static final long SET_FRAGMENT_INTERSECTION_FUNCTION_TABLE_AT_BUFFER_INDEX = ObjC.sel("setFragmentIntersectionFunctionTable:atBufferIndex:");
    private static final long SET_FRAGMENT_SAMPLER_STATE_AT_INDEX = ObjC.sel("setFragmentSamplerState:atIndex:");
    private static final long SET_FRAGMENT_SAMPLER_STATE_LOD_MIN_CLAMP_LOD_MAX_CLAMP_AT_INDEX = ObjC.sel("setFragmentSamplerState:lodMinClamp:lodMaxClamp:atIndex:");
    private static final long SET_FRAGMENT_SAMPLER_STATES_LOD_MIN_CLAMPS_LOD_MAX_CLAMPS_WITH_RANGE = ObjC.sel("setFragmentSamplerStates:lodMinClamps:lodMaxClamps:withRange:");
    private static final long SET_FRAGMENT_SAMPLER_STATES_WITH_RANGE = ObjC.sel("setFragmentSamplerStates:withRange:");
    private static final long SET_FRAGMENT_TEXTURE_AT_INDEX = ObjC.sel("setFragmentTexture:atIndex:");
    private static final long SET_FRAGMENT_TEXTURES_WITH_RANGE = ObjC.sel("setFragmentTextures:withRange:");
    private static final long SET_FRAGMENT_VISIBLE_FUNCTION_TABLE_AT_BUFFER_INDEX = ObjC.sel("setFragmentVisibleFunctionTable:atBufferIndex:");
    private static final long SET_FRONT_FACING_WINDING = ObjC.sel("setFrontFacingWinding:");
    private static final long SET_MESH_BUFFER_OFFSET_AT_INDEX = ObjC.sel("setMeshBuffer:offset:atIndex:");
    private static final long SETMESHBUFFEROFFSET_ATINDEX = ObjC.sel("setMeshBufferOffset:atIndex:");
    private static final long SET_MESH_BUFFERS_OFFSETS_WITH_RANGE = ObjC.sel("setMeshBuffers:offsets:withRange:");
    private static final long SET_MESH_BYTES_LENGTH_AT_INDEX = ObjC.sel("setMeshBytes:length:atIndex:");
    private static final long SET_MESH_SAMPLER_STATE_AT_INDEX = ObjC.sel("setMeshSamplerState:atIndex:");
    private static final long SET_MESH_SAMPLER_STATE_LOD_MIN_CLAMP_LOD_MAX_CLAMP_AT_INDEX = ObjC.sel("setMeshSamplerState:lodMinClamp:lodMaxClamp:atIndex:");
    private static final long SET_MESH_SAMPLER_STATES_LOD_MIN_CLAMPS_LOD_MAX_CLAMPS_WITH_RANGE = ObjC.sel("setMeshSamplerStates:lodMinClamps:lodMaxClamps:withRange:");
    private static final long SET_MESH_SAMPLER_STATES_WITH_RANGE = ObjC.sel("setMeshSamplerStates:withRange:");
    private static final long SET_MESH_TEXTURE_AT_INDEX = ObjC.sel("setMeshTexture:atIndex:");
    private static final long SET_MESH_TEXTURES_WITH_RANGE = ObjC.sel("setMeshTextures:withRange:");
    private static final long SET_OBJECT_BUFFER_OFFSET_AT_INDEX = ObjC.sel("setObjectBuffer:offset:atIndex:");
    private static final long SETOBJECTBUFFEROFFSET_ATINDEX = ObjC.sel("setObjectBufferOffset:atIndex:");
    private static final long SET_OBJECT_BUFFERS_OFFSETS_WITH_RANGE = ObjC.sel("setObjectBuffers:offsets:withRange:");
    private static final long SET_OBJECT_BYTES_LENGTH_AT_INDEX = ObjC.sel("setObjectBytes:length:atIndex:");
    private static final long SET_OBJECT_SAMPLER_STATE_AT_INDEX = ObjC.sel("setObjectSamplerState:atIndex:");
    private static final long SET_OBJECT_SAMPLER_STATE_LOD_MIN_CLAMP_LOD_MAX_CLAMP_AT_INDEX = ObjC.sel("setObjectSamplerState:lodMinClamp:lodMaxClamp:atIndex:");
    private static final long SET_OBJECT_SAMPLER_STATES_LOD_MIN_CLAMPS_LOD_MAX_CLAMPS_WITH_RANGE = ObjC.sel("setObjectSamplerStates:lodMinClamps:lodMaxClamps:withRange:");
    private static final long SET_OBJECT_SAMPLER_STATES_WITH_RANGE = ObjC.sel("setObjectSamplerStates:withRange:");
    private static final long SET_OBJECT_TEXTURE_AT_INDEX = ObjC.sel("setObjectTexture:atIndex:");
    private static final long SET_OBJECT_TEXTURES_WITH_RANGE = ObjC.sel("setObjectTextures:withRange:");
    private static final long SET_OBJECT_THREADGROUP_MEMORY_LENGTH_AT_INDEX = ObjC.sel("setObjectThreadgroupMemoryLength:atIndex:");
    private static final long SET_RENDER_PIPELINE_STATE = ObjC.sel("setRenderPipelineState:");
    private static final long SET_SCISSOR_RECT = ObjC.sel("setScissorRect:");
    private static final long SET_SCISSOR_RECTS_COUNT = ObjC.sel("setScissorRects:count:");
    private static final long SET_STENCIL_FRONT_REFERENCE_VALUE_BACK_REFERENCE_VALUE = ObjC.sel("setStencilFrontReferenceValue:backReferenceValue:");
    private static final long SET_STENCIL_REFERENCE_VALUE = ObjC.sel("setStencilReferenceValue:");
    private static final long SET_STENCIL_STORE_ACTION = ObjC.sel("setStencilStoreAction:");
    private static final long SET_STENCIL_STORE_ACTION_OPTIONS = ObjC.sel("setStencilStoreActionOptions:");
    private static final long SET_TESSELLATION_FACTOR_BUFFER_OFFSET_INSTANCE_STRIDE = ObjC.sel("setTessellationFactorBuffer:offset:instanceStride:");
    private static final long SET_TESSELLATION_FACTOR_SCALE = ObjC.sel("setTessellationFactorScale:");
    private static final long SET_THREADGROUP_MEMORY_LENGTH_OFFSET_AT_INDEX = ObjC.sel("setThreadgroupMemoryLength:offset:atIndex:");
    private static final long SET_TILE_ACCELERATION_STRUCTURE_AT_BUFFER_INDEX = ObjC.sel("setTileAccelerationStructure:atBufferIndex:");
    private static final long SET_TILE_BUFFER_OFFSET_AT_INDEX = ObjC.sel("setTileBuffer:offset:atIndex:");
    private static final long SETTILEBUFFEROFFSET_ATINDEX = ObjC.sel("setTileBufferOffset:atIndex:");
    private static final long SET_TILE_BUFFERS_OFFSETS_WITH_RANGE = ObjC.sel("setTileBuffers:offsets:withRange:");
    private static final long SET_TILE_BYTES_LENGTH_AT_INDEX = ObjC.sel("setTileBytes:length:atIndex:");
    private static final long SET_TILE_SAMPLER_STATE_AT_INDEX = ObjC.sel("setTileSamplerState:atIndex:");
    private static final long SET_TILE_SAMPLER_STATE_LOD_MIN_CLAMP_LOD_MAX_CLAMP_AT_INDEX = ObjC.sel("setTileSamplerState:lodMinClamp:lodMaxClamp:atIndex:");
    private static final long SET_TILE_SAMPLER_STATES_LOD_MIN_CLAMPS_LOD_MAX_CLAMPS_WITH_RANGE = ObjC.sel("setTileSamplerStates:lodMinClamps:lodMaxClamps:withRange:");
    private static final long SET_TILE_SAMPLER_STATES_WITH_RANGE = ObjC.sel("setTileSamplerStates:withRange:");
    private static final long SET_TILE_TEXTURE_AT_INDEX = ObjC.sel("setTileTexture:atIndex:");
    private static final long SET_TILE_TEXTURES_WITH_RANGE = ObjC.sel("setTileTextures:withRange:");
    private static final long SET_TRIANGLE_FILL_MODE = ObjC.sel("setTriangleFillMode:");
    private static final long SET_VERTEX_ACCELERATION_STRUCTURE_AT_BUFFER_INDEX = ObjC.sel("setVertexAccelerationStructure:atBufferIndex:");
    private static final long SET_VERTEX_AMPLIFICATION_COUNT_VIEW_MAPPINGS = ObjC.sel("setVertexAmplificationCount:viewMappings:");
    private static final long SET_VERTEX_BUFFER_OFFSET_AT_INDEX = ObjC.sel("setVertexBuffer:offset:atIndex:");
    private static final long SETVERTEXBUFFEROFFSET_ATINDEX = ObjC.sel("setVertexBufferOffset:atIndex:");
    private static final long SET_VERTEX_BUFFERS_OFFSETS_WITH_RANGE = ObjC.sel("setVertexBuffers:offsets:withRange:");
    private static final long SET_VERTEX_BYTES_LENGTH_AT_INDEX = ObjC.sel("setVertexBytes:length:atIndex:");
    private static final long SET_VERTEX_INTERSECTION_FUNCTION_TABLE_AT_BUFFER_INDEX = ObjC.sel("setVertexIntersectionFunctionTable:atBufferIndex:");
    private static final long SET_VERTEX_SAMPLER_STATE_AT_INDEX = ObjC.sel("setVertexSamplerState:atIndex:");
    private static final long SET_VERTEX_SAMPLER_STATE_LOD_MIN_CLAMP_LOD_MAX_CLAMP_AT_INDEX = ObjC.sel("setVertexSamplerState:lodMinClamp:lodMaxClamp:atIndex:");
    private static final long SET_VERTEX_SAMPLER_STATES_LOD_MIN_CLAMPS_LOD_MAX_CLAMPS_WITH_RANGE = ObjC.sel("setVertexSamplerStates:lodMinClamps:lodMaxClamps:withRange:");
    private static final long SET_VERTEX_SAMPLER_STATES_WITH_RANGE = ObjC.sel("setVertexSamplerStates:withRange:");
    private static final long SET_VERTEX_TEXTURE_AT_INDEX = ObjC.sel("setVertexTexture:atIndex:");
    private static final long SET_VERTEX_TEXTURES_WITH_RANGE = ObjC.sel("setVertexTextures:withRange:");
    private static final long SET_VERTEX_VISIBLE_FUNCTION_TABLE_AT_BUFFER_INDEX = ObjC.sel("setVertexVisibleFunctionTable:atBufferIndex:");
    private static final long SET_VIEWPORT = ObjC.sel("setViewport:");
    private static final long SET_VIEWPORTS_COUNT = ObjC.sel("setViewports:count:");
    private static final long SET_VISIBILITY_RESULT_MODE_OFFSET = ObjC.sel("setVisibilityResultMode:offset:");
    private static final long TILE_HEIGHT = ObjC.sel("tileHeight");
    private static final long TILE_WIDTH = ObjC.sel("tileWidth");
    private static final long UPDATE_FENCE_AFTER_STAGES = ObjC.sel("updateFence:afterStages:");
    private static final long USE_HEAP = ObjC.sel("useHeap:");
    private static final long USE_HEAP_STAGES = ObjC.sel("useHeap:stages:");
    private static final long USE_HEAPS_COUNT = ObjC.sel("useHeaps:count:");
    private static final long USE_HEAPS_COUNT_STAGES = ObjC.sel("useHeaps:count:stages:");
    private static final long USE_RESOURCE_USAGE = ObjC.sel("useResource:usage:");
    private static final long USE_RESOURCE_USAGE_STAGES = ObjC.sel("useResource:usage:stages:");
    private static final long USE_RESOURCES_COUNT_USAGE = ObjC.sel("useResources:count:usage:");
    private static final long USE_RESOURCES_COUNT_USAGE_STAGES = ObjC.sel("useResources:count:usage:stages:");
    private static final long WAIT_FOR_FENCE_BEFORE_STAGES = ObjC.sel("waitForFence:beforeStages:");

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
        L.invokeExact(id, SET_RENDER_PIPELINE_STATE, pipelineState.getId());
    }

    @SneakyThrows
    public void setVertexBytes(MemorySegment bytes, long length, long index) {
        ALL.invokeExact(id, SET_VERTEX_BYTES_LENGTH_AT_INDEX, bytes, length, index);
    }

    @SneakyThrows
    public void setVertexBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, SET_VERTEX_BUFFER_OFFSET_AT_INDEX, buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setVertexBufferOffset(long offset, long index) {
        LL.invokeExact(id, SETVERTEXBUFFEROFFSET_ATINDEX, offset, index);
    }

    @SneakyThrows
    public void setVertexBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, SET_VERTEX_BUFFERS_OFFSETS_WITH_RANGE, buffers, offsets, range);
    }

    @SneakyThrows
    public void setVertexTexture(MTLTexture texture, long index) {
        LL.invokeExact(id, SET_VERTEX_TEXTURE_AT_INDEX, texture.getId(), index);
    }

    @SneakyThrows
    public void setVertexTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, SET_VERTEX_TEXTURES_WITH_RANGE, textures, range);
    }

    @SneakyThrows
    public void setVertexSamplerState(MTLSamplerState sampler, long index) {
        LL.invokeExact(id, SET_VERTEX_SAMPLER_STATE_AT_INDEX, sampler.getId(), index);
    }

    @SneakyThrows
    public void setVertexSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, SET_VERTEX_SAMPLER_STATES_WITH_RANGE, samplers, range);
    }

    @SneakyThrows
    public void setVertexSamplerState(MTLSamplerState sampler, float lodMinClamp, float lodMaxClamp, long index) {
        LFFL.invokeExact(id, SET_VERTEX_SAMPLER_STATE_LOD_MIN_CLAMP_LOD_MAX_CLAMP_AT_INDEX, sampler.getId(),
                lodMinClamp, lodMaxClamp, index);
    }

    @SneakyThrows
    public void setVertexSamplerStates(MemorySegment samplers, MemorySegment lodMinClamps,
            MemorySegment lodMaxClamps, MemorySegment range) {
        AAAR.invokeExact(id, SET_VERTEX_SAMPLER_STATES_LOD_MIN_CLAMPS_LOD_MAX_CLAMPS_WITH_RANGE, samplers,
                lodMinClamps, lodMaxClamps, range);
    }

    @SneakyThrows
    public void setFragmentBytes(MemorySegment bytes, long length, long index) {
        ALL.invokeExact(id, SET_FRAGMENT_BYTES_LENGTH_AT_INDEX, bytes, length, index);
    }

    @SneakyThrows
    public void setFragmentBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, SET_FRAGMENT_BUFFER_OFFSET_AT_INDEX, buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setFragmentBufferOffset(long offset, long index) {
        LL.invokeExact(id, SETFRAGMENTBUFFEROFFSET_ATINDEX, offset, index);
    }

    @SneakyThrows
    public void setFragmentBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, SET_FRAGMENT_BUFFERS_OFFSETS_WITH_RANGE, buffers, offsets, range);
    }

    @SneakyThrows
    public void setFragmentTexture(MTLTexture texture, long index) {
        LL.invokeExact(id, SET_FRAGMENT_TEXTURE_AT_INDEX, texture.getId(), index);
    }

    @SneakyThrows
    public void setFragmentTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, SET_FRAGMENT_TEXTURES_WITH_RANGE, textures, range);
    }

    @SneakyThrows
    public void setFragmentSamplerState(MTLSamplerState sampler, long index) {
        LL.invokeExact(id, SET_FRAGMENT_SAMPLER_STATE_AT_INDEX, sampler.getId(), index);
    }

    @SneakyThrows
    public void setFragmentSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, SET_FRAGMENT_SAMPLER_STATES_WITH_RANGE, samplers, range);
    }

    @SneakyThrows
    public void setFragmentSamplerState(MTLSamplerState sampler, float lodMinClamp, float lodMaxClamp, long index) {
        LFFL.invokeExact(id, SET_FRAGMENT_SAMPLER_STATE_LOD_MIN_CLAMP_LOD_MAX_CLAMP_AT_INDEX, sampler.getId(),
                lodMinClamp, lodMaxClamp, index);
    }

    @SneakyThrows
    public void setFragmentSamplerStates(MemorySegment samplers, MemorySegment lodMinClamps,
            MemorySegment lodMaxClamps, MemorySegment range) {
        AAAR.invokeExact(id, SET_FRAGMENT_SAMPLER_STATES_LOD_MIN_CLAMPS_LOD_MAX_CLAMPS_WITH_RANGE, samplers,
                lodMinClamps, lodMaxClamps, range);
    }

    @SneakyThrows
    public void setTileBytes(MemorySegment bytes, long length, long index) {
        ALL.invokeExact(id, SET_TILE_BYTES_LENGTH_AT_INDEX, bytes, length, index);
    }

    @SneakyThrows
    public void setTileBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, SET_TILE_BUFFER_OFFSET_AT_INDEX, buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setTileBufferOffset(long offset, long index) {
        LL.invokeExact(id, SETTILEBUFFEROFFSET_ATINDEX, offset, index);
    }

    @SneakyThrows
    public void setTileBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, SET_TILE_BUFFERS_OFFSETS_WITH_RANGE, buffers, offsets, range);
    }

    @SneakyThrows
    public void setTileTexture(MTLTexture texture, long index) {
        LL.invokeExact(id, SET_TILE_TEXTURE_AT_INDEX, texture.getId(), index);
    }

    @SneakyThrows
    public void setTileTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, SET_TILE_TEXTURES_WITH_RANGE, textures, range);
    }

    @SneakyThrows
    public void setTileSamplerState(MTLSamplerState sampler, long index) {
        LL.invokeExact(id, SET_TILE_SAMPLER_STATE_AT_INDEX, sampler.getId(), index);
    }

    @SneakyThrows
    public void setTileSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, SET_TILE_SAMPLER_STATES_WITH_RANGE, samplers, range);
    }

    @SneakyThrows
    public void setTileSamplerState(MTLSamplerState sampler, float lodMinClamp, float lodMaxClamp, long index) {
        LFFL.invokeExact(id, SET_TILE_SAMPLER_STATE_LOD_MIN_CLAMP_LOD_MAX_CLAMP_AT_INDEX, sampler.getId(),
                lodMinClamp, lodMaxClamp, index);
    }

    @SneakyThrows
    public void setTileSamplerStates(MemorySegment samplers, MemorySegment lodMinClamps,
            MemorySegment lodMaxClamps, MemorySegment range) {
        AAAR.invokeExact(id, SET_TILE_SAMPLER_STATES_LOD_MIN_CLAMPS_LOD_MAX_CLAMPS_WITH_RANGE, samplers,
                lodMinClamps, lodMaxClamps, range);
    }

    @SneakyThrows
    public void setObjectBytes(MemorySegment bytes, long length, long index) {
        ALL.invokeExact(id, SET_OBJECT_BYTES_LENGTH_AT_INDEX, bytes, length, index);
    }

    @SneakyThrows
    public void setObjectBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, SET_OBJECT_BUFFER_OFFSET_AT_INDEX, buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setObjectBufferOffset(long offset, long index) {
        LL.invokeExact(id, SETOBJECTBUFFEROFFSET_ATINDEX, offset, index);
    }

    @SneakyThrows
    public void setObjectBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, SET_OBJECT_BUFFERS_OFFSETS_WITH_RANGE, buffers, offsets, range);
    }

    @SneakyThrows
    public void setObjectTexture(MTLTexture texture, long index) {
        LL.invokeExact(id, SET_OBJECT_TEXTURE_AT_INDEX, texture.getId(), index);
    }

    @SneakyThrows
    public void setObjectTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, SET_OBJECT_TEXTURES_WITH_RANGE, textures, range);
    }

    @SneakyThrows
    public void setObjectSamplerState(MTLSamplerState sampler, long index) {
        LL.invokeExact(id, SET_OBJECT_SAMPLER_STATE_AT_INDEX, sampler.getId(), index);
    }

    @SneakyThrows
    public void setObjectSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, SET_OBJECT_SAMPLER_STATES_WITH_RANGE, samplers, range);
    }

    @SneakyThrows
    public void setObjectSamplerState(MTLSamplerState sampler, float lodMinClamp, float lodMaxClamp, long index) {
        LFFL.invokeExact(id, SET_OBJECT_SAMPLER_STATE_LOD_MIN_CLAMP_LOD_MAX_CLAMP_AT_INDEX, sampler.getId(),
                lodMinClamp, lodMaxClamp, index);
    }

    @SneakyThrows
    public void setObjectSamplerStates(MemorySegment samplers, MemorySegment lodMinClamps,
            MemorySegment lodMaxClamps, MemorySegment range) {
        AAAR.invokeExact(id, SET_OBJECT_SAMPLER_STATES_LOD_MIN_CLAMPS_LOD_MAX_CLAMPS_WITH_RANGE, samplers,
                lodMinClamps, lodMaxClamps, range);
    }

    @SneakyThrows
    public void setMeshBytes(MemorySegment bytes, long length, long index) {
        ALL.invokeExact(id, SET_MESH_BYTES_LENGTH_AT_INDEX, bytes, length, index);
    }

    @SneakyThrows
    public void setMeshBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, SET_MESH_BUFFER_OFFSET_AT_INDEX, buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setMeshBufferOffset(long offset, long index) {
        LL.invokeExact(id, SETMESHBUFFEROFFSET_ATINDEX, offset, index);
    }

    @SneakyThrows
    public void setMeshBuffers(MemorySegment buffers, MemorySegment offsets, MemorySegment range) {
        AAR.invokeExact(id, SET_MESH_BUFFERS_OFFSETS_WITH_RANGE, buffers, offsets, range);
    }

    @SneakyThrows
    public void setMeshTexture(MTLTexture texture, long index) {
        LL.invokeExact(id, SET_MESH_TEXTURE_AT_INDEX, texture.getId(), index);
    }

    @SneakyThrows
    public void setMeshTextures(MemorySegment textures, MemorySegment range) {
        AR.invokeExact(id, SET_MESH_TEXTURES_WITH_RANGE, textures, range);
    }

    @SneakyThrows
    public void setMeshSamplerState(MTLSamplerState sampler, long index) {
        LL.invokeExact(id, SET_MESH_SAMPLER_STATE_AT_INDEX, sampler.getId(), index);
    }

    @SneakyThrows
    public void setMeshSamplerStates(MemorySegment samplers, MemorySegment range) {
        AR.invokeExact(id, SET_MESH_SAMPLER_STATES_WITH_RANGE, samplers, range);
    }

    @SneakyThrows
    public void setMeshSamplerState(MTLSamplerState sampler, float lodMinClamp, float lodMaxClamp, long index) {
        LFFL.invokeExact(id, SET_MESH_SAMPLER_STATE_LOD_MIN_CLAMP_LOD_MAX_CLAMP_AT_INDEX, sampler.getId(),
                lodMinClamp, lodMaxClamp, index);
    }

    @SneakyThrows
    public void setMeshSamplerStates(MemorySegment samplers, MemorySegment lodMinClamps,
            MemorySegment lodMaxClamps, MemorySegment range) {
        AAAR.invokeExact(id, SET_MESH_SAMPLER_STATES_LOD_MIN_CLAMPS_LOD_MAX_CLAMPS_WITH_RANGE, samplers,
                lodMinClamps, lodMaxClamps, range);
    }

    @SneakyThrows
    public void setViewport(MemorySegment viewport) {
        VIEWPORT.invokeExact(id, SET_VIEWPORT, viewport);
    }

    @SneakyThrows
    public void setViewports(MemorySegment viewports, long count) {
        AL.invokeExact(id, SET_VIEWPORTS_COUNT, viewports, count);
    }

    @SneakyThrows
    public void setFrontFacingWinding(long frontFacingWinding) {
        L.invokeExact(id, SET_FRONT_FACING_WINDING, frontFacingWinding);
    }

    @SneakyThrows
    public void setVertexAmplificationCount(long count, MemorySegment viewMappings) {
        LA.invokeExact(id, SET_VERTEX_AMPLIFICATION_COUNT_VIEW_MAPPINGS, count, viewMappings);
    }

    @SneakyThrows
    public void setCullMode(long cullMode) {
        L.invokeExact(id, SET_CULL_MODE, cullMode);
    }

    @SneakyThrows
    public void setDepthClipMode(long depthClipMode) {
        L.invokeExact(id, SET_DEPTH_CLIP_MODE, depthClipMode);
    }

    @SneakyThrows
    public void setDepthBias(float depthBias, float slopeScale, float clamp) {
        FFF.invokeExact(id, SET_DEPTH_BIAS_SLOPE_SCALE_CLAMP, depthBias, slopeScale, clamp);
    }

    @SneakyThrows
    public void setDepthTestMinBound(float minBound, float maxBound) {
        FF.invokeExact(id, SET_DEPTH_TEST_MIN_BOUND_MAX_BOUND, minBound, maxBound);
    }

    @SneakyThrows
    public void setScissorRect(MemorySegment rect) {
        SCISSOR.invokeExact(id, SET_SCISSOR_RECT, rect);
    }

    @SneakyThrows
    public void setScissorRects(MemorySegment scissorRects, long count) {
        AL.invokeExact(id, SET_SCISSOR_RECTS_COUNT, scissorRects, count);
    }

    @SneakyThrows
    public void setTriangleFillMode(long fillMode) {
        L.invokeExact(id, SET_TRIANGLE_FILL_MODE, fillMode);
    }

    @SneakyThrows
    public void setBlendColorRed(float red, float green, float blue, float alpha) {
        FFFF.invokeExact(id, SET_BLEND_COLOR_RED_GREEN_BLUE_ALPHA, red, green, blue, alpha);
    }

    @SneakyThrows
    public void setDepthStencilState(MTLDepthStencilState depthStencilState) {
        L.invokeExact(id, SET_DEPTH_STENCIL_STATE, depthStencilState.getId());
    }

    @SneakyThrows
    public void setStencilReferenceValue(int referenceValue) {
        I.invokeExact(id, SET_STENCIL_REFERENCE_VALUE, referenceValue);
    }

    @SneakyThrows
    public void setStencilFrontReferenceValue(int frontReferenceValue, int backReferenceValue) {
        II.invokeExact(id, SET_STENCIL_FRONT_REFERENCE_VALUE_BACK_REFERENCE_VALUE, frontReferenceValue,
                backReferenceValue);
    }

    @SneakyThrows
    public void setVisibilityResultMode(long mode, long offset) {
        LL.invokeExact(id, SET_VISIBILITY_RESULT_MODE_OFFSET, mode, offset);
    }

    @SneakyThrows
    public void setColorStoreAction(long storeAction, long colorAttachmentIndex) {
        LL.invokeExact(id, SET_COLOR_STORE_ACTION_AT_INDEX, storeAction, colorAttachmentIndex);
    }

    @SneakyThrows
    public void setDepthStoreAction(long storeAction) {
        L.invokeExact(id, SET_DEPTH_STORE_ACTION, storeAction);
    }

    @SneakyThrows
    public void setStencilStoreAction(long storeAction) {
        L.invokeExact(id, SET_STENCIL_STORE_ACTION, storeAction);
    }

    @SneakyThrows
    public void setColorStoreActionOptions(long storeActionOptions, long colorAttachmentIndex) {
        LL.invokeExact(id, SET_COLOR_STORE_ACTION_OPTIONS_AT_INDEX, storeActionOptions,
                colorAttachmentIndex);
    }

    @SneakyThrows
    public void setDepthStoreActionOptions(long storeActionOptions) {
        L.invokeExact(id, SET_DEPTH_STORE_ACTION_OPTIONS, storeActionOptions);
    }

    @SneakyThrows
    public void setStencilStoreActionOptions(long storeActionOptions) {
        L.invokeExact(id, SET_STENCIL_STORE_ACTION_OPTIONS, storeActionOptions);
    }

    @SneakyThrows
    public void setObjectThreadgroupMemoryLength(long length, long index) {
        LL.invokeExact(id, SET_OBJECT_THREADGROUP_MEMORY_LENGTH_AT_INDEX, length, index);
    }

    @SneakyThrows
    public void drawMeshThreadgroups(MemorySegment threadgroupsPerGrid, MemorySegment threadsPerObjectThreadgroup,
            MemorySegment threadsPerMeshThreadgroup) {
        SSS.invokeExact(id, DRAW_MESH_THREADGROUPS_THREADS_PER_OBJECT_THREADGROUP_THREADS_PER_MESH_THREADGROUP,
                threadgroupsPerGrid, threadsPerObjectThreadgroup, threadsPerMeshThreadgroup);
    }

    @SneakyThrows
    public void drawMeshThreads(MemorySegment threadsPerGrid, MemorySegment threadsPerObjectThreadgroup,
            MemorySegment threadsPerMeshThreadgroup) {
        SSS.invokeExact(id, DRAW_MESH_THREADS_THREADS_PER_OBJECT_THREADGROUP_THREADS_PER_MESH_THREADGROUP,
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
        LLLL.invokeExact(id, DRAW_PRIMITIVES_VERTEX_START_VERTEX_COUNT_INSTANCE_COUNT, primitiveType,
                vertexStart, vertexCount, instanceCount);
    }

    @SneakyThrows
    public void drawPrimitives(long primitiveType, long vertexStart, long vertexCount) {
        LLL.invokeExact(id, DRAW_PRIMITIVES_VERTEX_START_VERTEX_COUNT, primitiveType, vertexStart,
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
        LLLLL.invokeExact(id, DRAW_INDEXED_PRIMITIVES_INDEX_COUNT_INDEX_TYPE_INDEX_BUFFER_INDEX_BUFFER_OFFSET,
                primitiveType, indexCount, indexType, indexBuffer.getId(), indexBufferOffset);
    }

    @SneakyThrows
    public void drawPrimitives(long primitiveType, long vertexStart, long vertexCount, long instanceCount,
            long baseInstance) {
        LLLLL.invokeExact(id, DRAW_PRIMITIVES_VERTEX_START_VERTEX_COUNT_INSTANCE_COUNT_BASE_INSTANCE,
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
        LLL.invokeExact(id, DRAW_PRIMITIVES_INDIRECT_BUFFER_INDIRECT_BUFFER_OFFSET, primitiveType,
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
        LLL.invokeExact(id, SET_TESSELLATION_FACTOR_BUFFER_OFFSET_INSTANCE_STRIDE, buffer.getId(), offset,
                instanceStride);
    }

    @SneakyThrows
    public void setTessellationFactorScale(float scale) {
        F.invokeExact(id, SET_TESSELLATION_FACTOR_SCALE, scale);
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
        return sendLong(id, TILE_WIDTH);
    }

    public long tileHeight() {
        return sendLong(id, TILE_HEIGHT);
    }

    @SneakyThrows
    public void dispatchThreadsPerTile(MemorySegment threadsPerTile) {
        S.invokeExact(id, DISPATCH_THREADS_PER_TILE, threadsPerTile);
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length, long offset, long index) {
        LLL.invokeExact(id, SET_THREADGROUP_MEMORY_LENGTH_OFFSET_AT_INDEX, length, offset, index);
    }

    @SneakyThrows
    public void useResource(MTLResource resource, long usage) {
        LL.invokeExact(id, USE_RESOURCE_USAGE, resource.getId(), usage);
    }

    @SneakyThrows
    public void useResources(MemorySegment resources, long count, long usage) {
        ALL.invokeExact(id, USE_RESOURCES_COUNT_USAGE, resources, count, usage);
    }

    @SneakyThrows
    public void useResource(MTLResource resource, long usage, long stages) {
        LLL.invokeExact(id, USE_RESOURCE_USAGE_STAGES, resource.getId(), usage, stages);
    }

    @SneakyThrows
    public void useResources(MemorySegment resources, long count, long usage, long stages) {
        ALLL.invokeExact(id, USE_RESOURCES_COUNT_USAGE_STAGES, resources, count, usage, stages);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, MemorySegment range) {
        LR.invokeExact(id, EXECUTE_COMMANDS_IN_BUFFER_WITH_RANGE, buffer.getId(), range);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, MTLBuffer indirectBuffer,
            long indirectBufferOffset) {
        LLL.invokeExact(id, EXECUTE_COMMANDS_IN_BUFFER_INDIRECT_BUFFER_INDIRECT_BUFFER_OFFSET,
                buffer.getId(), indirectBuffer.getId(), indirectBufferOffset);
    }

    @SneakyThrows
    public void memoryBarrierWithScope(long scope, long after, long before) {
        LLL.invokeExact(id, MEMORY_BARRIER_WITH_SCOPE_AFTER_STAGES_BEFORE_STAGES, scope, after, before);
    }

    @SneakyThrows
    public void memoryBarrierWithResources(MemorySegment resources, long count, long after, long before) {
        ALLL.invokeExact(id, MEMORY_BARRIER_WITH_RESOURCES_COUNT_AFTER_STAGES_BEFORE_STAGES, resources,
                count, after, before);
    }

    @SneakyThrows
    public void sampleCountersInBuffer(MTLCounterSampleBuffer sampleBuffer, long sampleIndex, boolean barrier) {
        LLB.invokeExact(id, SAMPLE_COUNTERS_IN_BUFFER_AT_SAMPLE_INDEX_WITH_BARRIER, sampleBuffer.getId(),
                sampleIndex, barrier);
    }

    @SneakyThrows
    public void setVertexAccelerationStructure(MTLAccelerationStructure structure, long index) {
        LL.invokeExact(id, SET_VERTEX_ACCELERATION_STRUCTURE_AT_BUFFER_INDEX, structure.getId(), index);
    }

    @SneakyThrows
    public void setFragmentAccelerationStructure(MTLAccelerationStructure structure, long index) {
        LL.invokeExact(id, SET_FRAGMENT_ACCELERATION_STRUCTURE_AT_BUFFER_INDEX, structure.getId(), index);
    }

    @SneakyThrows
    public void setTileAccelerationStructure(MTLAccelerationStructure structure, long index) {
        LL.invokeExact(id, SET_TILE_ACCELERATION_STRUCTURE_AT_BUFFER_INDEX, structure.getId(), index);
    }

    @SneakyThrows
    public void setVertexIntersectionFunctionTable(MTLIntersectionFunctionTable table, long index) {
        LL.invokeExact(id, SET_VERTEX_INTERSECTION_FUNCTION_TABLE_AT_BUFFER_INDEX, table.getId(), index);
    }

    @SneakyThrows
    public void setFragmentIntersectionFunctionTable(MTLIntersectionFunctionTable table, long index) {
        LL.invokeExact(id, SET_FRAGMENT_INTERSECTION_FUNCTION_TABLE_AT_BUFFER_INDEX, table.getId(), index);
    }

    @SneakyThrows
    public void updateFence(MTLFence fence, long stages) {
        LL.invokeExact(id, UPDATE_FENCE_AFTER_STAGES, fence.getId(), stages);
    }

    @SneakyThrows
    public void waitForFence(MTLFence fence, long stages) {
        LL.invokeExact(id, WAIT_FOR_FENCE_BEFORE_STAGES, fence.getId(), stages);
    }

    @SneakyThrows
    public void setVertexVisibleFunctionTable(MTLVisibleFunctionTable table, long index) {
        LL.invokeExact(id, SET_VERTEX_VISIBLE_FUNCTION_TABLE_AT_BUFFER_INDEX, table.getId(), index);
    }

    @SneakyThrows
    public void setFragmentVisibleFunctionTable(MTLVisibleFunctionTable table, long index) {
        LL.invokeExact(id, SET_FRAGMENT_VISIBLE_FUNCTION_TABLE_AT_BUFFER_INDEX, table.getId(), index);
    }

    @SneakyThrows
    public void useHeap(MTLHeap heap) {
        L.invokeExact(id, USE_HEAP, heap.getId());
    }

    @SneakyThrows
    public void useHeaps(MemorySegment heaps, long count) {
        AL.invokeExact(id, USE_HEAPS_COUNT, heaps, count);
    }

    @SneakyThrows
    public void useHeap(MTLHeap heap, long stages) {
        LL.invokeExact(id, USE_HEAP_STAGES, heap.getId(), stages);
    }

    @SneakyThrows
    public void useHeaps(MemorySegment heaps, long count, long stages) {
        ALL.invokeExact(id, USE_HEAPS_COUNT_STAGES, heaps, count, stages);
    }
}
