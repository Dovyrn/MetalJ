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
        return sendLong(id, "tileWidth");
    }

    public long tileHeight() {
        return sendLong(id, "tileHeight");
    }

    @SneakyThrows
    public void setColorAttachmentMap(MTLLogicalToPhysicalColorAttachmentMap map) {
        L.invokeExact(id, ObjC.sel("setColorAttachmentMap:"), map.getId());
    }

    @SneakyThrows
    public void setRenderPipelineState(MTLRenderPipelineState state) {
        L.invokeExact(id, ObjC.sel("setRenderPipelineState:"), state.getId());
    }

    @SneakyThrows
    public void setArgumentTable(MTL4ArgumentTable table, long stages) {
        LL.invokeExact(id, ObjC.sel("setArgumentTable:atStages:"), table.getId(), stages);
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
    public void setVertexAmplificationCount(long count, MemorySegment mappings) {
        LA.invokeExact(id, ObjC.sel("setVertexAmplificationCount:viewMappings:"), count, mappings);
    }

    @SneakyThrows
    public void setCullMode(long mode) {
        L.invokeExact(id, ObjC.sel("setCullMode:"), mode);
    }

    @SneakyThrows
    public void setDepthClipMode(long mode) {
        L.invokeExact(id, ObjC.sel("setDepthClipMode:"), mode);
    }

    @SneakyThrows
    public void setDepthBias(float bias, float slopeScale, float clamp) {
        FFF.invokeExact(id, ObjC.sel("setDepthBias:slopeScale:clamp:"), bias, slopeScale, clamp);
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
    public void setScissorRects(MemorySegment rects, long count) {
        AL.invokeExact(id, ObjC.sel("setScissorRects:count:"), rects, count);
    }

    @SneakyThrows
    public void setTriangleFillMode(long mode) {
        L.invokeExact(id, ObjC.sel("setTriangleFillMode:"), mode);
    }

    @SneakyThrows
    public void setFrontFacingWinding(long winding) {
        L.invokeExact(id, ObjC.sel("setFrontFacingWinding:"), winding);
    }

    @SneakyThrows
    public void setBlendColorRed(float red, float green, float blue, float alpha) {
        FFFF.invokeExact(id, ObjC.sel("setBlendColorRed:green:blue:alpha:"), red, green, blue, alpha);
    }

    @SneakyThrows
    public void setDepthStencilState(MTLDepthStencilState state) {
        L.invokeExact(id, ObjC.sel("setDepthStencilState:"), state.getId());
    }

    @SneakyThrows
    public void setStencilReferenceValue(int value) {
        I.invokeExact(id, ObjC.sel("setStencilReferenceValue:"), value);
    }

    @SneakyThrows
    public void setStencilFrontReferenceValue(int front, int back) {
        II.invokeExact(id, ObjC.sel("setStencilFrontReferenceValue:backReferenceValue:"), front, back);
    }

    @SneakyThrows
    public void setVisibilityResultMode(long mode, long offset) {
        LL.invokeExact(id, ObjC.sel("setVisibilityResultMode:offset:"), mode, offset);
    }

    @SneakyThrows
    public void setColorStoreAction(long action, long index) {
        LL.invokeExact(id, ObjC.sel("setColorStoreAction:atIndex:"), action, index);
    }

    @SneakyThrows
    public void setDepthStoreAction(long action) {
        L.invokeExact(id, ObjC.sel("setDepthStoreAction:"), action);
    }

    @SneakyThrows
    public void setStencilStoreAction(long action) {
        L.invokeExact(id, ObjC.sel("setStencilStoreAction:"), action);
    }

    @SneakyThrows
    public void drawPrimitives(long type, long vertexStart, long vertexCount) {
        LLL.invokeExact(id, ObjC.sel("drawPrimitives:vertexStart:vertexCount:"), type, vertexStart, vertexCount);
    }

    @SneakyThrows
    public void drawPrimitives(long type, long vertexStart, long vertexCount, long instanceCount) {
        LLLL.invokeExact(id, ObjC.sel("drawPrimitives:vertexStart:vertexCount:instanceCount:"), type, vertexStart,
                vertexCount, instanceCount);
    }

    @SneakyThrows
    public void drawPrimitives(long type, long vertexStart, long vertexCount, long instanceCount,
            long baseInstance) {
        LLLLL.invokeExact(id, ObjC.sel("drawPrimitives:vertexStart:vertexCount:instanceCount:baseInstance:"), type,
                vertexStart, vertexCount, instanceCount, baseInstance);
    }

    @SneakyThrows
    public void drawPrimitives(long type, long indirectBuffer) {
        LL.invokeExact(id, ObjC.sel("drawPrimitives:indirectBuffer:"), type, indirectBuffer);
    }

    @SneakyThrows
    public void drawIndexedPrimitives(long type, long indexCount, long indexType, long indexBuffer,
            long indexBufferLength) {
        LLLLL.invokeExact(id, ObjC.sel("drawIndexedPrimitives:indexCount:indexType:indexBuffer:indexBufferLength:"),
                type, indexCount, indexType, indexBuffer, indexBufferLength);
    }

    @SneakyThrows
    public void drawIndexedPrimitives(long type, long indexCount, long indexType, long indexBuffer,
            long indexBufferLength, long instanceCount) {
        LLLLLL.invokeExact(id, ObjC.sel("drawIndexedPrimitives:indexCount:indexType:indexBuffer:indexBufferLength:"
                + "instanceCount:"),
                type, indexCount, indexType, indexBuffer, indexBufferLength, instanceCount);
    }

    @SneakyThrows
    public void drawIndexedPrimitives(long type, long indexCount, long indexType, long indexBuffer,
            long indexBufferLength, long instanceCount, long baseVertex, long baseInstance) {
        L8.invokeExact(id, ObjC.sel("drawIndexedPrimitives:indexCount:indexType:indexBuffer:indexBufferLength:"
                + "instanceCount:baseVertex:baseInstance:"),
                type, indexCount, indexType, indexBuffer, indexBufferLength, instanceCount, baseVertex,
                baseInstance);
    }

    @SneakyThrows
    public void drawIndexedPrimitivesWithIndirectBuffer(long type, long indexType, long indexBuffer,
            long indexBufferLength, long indirectBuffer) {
        LLLLL.invokeExact(id, ObjC.sel("drawIndexedPrimitives:indexType:indexBuffer:indexBufferLength:"
                + "indirectBuffer:"),
                type, indexType, indexBuffer, indexBufferLength, indirectBuffer);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, MemorySegment range) {
        LR.invokeExact(id, ObjC.sel("executeCommandsInBuffer:withRange:"), buffer.getId(), range);
    }

    @SneakyThrows
    public void executeCommandsInBuffer(MTLIndirectCommandBuffer buffer, long indirectBuffer) {
        LL.invokeExact(id, ObjC.sel("executeCommandsInBuffer:indirectBuffer:"), buffer.getId(), indirectBuffer);
    }

    @SneakyThrows
    public void setObjectThreadgroupMemoryLength(long length, long index) {
        LL.invokeExact(id, ObjC.sel("setObjectThreadgroupMemoryLength:atIndex:"), length, index);
    }

    @SneakyThrows
    public void setThreadgroupMemoryLength(long length, long offset, long index) {
        LLL.invokeExact(id, ObjC.sel("setThreadgroupMemoryLength:offset:atIndex:"), length, offset, index);
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
    public void drawMeshThreadgroupsWithIndirectBuffer(long indirectBuffer,
            MemorySegment threadsPerObjectThreadgroup, MemorySegment threadsPerMeshThreadgroup) {
        LSS.invokeExact(id, ObjC.sel("drawMeshThreadgroupsWithIndirectBuffer:threadsPerObjectThreadgroup:"
                + "threadsPerMeshThreadgroup:"),
                indirectBuffer, threadsPerObjectThreadgroup, threadsPerMeshThreadgroup);
    }

    @SneakyThrows
    public void dispatchThreadsPerTile(MemorySegment threadsPerTile) {
        S.invokeExact(id, ObjC.sel("dispatchThreadsPerTile:"), threadsPerTile);
    }

    @SneakyThrows
    public void writeTimestampWithGranularity(long granularity, long stage, MTL4CounterHeap heap, long index) {
        LLLL.invokeExact(id, ObjC.sel("writeTimestampWithGranularity:afterStage:intoHeap:atIndex:"), granularity,
                stage, heap.getId(), index);
    }
}
