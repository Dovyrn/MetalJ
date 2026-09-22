package dev.dov.metalj.metal4.encoders;

import dev.dov.metalj.commands.encoders.MTLScissorRect;
import dev.dov.metalj.commands.encoders.MTLViewport;
import dev.dov.metalj.metal4.MTL4ArgumentTable;
import dev.dov.metalj.metal4.counters.MTL4CounterHeap;
import dev.dov.metalj.objc.NSRange;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.depth.MTLDepthStencilState;
import dev.dov.metalj.pipelines.render.MTLLogicalToPhysicalColorAttachmentMap;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineState;
import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.indirect.MTLIndirectCommandBuffer;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTL4RenderCommandEncoder extends MTL4CommandEncoder {
    private static final long DRAW_INDEXED_PRIMITIVES_INDEX_COUNT_INDEX_TYPE_INDEX_BUFFER_INDEX_BUFFER_LENGTH_INSTANCE_COUNT = ObjC.sel("drawIndexedPrimitives:indexCount:indexType:indexBuffer:indexBufferLength:"
                + "instanceCount:");
    private static final long DRAW_INDEXED_PRIMITIVES_INDEX_COUNT_INDEX_TYPE_INDEX_BUFFER_INDEX_BUFFER_LENGTH_INSTANCE_COUNT_BASE_VERTEX_BASE_INSTANCE = ObjC.sel("drawIndexedPrimitives:indexCount:indexType:indexBuffer:indexBufferLength:"
                + "instanceCount:baseVertex:baseInstance:");
    private static final long DRAW_INDEXED_PRIMITIVES_INDEX_TYPE_INDEX_BUFFER_INDEX_BUFFER_LENGTH_INDIRECT_BUFFER = ObjC.sel("drawIndexedPrimitives:indexType:indexBuffer:indexBufferLength:"
                + "indirectBuffer:");
    private static final long DRAW_MESH_THREADGROUPS_WITH_INDIRECT_BUFFER_THREADS_PER_OBJECT_THREADGROUP_THREADS_PER_MESH_THREADGROUP = ObjC.sel("drawMeshThreadgroupsWithIndirectBuffer:threadsPerObjectThreadgroup:"
                + "threadsPerMeshThreadgroup:");

    private static final long DISPATCH_THREADS_PER_TILE = ObjC.sel("dispatchThreadsPerTile:");
    private static final long DRAW_INDEXED_PRIMITIVES_INDEX_COUNT_INDEX_TYPE_INDEX_BUFFER_INDEX_BUFFER_LENGTH = ObjC.sel("drawIndexedPrimitives:indexCount:indexType:indexBuffer:indexBufferLength:");
    private static final long DRAW_MESH_THREADGROUPS_THREADS_PER_OBJECT_THREADGROUP_THREADS_PER_MESH_THREADGROUP = ObjC.sel("drawMeshThreadgroups:threadsPerObjectThreadgroup:threadsPerMeshThreadgroup:");
    private static final long DRAW_MESH_THREADS_THREADS_PER_OBJECT_THREADGROUP_THREADS_PER_MESH_THREADGROUP = ObjC.sel("drawMeshThreads:threadsPerObjectThreadgroup:threadsPerMeshThreadgroup:");
    private static final long DRAW_PRIMITIVES_INDIRECT_BUFFER = ObjC.sel("drawPrimitives:indirectBuffer:");
    private static final long DRAW_PRIMITIVES_VERTEX_START_VERTEX_COUNT = ObjC.sel("drawPrimitives:vertexStart:vertexCount:");
    private static final long DRAW_PRIMITIVES_VERTEX_START_VERTEX_COUNT_INSTANCE_COUNT = ObjC.sel("drawPrimitives:vertexStart:vertexCount:instanceCount:");
    private static final long DRAW_PRIMITIVES_VERTEX_START_VERTEX_COUNT_INSTANCE_COUNT_BASE_INSTANCE = ObjC.sel("drawPrimitives:vertexStart:vertexCount:instanceCount:baseInstance:");
    private static final long EXECUTE_COMMANDS_IN_BUFFER_INDIRECT_BUFFER = ObjC.sel("executeCommandsInBuffer:indirectBuffer:");
    private static final long EXECUTE_COMMANDS_IN_BUFFER_WITH_RANGE = ObjC.sel("executeCommandsInBuffer:withRange:");
    private static final long SET_ARGUMENT_TABLE_AT_STAGES = ObjC.sel("setArgumentTable:atStages:");
    private static final long SET_BLEND_COLOR_RED_GREEN_BLUE_ALPHA = ObjC.sel("setBlendColorRed:green:blue:alpha:");
    private static final long SET_COLOR_ATTACHMENT_MAP = ObjC.sel("setColorAttachmentMap:");
    private static final long SET_COLOR_STORE_ACTION_AT_INDEX = ObjC.sel("setColorStoreAction:atIndex:");
    private static final long SET_CULL_MODE = ObjC.sel("setCullMode:");
    private static final long SET_DEPTH_BIAS_SLOPE_SCALE_CLAMP = ObjC.sel("setDepthBias:slopeScale:clamp:");
    private static final long SET_DEPTH_CLIP_MODE = ObjC.sel("setDepthClipMode:");
    private static final long SET_DEPTH_STENCIL_STATE = ObjC.sel("setDepthStencilState:");
    private static final long SET_DEPTH_STORE_ACTION = ObjC.sel("setDepthStoreAction:");
    private static final long SET_DEPTH_TEST_MIN_BOUND_MAX_BOUND = ObjC.sel("setDepthTestMinBound:maxBound:");
    private static final long SET_FRONT_FACING_WINDING = ObjC.sel("setFrontFacingWinding:");
    private static final long SET_OBJECT_THREADGROUP_MEMORY_LENGTH_AT_INDEX = ObjC.sel("setObjectThreadgroupMemoryLength:atIndex:");
    private static final long SET_RENDER_PIPELINE_STATE = ObjC.sel("setRenderPipelineState:");
    private static final long SET_SCISSOR_RECT = ObjC.sel("setScissorRect:");
    private static final long SET_SCISSOR_RECTS_COUNT = ObjC.sel("setScissorRects:count:");
    private static final long SET_STENCIL_FRONT_REFERENCE_VALUE_BACK_REFERENCE_VALUE = ObjC.sel("setStencilFrontReferenceValue:backReferenceValue:");
    private static final long SET_STENCIL_REFERENCE_VALUE = ObjC.sel("setStencilReferenceValue:");
    private static final long SET_STENCIL_STORE_ACTION = ObjC.sel("setStencilStoreAction:");
    private static final long SET_THREADGROUP_MEMORY_LENGTH_OFFSET_AT_INDEX = ObjC.sel("setThreadgroupMemoryLength:offset:atIndex:");
    private static final long SET_TRIANGLE_FILL_MODE = ObjC.sel("setTriangleFillMode:");
    private static final long SET_VERTEX_AMPLIFICATION_COUNT_VIEW_MAPPINGS = ObjC.sel("setVertexAmplificationCount:viewMappings:");
    private static final long SET_VIEWPORT = ObjC.sel("setViewport:");
    private static final long SET_VIEWPORTS_COUNT = ObjC.sel("setViewports:count:");
    private static final long SET_VISIBILITY_RESULT_MODE_OFFSET = ObjC.sel("setVisibilityResultMode:offset:");
    private static final long TILE_HEIGHT = ObjC.sel("tileHeight");
    private static final long TILE_WIDTH = ObjC.sel("tileWidth");
    private static final long WRITE_TIMESTAMP_WITH_GRANULARITY_AFTER_STAGE_INTO_HEAP_AT_INDEX = ObjC.sel("writeTimestampWithGranularity:afterStage:intoHeap:atIndex:");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle I = handle(null, ObjC.INT);
    private static final MethodHandle II = handle(null, ObjC.INT, ObjC.INT);
    private static final MethodHandle LL = handle(null, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLLLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            ObjC.LONG);
    private static final MethodHandle L8 = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LA = handle(null, ObjC.LONG, ValueLayout.ADDRESS);
    private static final MethodHandle AL = handle(null, ValueLayout.ADDRESS, ObjC.LONG);
    private static final MethodHandle LR = handle(null, ObjC.LONG, NSRange.LAYOUT);
    private static final MethodHandle FF = handle(null, ObjC.FLOAT, ObjC.FLOAT);
    private static final MethodHandle FFF = handle(null, ObjC.FLOAT, ObjC.FLOAT, ObjC.FLOAT);
    private static final MethodHandle FFFF = handle(null, ObjC.FLOAT, ObjC.FLOAT, ObjC.FLOAT, ObjC.FLOAT);
    private static final MethodHandle VIEWPORT = handle(null, MTLViewport.LAYOUT);
    private static final MethodHandle SCISSOR = handle(null, MTLScissorRect.LAYOUT);
    private static final MethodHandle S = handle(null, MTLSize.LAYOUT);
    private static final MethodHandle SSS = handle(null, MTLSize.LAYOUT, MTLSize.LAYOUT, MTLSize.LAYOUT);
    private static final MethodHandle LSS = handle(null, ObjC.LONG, MTLSize.LAYOUT, MTLSize.LAYOUT);

    private MTL4RenderCommandEncoder(long id) {
        super(id);
    }

    public static MTL4RenderCommandEncoder of(long id) {
        return new MTL4RenderCommandEncoder(id);
    }

    public long tileWidth() {
        return sendLong(id, TILE_WIDTH);
    }

    public long tileHeight() {
        return sendLong(id, TILE_HEIGHT);
    }

    @SneakyThrows
    public void setColorAttachmentMap(MTLLogicalToPhysicalColorAttachmentMap map) {
        L.invokeExact(id, SET_COLOR_ATTACHMENT_MAP, map.getId());
    }

    @SneakyThrows
    public void setRenderPipelineState(MTLRenderPipelineState state) {
        L.invokeExact(id, SET_RENDER_PIPELINE_STATE, state.getId());
    }

    @SneakyThrows
    public void setArgumentTable(MTL4ArgumentTable table, long stages) {
        LL.invokeExact(id, SET_ARGUMENT_TABLE_AT_STAGES, table.getId(), stages);
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
    public void setVertexAmplificationCount(long count, MemorySegment mappings) {
        LA.invokeExact(id, SET_VERTEX_AMPLIFICATION_COUNT_VIEW_MAPPINGS, count, mappings);
    }

    @SneakyThrows
    public void setCullMode(long mode) {
        L.invokeExact(id, SET_CULL_MODE, mode);
    }

    @SneakyThrows
    public void setDepthClipMode(long mode) {
        L.invokeExact(id, SET_DEPTH_CLIP_MODE, mode);
    }

    @SneakyThrows
    public void setDepthBias(float bias, float slopeScale, float clamp) {
        FFF.invokeExact(id, SET_DEPTH_BIAS_SLOPE_SCALE_CLAMP, bias, slopeScale, clamp);
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
    public void setScissorRects(MemorySegment rects, long count) {
        AL.invokeExact(id, SET_SCISSOR_RECTS_COUNT, rects, count);
    }

    @SneakyThrows
    public void setTriangleFillMode(long mode) {
        L.invokeExact(id, SET_TRIANGLE_FILL_MODE, mode);
    }

    @SneakyThrows
    public void setFrontFacingWinding(long winding) {
        L.invokeExact(id, SET_FRONT_FACING_WINDING, winding);
    }

    @SneakyThrows
    public void setBlendColorRed(float red, float green, float blue, float alpha) {
        FFFF.invokeExact(id, SET_BLEND_COLOR_RED_GREEN_BLUE_ALPHA, red, green, blue, alpha);
    }

    @SneakyThrows
    public void setDepthStencilState(MTLDepthStencilState state) {
        L.invokeExact(id, SET_DEPTH_STENCIL_STATE, state.getId());
    }

    @SneakyThrows
    public void setStencilReferenceValue(int value) {
        I.invokeExact(id, SET_STENCIL_REFERENCE_VALUE, value);
    }

    @SneakyThrows
    public void setStencilFrontReferenceValue(int front, int back) {
        II.invokeExact(id, SET_STENCIL_FRONT_REFERENCE_VALUE_BACK_REFERENCE_VALUE, front, back);
    }

    @SneakyThrows
    public void setVisibilityResultMode(long mode, long offset) {
        LL.invokeExact(id, SET_VISIBILITY_RESULT_MODE_OFFSET, mode, offset);
    }

    @SneakyThrows
    public void setColorStoreAction(long action, long index) {
        LL.invokeExact(id, SET_COLOR_STORE_ACTION_AT_INDEX, action, index);
    }

    @SneakyThrows
    public void setDepthStoreAction(long action) {
        L.invokeExact(id, SET_DEPTH_STORE_ACTION, action);
    }

    @SneakyThrows
    public void setStencilStoreAction(long action) {
        L.invokeExact(id, SET_STENCIL_STORE_ACTION, action);
    }

    @SneakyThrows
    public void drawPrimitives(long type, long vertexStart, long vertexCount) {
        LLL.invokeExact(id, DRAW_PRIMITIVES_VERTEX_START_VERTEX_COUNT, type, vertexStart, vertexCount);
    }

    @SneakyThrows
    public void drawPrimitives(long type, long vertexStart, long vertexCount, long instanceCount) {
        LLLL.invokeExact(id, DRAW_PRIMITIVES_VERTEX_START_VERTEX_COUNT_INSTANCE_COUNT, type, vertexStart,
                vertexCount, instanceCount);
    }

    @SneakyThrows
    public void drawPrimitives(long type, long vertexStart, long vertexCount, long instanceCount,
            long baseInstance) {
        LLLLL.invokeExact(id, DRAW_PRIMITIVES_VERTEX_START_VERTEX_COUNT_INSTANCE_COUNT_BASE_INSTANCE, type,
                vertexStart, vertexCount, instanceCount, baseInstance);
    }

    @SneakyThrows
    public void drawPrimitives(long type, long indirectBuffer) {
        LL.invokeExact(id, DRAW_PRIMITIVES_INDIRECT_BUFFER, type, indirectBuffer);
    }

    @SneakyThrows
    public void drawIndexedPrimitives(long type, long indexCount, long indexType, long indexBuffer,
            long indexBufferLength) {
        LLLLL.invokeExact(id, DRAW_INDEXED_PRIMITIVES_INDEX_COUNT_INDEX_TYPE_INDEX_BUFFER_INDEX_BUFFER_LENGTH,
                type, indexCount, indexType, indexBuffer, indexBufferLength);
    }

    @SneakyThrows
    public void drawIndexedPrimitives(long type, long indexCount, long indexType, long indexBuffer,
            long indexBufferLength, long instanceCount) {
        LLLLLL.invokeExact(id, DRAW_INDEXED_PRIMITIVES_INDEX_COUNT_INDEX_TYPE_INDEX_BUFFER_INDEX_BUFFER_LENGTH_INSTANCE_COUNT,
                type, indexCount, indexType, indexBuffer, indexBufferLength, instanceCount);
    }

    @SneakyThrows
    public void drawIndexedPrimitives(long type, long indexCount, long indexType, long indexBuffer,
            long indexBufferLength, long instanceCount, long baseVertex, long baseInstance) {
        L8.invokeExact(id, DRAW_INDEXED_PRIMITIVES_INDEX_COUNT_INDEX_TYPE_INDEX_BUFFER_INDEX_BUFFER_LENGTH_INSTANCE_COUNT_BASE_VERTEX_BASE_INSTANCE,
                type, indexCount, indexType, indexBuffer, indexBufferLength, instanceCount, baseVertex,
                baseInstance);
    }

    @SneakyThrows
    public void drawIndexedPrimitivesWithIndirectBuffer(long type, long indexType, long indexBuffer,
            long indexBufferLength, long indirectBuffer) {
        LLLLL.invokeExact(id, DRAW_INDEXED_PRIMITIVES_INDEX_TYPE_INDEX_BUFFER_INDEX_BUFFER_LENGTH_INDIRECT_BUFFER,
                type, indexType, indexBuffer, indexBufferLength, indirectBuffer);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, MemorySegment range) {
        LR.invokeExact(id, EXECUTE_COMMANDS_IN_BUFFER_WITH_RANGE, buffer.getId(), range);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, long indirectBuffer) {
        LL.invokeExact(id, EXECUTE_COMMANDS_IN_BUFFER_INDIRECT_BUFFER, buffer.getId(), indirectBuffer);
    }

    @SneakyThrows
    public void setObjectThreadgroupMemoryLength(long length, long index) {
        LL.invokeExact(id, SET_OBJECT_THREADGROUP_MEMORY_LENGTH_AT_INDEX, length, index);
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length, long offset, long index) {
        LLL.invokeExact(id, SET_THREADGROUP_MEMORY_LENGTH_OFFSET_AT_INDEX, length, offset, index);
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
    public void drawMeshThreadgroupsWithIndirectBuffer(long indirectBuffer,
            MemorySegment threadsPerObjectThreadgroup, MemorySegment threadsPerMeshThreadgroup) {
        LSS.invokeExact(id, DRAW_MESH_THREADGROUPS_WITH_INDIRECT_BUFFER_THREADS_PER_OBJECT_THREADGROUP_THREADS_PER_MESH_THREADGROUP,
                indirectBuffer, threadsPerObjectThreadgroup, threadsPerMeshThreadgroup);
    }

    @SneakyThrows
    public void dispatchThreadsPerTile(MemorySegment threadsPerTile) {
        S.invokeExact(id, DISPATCH_THREADS_PER_TILE, threadsPerTile);
    }

    @SneakyThrows
    public void writeTimestampWithGranularity(long granularity, long stage, MTL4CounterHeap heap, long index) {
        LLLL.invokeExact(id, WRITE_TIMESTAMP_WITH_GRANULARITY_AFTER_STAGE_INTO_HEAP_AT_INDEX, granularity,
                stage, heap.getId(), index);
    }
}
