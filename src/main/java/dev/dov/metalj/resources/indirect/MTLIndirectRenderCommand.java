package dev.dov.metalj.resources.indirect;

import dev.dov.metalj.resources.MTLSize;
import dev.dov.metalj.resources.buffers.MTLBuffer;
import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.render.MTLRenderPipelineState;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIndirectRenderCommand extends NSObject {
    private static final long DRAW_INDEXED_PRIMITIVES_INDEX_COUNT_INDEX_TYPE_INDEX_BUFFER_INDEX_BUFFER_OFFSET_INSTANCE_COUNT_BASE_VERTEX_BASE_INSTANCE = ObjC.sel("drawIndexedPrimitives:indexCount:indexType:indexBuffer:"
            + "indexBufferOffset:instanceCount:baseVertex:baseInstance:");

    private static final long DRAW_INDEXED_PRIMITIVES = DRAW_INDEXED_PRIMITIVES_INDEX_COUNT_INDEX_TYPE_INDEX_BUFFER_INDEX_BUFFER_OFFSET_INSTANCE_COUNT_BASE_VERTEX_BASE_INSTANCE;
    private static final long DRAW_MESH_THREADGROUPS_THREADS_PER_OBJECT_THREADGROUP_THREADS_PER_MESH_THREADGROUP = ObjC.sel("drawMeshThreadgroups:threadsPerObjectThreadgroup:threadsPerMeshThreadgroup:");
    private static final long DRAW_MESH_THREADS_THREADS_PER_OBJECT_THREADGROUP_THREADS_PER_MESH_THREADGROUP = ObjC.sel("drawMeshThreads:threadsPerObjectThreadgroup:threadsPerMeshThreadgroup:");
    private static final long DRAW_PRIMITIVES_VERTEX_START_VERTEX_COUNT_INSTANCE_COUNT_BASE_INSTANCE = ObjC.sel("drawPrimitives:vertexStart:vertexCount:instanceCount:baseInstance:");
    private static final long RESET = ObjC.sel("reset");
    private static final long SET_FRAGMENT_BUFFER_OFFSET_AT_INDEX = ObjC.sel("setFragmentBuffer:offset:atIndex:");
    private static final long SET_RENDER_PIPELINE_STATE = ObjC.sel("setRenderPipelineState:");
    private static final long SET_VERTEX_BUFFER_OFFSET_AT_INDEX = ObjC.sel("setVertexBuffer:offset:atIndex:");

    private static final MethodHandle L = handle(null, ObjC.LONG);
    private static final MethodHandle LLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle LLLLLLLL = handle(null, ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG,
            ObjC.LONG, ObjC.LONG, ObjC.LONG, ObjC.LONG);
    private static final MethodHandle SSS = handle(null, MTLSize.LAYOUT, MTLSize.LAYOUT, MTLSize.LAYOUT);

    private MTLIndirectRenderCommand(long id) {
        super(id);
    }

    public static MTLIndirectRenderCommand of(long id) {
        return new MTLIndirectRenderCommand(id);
    }

    @SneakyThrows
    public void setRenderPipelineState(MTLRenderPipelineState pipelineState) {
        L.invokeExact(id, SET_RENDER_PIPELINE_STATE, pipelineState.getId());
    }

    @SneakyThrows
    public void setVertexBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, SET_VERTEX_BUFFER_OFFSET_AT_INDEX, buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setFragmentBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, SET_FRAGMENT_BUFFER_OFFSET_AT_INDEX, buffer.getId(), offset, index);
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
        LLLLLLLL.invokeExact(id, DRAW_INDEXED_PRIMITIVES, primitiveType, indexCount,
                indexType, indexBuffer.getId(), indexBufferOffset, instanceCount, baseVertex, baseInstance);
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

    public void reset() {
        sendVoid(id, RESET);
    }
}
