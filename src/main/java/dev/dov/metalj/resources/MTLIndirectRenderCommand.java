package dev.dov.metalj.resources;

import dev.dov.metalj.objc.NSObject;
import dev.dov.metalj.objc.ObjC;
import dev.dov.metalj.pipelines.MTLRenderPipelineState;
import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import lombok.SneakyThrows;

public class MTLIndirectRenderCommand extends NSObject {
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
        L.invokeExact(id, ObjC.sel("setRenderPipelineState:"), pipelineState.getId());
    }

    @SneakyThrows
    public void setVertexBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, ObjC.sel("setVertexBuffer:offset:atIndex:"), buffer.getId(), offset, index);
    }

    @SneakyThrows
    public void setFragmentBuffer(MTLBuffer buffer, long offset, long index) {
        LLL.invokeExact(id, ObjC.sel("setFragmentBuffer:offset:atIndex:"), buffer.getId(), offset, index);
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
        LLLLLLLL.invokeExact(id, ObjC.sel("drawIndexedPrimitives:indexCount:indexType:indexBuffer:"
                + "indexBufferOffset:instanceCount:baseVertex:baseInstance:"), primitiveType, indexCount,
                indexType, indexBuffer.getId(), indexBufferOffset, instanceCount, baseVertex, baseInstance);
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

    public void reset() {
        sendVoid(id, "reset");
    }
}
